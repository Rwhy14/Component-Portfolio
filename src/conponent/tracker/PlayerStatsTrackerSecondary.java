package conponent.tracker;

/**
 * Implements all secondary methods by using kernal methods. Overrides object
 * methods using kernal methods.
 *
 * @author Ryan Chou
 */
public abstract class PlayerStatsTrackerSecondary
        implements PlayerStatsTrackerKernel {

    /**
     *
     * @param stat
     * @param gamesPlayed
     * @return this
     */
    public double getAverageStat(String stat, int gamesPlayed) {
        if (gamesPlayed == 0) {
            return 0.0;
        }
        return this.getStatTotal(stat) / gamesPlayed;
    }

    /**
     * Display player stats, including games played and win rate.
     */
    public void displayStats() {
        System.out.println("Player Stats: ");
        System.out.println("Games Played: " + this.totalGamePlayed());
        System.out.println("Win Rate of the player: " + this.getWinRate());
    }

    /**
     *
     * @return this
     */
    public double calculatedWinRate() {
        return this.getWinRate();
    }

    /**
     *
     * @return this
     */
    public double avPlusMinus() {
        return this.getAverageStat("plusMinus", this.totalGamePlayed());
    }

    /**
     * Returns a string representation of the object using kernel methods only.
     *
     * @return summary string of the component
     */
    @Override
    public String toString() {
        return "Games Played: " + this.totalGamePlayed() + ", Win Rate: "
                + String.format("%.2f", this.getWinRate());
    }

    /**
     * Compares two {@code PlayerStatsTracker} objects for equality using kernel
     * methods only.
     *
     * @param obj
     *            the object to compare
     * @return {@code true} if stats are equivalent, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PlayerStatsTracker)) {
            return false;
        }

        PlayerStatsTracker other = (PlayerStatsTracker) obj;
        return this.totalGamePlayed() == other.totalGamePlayed()
                && Double.compare(this.getWinRate(), other.getWinRate()) == 0;
        // Note: stat-by-stat comparison not possible unless kernel exposes all stats
    }

    /**
     * Computes hash code based on kernel-exposed values.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return this.totalGamePlayed() * 23 + Double.hashCode(this.getWinRate());
    }
}
