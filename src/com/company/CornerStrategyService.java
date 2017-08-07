package com.company;

import java.util.ArrayList;

/**
 * Created by lukes on 06-Aug-17.
 */
public class CornerStrategyService {

    public CornerStrategyService(){

    }
    public Direction getBestMove(Board board, Main.GameContext gameContext, int runs) {
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
        System.out.println("Move: " + Direction.getDir(bestMove).toString() + " Best Score: " + bestScore);
        return Direction.getDir(bestMove);
    }

    private int[] spawnMultiRun(Board board, int dir, int runs) {
        int bestScore = 0;

        int total = 0;
        int total_moves = 0;

        for (int i = 0; i < runs; i++){
            int[] vars = randomRun(board, dir);

            int score = vars[0];
            if (score == -1) return new int[]{-1, -1};
            total += score;
            total_moves = vars[1];

            if (score >= bestScore){
                bestScore = score;
            }
        }
        return new int[]{total/runs, total_moves/runs};
    }

    private int[] randomRun(Board board, int dir) {
        int score = 0;
        Board b = board;



        return new int[]{};
    }
}
