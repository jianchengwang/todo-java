package d5.designadapterdecorate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DecorateMain {
    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String hello = br.readLine();
            System.out.println("hello, " + hello);
        }
    }
}
