package cn.com.dao;

import java.util.List;

import cn.com.bean.DetailsGoodsPro;

public interface DetailsGoodsProMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(DetailsGoodsPro record);

	int insertSelective(DetailsGoodsPro record);

	DetailsGoodsPro selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(DetailsGoodsPro record);

	int updateByPrimaryKey(DetailsGoodsPro record);

	List<DetailsGoodsPro> selectAll();
}