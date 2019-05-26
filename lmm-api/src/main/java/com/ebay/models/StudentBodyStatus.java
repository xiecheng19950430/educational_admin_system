package com.ebay.models;
import lombok.Data;

@Data
public class StudentBodyStatus {
    private Integer id;
    //学号
    private String studentNo;
    //姓名
    private String name;
    //身高
    private Double height;
    //体重
    private Double weight;
    //左眼视力
    private Double leftEyesight;
    //右眼视力
    private Double rightEyesight;
    //健康状况
    private String health;
}
