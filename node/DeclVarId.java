package node;

import eval.Environment;
import eval.EvalException;

/**
 * Represents a variable declaration in the form of an identifier.
 * This class handles adding a variable to the environment with a default value
 * and ensures that no variable is declared more than once within the same scope.
 */

public class DeclVarId extends Decl{
    protected String lexeme;

    public DeclVarId(String lexeme){
        this.lexeme = lexeme;
    }

    /**
     * Adds a default value to a variable declaration to hold its place in the stack
     * @param env - map of environment
     * @return the double value of the id.
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        // check if it is already declared within the scope
        if (env.isInScope(lexeme)){
            throw new EvalException(0, lexeme + " has already been declared");
        }

        return env.put(lexeme, Double.POSITIVE_INFINITY); // assign a default value to hold variable
    }
}
