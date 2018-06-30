package com.carol.tool;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 模板生成工具
 */
public class BeetlTool {
    private  static Pattern p = Pattern.compile("_([a-z])");
    private  static Pattern lengthPattern = Pattern.compile("[(|（](.*?)[)|）]");

    public static String  normalize(String sqlMode){
        if(!StringUtils.isNotEmpty(sqlMode)){
            return "";
        }
        String javaModel=sqlMode.toLowerCase();
        Matcher m = p.matcher(sqlMode);
        StringBuffer sb = new StringBuffer();
        while (m.find()){
            m.appendReplacement(sb, "\\" + m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static String  normalizeEnity(String sqlMode){
        String className = normalize(sqlMode);
        return className.substring(0,1).toUpperCase()+className.substring(1,className.length());
    }

    public static void  main(String [] args){
        System.out.println(BeetlTool.normalize("dst_hospital_info"));
        System.out.println(BeetlTool.normalizeEnity("dst_hospital_info"));;
        System.out.println(BeetlTool.getDataLength("varchar（12）"));
        System.out.println(BeetlTool.lowerFirst("DstInt"));
        System.out.println(BeetlTool.getDataType("date"));
    }

    public static String lowerFirst(String str) {
        if(StringUtils.isNotEmpty(str)){
            return str.substring(0,1).toLowerCase()+str.substring(1,str.length());
        }else
            return str;
    }

    public static String getDataType(String s) {
        if(StringUtils.isNotEmpty(s)){
            s = s.toLowerCase();
            if(s.indexOf("double")>-1){
                return "Double";
            }else if(s.indexOf("bigint")>-1){
                return "Long";
            }else if(s.indexOf("int")>-1){
                return "Integer";
            }else if(s.indexOf("varchar")>-1){
                return "String";
            }else if(s.indexOf("date")>-1){
                return "Date";
            }
            return "String";
        }else{
            return "String";
        }
    }

    public static int getDataLength(String s) {
        Matcher  m = lengthPattern.matcher(s);
        if(m.find()){
            return Integer.parseInt(m.group(1));
        }
        return -1;
    }
}
