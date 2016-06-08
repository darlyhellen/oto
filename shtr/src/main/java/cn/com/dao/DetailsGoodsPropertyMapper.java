package cn.com.dao;

import java.util.List;

import cn.com.bean.DetailsGoodsProperty;

public interface DetailsGoodsPropertyMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(DetailsGoodsProperty record);

	int insertSelective(DetailsGoodsProperty record);

	DetailsGoodsProperty selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(DetailsGoodsProperty record);

	int updateByPrimaryKey(DetailsGoodsProperty record);

	List<DetailsGoodsProperty> selectAll();
}