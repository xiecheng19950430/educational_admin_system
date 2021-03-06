package com.ebay.utils;

import com.ebay.common.utils.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExcelUtil {
    public static Workbook getWorkBook(MultipartFile file) {
        Workbook workbook = null;
        String filename = file.getOriginalFilename();//  获取文件名
        try {
            if (filename.endsWith("xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());//  2003版本
            } else if (filename.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());//  2007版本
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC:// 数字
                    value = cell.getNumericCellValue() + "";
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        if (date != null) {
                            value = new SimpleDateFormat("yyyy-MM-dd").format(date); //  日期格式化
                        } else {
                            value = "";
                        }
                    }
//                    else {
//                        //  解析cell时候 数字类型默认是double类型的 但是想要获取整数类型 需要格式化
//                        value = new DecimalFormat("0").format(cell.getNumericCellValue());
//                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: //  字符串
                    value = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:   //  Boolean类型
                    value = cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK:   // 空值
                    value = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 错误类型
                    value = "非法字符";
                    break;
                default:
                    value = "未知类型";
                    break;
            }
        }
        return value.trim();
    }

    //去小数点 .0
    public static String formatDouble(String num) {
        if (StringUtils.isNotBlank(num)) {
            Double d = Double.parseDouble(num);
            return new DecimalFormat("0").format(d);
        }
        return "";
    }

    //获取学年学期
    public static String getSemester() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return year + "-" + (month < 8 ? "1" : "2");
    }

    public static void main(String[] args) {
        System.out.println(getSemester());
    }
}
