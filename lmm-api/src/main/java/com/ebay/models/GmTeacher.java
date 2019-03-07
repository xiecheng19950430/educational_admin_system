package com.ebay.models;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
public class GmTeacher {
		private Integer id;
		private Integer classId;
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

		private List<String> urls;
		private List<String> roles;

		//弃用
		private String roleName; //系统管理员systemAdmin，教务管理员educationAdmin,教学领导teachLeader，班主任headmaster,教师teacher
		private String role; //系统管理员systemAdmin，教务管理员educationAdmin,教学领导teachLeader，班主任headmaster,教师teacher

}
