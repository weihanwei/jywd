package com.lenovocw.utils;

import java.util.List;
import java.util.Map;

import com.lenovocw.music.dao.JdbcDao;

/**
 * bet19开奖结果分析
 * @author tanjin
 * @since 2012-04-29
 *
 */
public class AnalysisBet19Util {
	
	/**
	 * 创建bet19分析图的二维数组
	 * @return
	 */
	public static int[][] createBet19Analy(){
		JdbcDao dao = SpringGetBeanUtil.getDao();
		String sql = "SELECT result,betversion FROM tb_u_bet19list b WHERE isover=1 ORDER BY betversion DESC LIMIT 0,10";
		List<Map<String, Object>> list = dao.queryForListMap(sql);
		//定义一个2维数组，19行，11列,18个case语句选择，如果为1，则写的2维数组格式就是[1][1]=1 [1][2]=0 
		int[][] str = new int[20][11];
		for(int i=1;i<20;i++){
			str[i][0] = i-1 ;
		}
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			str[0][i+1] = Integer.parseInt(String.valueOf(map.get("betversion")));
			//选择语句开始
			switchNumber(Integer.parseInt(map.get("result") + ""),str,i);
		}
		return str;
	}
	/**
	 * 根据号码放在2维数组的行里面，其它列则为空
	 * @param result
	 * @param str
	 * @param i
	 * @return
	 */
	public static int[][] switchNumber(int result,int[][] str,int i){
		switch (result) {
			case 0:
				str[result+1][i+1] = result;
				break;
			case 1:
				str[result+1][i+1] = result;
				break;
			case 2:
				str[result+1][i+1] = result;
				break;
			case 3:
				str[result+1][i+1] = result;
				break;
			case 4:
				str[result+1][i+1] = result;
				break;
			case 5:
				str[result+1][i+1] = result;
				break;
			case 6:
				str[result+1][i+1] = result;
				break;
			case 7:
				str[result+1][i+1] = result;
				break;
			case 8:
				str[result+1][i+1] = result;
				break;
			case 9:
				str[result+1][i+1] = result;
				break;
			case 10:
				str[result+1][i+1] = result;
				break;
			case 11:
				str[result+1][i+1] = result;
				break;
			case 12:
				str[result+1][i+1] = result;
				break;
			case 13:
				str[result+1][i+1] = result;
				break;
			case 14:
				str[result+1][i+1] = result;
				break;
			case 15:
				str[result+1][i+1] = result;
				break;
			case 16:
				str[result+1][i+1] = result;
				break;
			case 17:
				str[result+1][i+1] = result;
				break;
			case 18:
				str[result+1][i+1] = result;
				break;
			default:
				break;
		}
		
		return str;
	}
}
