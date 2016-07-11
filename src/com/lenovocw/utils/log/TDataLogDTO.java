package com.lenovocw.utils.log;

/**
 * 保存日志实体类
 * @author tanjin
 * @since 2012-04-11
 *
 */
public class TDataLogDTO {
	//用户号码
	private String imsidn;
	//用户ID
	private String userId;
	//操作时间
	private String operationDate;
	//业务名称
	private String businessName;
	//业务ID
	private String businessId;
	//用户IP
	private String userIp;
	//应用来源
	private String applicationSource;
	//操作结果
	private String result;
	//操作描叙
	private String desc;
	/**
	 * 用户号码
	 * @return
	 */
	public String getImsidn() {
		return imsidn;
	}
	/**
	 * 用户号码
	 * @param imsidn
	 */
	public void setImsidn(String imsidn) {
		this.imsidn = imsidn;
	}
	/**
	 * 用户ID
	 * @return
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 用户ID
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 业务名称
	 * @return
	 */
	public String getBusinessName() {
		return businessName;
	}
	/**
	 * 业务名称
	 * @param businessName
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	/**
	 * 业务ID
	 * @return
	 */
	public String getBusinessId() {
		return businessId;
	}
	/**
	 * 业务ID
	 * @param businessId
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	/**
	 * 用户IP
	 * @return
	 */
	public String getUserIp() {
		return userIp;
	}
	/**
	 * 用户IP
	 * @param userIp
	 */
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	/**
	 * 应用来源
	 * @return
	 */
	public String getApplicationSource() {
		return applicationSource;
	}
	/**
	 * 应用来源
	 * @param applicationSource
	 */
	public void setApplicationSource(String applicationSource) {
		this.applicationSource = applicationSource;
	}
	/**
	 * 操作结果
	 * @return
	 */
	public String getResult() {
		return result;
	}
	/**
	 * 操作结果
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * 操作描述
	 * @return
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 操作描述
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 操作时间
	 * @return
	 */
	public String getOperationDate() {
		return operationDate;
	}
	/**
	 * 操作时间
	 * @param operationDate
	 */
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
}
