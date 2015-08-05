package com.git.iboke.mapper;

import com.git.iboke.model.ArtType;

public interface ArtTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArtType record);

    int insertSelective(ArtType record);

    ArtType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArtType record);

    int updateByPrimaryKey(ArtType record);
}