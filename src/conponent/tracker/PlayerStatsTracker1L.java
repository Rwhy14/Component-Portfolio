package conponent.tracker;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <p>
 * <b>Convention:</b><br>
 * - {@code stats} is not null<br>
 * - No keys in {@code stats} are null or empty strings<br>
 * - {@code totalGames} &gt;= 0<br>
 * - {@code 0.0 &lt;= wins &lt;= totalGames}
 *
 * <p>
 * <b>Correspondence:</b><br>
 * - {@code this} maps to (stats, totalGames, wins)<br>
 * - The {@code stats} map holds stat name → accumulated value<br>
 * - {@code totalGames} is the total number of games recorded<br>
 * - {@code wins} is the total number of games won
 *
 * <p>
 * This implementation uses standard Java collections and basic primitive types
 * to track statistics and win records for a basketball player.
 *
 * @author Ryan Chou
 */
public class PlayerStatsTracker1L extends PlayerStatsTrackerSecondary {

    /**
     * Stores player stat totals as a mapping from stat name (e.g., "points") to
     * accumulated value.
     */
    private Map<String, Double> stats;

    /**
     * Total number of games the player has participated in.
     */
    private int totalGames;

    /**
     * Total number of games the player has won.
     */
    private double wins;

    /**
     * Constructs an empty {@code PlayerStatsTracker1L} object with no stats and
     * no games recorded.
     *
     * @ensures stats is empty; totalGames = 0; wins = 0.0
     */
    public PlayerStatsTracker1L() {
        this.stats = new HashMap<>();
        this.totalGames = 0;
        this.wins = 0.0;
    }

    /**
     * Clears all player data, resetting the tracker to its initial state.
     *
     * @ensures stats is empty, totalGames = 0, wins = 0.0
     */
    @Override
    public void clear() {
        this.stats.clear();
        this.totalGames = 0;
        this.wins = 0.0;
    }

    /**
     * Adds or updates a player stat by incrementing its value.
     *
     * @param stat
     *            the stat name (e.g., "points", "rebounds")
     * @param value
     *            the value to add to the stat
     * @requires stat != null and not empty
     * @ensures getStatTotal(stat) = #getStatTotal(stat) + value
     */
    @Override
    public void addStat(String stat, double value) {
        double current = this.stats.getOrDefault(stat, 0.0);
        this.stats.put(stat, current + value);
    }

    /**
     * Removes a stat from the tracker.
     *
     * @param stat
     *            the stat name to remove
     * @requires stat != null
     * @ensures stat is no longer present in the stats map
     */
    @Override
    public void removeStat(String stat) {
        this.stats.remove(stat);
    }

    /**
     * Retrieves the total value for a given stat.
     *
     * @param stat
     *            the name of the stat
     * @return the total value of the stat, or 0.0 if not present
     * @requires stat != null
     * @ensures return = current stat total or 0.0 if stat not tracked
     */
    @Override
    public double getStatTotal(String stat) {
        return this.stats.getOrDefault(stat, 0.0);
    }

    /**
     * Records a game outcome and increments the total games played.
     *
     * @param win
     *            true if the game was won; false if lost
     * @ensures totalGamePlayed() = #totalGamePlayed() + 1<br>
     *          if win, then wins = #wins + 1
     */
    @Override
    public void recordGame(boolean win) {
        this.totalGames++;
        if (win) {
            this.wins++;
        }
    }

    /**
     * Calculates and returns the current win rate.
     *
     * @return the ratio of wins to total games (between 0.0 and 1.0), or 0.0 if
     *         no games played
     * @ensures return = wins / totalGames if totalGames &gt; 0, else 0.0
     */
    @Override
    public double getWinRate() {
        if (this.totalGames == 0) {
            return 0.0;
        }
        return this.wins / this.totalGames;
    }

    /**
     * Returns the total number of games recorded.
     *
     * @return number of games played
     * @ensures return &gt;= 0
     */
    @Override
    public int totalGamePlayed() {
        return this.totalGames;
    }
}
