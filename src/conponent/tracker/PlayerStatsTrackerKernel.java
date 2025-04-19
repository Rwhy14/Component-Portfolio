package conponent.tracker;

import conponent.src.Standard;

/**
 * kernal interface.
 */
public interface PlayerStatsTrackerKernel extends Standard<PlayerStatsTracker> {
    /**
     * Adds or updates a stat by adding the given value to any existing value.
     *
     * @param stat
     *            the name of the stat
     * @param value
     *            the value to add to the stat
     * @requires stat != null and stat is not empty
     * @ensures getStatTotal(stat) = #getStatTotal(stat) + value
     */
    void addStat(String stat, double value);

    /**
     * Removes a stat from the tracker.
     *
     * @param stat
     *            the name of the stat to remove
     * @requires stat != null
     * @ensures stat is no longer a key in the tracker
     */
    void removeStat(String stat);

    /**
     * Returns the total for a given stat; if not found, returns 0.
     *
     * @param stat
     *            the stat name
     * @return total value for the stat
     * @requires stat != null
     * @ensures return = total for stat OR 0 if not present
     */
    double getStatTotal(String stat);

    /**
     * Records the result of a game.
     *
     * @param win
     *            true if the game was a win, false otherwise
     * @ensures totalGamePlayed() = #totalGamePlayed() + 1 AND if win then
     *          getWinRate() increases accordingly
     */
    void recordGame(boolean win);

    /**
     * Returns the win rate as wins / total games.
     *
     * @return win rate
     * @ensures 0 <= return <= 1, returns 0 if no games played
     */
    double getWinRate();

    /**
     * Returns the total number of games played.
     *
     * @return total games played
     * @ensures return >= 0
     */
    int totalGamePlayed();
}
