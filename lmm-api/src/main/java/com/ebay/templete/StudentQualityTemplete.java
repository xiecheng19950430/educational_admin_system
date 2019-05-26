package com.ebay.templete;

import com.ebay.models.GmStudentQuality;
import com.ebay.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class StudentQualityTemplete {
    public static List<GmStudentQuality> temp(MultipartFile file) {
        Workbook workbook = ExcelUtil.getWorkBook(file);
        List<GmStudentQuality> qualities = new ArrayList<>();
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            if (rows > 0) {
                for (int i = 1; i <= rows; i++) {
                    Row row = sheet.getRow(i);
                    GmStudentQuality quality = new GmStudentQuality();
                    String studentNo = ExcelUtil.getCellValue(row.getCell(0));
                    quality.setStudentNo(ExcelUtil.formatDouble(studentNo));
                    quality.setName(ExcelUtil.getCellValue(row.getCell(1)));
                    quality.setMoralQualitySelfAssessment(ExcelUtil.getCellValue(row.getCell(2)));
                    quality.setMoralQualityMutualAssessment(ExcelUtil.getCellValue(row.getCell(3)));
                    quality.setMoralQualityTeacherAssessment(ExcelUtil.getCellValue(row.getCell(4)));
                    quality.setCivicQualitySelfAssessment(ExcelUtil.getCellValue(row.getCell(5)));
                    quality.setCivicQualityMutualAssessment(ExcelUtil.getCellValue(row.getCell(6)));
                    quality.setCivicQualityTeacherAssessment(ExcelUtil.getCellValue(row.getCell(7)));
                    quality.setLearningQualitySelfAssessment(ExcelUtil.getCellValue(row.getCell(8)));
                    quality.setLearningQualityMutualAssessment(ExcelUtil.getCellValue(row.getCell(9)));
                    quality.setLearningQualityTeacherAssessment(ExcelUtil.getCellValue(row.getCell(10)));
                    quality.setCommAndCooperSelfAssessment(ExcelUtil.getCellValue(row.getCell(11)));
                    quality.setCommAndCooperMutualAssessment(ExcelUtil.getCellValue(row.getCell(12)));
                    quality.setCommAndCooperTeacherAssessment(ExcelUtil.getCellValue(row.getCell(13)));
                    quality.setSportsHealthSelfAssessment(ExcelUtil.getCellValue(row.getCell(14)));
                    quality.setSportsHealthMutualAssessment(ExcelUtil.getCellValue(row.getCell(15)));
                    quality.setSportsHealthTeacherAssessment(ExcelUtil.getCellValue(row.getCell(16)));
                    quality.setAestheticExpressionSelfAssessment(ExcelUtil.getCellValue(row.getCell(17)));
                    quality.setAestheticExpressionMutualAssessment(ExcelUtil.getCellValue(row.getCell(18)));
                    quality.setAestheticExpressionTeacherAssessment(ExcelUtil.getCellValue(row.getCell(19)));
//                    学年学期
                    quality.setSemester(ExcelUtil.getSemester());
                    qualities.add(quality);
                }
            }
        }
        return qualities;
    }

}
