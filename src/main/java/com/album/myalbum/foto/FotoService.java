package com.album.myalbum.foto;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private ImageStorage imageStorage;
    
    public List<Foto> findAll(){
        return fotoRepository.findAll();
    }

    public Foto saveFoto(Foto foto, MultipartFile imageFile){
        String imageName = "";

        try {
            imageName = this.uploadImage(imageFile); 
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        
        foto.setImageName(imageName);
        return fotoRepository.save(foto);
    }

    public String uploadImage(MultipartFile imageFile) throws IOException, IllegalStateException{
        //DBに保存するファイル名(年月日時間 + 元ファイル名)を取得
        String fileName = imageFile.getOriginalFilename();
        ImageUri imageUri = new ImageUri(fileName);
        String newFileName = imageUri.getName();

        File fileDir = new File(imageStorage.getUrl());

        if(!fileDir.exists()) {
			fileDir.mkdir();
		}
        
        File dest = new File(fileDir, newFileName).getAbsoluteFile();
        
        imageFile.transferTo(dest);

        return newFileName;
    }
}
