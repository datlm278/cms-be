package com.example.cmsbe.common.utils;

import com.example.cmsbe.entity.Image;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
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
