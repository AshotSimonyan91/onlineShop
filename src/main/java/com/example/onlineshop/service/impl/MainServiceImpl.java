package com.example.onlineshop.service.impl;


import com.example.onlineshop.service.MainService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {
    @Value("${shopping-app.upload.image.path}")
    private String imageUploadPath;

    @Override
    public byte[] getImage(String imageName) throws IOException {
        File file = new File(imageUploadPath + imageName);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.toByteArray(fis);
        }
        return null;
    }

}