package com.ebay.models;

import lombok.Data;

import java.util.Date;

@Data
public class GmGradeInfo {
    private Integer id;
    private Integer teacherId;
    private Integer classId;
    private String gradeNo;
    private String courseName;
    private String studentNo;
    private String name;
    private Integer grade_ordinary;
    private Integer grade_mid;
    private Integer grade_final;
    private Integer grade_all;
    private Integer grade_semester;
    private Integer grade_year;
    private Integer isPass;
    private Date createAt;
    private Date updateAt;
    private Integer schoolYear;
    private String term;
    private Integer isDelete;
}
