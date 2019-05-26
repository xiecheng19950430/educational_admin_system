package com.ebay.models;

import lombok.*;

import java.util.Date;

//综合能力考核
@Data
public class GmStudentAssessment {
    private Integer id;
    //    学号
    private String studentNo;
    //    姓名
    private String name;
    //    操行等第
    private String conduction;
    //    特别表现
    private String performance;
    //    奖惩记载
    private String rewardsPunishments;
    //    班主任评语
    private String comment;

    // 学年学期 2019-1：2019学年上学期
    private String semester;

    private Date createAt;
}
