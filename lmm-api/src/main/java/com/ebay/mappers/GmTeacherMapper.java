package com.ebay.mappers;

import com.ebay.models.GmTeacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GmTeacherMapper {

    List<GmTeacher> getTeacherList(@Param("classId") Integer classId,@Param("workNo") String workNo,@Param("isDelete") Integer isDelete,
                                   @Param("name") String name,@Param("start") Integer start,@Param("size") Integer size);

    GmTeacher findByClassId(@Param("classId") Integer classId,@Param("role") String role);

    GmTeacher findByLoginId(@Param("loginId") String loginId);

    GmTeacher findById(@Param("id") Integer id);

    int count(@Param("workNo") String workNo,@Param("isDelete") Integer isDelete,
              @Param("name") String name);

    int insert(GmTeacher gmTeacher);

    int update(GmTeacher gmTeacher);

    @Delete("delete from gm_teacher where id=#{id}")
    Integer deleteById(@Param("id") Integer id);
}
