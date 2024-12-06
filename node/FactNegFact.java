package node;

import eval.Environment;
import eval.EvalException;

/**
 * a class that stores a fact that when evaluated, becomes negative.
 */
public class FactNegFact extends Fact {

    protected Fact fact;

    public FactNegFact(Fact fact) {
        this.fact = fact;
    }

    /**
     * evaluates a fact node and returns the negative double value.
     * @param env - map of environment
     * @return a negative double value returned from a parsed fact node
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return this.fact.eval(env) * -1;
    }
}
