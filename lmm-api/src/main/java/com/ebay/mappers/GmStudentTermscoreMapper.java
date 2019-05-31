package com.ebay.mappers;

import com.ebay.models.GmGradeInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GmStudentTermscoreMapper {
    @Select("select * from gm_gradeinfo where studentNo=#{studentNo} and semester=#{semester}")
    GmGradeInfo findByNoAndSemester(@Param("studentNo")String studentNo, @Param("semester")String semester);

    @Select("select g.*,c.scoreType,c.score " +
        "from gm_gradeinfo g " +
        "left join gm_course c on c.courseName=g.courseName " +
        "where studentNo=#{studentNo} and semester=#{semester} " +
        "order by c.scoreType,c.score desc,c.courseName")
    List<GmGradeInfo> query(@Param("studentNo")String studentNo, @Param("semester")String semester);
}
