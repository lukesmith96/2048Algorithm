# 2048Algorithm
By: Luke Smith

## Project Overview and Goals:

1. Learn to create a game using JavaFX
2. Experiment with algorithms to beat the game
3. Implement popular AI algorithms to increase win percentage and time efficiency
4. Utilize multithreading for more seamless user experience

This project is a JavaFX implementation of the popular game 2048 with multiple strategies implemented to show
the computer's ability to win the game. Also utilized is multithreading in order to use multiple game states
in the algorithms and allow the user to hot switch game strategies and algorithm timers.

## Strategies:

- Random:

    Make moves in random order no intelligence used

- Corner

    Basic algorithm for using the popular corner priority scheme showing it's
    in effectiveness without any other additional strategy

- User Control

    Allows the user to test their own ability

- Deterministic Depth First Search

    Uses depth first search and random game simulations to average the best next move in the current state
    To do this it spawns 4 'runs' one for each initial direction then simulates an inputted amount of game
    runs using the random moves strategy. It averages all of these runs and chooses the initial direction with
    the highest (run score average / run move average).

- Look Ahead

    Attempts to over come DDFS issue with inability to predict successive moves
    DDFS is random after the first move and lacks an ability to find move series
    This looks ahead of just the first level deciding on multiple levels
    what the move should be hopefully following that path down.

    Currently this process is slow and inefficient compared to DDFS, success rate is lower.

## Future Plans:

- Minimax with pruning

    The next strategy I plan on implementing. A popular game algorithm it will give me experience prioritizing
    tree paths and more advance AI algorithms.

- Statistic Dashboard

    Easy to understand statistic dashboard with statics on implemented strategies, success rate, time efficiency,
    and others.
