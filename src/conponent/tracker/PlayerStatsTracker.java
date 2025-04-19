import java.util.HashMap;
import java.util.Map;

/**
 * It support operations for adding stat values, tracking wins and total games
 * played
 * <p>
 * This class can be used in sports analytics applications to monitor player
 * performance over time.
 *
 * @author Ryan Chou
 */
public class PlayerStatsTracker {
    // This structure is mutable because player stats change after each game.
    /**
     * Map that stores stat names (e.g., "points", "rebounds") and their
     * accumulated values.
     */
    private Map<String, Double> stats;
    /**
     * Total number of games the player has participated in.
     */
    private int totalGames;
    /**
     * Number of games the player won.
     */
    private double wins;

    // Constructor
    /**
     * Constructs an empty {@code PlayerStatsTracker} with no stats and no games
     * recorded.
     *
     * @ensures stats is empty, totalGames = 0, wins = 0.0
     */
    public PlayerStatsTracker() {
        this.stats = new HashMap<>();
        this.totalGames = 0;
        this.wins = 0.0;
    }

    // Kernel Methods

    // Adds or updates a stat by adding the given value to any existing value.
    /**
     * Adds to an existing stat or creates a new one with the specified value.
     *
     * @param stat
     *            the name of the stat (e.g., "points", "assists")
     * @param value
     *            the amount to add to the stat
     * @requires stat != null and not empty
     * @ensures getStatTotal(stat) = #getStatTotal(stat) + value
     */
    public final void addStat(String stat, double value) {
        double current = this.stats.getOrDefault(stat, 0.0);
        this.stats.put(stat, current + value);
    }

    // Removes a stat from the tracker.
    /**
     * Removes a stat from the tracker.
     *
     * @param stat
     *            the name of the stat to remove
     * @requires stat != null
     * @ensures stat is no longer in the tracker
     */
    public final void removeStat(String stat) {
        this.stats.remove(stat);
    }

    // Returns the total for a given stat; if not found, returns 0.
    /**
     * Returns the total accumulated value for a stat.
     *
     * @param stat
     *            the name of the stat
     * @return the stat total or 0.0 if not found
     * @requires stat != null
     * @ensures return = value of stat or 0.0 if not present
     */
    public final double getStatTotal(String stat) {
        return this.stats.getOrDefault(stat, 0.0);
    }

    // Increases the count of games played and, if the game was a win, increments win count.
    /**
     * Records a game result and increments total games played.
     *
     * @param win
     *            {@code true} if the game was won, {@code false} otherwise
     * @ensures totalGamePlayed() = #totalGamePlayed() + 1 and if win then wins
     *          = #wins + 1
     */
    public final void recordGame(boolean win) {
        this.totalGames++;
        if (win) {
            this.wins++;
        }
    }

    // Returns the win rate calculated as wins divided by total games played.
    /**
     * Computes the win rate.
     *
     * @return win rate as a value between 0 and 1; returns 0.0 if no games have
     *         been played
     * @ensures return = wins / totalGames if totalGames > 0, else 0.0
     */
    public final double getWinRate() {
        if (this.totalGames == 0) {
            return 0;
        }
        return this.wins / this.totalGames;
    }

    // Returns the total number of games played.
    /**
     * Returns the total number of games played.
     *
     * @return total games
     * @ensures return >= 0
     */
    public final int totalGamePlayed() {
        return this.totalGames;
    }

    // Secondary Methods

    // Calculates the average for a given stat over a specified number of games.
    /**
     * Calculates the average value for a stat over the given number of games.
     *
     * @param stat
     *            the stat name
     * @param gamesPlayed
     *            number of games over which to calculate the average
     * @return average stat per game, or 0 if gamesPlayed <= 0
     * @requires stat != null
     * @ensures return = getStatTotal(stat) / gamesPlayed if gamesPlayed > 0,
     *          else 0
     */
    public final double getAverageStat(String stat, int gamesPlayed) {
        if (gamesPlayed <= 0) {
            return 0;
        }
        return this.getStatTotal(stat) / gamesPlayed;
    }

    // Displays all stats for the player.
    /**
     * Prints all recorded stats, games played, and win rate to the console.
     *
     * @ensures all stat entries and general info are displayed
     */
    public final void displayStats() {
        System.out.println("Player Stats:");
        for (Map.Entry<String, Double> entry : this.stats.entrySet()) {
            System.out
                    .println(" - " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Games Played: " + this.totalGamePlayed());
        System.out.println("Win Rate: " + this.getWinRate());
    }

    // Example secondary method that uses getWinRate() kernel method to provide a calculated win rate.
    /**
     * Returns the same result as {@code getWinRate()}. Used for demonstrating
     * use of kernel methods in secondary methods.
     *
     * @return calculated win rate
     * @ensures return = getWinRate()
     */
    public final double calculatedWinRate() {
        // For demonstration, it simply calls getWinRate()
        return this.getWinRate();
    }

    // Calculates average plus-minus value per game. Assumes a stat named "plusMinus" exists.
    /**
     * Computes the average plus-minus score per game.
     *
     * @return average plus-minus stat per game
     * @ensures return = getAverageStat("plusMinus", totalGamePlayed())
     */
    public final double avPlusMinus() {
        return this.getAverageStat("plusMinus", this.totalGamePlayed());
    }

    /**
     * Demonstrates basic usage of the PlayerStatsTracker.
     *
     * @param args
     *            command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create an instance of PlayerStatsTracker
        PlayerStatsTracker tracker = new PlayerStatsTracker();

        // Record some games and stats
        tracker.recordGame(true); // Game 1: win
        tracker.addStat("points", 20);
        tracker.addStat("rebounds", 10);
        tracker.addStat("plusMinus", 5);

        tracker.recordGame(false); // Game 2: loss
        tracker.addStat("points", 15);
        tracker.addStat("rebounds", 8);
        tracker.addStat("plusMinus", -3);

        tracker.recordGame(true); // Game 3: win
        tracker.addStat("points", 25);
        tracker.addStat("rebounds", 12);
        tracker.addStat("plusMinus", 7);

        // Display the current stats
        tracker.displayStats();

        // Example usage of secondary methods
        System.out
                .println("Calculated Win Rate: " + tracker.calculatedWinRate());
        System.out.println("Average Plus/Minus: " + tracker.avPlusMinus());

    }
}
