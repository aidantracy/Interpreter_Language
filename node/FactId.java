package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * This is a subclass terminal for an ID token
 */
public class FactId extends Fact{

    protected Token id;

    public FactId(Token idToken, int position) {
        this.id = idToken;
        this.position = position;

    }

    /**
     * retrieves the double value from the map from a specified id.
     * @param env - map of environment
     * @return the double value of the id.
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
            return env.get(this.position, id.getLexeme());
    }

}
