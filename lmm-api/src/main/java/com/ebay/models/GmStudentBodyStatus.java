package com.ebay.models;

import lombok.*;

import java.util.Date;
//身体状况
@Data
public class GmStudentBodyStatus {
    private Integer id;
    //    学号
    private String studentNo;
    //    姓名
    private String name;
    //    身高(厘米)
    private String height;
    //    体重(千克)
    private String weight;
    //    左眼视力
    private String leftVision;
    //    右眼视力
    private String rightVision;
    //    健康状况
    private String healthStatus;

    // 学年学期 2019-1：2019学年上学期
    private String semester;

    private Date createAt;
}
