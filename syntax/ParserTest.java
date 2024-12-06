//package syntax;
//
//import eval.EvalException;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
///**
// * To assist you in creating correct parse trees.
// */
//class ParserTest {
//
//    @Test
//    void testIdentifier() throws SyntaxException, EvalException {
//        Parser parser = new Parser();
//        String prg = "x";
//        System.out.println(formatString(parser.parse(prg).toString()));
//    }
//
//
//    @Test
//    void testAddition() throws SyntaxException, EvalException {
//        Parser parser = new Parser();
//        String prg = "x + 3";
//
//        System.out.println(formatString(parser.parse(prg).toString()));
//    }
//
//    @Test
//    void testSyntax_1() {
//
//        Parser parser = new Parser();
//        String prg = "x * *";
//        assertThrows(SyntaxException.class, () -> {
//            parser.parse(prg);
//        });
//    }
//
//    @Test
//    void testSyntax_2() {
//
//        Parser parser = new Parser();
//        String prg = "x + (";
//        assertThrows(SyntaxException.class, () -> {
//            parser.parse(prg);
//        });
//    }
//
//    @Test
//    void testSyntax_3() throws SyntaxException {
//
//        Parser parser = new Parser();
//        String prg = "x + (x)";
//        System.out.println(formatString(parser.parse(prg).toString()));
//    }
//
//    @Test
//    void testSyntax_4() throws SyntaxException {
//
//        Parser parser = new Parser();
//        String prg = "10 - 4 - 3";
//        System.out.println(formatString(parser.parse(prg).toString()));
//    }
//
//    @Test
//    void testSyntax_5() throws SyntaxException {
//
//        Parser parser = new Parser();
//        String prg = "x + ((x))";
//        System.out.println(formatString(parser.parse(prg).toString()));
//    }
//
//    @Test
//    void testSyntax_6() {
//
//        Parser parser = new Parser();
//        String prg = "x + 3)";
//        assertThrows(SyntaxException.class, () -> {
//            parser.parse(prg);
//        });
//    }
//
//    @Test
//    void testSyntax_7() {
//
//        Parser parser = new Parser();
//        String prg = "-x + 3";
//        assertThrows(SyntaxException.class, () -> {
//            parser.parse(prg);
//        });
//    }
//
//    @Test
//    void testSyntax_8() throws SyntaxException {
//
//        Parser parser = new Parser();
//        String prg = "( x + 3 ) / 3";
//        System.out.println(formatString(parser.parse(prg).toString()));
//    }
//
//    @Test
//    void testSyntax_9() throws SyntaxException {
//
//        Parser parser = new Parser();
//        String prg = "(((x)))";
//        System.out.println(formatString(parser.parse(prg).toString()));
//    }
//
//    /**
//     * Helper method that simply adds newlines and tabs to a string where there are ( and )
//     * @param tree - Non formatted version of the tree
//     * @return formatted version of the String
//     */
//    private String formatString(String tree) {
//        StringBuilder sb = new StringBuilder();
//
//        int indents = 0;
//        int position = 0;
//        int old = 0;
//
//        while (position < tree.length()) {
//            if (tree.charAt(position) == '(') {
//                sb.append(tree, old, position + 1);
//                old = position + 1;
//                indents++;
//                sb.append('\n');
//                sb.append("\t".repeat(indents));
//            }
//            if (tree.charAt(position) == ')') {
//                sb.append(tree, old, position);
//                old = position + 1;
//                sb.append('\n');
//                indents--;
//                sb.append("\t".repeat(indents));
//                sb.append(" )\n");
//                sb.append("\t".repeat(indents));
//            }
//            position++;
//        }
//
//        return sb.toString();
//    }
//
//}