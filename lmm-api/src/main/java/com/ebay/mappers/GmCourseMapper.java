package com.ebay.mappers;

import com.ebay.models.GmCourse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GmCourseMapper {

    List<GmCourse> getCourseList(@Param("openGrade") Integer openGrade, @Param("openTerm") String openTerm,
                                 @Param("isDelete") Integer isDelete, @Param("courseName") String courseName,
                                 @Param("start") Integer start, @Param("size") Integer size);

    int count(@Param("openGrade") Integer openGrade,@Param("openTerm") String openTerm,
              @Param("isDelete") Integer isDelete, @Param("courseName") String courseName);

    int update(GmCourse gmCourse);

    GmCourse findById(@Param("id") Integer id);

    @Delete("delete from gm_course where id=#{id}")
    Integer deleteById(@Param("id") Integer id);
}
