package com.ebay.models;

import lombok.*;

import java.util.Date;

//学生表附录
@Data
public class GmStudentSub {
    private Integer id;
    //    学籍辅助号
    private String studentNo;
    //    班级
    private String className;
    //    学生姓名
    private String name;
    //    性别
    private String sex;
    //    身份证号
    private String idCard;
    //    出生日期
    private String birthday;
    //    入校日期
    private String inSchoolTime;
    //    毕业日期
    private String outSchoolTime;
    //    入校分数
    private String inScore;
    //    来源学校
    private String sourceSchool;
    //    政治面貌
    private String political;
    //    父亲姓名
    private String father;
    //    父亲联系电话
    private String fatherPhone;
    //    母亲姓名
    private String mother;
    //    母亲联系电话
    private String motherPhone;
    //    其他联系人姓名
    private String other;
    //    其他联系人电话
    private String otherPhone;
    //    状态
    private String status;

    // 学年学期 2019-1：2019学年上学期
    private String semester;
    private Date createAt;
    private Date updateAt;

}
