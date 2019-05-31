package com.ebay.mappers;

import com.ebay.models.GmGradeInfo;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GmGradeInfoMapper {

    List<GmGradeInfo> list(@Param("start")Integer start, @Param("size")Integer size);

    int count();
}
