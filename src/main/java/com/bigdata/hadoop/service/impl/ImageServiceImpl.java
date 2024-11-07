package com.bigdata.hadoop.service.impl;

import com.bigdata.hadoop.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final FileSystem hadoopFileSystem;

    @Override
    public byte[] getImage(String imageName) {
        Path imagePath = new Path("/user/hadoop/images/" + imageName);
        try (InputStream inputStream = hadoopFileSystem.open(imagePath);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveImageToHadoop(MultipartFile file, String imageName) {
        Path imagePath = new Path("/user/hadoop/images/" + imageName);

        try (FSDataOutputStream outputStream = hadoopFileSystem.create(imagePath);
             InputStream inputStream = file.getInputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error saving image to Hadoop", e);
        }
    }
}
