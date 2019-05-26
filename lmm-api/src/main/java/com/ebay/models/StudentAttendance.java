package com.ebay.models;
import lombok.*;
@Data
public class StudentAttendance {
    private Integer id;
    //学号
    private String studentNo;
    //姓名
    private String name;
    //迟到天数
    private Integer lateNumberOfDays;
    //病假天数
    private Integer sickLeaveNumberOfDays;
    //事假天数
    private Integer affairLeaveNumberOfDays;
}
