package syntax;

import java.util.*;

/**
 * Lexical Analyzer for CS354 programming language
 */
public class Lexer {

    private String program;      // source program being interpreted
    private int position;        // index of next char in program

    private Set<String> whitespace = new HashSet<>();
    private Set<String> letters = new HashSet<>();
    private Set<String> keywords = new HashSet<>();
    private Set<String> numbers = new HashSet<>();
    private Set<String> operators = new HashSet<>();


    /**
     * Creates a new lexical analyzer
     *
     * @param program - the program text to scan
     */
    public Lexer(String program) {
        this.program = program;
        position = 0;
        initWhitespace(whitespace);
        initLetters(letters);
        initKeywords(keywords);
        initNumbers(numbers);
        initoperators(operators);
    }

    private void initKeywords(Set<String> keywords2) {
        keywords2.add("wr");
        keywords2.add("rd");
        keywords2.add("if");
        keywords2.add("then");
        keywords2.add("else");
        keywords2.add("while");
        keywords2.add("do");
        keywords2.add("begin");
        keywords2.add("end");
        keywords2.add("var");
        keywords2.add("def");
    }

    private void initLetters(Set<String> s) {
        fill(s, 'A', 'Z');
        fill(s, 'a', 'z');
    }

    private void fill(Set<String> s, char lo, char hi) {
        for (char c = lo; c <= hi; c++) {
            s.add(c + "");
        }
    }

    private void initWhitespace(Set<String> s) {
        s.add(" ");
        s.add("\n");
        s.add("\t");
    }

    private void initNumbers(Set<String> s) {
        fill(s, '0', '9');
        s.add(".");
    }

    private void initoperators(Set<String> s) {
        fill(s, '(', '+'); // ( ) * +
        s.add("-");
        s.add("/");
        s.add(";");
        s.add("=");
        s.add("==");
        s.add("<");
        s.add(">");
        s.add("<=");
        s.add(">=");
        s.add("<>");
    }

    private void advance() {
        this.position++;
    }

    private String peek() {
        if (hasChar()) {
            return program.charAt(position) + "";
        } else {
            return null;
        }
    }

    /**
     *  nextKwID - determines if this is an identifier or "id" token
     * @return the scanned token.
     */
    private Token nextKwID() {

        int old = this.position;
        advance();

        while (hasChar() && (letters.contains(peek()) || numbers.contains(peek()))) {
            advance();
        }

        String lexeme = program.substring(old, position);

        if (keywords.contains(lexeme))
            return new Token(lexeme, lexeme, position);
        else
            return new Token("id", lexeme, position);
    }

    /**
     *  nextKwNum - determines if this is an identifier or "num" token
     * @return the scanned token.
     */
    private Token nextKwNum() {
        boolean decimalFlag = program.charAt(position) == '.'; // checks if current position is a decimal

        int old = this.position;
        advance();


        while (hasChar() && numbers.contains(peek())) {
            if (!decimalFlag && program.charAt(position) == '.') { // check again if a decimal appeared
                decimalFlag = true;
                advance();
            } else if (decimalFlag && program.charAt(position) == '.') { // checks for another decimal if already present
                String lexeme = program.substring(old, position);
                return new Token("num", lexeme, position);
            } else {
                advance();
            }
        }

        String lexeme = program.substring(old, position);
        return new Token("num", lexeme, position);
    }


    /**
     *  nextKwNum - determines if this is an identifier or "op" token
     * @return the scanned token.
     */
    private Token nextKwOp() {

        int old = getPosition();
        advance();

        // check if the operator is an increment or decrement (allowed: ++ --)
        if (hasChar() && operators.contains(peek())
                && (program.charAt(old) == program.charAt(getPosition()))
                && (program.charAt(old) == '+' || program.charAt(old) == '-')) {

                advance();
        } else if (hasChar() && operators.contains(peek())
                && (program.charAt(old) == program.charAt(getPosition()))
                && (program.charAt(old) == '=' || program.charAt(old) == '-')) {

                advance();
        } else if (hasChar() && operators.contains(peek())
                && (program.charAt(old) == '>')
                && (program.charAt(getPosition()) == '=')) {

                advance();
        } else if (hasChar() && operators.contains(peek())
                && (program.charAt(old) == '<')
                && (program.charAt(getPosition()) == '=')) {

                advance();
        } else if (hasChar() && operators.contains(peek())
                && (program.charAt(old) == '<')
                && (program.charAt(getPosition()) == '>')) {

                advance();
        }

        String lexeme = program.substring(old, getPosition());

        return new Token(lexeme, lexeme, position);
    }


    /**
     * Determines the kind of the next token (e.g., "id") and calls the
     * appropriate method to scan the token's lexeme (e.g., "foo").
     *
     * @return the scanned token.
     */
    public Token next() {
        checkIfNullProgram();
        skipWhiteSpace();
        checkForComments();
        return createToken();
    }

    /**
     * Skips a single line comments that has a preceding indicator of =>
     */
    private void skipSLComment() {

        // find the end of the comment line
        while(hasChar() && (program.charAt(getPosition()) != '\n' && program.charAt(getPosition()) != '\r')) {
            advance();
        }

        skipWhiteSpace();
    }

    /**
     * Checks for single line comments and calls skipSLComment
     */
    private void checkForComments(){
        // check for single line comments
        while ((hasChar() && program.charAt(getPosition()) == '=')
                && (hasNextChar() && program.charAt(getPosition() + 1) == '>')) { // single line comment indicator =>
            skipSLComment();
        }
    }

    /**
     * skips all the white space in the program string
     */
    private void skipWhiteSpace() {
        while (hasChar() && whitespace.contains(peek())) {
            advance();
        }
    }

    /**
     * catches if the program is null and instantiates it to an empty string.
     */
    private void checkIfNullProgram(){
        if(program == null){
            program = "";
        }
    }

    /**
     * Determines the Token and returns what type of token it is
     */
    private Token createToken(){
        if (!hasChar()) {
            return new Token("EOF", position);
        } else if (hasChar() && letters.contains(peek())) {
            return nextKwID(); // identifiers
        } else if (hasChar() && numbers.contains(peek())) {
            return nextKwNum(); // numbers
        } else if (hasChar() && operators.contains(peek())) {
            return nextKwOp(); // operators
        } else {
            System.err.println("illegal character at position "+ position);
            position++;
            return next();
        }
    }

    /**
     * Determines if the current position of the lexer is in the bounds of the
     * program
     * @return true if there are more characters in program
     */
    public boolean hasChar() {
        return position < program.length();
    }

    /**
     * Determines if the next position of the lexer is in the bounds of the
     * program
     * @return true if there are more characters in program
     */
    public boolean hasNextChar() {
        return position + 1 < program.length();
    }


    /**
     * Getter for position of the lexer in the program
     * @return index of the current position of the scanner
     */
    public int getPosition() {
        return position;
    }
}