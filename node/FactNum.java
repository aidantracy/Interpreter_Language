package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;


/**
 * This is a subclass terminal for a num token
 */
public class FactNum extends Fact{
    protected Token num;

    public FactNum(Token numToken, int position) {
        this.num = numToken;
        this.position = position;

    }

    /**
     * parses the value of the token num and returns it.
     * @param env - map of environment
     * @return a parsed double value of the token num
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return Double.parseDouble(this.num.getLexeme());
    }

}
