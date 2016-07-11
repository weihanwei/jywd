package com.lenovocw.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 制作人 林晓辉
 * 制作时间  2009.4.7
 * 分页操作 实现类
 * @param <T>
 * ***/
public class Pager<T> {
	
	private int curPage=1;
	//每页显示数据行数
	private int pageRows=10;
	private Integer totalPages;
	private int totalRows;
	private List<T> data;
	private Map<Object,Object> params;
	private String url;
	
	public Pager() {
	}

	public int getCurPage() {
		return this.curPage;
	}

	public void setCurPage(int curPage) {
		if(curPage<1)
			curPage=1;
		this.curPage = curPage;
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	public int getTotalPages() {
		if(totalPages==null||totalPages<0)
			totalPages = Math.max(1,(totalRows + pageRows - 1) / pageRows);
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages=totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}


	public int getFirstPage() {
		return 1;
	}
	public int getLastPage() {
		return this.getTotalPages();
	}
	public int getNextPage() {
		return Math.min(curPage+1,getTotalPages());
	}
	public int getPreviousPage() {
		return Math.max(curPage-1,1);
	}

	public int getFirstRow() {
		return (getCurPage() - 1) * this.pageRows;
	}

	public Map<Object,Object> getParams() {
		return params;
	}

	public void setParams(Map<Object,Object> params) {
		this.params = params;
	}
	
	public Object removeParam(Object key){
		return this.params.remove(key);
	}
	
	public Object putParam(Object key,Object value){
		if(params==null)
			params=new HashMap<Object,Object>();
		return this.params.put(key, value);
	}
	
	public void cleanParams(){
		this.params.clear();		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}