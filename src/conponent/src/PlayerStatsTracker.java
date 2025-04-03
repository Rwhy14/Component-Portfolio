import java.util.HashMap;
import java.util.Map;

public class PlayerStatsTracker {
    // This structure is mutable because player stats change after each game.
    private Map<String, Double> stats;
    private int totalGames;
    private double wins;

    // Constructor
    public PlayerStatsTracker() {
        this.stats = new HashMap<>();
        this.totalGames = 0;
        this.wins = 0.0;
    }

    // Kernel Methods

    // Adds or updates a stat by adding the given value to any existing value.
    public void addStat(String stat, double value) {
        double current = this.stats.getOrDefault(stat, 0.0);
        this.stats.put(stat, current + value);
    }

    // Removes a stat from the tracker.
    public void removeStat(String stat) {
        this.stats.remove(stat);
    }

    // Returns the total for a given stat; if not found, returns 0.
    public double getStatTotal(String stat) {
        return this.stats.getOrDefault(stat, 0.0);
    }

    // Increases the count of games played and, if the game was a win, increments win count.
    public void recordGame(boolean win) {
        this.totalGames++;
        if (win) {
            this.wins++;
        }
    }

    // Returns the win rate calculated as wins divided by total games played.
    public double getWinRate() {
        if (this.totalGames == 0) {
            return 0;
        }
        return this.wins / this.totalGames;
    }

    // Returns the total number of games played.
    public int totalGamePlayed() {
        return this.totalGames;
    }

    // Secondary Methods

    // Calculates the average for a given stat over a specified number of games.
    // Example: average points per game.
    public double getAverageStat(String stat, int gamesPlayed) {
        if (gamesPlayed <= 0) {
            return 0;
        }
        return this.getStatTotal(stat) / gamesPlayed;
    }

    // Displays all stats for the player.
    public void displayStats() {
        System.out.println("Player Stats:");
        for (Map.Entry<String, Double> entry : this.stats.entrySet()) {
            System.out
                    .println(" - " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Games Played: " + this.totalGamePlayed());
        System.out.println("Win Rate: " + this.getWinRate());
    }

    // Example secondary method that uses getWinRate() kernel method to provide a calculated win rate.
    public double calculatedWinRate() {
        // For demonstration, it simply calls getWinRate()
        return this.getWinRate();
    }

    // Calculates average plus-minus value per game. Assumes a stat named "plusMinus" exists.
    public double avPlusMinus() {
        return this.getAverageStat("plusMinus", this.totalGamePlayed());
    }

    public static void main(String[] args) {
        // Create an instance of PlayerStatsTracker
        PlayerStatsTracker tracker = new PlayerStatsTracker();

        // Record some games and stats
        tracker.recordGame(true);   // Game 1: win
        tracker.addStat("points", 20);
        tracker.addStat("rebounds", 10);
        tracker.addStat("plusMinus", 5);

        tracker.recordGame(false);  // Game 2: loss
        tracker.addStat("points", 15);
        tracker.addStat("rebounds", 8);
        tracker.addStat("plusMinus", -3);

        tracker.recordGame(true);   // Game 3: win
        tracker.addStat("points", 25);
        tracker.addStat("rebounds", 12);
        tracker.addStat("plusMinus", 7);

        // Display the current stats
        tracker.displayStats();

        // Example usage of secondary methods
        System.out.println("Calculated Win Rate: " + tracker.calculatedWinRate());
        System.out.println("Average Plus/Minus: " + tracker.avPlusMinus());

}
