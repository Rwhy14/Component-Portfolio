package conponent.src;

/**
 * A minimal interface representing a standard component with clearable state.
 *
 * @param <T>
 *            the type of the component implementing this interface
 */
public interface Standard<T> {
    /**
     * Resets this component to its initial state.
     *
     * @ensures this is in initial state
     */
    void clear();
}
