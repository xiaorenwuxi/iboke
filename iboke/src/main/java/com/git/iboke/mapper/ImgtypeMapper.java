package com.git.iboke.mapper;

import com.git.iboke.model.Imgtype;

public interface ImgtypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Imgtype record);

    int insertSelective(Imgtype record);

    Imgtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Imgtype record);

    int updateByPrimaryKey(Imgtype record);
}