package com.ebay.mappers;

import com.ebay.models.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRoleMapper {

		@Select("select * from user_role where role=#{role} ")
		UserRole findByRole(String role);

		@Select("select * from user_role")
		List<UserRole> query();

		@Update("update user_role set roleName=#{roleName} ,moduleUrls=#{moduleUrls} where role=#{role} ")
		int update(UserRole userRole);

		@Delete("delete from user_role where id=#{id} ")
		int delete(int id);

		@Insert("insert into user_role(role,roleName,moduleUrls) values (#{role} ,#{roleName} ,#{moduleUrls} ) ")
		int insert(UserRole userRole);

		@Select("select * from user_role where id=#{id} ")
		UserRole findById(int id);

		@Select("select * from user_role ur " +
				"left join teacher_role_relation trr on trr.roleId=ur.id " +
				"where trr.teacherId=#{teacherId} ")
		List<UserRole> queryByTeacherId(int teacherId);
}
