package d5.designadapterdecorate;

import java.io.IOException;

public interface TokenStream {
    Token consumeToken() throws IOException;
}
