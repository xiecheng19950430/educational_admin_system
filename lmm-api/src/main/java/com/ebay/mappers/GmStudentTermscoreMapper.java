package com.ebay.mappers;

import com.ebay.models.GmGradeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GmStudentTermscoreMapper {
    @Select("select * from gm_gradeinfo where studentNo=#{studentNo} and semester=#{semester}")
    GmGradeInfo findByNoAndSemester(String studentNo, String semester);

    @Select("select g.*,c.scoreType,c.score " +
        "from gm_gradeinfo g " +
        "left join gm_coruse c on c.courseName=g.courseName " +
        "where studentNo=#{studentNo} and semester=#{semester} " +
        "order by c.scoreType,c.score desc,c.courseName")
    List<GmGradeInfo> query(String studentNo, String semester);
}
