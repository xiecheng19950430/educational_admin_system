package com.ebay.models;

import lombok.*;

import java.util.Date;

@Data
public class GmGradeInfo {
    private Integer id;
    private Integer teacherId;
    private Integer classId;
    //成绩编号
    private String gradeNo;
    private String courseName;
    private String studentNo;
    private String name;
    //平时成绩
    private Integer grade_ordinary;
    //期中成绩
    private Integer grade_mid;
    //期末成绩
    private Integer grade_final;
    //总评
    private Integer grade_all;
    //学期成绩
    private Integer grade_semester;
    //学年成绩
    private Integer grade_year;
    private Integer isPass;
    private Date createAt;
    private Date updateAt;
    //学年
    private Integer schoolYear;
    //学期
    private String term;
    private Integer isDelete;

    private Integer scoreType;
    private Integer score;
}
