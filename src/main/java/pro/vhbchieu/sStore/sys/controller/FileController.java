package pro.vhbchieu.sStore.sys.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

@Slf4j
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    @Value("${file.folder}")
    private String folder;
    private final FileService fileService;

    @PostMapping("/upload")
    public FileDto uploadImage(@RequestParam("file") MultipartFile file) {
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
                // Save file
                Files.copy(file.getInputStream(), filePath);
            } catch (Exception e) {
                throw new CustomException(ErrorContent.SAVED_FILE_FAILED);
            }

            return new FileDto(fileName);
        } else {
            throw new CustomException(ErrorContent.FILE_FORMAT_NOT_SUPPORT);
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable("fileName") String fileName) {
        try {
            String uploadDir = System.getProperty("user.home") + folder;
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                        .body(resource);
            }

            throw new CustomException(ErrorContent.GET_FILE_FAILED);

        } catch (Exception e) {
            throw new CustomException(ErrorContent.GET_FILE_FAILED);
        }
    }

}
