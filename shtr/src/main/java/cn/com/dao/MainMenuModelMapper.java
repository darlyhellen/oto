package cn.com.dao;

import java.util.List;

import cn.com.bean.MainMenuModel;

public interface MainMenuModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MainMenuModel record);

    int insertSelective(MainMenuModel record);

    MainMenuModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MainMenuModel record);

    int updateByPrimaryKey(MainMenuModel record);
    
    List<MainMenuModel> selectAll();
}