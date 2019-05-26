package com.ebay.templete;

import com.ebay.models.GmStudentSub;
import com.ebay.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class StudentSubTemplete {

    public static List<GmStudentSub> temp(MultipartFile file) {
        Workbook workbook = ExcelUtil.getWorkBook(file);
        List<GmStudentSub> list = new ArrayList<>();
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            if (rows > 0) {
                for (int i = 1; i <= rows; i++) {
                    Row row = sheet.getRow(i);
                    GmStudentSub sub = new GmStudentSub();
                    String studentNo = ExcelUtil.getCellValue(row.getCell(0));
                    sub.setStudentNo(ExcelUtil.formatDouble(studentNo));
                    sub.setClassName(ExcelUtil.getCellValue(row.getCell(1)));
                    sub.setName(ExcelUtil.getCellValue(row.getCell(2)));
                    sub.setSex(ExcelUtil.getCellValue(row.getCell(3)));
                    sub.setIdCard(ExcelUtil.getCellValue(row.getCell(4)));
                    sub.setBirthday(ExcelUtil.getCellValue(row.getCell(5)));
                    sub.setInSchoolTime(ExcelUtil.getCellValue(row.getCell(6)));
                    sub.setOutSchoolTime(ExcelUtil.getCellValue(row.getCell(7)));
                    sub.setInScore(ExcelUtil.getCellValue(row.getCell(8)));
                    sub.setInScore(ExcelUtil.getCellValue(row.getCell(9)));
                    sub.setSourceSchool(ExcelUtil.getCellValue(row.getCell(10)));
                    sub.setPolitical(ExcelUtil.getCellValue(row.getCell(11)));
                    sub.setFather(ExcelUtil.getCellValue(row.getCell(12)));
                    sub.setFatherPhone(ExcelUtil.getCellValue(row.getCell(13)));
                    sub.setMother(ExcelUtil.getCellValue(row.getCell(14)));
                    sub.setMotherPhone(ExcelUtil.getCellValue(row.getCell(15)));
                    sub.setOther(ExcelUtil.getCellValue(row.getCell(16)));
                    sub.setOtherPhone(ExcelUtil.getCellValue(row.getCell(17)));
                    sub.setStatus(ExcelUtil.getCellValue(row.getCell(18)));
//                    学年学期
                    sub.setSemester(ExcelUtil.getSemester());
                    list.add(sub);
                }
            }
        }
        return list;
    }
}
