package node;

import eval.Environment;
import eval.EvalException;
import syntax.Token;
import java.util.Scanner;

/**
 * StmtRdId node class - reads a value from user input and places it into a variable in the environment
 */
public class StmtRdId extends Stmt{
    protected Scanner scanner;
    protected Token id;

    public StmtRdId(Token id, Scanner scan) {
        this.scanner = scan;
        this.id = id;
    }


    /**
     * reads a double value from user input and places it into the environment variable.
     * @param env - map of environment
     * @return
     * @throws EvalException
     */
    @Override
    public double eval(Environment env) throws EvalException {
        double val = scanner.nextDouble();
        return env.update(id.getLexeme(), val);
    }

}
