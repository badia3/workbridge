package com.example.workbridgeback.imageRelated;

import com.example.workbridgeback.dao.UserDao;
import com.example.workbridgeback.entity.ImageModel;
import com.example.workbridgeback.entity.User;
import com.example.workbridgeback.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

@Component
public class ImageUtil<T> {

    private static String UPLOAD_DIR = ".\\src\\main\\resources\\static\\imagesuploads\\";


    @Transactional
    public Set<ImageModel> saveImages(T obj, MultipartFile[] imageFiles) {
        Set<ImageModel> savedImages = new HashSet<>();

        try {
            for (MultipartFile imageFile : imageFiles) {
                ImageModel savedImage = saveImage(obj, imageFile);
                savedImages.add(savedImage);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        return savedImages;
    }

    private ImageModel saveImage(T obj, MultipartFile imageFile) throws IOException {
        InputStream inputStream = imageFile.getInputStream();
        String fileName = "_" + imageFile.getOriginalFilename();
        String filePath = UPLOAD_DIR + fileName;

        try (OutputStream outputStream = new FileOutputStream(new File(filePath))) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

        ImageModel img = new ImageModel();
        img.setName(fileName);
        img.setType(imageFile.getContentType());
        img.setPicByte(imageFile.getBytes());
        if(obj instanceof User){
            img.setUser((User) obj);
        }

        return img;
    }

}
