package com.luochaoqun.plus.ai.ocr.simChina;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

public class SimChina {

    /*
     * 中文转unicode编码
     */
    public static String char2Unicode(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    /*
     * unicode编码转中文
     */
    public static String unicode2Char(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    public static int char2Num(char c) {
        int num = (int)c;
        return num;
    }

    public static String num2Unicode(int num) {
        String uniocode =  Integer.toHexString(num);
        return uniocode;
    }

    public static String char2unicode(char c) {
        String unicode = Integer.toHexString(c);
        return "\\u"+unicode;
    }

    public static int unicode2Int(String unicode) {
        int num = Integer.parseInt(unicode,16);
        return num;
    }

    public static String char2pingying(char simpleChina) {
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String[] res;
        StringBuffer sb=new StringBuffer();
        try {
            res = PinyinHelper.toHanyuPinyinStringArray(simpleChina,outputFormat);
            sb.append(res[0]);//对于多音字，只用第一个拼音
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        // 基本汉子，20902，4E00-9FA5 19968-40869
        System.out.println("4E00="+unicode2Int("4E00"));
        System.out.println("9FA5="+unicode2Int("9FA5"));

        System.out.println(unicode2Int("4E00"));

        for (int i = 19968;i<=40869;i++) {
            char simpleChar = unicode2Char("\\u"+num2Unicode(i)).charAt(0);
            System.out.println(i + " " + simpleChar + " " + char2pingying(simpleChar));
        }
    }

}
