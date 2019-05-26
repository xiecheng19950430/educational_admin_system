package com.ebay.mappers;

import com.ebay.models.GmStudentQuality;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GmStudentQualityMapper {
    int insert(GmStudentQuality quality);

    boolean findByStudentNoWithOutSelf(@Param("studentNo") String studentNo, @Param("semester")String semester, @Param("id") Integer id);

    @Select("select * from gm_student_quality where studentNo=#{studentNo} and semester=#{semester}")
    GmStudentQuality findByNoAndSemester(String studentNo, String semester);

    int update(GmStudentQuality quality);

}
