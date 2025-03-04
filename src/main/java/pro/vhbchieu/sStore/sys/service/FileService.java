package pro.vhbchieu.sStore.sys.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.vhbchieu.sStore.sys.domain.dto.file.FileDto;

@Service
public interface FileService {
    FileDto saveImage(MultipartFile file);

    Resource getImage(String fileName, HttpServletResponse response);

    void deleteFile(String fileName);
}
