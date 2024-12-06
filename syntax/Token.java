package syntax;

/**
 * A Token for a programming language
 */
public class Token {
// added position
    private String token;
    private String lexeme;
    private int position;

    public Token(String token, String lexeme, int position) {
        this.token = token;
        this.lexeme = lexeme;
        this.position = position;
    }

    public Token(String token, int position) {
        this(token, token, position);
    }

    public String getToken() {
        return token;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getPosition() {
        return position;
    }

    public boolean equalType(Token t) {
        return this.token.equals(t.token);
    }

    public boolean equalType(String s) {
        return this.token.equals(s);
    }

    @Override
    public boolean equals(Object o) {
        Token t = null;
        if (o instanceof Token) {
            t = (Token) o;
        } else {
            return false;
        }
        return token.equals(t.getToken()) && lexeme.equals(t.getLexeme());
    }

    public String toString() {
        return "<" + getToken() + "," + getLexeme() + ">";
    }

}
