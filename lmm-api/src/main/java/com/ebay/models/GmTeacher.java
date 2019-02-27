package com.ebay.models;

import lombok.Data;

import java.util.Date;

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
    private String roleName; //系统管理员systemAdmin，教务管理员educationAdmin,教学领导teachLeader，班主任headmaster,教师teacher
    private Date createAt;
    private Date updateAt;
    private Integer isDelete;
}
