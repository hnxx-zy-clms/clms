/**
 * @FileName: StringUtils
 * @Author: code-fusheng
 * @Date: 2020/3/18 11:18
 * Description: 增强String工具类
 * 1. 此工具类为 org.apache.commons.lang3.StringUtils 的增强实现
 * 2. 操作对象为java.lang.String类型的对象，是jdk提供的String类型操作的补充，保证null安全
 * 3. public static boolean isEmpty(String str) 判断某字符串是否为空，为空的标准 str==null或str.length()==0
 * 4. public static boolean isNotEmpty(String str) 判断某字符串是否非空，等于!isEmpty(String str)
 * 5. public static boolean isBlank(String str) 判断某字符串是否为空或长度为0或由空白符(whitespace)构成
 * 6. public static boolean isNotBlank(String str) 判断某字符串是否不为空且长度不为0且不由空白符(whitespace)构成，等于!isBlank(String str)
 * 详情参考博客 https://www.cnblogs.com/zhaoyan001/p/6599477.html
 */
package com.hnxx.zy.clms.common.utils;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final String LOCAL_HOST_IP = "127.0.0.1";

    /**
     * Excel科学计数法关键字
     */
    private static final String EXCEL_F_E = "E";

    private StringUtils() {
    }

    /**
     * 下划线转驼峰
     */
    public static String upperTable(String str) {
        StringBuilder result = new StringBuilder();
        String[] a = str.split("_");
        for (String s : a) {
            if (!str.contains("_")) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 驼峰转下划线
     */
    @SuppressWarnings("all")
    public static String upperCharToUnderLine(String param) {
        StringBuilder sb = new StringBuilder(param);
        int temp = 0;
        if (!param.contains("_")) {
            for (int i = 0; i < param.length(); i++) {
                if (Character.isUpperCase(param.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteIp(HttpServletRequest request) {

        String remoteAddr = request.getRemoteAddr();
        String remoteIp = request.getHeader("X-Forwarded-For");
        if (isBlank(remoteIp) || LOCAL_HOST_IP.equals(remoteIp)) {
            remoteIp = request.getHeader("X-Real-IP");
        }
        if (isBlank(remoteIp) || LOCAL_HOST_IP.equals(remoteIp)) {
            remoteIp = request.getHeader("Proxy-Client-IP");
        }
        if (isBlank(remoteIp) || LOCAL_HOST_IP.equals(remoteIp)) {
            remoteIp = request.getHeader("WL-Proxy-Client-IP");
        }
        String ip = remoteIp != null ? remoteIp : remoteAddr;

        int pos = ip.indexOf(',');
        if (pos >= 0) {
            ip = ip.substring(0, pos);
        }

        return ip;
    }

    /**
     * 替换集合中的字符串
     *
     * @param str
     * @param beforeList
     * @param after
     * @return
     */
    public static String getReplaced(String str, List<String> beforeList, String after) {
        String result = trim(str);
        if (StringUtils.isNotBlank(result)) {
            for (String before : beforeList) {
                result = StringUtils.replace(result, before, after);
            }
        }
        return result;
    }

    /**
     * 中文钱数
     *
     * @param money
     * @return
     */
    public static String getChineseMoney(BigDecimal money) {
        if (money != null) {
            String s = new DecimalFormat("#.00").format(money.abs());
            // 将字符串中的"."去掉
            s = s.replaceAll("\\.", "");
            char[] d = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
            String unit = "仟佰拾兆仟佰拾亿仟佰拾万仟佰拾元角分";
            int c = unit.length();
            StringBuffer sb = new StringBuffer(unit);
            for (int i = s.length() - 1; i >= 0; i--) {
                sb.insert(c - s.length() + i, d[s.charAt(i) - 0x30]);
            }
            s = sb.substring(c - s.length(), c + s.length());
            s = s.replaceAll("零[仟佰拾]", "零").replaceAll("零{2,}", "零").replaceAll("零([兆万元Ԫ])", "$1").replaceAll("零[角分]", "");
            if (BigDecimal.ZERO.compareTo(money) == 1) {
                return "负" + s + "整";
            }
            if (BigDecimal.ZERO.compareTo(money) == 0) {
                return "零元整";
            }
            return s + "整";
        }
        return "";
    }

    /**
     * 获取列对应的下标字母
     */
    public static String getExcelCellIndex(int index) {
        StringBuilder rs = new StringBuilder();
        while (index > 0) {
            index--;
            rs.insert(0, ((char) (index % 26 + 'A')));
            index = (index - index % 26) / 26;
        }
        return rs.toString();
    }

    public static String toString(Object obj) {
        return Objects.toString(obj, "");
    }

    public static Boolean equal(Object obj1, Object obj2) {
        return toString(obj1).equals(toString(obj2));
    }

    /**
     * 把中文转成Unicode码
     *
     * @param str 字符
     * @return 转码后的字符
     */
    public static String chinaToUnicode(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int chr1 = str.charAt(i);
            // 汉字范围 \u4e00-\u9fa5 (中文)
            if (chr1 >= 19968 && chr1 <= 171941) {
                result.append("\\u").append(Integer.toHexString(chr1));
            } else {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    /**
     * 去除空白字符
     *
     * @return String 去除\u00A0和空格后的字符串
     */
    public static String trim(String str) {
        // unicode 化
        if (isEmpty(str)) {
            return null;
        }
        String u00A0 = StringEscapeUtils.unescapeJava("\u00A0");
        str = str.replaceAll(u00A0, "");
        return str.trim();
    }

    /**
     * 判断字符串是否是链接
     *
     * @param href
     * @return
     */
    public static Boolean isHref(String href) {
        boolean foundMatch;
        try {
            foundMatch = href.matches("(?sm)^https?://[-\\w+&@#/%=~_|$?!:,.\\\\*]+$");
        } catch (Exception e) {
            return false;
        }
        return foundMatch;
    }

    /**
     * 清除bom
     *
     * @param str
     * @return
     */
    public static String clearUtf8bm4(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str.replaceAll("(?sm)[^\u0000-\uD7FF\uE000-\uFFFF]", "");
    }

    public static void assertString(String object, String objectName) {
        Assert.notNull(object, objectName + " is null");
        Assert.hasLength(object.trim(), objectName + " is empty");
    }

    /**
     * 字串是否包含字串集合中的某一个字串
     *
     * @param str      字串
     * @param contains 被包含字串集合
     * @return
     */
    public static boolean stringContainsList(String str, List<String> contains) {
        for (String item : contains) {
            if (str.contains(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否包含数组中的某一项
     */
    public static boolean stringContainsArr(String uri, String[] arr) {
        for (String s : arr) {
            if (uri.contains(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否包含数组中的某一项，不区分大小写
     */
    public static boolean stringContainsArrIgnoreCase(String uri, String[] arr) {
        for (String s : arr) {
            if (uri.toLowerCase().contains(s.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取异常堆栈信息
     *
     * @param e
     * @return
     */
    public static String getStackTraceInfo(Throwable e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        return writer.getBuffer().toString();
    }

    /**
     * 获取指定包名的异常
     *
     * @param e
     * @return
     */
    public static String getPackageException(Throwable e, String packageName) {
        String exception = getStackTraceInfo(e);
        StringBuilder returnStr = new StringBuilder();
        Pattern pattern = Pattern.compile("^.*(" + packageName + "|Exception:|Cause).*$", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(exception);
        while (matcher.find()) {
            returnStr.append(matcher.group()).append("\n");
        }
        return returnStr.toString();
    }

    /**
     * 对象是否为无效值
     *
     * @param obj 要判断的对象
     * @return 是否为有效值（不为null 和 "" 字符串）
     */
    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || "".equals(obj.toString());
    }

    /**
     * 参数是否是有效整数
     *
     * @param obj 参数（对象将被调用string()转为字符串类型）
     * @return 是否是整数
     */
    public static boolean isInt(Object obj) {
        if (isNullOrEmpty(obj)) {
            return false;
        }
        if (obj instanceof Integer) {
            return true;
        }
        return obj.toString().matches("[-+]?\\d+");
    }

    /**
     * 判断一个对象是否为boolean类型,包括字符串中的true和false
     *
     * @param obj 要判断的对象
     * @return 是否是一个boolean类型
     */
    public static boolean isBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return true;
        }
        String strVal = String.valueOf(obj);
        return "true".equalsIgnoreCase(strVal) || "false".equalsIgnoreCase(strVal);
    }

    /**
     * 对象是否为true
     *
     * @param obj
     * @return
     */
    public static boolean isTrue(Object obj) {
        return "true".equals(String.valueOf(obj));
    }

    public static boolean matches(String regex, String input) {
        return (null != regex && null != input) && Pattern.matches(regex, input);
    }

    /**
     * 公用脱敏
     *
     * @param bankNumber 需要脱敏的字符串
     * @param startNum   保留前几位
     * @param endNum     保留后几位
     */
    public static String hideStr(String bankNumber, int startNum, int endNum) {
        if (isEmpty(bankNumber)) {
            return "";
        } else if (bankNumber.length() < (startNum + endNum)) {
            return bankNumber;
        } else {
            StringBuilder temp = new StringBuilder();
            if (startNum > 0) {
                temp.append(bankNumber, 0, startNum);
            }
            for (int i = 0; i < bankNumber.length() - startNum - endNum; i++) {
                temp.append("*");
            }
            temp.append(bankNumber.substring(bankNumber.length() - endNum));
            return temp.toString();
        }
    }

    /**
     * 生成指定位数的随机数
     *
     * @return
     */
    public static String getRandom(int num) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    /**
     * double to String 防止科学计数法
     *
     * @param value
     * @return
     */
    public static String doubleToString(Double value) {
        String temp = value.toString();
        if (temp.contains(EXCEL_F_E)) {
            BigDecimal bigDecimal = new BigDecimal(temp);
            temp = bigDecimal.toPlainString();
        }
        return temp;
    }

}
