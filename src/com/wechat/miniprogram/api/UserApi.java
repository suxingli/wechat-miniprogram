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
	private static final String get_paidunionid = "https://api.weixin.qq.com/wxa/getpaidunionid";

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

	/**
	 * 获取用户的UnionId(无需用户授权，调用前需要用户完成支付，且在支付后的五分钟内有效)
	 * @author 苏行利
	 * @param access_token 接口调用凭证
	 * @param openid 支付用户唯一标识
	 * @param transaction_id 微信支付订单号
	 * @return 用户的UnionId
	 * @throws HttpException
	 * @date 2019-08-30 10:46:30
	 */
	public static String getPaidUnionId(String access_token, String openid, String transaction_id) throws HttpException {
		HttpGetRequest request = new HttpGetRequest(get_paidunionid);
		request.addLinkParam("access_token", access_token);
		request.addLinkParam("openid", openid);
		request.addLinkParam("transaction_id", transaction_id);
		HttpResponse response = explorer.doGet(request);
		if (response.isOKStatus()) {
			JSONObject result = JSONObject.fromObject(response.getContent());
			if (result.has("errcode") && result.getInt("errcode") != 0) {
				throw new HttpException(result.getInt("errcode"), MessageResource.getMsg(result.getInt("errcode"), result.getString("errmsg")));
			}
			return result.getString("unionid");
		}
		throw new HttpException(response.getStatusCode(), response.getReasonPhrase());
	}

	/**
	 * 获取用户的UnionId(无需用户授权，调用前需要用户完成支付，且在支付后的五分钟内有效)
	 * @author 苏行利
	 * @param access_token 接口调用凭证
	 * @param openid 支付用户唯一标识
	 * @param mch_id 微信支付分配的商户号
	 * @param out_trade_no 微信支付商户订单号
	 * @return 用户的UnionId
	 * @throws HttpException
	 * @date 2019-08-30 10:47:44
	 */
	public static String getPaidUnionId(String access_token, String openid, String mch_id, String out_trade_no) throws HttpException {
		HttpGetRequest request = new HttpGetRequest(get_paidunionid);
		request.addLinkParam("access_token", access_token);
		request.addLinkParam("openid", openid);
		request.addLinkParam("mch_id", mch_id);
		request.addLinkParam("out_trade_no", out_trade_no);
		HttpResponse response = explorer.doGet(request);
		if (response.isOKStatus()) {
			JSONObject result = JSONObject.fromObject(response.getContent());
			if (result.has("errcode") && result.getInt("errcode") != 0) {
				throw new HttpException(result.getInt("errcode"), MessageResource.getMsg(result.getInt("errcode"), result.getString("errmsg")));
			}
			return result.getString("unionid");
		}
		throw new HttpException(response.getStatusCode(), response.getReasonPhrase());
	}

}
