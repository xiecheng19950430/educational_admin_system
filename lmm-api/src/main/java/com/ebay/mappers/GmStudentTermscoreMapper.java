package com.ebay.mappers;

import com.ebay.models.GmGradeInfo;
import org.apache.ibatis.annotations.Select;

public interface GmStudentTermscoreMapper {
    @Select("select * from gm_gradeinfo where studentNo=#{studentNo} and semester=#{semester}")
    GmGradeInfo findByNoAndSemester(String studentNo, String semester);
}
