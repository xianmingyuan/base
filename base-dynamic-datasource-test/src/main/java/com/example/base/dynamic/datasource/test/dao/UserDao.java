package com.example.base.dynamic.datasource.test.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xianmingyuan
 */
@Mapper
public interface UserDao {

    @Insert("insert into user (id, name) values (#{id}, #{name})")
    Integer insert(@Param("id") Integer id, @Param("name") String name);

}
