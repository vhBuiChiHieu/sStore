package pro.vhbchieu.sStore.sys.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.file.FileDto;
import pro.vhbchieu.sStore.sys.service.FileService;
import pro.vhbchieu.sStore.sys.utils.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${file.folder}")
    private String folder;

    @Override
    public FileDto saveImage(MultipartFile file) {
        if (file.isEmpty())
            throw new CustomException(ErrorContent.EMPTY_FILE);

        // Check file name
        String fileOriginName = file.getOriginalFilename();
        if (fileOriginName != null && (fileOriginName.endsWith(".jpg") || fileOriginName.endsWith(".jpeg") || fileOriginName.endsWith(".png"))) {

            String uploadDir = folder;
            Path uploadPath = Paths.get(uploadDir);

            String extension = fileOriginName.substring(fileOriginName.lastIndexOf("."));
            String formatedFileName = StringUtils.abbreviate(fileOriginName.replace(" ", "-"), 30) + extension;
            String fileName = UUID.randomUUID() + "_" + formatedFileName;

            Path filePath = uploadPath.resolve(fileName);

            try {
                Files.copy(file.getInputStream(), filePath);
            } catch (Exception e) {
                throw new CustomException(ErrorContent.SAVED_FILE_FAILED);
            }

            return new FileDto(fileName);

        } else {
            throw new CustomException(ErrorContent.FILE_FORMAT_NOT_SUPPORT);
        }
    }

    @Override
    public Resource getImage(String fileName, HttpServletResponse response) {
        try {
            String uploadDir = folder;
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                response.setContentType(contentType);
                return resource;
            }

            throw new CustomException(ErrorContent.GET_FILE_FAILED);

        } catch (Exception e) {
            throw new CustomException(ErrorContent.GET_FILE_FAILED);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        try {
            String uploadDir = folder;
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();

            if (!Files.exists(filePath)) {
                throw new CustomException(ErrorContent.FILE_NOT_FOUND);
            }

            Files.delete(filePath);

        } catch (Exception e) {
            throw new CustomException(ErrorContent.DELETE_FILE_FAILED);
        }
    }

}
