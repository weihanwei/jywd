package com.lenovocw.utils.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lenovocw.music.sql.DataLogSqlSet;
import com.lenovocw.utils.DataBaseSqlTools;
import com.lenovocw.utils.SpringGetBeanUtil;

/**
 * 缓存保存日志文件实现类，提供单例模式
 * @author tanjin
 * @since 2012-04-10
 *
 */
public class CacheSaveData extends AbstractCacheSaveData {
	public static final Logger log = LoggerFactory.getLogger(CacheSaveData.class);
	private static final LogConfig config = new LogConfig();
	
	private static CacheSaveData cacheSaveData = null;
	
	
	private CacheSaveData(){
		
	}
	
	public static CacheSaveData getInstance(){
		if(cacheSaveData == null){
			cacheSaveData = new CacheSaveData();
		}
		return cacheSaveData;
	}
	
	/**
	 * 保存数据库
	 * @param obj
	 */
	public void saveDB(List<TDataLogDTO> list) {
		for(TDataLogDTO dto : list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("USER_NUMBER", dto.getImsidn());
			map.put("USER_ID", dto.getUserId());
			map.put("USER_IP", dto.getUserIp());
			map.put("OPERATION_DATE", dto.getOperationDate());
			map.put("BUSINESS_NAME", dto.getBusinessName());
			map.put("BUSINESS_ID", dto.getBusinessId());
			map.put("APPLICATION_SOURCE", dto.getApplicationSource());
			map.put("RESULT",dto.getResult());
			map.put("DESC", dto.getDesc());
			
			String saveSql = DataBaseSqlTools.constractSql(DataLogSqlSet.INSERT_DATA_SQL,map);
			SpringGetBeanUtil.executeBatchInTransactional(new String []{saveSql});
		}
	}

	/**
	 * 保存日志文件
	 * @param obj
	 */
	public void saveFile(List<TDataLogDTO> list) throws IOException {
		File file = new File(config.getFileName() + "data_" + getHour() + ".txt");
		if(!file.exists()){
			file.createNewFile();
		}
		BufferedWriter output = new BufferedWriter(new FileWriter(file, config.isFileAppend()));
		try {
			for(TDataLogDTO dto : list){
				Object[] obj = new Object[10];
				obj[0] = dto.getImsidn();
				obj[1] = dto.getUserId();
				obj[2] = dto.getUserIp();
				obj[3] = dto.getOperationDate();
				obj[4] = dto.getBusinessName();
				obj[5] = dto.getBusinessId();
				obj[6] = dto.getApplicationSource();
				obj[7] = dto.getResult();
				obj[8] = dto.getDesc();
				output.write(MessageFormat.format(config.getSaveTemple(), obj));
				output.newLine();
			}
		} finally {
			output.close();
		}
	}
	
	/**
	 * 保存数据
	 * @param obj
	 */
	public synchronized void save(TDataLogDTO dto) {
		// TODO Auto-generated method stub
		log.info("缓存保存数据库数量:"+cache.size());
		log.info("当前缓存保存数据库容量大小 ：" + config.getClearCount() + " *************************** ");
		if(cache.size()>=config.getClearCount()){
			super.clearCache();
		}
		cache.add(dto);
	}
	
	/**
	 * 保存文件
	 * @param obj
	 */
	public synchronized void file(TDataLogDTO dto) {
		// TODO Auto-generated method stub
		log.info("缓存记录文件数量:"+fileCache.size());
		log.info("当前缓存记录文件容量大小 ：" + config.getClearCount() + " *************************** ");
		if(fileCache.size()>=config.getClearCount()){
			super.clearFileCache();
		}
		fileCache.add(dto);
	}
	
	/**
	 * 获取当前小时
	 * @return
	 */
	private String getHour(){
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		return String.valueOf(hour);
	}
	
}
