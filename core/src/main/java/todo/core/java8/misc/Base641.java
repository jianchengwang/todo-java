package todo.core.java8.misc;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Base641 {


    public static void main(String[] args) {
        // Encoding a string to base 64
        Base64.Encoder encoder = Base64.getEncoder();
        String normalString = "username:password";
        String encodedString1 = encoder.encodeToString(
                normalString.getBytes(StandardCharsets.UTF_8) );

        // Decoding a base 64 encoded string
        String encodedString2 = "dXNlcm5hbWU6cGFzc3dvcmQ=";
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(encodedString2);
        //Verify the decoded string
        System.out.println(new String(decodedByteArray));

        // Wrap to a base 64 encoded output stream
        Path originalPath = Paths.get("c:/temp", "mail.txt");
        Path targetPath = Paths.get("c:/temp", "encoded.txt");
        Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
        try(OutputStream output = Files.newOutputStream(targetPath)){
            //Copy the encoded file content to target file
            Files.copy(originalPath, mimeEncoder.wrap(output));
            //Or simply use the encoded output stream
            OutputStream encodedStrem = mimeEncoder.wrap(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
