package com.ebay.mappers;

import com.ebay.models.GmStudentQuality;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;

@Mapper
public interface GmStudentQualityMapper {
    int insert(GmStudentQuality quality);

    boolean findByStudentNoWithOutSelf(@Param("studentNo") String studentNo, @Param("semester")String semester, @Param("id") Integer id);
}
