package com.example.paoma_zf.config;

public class ZfConfig {

	// ********************** int �Ͳ��� *************************************
	// ��ʧ�Ի���ȴ�ʱ�䣺1.5��
	public static final int DIG_DISMISS_DELAY = 1500;
	// ���ݿ�汾
	public static final int DATABASE_VERSION = 1;

	// **************** boolean �Ͳ���****************************************
	// �Ƿ���Ҫ�����ֻ����ذ���
	public static final boolean IS_NEED_SHIELD = false;
	// ����Ƿ���Ҫע��
	public static final boolean IS_NEED_REGISTER = false;

	// ***************** String �Ͳ���****************************************
	// ͷ��Ĭ�Ϻ�׺��
	public static final String HEAD_NAME = ".jpg";
	// �ֻ�����
	public static final String USER_PHONE_NUMBER = "phone_number";
	// �û�����
	public static final String USER_TYPE = "user_type";
	// �û�ID
	public static final String USER_ID = "user_id";
	// session ID
	public static final String SESSION_ID = "sid";
	// cookie
	public static final String COOKIE = "Cookie";
	// ͼ���ַ
	public static final String HEAD_PIC_PATH = "head_pic_path";
	// �û�����
	public static final String USER_PASSWORD = "user_password";
	// �Ƿ���Ҫ������ݷ�ʽ
	public static final String IS_NEED_CREATE_SHORT = "isNeedCreateShort";
	// shared�洢�Ƿ���Ҫ�����������
	public static final String IS_NEED_GUIDE = "isGuide";
	// �������ݹ����ļ�����
	public static final String SHARED_NAME = "pm";
	// Ĭ����־ǰ׺
	public static final String LOG_PREFIX = " -pm- ";
	// Ĭ���ļ��洢·��
	public static final String FILE_PATH = "/OrderingAMeal";
	// �ؼ��ٲ������б��л����
	public static final String SPECIAL_IS_LISTVIEW = "isListView";
	// �޸�ͷ��㲥
	public static final String UPDATE_HEAD_PIC = "update_head_pic_broad";
	// ��λ�㲥
	public static final String LOCAL_BROAD_STRING = "local.broad.string";
	// ���õ�ַ�㲥
	public static final String SET_LOCATION_ADDRESS = "set.location.address";
	// ��ʱ�޷������
	public static final String UPDATE_COUNT_FLAG = "update.count.flag";

	/**
	 * ***************************** ���ݿ����
	 * ***********************************************
	 */
	// ���ݿ�����
	public static final String DATABASE_NAME = "pmdb";
	// ��ַ����
	public static final String TABLE_ADDRESS = "address";
	// ��������
	public static final String TABLE_BOARD = "board";
	// �����ͱ���
	public static final String TABLE_VEGETABLE = "vegetable_type";
	// �ҵ����ϱ���
	public static final String TABLE_MY_INFO = "my_information";
	// ����״̬
	public static final String COMPANY_STATE = "company_state";
	// �˷�
	public static final String LOW_MONEY = "low_money";

	/**
	 * ***************************** �����������
	 * ***********************************************
	 * 
	 */

	// // ��¼
	// public static final String getUserList =
	// "http://jiatx.longguanhuamu.com/eimh/appUserAction.action";
	// //�鿴��Դ��Ϣ
	// public static final String gethouseList =
	// "http://jiatx.longguanhuamu.com/eimh/appHouseAction.action";
	// //��ȡ������Ϣ
	// public static final String getOrders =
	// "http://jiatx.longguanhuamu.com/eimh/appOrdersAction.action";
	// //��ȡ�ܼ���Ϣ
	// public static final String getHouseKeeper =
	// "http://jiatx.longguanhuamu.com/eimh/appManagerAction.action";
	// //��ȡ����
	// public static final String getDiscussion =
	// "http://jiatx.longguanhuamu.com/eimh/appDynamicAction.action";
	// //��ȡ�ղ�
	// public static final String getCollection =
	// "http://jiatx.longguanhuamu.com/eimh/appCollectAction.action";
	// //��ȡ����
	// public static final String getComment =
	// "http://jiatx.longguanhuamu.com/eimh/appAppraiseAction.action";
	// //��ȡ��ַ
	// public static final String getAddress =
	// "http://jiatx.longguanhuamu.com/eimh/appAddressAction.action";
	// //��ȡ�����¼
	// public static final String getBrowserecord =
	// "http://jiatx.longguanhuamu.com/eimh/appBrowseAction.action";

	// IP
	// public static final String url = "http://192.168.1.102:8080/eimh/";
	public static final String url = "http://jiatx.longguanhuamu.com/eimh/";

	// ��¼
	public static final String getUserList = url + "appUserAction.action";
	// �鿴��Դ��Ϣ
	public static final String gethouseList = url + "appHouseAction.action";
	// ��ȡ������Ϣ
	public static final String getOrders = url + "appOrdersAction.action";
	// ��ȡ�ܼ���Ϣ
	public static final String getHouseKeeper = url + "appManagerAction.action";
	// ��ȡ����
	public static final String getDiscussion = url + "appDynamicAction.action";
	// ��ȡ�ղ�
	public static final String getCollection = url + "appCollectAction.action";
	// ��ȡ����
	public static final String getComment = url + "appAppraiseAction.action";
	// ��ȡ��ַ
	public static final String getAddress = url + "appAddressAction.action";
	// ��ȡ�����¼
	public static final String getBrowserecord = url + "appBrowseAction.action";
	// ��ȡ�û��ȼ�
	public static final String getUserLevelList = url
			+ "appUserLevelAction.action";

}
