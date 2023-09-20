package d5.designadapterdecorate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * https://zhuanlan.zhihu.com/p/24457041
 */
public class AdapterMain {
    public static void main(String[] args) {
        char[] cbuf = new char[256];
        System.out.println("hey, may I have your name, please? ");
        int n = 0;
        Reader r = new InputStreamReader(System.in);
        try {
            n = r.read(cbuf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("hello, Mr. " + cbuf[0]);
    }
}
