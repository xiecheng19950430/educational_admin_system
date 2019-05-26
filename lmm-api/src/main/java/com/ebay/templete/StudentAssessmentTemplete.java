package com.ebay.templete;

import com.ebay.models.GmStudentAssessment;
import com.ebay.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class StudentAssessmentTemplete {
    public static List<GmStudentAssessment> temp(MultipartFile file) {
        Workbook workbook = ExcelUtil.getWorkBook(file);
        List<GmStudentAssessment> list = new ArrayList<>();
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            if (rows > 0) {
                for (int i = 1; i <= rows; i++) {
                    Row row = sheet.getRow(i);
                    GmStudentAssessment assessment = new GmStudentAssessment();
                    String studentNo = ExcelUtil.getCellValue(row.getCell(0));
                    assessment.setStudentNo(ExcelUtil.formatDouble(studentNo));
                    assessment.setName(ExcelUtil.getCellValue(row.getCell(1)));
                    assessment.setConduction(ExcelUtil.getCellValue(row.getCell(2)));
                    assessment.setPerformance(ExcelUtil.getCellValue(row.getCell(3)));
                    assessment.setRewardsPunishments(ExcelUtil.getCellValue(row.getCell(4)));
                    assessment.setComment(ExcelUtil.getCellValue(row.getCell(5)));
//                    学年学期
                    assessment.setSemester(ExcelUtil.getSemester());
                    list.add(assessment);
                }
            }
        }
        return list;
    }
}
