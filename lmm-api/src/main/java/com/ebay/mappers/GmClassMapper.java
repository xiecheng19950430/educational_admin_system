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

		@Select("select max(cast(classNo as int))  from gm_class")
		Integer getLatestClassNo();
}
