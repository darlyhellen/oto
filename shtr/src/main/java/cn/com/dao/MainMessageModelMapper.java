package cn.com.dao;

import java.util.List;

import cn.com.bean.MainMessageModel;

public interface MainMessageModelMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(MainMessageModel record);

	int insertSelective(MainMessageModel record);

	MainMessageModel selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MainMessageModel record);

	int updateByPrimaryKey(MainMessageModel record);

	List<MainMessageModel> selectAll();
}