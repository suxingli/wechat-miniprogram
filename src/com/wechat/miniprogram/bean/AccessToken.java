package com.wechat.miniprogram.bean;

import java.util.Calendar;
import java.util.Date;

/**
 * 接口调用凭证
 * @author 苏行利
 * @date 2019-08-30 10:13:16
 */
public class AccessToken {
	private String access_token; // 凭证
	private Integer expires_in; // 凭证有效时间(单位：秒，目前是7200秒之内的值)
	private Date expires_time; // 凭证失效时间

	/**
	 * 获取凭证
	 * @author 苏行利
	 * @return 凭证
	 * @date 2019-08-30 10:15:09
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * 设置凭证
	 * @author 苏行利
	 * @param access_token 凭证
	 * @date 2019-08-30 10:15:16
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * 获取凭证有效时间
	 * @author 苏行利
	 * @return 设置凭证有效时间(单位：秒)
	 * @date 2019-08-30 10:15:29
	 */
	public Integer getExpires_in() {
		return expires_in;
	}

	/**
	 * 设置凭证有效时间
	 * @author 苏行利
	 * @param expires_in 凭证有效时间(单位：秒)
	 * @date 2019-08-30 10:16:15
	 */
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MILLISECOND, (expires_in - 600) * 1000);
		this.setExpires_time(c.getTime());
	}

	/**
	 * 获取凭证失效时间
	 * @author 苏行利
	 * @return 凭证失效时间
	 * @date 2019-08-30 10:16:45
	 */
	public Date getExpires_time() {
		return expires_time;
	}

	/**
	 * 设置凭证失效时间
	 * @author 苏行利
	 * @param expires_time 凭证失效时间
	 * @date 2019-08-30 10:16:52
	 */
	public void setExpires_time(Date expires_time) {
		this.expires_time = expires_time;
	}

	/**
	 * 判断评证是否已失效
	 * @author 苏行利
	 * @return 评证是否已失效
	 * @date 2019-08-30 10:17:14
	 */
	public boolean isExpires() {
		if (new Date().getTime() > expires_time.getTime()) {
			return true;
		}
		return false;
	}
}
