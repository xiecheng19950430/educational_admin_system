package com.ebay.templete;

import com.ebay.models.GmStudentBodyStatus;
import com.ebay.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class StudentBodyStatusTemplete {
    public static List<GmStudentBodyStatus> temp(MultipartFile file) {
        Workbook workbook = ExcelUtil.getWorkBook(file);
        List<GmStudentBodyStatus> bodyStatuses = new ArrayList<>();
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            if (rows > 0) {
                for (int i = 1; i <= rows; i++) {
                    Row row = sheet.getRow(i);
                    GmStudentBodyStatus bodyStatus = new GmStudentBodyStatus();
                    String studentNo = ExcelUtil.getCellValue(row.getCell(0));
                    bodyStatus.setStudentNo(ExcelUtil.formatDouble(studentNo));
                    bodyStatus.setName(ExcelUtil.getCellValue(row.getCell(1)));
                    String height = ExcelUtil.getCellValue(row.getCell(2));
                    bodyStatus.setHeight(ExcelUtil.formatDouble(height));
                    bodyStatus.setWeight(ExcelUtil.getCellValue(row.getCell(3)));
                    bodyStatus.setLeftVision(ExcelUtil.getCellValue(row.getCell(4)));
                    bodyStatus.setRightVision(ExcelUtil.getCellValue(row.getCell(5)));
                    bodyStatus.setHealthStatus(ExcelUtil.getCellValue(row.getCell(6)));
//                    学年学期
                    bodyStatuses.add(bodyStatus);
                }
            }
        }
        return bodyStatuses;
    }
}
