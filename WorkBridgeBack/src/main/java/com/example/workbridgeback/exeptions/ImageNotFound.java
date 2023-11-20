package com.example.workbridgeback.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ImageNotFound extends Exception{
    private String message;


    public ImageNotFound(String message){
        super(message);
    }

}
