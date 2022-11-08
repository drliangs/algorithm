package com.example.suanfa.bilbil;


import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

//https://leetcode.cn/problems/decode-string/
public class LeetCode_394 {
    int ptr;


    //输入：s = "3[a]2[bc]"
    //输出："aaabcbc"
    public String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }

    //输入：s = "3[a]2[bc]"
    //输出："aaabcbc"
    //3[a2[c]]
    public static String decodeString1(String s) {
        StringBuilder res = new StringBuilder();
        Stack<String> resStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                StringBuilder builder = new StringBuilder();
                while (Character.isDigit(s.charAt(index))) {
                    builder.append(s.charAt(index++));
                }
                countStack.push(Integer.parseInt(builder.toString()));
            } else if (c == '[') {
                resStack.push(res.toString());
                index++;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder builder = new StringBuilder(resStack.pop());
                int repeatTime = countStack.pop();
                for (int i = 0; i < repeatTime; i++) {
                    builder.append(res);
                }
                res = builder;
                index++;
            } else res.append(s.charAt(index++));

        }
        
        return res.toString();
    }

    //3[a2[c]]
    public static String decodeString2(String s) {
        StringBuilder result = new StringBuilder();
        Stack<String> resStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            if (Character.isDigit(s.charAt(index))) {
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(s.charAt(index))) {
                    sb.append(sb.charAt(index++));
                }
                countStack.push(Integer.parseInt(sb.toString()));
            } else if (c == '[') {
                resStack.add(result.toString());
                index++;
                result = new StringBuilder();
            } else if (c == ']') {
                StringBuilder builder = new StringBuilder(resStack.pop());
                for (int i = 0; i < countStack.pop(); i++) {
                    builder.append(result);
                }
                result = builder;
                index++;
            } else result.append(s.charAt(index++));
        }
        return result.toString();
    }
}
