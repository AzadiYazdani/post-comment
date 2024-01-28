package com.haraji.app.util;

import org.apache.commons.fileupload.InvalidFileNameException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Optional;

public class FileUploadUtil {
    public static void saveFile(MultipartFile multipartFile, Map<String, String> attributes) throws IOException {

        String fileName = multipartFile.getOriginalFilename();
        if(StringUtils.isBlank(fileName))
            throw new InvalidFileNameException("", "The uploaded file has no name");

        Path path = Paths.get("../Files-Upload");

        if (attributes != null) {
            attributes.forEach((key, value) -> {
                try {
                    saveAttributes(path, key, value);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        if (!Files.exists(path))
            Files.createDirectories(path);

        String fileCode = RandomStringUtils.randomAlphanumeric(8);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = path.resolve(fileName);
            getExtensionByStringHandling(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }
    }

    private static void saveAttributes(Path path, String key, String value) throws IOException {
        Files.setAttribute(path, "user:" + key, value);
    }

    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
