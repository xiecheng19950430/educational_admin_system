package com.ebay.models;

import lombok.*;

import java.util.List;

@Data
public class GmTeacherAuth {
		private Integer id;
		//		教师id
		private Integer teacherId;
		//工号
		private String workNo;
		//		姓名
		private String name;
		//		角色ids 多个角色id用“，”隔开
		private String roleIds;
		//角色名称 多个角色用“，”隔开
		private String roleNames;

		//		模块urls
		private List<String> urls;
		//角色代码
		private List<String> roles;
}
