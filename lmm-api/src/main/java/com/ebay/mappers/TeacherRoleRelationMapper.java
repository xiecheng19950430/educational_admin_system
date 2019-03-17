package com.ebay.mappers;

import com.ebay.models.TeacherRoleRelation;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TeacherRoleRelationMapper {

		@Insert("insert into teacher_role_relation(teacherId,roleId) values (#{teacherId} ,#{roleId}  ) ")
		int insert(TeacherRoleRelation relation);

		@Delete("delete from teacher_role_relation where teacherId=#{teacherId}  ")
		void deleteByTeacherId(int teacherId);

		@Delete("delete from teacher_role_relation where roleId=#{roleId}  ")
		void deleteByRoleId(int roleId);
}
