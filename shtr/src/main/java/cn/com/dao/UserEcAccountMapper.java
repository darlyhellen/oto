package cn.com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.bean.UserEcAccount;

public interface UserEcAccountMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserEcAccount record);

	int insertSelective(UserEcAccount record);

	UserEcAccount selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserEcAccount record);

	int updateByPrimaryKey(UserEcAccount record);

	List<UserEcAccount> selectAll();

	UserEcAccount selectByTel(@Param("tel") String tel);
}