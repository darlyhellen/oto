package cn.com.dao;

import java.util.List;

import cn.com.bean.Action_Theme;

public interface Action_ThemeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Action_Theme record);

	int insertSelective(Action_Theme record);

	Action_Theme selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Action_Theme record);

	int updateByPrimaryKey(Action_Theme record);

	List<Action_Theme> selectAll();

	List<Action_Theme> selectPage(int pagestart, int pageend);
}