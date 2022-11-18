package com.example.cmsbe.common.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;

public class UploadFileUtils {

    public static String upload(ServletContext context, MultipartFile file) {
        boolean isExisted = new File(context.getRealPath("/images/")).exists();
        if (!isExisted) {
            new File(context.getRealPath("/images/")).mkdir();
        }
        String fileName = file.getOriginalFilename();
        String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_"
                + System.currentTimeMillis() + "."
                + FilenameUtils.getExtension(fileName);

        File serverFile = new File(context.getRealPath("/images/" + File.separator + modifiedFileName));
        try {
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modifiedFileName;
    }
}
