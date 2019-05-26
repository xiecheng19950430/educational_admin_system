package com.ebay.utils;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    /**
     * 将多个文件打包成zip文件
     *
     * @param srcfile
     * @param zipfile
     */
    public static void zipFiles(List<File> srcfile, File zipfile) {
        byte[] buf = new byte[1024];
        try {
            // Create the ZIP file
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            // Compress the files
            for (int i = 0; i < srcfile.size(); i++) {
                File file = srcfile.get(i);
                FileInputStream in = new FileInputStream(file);
                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(file.getName()));
                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // Complete the entry
                out.closeEntry();
                in.close();
            }
            // Complete the ZIP file
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //压缩后删除文件
            deleteFiles(srcfile);
        }
    }

    /**
     * 删除多个文件方法
     *
     * @param srcfile
     */
    public static void deleteFiles(List<File> srcfile) {
        for (File file : srcfile) {
            deleteFile(file);
        }
    }

    /**
     * 删除单个文件
     */
    public static void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }


    /**
     * 复制文件
     *
     * @param s 源文件
     * @param t 复制到的新文件
     */

    public static void fileChannelCopy(File s, File t) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(s), 1024);
                out = new BufferedOutputStream(new FileOutputStream(t), 1024);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将模板复制到新文件中供写入和下载
     *
     * @return
     */
    public static File createNewFile(File tempFile, String newFileName, String realPath) {
        //读取模板，并赋值到新文件************************************************************
        //判断路径是否存在
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //写入到新的excel
        File newFile = new File(realPath, newFileName);
        try {
            newFile.createNewFile();
            //复制模板到新文件
            fileChannelCopy(tempFile, newFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFile;
    }
}
