/**
 * 
 */
package com.lenovocw.web.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.servlet.view.AbstractView;

public class JsonView extends AbstractView {

	private String id;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public JsonView() {

	}

	public JsonView(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map modelMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject json = new JSONObject(modelMap, false);
		response.getWriter().write(json.toString());
//		String jsonData = "";
//		if (null == id || id.length() == 0) {
//			jsonData = JsonConvert.toJson(modelMap);
//		} else {
//			jsonData = JsonConvert.toJson(this.id, modelMap);
//		}
//		resultDTO.setData(jsonData);
//		response.getWriter().write(JsonConvert.toJson("resultDTO", resultDTO));
	}

}
