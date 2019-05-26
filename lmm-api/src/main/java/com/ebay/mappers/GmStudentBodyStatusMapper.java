package com.ebay.mappers;

import com.ebay.models.GmStudentBodyStatus;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GmStudentBodyStatusMapper {
    int insert(GmStudentBodyStatus bodyStatus);

    boolean findByStudentNoWithOutSelf(@Param("studentNo") String studentNo, @Param("semester") String semester, @Param("id") Integer id);

    @Select("select * from gm_student_bodystatus where studentNo=#{studentNo} and semester=#{semester}")
    GmStudentBodyStatus findByNoAndSemester(@Param("studentNo")String studentNo, @Param("semester")String semester);

    int update(GmStudentBodyStatus bodyStatuses);
}
