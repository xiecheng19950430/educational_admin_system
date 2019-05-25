package com.ebay.services;

import com.ebay.common.DateUtil;
import com.ebay.utils.DocxUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class ExportService {

    public String exportDoc() {
        XWPFDocument doc = null;
        OutputStream out = null;
        FileInputStream in = null;
        try {
            String rootPath = "";
//            ServletContext context = servletRequest.getSession().getServletContext();
//            String studentId = this.servletRequest.getParameter("studentid");//得到页面的参数
//            RankExportDocVO docVo = null;
//            if (studentId != null) {
//                rootPath = context.getRealPath("/docmodel/PG/RankPrintAll_WordTemplate.docx");//Word模板所在的路径
//                docVo = rankPrintService.queryStudentInfo(studentId);//调用service得到对应的VO类
//            } else {
//                throw new Exception("传输字段为空！");
//            }
            String path = this.getClass().getResource("/temp").getPath();
            String tempName = "综合素质报告单.加水印（复件）.docx";
//            URL url = this.getClass().getResource("/temp/");
//            System.out.println(url.toString());
            System.out.println(path + "/" + tempName);
            InputStream is = new FileInputStream(path + "/" + tempName);
            XWPFDocument document = new XWPFDocument(is);

//            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(path + "/" + tempName));//读取Word模板
            //计算通用信息 学年学期，假期时间，下学期开学时间，打印日期
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.WEEK_OF_YEAR);
            int day = calendar.get(Calendar.DATE);
            Map<String, String> common = new HashMap<>();
            //标题
            common.put("!schoolYear", year + "-" + year + 1);//学年
            common.put("!semester", month < 8 ? "1" : "2");//学期
            //假期
            common.put("!holidayS", "2019年01月23日");
            common.put("!holidayE", "2019年02月21日");
            //下学期报到时间
            common.put("!startSchool", "2019年2月22日09时");
            //打印日期
            String printDate = DateUtil.format(new Date(), "YYYY月MM月DD日");
            common.put("!printDate", printDate);
            DocxUtil.searchAndReplace(document, common);//替换模板中的对应变量。


            //查找学生表 匹配学生基本信息和身体状况
            Map<String, String> stu = new HashMap<>();
            stu.put("!name", "xxx");
            stu.put("!studentId", "xxx");
            stu.put("!className", "xxx");
            stu.put("!height", "xxx");
            stu.put("!bodyWeight", "xxx");
            stu.put("!leftVision", "xxx");
            stu.put("!rightVision", "xxx");
            stu.put("!healthStatus", "xxx");
            DocxUtil.searchAndReplace(document, stu);//替换模板中的对应变量。

            //查找综合素质评价 匹配综合素质评价
            Map<String, String> quality = new HashMap<>();
            quality.put("s_1", "xxx");
            quality.put("c_1", "xxx");
            quality.put("t_1", "xxx");
            quality.put("s_2", "xxx");
            quality.put("c_2", "xxx");
            quality.put("t_2", "xxx");
            quality.put("s_3", "xxx");
            quality.put("c_3", "xxx");
            quality.put("t_3", "xxx");
            quality.put("s_4", "xxx");
            quality.put("c_4", "xxx");
            quality.put("t_4", "xxx");
            quality.put("s_5", "xxx");
            quality.put("c_5", "xxx");
            quality.put("t_5", "xxx");
            quality.put("s_6", "xxx");
            quality.put("c_6", "xxx");
            quality.put("t_6", "xxx");
            DocxUtil.searchAndReplace(document, quality);//替换模板中的对应变量。

            //查找成绩表 匹配sub开头的 //循环匹配
            Map<String, String> score = new HashMap<>();
            score.put("!sub1", "xxx");
            score.put("!A1", "xxx");
            score.put("!A2", "xxx");
            score.put("!A3", "xxx");
            score.put("!A4", "xxx");
            score.put("!A5", "xxx");
            DocxUtil.searchAndReplace(document, score);//替换模板中的对应变量。

            //综合能力考核
            Map<String, String> ass = new HashMap<>();
            ass.put("!conductionLevel", "xxx");
            ass.put("!specialPerformance", "xxx");
            ass.put("!classTeacherComment", "xxx");
            ass.put("!rewardsPunishments", "xxx");
            DocxUtil.searchAndReplace(document, ass);//替换模板中的对应变量。

            //查找考勤表 匹配考勤信息
            Map<String, String> att = new HashMap<>();
            att.put("!allDay", "xxx");
            att.put("!att", "xxx");
            att.put("!beL", "xxx");
            att.put("!sickL", "xxx");
            att.put("!l", "xxx");
            DocxUtil.searchAndReplace(document, att);//替换模板中的对应变量。

            //写入本地
//            File newFile = new File();
//            File newFile = new File(path, path + "/" + UUID.randomUUID().toString() + ".docx");
//            if(!newFile.exists()){
//                newFile.mkdirs();
//            }
//            FileOutputStream fos = new FileOutputStream(newFile);
//            document.write(fos);
//            fos.flush();
//            fos.close();
//            XWPFTemplate template = XWPFTemplate.compile(filePath).render(params);
//            FileOutputStream out = new FileOutputStream(outFilePath);
//            template.write(out);
//            out.flush();
//            out.close();
//            template.close();
            System.out.println(path + "/" + UUID.randomUUID().toString() + ".docx");
            OutputStream os = new FileOutputStream(path + "/" + UUID.randomUUID().toString() + ".docx");
            document.write(os);
            os.flush();
            os.close();
            is.close();
            //默导出到流
//                response.setContentType("application/vnd.ms-excel");
//                response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xlsx");
//                OutputStream out = response.getOutputStream();
//                workbook.write(out);
//                out.flush();
//                out.close();
            //下载
//                InputStream fis = new BufferedInputStream(new FileInputStream(newFile));
//                HttpServletResponse response = this.getResponse();
//                byte[] buffer = new byte[fis.available()];
//                fis.read(buffer);
//                fis.close();
//                response.reset();
//                response.setContentType("text/html;charset=UTF-8");
//                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//                response.setContentType("application/x-msdownload");
//                String newName = URLEncoder.encode("采购合同"+System.currentTimeMillis()+".xls", "UTF-8");
//                response.addHeader("Content-Disposition", "attachment;filename=\""+ newName + "\"");
//                response.addHeader("Content-Length", "" + newFile.length());
//                toClient.write(buffer);
//                toClient.flush();


//            Map<String, String> params = new HashMap<String, String>();
//            DocxUtil.copyParamFromBean(docVo, params);//调用DocxUtil中的copyParamFromBean方法，为VO的每个值建立“${valuename}”键
//            DocxUtil.searchAndReplace(document, params);//替换模板中的对应变量。
//            // 清空response
//            this.servletResponse.reset();
//            // 设置response的Header
//            String userAgent = this.servletRequest.getHeader("User-Agent");
//            String realname = "学生信息文档_" + docVo.getXm() + ".docx";//最终导出文档的新文档名
//            if (userAgent.contains("Firefox")) {
//                // 采用BASE64编码
//                realname = "=?UTF-8?B?" + new BASE64Encoder().encode(realname.getBytes("utf-8")) + "?=";
//            } else {
//                // 其它浏览器 IE 、google 采用URL编码
//                realname = URLEncoder.encode(realname, "utf-8");
//                realname = realname.replace("+", " ");
//            }
//            servletResponse.setHeader("Content-Disposition", "attachment;filename=" + realname);
//            String minitype = ServletActionContext.getServletContext().getMimeType(realname);
//            servletResponse.setContentType(minitype);
//            out = this.servletResponse.getOutputStream();
//            document.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (null != out)
//                DocxUtil.close(out);//关闭流
        }
        return null;
    }
}
