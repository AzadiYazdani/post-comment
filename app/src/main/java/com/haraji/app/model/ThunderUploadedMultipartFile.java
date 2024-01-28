package com.haraji.app.model;

import org.springframework.integration.http.multipart.UploadedMultipartFile;


public class ThunderUploadedMultipartFile extends UploadedMultipartFile {

    public ThunderUploadedMultipartFile(byte[] bytes, String contentType, String formParameterName, String originalFilename) {
        super(bytes, contentType, formParameterName, originalFilename);
    }
}
