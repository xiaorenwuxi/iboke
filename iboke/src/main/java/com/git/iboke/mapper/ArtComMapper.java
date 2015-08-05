package com.git.iboke.mapper;

import com.git.iboke.model.ArtCom;

public interface ArtComMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArtCom record);

    int insertSelective(ArtCom record);

    ArtCom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArtCom record);

    int updateByPrimaryKey(ArtCom record);
}