package com.ebay.mappers;

import com.ebay.models.GmClass;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface GmClassMapper {

    List<GmClass> getClassList(@Param("name") String name, @Param("isDelete") Integer isDelete, @Param("status") Integer status,
                               @Param("year") Integer year, @Param("start") Integer start, @Param("size") Integer size);

    GmClass findById(@Param("id") Integer id);

    int count(@Param("name") String name, @Param("isDelete") Integer isDelete,
              @Param("status") Integer status, @Param("year") Integer year);

    int insert(GmClass gmClass);

    int update(GmClass gmClass);

    @Delete("delete from gm_class where id=#{id}")
    Integer deleteById(@Param("id") Integer id);

    @Delete("delete from gm_class where year=#{year}")
    void deleteByYear(int year);

    Integer getLatestClassNo(@Param("year")Integer year);

    @Select("select gc.name  from gm_class gc left join gm_class_has_gm_teacher cht on cht.gm_class_id=gc.id where cht.gm_teacher_id=#{teacherId} ")
    List<String> queryNameByTeacherId(int teacherId);

    boolean findByNameWithOutSelf(@Param("id") Integer id, @Param("name") String name);

    @Select("select * from gm_class where name=#{name} and isDelete=0")
    GmClass findByName(String name);
}
