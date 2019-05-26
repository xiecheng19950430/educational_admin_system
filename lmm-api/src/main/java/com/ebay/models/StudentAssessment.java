package com.ebay.models;

import lombok.Data;

@Data
public class StudentAssessment {
    private Integer id;
    //学号
    private String studentNo;
    //姓名
    private String name;
    //操行等第
    private String conductOrderMerit;
    //特别表现
    private String specialPerformance;
    //奖惩记载
    private String rewardsAndPunishRecord;
    //班主任评语
    private String headTeacherComments;
}
