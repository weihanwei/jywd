package com.lenovocw.utils;

import com.lenovocw.bss.BssInterface;

public class BachHelpFactory {

	/**获取一个新的BachHelp类(非单例)
	 * @param bif	预编译设置接口
	 * @return
	 */
	public static BachHelp getBachHelp(BssInterface bif){
		BachHelp b=new BachHelp();
		b.setBif(bif);
		return b;
	}
	
	/**获取一个新的BachHelp类(非单例)
	 * @param bif	预编译设置接口
	 * @param bufsize 批量插入缓冲大小
	 * @return
	 */
	public static BachHelp getBachHelp(BssInterface bif,int bufsize){
		BachHelp b=new BachHelp();
		b.setBif(bif);
		b.setBachSize(bufsize);
		return b;
	}
	
	/**获取一个新的BachHelp类(非单例)
	 * @return
	 */
	public static BachHelp getBachHelp(){
		BachHelp b=new BachHelp();
		return b;
	}
}
