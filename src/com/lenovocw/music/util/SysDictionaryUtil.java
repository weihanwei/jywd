package com.lenovocw.music.util;

import java.util.*;

import javax.annotation.Resource;

import com.lenovocw.music.dao.JdbcDao;

public class SysDictionaryUtil {

    

	public static Map<String,Object> getLevelParam(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("VC0000", "普通");
		map.put("VC1000", "钻石卡");
		map.put("VC1100", "金卡");
		map.put("VC1200", "银卡");
		map.put("VC1300", "贵宾卡");
		map.put("VC1400", "积分卡");
		map.put("VC1500", "取消白金卡");
		map.put("VC1600", "金卡1");
		map.put("VC1700", "银卡1");
		map.put("VC1800", "钻卡1");
		map.put("VC2900", "优先接入");
		map.put("PreGroup", "潜在集团");
		map.put("RivalGroup", "竞争对手集团");
		map.put("GROUP", "在网集团");
		map.put("VC1900 ", "钻石卡1");
		return map;

	}
    public static void main(String[] arg){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("w", "e");
    	System.out.println(map.get("f"));
    }
    
	public static Map<String,Object> getUserStatusParam(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("US281", "套卡开通");
		map.put("US99", "无效用户");
		map.put("US29", "已携号转地市 ");
		map.put("US41", "沉默期用户状态");
		map.put("US532", "贵宾卡");
		map.put("US28", "临时生成资料 ");
		map.put("US40", "申请报停");
		map.put("US26", "回退（注销）");
		map.put("US23", "欠费销户");
		map.put("US27", "已转换品牌");
		map.put("US528", "销户订单处理中");
		map.put("US24", "强制退网");
		map.put("US531", "集团预约销户");
		map.put("US346", "欠费呼出限制");
		map.put("US344", "欠费停用");
		map.put("US347", "欠费长途限制");
		map.put("US348", "欠费国际长途限制");
		map.put("US30", " 停机");
		map.put("US10", "正使用");
		map.put("US22", "预约销户");
		map.put("US302", "全停");
		map.put("US301", "半停");
		map.put("US21", "有效期到期销户");
		return map;

	}
	
	public static Map<String,Object> getChangeFamilyInfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("0", "成功");
		map.put("1", "已是家庭成员");
		map.put("2", "已是它网家庭成员");
		map.put("4", "输入的号码有误");
		map.put("5", "变更类型不正确");
		map.put("6", "外网成员已超过限制");
		
		map.put("7", "协议期未过，取消失败");
		map.put("8", "未申请此业务");
		map.put("9", "非家庭网主号");
		map.put("10", "非全球通用户");
		map.put("11", "成功，优惠未生效");
		map.put("12", "成员加入成功，短号已被使用");
		map.put("13", "主号的短号不能修改");
		
		map.put("14", "短号变更失败，短号已被使用");
		map.put("15", "副号短号必须是552～559");
		map.put("16", "异地移动手机号码不能加入本地家庭短号网");
		map.put("17", "付费类型不正确");
		map.put("18", "副号当月使用过欢乐在线业务，不允许受理");
		map.put("19", "短号群聊网加入总成员数量(数字)|成员已达到上限（包括下月失效的成员）");
		map.put("20", "主号为无效号码（号码状态不为在用）");
		map.put("21", "副号为无效号码（号码状态不为在用）");
		map.put("22", "该短号群聊网不是个付版（商务版）不适用短信二次确认方式");
		map.put("23", "允许短号群聊网加入异地成员数量(数字)|异地号码达到上限");
		map.put("24", "旧资费不允许新增成员，请升级到新资费后再办理");
		map.put("25", "号变更失败，副号还未生效（下月生效），不能修改副号短号");
		map.put("26", "成员号码[%s]申请了捆绑业务，捆绑期内不能退出家庭短号网");
		map.put("27", "成员号码短号已经预占，重复预占失败");
		map.put("1098", "此号码办理了携号转品牌业务还未生效,不能办理此业务]");
		map.put("1100", "全球通实时上传月末办理限制");
		map.put("111", "其它原因");
		map.put("28", "用户号码不能办理此业务!");
		map.put("29", "[主体产品名称]不能作为短号群聊网的副号或者[主体产品名称]用户不允许加入欢乐在线！.");
		map.put("30", "用户已经办理过家庭服务计划，不允许办理欢乐在线");
		map.put("31", "该号码已加入相同集团类型产品网,不可再加入.");
		map.put("32", "号码对应的用户不存在");
		
		return map;
		
	}
}
