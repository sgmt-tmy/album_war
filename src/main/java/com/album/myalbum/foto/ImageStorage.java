package com.album.myalbum.foto;

import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("file.storage")
@Component
public class ImageStorage {

    String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return System.getProperty("user.home") + File.separator + url;
    }

}