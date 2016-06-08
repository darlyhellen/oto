package cn.com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.bean.Cart;

public interface CartMapper {
    int insert(Cart record);

    int insertSelective(Cart record);
    
    List<Cart> selectAll();
    
    Cart findById(@Param("id") Integer id);
}