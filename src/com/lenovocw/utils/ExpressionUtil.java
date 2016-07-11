package com.lenovocw.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公式工具类
 * 
 * @author jamlin
 * @version Mar 30, 2011
 */
public class ExpressionUtil {

	private static final Pattern regexMultiAndDivision = Pattern
			.compile("[\\d\\.]+(\\*|\\/)[\\d\\.]+");
	private static final Pattern regexAdditionAndSubtraction = Pattern
			.compile("[\\d\\.]+(\\+|\\-)[\\d\\.]+");
	private static final Pattern regexPow = Pattern
			.compile("[\\d\\.]+(\\^)[\\d\\.]+");

	@SuppressWarnings("unchecked")
	public static Number invoke(String expression, Map<String, ?> ctx) {
		if (ctx != null) {
			for (String key : ctx.keySet()) {
				expression = expression
						.replaceAll(key, ctx.get(key).toString());
			}
		}
		String result = computeExpression(expression);
		return (Number) Double.valueOf(result);
	}

	/**
	 * 执行加减法
	 * 
	 * @param string
	 * @return
	 */
	public static String doAdditionAndSubtraction(String string) {
		float d1 = 0f;
		float d2 = 0f;
		String[] temp = null;
		String value = "";
		if (string.indexOf("+") > 0) {
			temp = string.split("\\+");
		} else {
			temp = string.split("-");
		}
		if (temp.length < 2) {
			return string;
		}
		d1 = Float.valueOf(temp[0]);
		d2 = Float.valueOf(temp[1]);
		if (string.indexOf("+") > 0) {
			value = String.valueOf(d1 + d2);
		} else {
			value = String.valueOf(d1 - d2);
		}
		return value;
	}

	/**
	 * 执行乘除法
	 * 
	 * @param string
	 * @return
	 */
	private static String doMultiAndDivision(String string) {
		String value = "";
		float d1 = 0f;
		float d2 = 0f;
		String[] temp = null;
		if (string.indexOf("*") > 0) {
			temp = string.split("\\*");
		} else {
			temp = string.split("/");
		}

		if (temp.length < 2)
			return string;

		d1 = Float.valueOf(temp[0]);
		d2 = Float.valueOf(temp[1]);
		if (string.indexOf("*") > 0) {
			value = String.valueOf(d1 * d2);
		} else {
			value = String.valueOf(d1 / d2);
		}
		return value;
	}

	/**
	 * 执行平方
	 * 
	 * @param string
	 * @return
	 */
	private static String doPow(String string) {
		String value = "";
		float d1 = 0f;
		float d2 = 0f;
		String[] temp = null;
		if (string.indexOf("^") > 0) {
			temp = string.split("\\^");
		}
		if (temp.length < 2)
			return string;

		d1 = Float.valueOf(temp[0]);
		d2 = Float.valueOf(temp[1]);
		if (string.indexOf("^") > 0) {
			value = String.valueOf(Math.pow(d1, d2));
		}
		return value;
	}

	/**
	 * 计算不包含括号的表达式
	 * 
	 * @param string
	 * @return
	 */
	private static String computeStirngNoBracket(String string) {
		Pattern replace = Pattern.compile("(^\\()|(\\)$)");
		while (replace.matcher(string).find()) {
			string = string.replaceAll("(^\\()|(\\)$)", "");
		}
		String temp = "";
		int index = -1;
		Matcher matcher = null;
		// 解析平方
		while ((matcher = regexPow.matcher(string)).find()) {
			temp = matcher.group(0);
			index = string.indexOf(temp);
			string = string.substring(0, index) + doPow(temp)
					+ string.substring(index + temp.length());
		}
		// 解析乘除法
		while ((matcher = regexMultiAndDivision.matcher(string)).find()) {
			temp = matcher.group(0);
			index = string.indexOf(temp);
			string = string.substring(0, index) + doMultiAndDivision(temp)
					+ string.substring(index + temp.length());
		}
		// 解析加减法
		while ((matcher = regexAdditionAndSubtraction.matcher(string)).find()) {
			temp = matcher.group(0);
			index = string.indexOf(temp);
			string = string.substring(0, index)
					+ doAdditionAndSubtraction(temp)
					+ string.substring(index + temp.length());
		}
		return string;
	}

	/**
	 * 计算字符串四则运算表达式
	 * 
	 * @param string
	 * @return
	 */
	public static String computeExpression(String string) {
		string = string.trim();
		Pattern regexCheck = Pattern.compile("[\\(\\)\\d\\+\\-\\*/\\.]*"); // 是否是合法的表达式
		if (!regexCheck.matcher(string).find()) {
			string = null;
		}
		String temp = "";
		int index = -1;
		Pattern regex = Pattern.compile("\\([\\d\\.\\+\\-\\^\\*/]+\\)"); // 提取括号表达式
		Pattern bla = Pattern.compile("\\s");
		while (bla.matcher(string).find()) {
			string = string.replace("\\s", ""); // 去除空格
		}
		Matcher matcher = null;
		// 循环计算所有括号里的表达式
		while ((matcher = regex.matcher(string)).find()) {
			temp = matcher.group(0);
			index = string.indexOf(temp);
			string = string.substring(0, index) + computeStirngNoBracket(temp)
					+ string.substring(index + temp.length());
		}

		// 最后计算总的表达式结果
		string = computeStirngNoBracket(string);
		return string;
	}
	
	public static void main(String[] args) {
		String ex = "a*b*c+(d/a*b)";
		Map<String,Integer> es = new HashMap<String,Integer>();
		es.put("a",1);
		es.put("b",1);
		es.put("c",1);
		es.put("d",1);
		System.out.println(invoke(ex, es));
	}

}
