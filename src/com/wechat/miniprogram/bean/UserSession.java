
package com.wechat.miniprogram.bean;

/**
 * 用户会话
 * @author 苏行利
 * @date 2019-08-30 09:58:53
 */
public class UserSession {
	private String openid; // 用户唯一标识
	private String session_key; // 会话密钥
	private String unionid; // 用户在开放平台的唯一标识符(在满足 UnionID下发条件的情况下会返回)

	/**
	 * 获取用户唯一标识
	 * @author 苏行利
	 * @return 用户唯一标识
	 * @date 2019-08-30 09:59:00
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * 设置用户唯一标识
	 * @author 苏行利
	 * @param openid 用户唯一标识
	 * @date 2019-08-30 09:59:12
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 获取会话密钥
	 * @author 苏行利
	 * @return 会话密钥
	 * @date 2019-08-30 09:59:21
	 */
	public String getSession_key() {
		return session_key;
	}

	/**
	 * 设置会话密钥
	 * @author 苏行利
	 * @param session_key 会话密钥
	 * @date 2019-08-30 09:59:26
	 */
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	/**
	 * 获取用户在开放平台的唯一标识符
	 * @author 苏行利
	 * @return 用户在开放平台的唯一标识符(在满足 UnionID下发条件的情况下会返回)
	 * @date 2019-08-30 09:59:49
	 */
	public String getUnionid() {
		return unionid;
	}

	/**
	 * 设置用户在开放平台的唯一标识符
	 * @author 苏行利
	 * @param unionid 用户在开放平台的唯一标识符
	 * @date 2019-08-30 09:59:58
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}
