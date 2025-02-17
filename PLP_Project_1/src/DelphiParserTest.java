import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

public class DelphiParserTest {
    public static void main(String[] args) throws IOException {
        // Example Delphi source code
        String inputCode = " PROGRAM Test;\n" +
                "VAR\n" +
                "    x: Integer;\n" +
                "BEGIN\n" +
                "    x := 5 + 3;\n" +
                "END. ";

        // Create input stream from the code
        CharStream input = CharStreams.fromString(inputCode);

        // Create a lexer
        DelphiLexer lexer = new DelphiLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser
        DelphiParser parser = new DelphiParser(tokens);

        // Parse using the starting rule (change `program` if your grammar has a different entry point)
        ParseTree tree = parser.program();

        // Print the parse tree
        System.out.println(tree.toStringTree(parser));
    }
}
