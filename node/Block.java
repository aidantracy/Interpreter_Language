package node;

import eval.Environment;
import eval.EvalException;

/**
 * Block - Node class that contains pointers for other nodes for the parsing tree.
 */
public class Block extends Node {
    protected Stmt stmt;
    protected Block block;

    public Block(Stmt stmt) {
        this.stmt = stmt;
        this.block = null;
    }

    public Block(Stmt stmt, Block block) {
        this.stmt = stmt;
        this.block = block;
    }

    /**
     * returns the double value from a stmt node and or another block node.
     * @param env - map of environment
     * @return
     * @throws EvalException
     */
    @Override
    public double eval(Environment env) throws EvalException {
        if (this.block==null) {
            return this.stmt.eval(env);
        } else {
            this.stmt.eval(env);
            return this.block.eval(env);
        }
    }

}
