package com.ebay.templete;

import com.ebay.models.GmStudentBodyStatus;
import com.ebay.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

public class StudentBodyStatusRemplete {
    public static void temp(MultipartFile file) {
        Workbook workbook = ExcelUtil.getWorkBook(file);
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            for (int i = 1; i <= rows + 1; i++) {
                Row row = sheet.getRow(i);
                GmStudentBodyStatus bodyStatus = new GmStudentBodyStatus();
                bodyStatus.setStudentNo(ExcelUtil.getCellValue(row.getCell(0)));
            }
        }

    }
}
