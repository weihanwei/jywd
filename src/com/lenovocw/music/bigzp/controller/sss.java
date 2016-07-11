package com.lenovocw.music.bigzp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class sss {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar c = Calendar.getInstance();  
//        c.setTime(new Date());  
//        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));  
//        String stime=df.format(c.getTime());
//		System.out.println(stime);
//	    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH)); 
//	    String etime=df.format(c.getTime());
//	  	System.out.println(etime);
		 int[] x = {1,2,3,4,5,6,7,8,9};    
	        List list = new ArrayList();    
	        for(int i = 0;i < x.length;i++){    
	            System.out.print(x[i]+", ");    
	            list.add(x[i]);    
	        }    
	        System.out.println();    
	            
	        Collections.shuffle(list);
	        Random random=new Random();
	        int result=random.nextInt(99);
	        System.out.println(result);
	       System.out.println(list.get(0));
	}

}
