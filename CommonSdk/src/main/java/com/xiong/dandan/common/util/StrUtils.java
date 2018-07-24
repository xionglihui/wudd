package com.xiong.dandan.common.util;

import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * String process utilities.
 * 
 */
public class StrUtils {
    private static char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * Checks whether the given string is null or has zero length.
     * 
     * @param str
     *            String to test.
     * @return true when the given string is null or zero length, or false.
     */
    public static boolean isEmpty(String str) {
        return (str == null) || (str.length() == 0);
    }

    /**
     * Retrieves either the original string or an empty one if the argument is
     * null.
     * 
     * @param value
     *            Original string.
     * @return Not null string;
     */
    public static String notNullStr(String value) {
        return (value == null) ? "" : value;
    }

    /**
     * Converts given string to an integer value in a safe way.
     * 
     * @param str
     *            string to convert
     * @param dft
     *            default value in case the string cannot be converted
     * @return integer value represented in the string, or the default value
     */
    public static int toInt(String str, int dft) {
        if (isEmpty(str)) {
            return dft;
        }

        int ret = dft;
        try {
            ret = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            ret = dft;
        }

        return ret;
    }

    /**
     * Converts given string to a long value in a safe way.
     * 
     * @param str
     *            string to convert
     * @param dft
     *            default value in case the string cannot be converted
     * @return long value represented in the string, or the default value
     */
    public static long toLong(String str, long dft) {
        if (isEmpty(str)) {
            return dft;
        }

        long ret = dft;
        try {
            ret = Long.parseLong(str);
        } catch (NumberFormatException ex) {
            ret = dft;
        }

        return ret;
    }

    /**
     * Converts given string to a byte value in a safe way.
     * 
     * @param str
     *            string to convert
     * @param dft
     *            default value in case the string cannot be converted
     * @return long value represented in the string, or the default value
     */
    public static byte toByte(String str, byte dft) {
        return (byte) toInt(str, dft);
    }

    /**
     * Converts given string to a float value in a safe way.
     * 
     * @param str
     *            string to convert
     * @param dft
     *            default value in case the string cannot be converted
     * @return float value represented in the string, or the default value
     */
    public static float toFloat(String str, float dft) {
        if (isEmpty(str)) {
            return dft;
        }

        float ret = dft;
        try {
            ret = Float.parseFloat(str);
        } catch (NumberFormatException ex) {
            ret = dft;
        }

        return ret;
    }

    /**
     * Converts given string to a double value in a safe way.
     * 
     * @param str
     *            string to convert
     * @param dft
     *            default value in case the string cannot be converted
     * @return float value represented in the string, or the default value
     */
    public static double toDouble(String str, double dft) {
        if (isEmpty(str)) {
            return dft;
        }

        double ret = dft;
        try {
            ret = Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            ret = dft;
        }

        return ret;
    }

    /**
     * Converts the given string to a boolean value in a safe way. True can be
     * represented as: "1", "yes", "true", "ok" or a number > 0. False can be
     * represented as: "0", "no", "false".
     * 
     * @param str
     *            string to convert
     * @param dft
     *            default value in case the string cannot be converted
     * @return boolean value represented in the string, or the default value
     */
    public static boolean toBoolean(String str, boolean dft) {
        if (isEmpty(str)) {
            return dft;
        }

        boolean ret = dft;

        if (str.equals("1")) {
            ret = true;
        } else if (str.equals("0")) {
            ret = false;
        } else {
            int value = toInt(str, 0);
            if (value > 0) {
                ret = true;
            } else {
                String uStr = str.toUpperCase();
                if (uStr.equals("TRUE") || uStr.equals("YES")
                        || uStr.equals("OK")) {
                    ret = true;
                } else if (uStr.equals("FALSE") || uStr.equals("NO")) {
                    ret = false;
                }
            }
        }

        return ret;
    }

    /**
     * Private construtors means that the class is just a utilities and it is
     * not allowed to extend.
     */
    private StrUtils() {
        // empty here
    }

    public static String bytes2HexString(byte[] b, String separator) {
        if (b != null) {
            StringBuffer sb = new StringBuffer(b.length * 2);
            for (int i = 0; i < b.length; i++) {
                // look up high nibble char
                sb.append(hexChars[(b[i] & 0xf0) >>> 4]); // fill left with zero
                                                          // bits
                // look up low nibble char
                sb.append(hexChars[b[i] & 0x0f]);

                if (!isEmpty(separator) && (i < b.length - 1)) {
                    sb.append(separator);
                }
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * Extracts the value part of the token starting with the specified pattern.
     * Tokens are delimited with sSep. Case is ignored.
     * 
     * Example: GetValueStartingWith("a=15;b=-23;c=3", "b=", ";", "0") ==>
     * "-23".
     * 
     * Warning! GetValueStartingWith("stt=5;t=abc", "t=", ";", "0") ==> "5" !!!
     * 
     * @param sStr
     *            string String to search in.
     * @param sPtt
     *            string Pattern the value starts with.
     * @param sSep
     *            string Tokens separator.
     * @param sDft
     *            string Default value in case pattern is not found.
     * @return Value part of the token.
     */
    public static String getValueStartingWith(final String sStr,
            final String sPtt, final String sSep, final String sDft) {
        if (isEmpty(sStr)) {
            return sDft;
        }

        String ret = sDft;
        try {
            int pos = sStr.indexOf(sPtt);
            if (pos < 0) {
                pos = sStr.toUpperCase().indexOf(sPtt.toUpperCase());
            }
            if (pos >= 0) {
                pos += sPtt.length();

                int end = sStr.indexOf(sSep, pos);
                if (end >= pos) {
                    ret = sStr.substring(pos, end);
                } else {
                    ret = sStr.substring(pos);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return ret;
    }

    /**
     * Returns a trimmed string. Additionally to specified characters the
     * function also removes bounding newline characters.
     * 
     * @param text
     *            string to trim
     * @return trimmed string
     */
    public static String trimAll(String text, char ch) {
        String ret = "";

        if (text != null) {
            ret = text.trim();
            while ((ret.length() > 0) && (ret.charAt(0) == ch)) {
                ret = ret.substring(1);
            }
            while ((ret.length() > 0) && (ret.charAt(ret.length() - 1) == ch)) {
                ret = ret.substring(0, ret.length() - 1);
            }
        }
        return ret;
    }

    /**
     * Checks whether the string is number.
     * 
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        if (isEmpty(str)) {
            return false;
        } else {
            return str
                    .matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
        }
    }

    /**
     * Filter all special character.
     * 
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String tringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        // String regEx = "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * Checks whether string contains Chinese.
     * 
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(charArray[i]);
            if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                    || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                    || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                    || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                return true;
            }
        }
        return false;
    }

    /**
     * Splits the given string into an array of substrings. Examples:
     * split("a;b;c;d;e", ';') ->"a", "b", "c", "d", "e" split("a;;c;d;e", ';')
     * -> "a", "", "c", "d", "e" split("a;b;c;d;e",'=') -> "a;b;c;d;e"
     * split(";b;c;d;e", ';') -> "", "b", "c", "d", "e" split(";", ';') -> "",""
     * split("", ';') -> ""
     * 
     * @param source
     *            String to split.
     * @param sep
     *            Separator character.
     * @return Array of string tokens. In case of no tokens an empty array is
     *         returned (never null.)
     */
    public static String[] split(String source, char sep) {
        if (isEmpty(source)) {
            return new String[] { "" };
        }

        int len = source.length();
        Vector<String> list = new Vector<String>();
        int i = 0;
        int start = 0;

        while (i < len) {
            if (source.charAt(i) == sep) {
                list.addElement(source.substring(start, i));
                start = i + 1;
            }
            i++;
        }
        if (i > start) // some trailing text found, append it
        {
            list.addElement(source.substring(start));
        } else if (source.charAt(len - 1) == sep) // source ends with a
                                                  // separator, add the final
                                                  // empty token
        {
            list.addElement("");
        }

        String[] arr = new String[list.size()];
        list.copyInto(arr);

        return arr;
    }
    
    /**
     * 创建UUID
     * 
     * @return UUID
     */
    public static String createUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * Replaces "searchFor" with "replaceWith" in "sourceStr".
     * 
     * @param sourceStr
     *            The source string
     * @param searchFor
     *            Pattern to be replaced
     * @param replaceWith
     *            Pattern to replace with
     * @return resulting string.
     */
    public static String replace(String sourceStr, String searchFor, String replaceWith) {
        if (sourceStr == null || searchFor == null || searchFor.length() == 0 || replaceWith == null)
            return sourceStr;

        // Search for searchStr
        int pos = sourceStr.indexOf(searchFor);
        if (pos < 0) {
            return sourceStr;
        }

        StringBuffer sb = new StringBuffer();

        while (pos >= 0) {
            sb.append(sourceStr.substring(0, pos)).append(replaceWith);

            sourceStr = sourceStr.substring(pos + searchFor.length());
            pos = sourceStr.indexOf(searchFor);
        }

        sb.append(sourceStr);

        return sb.toString();
    }
    
    /**
     * Trim the referred string on the right of source string.
     * 
     * @param src
     * @param trimString
     * @return
     */
    public static String trimRight(String src, String trimString)
    {
        if (isEmpty(src) || isEmpty(trimString))
        {
            return src;
        }
        while (src.endsWith(trimString))
        {
            src = src.substring(0, src.length() - trimString.length());
        }
        return src;
    }
    

    /**
     * Trim the referred string on the left of source string.
     * 
     * @param src
     * @param trimString
     * @return
     */
    public static String trimLeft(String src, String trimString)
    {
        if (isEmpty(src) || isEmpty(trimString))
        {
            return src;
        }
        while (src.startsWith(trimString))
        {
            src = src.substring(trimString.length());
        }
        return src;
    }

}
