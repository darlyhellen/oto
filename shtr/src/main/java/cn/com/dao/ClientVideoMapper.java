package cn.com.dao;

import java.util.List;

import cn.com.bean.ClientVideo;

public interface ClientVideoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ClientVideo record);

	int insertSelective(ClientVideo record);

	ClientVideo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ClientVideo record);

	int updateByPrimaryKey(ClientVideo record);

	List<ClientVideo> selectAll();
}