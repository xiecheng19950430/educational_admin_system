package com.ebay.controllers;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/file")
public class FileController {
    /**
     * 单文件上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload";
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            //上传文件名
            String fileName = file.getOriginalFilename();
            //服务器端保存的文件对象
            File serverFile = new File(uploadDir + fileName);
            //将上传的文件写入到服务器端文件内
            file.transferTo(serverFile);
        }catch(Exception e) {
            //打印错误堆栈信息
            e.printStackTrace();
            return "上传失败";
        }
        System.out.println("上传成功");
        return "上传成功";

    }
}
