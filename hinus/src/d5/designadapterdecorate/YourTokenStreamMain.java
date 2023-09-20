package d5.designadapterdecorate;

import java.io.IOException;

public class YourTokenStreamMain {
    public static void main(String[] args) throws IOException {
        TokenStream ts = new YourTokenStream(System.in);
        Token token = ts.consumeToken();
        while (token.tokenType != Token.TokenType.NONE) {
            ts.consumeToken();
            System.out.printf("{%s, %s} ", token.tokenType, token.value);
        }
    }
}
