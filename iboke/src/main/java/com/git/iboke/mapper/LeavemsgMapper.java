package com.git.iboke.mapper;

import com.git.iboke.model.Leavemsg;

public interface LeavemsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leavemsg record);

    int insertSelective(Leavemsg record);

    Leavemsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leavemsg record);

    int updateByPrimaryKey(Leavemsg record);
}