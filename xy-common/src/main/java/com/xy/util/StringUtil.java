package com.xy.util;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @desc:
 * @param:
 * @author: xuweidong
 * @date 2016/4/27,15:22
 */
public class StringUtil {

    public static boolean isEmpty(String s){
        return s == null || ("").equals(s.trim());
    }
    public static boolean isNotEmpty(String s){
        return s != null &&! ("").equals(s.trim());
    }

    public static boolean isNumeric(String str){
        Pattern pattern = compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    // date格式转换成long
    public static long getLongTimeByDate(Date dateTime){
        long lSysTime1 = dateTime.getTime() / 1000;
        return lSysTime1;
    }

    // 时间字符串转换成long
    public static long getLongTimeByDate(String dateTime) throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            if(null != dateTime && !"".equals(dateTime)){
                date = df.parse(dateTime);
            }
            else {
                throw new Exception("日期时间不能为空!");
            }

        } catch (ParseException e) {
            throw new Exception(e.toString());
        }
        return date.getTime()/1000;
    }

    public static String getFileName(String userAgent, String fileName) throws UnsupportedEncodingException {
        // IE浏览器
        if (StringUtils.contains(userAgent, "MSIE")) {
            fileName = URLEncoder.encode(fileName, "UTF8");
            // google,火狐浏览器
        } else if (StringUtils.contains(userAgent, "Mozilla")) {
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            // 其他浏览器
        } else {
            fileName = URLEncoder.encode(fileName, "UTF8");
        }
        return fileName;
    }
    public static void main(String[] args) throws IOException {
        Thread t = new Thread(){
          @Override
          public void run(){
              pong();
          }
        };
        t.run();
        t.start();
        System.out.println("ping");

    }
    static void pong(){
        System.out.println("pong");
    }
}
