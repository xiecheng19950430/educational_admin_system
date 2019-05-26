package com.ebay.mappers;

import com.ebay.models.GmStudentAssessment;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;

@Mapper
public interface GmStudentAssessmentMapper {
    int insert(GmStudentAssessment assessment);

    boolean verifyWithOutSelf(@Param("studentNo")String studentNo, @Param("semester")String semester, @Param("id")Integer id);
}
