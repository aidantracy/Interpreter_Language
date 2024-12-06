package eval;

import node.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import syntax.Parser;
import syntax.SyntaxException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * System tests for the semantic actions of the programming language.
 */
class EvaluationTest {

    private Parser parser;
    private Environment env;


    @BeforeEach
    void setUp() {

        parser = new Parser();
        env = new Environment();
    }

    @Test
    void testAssignment() throws SyntaxException, EvalException{
        String prg = "x = 1; wr x";


        Node parseTree = parser.parse(prg);
        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });
    }


    @Test
    void testAssignment2() throws SyntaxException, EvalException{
        String prg = "x = 1";


        Node parseTree = parser.parse(prg);
        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });
    }

    @Test
    void testAssignment3() throws SyntaxException, EvalException{
        String prg = "x = -1";


        Node parseTree = parser.parse(prg);
        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });

    }

    @Test
    void testAssignment4() throws SyntaxException, EvalException{
        String prg = "x = 1; wr x - 1";


        Node parseTree = parser.parse(prg);
        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });
    }

    @Test
    void testAssignment5() throws SyntaxException, EvalException{
        String prg = "var x = 1; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }


    @Test
    void testAssignment6() throws SyntaxException, EvalException{
        String prg = "var x = 1";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    void testAssignment7() throws SyntaxException, EvalException{
        String prg = "var x = -1";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(-1, evaluation);
    }

    @Test
    void testAssignment8() throws SyntaxException, EvalException{
        String prg = "var x = 1; wr x - 1";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }

    @Test
    void testAssignment9() throws SyntaxException, EvalException{
        String prg = "var x = 1; var x = 2";


        Node parseTree = parser.parse(prg);
        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });
    }


    @Test
    void testEvalError() throws SyntaxException, EvalException {

        String prg = "wr x";
        Node parseTree = parser.parse(prg);

        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });
    }

    @Test
    void testEvalError2() throws SyntaxException, EvalException {

        String prg = "wr x; x = 4";
        Node parseTree = parser.parse(prg);

        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });
    }


    @Test
    void testEvalError3() throws SyntaxException, EvalException {

        String prg = "var x; wr x; x = 4; var x";
        Node parseTree = parser.parse(prg);

        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });
    }

    @Test
    void testAssociation() throws SyntaxException, EvalException{
        String prg = "wr 10 - 4 - 3";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    void testAssociation2() throws SyntaxException, EvalException{
        String prg = "wr (4 * 6) / (4 - 1)";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(8, evaluation);
    }


    @Test
    void testAssociation3() throws SyntaxException, EvalException{
        String prg = "wr 4 * 6 /( 2 * (4 - 1))";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(4, evaluation);
    }

    @Test
    void testPrecedence() throws SyntaxException, EvalException{
        String prg = "wr 6 / (10 - 8)";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    void testPrecedence2() throws SyntaxException, EvalException{
        String prg = "wr -(6 / (10 - 8))";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(-3, evaluation);
    }

    @Test
    void testPrecedence3() throws SyntaxException, EvalException{
        String prg = "var x = 4; wr -(x - -x)"; // - (4 - -4)

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(-8, evaluation);
    }


    @Test
    void testRd() throws SyntaxException, EvalException {
        String input = "4.1";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);


        String prg = "var x; rd x; wr x";


        double evaluation = parser.parse(prg).eval(env);
        assertEquals(4.1, evaluation);
    }

    @Test
    void testRd2() throws SyntaxException, EvalException {
        String input = "5.2";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);


        String prg = "var x; rd x; wr x; if x == 5.2 then x = 6; wr x";


        double evaluation = parser.parse(prg).eval(env);
        assertEquals(6, evaluation);
    }


    @Test
    void testRd3() throws SyntaxException, EvalException {
        String input = "2";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);


        String prg = "var x; rd x; while x <> 5 do x = x + 1 ";


        double evaluation = parser.parse(prg).eval(env);
        assertEquals(5, evaluation);
    }

    @Test
    void testRd4() throws SyntaxException, EvalException {
        String input = "2";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);
        String prg = "rd x; wr x";

        Node parseTree = parser.parse(prg);

        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });
    }

    @Test
    void testEquality1() throws SyntaxException, EvalException {
        String prg = "var x = 1; if x == 1 then x = 2";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(2, evaluation);
    }

    @Test
    void testEquality2() throws SyntaxException, EvalException {
        String prg = "var x = 5; if x == 1 then x = 2 else x = 3";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(3, evaluation);
    }

    @Test
    void testEquality3() throws SyntaxException, EvalException {
        String prg = "var x = 5; if x <= 5 then wr 0 else wr 1";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }


    @Test
    void testEquality4() throws SyntaxException, EvalException {
        String prg = "var x = 5; if x < 1 then wr 1 else wr 0";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }


    @Test
    void testEquality5() throws SyntaxException, EvalException {
        String prg = "var x = 5; if x > 1 then wr 1 else wr 0";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    void testEquality6() throws SyntaxException, EvalException {
        String prg = "var x = 5; if x >= 5 then wr x else wr 0";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(5, evaluation);
    }

    @Test
    void testEquality7() throws SyntaxException, EvalException {
        String prg = "var x = 5; if x <> 1 then wr 1 else wr 0";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    void testFullProgram() throws SyntaxException, EvalException {
        String input = "25 15";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "var a; var b; rd a; rd b; while a<>b do if a > b then a = a-b else b = b-a; wr a";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(5, evaluation);
    }


    @Test
    void testFullProgram2() throws SyntaxException, EvalException {
        String input = "3";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "var n; rd n; if n <= 2 then wr 1 else begin var a = 1; var b = 1; var i = 3; while i <= n do begin var temp = a; a = a + b; b = temp; i = i + 1 end; wr a end";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(2, evaluation);
    }

    @Test
    void testFullProgram3() throws SyntaxException, EvalException {
        String input = "6";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "var n; rd n; if n <= 2 then wr 1 else begin var a = 1; var b = 1; var i = 3; while i <= n do begin var temp = a; a = a + b; b = temp; i = i +1 end; wr a end";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(8, evaluation);
    }

    @Test
    void testFullProgram4() throws SyntaxException, EvalException {
        String input = "10";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "var n; rd n; if n <= 2 then wr 1 else begin var a = 1; var b = 1; var i = 3; while i <= n do begin var temp = a; a = a + b; b = temp; i = i +1 end; wr a end";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(55, evaluation);
    }

    @Test
    void testFullProgram5() throws SyntaxException, EvalException {
        String input = "10";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "var x; rd x; if x==5 then x = 15 else x = 0; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }

    @Test
    void testFullProgram6() throws SyntaxException, EvalException {
        String input = "10";
        InputStream io = new ByteArrayInputStream(input.getBytes());
        System.setIn(io);

        String prg = "var x; rd x; if x==5 then if x == 15 then x = 2 else x = 0 else x = 0; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(0, evaluation);
    }

    @Test
    void testScope() throws SyntaxException, EvalException {
        String prg = "var x = 1; begin var x = 2 end; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

    @Test
    void testScope2() throws SyntaxException, EvalException {
        String prg = "var x = 1; begin x = 2 end; wr x";

        double evaluation = parser.parse(prg).eval(env);
        assertEquals(2, evaluation);
    }

    @Test
    void testScope3() throws SyntaxException, EvalException {
        String prg = "var x = 1; begin var y = 2; begin y = 3; begin y = 4 end end end; wr y ";
        Node parseTree = parser.parse(prg);

        assertThrows(EvalException.class, () -> {
            parseTree.eval(env);
        });
    }

    @Test
    void testScope4() throws SyntaxException, EvalException {
        String prg = "var x = 1; begin var x = 2; begin var x = 3; begin var x = 4 end end end; wr x ";


        double evaluation = parser.parse(prg).eval(env);
        assertEquals(1, evaluation);
    }

}
