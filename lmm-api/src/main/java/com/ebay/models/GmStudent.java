package com.ebay.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GmStudent {
    private Integer id;
    private Integer classId;
    private String name;
    private String studentNo;
    private String sex;
    private Integer isDelete;
    private Date birthday;
    private Date createAt;
    private Date updateAt;

    private List<String> urls;
    private List<String> roles;

    //超级管理员为superAdmin，其他为角色id（多个用’，'隔开）
    private String role; //系统管理员systemAdmin，教务管理员educationAdmin,教学领导teachLeader，班主任headmaster,教师teacher，学生student
    //弃用
    private String roleName; //系统管理员systemAdmin，教务管理员educationAdmin,教学领导teachLeader，班主任headmaster,教师teacher，学生student
}
