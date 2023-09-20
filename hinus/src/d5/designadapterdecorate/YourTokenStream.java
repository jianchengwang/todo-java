package d5.designadapterdecorate;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class YourTokenStream extends InputStreamReader implements TokenStream {

    private final static Map<Character, Token> SymbolTypeMap = new HashMap<>();
    static {
        SymbolTypeMap.put('(', new Token(Token.TokenType.LPAR, "("));
        SymbolTypeMap.put(')', new Token(Token.TokenType.RPAR, ")"));
        SymbolTypeMap.put('+', new Token(Token.TokenType.PLUS, "+"));
        SymbolTypeMap.put('-', new Token(Token.TokenType.MINUS, "-"));
        SymbolTypeMap.put('*', new Token(Token.TokenType.MULT, "*"));
        SymbolTypeMap.put('/', new Token(Token.TokenType.DIV, "/"));
    }

    public YourTokenStream(InputStream in) {
        super(in);
    }

    @Override
    public Token consumeToken() throws IOException {
        int character = read();
        Token currentToken;
        if(character == -1) {
            currentToken = new Token(Token.TokenType.NONE, null);
            return currentToken;
        }
        char characterChar = (char) character;
        if(SymbolTypeMap.containsKey(characterChar)) {
            currentToken = SymbolTypeMap.get(characterChar);
        } else {
            currentToken = new Token(Token.TokenType.INT, (int) characterChar);
        }
        return currentToken;
    }
}
