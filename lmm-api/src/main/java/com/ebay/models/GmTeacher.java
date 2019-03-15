package com.ebay.models;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
public class GmTeacher {
		private Integer id;
		private String workNo;
		private String name;
		private String loginId;
		private String password;
		private String sex;
		private Date birthday;
		private Date hiredate;
		private String position;
		private String phone;

		private Date createAt;
		private Date updateAt;
		private Integer isDelete;

		//		模块urls
		private List<String> urls;
		//角色代码
		private List<String> roles;

		//默认角色
		//超级管理员为superAdmin,教师teacher(初始默认，直到授权更换)
		private String role; //系统管理员systemAdmin，教务管理员educationAdmin,教学领导teachLeader，班主任headmaster,教师teacher

		//		角色ids 多个角色id用“，”隔开
		private String roleIds;
		//角色名称 多个角色用“，”隔开
		private String roleNames;


		//弃用
		private String roleName; //系统管理员systemAdmin，教务管理员educationAdmin,教学领导teachLeader，班主任headmaster,教师teacher

}
