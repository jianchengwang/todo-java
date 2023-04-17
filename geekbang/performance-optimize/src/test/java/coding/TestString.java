package coding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class TestString {

    @Test
    public void test() {
        String str1= "ab" + "cd" + "ef";
        String str = "abcdef";
        for(int i=0; i<1000; i++) {
            str = str + i;
        }
    }

    @Test
    public void test2() {

        String a = "abc";
        String b = new String("abc").intern();
        if(a==b) {
            System.out.println("a==b");
        }
    }

    @Test
    public void test3() {

        String a = "a,b,c";
        a.split(",");
        // split有两种情况不会使用正则表达式：
        //第一种为传入的参数长度为1，且不包含“.$|()[{^?*+\\”regex元字符的情况下，不会使用正则表达式；
        //第二种为传入的参数长度为2，第一个字符是反斜杠，并且第二个字符不是ASCII数字或ASCII字母的情况下，不会使用正则表达式。
    }

    @Test
    public void test4() {

        String str1= "abc";
        String str2= new String("abc");
        String str3= str2.intern();
        System.out.println(str1==str2);
        System.out.println(str2==str3);
        System.out.println(str1==str3);
    }

    @Test
    public void test5() {
        String s1 = new String("1")+new String("1");
        s1.intern();
        String s2="11";
        System.out.println(s1==s2);


        String s3 = new String("11");
        s3.intern();
        String s4="11";
        System.out.println(s3==s4);
    }
}
