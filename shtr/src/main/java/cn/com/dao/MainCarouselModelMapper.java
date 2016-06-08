package cn.com.dao;

import java.util.List;

import cn.com.bean.MainCarouselModel;

public interface MainCarouselModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MainCarouselModel record);

    int insertSelective(MainCarouselModel record);

    MainCarouselModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MainCarouselModel record);

    int updateByPrimaryKey(MainCarouselModel record);
    
    List<MainCarouselModel> selectAll();
}