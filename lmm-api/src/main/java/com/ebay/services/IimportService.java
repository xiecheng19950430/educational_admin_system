package com.ebay.services;

import com.alibaba.fastjson.JSON;
import com.ebay.common.utils.StringUtils;
import com.ebay.mappers.ExcelMapper;
import com.ebay.models.GmTeacher;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IimportService {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    private static final Logger logger = LoggerFactory.getLogger(IimportService.class);

    @Autowired
    private ExcelMapper excelMapper;

    public Integer importTeacherExcel(MultipartFile teacherFile)  throws ParseException {
        //1.  使用HSSFWorkbook 打开或者创建 “Excel对象”
        //2.  用HSSFWorkbook返回对象或者创建sheet对象
        //3.  用sheet返回行对象，用行对象得到Cell对象
        //4.  对Cell对象进行读写
        List<GmTeacher> teacherList = new ArrayList<>();
        Workbook workbook = null;
        String teacherFileName = teacherFile.getOriginalFilename();//  获取文件名
        logger.info("【teacherFileName】{}",teacherFileName);
        if (teacherFileName.endsWith(XLS))
        {
            try {
                workbook = new HSSFWorkbook(teacherFile.getInputStream());//  2003版本
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(teacherFileName.endsWith(XLSX)) {
            try {
                workbook = new XSSFWorkbook(teacherFile.getInputStream());//  2007版本
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Sheet sheet = workbook.getSheet("sheet1");
        int rows = sheet.getLastRowNum();
        logger.info("【rows】{}",rows);
        long startTime = System.currentTimeMillis();
        for(int i = 1;i<= rows+1;i++){
            Row row = sheet.getRow(i);
            if(row !=null){
                GmTeacher teacher = new GmTeacher();
                //工号
                String workNo = getCellValue(row.getCell(0));
                teacher.setWorkNo(workNo);
                //姓名
                String name = getCellValue(row.getCell(1));
                teacher.setName(name);
                //登陆账号
                String loginId = getCellValue(row.getCell(2));
                teacher.setLoginId(loginId);
                //登陆密码
                String password = getCellValue(row.getCell(3));
                teacher.setPassword(password);
                //性别
                String sex = getCellValue(row.getCell(4));
                teacher.setSex(sex);
                //生日
                String birthday = getCellValue(row.getCell(5));
                if(!StringUtils.isEmpty(birthday)){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    teacher.setBirthday(sdf.parse(birthday));
                }
                //入职日期
                String hiredate = getCellValue(row.getCell(6));
                if(!StringUtils.isEmpty(hiredate)){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    teacher.setHiredate(sdf.parse(hiredate));
                }//职称
                String position = getCellValue(row.getCell(7));
                if(!StringUtils.isEmpty(position)){
                    teacher.setPosition(position);
                }
                //联系电话
                String phone = getCellValue(row.getCell(8));
                if(!StringUtils.isEmpty(phone)){
                    teacher.setPhone(phone);
                }
                teacherList.add(teacher);
            }
        }
        excelMapper.batchTeacherInsert(teacherList);  //  批量插入 五秒完成
        long endTime = System.currentTimeMillis();
        long totaltime = endTime - startTime;
        logger.info("【消耗时间为】{}",totaltime);  //  将近两万条数据 3秒解析完成
        logger.info("【第一条数据为】{}",JSON.toJSON(teacherList.get(0)));
        return rows;
    }

    public String getCellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            switch(cell.getCellType()){
                case HSSFCell.CELL_TYPE_NUMERIC:// 数字
                    value = cell.getNumericCellValue()+ " ";
                    if(HSSFDateUtil.isCellDateFormatted(cell)){
                        Date date = cell.getDateCellValue();
                        if(date != null){
                            value = new SimpleDateFormat("yyyy-MM-dd").format(date); //  日期格式化
                        }else{
                            value = "";
                        }
                    }else {
                        //  解析cell时候 数字类型默认是double类型的 但是想要获取整数类型 需要格式化
                        value = new DecimalFormat("0").format(cell.getNumericCellValue());
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: //  字符串
                    value = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:   //  Boolean类型
                    value = cell.getBooleanCellValue()+"";
                    break;
                case HSSFCell.CELL_TYPE_BLANK:   // 空值
                    value = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 错误类型
                    value ="非法字符";
                    break;
                default:
                    value = "未知类型";
                    break;
            }
        }
        return value.trim();
    }
}
