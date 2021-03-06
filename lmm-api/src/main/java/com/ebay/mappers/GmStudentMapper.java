package com.ebay.mappers;

import com.ebay.models.GmStudent;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GmStudentMapper {

    List<GmStudent> getStudentList(@Param("classId") Integer classId,
                                   @Param("studentNo") String studentNo,
                                   @Param("isDelete") Integer idDelete,
                                   @Param("name") String name,
                                   @Param("birthday") String birthday,
                                   @Param("start") Integer start,
                                   @Param("size") Integer size);

    GmStudent findById(@Param("id") Integer id);

    int count(@Param("studentNo") String studentNo, @Param("isDelete") Integer isDelete,
              @Param("name") String name, @Param("birthday") String birthday);

    GmStudent findByClassId(@Param("classId") Integer classId, @Param("role") String role);

    int insert(GmStudent gmStudent);

    int update(GmStudent gmStudent);

    @Delete("delete from gm_student where id=#{id}")
    Integer deleteById(@Param("id") Integer id);


    List<GmStudent> query(@Param("classId") Integer classId, @Param("name") String name, @Param("studentNo") String studentNo);

    boolean findByStudentNoWithOutSelf(@Param("studentNo") String studentNo, @Param("id") Integer id);

    @Select("select * from gm_student where studentNo=#{studentNo} and isDelete=0")
    GmStudent findByStudentNo(String studentNo);
}
