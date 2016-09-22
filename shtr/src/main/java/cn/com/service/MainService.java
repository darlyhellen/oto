/**上午10:49:02
 * @author zhangyh2
 * MainService.java
 * TODO
 */
package cn.com.service;

import java.util.List;

import cn.com.bean.Action_Theme;
import cn.com.bean.Cart;
import cn.com.bean.ClientCompHomePage;
import cn.com.bean.ClientTheme;
import cn.com.bean.ClientVideo;
import cn.com.bean.MainCarouselModel;
import cn.com.bean.MainMenuModel;
import cn.com.bean.MainMessageModel;
import cn.com.bean.Main_Flower;
import cn.com.bean.UserAddress;
import cn.com.bean.UserEcAccount;
import cn.com.bean.UserInfoData;

/**
 * @author zhangyh2 MainService 上午10:49:02 TODO
 */
public interface MainService {

	/**
	 * 上午10:50:56
	 * 
	 * @author zhangyh2 TODO 测试接口
	 */
	public List<Cart> getAllUser();

	/**
	 * 上午10:14:35
	 * 
	 * @author zhangyh2 TODO 获取首页广告列表
	 */
	List<MainCarouselModel> findCarousel();

	/**
	 * 上午10:14:32
	 * 
	 * @author zhangyh2 TODO 获取首页商品列表
	 */
	List<MainMessageModel> findGoods();

	/**
	 * 上午10:15:35
	 * 
	 * @author zhangyh2 TODO 获取首页菜单按钮
	 */
	List<MainMenuModel> findMenu();

	/**
	 * 下午3:29:48
	 * 
	 * @author zhangyh2 TODO 根据用户信息，进行查询
	 */
	UserInfoData findUser(String name);

	/**
	 * 上午10:47:03
	 * 
	 * @author zhangyh2 TODO 用户输入用户名密码，进行匹配
	 */
	public UserInfoData findUserByLogin(String tel, String pass);

	/**
	 * 下午3:29:48
	 * 
	 * @author zhangyh2 TODO 根据用户信息，进行查询
	 */
	List<UserInfoData> findUsers();

	/**
	 * 上午10:15:05
	 * 
	 * @author zhangyh2 TODO 查看整个表内容
	 */
	List<UserEcAccount> findAccount();

	/**
	 * 上午10:15:18
	 * 
	 * @author zhangyh2 TODO 根据手机号码查找用户云通讯账号
	 */
	UserEcAccount findByTel(String tel);

	/**
	 * 下午4:35:07
	 * 
	 * @author zhangyh2 TODO 用户注册完成后将用户信息插入用户表格中
	 */
	int insertRegistUser(UserInfoData userInfoData);

	/**
	 * 下午4:41:45
	 * 
	 * @author zhangyh2 TODO 注册成功后，进行云通讯注册，云通讯账户注册完成后，进行保存
	 */
	int insertRegistEcAccount(UserEcAccount ecUsers);

	/**
	 * 上午10:33:17
	 * 
	 * @author zhangyh2 TODO
	 */
	int updataUserInfo(UserInfoData infoData);

	/**
	 * 下午2:19:27
	 * 
	 * @author zhangyh2 TODO 查找所有地址接口
	 * @param tel
	 */
	List<UserAddress> findAllAddress();

	/**
	 * 下午2:19:44
	 * 
	 * @author zhangyh2 TODO 对地址进行修改
	 */
	int updateAddress(UserAddress address);

	/**
	 * 下午2:19:44
	 * 
	 * @author zhangyh2 TODO 对地址进行修改
	 */
	int insertAddress(UserAddress address);

	/**
	 * 下午2:19:55
	 * 
	 * @author zhangyh2 TODO 删除用户一条地址
	 */
	int deleteAddress(UserAddress address);

	/**
	 * 下午2:19:27
	 * 
	 * @author zhangyh2 TODO 根据id查找用户地址。
	 * @param tel
	 */
	UserAddress findAddress(Integer id);

	/**
	 * 下午3:22:18
	 * 
	 * @author zhangyh2 TODO 获取用户的地址列表
	 */
	List<UserAddress> findUserAddress(String tel);

	/**
	 * 上午10:39:55
	 * 
	 * @author zhangyh2 TODO 获取新主题
	 */
	List<ClientTheme> findClienTheme();

	/**
	 * 上午10:40:17
	 * 
	 * @author zhangyh2 TODO 获取服务器上的视频
	 */
	List<ClientVideo> findClienVideo();

	/**
	 * 上午10:59:12
	 * 
	 * @author zhangyh2 TODO 获取服务器上公司首页展示内容
	 */
	List<ClientCompHomePage> findClientHomePage();

	/**
	 * 上午9:57:25
	 * 
	 * @author zhangyh2 TODO 插入第二个页面的新数据
	 */
	int insertAction(Action_Theme theme);

	/**
	 * 上午11:03:09
	 * 
	 * @author zhangyh2 TODO 查找数据
	 */
	List<Action_Theme> findAction_Themes(int page);

	/**
	 * 下午4:49:08
	 * 
	 * @author zhangyh2 TODO 查找花海首页参数
	 */
	public List<Main_Flower> findMain_Flower(String group, int page);
}
