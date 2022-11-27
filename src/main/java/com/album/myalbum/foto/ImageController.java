package com.album.myalbum.foto;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageStorage imageStorage;

	@GetMapping(value="/{fileName}",produces=MediaType.IMAGE_JPEG_VALUE )
	public @ResponseBody byte[] responseImage(@PathVariable String fileName) throws IOException {

		// String filename = "/images/" + fileName;
        // File file = new File(System.getProperty("user.dir") + File.separator + filename);
        File file = new File(imageStorage.getUrl() + File.separator + fileName);

		return FileUtils.readFileToByteArray(file);
	}
}
