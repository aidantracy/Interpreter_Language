package node;

import syntax.Token;

/**
 * Relop node class is a representation of one of these relational operators: ==, <>, <=, >=, <, and >
 * */
public class Relop extends Node{
    protected Token relop;
    protected int pos;

    public Relop(int position, Token operator){
        this.relop = operator;
        this.pos = position;
    }

    /**
     * Computes the values of two double values by subtracting or adding them together.
     * @param op1 - value from a parsed Term Node
     * @param op2 - value from a parsed EXPR Node
     * @return 1.0 => true, 0.0 => False
     */
    public double compute(double op1, double op2) {
        if (relop.equalType("<")){
            return op1 < op2? 1 : 0;
        } else if (relop.equalType(">")){
            return op1 > op2? 1 : 0;
        } else if (relop.equalType("<=")){
            return op1 <= op2? 1 : 0;
        } else if (relop.equalType(">=")){
            return op1 >= op2? 1 : 0;
        } else if (relop.equalType("==")){
            return op1 == op2? 1 : 0;
        } else { // <> operator
            return op1 != op2? 1 : 0;
        }
    }
}
