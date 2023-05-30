package com.example.onlineshop.service;

import java.io.IOException;

public interface MainService {
    byte[] getImage(String imageName) throws IOException;
}
