package com.lisi.esdemo.正则;

import org.junit.Test;

import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
//        String regex = ".*my\\s+name\\s+is\\s+lisi";
        String regex = ".*my\\s+name\\s+is\\s+lisi";
        boolean my = Pattern.matches(regex, "hello amy name is lisi");
        System.out.println(my);
    }


    @Test
    public void test01(){
        String regex = "\\d+(\\.\\d)?";
        boolean flag = Pattern.matches(regex, "5");
        System.out.println(flag);
    }

    @Test
    public void test02(){
        String content = "I am noob " +
                "from runoob.com.";
        String pattern = ".*runoob.*";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
    }

}
