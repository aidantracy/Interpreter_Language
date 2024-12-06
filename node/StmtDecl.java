package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;

/**
 * Represents a statement that declares a variable, optionally with an initial assignment.
 * The statement evaluates by delegating to the appropriate declaration type (either with or without an assignment)
 */

public class StmtDecl extends Stmt {
    protected Decl decl;

    public StmtDecl(Decl d) {
        this.decl = d;
    }

    /**
     * returns a value specified by declaration
     *
     * @param env the environment map that stores variable declarations and their values
     * @return the double value of the variable (default or evaluated expression)
     * @throws EvalException if there is an issue during evaluation, such as redeclaration in the same scope
     */

    public double eval(Environment env) throws EvalException {
            return this.decl.eval(env);
    }



}
