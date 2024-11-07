package com.bigdata.hadoop.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    byte[] getImage(String imageName);
    void saveImageToHadoop(MultipartFile file, String imageName);
}
