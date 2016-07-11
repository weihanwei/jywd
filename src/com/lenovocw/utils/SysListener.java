/** configuration in web.xml get rootpath
 *  <listener>
    	<listener-class>
    		com.eemedia.web.listener.SysListener
    	</listener-class>
  	</listener>
 */
package com.lenovocw.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SysListener extends HttpServlet implements ServletContextListener {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(SysListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		/*
		Const.CONTEXTPATH=sce.getServletContext().getContextPath();
		sce.getServletContext().setAttribute("contextPath",Const.CONTEXTPATH);
		String rootpath = sce.getServletContext().getRealPath("/");
		if (rootpath != null) {
			rootpath = rootpath.replaceAll("\\\\", "/");
		} else {
			rootpath = "/";
		}
		if (!rootpath.endsWith("/")) {
			rootpath = rootpath + "/";
		}
		Const.ROOTPATH = rootpath;
		logger.info("Application Run Path:" + rootpath);
		try {
			File f=new File(Const.ROOTPATH+"/picture");
			if(!f.exists())
				f.mkdirs();
			f=new File(Const.ROOTPATH+"/res");
			if(!f.exists())
				f.mkdirs();
		} catch (Exception e) {

		}
		
		try {
			FileInputStream fr = new FileInputStream(Const.ROOTPATH + "software/version.txt");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte []buf = new byte[512];
			int n = -1;
			while((n = fr.read(buf)) > 0)
			{
				bos.write(buf, 0, n);
			}
			String data = new String(bos.toByteArray());
			Const.CLIENTVERSION = data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Application Client Version:" + Const.CLIENTVERSION);
	}
*/
	}
	public void contextDestroyed(ServletContextEvent sce) {	}
	
}
