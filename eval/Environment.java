package eval;

import java.util.*;

/**
 * Environment for storing values in variables in the program.
 */
public class Environment {
    private Deque<Map<String, Double>> stack;

    public Environment() {
        this.stack = new ArrayDeque<>();
        this.addScope();
    }

    /**
     * Adds a variable and its value to the most recent map on the stack.
     * @param var The variable name to add
     * @param val The value to associate with the variable
     * @return The previous value associated with the variable, or 0 if none existed
     */
    public double put(String var, double val) {
        if (this.stack.isEmpty()) {
            throw new RuntimeException("Memory Error, Stack is empty");
        }
        // grabs the map on the top of the stack
        Map<String, Double> map = this.stack.peek();
        map.put(var, val);
        return val;
    }

    /**
     * Updates the variable in a scope in the stack.
     * @param var The variable name to add
     * @param val The value to associate with the variable
     * @return The previous value associated with the variable, or 0 if none existed
     */
    public double update(String var, double val) throws EvalException {
        for (Iterator<Map<String, Double>> it = this.stack.descendingIterator(); it.hasNext();) {
            Map<String, Double> map = it.next();
            if (map.containsKey(var)) {
                map.put(var, val);
                return val;
            }
        }

        // If the variable is not found, throw an exception
        throw new EvalException(0, var + " is not defined");
    }

    /**
     * Retrieves the value of a variable from the environment.
     * @param pos The position in the expression (used for error messages)
     * @param var The variable name to look up
     * @return The value associated with the variable
     * @throws EvalException If the variable is not found in the environment
     */
    public double get(int pos, String var) throws EvalException {
        for (Iterator<Map<String, Double>> it = this.stack.descendingIterator(); it.hasNext();) {
            Map<String, Double> map = it.next();
            if (map.containsKey(var)) {
                return map.get(var);
            }
        }

        // If the variable is not found, throw an exception
        throw new EvalException(pos, var + " is not defined");

    }

    /**
     * Checks if a key is already declared within the current scope.
     * @param key
     * @return
     */
    public boolean isInScope(String key) {
        Map<String, Double> map = this.stack.peek();
        return map.containsKey(key);
    }

    /**
     * addScope creates a new map and adds it to the stack.
     */
    public void addScope(){
        Map<String, Double> scopeMap = new HashMap<>();
        this.stack.push(scopeMap);
    }

    /**
     * removeScope removes the last map on the stack
     */
    public void removeScope(){
        if (this.stack.isEmpty()) return;
        this.stack.pop();
    }

}
