package cn.com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.bean.Main_Flower;

public interface Main_FlowerMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Main_Flower record);

	int insertSelective(Main_Flower record);

	Main_Flower selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Main_Flower record);

	int updateByPrimaryKey(Main_Flower record);

	List<Main_Flower> selectFromPageAndGroup(int pagestart, int pageend,
			@Param("goup") String goup);
}