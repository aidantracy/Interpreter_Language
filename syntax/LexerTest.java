//package syntax;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Test class for syntax.Lexer
// *
// * Uses Junit5.
// */
//public class LexerTest {
//
//    /**
//     * Simply creates a 'program' that has only one token. When scanned,
//     * the test checks to see that the current token is of the correct type.
//     * Try more than one Token in a different test case!
//     * @throws SyntaxException - This suppresses the need for a try/catch block.
//     */
//    @Test
//    public void testOneMisc() throws SyntaxException{
//
//        String prg = "4";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", "4"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testTwoMisc() throws SyntaxException{
//
//        String prg = "=> this is a single line comment \n 4";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", "4"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testThreeMisc() throws SyntaxException{
//
//        String prg = "=> this is a multiple comment test\n =>\n 4";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", "4"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testFourMisc() throws SyntaxException{
//
//        String prg = "2.0.3";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", "2.0"), lexer.next());
//        assertEquals(new Token("num", ".3"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testFiveMisc() throws SyntaxException{
//
//        String prg = ".2.0.3";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", ".2"), lexer.next());
//        assertEquals(new Token("num", ".0"), lexer.next());
//        assertEquals(new Token("num", ".3"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testSixMisc() throws SyntaxException{
//
//        String prg = "1234.";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", "1234."), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testSevenMisc() throws SyntaxException{
//        // numbers and a comment
//        String prg = ".002 => 50.asdf.00\r .002";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", ".002"), lexer.next());
//        assertEquals(new Token("num", ".002"), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testEightMisc() throws SyntaxException{
//        // numbers and a comment
//        String prg = ".002 => 50.asdf.00\n \n.002";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", ".002"), lexer.next());
//        assertEquals(new Token("num", ".002"), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testNineMisc() throws SyntaxException{
//        // number and identifier
//        String prg = "2BorNot2B";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", "2"), lexer.next());
//        assertEquals(new Token("id", "BorNot2B"), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testTenMisc() throws SyntaxException{
//        // null pointer
//        String prg = null;
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testElevenMisc() throws SyntaxException{
//        String prg = "=> comment 1 random stuff \n=> comment 2 //=\n\t+3-0.1.=>end\n";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("+", "+"), lexer.next());
//        assertEquals(new Token("num", "3"), lexer.next());
//        assertEquals(new Token("-", "-"), lexer.next());
//        assertEquals(new Token("num", "0.1"), lexer.next());
//        assertEquals(new Token("num", "."), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//
//
//    /**
//     * Tests that the lexer can recognize an identifier
//     *
//     * @throws SyntaxException
//     */
//    @Test
//    public void testOneIdentifier() throws SyntaxException{
//
//        String prg = "x";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("id", "x"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testTwoIdentifier() throws SyntaxException{
//
//        String prg = "xx";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("id", "xx"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testThreeIdentifier() throws SyntaxException{
//
//        String prg = "x13";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("id", "x13"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testFourIdentifier() throws SyntaxException{
//
//        String prg = "x13y\ny";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("id", "x13y"), lexer.next());
//        assertEquals(new Token("id", "y"), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testFiveIdentifier() throws SyntaxException{
//
//        String prg = "toString";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("id", "toString"), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testSixIdentifier() throws SyntaxException{
//
//        String prg = "hi8friends";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("id", "hi8friends"), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//
//    /**
//     * Tests that the lexer can recognize an operator (e.g. the semicolon)
//     * @throws SyntaxException
//     */
//    @Test
//    public void testOneOperator() throws SyntaxException{
//
//        String prg = ";";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token(";", ";"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//
//    @Test
//    public void testTwoOperator() throws SyntaxException{
//
//        String prg = "+";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token("+", "+"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testThreeOperator() throws SyntaxException{
//
//        String prg = "++";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token("++", "++"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testFourOperator() throws SyntaxException{
//
//        String prg = "+-";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token("+", "+"), lexer.next());
//        assertEquals(new Token("-", "-"), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testFiveOperator() throws SyntaxException{
//
//        String prg = "--";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token("--", "--"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testSixOperator() throws SyntaxException{
//
//        String prg = "=";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token("=", "="), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testSevenOperator() throws SyntaxException{
//
//        String prg = "= ";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token("=", "="), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testEightOperator() throws SyntaxException{
//
//        String prg = "= =";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token("=", "="), lexer.next());
//        assertEquals(new Token("=", "="), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//
//    @Test
//    public void testNineOperator() throws SyntaxException{
//
//        String prg = "==";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token("==", "=="), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testTenOperator() throws SyntaxException{
//
//        String prg = "<=";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token("<=", "<="), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testElevenOperator() throws SyntaxException{
//
//        String prg = ">=";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token(">=", ">="), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//
//    @Test
//    public void testTwelveOperator() throws SyntaxException{
//
//        String prg = "<>";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token("<>", "<>"), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testThirteenOperator() throws SyntaxException{
//
//        String prg = "< =";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token("<", "<"), lexer.next());
//        assertEquals(new Token("=", "="), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testFourteenOperator() throws SyntaxException{
//
//        String prg = "> =";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token(">", ">"), lexer.next());
//        assertEquals(new Token("=", "="), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//
//    @Test
//    public void testFifteenOperator() throws SyntaxException{
//
//        String prg = "< >";
//        Lexer lexer = new Lexer(prg);
//
//        assertEquals(new Token("<", "<"), lexer.next());
//        assertEquals(new Token(">", ">"), lexer.next());
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//
//    /**
//     * Tests that the lexer can recognize a number
//     * @throws SyntaxException
//     */
//    @Test
//    public void testOneNumber() throws SyntaxException{
//
//        String prg = "1.0";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", "1.0"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testTwoNumber() throws SyntaxException{
//
//        String prg = "11.";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", "11."), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testThreeNumber() throws SyntaxException{
//
//        String prg = ".";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", "."), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//    @Test
//    public void testFourNumber() throws SyntaxException{
//
//        String prg = ".05";
//        Lexer lexer = new Lexer(prg);
//        assertEquals(new Token("num", ".05"), lexer.next());
//
//        assertEquals(new Token("EOF", "EOF"), lexer.next());
//    }
//
//}
