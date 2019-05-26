package com.ebay.templete;

import com.ebay.models.GmStudentAttendance;
import com.ebay.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class StudentAttendanceTemplete {
    public static List<GmStudentAttendance> temp(MultipartFile file) {
        Workbook workbook = ExcelUtil.getWorkBook(file);
        List<GmStudentAttendance> attendances = new ArrayList<>();
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            if (rows > 0) {
                for (int i = 1; i <= rows; i++) {
                    Row row = sheet.getRow(i);
                    GmStudentAttendance attendance = new GmStudentAttendance();
                    String studentNo = ExcelUtil.getCellValue(row.getCell(0));
                    attendance.setStudentNo(ExcelUtil.formatDouble(studentNo));
                    attendance.setName(ExcelUtil.getCellValue(row.getCell(1)));
                    Integer lateNumberOfDays = Integer.parseInt(ExcelUtil.getCellValue(row.getCell(2)));
                    attendance.setLateNumberOfDays(lateNumberOfDays);
                    Integer sickLeaveNumberOfDays = Integer.parseInt(ExcelUtil.getCellValue(row.getCell(3)));
                    attendance.setSickLeaveNumberOfDays(sickLeaveNumberOfDays);
                    Integer affairLeaveNumberOfDays = Integer.parseInt(ExcelUtil.getCellValue(row.getCell(4)));
                    attendance.setAffairLeaveNumberOfDays(affairLeaveNumberOfDays);
//                    学年学期
                    attendance.setSemester(ExcelUtil.getSemester());
                    attendances.add(attendance);
                }
            }
        }
        return attendances;
    }
}
