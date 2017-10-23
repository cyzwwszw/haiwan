package com.lincomb.loans.model;
/** 
* @author jun.lei
* @version 2017年4月10日 下午4:40:26 
*/
public class RespCode {

	public static String SUCCESS = "0000";
	
	public static String FAIL = "1111";
	
	public static String SYS_ERROR = "9999";
	
	//用户相关（登陆、注册、实名认证）响应码，以M（Member）开头
	//实名认证失败
	public static String REAL_NAME_AUTH_FAIL = "M100001";
	//未实名认证
	public static String NOT_REAL_NAME_AUTH_FAIL = "M100002";
	//产品相关响应码，以P（Product）开头
	
	//进件相关（提交进件，发标，进件审核等）响应码，以R（Risk）开头
	public static String PARAM_VALID_FAIL = "R100001";

	public static String RISK_PARAM_VALID_FAIL = "R100001";

	public static String CAMERA_NO_FOUND_FAIL = "R100002";
	  //进件相关
	public static String HAS_UNCOMPLETE_CAMERA = "R200001";
	  //借款年龄受限
	public static String USER_AGE_NOT_ALLOW_LOAN = "R200002";
	  //审批
	
	  //标
	
	//资金相关（充值、提现、还款等）响应码，以I（Item）开头

	
	//内部其他系统相关
	
	//第三方相关
	public static String ITEM_PARAM_VALID_FAIL = "I100001"; //请求参数不合法
	public static String INSERT_RECHARGE_ORDER_ERROR = "I100002"; //生成充值订单错误
	public static String HANDLE_RECHARGE_RESULT_ERROR = "I100003"; //处理连连充值结果错误
	public static String BANK_CARD_ISBINDED_ERROR = "I100004"; //银行卡已被绑定
	public static String HAS_UNCOMPLETE_RECHARE_ORDER_ERROR = "I100005"; //有未完成的充值订单
	public static String CANT_FIND_RECHARGE_ORDER = "I100006"; //查找不到充值订单
	public static String CANT_FIND_BANK_CARD = "I100007"; //查找不到银行卡
	
	//内部其他系统相关
	
	//第三方相关响应码，以T（Third）开头
	public static String LIAN_LIAN_RETURN_ERROR = "T100001";
}
