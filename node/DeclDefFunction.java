package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * todo
 */
public class DeclDefFunction extends Decl {
    Token funcName, param1;
    Block block;

    public DeclDefFunction(Token id1, Token id2, Block block) {
        this.funcName = id1;
        this.param1 = id2;
        this.block = block;
    }

    /**
     * todo
     * @param env - map of environment
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {

        return 0; // todo
    }


}
