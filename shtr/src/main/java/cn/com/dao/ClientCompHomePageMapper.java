package cn.com.dao;

import java.util.List;

import cn.com.bean.ClientCompHomePage;

public interface ClientCompHomePageMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ClientCompHomePage record);

	int insertSelective(ClientCompHomePage record);

	ClientCompHomePage selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ClientCompHomePage record);

	int updateByPrimaryKey(ClientCompHomePage record);

	List<ClientCompHomePage> selectAll();
}