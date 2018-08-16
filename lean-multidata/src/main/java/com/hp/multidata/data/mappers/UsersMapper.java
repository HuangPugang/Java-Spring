package com.hp.multidata.data.mappers;

import com.hp.multidata.data.models.Users;
import org.apache.ibatis.annotations.Mapper;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}