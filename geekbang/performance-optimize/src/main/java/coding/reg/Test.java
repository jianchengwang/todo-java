package coding.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jianchengwang
 * @date 2023/4/21
 */
public class Test {

    public void test1() {
        String text = "<input high=\"20\" weight=\"70\">test</input>";
        String reg="(<input.*?>)(.*?)(</input>)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(text);
        while(m.find()) {
            System.out.println(m.group(0));//整个匹配到的内容
            System.out.println(m.group(1));//(<input.*?>)
            System.out.println(m.group(2));//(.*?)
            System.out.println(m.group(3));//(</input>)
        }
    }

    public void test2() {

        String text = "<input high=\"20\" weight=\"70\">test</input>";
        String reg="(?:<input.*?>)(.*?)(?:</input>)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(text);
        while(m.find()) {
            System.out.println(m.group(0));//整个匹配到的内容
            System.out.println(m.group(1));//(.*?)
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.test1();
        test.test2();
    }
}
