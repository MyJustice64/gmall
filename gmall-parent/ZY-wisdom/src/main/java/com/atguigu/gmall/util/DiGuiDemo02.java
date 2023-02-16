package com.atguigu.gmall.util;
 
import java.io.File;
import java.nio.file.Paths;

import static com.atguigu.gmall.util.Mp4Utils.readDuration;

public class DiGuiDemo02 {
    public static void main(String[] args) {
        // 1：根据给定的路径创建一个File对象
//        File srcFile = new File("E:\\itcast");
        File srcFile = new File("D:\\AStudy\\（6）凡耿老师__尚品汇项目\\gmall\\gmall-day02\\视频");

        // 6：调用方法
        getAllFilePath(srcFile);
    }
 
    // 2：定义一个方法，用于获取给定目录下的所有内容，参数为第1步创建的File对象
    public static void getAllFilePath(File srcFile) {
        // 3：获取给定的File目录下所有的文件或者目录的File数组
        File[] fileArray = srcFile.listFiles();
        // 4：遍历该File数组，得到每一个File对象
        if (fileArray != null) {
            for (File file : fileArray) {
                // 5：判断该File对象是否是目录
                if (file.isDirectory()) {
                    // 5.1：是：递归调用
                    getAllFilePath(file);
                } else {
                    // 5.2：不是：获取绝对路径输出在控制台
//                    System.out.println(file.getAbsolutePath());
                    readDuration(Paths.get(file.getAbsolutePath()));
                    //输出文件名 时长
                    System.out.println(file.getAbsolutePath()+" "+readDuration(Paths.get(file.getAbsolutePath())));
                }
            }
        }
    }
}