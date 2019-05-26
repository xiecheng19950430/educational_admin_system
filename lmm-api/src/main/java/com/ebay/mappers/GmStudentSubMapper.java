package com.ebay.mappers;

import com.ebay.models.GmStudentSub;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GmStudentSubMapper {
    int insert(GmStudentSub sub);

    boolean verifyWithOutSelf(@Param("studentNo") String studentNo, @Param("semester") String semester, @Param("id") Integer id);

    @Select("select * from gm_student_sub where studentNo=#{studentNo} and semester=#{semester}")
    GmStudentSub findByNoAndSemester(@Param("studentNo") String studentNo, @Param("semester") String semester);

    int update(GmStudentSub sub);
}
