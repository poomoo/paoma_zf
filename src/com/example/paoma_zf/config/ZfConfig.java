package com.example.paoma_zf.config;

public class ZfConfig {

	// ********************** int 型参数 *************************************
	// 消失对话框等待时间：1.5秒
	public static final int DIG_DISMISS_DELAY = 1500;
	// 数据库版本
	public static final int DATABASE_VERSION = 1;

	// **************** boolean 型参数****************************************
	// 是否需要屏蔽手机返回按键
	public static final boolean IS_NEED_SHIELD = false;
	// 软件是否需要注册
	public static final boolean IS_NEED_REGISTER = false;

	// ***************** String 型参数****************************************
	// 头像默认后缀名
	public static final String HEAD_NAME = ".jpg";
	// 手机号码
	public static final String USER_PHONE_NUMBER = "phone_number";
	// 用户类型
	public static final String USER_TYPE = "user_type";
	// 用户ID
	public static final String USER_ID = "user_id";
	// session ID
	public static final String SESSION_ID = "sid";
	// cookie
	public static final String COOKIE = "Cookie";
	// 图像地址
	public static final String HEAD_PIC_PATH = "head_pic_path";
	// 用户密码
	public static final String USER_PASSWORD = "user_password";
	// 是否需要创建快捷方式
	public static final String IS_NEED_CREATE_SHORT = "isNeedCreateShort";
	// shared存储是否需要引导界面键名
	public static final String IS_NEED_GUIDE = "isGuide";
	// 工程数据共享文件名字
	public static final String SHARED_NAME = "pm";
	// 默认日志前缀
	public static final String LOG_PREFIX = " -pm- ";
	// 默认文件存储路径
	public static final String FILE_PATH = "/OrderingAMeal";
	// 特价瀑布流和列表切换标记
	public static final String SPECIAL_IS_LISTVIEW = "isListView";
	// 修改头像广播
	public static final String UPDATE_HEAD_PIC = "update_head_pic_broad";
	// 定位广播
	public static final String LOCAL_BROAD_STRING = "local.broad.string";
	// 设置地址广播
	public static final String SET_LOCATION_ADDRESS = "set.location.address";
	// 及时修分数标记
	public static final String UPDATE_COUNT_FLAG = "update.count.flag";

	/**
	 * ***************************** 数据库相关
	 * ***********************************************
	 */
	// 数据库名字
	public static final String DATABASE_NAME = "pmdb";
	// 地址表名
	public static final String TABLE_ADDRESS = "address";
	// 餐桌表名
	public static final String TABLE_BOARD = "board";
	// 菜类型表明
	public static final String TABLE_VEGETABLE = "vegetable_type";
	// 我的资料表名
	public static final String TABLE_MY_INFO = "my_information";
	// 店铺状态
	public static final String COMPANY_STATE = "company_state";
	// 运费
	public static final String LOW_MONEY = "low_money";

	/**
	 * ***************************** 访问网络相关
	 * ***********************************************
	 * 
	 */

	// // 登录
	// public static final String getUserList =
	// "http://jiatx.longguanhuamu.com/eimh/appUserAction.action";
	// //查看房源信息
	// public static final String gethouseList =
	// "http://jiatx.longguanhuamu.com/eimh/appHouseAction.action";
	// //获取订单信息
	// public static final String getOrders =
	// "http://jiatx.longguanhuamu.com/eimh/appOrdersAction.action";
	// //获取管家信息
	// public static final String getHouseKeeper =
	// "http://jiatx.longguanhuamu.com/eimh/appManagerAction.action";
	// //获取讨论
	// public static final String getDiscussion =
	// "http://jiatx.longguanhuamu.com/eimh/appDynamicAction.action";
	// //获取收藏
	// public static final String getCollection =
	// "http://jiatx.longguanhuamu.com/eimh/appCollectAction.action";
	// //获取评论
	// public static final String getComment =
	// "http://jiatx.longguanhuamu.com/eimh/appAppraiseAction.action";
	// //获取地址
	// public static final String getAddress =
	// "http://jiatx.longguanhuamu.com/eimh/appAddressAction.action";
	// //获取浏览记录
	// public static final String getBrowserecord =
	// "http://jiatx.longguanhuamu.com/eimh/appBrowseAction.action";

	// IP
	// public static final String url = "http://192.168.1.102:8080/eimh/";
	public static final String url = "http://jiatx.longguanhuamu.com/eimh/";

	// 登录
	public static final String getUserList = url + "appUserAction.action";
	// 查看房源信息
	public static final String gethouseList = url + "appHouseAction.action";
	// 获取订单信息
	public static final String getOrders = url + "appOrdersAction.action";
	// 获取管家信息
	public static final String getHouseKeeper = url + "appManagerAction.action";
	// 获取讨论
	public static final String getDiscussion = url + "appDynamicAction.action";
	// 获取收藏
	public static final String getCollection = url + "appCollectAction.action";
	// 获取评论
	public static final String getComment = url + "appAppraiseAction.action";
	// 获取地址
	public static final String getAddress = url + "appAddressAction.action";
	// 获取浏览记录
	public static final String getBrowserecord = url + "appBrowseAction.action";
	// 获取用户等级
	public static final String getUserLevelList = url
			+ "appUserLevelAction.action";

}
