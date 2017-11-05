package com.company;

import javafx.util.Pair;

import java.util.ArrayList;

/*
 * @author: Luke Smith
 * 
 */
public class MiniMaxService {
    public Direction findBestMove(Board board, int depth, int runs){
        Pair<Float, Direction> bestMove = maximize(board.clone(), -1, 1, depth, runs);
        return bestMove.getValue();
    }

    private Pair maximize(Board board, float alpha, float beta, int depth, int runs) {
        if (!board.hasValidMove() || depth == 0) {
            return new Pair<>(utility(board), null);
        }
        Pair<Float, Direction> best = null;
        for (Direction d : actions(board)){
            Board newBoard = board.clone();
            newBoard.move(d);
            Pair<Float, Direction> curr = minimize(newBoard, alpha, beta, depth - 1, runs);
            if (best == null || best.getKey() < curr.getKey()){
                best = new Pair<>(curr.getKey(), d);
            }
            if (best.getKey() >= beta)
                return best;
            alpha = Math.max(beta, best.getKey());
        }
        return best;
    }

    private Pair minimize(Board board, float alpha, float beta, int depth, int runs) {
        int[] best = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        float[] util = {Float.MAX_VALUE, Float.MAX_VALUE};

        ArrayList<Pair<Float, Pair>> options = new ArrayList<>();
        Board test = board.clone();
        int j = 0;
        for (int i = 0; i < 2; i++) {
            for (Pair<Integer, Integer> action : board.getOpenTiles()) {
                test.addCustomTile(action.getKey(), action.getValue(), 2 + (2*i));
                options.add(new Pair<>(utility(test), action));
                if (util[i] > utility(test)){
                    util[i] = utility(test);
                    best[i] = j;
                }
                test.removeTile(action.getKey(), action.getValue());
                j++;
            }
            j = 0;
        }
        Pair<Float, Direction> bestScore = null;
        for (int i = 0; i < 2; i++) {
            Board clone = board.clone();
            clone.addCustomTile((int)options.get(best[i]).getValue().getKey(),
                    (int)options.get(best[i]).getValue().getValue(), 2 + (2*i));
            Pair<Float, Direction> curr = maximize(clone, alpha, beta, depth, runs);
            if (bestScore == null || bestScore.getKey() > curr.getKey()){
                bestScore = curr;
            }
            if (bestScore.getKey() < alpha)
                return bestScore;
            beta = Math.min(beta, bestScore.getKey());
        }
        return bestScore;
    }

    private ArrayList<Direction> actions(Board board) {
        ArrayList<Direction> d = new ArrayList<>();
        for (Direction dir : Direction.values()){
            if (board.hasValidMove(dir))
                d.add(dir);
        }
        return d;
    }


    private float utility(Board board) {
        float maxutil = board.maxvalue();
        float smoothness = board.findSmoothness();
        float lonecount = board.findLoneGroups();
        // do random runs on the board.
        //?
        return maxutil + smoothness + lonecount;
    }
}
