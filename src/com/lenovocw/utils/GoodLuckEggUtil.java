package com.lenovocw.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 幸运砸蛋工具类
 * @author tanjin
 * @since 2012-05-08
 *
 */
public class GoodLuckEggUtil {
	private static final Logger logger = LoggerFactory.getLogger(GoodLuckEggUtil.class);
	/**
	 * 获取用户中奖的产品
	 * @param list
	 * @param chanceSum 产品概率总和
	 * @return
	 */
	public static Map<String, Object> getRandomProduct(List<Map<String, Object>> currentList , int chanceSum ){
		Map<String, Object> newMap = new HashMap<String, Object>();
		//根据概率总和获取1个产品随机数，并判断随机数在那个概率中
		int arrayRandom = getRandom(chanceSum);
		
		//保存上一次的概率
		int lastChance = 0;
		for(Map<String, Object> map : currentList){
			int rate_value = Integer.parseInt(String.valueOf(map.get("rate_value")));
			if(arrayRandom >= lastChance && arrayRandom <= rate_value + lastChance -1){
				int product_num = (Integer)map.get("product_num");
				
				//判断该产品中奖有效期和中奖个数
				if(product_num > 0){
					map.put("isWinning", 1);
				}else{
					getRandomProduct(currentList,chanceSum);
				}
				newMap = map;
			}
			lastChance += rate_value;
		}
		return newMap;
	}
	
	/**
	 * 随机选中一个中奖产品
	 * @param currentList
	 * @return 
	 *
	 */
	public static Map<String, Object> getRandomProduct(List<Map<String, Object>> currentList){
		logger.info("获取共有多少产品 ： " + currentList.size());
		Map<String, Object> newMap = new HashMap<String, Object>();
		//根据概率总和获取1个产品随机数，并判断随机数在那个概率中
		Integer[] ratios = new Integer[currentList.size()];
		
		for(int i=0;i<currentList.size();i++){
			Map<String, Object> map = currentList.get(i);
			logger.info(map.get("name") + "的倍率为：" + map.get("rate_value"));
			ratios[i] = Integer.parseInt(String.valueOf(map.get("rate_value")));
		}
		try {
			//获取中奖产品的存入数组中的下标值
			int index_of = getRandomChoiceWithRatioArr(ratios);
			logger.info("随机获取产品概率数组中的下标值为：" + index_of);
			//根据获取到的下标，从产品集合里面获取产品
			newMap = currentList.get(index_of);
			int product_num = (Integer)newMap.get("product_num");
			if(product_num > 0){
				newMap.put("isWinning", 1);
			}else{
				logger.info("获取到的产品中数量为0，递归重新选择产品.....");
				getRandomProduct(currentList);
			}
		} catch (Exception e) {
			logger.info("没有随机到产品，重新获取到产品....");
			//如果抛出异常，则递归再次选择产品
			getRandomProduct(currentList);
		}
		return newMap;
	}
	
	/**
	 * 根据产品的概率随机抽取2个前台显示的产品
	 * @param currnetList
	 * @param id
	 * @return  
	 *
	 */
	public static List<Map<String, Object>> getShowProduct(List<Map<String, Object>> currentList,String id){
		logger.info("根据概率随机获取前台显示的2个产品");
		List<Map<String, Object>> showList = new ArrayList<Map<String,Object>>();
		//读取配置文件中前台显示产品的概率值，顺序须与currentList中的产品位置一致
		String eggRateValue = ServiceConfig.getValue("egg_rate_value");
		String[] rate = eggRateValue.split(",");
		
		Integer[] ratios = new Integer[rate.length];
		for(int i=0;i<rate.length;i++){
			ratios[i] = Integer.parseInt(rate[i]);
		}
		String product_name = "";
		//循环2次获取2个产品
		for(int i=0;i<2;i++){
			try {
				Map<String, Object> newMap = new HashMap<String, Object>();
				//获取中奖产品的存入数组中的下标值
				int index_of = getRandomChoiceWithRatioArr(ratios);
				//根据获取到的下标，从产品集合里面获取产品
				newMap = currentList.get(index_of);
				
				String newId = String.valueOf(newMap.get("id"));
				String name = String.valueOf(newMap.get("name"));
				
				newMap = recursionMap(newId,id,name,product_name,index_of,ratios,newMap,currentList);
				
				product_name = String.valueOf(newMap.get("name"));
				logger.info("前台随机显示的产品：" + product_name);
				showList.add(newMap);
			} catch (Exception e) {
				//如果抛出异常，则递归再次选择产品
				getShowProduct(currentList,id);
			}
		}
		
		return showList;
	}
	
	/**
	 * 根据条件选择产品
	 * @param newId 新产品ID
	 * @param id 产品ID
	 * @param name 新产品名称
	 * @param product 产品名称
	 * @param index_of 获取产品下标
	 * @param ratios 产品概率集合
	 * @param newMap 获取的产品
	 * @param currentList 当前所有产品的集合
	 * 
	 * @return 
	 *
	 */
	private static Map<String, Object> recursionMap(String newId,String id,String name,String product_name,int index_of,Integer[] ratios,
					Map<String, Object> newMap,List<Map<String, Object>> currentList) throws Exception{
		
		while(true){
			if(newId.equals(id)){
				logger.info("获取的产品ID与中奖的产品ID相同，再次获取");
				index_of = getRandomChoiceWithRatioArr(ratios);
				newMap = currentList.get(index_of);
				name = String.valueOf(newMap.get("name"));
				logger.info("再次获取的产品名：" + name);
				newId = String.valueOf(newMap.get("id"));
			}else{
				name = String.valueOf(newMap.get("name"));
				//如果产品名称相同，则再次获取
				if(name.equals(product_name)){
					logger.info("获取的产品名称相同，再次获取");
					index_of = getRandomChoiceWithRatioArr(ratios);
					newMap = currentList.get(index_of);
				}else{
					return newMap;
				}
			}
		}
	}
	
	private static int getRandomChoiceWithRatioArr(Integer[] ratios) throws Exception {
		int sum = 0;
		for (int i : ratios) {
			sum += i;
		}
		
		int r = getRandomNumber(1, sum);
		int total = 0;
		for (int i = 0; i < ratios.length; i++) {
			total += ratios[i];
			if (r <= total ){
				return i;
			}
		}
		throw new Exception("It can't be here!");
	}
	
	private static int getRandomNumber(int min, int max) {
		int result =  getRandom().nextInt((max - min + 1) << 5);
		return (result >> 5) + min;
	}
	
	private static Random getRandom() {
		return randoms.get();
	}
	
	private static ThreadLocal<Random> randoms = new ThreadLocal<Random>(){
		  @Override protected Random initialValue() {
			  return new Random();
		  }
	};
	/**
	 * 获取随机整数
	 * @param chanceSum
	 * @return
	 */
	private static int getRandom(int chanceSum){
		Random r = new Random();
		return r.nextInt(chanceSum);
	}
}
