package cn.com.dao;

import java.util.List;

import cn.com.bean.ClientTheme;


public interface ClientThemeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ClientTheme record);

	int insertSelective(ClientTheme record);

	ClientTheme selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ClientTheme record);

	int updateByPrimaryKey(ClientTheme record);

	List<ClientTheme> selectAll();
}