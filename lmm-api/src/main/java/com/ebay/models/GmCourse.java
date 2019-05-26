package com.ebay.models;

import lombok.Data;

import java.util.Date;

@Data
public class GmCourse {
    private Integer id;
    private String courseNo;
    private Integer studentId;
    private String courseName;
    private String openGrade;
    private String openTerm;
    private Integer isDelete;
    private Integer type;
    private Integer status;
    //分值类型  1：非百分制 2:百分制 3：等级制
    private Integer scoreType;
    //分值
    private Integer score;
    private String description;
    private Date classAt;
    private Date createAt;
    private Date updateAt;
}
