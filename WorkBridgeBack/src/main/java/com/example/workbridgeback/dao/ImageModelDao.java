package com.example.workbridgeback.dao;


import com.example.workbridgeback.entity.ImageModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageModelDao extends CrudRepository<ImageModel,Long> {
    @Override
    Optional<ImageModel> findById(Long id);

    @Override
    ImageModel save(ImageModel photo);



}
