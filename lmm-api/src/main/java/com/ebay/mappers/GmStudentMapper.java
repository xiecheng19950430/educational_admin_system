package com.ebay.mappers;

import com.ebay.models.GmStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GmStudentMapper {

    GmStudent getStudentList(@Param("classId") Integer classId,
                             @Param("studentNo") String studentNo,
                             @Param("isDelete") Integer idDelete,
                             @Param("name") String name,
                             @Param("birthday") String birthday,
                             @Param("start") Integer start,
                             @Param("size") Integer size);

    int insert(GmStudent gmStudent);

    int update(GmStudent gmStudent);


}
