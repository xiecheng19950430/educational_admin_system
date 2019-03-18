package com.ebay.models;

import lombok.Data;

import java.util.Date;

@Data
public class GmClass {
    private Integer id;
    //班主任id
    private Integer teacherId;
    //班主任名字
    private String teacherName;
    //班级编号
    private String classNo;
    //年级
    private String grade;
    //班级名称
    private String name;
    //年份
    private Integer year;
    //班级人数
    private Integer amount;
    //班级状态
    private Integer status; //0:在校；1:已毕业
    private Date createAt;
    private Date updateAt;
    private Integer isDelete;
}
