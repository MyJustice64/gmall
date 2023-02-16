package com.atguigu.gmall.util;

import lombok.extern.slf4j.Slf4j;
import org.mp4parser.IsoFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author huyi @Date 2021/10/20 11:09 @Description: MP4工具类
 */
@Slf4j
public class Mp4Utils {
    public static long readDuration(Path mp4Path) {
        if (Files.notExists(mp4Path) || !Files.isReadable(mp4Path)) {
            log.warn("文件路径不存在或不可读 {}", mp4Path);
            return 0;
        }
        try {
            IsoFile isoFile = new IsoFile(mp4Path.toFile());
            long duration = isoFile.getMovieBox().getMovieHeaderBox().getDuration();
//      long timescale = isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
            return duration;
        } catch (IOException e) {
            log.error("读取MP4文件时长出错", e);
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(readDuration(Paths.get("D:\\AStudy\\每日资料\\电商项目\\20221205(day13)\\视频\\01-单点登录-总体执行流程.mp4")));
//    System.out.println(readDuration(Paths.get("C:\\Users\\huyi\\Desktop\\测试.mp4")));
    }
}