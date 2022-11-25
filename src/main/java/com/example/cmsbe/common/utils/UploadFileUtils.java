package com.example.cmsbe.common.utils;

import com.example.cmsbe.dto.FileDTO;
import com.example.cmsbe.entity.Image;
import com.example.cmsbe.services.minio.MinIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UploadFileUtils {

    public static Set<Image> upload(MultipartFile[] files) throws IOException {
        Set<Image> images = new HashSet<>();

        for (MultipartFile file: files) {
            Image image = new Image(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            images.add(image);
        }
        return images;
    }
}
