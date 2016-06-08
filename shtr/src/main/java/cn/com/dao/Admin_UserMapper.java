package cn.com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.bean.Admin_User;

public interface Admin_UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin_User record);

    int insertSelective(Admin_User record);

    Admin_User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin_User record);

    int updateByPrimaryKey(Admin_User record);
    
    Admin_User selectByLogin(@Param("name")String name, @Param("pass")String pass);
    
    List<Admin_User> selectAll();
}