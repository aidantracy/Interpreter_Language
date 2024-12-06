package node;

import eval.Environment;
import eval.EvalException;

import java.lang.reflect.GenericDeclaration;


/**
 * StmtBeginBlockEnd - stores a block node and returns a parsed double from the Block node.
 */
public class StmtBeginBlockEnd extends Stmt{
    Block block;
    public StmtBeginBlockEnd(Block block) {
        this.block = block;
    }

    /**
     * eval() assigns a value to a specific ID from a parsed Block node
     * @param env - map of environment
     * @return the double value of the assigned val
     * @throws EvalException returns an error if lexeme does not exist.
     */
    public double eval(Environment env) throws EvalException {
        env.addScope();
        double val = this.block.eval(env);
        env.removeScope();
        return val;
    }

}
