package com.ebay.mappers;

import com.ebay.models.GmClassHasGmTeacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GmClassHasGmTeacherMapper {
		@Delete("delete from gm_class_has_gm_teacher where gm_teacher_id=#{teacherId} ")
		void deleteByTeacherId(int teacherId);

		@Select("select * from gm_class_has_gm_teacher where gm_teacher_id=#{teacherId} ")
		List<GmClassHasGmTeacher> queryByTeacherId(int teacherId);

		@Delete("delete from gm_class_has_gm_teacher where id=#{id} ")
		void delete(int id);

		@Insert("insert into gm_class_has_gm_teacher(gm_teacher_id,gm_class_id) values (#{teacherId} ,#{classId} )")
		void save(@Param("teacherId") int teacherId, @Param("classId") int classId);
}
