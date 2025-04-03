package conponent.src;
/**
 * Kernel interface for the PlayerStatsTracker component.
 * <p>
 * Provides minimal operations for tracking player statistics and game outcomes.
 */
public interface Bball {
    /**
     * Adds a value to an existing stat or initializes it if not present.
     *
     * @param stat  The name of the stat (e.g., "points", "rebounds").
     * @param value The value to add to the stat.
     */
    void addStat(String stat, double value);

    /**
     * Removes the specified stat from the tracker.
     *
     * @param stat The name of the stat to remove.
     */
    void removeStat(String stat);

    /**
     * Retrieves the total value of a specified stat.
     *
     * @param stat The name of the stat.
     * @return The total value of the stat, or 0.0 if not present.
     */
    double getStatTotal(String stat);

    /**
     * Records a game result and updates the win count if applicable.
     *
     * @param win True if the game was won; false otherwise.
     */
    void recordGame(boolean win);

    /**
     * Calculates and returns the win rate.
     *
     * @return Win rate as a value between 0.0 and 1.0.
     */
    double getWinRate();

    /**
     * Returns the total number of games recorded.
     *
     * @return The number of games played.
     */
    int totalGamePlayed();
}

