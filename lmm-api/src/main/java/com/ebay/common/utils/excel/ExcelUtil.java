package com.ebay.common.utils.excel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;
import com.ebay.common.DateUtil;
import com.ebay.common.utils.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ldm on 2017/5/31.
 */
public class ExcelUtil {
    private XSSFWorkbook wb;
    private XSSFSheet sheet;


    public XSSFWorkbook createExcel(List<Map> dataMaps, List<ColumnMap> columnMaps) {
        return this.createExcel(dataMaps, columnMaps, null);
    }

    public XSSFWorkbook createExcel(List<Map> dataMaps, List<ColumnMap> columnMaps, String name) {
        wb = new XSSFWorkbook();
        sheet = wb.createSheet(StringUtils.isBlank(name) ? "sheet1" : name);
        XSSFCellStyle headStyle = this.getHeadStyle();
        XSSFCellStyle bodyStyle = this.getBodyStyle();

        // 构建表头
        Integer rowNum = 0;
        XSSFRow row = sheet.createRow(rowNum);
        rowNum++;
        XSSFCell cell = null;

        cell = row.createCell(0);
        cell.setCellStyle(headStyle);
        cell.setCellValue("序号");

        for (Integer i = 0; i < columnMaps.size(); i++) {
            cell = row.createCell(i + 1);
            cell.setCellStyle(headStyle);
            cell.setCellValue(columnMaps.get(i).getName());
        }

        Integer index = 1;

        //构建表体
        for (Map data : dataMaps) {
            row = sheet.createRow(rowNum);
            rowNum++;
            cell = row.createCell(0);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(index);
            index++;
            for (Integer i = 0; i < columnMaps.size(); i++) {
                cell = row.createCell(i + 1);
                cell.setCellStyle(bodyStyle);
                ColumnMap columnMap = columnMaps.get(i);
                String value;
                Object obj = data.get(columnMap.getKey());
                if (StringUtils.isBlank(columnMap.getDateFormat()) && obj != null) {
                    value = obj.toString();
                } else {
                    value = DateUtil.format((Date) obj, columnMap.getDateFormat());
                }
                cell.setCellValue(value);
            }
        }
        return wb;
    }


    /**
     * 设置表头的单元格样式
     *
     * @return
     */
    private XSSFCellStyle getHeadStyle() {
        // 创建单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色
        cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        // 设置单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }

    /**
     * 设置表体的单元格样式
     *
     * @return
     */
    private XSSFCellStyle getBodyStyle() {
        // 创建单元格样式
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 创建单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体样式
        XSSFFont font = wb.createFont();
        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 设置单元格边框为细线条
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        return cellStyle;
    }
}
