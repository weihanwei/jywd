package com.lenovocw.utils.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 缓存保存日志文件抽象类
 * @author tanjin
 * @since 2012-04-10
 *
 */
public abstract class AbstractCacheSaveData implements ILogCommon{

	// 线程保存数据库安全同步缓存池
	protected List<TDataLogDTO> cache = Collections.synchronizedList(new LinkedList<TDataLogDTO>());;

	// 线程保存文件安全同步缓存池
	protected List<TDataLogDTO> fileCache = Collections.synchronizedList(new LinkedList<TDataLogDTO>());;
	
	// 缓存数据
	public void put(TDataLogDTO dto) {
		cache.add(dto);
	}
	
	/**
	 * 清除数据库当前缓存
	 */
	public synchronized void clearCache() {
		if (cache.size() > 0) {
			// 存入临时缓存集合
			List<TDataLogDTO> dbList = new ArrayList<TDataLogDTO>();
			dbList.addAll(this.cache);
			// 快速清空原有集合
			this.cache.clear();
			// 保存到日志文件中
			try {
				//保存数据库
				this.saveDB(dbList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 清除日志文件当前缓存
	 */
	public synchronized void clearFileCache() {
		if (fileCache.size() > 0) {
			// 存入临时缓存集合
			List<TDataLogDTO> dbList = new ArrayList<TDataLogDTO>();
			dbList.addAll(this.fileCache);
			// 快速清空原有集合
			this.fileCache.clear();
			// 保存到日志文件中
			try {
				//保存文件
				this.saveFile(dbList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 保存到文件
	 * 
	 * @param dto
	 * @throws IOException
	 */
	public abstract void saveFile(List<TDataLogDTO> obj) throws IOException;
	
	/**
	 * 保存到数据库中
	 * 
	 * @param dto
	 */
	public abstract void saveDB(List<TDataLogDTO> dto);
	
	/**
	 * 保存数据
	 */
	public abstract void save(TDataLogDTO dto);	
	
	/**
	 * 保存文件
	 */
	public abstract void file(TDataLogDTO dto);	
}
