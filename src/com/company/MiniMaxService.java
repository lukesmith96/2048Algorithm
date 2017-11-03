package com.company;

import javafx.util.Pair;

import java.util.ArrayList;

/*
 * @author: Luke Smith
 * 
 */
public class MiniMaxService {
    public Pair findBestMove(Board board, int depth, int runs){
        Pair<Integer, Integer> bestMove = maximize(board.clone(), -1, 1, depth, runs);
        return bestMove;
    }

    private Pair maximize(Board board, int alpha, int beta, int depth, int runs) {
        if (!board.hasValidMove()) {
            return new Pair<>(utility(board), null);
        }
        Pair<Integer, Integer> best = null;
        for (Direction d : actions(board)){
            Board newBoard = board.clone();
            newBoard.move(d);
            Pair<Integer, Integer> curr = minimize(newBoard, alpha, beta, depth, runs);
            if (best.getKey() < curr.getKey()){
                best = curr;
            }
            if (best.getKey() >= beta)
                return best;
            alpha = Math.max(beta, best.getKey());
        }
        return best;
    }

    private Pair minimize(Board board, int alpha, int beta, int depth, int runs) {
        int[] best = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[] util = {Integer.MAX_VALUE, Integer.MAX_VALUE};

        ArrayList<Pair<Integer, Pair>> options = new ArrayList<>();
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
        for (int i = 0; i < 2; i++) {
            Board clone = board.clone();
            clone.addCustomTile((int)options.get(best[i]).getValue().getKey(),
                    (int)options.get(best[i]).getValue().getValue(), 2 + (2*1));
            maximize(clone, alpha, beta, depth, runs);
        }
    }

    private ArrayList<Direction> actions(Board board) {
        ArrayList<Direction> d = new ArrayList<>();
        for (Direction dir : Direction.values()){
            if (board.hasValidMove(dir))
                d.add(dir);
        }
        return d;
    }

    private int utility(Board board) {
        return 0;
    }
}
