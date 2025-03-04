package pro.vhbchieu.sStore.sys.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.vhbchieu.sStore.config.constant.ErrorContent;
import pro.vhbchieu.sStore.exception.CustomException;
import pro.vhbchieu.sStore.sys.domain.dto.file.FileDto;
import pro.vhbchieu.sStore.sys.service.FileService;

@Slf4j
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public FileDto uploadImage(@RequestParam("file") MultipartFile file) {
        return fileService.saveImage(file);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getImage(
            @PathVariable("fileName") String fileName,
            HttpServletResponse response
    ) {
        if (fileName.contains("..")) {
            throw new CustomException(ErrorContent.GET_FILE_FAILED);
        }
        Resource resource = fileService.getImage(fileName, response);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.parseMediaType(response.getContentType()))
                .body(resource);
    }

    @DeleteMapping("/{fileName}")
    public void deleteFile(@PathVariable("fileName") String fileName) {
        if (fileName.contains("..")) {
            throw new CustomException(">> Invalid file name");
        }
        fileService.deleteFile(fileName);
    }

}
