package com.lincomb.loans.model;

import java.util.Arrays;
import java.util.List;

/**
 * <br>
 * FileName: Constants.java <br>
 * Creater: xujs <br>
 * Date: 2016-07-26 <br>
 * <br>
 * Modifier: <br>
 * Date: <br>
 * <br>
 * Description: 公共常量类 <br>
 */
public class Constants {
	
	/**
	 * 成功CODE
	 */
	public static final String SUCCESS_CODE = "0000";
	
	/**
	 * 业务失败CODE
	 */
	public static final String ERROR_CODE = "0009";
	
	/**
	 * 异常CODE
	 */
	public static final String EXCEPTION_CODE = "9999";
	
	/**
	 * BRAND:品牌名称CODE
	 */
	public static final String BRAND_NAME_CODE = "1000";
	
	/**
	 * 上传（上传文件为空）
	 */
	public static final String UPLOAD_CODE_9000 = "9000";
	
	/**
	 * 上传（上传文件格式错误）
	 */
	public static final String UPLOAD_CODE_9001 = "9001";
	
	/**
	 * 消息
	 */
	public static final int SMS = 1;
	
	/**
	 * 邮件
	 */
	public static final int EMAIL = 2;
	
	/**
	 * 发票类型 无发票
	 */
	public static final String INVOICE_TYPE_NO = "1";
	
	/**
	 * 发票类型 普通发票
	 */
	public static final String INVOICE_TYPE_ORDINARY = "2";
	
	/**
	 * fastDFS 商品用 分组名 
	 */
	public static final String FASTDFS_GROUP_GOODS = "goods";
	
	/**
	 * fastDFS 文章用 分组名 
	 */
	public static final String FASTDFS_GROUP_ARTICLE = "article";
	
	/**
	 * fastDFS 提现用 分组名 
	 */
	public static final String FASTDFS_GROUP_WITHDRAW = "withdraw";
	
	
	/**
	 * fastDFS 文章用 分组名 
	 */
	public static final String FASTDFS_GROUP_INFOR = "information";
	
	/**
	 * fastDFS 用户用 分组名 
	 */
	public static final String FASTDFS_GROUP_USER = "user";
	/**
	 * fastDFS 订单用 分组名 
	 */
	public static final String FASTDFS_GROUP_ORDER = "order";
	/**
	 * 接口传入from取值范围
	 */
	public static final List<String> FROM_LIST  = Arrays.asList("WX","WEB","ANDROID","IOS","LBJR", "MANAGE");
	
	/**
	 * 短信接口传入from取值范围
	 */
	public static final List<String> MSG_FROM_LIST  = Arrays.asList("MANAGE","QUARTZ", "PAY");
	
	/**
	 * 信息推送传入from取值范围
	 */
	public static final List<String> PUSH_MESSAGE_FROM_LIST  = Arrays.asList("MANAGE");
	
	/**
	 * 邮件接口传入from取值范围
	 */
	public static final List<String> EMAIL_FROM_LIST  = Arrays.asList("MERCHANT");
	
	/**
	 * 用户上传头像图片类型取值范围
	 */
	public static final List<String> USER_IMG_CONTENT_TYPE = Arrays.asList("jpg","png","JPG","PNG","JPEG");
	
	
	/**
	 * 字符串逗号分隔符
	 */
	public static final String STRING_SPLIT_COMMA = ",";
	
	/**
	 * 字符串分号分隔符
	 */
	public static final String STRING_SPLIT_SEMICOLON = ";";
	
	/**
	 * 字符串冒号分隔符
	 */
	public static final String STRING_SPLIT_COLON = ":";
	//支付平台键值，在验签中需要去除
	public static final String PAYMENT_PLATFORM="paymentPlatform";
	
	//付款类型
	//购买付款
	public static final String PAYMENT_TYPE_PAYMENT_BUY="1";
	//预充值
	public static final String PAYMENT_TYPE_PRE_RECHARGE="2";
	//一元购购买
	public static final String PAYMENT_TYPE_ONE_BUY="3";
	
	//资金类型
	//购买
	public static final String FUND_TYPE_PAYMENT_BUY="1";
	//退款
	public static final String FUND_TYPE_REFUND="2";
	//一元购资金类型
	public static final String FUND_TYPE_BUY_SCORE="5";
	
	//智豆流水类型
	//购买
	public static final String POINT_FLOW_CLOUD_BUY="1";
	//充值
	public static final String POINT_FLOW_BUY_SCORE="2";
	//注册送智豆
	public static final String POINT_FLOW_REGISTER_GIVE="3";
	//评价送智豆
	public static final String POINT_FLOW_EVALUATE_GIVE="4";
	//活动赠送智豆
	public static final String POINT_FLOW_ACTIVITY_GIVE="5";
	//签到赠送智豆
	public static final String POINT_FLOW_SIGNIN_GIVE="6";
	
	/**
	 * 一元购幸运号码 基数
	 */
	public static final Long CLOUD_BUY_BASE_NUM = 10000000L;
	
	/**
	 * 商品类别 实物商品
	 */
	public static final String GOODS_TYPE_REAL = "1";

	/**
	 * 商品类别 虚拟商品
	 */
	public static final String GOODS_TYPE_FICTITIOUS = "2";
	
	/**
	 * 一元购（云购）开关 ON：开 OFF：关
	 */
	public static final String CLOUD_BUY_FLG = "cloud_buy_flg";

	/**
	 * 一元购tab名
	 */
	public static final String CLOUD_BUY_TAB_NAME = "cloud_buy_tab_name";
	
	/**
	 * 渠道
	 */
	public static final String CHANNEL_LBJR = "LBJR";
	
	/**
	 * 接口传入评论来源类型 ('ARTICLE':文章 'INFO':资讯)
	 */
	public static final List<String> FROM_TYPE_LIST  = Arrays.asList("ARTICLE", "INFO");
	public static final String FROM_TYPE_ARTICLE = "ARTICLE";
	public static final String FROM_TYPE_INFO = "INFO";
	
	/**
	 * 习之老板娘
	 */
	public static final String CHANNEL_NO_XZLBN = "200";
	
	/**
	 * 斐讯
	 */
	public static final String CHANNEL_NO_CQGD = "201";
	
	/**
	 * 重庆发布会购买
	 */
	public static final String CHANNEL_NO_CQFBHBUY = "202";
	
	/**
	 *接口传入参数的反馈客户类型（"1":平台用户，"2":商户）
	 */
	
	public static final List<String> USER_TYPE_LIST=Arrays.asList("1", "2","0");
	public static final String USER_TYPE_DEF="0";
	public static final String USER_TYPE_PL="1";
	public static final String USER_TYPE_COM="2";

	/**
	 *接口传入参数商家来源渠道类型（"1":智慧家，"2":礼物说）
	 */
	public static final List<String> BUSINESS_CHANNEL_LIST=Arrays.asList("1", "2","0");
	public static final String BUSINESS_CHANNEL_DEF="0";
	public static final String BUSINESS_CHANNEL_WIS="1";
	public static final String BUSINESS_CHANNEL_GIF="2";
	
	/**
	 * 商品或文章资讯评论内容的长度
	 */
	public static final int EVALUATE_CONTENT_LENGTH = 120;
	
	
	/**
	 * 文章状态 待发布
	 */
	public static final String SCENE_ARTICLE_STATUS_WAIT_REL = "0";

	/**
	 * 文章状态 待审核
	 */
	public static final String SCENE_ARTICLE_STATUS_WAIT_REVIEW = "1";
	
	/**
	 * 文章状态 审核通过
	 */
	public static final String SCENE_ARTICLE_STATUS_REVIEW_ON = "2";

	/**
	 * 文章状态 审核未通过
	 */
	public static final String SCENE_ARTICLE_STATUS_REVIEW_OFF = "3";
	
	/**
	 * 文章状态 已下架
	 */
	public static final String SCENE_ARTICLE_STATUS_OFF_SELF = "4";
	
	/**
	 * 删除状态 未删除
	 */
	public static final String DEL_FLG_NOT_DEL = "0";

	/**
	 * 删除状态 已删除
	 */
	public static final String DEL_FLG_HAS_DEL = "1";

	/**
	 * 分享活动单日领取上限
	 */
    public static final String SHARE_ACTIVITY_DAILY_LIMIT="SHARE_ACTIVITY_DAILY_LIMIT";
    
    /**
	 * 物品配置类型 智豆
	 */
    public static final String EXCHANGE_CODE_TYPE_ZD = "01";
    
    /**
	 * 物品配置类型 代金券
	 */
    public static final String EXCHANGE_CODE_TYPE_DHM = "02";
    
    /**
	 * 物品配置类型 虚拟商品
	 */
    public static final String EXCHANGE_CODE_TYPE_XN = "03";
    
    /**
	 * 物品配置类型 实物商品
	 */
    public static final String EXCHANGE_CODE_TYPE_SW = "04";
    
    /**
	 * 物品配置类型 智豆和代金券
	 */
    public static final String EXCHANGE_CODE_TYPE_ZDDHM = "05";
    
    /**
	 * 物品配置类型 其他
	 */
    public static final String EXCHANGE_CODE_TYPE_OTHER = "99";
    
    /**
     * 智慧应用场景图片上传类型
     */
    public static final List<String> SCENE_IMG_CONTENT_TYPE = Arrays.asList("jpg","png","JPG","PNG","JPEG");
    
    /**
     * 流程模板的位置
     */
    public static final String PROCESS_FILE_PATH = "process/";
    /**
     * 流程类型-贷款短流程
     */
    public static final String PROCESS_TYPE_LOANSHORTPROCESS = "loanShortProcess";
    
    /**
     * 流程分配类型-组
     */
    public static final String TYPE_GROUP = "group";
    
    /**
     * 流程分配类型-个人
     */
    public static final String TYPE_PERSON = "person";
    
    /**
     * 角色名称-初审主管
     */
    public static final String ROLE_NAME_FIRST_LEADER = "初审主管";
    
    /**
     * 角色名称-复审主管
     */
    public static final String ROLE_NAME_RECHECK_LEADER = "复审主管";
    
    /**
     * 借款年龄限制-agemin
     */
    public static final String LOAN_AGE_MIN = "18";
    
    /**
     * 借款年龄限制-agemax
     */
    public static final String LOAN_AGE_MAX = "65";

	/**
	 * 短信签名
	 */
	public static final String MESSAGE_HEADER = "多点小贷";

	/**
	 * 用户注册
	 */
	public static final String USER_REGISTER = "user_register";

	/**
	 * 找回密码
	 */
	public static final String USER_FIND_BACK = "user_find_back";

	/**
	 * 充值交易密码
	 */
	public static final String RESET_TRADE_CODE = "reset_trade_code";


	public static final String AUDIT_REJECT = "audit_reject";
	public static final String AUDIT_APPROVE = "audit_approve";
	public static final String LOAN_TO_PLATFORM = "loan_to_platform";
	public static final String LOAN_TO_BANKCARD = "loan_to_bankcard";
	public static final String WITHDRAW_CASH = "withdraw_cash";
	public static final String RECHARGE_SUCCESS = "recharge_success";
	public static final String REMIND_REPAYMENT = "remind_repayment";
	public static final String REPAYMENT_SUCCESS = "repayment_success";

	/**
	 * 短信发送最小间隔(分钟)
	 */
	public static final String BETWEEN_MIN_LIMIT = "between_min_limit";

	/**
	 * 短信发送24小时内最大上限(次)
	 */
	public static final String DAILY_MAX_LIMIT = "daily_limit";

	/**
	 * 短信发送有效时间(分钟)
	 */
	public static final String EFFECTIVE_TIME_LIMIT = "effective_time_limit";

	/**
	 *	用户账户表status
	 *	正常:1
	 */
	public static final String USER_ACCT_STATUS_NORMAL = "1";
	/**
	 * 用户账户表channel_sign
	 * 平台注册账户:1
	 */
	public static final String USER_ACCT_CNANNEL_SIGN_PLATFORM = "1";
	
	/**
	 * 用户类型（01：个人、02：企业）
	 */
	public static final String USER_TYPE_01 = "01";
	public static final String USER_TYPE_02 = "02";
	
	 /**
     * 下载文件路径
     */
    public static final String DOWNLOAD_PATH = "http://172.31.31.253/";
    
    /**
     * 充值温馨提示
     */
    public static final String CHARGE_TIPS = "charge_tips";
    /**
     * 提现温馨提示
     */
    public static final String WITHDRAW_TIPS = "withdraw_tips";

}
