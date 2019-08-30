package com.wechat.miniprogram.api;

import com.wechat.miniprogram.MessageResource;
import com.wechat.miniprogram.bean.UserSession;
import com.wechat.miniprogram.factory.HttpExplorerFactory;

import jxl.httpclient.HttpExplorer;
import jxl.httpclient.exception.HttpException;
import jxl.httpclient.request.HttpGetRequest;
import jxl.httpclient.response.HttpResponse;
import net.sf.json.JSONObject;

/**
 * 用户接口
 * @author 苏行利
 * @date 2019-08-30 09:56:11
 */
public class UserApi {
	private static final HttpExplorer explorer = HttpExplorerFactory.getHttpExplorer();
	private static final String sns_jscode2session = "https://api.weixin.qq.com/sns/jscode2session";

	/**
	 * 登录
	 * @author 苏行利
	 * @param appid 小程序唯一凭证
	 * @param secret 小程序唯一凭证密钥
	 * @param js_code 登录时获取的code
	 * @return 用户会话
	 * @throws HttpException
	 * @date 2019-08-30 09:56:18
	 */
	public static UserSession login(String appid, String secret, String js_code) throws HttpException {
		HttpGetRequest request = new HttpGetRequest(sns_jscode2session);
		request.addLinkParam("appid", appid);
		request.addLinkParam("secret", secret);
		request.addLinkParam("js_code", js_code);
		request.addLinkParam("grant_type", "authorization_code");
		HttpResponse response = explorer.doGet(request);
		if (response.isOKStatus()) {
			JSONObject result = JSONObject.fromObject(response.getContent());
			if (result.has("errcode") && result.getInt("errcode") != 0) {
				throw new HttpException(result.getInt("errcode"), MessageResource.getMsg(result.getInt("errcode"), result.getString("errmsg")));
			}
			UserSession o = new UserSession();
			o.setOpenid(result.getString("openid"));
			o.setSession_key(result.getString("session_key"));
			o.setUnionid(result.has("unionid") ? result.getString("unionid") : null);
			return o;
		}
		throw new HttpException(response.getStatusCode(), response.getReasonPhrase());
	}

}
