package eval;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * todo
 */
public class StackFrame {
    private Map<String, Double> MapOfDoubles;
    private Map<String, Integer> MapOfIntegers;
    private Map<String, String> MapOfStrings;
    private Map<String, Character> MapOfCharacters;

    /**
     * This is a stretch goal to extend programming language to accept a stackframe instead of just a stack of maps so
     * that the programming language can store other values other than doubles.
     */
    public StackFrame() {
        this.MapOfCharacters = null;
        this.MapOfDoubles = null;
        this.MapOfIntegers = null;
        this.MapOfStrings = null;

    }

    /**
     *  todo
     */
    public double put(String var, double val) {
        // Todo
        return 0;
    }

    /**
     * todo
     */
    public double get(int pos, String var) throws EvalException {
        // Todo
        return 0;
    }
}
