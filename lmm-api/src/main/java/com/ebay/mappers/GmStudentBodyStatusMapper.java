package com.ebay.mappers;

import com.ebay.models.GmStudentBodyStatus;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;

@Mapper
public interface GmStudentBodyStatusMapper {
    int insert(GmStudentBodyStatus bodyStatus);

    boolean findByStudentNoWithOutSelf(@Param("studentNo")String studentNo, @Param("id")Integer id);
}
