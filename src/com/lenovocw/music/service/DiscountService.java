package com.lenovocw.music.service;

import java.util.List;
import java.util.Map;





public interface DiscountService {

	public List<Map<String, Object>> getDiscount() throws Exception;

	/**
	 * 功能：根据Id查询折扣信息
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getDiscountById(String id);

}
