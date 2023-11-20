package com.example.workbridgeback.service;


import com.example.workbridgeback.dao.ImageModelDao;
import com.example.workbridgeback.entity.ImageModel;
import com.example.workbridgeback.exeptions.ImageNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ImageService {
    @Autowired
    private ImageModelDao photoRepo;



    public ImageModel getPhoto(Long id) throws ImageNotFound {
        Optional<ImageModel> img = photoRepo.findById(id);
        ImageModel imageModel = img.orElseThrow(()->new ImageNotFound(String.format("Image Not Found")));
        return imageModel;
    }

    public ImageModel save(ImageModel photo) {

        return photoRepo.save(photo);
    }









}
