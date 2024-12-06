package node;

import eval.Environment;
import eval.EvalException;

/**
 * Wr - Class that writes the value of the parsed expr node to the console.
 */
public class Wr {
    private Expr expr;

    public Wr(Expr expr) {
        this.expr = expr;
    }

    /**
     * Retrieves the value of the parsed expr node, prints it to the console and returns the double value.
     * @param env - map of environment
     * @return the double result from evaluating the expr node
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        double result = expr.eval(env);
        System.out.println(result);
        return result;
    }

}
