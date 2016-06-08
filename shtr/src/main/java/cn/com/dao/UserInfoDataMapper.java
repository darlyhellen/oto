package cn.com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.bean.UserInfoData;

public interface UserInfoDataMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserInfoData record);

	int insertSelective(UserInfoData record);

	UserInfoData selectByPrimaryKey(Integer id);

	UserInfoData selectByTel(@Param("tel")String tel);

	UserInfoData selectByLogin(@Param("tel")String tel, @Param("pass")String pass);

	int updateByPrimaryKeySelective(UserInfoData record);

	int updateByPrimaryKey(UserInfoData record);

	List<UserInfoData> selectAll();
	
	int updateByTel(UserInfoData record);
}