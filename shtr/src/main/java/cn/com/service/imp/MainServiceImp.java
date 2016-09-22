/**上午10:52:00
 * @author zhangyh2
 * MainServiceImp.java
 * TODO
 */
package cn.com.service.imp;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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
import cn.com.dao.Action_ThemeMapper;
import cn.com.dao.CartMapper;
import cn.com.dao.ClientCompHomePageMapper;
import cn.com.dao.ClientThemeMapper;
import cn.com.dao.ClientVideoMapper;
import cn.com.dao.MainCarouselModelMapper;
import cn.com.dao.MainMenuModelMapper;
import cn.com.dao.MainMessageModelMapper;
import cn.com.dao.Main_FlowerMapper;
import cn.com.dao.UserAddressMapper;
import cn.com.dao.UserEcAccountMapper;
import cn.com.dao.UserInfoDataMapper;
import cn.com.service.MainService;

/**
 * @author zhangyh2 MainServiceImp 上午10:52:00 TODO
 */
@Service
@Transactional
public class MainServiceImp implements MainService {
	@Resource
	private CartMapper mapCart;
	@Resource
	private MainCarouselModelMapper mapCarousel;
	@Resource
	private MainMessageModelMapper mapMess;
	@Resource
	private MainMenuModelMapper mapMenu;
	@Resource
	private UserInfoDataMapper mapUserInfo;
	@Resource
	private UserEcAccountMapper mapUserEc;
	@Resource
	private UserAddressMapper mapUserAddress;
	@Resource
	private ClientThemeMapper mapClientTheme;
	@Resource
	private ClientVideoMapper mapClientVideo;
	@Resource
	private ClientCompHomePageMapper mapClientComp;
	@Resource
	private Action_ThemeMapper mapAction;
	@Resource
	private Main_FlowerMapper mapFlower;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#getAllUser()
	 */
	@Override
	public List<Cart> getAllUser() {
		// TODO Auto-generated method stub
		return mapCart.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findCarousel()
	 */
	@Override
	public List<MainCarouselModel> findCarousel() {
		// TODO Auto-generated method stub
		return mapCarousel.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findGoods()
	 */
	@Override
	public List<MainMessageModel> findGoods() {
		// TODO Auto-generated method stub
		return mapMess.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findMenu()
	 */
	@Override
	public List<MainMenuModel> findMenu() {
		// TODO Auto-generated method stub
		return mapMenu.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findUser()
	 */
	@Override
	public UserInfoData findUser(String name) {
		// TODO Auto-generated method stub
		return mapUserInfo.selectByTel(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findUsers()
	 */
	@Override
	public List<UserInfoData> findUsers() {
		// TODO Auto-generated method stub
		return mapUserInfo.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findUserByLogin(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public UserInfoData findUserByLogin(String tel, String pass) {
		// TODO Auto-generated method stub
		return mapUserInfo.selectByLogin(tel, pass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findAccount()
	 */
	@Override
	public List<UserEcAccount> findAccount() {
		// TODO Auto-generated method stub
		return mapUserEc.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findByTel(java.lang.String)
	 */
	@Override
	public UserEcAccount findByTel(String tel) {
		// TODO Auto-generated method stub
		return mapUserEc.selectByTel(tel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.service.MainService#insertRegistUser(cn.com.bean.UserInfoData)
	 */
	@Override
	public int insertRegistUser(UserInfoData userInfoData) {
		// TODO Auto-generated method stub
		return mapUserInfo.insert(userInfoData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.service.MainService#insertRegistEcAccount(cn.com.bean.UserEcAccount
	 * )
	 */
	@Override
	public int insertRegistEcAccount(UserEcAccount ecUsers) {
		// TODO Auto-generated method stub
		return mapUserEc.insert(ecUsers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#updataUserInfo(cn.com.bean.UserInfoData)
	 */
	@Override
	public int updataUserInfo(UserInfoData infoData) {
		// TODO Auto-generated method stub
		return mapUserInfo.updateByTel(infoData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findAllAddress()
	 */
	@Override
	public List<UserAddress> findAllAddress() {
		// TODO Auto-generated method stub
		return mapUserAddress.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#updateAddress(cn.com.bean.UserAddress)
	 */
	@Override
	public int updateAddress(UserAddress address) {
		// TODO Auto-generated method stub
		return mapUserAddress.updateByPrimaryKey(address);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#insertAddress(cn.com.bean.UserAddress)
	 */
	@Override
	public int insertAddress(UserAddress address) {
		// TODO Auto-generated method stub
		return mapUserAddress.insert(address);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#deleteAddress(cn.com.bean.UserAddress)
	 */
	@Override
	public int deleteAddress(UserAddress address) {
		// TODO Auto-generated method stub
		return mapUserAddress.deleteByPrimaryKey(address.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findAddress(java.lang.Integer)
	 */
	@Override
	public UserAddress findAddress(Integer id) {
		// TODO Auto-generated method stub
		return mapUserAddress.selectByPrimaryKey(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findUserAddress(java.lang.String)
	 */
	@Override
	public List<UserAddress> findUserAddress(String tel) {
		// TODO Auto-generated method stub
		return mapUserAddress.selectUserAddress(tel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findClienTheme()
	 */
	@Override
	public List<ClientTheme> findClienTheme() {
		// TODO Auto-generated method stub
		return mapClientTheme.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findClienVideo()
	 */
	@Override
	public List<ClientVideo> findClienVideo() {
		// TODO Auto-generated method stub
		return mapClientVideo.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findClientHomePage()
	 */
	@Override
	public List<ClientCompHomePage> findClientHomePage() {
		// TODO Auto-generated method stub
		return mapClientComp.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#insertAction(cn.com.bean.Action_Theme)
	 */
	@Override
	public int insertAction(Action_Theme theme) {
		// TODO Auto-generated method stub
		return mapAction.insert(theme);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findAction_Themes(int)
	 */
	@Override
	public List<Action_Theme> findAction_Themes(int page) {
		// TODO Auto-generated method stub
		return mapAction.selectPage((page - 1) * 10, 10);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.service.MainService#findMain_Flower(java.lang.String, int)
	 */
	@Override
	public List<Main_Flower> findMain_Flower(String goup, int page) {
		// TODO Auto-generated method stub
		return mapFlower.selectFromPageAndGroup((page - 1) * 10, 10, goup);
	}

}
