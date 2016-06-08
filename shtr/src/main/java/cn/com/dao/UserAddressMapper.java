package cn.com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.bean.UserAddress;

public interface UserAddressMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserAddress record);

	int insertSelective(UserAddress record);

	UserAddress selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserAddress record);

	int updateByPrimaryKey(UserAddress record);

	List<UserAddress> selectUserAddress(@Param("tel") String tel);
	
	List<UserAddress> selectAll();
}