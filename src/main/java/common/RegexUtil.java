package common;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\t|\r|\n";//定义空格回车换行符
    private static final String regEx_html_quot = "&\\w+;|&#\\w+;"; //匹配&quot
    private static final String regEx_html_String = "Br>"; //匹配&quot

    private static final String HTTP_REG = "[a-zA-z]+://[^\\s]*";

    /**
     * 检查http
     *
     * @param link
     * @return
     */
    public static boolean checkHttp(String link) {
        if (!Strings.isNullOrEmpty(link)) {
            if (link.matches(HTTP_REG)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param htmlStr
     * @return 删除Html标签
     */
    public static String delHTMLTag(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签

        Pattern p_quot = Pattern.compile(regEx_html_quot, Pattern.CASE_INSENSITIVE);
        Matcher m_quot = p_quot.matcher(htmlStr);
        htmlStr = m_quot.replaceAll(""); // 过滤html标签

        Pattern p_string = Pattern.compile(regEx_html_String, Pattern.CASE_INSENSITIVE);
        Matcher m_string = p_string.matcher(htmlStr);
        htmlStr = m_string.replaceAll(""); // 过滤html标签

        return htmlStr.trim(); // 返回文本字符串
    }

    public static String getTextFromHtml(String htmlStr) {
        htmlStr = delHTMLTag(htmlStr);
        htmlStr = htmlStr.replaceAll(" ", "");
        htmlStr = htmlStr.substring(0, htmlStr.indexOf("。") + 1);
        return htmlStr;
    }

    /**
     * 根据正则表达式解析数据
     *
     * @param regexp
     * @param data
     * @return
     */
    public static String getDataByRegex(String regexp, String data) {
        List<String> list = matchGroup(regexp, data);
        if (null != list && list.size() > 0) {
            return list.get(0).trim();
        }
        return null;
    }

    public static List<String> matchGroup(String regexp, String data) {
        Pattern p = Pattern.compile(regexp, Pattern.DOTALL);
        Matcher matcher = p.matcher(data);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {
            int length = matcher.groupCount();
            for (int i = 1; i < length + 1; i++) {
                list.add(matcher.group(i).trim());
            }
        }
        return list;
    }


    public static void main(String[] args) {
        String str = "“JE T&#39;AIME” TEDDY黑色缎面和透明水晶夹克";
        System.out.println(RegexUtil.delHTMLTag(str));
    }
}
