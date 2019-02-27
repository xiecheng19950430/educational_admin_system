package com.ebay.mappers;

import com.ebay.models.GmCourse;
import com.ebay.models.GmGradeInfo;
import com.ebay.models.GmTeacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExcelMapper {
    /**
     * 批量导入课程数据
     * @param courseList
     */
    int batchCourseInsert(List<GmCourse> courseList);

    /**
     * 批量导入成绩数据
     * @param gradeList
     */
    int batchGradeInsert(List<GmGradeInfo> gradeList);

    /**
     * 批量导入教师数据
     * @param teacherList
     */
    int batchTeacherInsert(List<GmTeacher> teacherList);
}
