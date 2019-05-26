package com.ebay.models;

import lombok.Data;

@Data
public class GmStudentQuality {
    private Integer id;
    //学号
    private String studentNo;
    // 学年学期 2019-1：2019学年上学期
    private String semester;

    //姓名
    private String name;
    //道德素质自评
    private String moralQualitySelfAssessment;
    //道德素质互评
    private String moralQualityMutualAssessment;
    //道德素质老师
    private String moralQualityTeacherAssessment;
    //公民素养自评
    private String civicQualitySelfAssessment;
    //公民素养互评
    private String civicQualityMutualAssessment;
    //公民素养老师
    private String civicQualityTeacherAssessment;
    //学习能力自评
    private String learningQualitySelfAssessment;
    //学习能力互评
    private String learningQualityMutualAssessment;
    //学习能力老师
    private String learningQualityTeacherAssessment;
    //交流合作自评
    private String commAndCooperSelfAssessment;
    //交流合作互评
    private String commAndCooperMutualAssessment;
    //交流合作老师
    private String commAndCooperTeacherAssessment;
    //运动健康自评
    private String sportsHealthSelfAssessment;
    //运动健康互评
    private String sportsHealthMutualAssessment;
    //运动健康老师
    private String sportsHealthTeacherAssessment;
    //审美表现自评
    private String aestheticExpressionSelfAssessment;
    //审美表现互评
    private String aestheticExpressionMutualAssessment;
    //审美表现老师
    private String aestheticExpressionTeacherAssessment;
}
