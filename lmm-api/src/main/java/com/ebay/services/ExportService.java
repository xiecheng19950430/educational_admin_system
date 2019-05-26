package com.ebay.services;

import com.ebay.templete.QualityReportDocTemplete;
import com.ebay.utils.FileUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportService {

    //学生素质报告导出
    public void exportQualityReportDoc(HttpServletRequest request, HttpServletResponse response) {
        String classId = request.getParameter("classId");
        String name = request.getParameter("name");
        String stuNo = request.getParameter("stuNo");


        InputStream is = null;
        try {
            //读取模板
            String path = this.getClass().getResource("/temp").getPath();
            String tempName = "综合素质报告单.加水印（复件）.docx";

            File tempFile = new File(path + "/" + tempName);

            //查找学生 遍历 生成空白模板
            List<File> files = new ArrayList<>();
            for (int i = 1; i < 3; i++) {
                File newFile = FileUtil.createNewFile(tempFile, "测试xx" + i + ".docx", path);

                //填充参数
                is = new FileInputStream(newFile);
                XWPFDocument document = new XWPFDocument(is);
                QualityReportDocTemplete.temp(document);

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

//            FileUtil.downFile(response, zipFile);
            this.downZip(response, zipFile.getName(), zipFile.getPath());

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


    public void downZip(HttpServletResponse response, String filename, String path) {
        if (filename != null) {
            FileInputStream is = null;
            BufferedInputStream bs = null;
            OutputStream os = null;
            try {
                File file = new File(path);
                if (file.exists()) {
                    //设置Headers
                    response.setHeader("Content-Type", "application/octet-stream");
                    //设置下载的文件的名称-该方式已解决中文乱码问题
                    response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1"));
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
