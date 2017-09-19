package com.company;

import java.util.Random;

/**
 * Created by lukes on 06-Aug-17.
 */
public class DeterministicDFSService {

    public DeterministicDFSService(){}

    public int[] getBestMove(Board board, int runs) {
        int bestScore = 0;
        int bestMove = -1;

        for (int dir = 0; dir < 4; dir++){
            int[] vars = spawnMultiRun(board, dir, runs);
            int score = vars[0];
            if (score >= bestScore){
                bestScore = score;
                bestMove = dir;
            }
        }
        return new int[] {bestScore, bestMove};
    }

    private int[] spawnMultiRun(Board board, int dir, int runs) {
        int bestScore = 0;

        int tScore = board.selfScore;
        int tMoves = 0;

        Board[] boardClones = initRuns(board, runs);
        for (int i = 0; i < runs; i++){
            int[] vars = randomRun(boardClones[i], dir);

            int score = vars[0];
            if (score == -1) return new int[]{-1, -1};
            tScore += score;
            tMoves += vars[1];

            if (score >= bestScore){
                bestScore = score;
            }
        }
        return new int[]{tScore/runs, tMoves/runs};
    }

    private Board[] initRuns(Board board, int runs) {
        Board[] boards = new Board[runs];
        for (int i = 0; i < runs; i++) {
            boards[i] = board.clone();
            boards[i].selfScore = board.selfScore;
        }
        return boards;
    }

    private int[] randomRun(Board board, int dir) {
        int score = board.selfScore;
        int moves = 0;
        boolean running = true;
        int mvScore = board.move(Direction.getDir(dir));
        if (mvScore >= 0) board.createTile(false);
        if(mvScore < 0) return new int[]{-1};
        score += mvScore;

        while(running){
            if(!board.hasValidMove())
                break;

            int pick = new Random().nextInt(Direction.values().length);
            Direction curr = Direction.values()[pick];
            mvScore = board.move(curr);
            if(mvScore < 0)
                continue;

            score += mvScore;
            board.createTile(false);
            moves++;
        }
        return new int[]{score, moves};
    }

    public int[] lookAheadDDFS(Board board, int lookAhead) {
        int currentLayerScore = 0;
        int currentBestMove = -1;
        if (lookAhead > 0 && board.hasValidMove()) {
            for (int i = 0; i < 4; i++) {
                if (board.hasValidMove(Direction.getDir(i))) {
                    Board newBoard = board.clone();
                    int mvScore = newBoard.move(Direction.getDir(i));
                    newBoard.createTile(false);
                    int[] forwardMove = lookAheadDDFS(newBoard, lookAhead - 1);
                    if(currentLayerScore < (forwardMove[0] + mvScore)){
                        currentLayerScore = forwardMove[0] + mvScore;
                        currentBestMove = i;
                    }
                }
            }
        }else if(board.hasValidMove()){
            for (int i = 0; i < 4; i++) {
                if(board.hasValidMove(Direction.getDir(i))) {
                    int[] move = spawnMultiRun(board.clone(), i, 200);
                    if (currentLayerScore < move[0]) {
                        currentLayerScore = move[0];
                        currentBestMove = i;
                    }
                }
            }
        }
        return new int[] {currentLayerScore, currentBestMove};
    }
}
