package com.lenovocw.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {


    HttpServletRequest orgRequest = null;  
  
    public XssHttpServletRequestWrapper(HttpServletRequest request) {  
        super(request);  
        orgRequest = request;  
    }  
  
    /** 
    * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/> 
    * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/> 
    * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖 
    */  
    @Override  
    public String getParameter(String name) {  
        String value = super.getParameter(xssEncode(name));  
        if (value != null) {  
            value = xssEncode(value);  
        }  
        return value;  
    }  
  
    /** 
    * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/> 
    * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> 
    * getHeaderNames 也可能需要覆盖 
    */  
    @Override  
    public String getHeader(String name) {  
  
/*        String value = super.getHeader(xssEncode(name));  
        if (value != null) {  
            value = xssEncode(value);  
        }  */
    	
        String value = super.getHeader(name);  
       if(sqlValidate(value)){
        if (value != null) {  
            value = xssEncode(value);  
        } 
       }
        return value;  
    }  
  
    /** 
    * 将容易引起xss漏洞的半角字符直接替换成全角字符 
    * 
    * @param s 
    * @return 
    */  
    private static String xssEncode(String s) {  
        if (s == null || "".equals(s)) {  
            return s;  
        }  
        StringBuilder sb = new StringBuilder(s.length() + 16);  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
            switch (c) {  
            case '>':  
                sb.append('＞');//全角大于号  
                break;  
            case '<':  
                sb.append('＜');//全角小于号  
                break;  
            case '\'':  
                sb.append('‘');//全角单引号  
                break;  
            case '\"':  
                sb.append('“');//全角双引号  
                break;  
            case '&':  
                sb.append('＆');//全角  
                break;  
            case '\\':  
                sb.append('＼');//全角斜线  
                break;  
            case '#':  
                sb.append('＃');//全角井号  
                break;  
            default:  
                sb.append(c);  
                break;  
            }  
        }  
        return sb.toString();  
    }  
  
    /** 
    * 获取最原始的request 
    * 
    * @return 
    */  
    public HttpServletRequest getOrgRequest() {  
        return orgRequest;  
    }  
  
    /** 
    * 获取最原始的request的静态方法 
    * 
    * @return 
    */  
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {  
        if (req instanceof XssHttpServletRequestWrapper) {  
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();  
        }  
  
        return req;  
    } 
    
  //效验
    protected static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

}
