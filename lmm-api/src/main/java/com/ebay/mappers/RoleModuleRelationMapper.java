package com.ebay.mappers;

import com.ebay.models.RoleModuleRelation;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RoleModuleRelationMapper {
		@Delete("delete from role_module_relation where moduleId=#{moduleId} ")
		void deleteByModuleId(int moduleId);

		@Delete("delete from role_module_relation where roleId=#{roleId} ")
		void deleteByRoleId(int roleId);

		@Insert("insert into role_module_relation(roleId,moduleId) values (#{roleId} ,#{moduleId} ) ")
		void insert(RoleModuleRelation relation);
}
