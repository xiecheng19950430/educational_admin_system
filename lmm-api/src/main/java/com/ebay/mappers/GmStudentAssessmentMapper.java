package com.ebay.mappers;

import com.ebay.models.GmStudentAssessment;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GmStudentAssessmentMapper {
    int insert(GmStudentAssessment assessment);

    boolean verifyWithOutSelf(@Param("studentNo") String studentNo, @Param("semester") String semester, @Param("id") Integer id);

    @Select("select * from gm_student_assessment where studentNo=#{studentNo} and semester=#{semester}")
    GmStudentAssessment findByNoAndSemester(@Param("studentNo") String studentNo, @Param("semester") String semester);

    int update(GmStudentAssessment assessment);
}
