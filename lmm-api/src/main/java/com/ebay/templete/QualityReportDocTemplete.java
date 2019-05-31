package com.ebay.templete;

import com.ebay.common.DateUtil;
import com.ebay.models.*;
import com.ebay.utils.DocxUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

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
        if (ObjectUtils.isEmpty(student)) student = new GmStudent();
        stu.put("$stuName", student.getName());
        stu.put("$stuId", student.getStudentNo());
        stu.put("$stuClassName", student.getClassName());
        if (ObjectUtils.isEmpty(bodyStatus)) bodyStatus = new GmStudentBodyStatus();
        stu.put("$stuHeight", bodyStatus.getHeight());
        stu.put("$stuWeight", bodyStatus.getWeight());
        stu.put("$leftVision", bodyStatus.getLeftVision());
        stu.put("$rightVision", bodyStatus.getRightVision());
        stu.put("$healthStatus", bodyStatus.getHealthStatus());
        DocxUtil.searchAndReplace(document, stu);//替换模板中的对应变量。

        GmStudentQuality studentQuality = (GmStudentQuality) dataMap.get("quality");
        //查找综合素质评价 匹配综合素质评价
        Map<String, String> quality = new HashMap<>();
        if (ObjectUtils.isEmpty(studentQuality)) studentQuality = new GmStudentQuality();
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

        List<GmGradeInfo> termscore = (List<GmGradeInfo>) dataMap.get("termscore");
        //查找成绩表 匹配sub开头的 //循环匹配
        Map<String, String> score = new HashMap<>();
        if (CollectionUtils.isEmpty(termscore)) termscore = new ArrayList<>();

        String az = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 1; i < 17; i++) {
            String p = az.substring(i - 1, i);
            score.put("$sub" + i, "");
            score.put("$" + p + 1, "");
            score.put("$" + p + 2, "");
            score.put("$" + p + 3, "");
            score.put("$" + p + 4, "");
            score.put("$" + p + 5, "");
            if (termscore.size() > i - 1) {
                GmGradeInfo info = termscore.get(i - 1);
                score.put("$sub" + i, info.getCourseName());
                if (info.getGrade_ordinary() != null)
                    score.put("$" + p + 1, info.getGrade_ordinary().toString());
                else
                    score.put("$" + p + 1, "");
                if (info.getGrade_mid() != null)
                    score.put("$" + p + 2, info.getGrade_mid().toString());
                else
                    score.put("$" + p + 2, "");
                if (info.getGrade_final() != null)
                    score.put("$" + p + 3, info.getGrade_final().toString());
                else
                    score.put("$" + p + 3, "");
                if (info.getGrade_semester() != null)
                    score.put("$" + p + 4, info.getGrade_semester().toString());
                else
                    score.put("$" + p + 4, "");
                if (info.getGrade_year() != null)
                    score.put("$" + p + 5, info.getGrade_year().toString());
                else
                    score.put("$" + p + 5, "");
            }
        }
        List<GmGradeInfo> infos = termscore.stream().filter(info -> info.getScoreType() == 1).collect(Collectors.toList());
        StringBuilder nonss = new StringBuilder();
        for (GmGradeInfo info : infos) {
            nonss.append(info.getCourseName()).append(":").append(info.getScore()).append(";");
        }
        score.put("$stuNonPercentageCourse", nonss.toString());
        DocxUtil.searchAndReplace(document, score);//替换模板中的对应变量。

        GmStudentAssessment assessment = (GmStudentAssessment) dataMap.get("assessment");
        //综合能力考核
        Map<String, String> ass = new HashMap<>();
        if (ObjectUtils.isEmpty(assessment)) assessment = new GmStudentAssessment();
        ass.put("$conductionLevel", assessment.getConduction());
        ass.put("$specialPerformance", assessment.getPerformance());
        ass.put("$classTeacherComment", assessment.getComment());
        ass.put("$rewardsPunishments", assessment.getRewardsPunishments());
        DocxUtil.searchAndReplace(document, ass);//替换模板中的对应变量。

        GmStudentAttendance attendance = (GmStudentAttendance) dataMap.get("attendance");
        //查找考勤表 匹配考勤信息
        Map<String, String> att = new HashMap<>();
        if (ObjectUtils.isEmpty(attendance)) attendance = new GmStudentAttendance();
        att.put("本学期上课$allDay", "本学期上课" + 0);
        att.put("该生实际上课$att", "该生实际上课" + 0);
        String bel = "";
        if (attendance.getLateNumberOfDays() != null) bel = attendance.getLateNumberOfDays().toString();
        att.put("$beL", bel);
        String sickL = "";
        if (attendance.getSickLeaveNumberOfDays() != null) sickL = attendance.getSickLeaveNumberOfDays().toString();
        att.put("$sickL", sickL);
        String l = "";
        if (attendance.getAffairLeaveNumberOfDays() != null) sickL = attendance.getAffairLeaveNumberOfDays().toString();
        att.put("$l", l);
        DocxUtil.searchAndReplace(document, att);//替换模板中的对应变量。
        DocxUtil.searchAndReplace(document, att);//替换模板中的对应变量。

        return document;
    }
}
