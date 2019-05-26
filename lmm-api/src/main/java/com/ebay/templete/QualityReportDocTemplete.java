package com.ebay.templete;

import com.ebay.common.DateUtil;
import com.ebay.models.*;
import com.ebay.utils.DocxUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.ObjectUtils;

import java.util.*;

public class QualityReportDocTemplete {

    public static XWPFDocument temp(XWPFDocument document, Map dataMap) {

        //计算通用信息 学年学期，假期时间，下学期开学时间，打印日期
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
//        int day = calendar.get(Calendar.DATE);
        Map<String, String> common = new HashMap<>();
        //标题
        common.put("$schoolYear", year + "-" + (year + 1));//学年
        common.put("$semester", month < 8 ? "1" : "2");//学期
        //假期
        common.put("$holidayS", "2019年01月23日");
        common.put("$holidayE", "2019年02月21日");
        //下学期报到时间
        common.put("$startSchool", "2019年2月22日09时");
        //打印日期
        String printDate = DateUtil.format(new Date(), "yyyy月MM月dd日");
        common.put("$printDate", printDate);
        DocxUtil.searchAndReplace(document, common);//替换模板中的对应变量。

        GmStudent student = (GmStudent) dataMap.get("student");
//        GmStudentSub sub = (GmStudentSub) dataMap.get("sub");
        GmStudentBodyStatus bodyStatus = (GmStudentBodyStatus) dataMap.get("bodystatus");
        //查找学生表 匹配学生基本信息和身体状况
        Map<String, String> stu = new HashMap<>();
        if (!ObjectUtils.isEmpty(student)) {
            stu.put("$stuName", student.getName());
            stu.put("$stuId", student.getStudentNo());
            stu.put("$stuClassName", student.getClassName());
        }
        if (!ObjectUtils.isEmpty(bodyStatus)) {
            stu.put("$stuHeight", bodyStatus.getHeight());
            stu.put("$stuWeight", bodyStatus.getWeight());
            stu.put("$leftVision", bodyStatus.getLeftVision());
            stu.put("$rightVision", bodyStatus.getRightVision());
            stu.put("$healthStatus", bodyStatus.getHealthStatus());
        }
        DocxUtil.searchAndReplace(document, stu);//替换模板中的对应变量。

        GmStudentQuality studentQuality = (GmStudentQuality) dataMap.get("quality");
        if (!ObjectUtils.isEmpty(studentQuality)) {
            //查找综合素质评价 匹配综合素质评价
            Map<String, String> quality = new HashMap<>();
            quality.put("$s_1", studentQuality.getMoralQualitySelfAssessment());
            quality.put("$c_1", studentQuality.getMoralQualityMutualAssessment());
            quality.put("$t_1", studentQuality.getMoralQualityTeacherAssessment());
            quality.put("$s_2", studentQuality.getCivicQualitySelfAssessment());
            quality.put("$c_2", studentQuality.getCivicQualityMutualAssessment());
            quality.put("$t_2", studentQuality.getCivicQualityTeacherAssessment());
            quality.put("$s_3", studentQuality.getLearningQualitySelfAssessment());
            quality.put("$c_3", studentQuality.getLearningQualityMutualAssessment());
            quality.put("$t_3", studentQuality.getLearningQualityTeacherAssessment());
            quality.put("$s_4", studentQuality.getCommAndCooperSelfAssessment());
            quality.put("$c_4", studentQuality.getCommAndCooperMutualAssessment());
            quality.put("$t_4", studentQuality.getCommAndCooperTeacherAssessment());
            quality.put("$s_5", studentQuality.getSportsHealthSelfAssessment());
            quality.put("$c_5", studentQuality.getSportsHealthMutualAssessment());
            quality.put("$t_5", studentQuality.getSportsHealthTeacherAssessment());
            quality.put("$s_6", studentQuality.getAestheticExpressionSelfAssessment());
            quality.put("$c_6", studentQuality.getAestheticExpressionMutualAssessment());
            quality.put("$t_6", studentQuality.getAestheticExpressionTeacherAssessment());
            DocxUtil.searchAndReplace(document, quality);//替换模板中的对应变量。
        }


        //查找成绩表 匹配sub开头的 //循环匹配
        Map<String, String> score = new HashMap<>();
        String az = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 1; i < 17; i++) {
            String p = az.substring(i - 1, i);
            score.put("$sub" + i, "xxx");
            score.put("$" + p + 1, "xxx");
            score.put("$" + p + 2, "xxx");
            score.put("$" + p + 3, "xxx");
            score.put("$" + p + 4, "xxx");
            score.put("$" + p + 5, "xxx");
        }
//            score.put("$sub1", "xxx");
//            score.put("$A1", "xxx");
//            score.put("$A2", "xxx");
//            score.put("$A3", "xxx");
//            score.put("$A4", "xxx");
//            score.put("$A5", "xxx");

        score.put("$stuNonPercentageCourse", "xxx");
        DocxUtil.searchAndReplace(document, score);//替换模板中的对应变量。

        GmStudentAssessment assessment = (GmStudentAssessment) dataMap.get("assessment");
        if (!ObjectUtils.isEmpty(assessment)) {
            //综合能力考核
            Map<String, String> ass = new HashMap<>();
            ass.put("$conductionLevel", assessment.getConduction());
            ass.put("$specialPerformance", assessment.getPerformance());
            ass.put("$classTeacherComment", assessment.getComment());
            ass.put("$rewardsPunishments", assessment.getRewardsPunishments());
            DocxUtil.searchAndReplace(document, ass);//替换模板中的对应变量。
        }

        GmStudentAttendance attendance = (GmStudentAttendance) dataMap.get("attendance");
        if (!ObjectUtils.isEmpty(attendance)) {
            //查找考勤表 匹配考勤信息
            Map<String, String> att = new HashMap<>();
            att.put("本学期上课$allDay", "本学期上课" + 0);
            att.put("该生实际上课$att", "该生实际上课" + 0);
            att.put("$beL", attendance.getLateNumberOfDays().toString());
            att.put("$sickL", attendance.getSickLeaveNumberOfDays().toString());
            att.put("$l", attendance.getAffairLeaveNumberOfDays().toString());
            DocxUtil.searchAndReplace(document, att);//替换模板中的对应变量。
        }


        return document;
    }
}
