package com.ebay.services;

import com.ebay.models.GmStudent;
import com.ebay.templete.QualityReportDocTemplete;
import com.ebay.utils.FileUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class ExportService {
    @Autowired
    private GmStudentService studentService;

    //学生素质报告导出
    public void exportQualityReportDoc(HttpServletRequest request, HttpServletResponse response) {
        String classId = request.getParameter("classId");
        String name = request.getParameter("name");
        String studentNo = request.getParameter("studentNo");


        InputStream is = null;
        try {
            //读取模板
            String path = this.getClass().getResource("/temp").getPath();
            String tempName = "综合素质报告单.加水印（复件）.docx";

            File tempFile = new File(path + "/" + tempName);

            //查找学生 遍历 生成空白模板
            List<GmStudent> studentList = studentService.query(Integer.valueOf(classId), name, studentNo);
            if (!CollectionUtils.isEmpty(studentList)) {
                if (studentList.size() > 1) {
                    List<File> files = new ArrayList<>();
                    for (GmStudent student : studentList) {

                        File newFile = FileUtil.createNewFile(tempFile, student.getName() + "_" + student.getStudentNo() + ".docx", path);

                        //获取学生素质报告所需参数
                        Map map = this.getQualityReport(student);

                        //填充参数
                        is = new FileInputStream(newFile);
                        XWPFDocument document = new XWPFDocument(is);
                        QualityReportDocTemplete.temp(document, map);

                        //写入文件
                        FileOutputStream fos = new FileOutputStream(newFile);
                        document.write(fos);
                        fos.flush();
                        fos.close();

                        files.add(newFile);
                    }


                    File zipFile = new File(path, "测试xxx.zip");
                    zipFile.createNewFile();
                    FileUtil.zipFiles(files, zipFile);

                    this.downZip(request, response, zipFile.getName(), zipFile.getPath());
                } else {
                    GmStudent student = studentList.get(0);
                    //获取学生素质报告所需参数
                    Map map = this.getQualityReport(student);

                    is = new FileInputStream(tempFile);
                    XWPFDocument document = new XWPFDocument(is);
                    QualityReportDocTemplete.temp(document, map);
                    this.setBrowser(request, response, document, student.getName() + "_" + student.getStudentNo() + ".docx");
                }
            }


//            setBrowser(request, response, document, "测试.doc");
//            this.setBrowserWithZip(request, response, document, map);
            System.out.println("导出解析成功$");
        } catch (Exception e) {
            System.out.println("导出解析失败$");
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 方法名：setBrowser
     * 功能：使用浏览器下载
     */
    private void setBrowser(HttpServletRequest request, HttpServletResponse response, XWPFDocument document, String fileName) {
        OutputStream os = null;
        try {
            //清空response
            response.reset();
            fileName = this.formatFileName(request, fileName);
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-word;charset=UTF-8");


            //将word写入到输出流中
            document.write(os);
            os.flush();
            os.close();
            System.out.println("设置浏览器下载成功！");
        } catch (Exception e) {
            System.out.println("设置浏览器下载失败！");
            e.printStackTrace();
        } finally {
            try {
                if (os != null) os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private void downZip(HttpServletRequest request, HttpServletResponse response, String filename, String path) {
        if (filename != null) {
            FileInputStream is = null;
            BufferedInputStream bs = null;
            OutputStream os = null;
            File file = null;
            try {
                file = new File(path);
                if (file.exists()) {
                    //设置Headers
                    response.setHeader("Content-Type", "application/octet-stream");
                    filename = this.formatFileName(request, filename);
                    //设置下载的文件的名称-该方式已解决中文乱码问题
                    response.setHeader("Content-Disposition", "attachment;filename=" + filename);
                    is = new FileInputStream(file);
                    bs = new BufferedInputStream(is);
                    os = response.getOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = bs.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                } else {
                    String error = URLEncoder.encode("下载的文件资源不存在");
                    response.sendRedirect("/imgUpload/imgList?error=" + error);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (bs != null) {
                        bs.close();
                    }
                    if (os != null) {
                        os.flush();
                        os.close();
                    }
                    //删除文件
                    if (file != null) FileUtil.deleteFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String formatFileName(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        //设置response的Header
        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1"); // firefox浏览器
        } else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
        } else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");// 谷歌
        } else {  //其他浏览器
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        }
        return fileName;
    }

    private Map getQualityReport(GmStudent student) {
        Map map = new HashMap();
        map.put("student", "xxx");//学生基本信息
        map.put("attendance", "xxx");//出勤信息
        map.put("termscore", "xxx");//学科课程学习状况
        map.put("quality", "xxx");//综合素质评价
        map.put("assessment", "xxx");//综合能力考核
        map.put("bodystatus", "xxx");//身体状况
        return map;
    }
}
