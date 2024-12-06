package syntax;

import node.*;

import java.util.Scanner;

/**
 * A recursive descent parser
 */
public class Parser {

    private Lexer lexer;
    private Token lookahead;
    private Scanner scanner;

    /**
     * Parse an input program and return a Node that is the root of the
     * resulting parse tree.
     *
     * @param program String to be scanned and parsed
     * @return the Root Node of a parse tree
     * @throws SyntaxException - If there is a syntax error
     */
    public Node parse(String program) throws SyntaxException {
        scanner = new Scanner(System.in);
        lexer = new Lexer(program);
        lookahead = lexer.next(); //"prime the pump"
        Block block = parseBlock();
        match("EOF");
        return block;
    }

    /**
     * Parses a Block non-terminal and returns it.
     * @return a Node that represents a Block
     * @throws SyntaxException
     */

    private Block parseBlock() throws SyntaxException {

        // always start with statement
        Stmt stmt = parseStmt();

        // check, next value must be ; to execute the statement
        if (lookahead.equalType(";")){
            match(";");
            Block block = parseBlock();
            return new Block(stmt, block);
        } else {
            return new Block(stmt);
        }
    }

    /**
     * Parses a Statement (stmt) non-terminal and returns it.
     * @return a Node that represents a Statement
     * @throws SyntaxException
     */

    private Stmt parseStmt() throws SyntaxException {
        if (lookahead.equalType("wr")){
            match("wr");
            Expr expr = parseExpr();
            return new StmtWrExpr(expr);

        } else if (lookahead.equalType("rd")){
            match("rd");
            if (!lookahead.equalType("id")){
                throw new SyntaxException(lexer.getPosition(), new Token("id", lookahead.getPosition()), lookahead);
            }
            Token id = lookahead;
            match("id");
            return new StmtRdId(id, scanner);

        } else if (lookahead.equalType("begin")){
            match("begin");
            Block block = parseBlock();
            match("end");
            return new StmtBeginBlockEnd(block);

        } else if (lookahead.equalType("if")){
            match("if");
            BoolExpr boolExpr = parseBoolExpr();
            match("then");
            Stmt stmt = parseStmt();

            if (lookahead.equalType("else")){
                match("else");
                Stmt elseStmt = parseStmt();
                return new StmtIfBoolStmt(boolExpr, stmt, elseStmt);
            } else {
                return new StmtIfBoolStmt(boolExpr, stmt);
            }

        } else if (lookahead.equalType("while")) {
            match("while");
            BoolExpr boolExpr = parseBoolExpr();
            if (lookahead.equalType("do")){
                match("do");
                Stmt stmt = parseStmt();
                return new StmtWhileBoolExpr(stmt, boolExpr);
            } else {
                throw new SyntaxException(lexer.getPosition(), new Token("do", lexer.getPosition()), lookahead);
            }

        } else if (lookahead.equalType("id")) {
            Token id1 = lookahead;
            match("id");
            if (lookahead.equalType("=")){
                Assn assin = parseAssn(id1);
                return new StmtAssn(assin);
            }

            match("(");
            Expr expr = parseExpr();
            match(")");
            return new StmtIdExpr(id1, expr);

        } else if (lookahead.equalType("var")) {
            match("var");
            Decl decl = parseDecl();
            return new StmtDecl(decl);

        } else if (lookahead.equalType("def")) {
            match("def");
            Decl decl = parseDecl();
            return new StmtDecl(decl);

        }
        return null;
    }

    /**
     * Parses a Decl (declare) and returns it.
     * @return a Node that represents a declaritive.
     * @throws SyntaxException
     */
    private Decl parseDecl() throws SyntaxException {
        Token id1 = lookahead;
        match("id");

        if (lookahead.equalType("=")){
            // declared and assigned value
            match("=");
            Expr expr = parseExpr();
            return new DeclVarIdAssn(id1.getLexeme(), expr);

        } else if (lookahead.equalType("(")) {
            // creating a function
            match("(");
            Token id2 = lookahead;
            match(")");
            match("begin");
            Block block = parseBlock();
            match("end");
            return new DeclDefFunction(id1, id2, block);
        } else {
            // only declared
            return new DeclVarId(id1.getLexeme());
        }
    }


    /**
     * Parses a boolean and expr node set and returns a BoolExpr node
     * @return a node that represents a relational expression
     * @throws SyntaxException
     */
    private BoolExpr parseBoolExpr() throws SyntaxException {
        Expr expr1 = parseExpr();
        Relop relop = parseRelop();
        Expr expr2 = parseExpr();
        return new BoolExpr(expr1, relop, expr2);
    }


    /**
     * Parses an assignment (assn) non-terminal and returns it.
     * @return a Node that represents an Assignment
     * @throws SyntaxException
     */
    private Assn parseAssn(Token id) throws SyntaxException {
        if (lookahead.equalType("=")){
            match("=");
        }

        Expr expr = parseExpr();
        return new AssnIdExpr(id, expr);
    }

    /**
     * Parses an EXPR non-terminal and returns it.
     * @return a Node that represents an EXPR
     * @throws SyntaxException
     */
    private Expr parseExpr() throws SyntaxException{

        Term term = parseTerm();
        Addop addop = parseAddop();

        if (addop == null) {
            return new Expr(term);
        } else {
            Expr expr = parseExpr();

            expr.append(new Expr(term,addop,null));
            return expr;
        }
    }

    /**
     * Parses a Term and returns it.
     * @return a Node that represents an Term
     * @throws SyntaxException
     */
    private Term parseTerm() throws SyntaxException{

        Fact fact = parseFact(); // always a fact first
        Mulop mulop = parseMulop();

        if (mulop == null) { // Fact only
            return new Term(fact);

        } else { // Fact Mulop Term
            Term term = parseTerm();
            term.append(new Term(fact, mulop,null));
            return term;
        }
    }

    /**
     * Parses a Fact and returns it.
     * @return a Node that represents an Fact
     * @throws SyntaxException
     */
    private Fact parseFact() throws SyntaxException {

        Token current = lookahead;

        if (current.equalType(new Token("id", lexer.getPosition()))) {
            match("id");
            if(lookahead.equalType("(")){
                match("(");
                Expr expr = parseExpr();
                match(")");
                return new FactIdExpr(current, expr);
            }
            return new FactId(current, lexer.getPosition());

        } else if (current.equalType(new Token("num", lexer.getPosition()))) {
            match("num");
            return new FactNum(current, lexer.getPosition());

        } else if(current.equalType(new Token("(", lexer.getPosition()))) {
            match("(");
            Expr expr = parseExpr();
            if (!lookahead.equalType(new Token(")", lexer.getPosition()))) {
                throw new SyntaxException(lexer.getPosition(), new Token(")", lexer.getPosition()), current);
            } else {
                match(")");
            }
            return new FactOpExprOp(expr);

        } else if (current.equalType("-")) {
            match("-");
            Fact fact = parseFact();
            return new FactNegFact(fact);
        }
        else {
            throw new SyntaxException(lexer.getPosition(), new Token("(", lexer.getPosition()), current);
        }
    }

    /**
     * Parses an Addop non-terminal and returns it.
     * @return a Node that represent an addition/subtraction operator
     * @throws SyntaxException if an invalid terminal is discovered
     */
    private Addop parseAddop() throws SyntaxException {
        Token addop = lookahead;
        if (addop.equalType("+")) {
            match("+");
            return new Addop(lexer.getPosition(), addop);

        } else if (addop.equalType("-")) {
            match("-");
            return new Addop(lexer.getPosition(), addop);

        } else {
            return null;
        }
    }

    /**
     * Parses a Mulop non-terminal and returns it
     * @return a node that represents a multiplying/dividing operator
     * @throws SyntaxException
     */
    private Mulop parseMulop() throws SyntaxException {
        Token mulop = lookahead;

        if (mulop.equalType("/")) {
            match("/");
            return new Mulop(lexer.getPosition(), mulop);

        } else if (mulop.equalType("*")) {
            match("*");
            return new Mulop(lexer.getPosition(), mulop);

        } else {
            return null;
        }
    }


    /**
     * Parses a Relop non-terminal and returns it
     * @return a node that represents a Relational operator
     * @throws SyntaxException
     */
    private Relop parseRelop() throws SyntaxException {
        Token relop = lookahead;

        if (relop.equalType("<")) {
            match("<");
            return new Relop(lexer.getPosition(), relop);

        } else if (relop.equalType("<=")) {
            match("<=");
            return new Relop(lexer.getPosition(), relop);

        } else if (relop.equalType(">")) {
            match(">");
            return new Relop(lexer.getPosition(), relop);

        } else if (relop.equalType(">=")) {
            match(">=");
            return new Relop(lexer.getPosition(), relop);

        } else if (relop.equalType("==")) {
            match("==");
            return new Relop(lexer.getPosition(), relop);

        } else if (relop.equalType("<>")) {
            match("<>");
            return new Relop(lexer.getPosition(), relop);

        } else {
            return null;
        }
    }


    /**
     * Matches the current token with a specified token, moves lexer to next token in order for parsing.
     * @param s - string token to match
     * @throws SyntaxException
     */
    private void match(String s) throws SyntaxException {
        if (lookahead.equalType(s)) {
            lookahead = lexer.next();
        } else {
            throw new SyntaxException(lexer.getPosition(), new Token(s, lexer.getPosition()), lookahead);
        }
    }
}
