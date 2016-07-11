package com.lenovocw.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;

import com.lenovocw.music.util.LogRecord;

/**
 * <span> <b>功能：</b> </span><br />
 * <span> <!--在这下面一行写功能描述-->
 *
 *</span><br /><br />
 * <span> Copyright LENOVO </span><br /> 
 * <span> Project Name apps-common </span><br /> 
 * <span> Author  ZengZS </span><br /> 
 * <span> Create Time 2012-6-4  下午04:53:13 </span><br /> 
 * <span> App version 1.0.0 </span> <br />
 * <span> JDK version used 6.0 </span><br /> 
 * <span> Modification history none    </span><br /> 
 * <span> Modified by none    </span><br />
 * 
 */
public class HttpUtil{
	public static final int BUFFER_SIZE = 1024 * 16; // stream缓冲大小
	public static final int ARRAY_SIZE = 1024 * 8; // 每次读取缓冲大小
	
	/**
	 * Http发送请求，取得返回值(返回xml格式）
	 * ----------------------------------------------
	 * @return 
	 * <span>  </span><br /> 
	 * <span> Author ZengZS </span><br /> 
	 * <span> Create Time  2012-6-4  下午04:53:51 </span><br /> 
	 *
	 */
	public static String postRequest(String urlstr, String strXml) {
		StringBuffer buffer = null;
		HttpURLConnection c = null;
		try{
			URL url = new URL(urlstr);
			c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("POST");
			c.setRequestProperty("content-type", "text/html");
			c.setRequestProperty("Accept-Charset", "utf-8");
			c.setDoOutput(true);
			c.setDoInput(true);
			c.setConnectTimeout(30000);//设置连接主机超时（单位：毫秒）
			c.setReadTimeout(30000);//设置从主机读取数据超时（单位：毫秒）
			c.connect();
			System.out.println("-------begin--------");
			PrintWriter out = new PrintWriter(c.getOutputStream());// 发送数据
			out.print(strXml);
			out.flush();
			out.close();
			int res = 0;
			res = c.getResponseCode();
			System.out.println("rst status res=" + res);
			if(res == 200){
				InputStream u = c.getInputStream();// 获取servlet返回值，接收
				BufferedReader in = new BufferedReader(new InputStreamReader(u));
				buffer = new StringBuffer();
				String line = "";
				while ((line = in.readLine()) != null){
					buffer.append(line);
				}
				System.out.println(">>" + buffer.toString());
			}else{
				System.out.println("------接收出错了-------");
			}
			c.disconnect();
			System.out.println("-------end--------");
		}catch (Exception e){
			System.out.println("异常！");
			System.out.println(e.toString());
		}
		return buffer.toString();
	}
	
	/**
	 * HTTP通信-POST方式 获取数据，json格式
	 * ----------------------------------------------
	 * @return 
	 * <span>  </span><br /> 
	 * <span> Author Administrator </span><br /> 
	 * <span> Create Time  2013-3-11  上午11:36:47 </span><br /> 
	 *
	 */
	public static ResBean postRequestJson( String queryUrl) {
		ResBean res = new ResBean();
		InputStream is = null;
		HttpURLConnection conn = null;
		//BufferedInputStream bis = null;
		InputStreamReader  bis = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			URL url = new URL(queryUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			//conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			//conn.setRequestMethod("POST");
			//conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
			conn.addRequestProperty("Content-Type","application/json");
			conn.addRequestProperty("Accept","application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(30000);//设置连接主机超时（单位：毫秒）
			conn.setReadTimeout(30000);//设置从主机读取数据超时（单位：毫秒）
			conn.setRequestMethod("POST"); 
			conn.connect();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(queryUrl)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
			
			String result = "";  
	        // bis = new InputStreamReader(conn.getInputStream(),"UTF-8");  
	        int c = 0;  
	        while((c = reader.read()) != -1){  
	            result = result + (char)c;     
	        }  
			
	        res.setData(result);
			res.setLen(out.toByteArray().length);
			
		/*	is = conn.getInputStream();
			res.setStatus(conn.getResponseCode());
			int len = conn.getContentLength();
			if (len > 0)
				out = new ByteArrayOutputStream(len);
			else
				out = new ByteArrayOutputStream(100 * 1024);
			if (res.getStatus() == 200) {
				bis = new BufferedInputStream(is, BUFFER_SIZE);
				int i = -1;
				byte data[] = new byte[ARRAY_SIZE];
				while ((i = bis.read(data)) != -1) {
					out.write(data, 0, i);
				}
				res.setData(new String(out.toByteArray(), "utf-8"));
				res.setLen(out.toByteArray().length);
			} else {
				res.setData("");
				res.setLen(0);
			}	*/	
		}catch (MalformedURLException e) {
			System.out.println("请求 异常：URL协议错误！" + e.getMessage());
			e.printStackTrace();
		
		} catch (ConnectException e) {
			System.out.println("请求 连接超时！" + e.getMessage());
			e.printStackTrace();
		
		} catch (SocketTimeoutException e) {
			System.out.println("请求 连接超时！" + e.getMessage());
			e.printStackTrace();
			
		} catch (IOException e) {
			if (conn != null) {
				try {
					int errorCode = conn.getResponseCode();
					String errorMessage = "请求Webservice异常!服务器返回状态码:";
					switch (errorCode) {
					case 400:
						System.out.println(errorMessage + "400，错误的请求！");
						break;
					case 403:
						System.out.println(errorMessage + "403，服务器拒绝访问！");
						break;
					case 404:
						System.out.println(errorMessage + "404，请求地址不存在！");
						break;
					case 500:
						System.out.println(errorMessage + "500， 服务器内部错误！");
						break;
					case 503:
						System.out.println(errorMessage + "503，服务不可用！");
						break;
					default:
						System.out.println(errorMessage + errorCode);
						break;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("请求接口异常：" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
				if (bis != null)
					bis.close();
				if (out != null)
					out.close();
				if (conn != null)
					conn.disconnect();
				is = null;
				bis = null;
				out = null;
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	
	/**
	 * HTTP通信-POST方式 获取数据，json格式
	 * ----------------------------------------------
	 * @return 
	 * <span>  </span><br /> 
	 * <span> Author Administrator </span><br /> 
	 * <span> Create Time  2013-3-11  上午11:36:47 </span><br /> 
	 *
	 */
	public static ResBean postRequestJsonForLog( String queryUrl,String msg) {
		ResBean res = new ResBean();
		InputStream is = null;
		HttpURLConnection conn = null;
		//BufferedInputStream bis = null;
		InputStreamReader  bis = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			LogRecord.info("请求"+msg+"开始"+DateUtil.getNowStrDate("yyyy-MM-dd HH:mm:ss"));
			URL url = new URL(queryUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			//conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			//conn.setRequestMethod("POST");
			//conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
			conn.addRequestProperty("Content-Type","application/json");
			conn.addRequestProperty("Accept","application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(30000);//设置连接主机超时（单位：毫秒）
			conn.setReadTimeout(30000);//设置从主机读取数据超时（单位：毫秒）
			conn.setRequestMethod("POST"); 
			conn.connect();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(queryUrl)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
			
			String result = "";  
	        // bis = new InputStreamReader(conn.getInputStream(),"UTF-8");  
	        int c = 0;  
	        while((c = reader.read()) != -1){  
	            result = result + (char)c;     
	        }  
			
	        res.setData(result);
			res.setLen(out.toByteArray().length);
			
		/*	is = conn.getInputStream();
			res.setStatus(conn.getResponseCode());
			int len = conn.getContentLength();
			if (len > 0)
				out = new ByteArrayOutputStream(len);
			else
				out = new ByteArrayOutputStream(100 * 1024);
			if (res.getStatus() == 200) {
				bis = new BufferedInputStream(is, BUFFER_SIZE);
				int i = -1;
				byte data[] = new byte[ARRAY_SIZE];
				while ((i = bis.read(data)) != -1) {
					out.write(data, 0, i);
				}
				res.setData(new String(out.toByteArray(), "utf-8"));
				res.setLen(out.toByteArray().length);
			} else {
				res.setData("");
				res.setLen(0);
			}	*/	
			
			LogRecord.info("请求"+msg+"成功"+DateUtil.getNowStrDate("yyyy-MM-dd HH:mm:ss"));
		}catch (MalformedURLException e) {
			System.out.println("请求 异常：URL协议错误！" + e.getMessage());
			LogRecord.error("请求"+msg+" 异常：URL协议错误！" + e.getMessage());
			e.printStackTrace();
		
		} catch (ConnectException e) {
			System.out.println("请求 连接超时！" + e.getMessage());
			LogRecord.error("请求 "+msg+" 连接超时!" + e.getMessage());
			e.printStackTrace();
		
		} catch (SocketTimeoutException e) {
			LogRecord.error("请求 "+msg+" 连接超时!" + e.getMessage());
			System.out.println("请求 连接超时！" + e.getMessage());
			e.printStackTrace();
			
		} catch (IOException e) {
			if (conn != null) {
				try {
					int errorCode = conn.getResponseCode();
					String errorMessage = "请求"+msg+"的Webservice异常!服务器返回状态码:";
					switch (errorCode) {
					case 400:
						LogRecord.error(errorMessage + "400，错误的请求！");
						System.out.println(errorMessage + "400，错误的请求！");
						break;
					case 403:
						LogRecord.error(errorMessage + "403，服务器拒绝访问！");
						System.out.println(errorMessage + "403，服务器拒绝访问！");
						break;
					case 404:
						LogRecord.error(errorMessage + "404，请求地址不存在！");
						System.out.println(errorMessage + "404，请求地址不存在！");
						break;
					case 500:
						LogRecord.error(errorMessage + "500， 服务器内部错误！");
						System.out.println(errorMessage + "500， 服务器内部错误！");
						break;
					case 503:
						LogRecord.error(errorMessage + "503，服务不可用！");
						System.out.println(errorMessage + "503，服务不可用！");
						break;
					default:
						LogRecord.error(errorMessage + errorCode);
						System.out.println(errorMessage + errorCode);
						break;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			LogRecord.error(msg +"接口异常"+ e.getMessage());
			System.out.println("请求接口异常：" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
				if (bis != null)
					bis.close();
				if (out != null)
					out.close();
				if (conn != null)
					conn.disconnect();
				is = null;
				bis = null;
				out = null;
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	/**
	 * HTTP通信-POST方式 获取数据，json格式设置代理
	 * ----------------------------------------------
	 * @return 
	 * <span>  </span><br /> 
	 * <span> Author Administrator </span><br /> 
	 * <span> Create Time  2013-3-11  上午11:36:47 </span><br /> 
	 *
	 */
	public static ResBean postRequestJsonBydl( String queryUrl) {
		ResBean res = new ResBean();
		InputStream is = null;
		HttpURLConnection conn = null;
		//BufferedInputStream bis = null;
		InputStreamReader  bis = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			URL url = new URL(queryUrl);
		   InetSocketAddress addr = new InetSocketAddress("cmproxy.gmcc.net",8081);   //创建代理服务器
		   Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
			conn = (HttpURLConnection) url.openConnection(proxy);
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			//conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			//conn.setRequestMethod("POST");
			//conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
			conn.addRequestProperty("Content-Type","application/json");
			conn.addRequestProperty("Accept","application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(30000);//设置连接主机超时（单位：毫秒）
			conn.setReadTimeout(30000);//设置从主机读取数据超时（单位：毫秒）
			conn.setRequestMethod("POST"); 
			conn.connect();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(queryUrl)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
			
			String result = "";  
	        // bis = new InputStreamReader(conn.getInputStream(),"UTF-8");  
	        int c = 0;  
	        while((c = reader.read()) != -1){  
	            result = result + (char)c;     
	        }  
			
	        res.setData(result);
			res.setLen(out.toByteArray().length);
			
		/*	is = conn.getInputStream();
			res.setStatus(conn.getResponseCode());
			int len = conn.getContentLength();
			if (len > 0)
				out = new ByteArrayOutputStream(len);
			else
				out = new ByteArrayOutputStream(100 * 1024);
			if (res.getStatus() == 200) {
				bis = new BufferedInputStream(is, BUFFER_SIZE);
				int i = -1;
				byte data[] = new byte[ARRAY_SIZE];
				while ((i = bis.read(data)) != -1) {
					out.write(data, 0, i);
				}
				res.setData(new String(out.toByteArray(), "utf-8"));
				res.setLen(out.toByteArray().length);
			} else {
				res.setData("");
				res.setLen(0);
			}	*/	
		}catch (MalformedURLException e) {
			System.out.println("请求 异常：URL协议错误！" + e.getMessage());
			e.printStackTrace();
		
		} catch (ConnectException e) {
			System.out.println("请求 连接超时！" + e.getMessage());
			e.printStackTrace();
		
		} catch (SocketTimeoutException e) {
			System.out.println("请求 连接超时！" + e.getMessage());
			e.printStackTrace();
			
		} catch (IOException e) {
			if (conn != null) {
				try {
					int errorCode = conn.getResponseCode();
					String errorMessage = "请求Webservice异常!服务器返回状态码:";
					switch (errorCode) {
					case 400:
						System.out.println(errorMessage + "400，错误的请求！");
						break;
					case 403:
						System.out.println(errorMessage + "403，服务器拒绝访问！");
						break;
					case 404:
						System.out.println(errorMessage + "404，请求地址不存在！");
						break;
					case 500:
						System.out.println(errorMessage + "500， 服务器内部错误！");
						break;
					case 503:
						System.out.println(errorMessage + "503，服务不可用！");
						break;
					default:
						System.out.println(errorMessage + errorCode);
						break;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("请求接口异常：" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
				if (bis != null)
					bis.close();
				if (out != null)
					out.close();
				if (conn != null)
					conn.disconnect();
				is = null;
				bis = null;
				out = null;
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	//---------------------------------------------------------------------------------------
	/**
	 * HTTP通信-POST方式 获取数据，json格式
	 * ----------------------------------------------
	 * @return 
	 * <span>  </span><br /> 
	 * <span> Author Administrator </span><br /> 
	 * <span> Create Time  2013-3-11  上午11:36:47 </span><br /> 
	 *
	 */
	public static ResBean getRequestJson( String queryUrl) {
		ResBean res = new ResBean();
		InputStream is = null;
		HttpURLConnection conn = null;
		//BufferedInputStream bis = null;
		InputStreamReader  bis = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			URL url = new URL(queryUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			//conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			//conn.setRequestMethod("POST");
			//conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
			conn.addRequestProperty("Content-Type","application/json");
			conn.addRequestProperty("Accept","application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(30000);//设置连接主机超时（单位：毫秒）
			conn.setReadTimeout(30000);//设置从主机读取数据超时（单位：毫秒）
			conn.setRequestMethod("GET"); 
			conn.connect();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(queryUrl)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
			
			String result = "";  
	        // bis = new InputStreamReader(conn.getInputStream(),"UTF-8");  
	        int c = 0;  
	        while((c = reader.read()) != -1){  
	            result = result + (char)c;     
	        }  
			
	        res.setData(result);
			res.setLen(out.toByteArray().length);
			
		/*	is = conn.getInputStream();
			res.setStatus(conn.getResponseCode());
			int len = conn.getContentLength();
			if (len > 0)
				out = new ByteArrayOutputStream(len);
			else
				out = new ByteArrayOutputStream(100 * 1024);
			if (res.getStatus() == 200) {
				bis = new BufferedInputStream(is, BUFFER_SIZE);
				int i = -1;
				byte data[] = new byte[ARRAY_SIZE];
				while ((i = bis.read(data)) != -1) {
					out.write(data, 0, i);
				}
				res.setData(new String(out.toByteArray(), "utf-8"));
				res.setLen(out.toByteArray().length);
			} else {
				res.setData("");
				res.setLen(0);
			}	*/	
		}catch (MalformedURLException e) {
			System.out.println("请求 异常：URL协议错误！" + e.getMessage());
			e.printStackTrace();
		
		} catch (ConnectException e) {
			System.out.println("请求 连接超时！" + e.getMessage());
			e.printStackTrace();
		
		} catch (SocketTimeoutException e) {
			System.out.println("请求 连接超时！" + e.getMessage());
			e.printStackTrace();
			
		} catch (IOException e) {
			if (conn != null) {
				try {
					int errorCode = conn.getResponseCode();
					String errorMessage = "请求Webservice异常!服务器返回状态码:";
					switch (errorCode) {
					case 400:
						System.out.println(errorMessage + "400，错误的请求！");
						break;
					case 403:
						System.out.println(errorMessage + "403，服务器拒绝访问！");
						break;
					case 404:
						System.out.println(errorMessage + "404，请求地址不存在！");
						break;
					case 500:
						System.out.println(errorMessage + "500， 服务器内部错误！");
						break;
					case 503:
						System.out.println(errorMessage + "503，服务不可用！");
						break;
					default:
						System.out.println(errorMessage + errorCode);
						break;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("请求接口异常：" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
				if (bis != null)
					bis.close();
				if (out != null)
					out.close();
				if (conn != null)
					conn.disconnect();
				is = null;
				bis = null;
				out = null;
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}
