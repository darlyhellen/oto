package cn.com.dao;

import java.util.List;

import cn.com.bean.DetailsGoodsShow;

public interface DetailsGoodsShowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DetailsGoodsShow record);

    int insertSelective(DetailsGoodsShow record);

    DetailsGoodsShow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DetailsGoodsShow record);

    int updateByPrimaryKey(DetailsGoodsShow record);
    
    List<DetailsGoodsShow> selectAll();
}