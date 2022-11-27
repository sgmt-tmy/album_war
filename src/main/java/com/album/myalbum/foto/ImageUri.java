package com.album.myalbum.foto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ImageUri {

    private final String name;
    
    public ImageUri(String fileName) {

        //拡張子を除くファイル名
        String originName = fileName.substring(0,fileName.lastIndexOf("."));
        //拡張子
        String extention = fileName.substring(fileName.lastIndexOf("."));

        //アップロード日時を取得
        LocalDateTime nowDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        //連結
        this.name = originName + dtf.format(nowDate) + extention;
        
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ImageUri [name=" + name + "]";
    }
    
}
