package com.ebay.templete;

import com.ebay.common.DateUtil;
import com.ebay.utils.DocxUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.*;

public class QualityReportDocTemplete {

    public static XWPFDocument temp(XWPFDocument document, Map map) {

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


        //查找学生表 匹配学生基本信息和身体状况
        Map<String, String> stu = new HashMap<>();
        stu.put("$stuName", "xxx");
        stu.put("$stuId", "xxx");
        stu.put("$stuClassName", "xxx");
        stu.put("$stuHeight", "xxx");
        stu.put("$stuWeight", "xxx");
        stu.put("$leftVision", "xxx");
        stu.put("$rightVision", "xxx");
        stu.put("$healthStatus", "xxx");
        DocxUtil.searchAndReplace(document, stu);//替换模板中的对应变量。

        //查找综合素质评价 匹配综合素质评价
        Map<String, String> quality = new HashMap<>();
        for (int i = 1; i < 7; i++) {
            quality.put("$s_" + i, "xxx");
            quality.put("$c_" + i, "xxx");
            quality.put("$t_" + i, "xxx");
        }
//            quality.put("$s_1", "xxx");
//            quality.put("$c_1", "xxx");
//            quality.put("$t_1", "xxx");
//            quality.put("$s_2", "xxx");
//            quality.put("$c_2", "xxx");
//            quality.put("$t_2", "xxx");
//            quality.put("$s_3", "xxx");
//            quality.put("$c_3", "xxx");
//            quality.put("$t_3", "xxx");
//            quality.put("$s_4", "xxx");
//            quality.put("$c_4", "xxx");
//            quality.put("$t_4", "xxx");
//            quality.put("$s_5", "xxx");
//            quality.put("$c_5", "xxx");
//            quality.put("$t_5", "xxx");
//            quality.put("$s_6", "xxx");
//            quality.put("$c_6", "xxx");
//            quality.put("$t_6", "xxx");
        DocxUtil.searchAndReplace(document, quality);//替换模板中的对应变量。

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

        //综合能力考核
        Map<String, String> ass = new HashMap<>();
        ass.put("$conductionLevel", "xxx");
        ass.put("$specialPerformance", "xxx");
        ass.put("$classTeacherComment", "xxx");
        ass.put("$rewardsPunishments", "xxx");
        DocxUtil.searchAndReplace(document, ass);//替换模板中的对应变量。

        //查找考勤表 匹配考勤信息
        Map<String, String> att = new HashMap<>();
        att.put("本学期上课$allDay", "xxx");
        att.put("该生实际上课$att", "xxx");
        att.put("$beL", "xxx");
        att.put("$sickL", "xxx");
        att.put("$l", "xxx");
        DocxUtil.searchAndReplace(document, att);//替换模板中的对应变量。

        return document;
    }
}
