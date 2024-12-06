package node;

import eval.Environment;
import eval.EvalException;

/**
 * Represents a variable declaration with an initial assignment.
 * This class handles adding a variable to the environment with a specified value
 * evaluated from an expression, ensuring that the variable is not redeclared in the same scope.
 */

public class DeclVarIdAssn extends Decl{
    protected String lexeme;
    protected Expr expr;

    public DeclVarIdAssn(String lexeme, Expr expr) {
        this.lexeme = lexeme;
        this.expr = expr;
    }

    /**
     * Evaluates the declaration by assigning the value of the expression to the specified variable.
     * Ensures the variable is not already declared within the current scope before adding it to the environment.
     *
     * @param env the environment map that stores variable declarations and their values
     * @return the evaluated double value of the expression assigned to the variable
     * @throws EvalException if the variable has already been declared in the current scope
     */

    public double eval(Environment env) throws EvalException {
        // check if it is already declared within the scope
        if (env.isInScope(lexeme)){
            throw new EvalException(0, lexeme + " has already been declared");
        }
        // else assign value
        return env.put(lexeme, expr.eval(env));
    }
}
