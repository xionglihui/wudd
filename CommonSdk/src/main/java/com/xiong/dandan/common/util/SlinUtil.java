/**
 *
 */
package com.xiong.dandan.common.util;

import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xionglh
 * @version SlinUtil.java Created by xionglh on 2015年4月2日 下午2:20:37 v1.0.3
 */
public class SlinUtil {

    /**
     * 匹配邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email))
            return false;
        // Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 验证是否为手机
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if (mobiles.length() < 11)
            return false;
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[0-9])|(14[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 生成验证码
     *
     * @return
     */
    public static String getVerificationCode(TextView btn) {
        String code = getRandomString(4);
        btn.setText(code);
        return code;
    }

    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 手机显示
     *
     * @param mobile
     * @return
     */
    public static String mobileFilter(String mobile) {
        try {
            String str = mobile.substring(0, 3) + " **** "
                    + mobile.substring(mobile.length() - 4, mobile.length());
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 证件(身份证)显示
     *
     * @param id
     * @return
     */
    public static String idFilter(String id) {
        if (StrUtils.isEmpty(id))
            return "";
        try {
            String str = id.substring(0, 4) + " **** **** "
                    + id.substring(id.length() - 4, id.length());
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 卡号显示
     *
     * @param cardNo
     * @return
     */
    public static String cardNoFilter(String cardNo) {
        try {
            String str = cardNo.substring(0, 4) + " **** **** "
                    + cardNo.substring(cardNo.length() - 4, cardNo.length());
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 卡号显示
     *
     * @param cardNo
     * @return
     */
    public static String cardNoFilter2(String cardNo) {
        try {
            String str = "**** "
                    + cardNo.substring(cardNo.length() - 4, cardNo.length());
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 邮箱显示
     *
     * @param em
     * @return
     */
    public static String emailFiger(String em) {
        if (null != em && !"".equals(em)) {
            String[] emimal = em.split("\\@");
            String showOne = emimal[0].length() <= 3 ? emimal[0] : emimal[0]
                    .substring(0, 3);

            return showOne + "****@" + emimal[1];
        } else {
            return "";
        }
    }

    /**
     * 必须是同时包含数字与字母
     *
     * @param password 密码
     * @return
     */
    public static boolean isPasswordAvailable(String password) {
        String str2 = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        return password.matches(str2);
    }


    /**
     * 将ip的整数形式转换成ip形式
     *
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    /**
     * 格式化数字为千分位显示；
     *
     * @param text 要格式化的数字
     * @return
     */
    public static String fmtMicrometer(String text) {
        if ("0".equals(text) || "".equals(text) || text == null) {
            return "0.00";
        }

        int strIndexOf = text.lastIndexOf(".");
        if (strIndexOf > 0) {
            text = text + "00";
            text = text.substring(0, strIndexOf + 3);
        } else {
            text += ".00";
        }

        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {
            if (text.length() - text.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.");
            } else if (text.length() - text.indexOf(".") - 1 == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }

    /**
     * 格式化数字为千分位显示；
     *
     * @param text 要格式化的数字
     * @return
     */
    public static String fmtMicrometerNoPoint(String text) {
        if ("0".equals(text) || "".equals(text) || text == null) {
            return "0";
        }

        int strIndexOf = text.lastIndexOf(".");
        if (strIndexOf > 0) {
            text = text + "00";
            text = text.substring(0, strIndexOf + 3);
        }

        DecimalFormat df = null;

        df = new DecimalFormat("###,##0");

        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }

    /**
     * @param text
     * @return
     */
    public static String fmtMicrometerTwo(String text) {

        int strIndexOf = text.lastIndexOf(".");
        if (strIndexOf > 0) {
            text = text + "00";
            text = text.substring(0, strIndexOf + 3);
        }

        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {
            if (text.length() - text.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.");
            } else if (text.length() - text.indexOf(".") - 1 == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }

    /**
     * 格式化年化利率 *
     */
    public static String forMatyearRate(double yearRate) {
        DecimalFormat dfTwo = new DecimalFormat("0.00");
        return dfTwo.format(yearRate * 100) + "%";
    }

    /**
     * Bigdecimal截取0位小数
     *
     * @param v1 截取值
     * @return 补位后的值
     */
    public static BigDecimal formatScale0(BigDecimal v1) {
        if (v1 == null)
            return new BigDecimal("0");
        return v1.setScale(0, 5);
    }

    /**
     * Bigdecimal截取两位小数
     *
     * @param v1 截取值
     * @return 补位后的值
     */
    public static BigDecimal formatScale2(BigDecimal v1) {
        if (v1 == null)
            return new BigDecimal("0");
        return v1.setScale(2, BigDecimal.ROUND_DOWN);
    }

    /**
     * 格式化年化利率
     *
     * @param v1 截取值
     * @return 补位后的值
     */
    public static String formatforMatyearRateScale2(BigDecimal v1) {
        if (v1 == null)
            return "0.00%";
        return formatScale2(v1.multiply(new BigDecimal("100"))).toString()
                + "%";
    }

    /**
     * 格式化年化利率
     *
     * @param v1 截取值
     * @return 补位后的值
     */
    public static String formatforMatyearRateScaleper(BigDecimal v1) {
        if (v1 == null)
            return "0.0";
        return formatScale1(v1.multiply(new BigDecimal("100"))).toString();
    }

    /**
     * 截取0位小数 并格式化金额
     *
     * @param amount
     * @return
     */
    public static String formatAmount0(BigDecimal amount) {
        return fmtMicrometerNoPoint(formatScale0(amount).toString());
    }


    /**
     * 截取两位小数 并格式化金额
     *
     * @param amount
     * @return
     */
    public static String formatAmount(BigDecimal amount) {
        return fmtMicrometer(formatScale2(amount).toString());
    }

    /**
     * Bigdecimal截取一位小数
     *
     * @param v1 截取值
     * @return 补位后的值
     */
    public static BigDecimal formatScale1(BigDecimal v1) {
        if (v1 == null)
            return new BigDecimal("0");
        return v1.setScale(1, BigDecimal.ROUND_DOWN);
    }


    /**
     * 验证密码格式是否正确
     *
     * @param passwd
     * @return
     */
    public static boolean isPasswd(String passwd) {
        Pattern p = Pattern
                .compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
        Matcher m = p.matcher(passwd);
        return m.matches();
    }

    /**
     * 当期日期加numberDay天
     *
     * @return
     */
    public static String getCurrentAddTowDay(int numberDay) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        c.add(Calendar.DAY_OF_MONTH, numberDay);
        return sf.format(c.getTime());
    }

    /**
     * 转换人名只显示姓
     */
    public static String forMateStrName(String name) {
        if (StrUtils.isEmpty(name))
            return "";
        String strNameStart = name.substring(0, 1);
        return strNameStart + "***";
    }

    /**
     * 转Date yyyy-MM-dd *
     */
    public static String forSortDate(String shorDate) {
        return DateUtilSL.dateToStr(DateUtilSL.getDateByLong(Long
                .valueOf(shorDate)));
    }
}
