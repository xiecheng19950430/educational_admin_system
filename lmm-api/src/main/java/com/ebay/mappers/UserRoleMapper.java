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

		@Update("update user_role set roleName=#{roleName} ,moduleIds=#{moduleIds} where role=#{role} ")
		int update(UserRole userRole);

		@Delete("delete from user_role where id=#{id} ")
		int delete(int id);

		@Insert("insert into user_role(role,roleName,moduleIds) values (#{role} ,#{roleName} ,#{moduleIds} ) ")
		int insert(UserRole userRole);
}
