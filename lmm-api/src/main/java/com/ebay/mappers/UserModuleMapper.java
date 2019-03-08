package com.ebay.mappers;

import com.ebay.models.UserModule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserModuleMapper {
		@Select("select * from user_module where url=#{url} ")
		UserModule findByUrl(String url);

		@Select("select * from user_module")
		List<UserModule> query();

		@Update("update user_module set moduleName=#{moduleName},url=#{url} ,pid=#{pid}  where id=#{id}  ")
		int update(UserModule module);

		@Delete("delete from user_module where id=#{id} ")
		int delete(int id);

		@Insert("insert into user_module(moduleName,url,pid) values (#{moduleName}  ,#{url},#{pid}  ) ")
		int insert(UserModule module);

		@Select("select * from user_module where id=#{id} ")
		UserModule findById(Integer id);

		@Select("select * from user_module where pid=#{pid} ")
		List<UserModule> queryByPid(int pid);

		@Select("select um.* from user_module um " +
				"left join role_module_relation rmr on rmr.moduleId=um.id " +
				"left join user_role ur on ur.id=rmr.roleId " +
				"left join teacher_role_relation trr on trr.roleId=ur.id " +
				"where trr.teacherId=#{teacherId} ")
		List<UserModule> queryByTeacherId(int teacherId);
}
