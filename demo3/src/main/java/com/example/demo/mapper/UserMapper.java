package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.DO.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
    @Select("SELECT * FROM t_user WHERE username = #{username}")
    UserDO selectByUsername(String username);
}
