package node;

import eval.Environment;
import eval.EvalException;

/**
 * Node class that contains pointers for other nodes for the parsing tree.
 */
public class Term extends Node {
    protected Fact fact;
    protected Mulop mulop;
    protected Term term;

    public Term(Fact fact, Mulop mulop, Term term) {
        this.fact = fact;
        this.mulop = mulop;
        this.term = term;
    }

    public Term(Fact fact) {
        this.fact = fact;
        this.mulop = null;
        this.term = null;
    }

    /**
     * appends the subgrammar to the term
     * @param term
     */
    public void append(Term term) {
        if (this.term==null)
        {
            this.mulop=term.mulop;
            this.term=term;
            term.mulop=null;
        }
        else
        {
            this.term.append(term);
        }
    }

    /**
     * evaluates a parsed fact or computes two double values via mulop.
     * @param env
     * @return a parsed fact if mulop is null or computes the two double values.
     * @throws EvalException
     */
    @Override
    public double eval(Environment env) throws EvalException {
        if (this.mulop == null){
            return this.fact.eval(env);
        } else {
            return this.mulop.compute(term.eval(env), fact.eval(env));
        }

    }
}
