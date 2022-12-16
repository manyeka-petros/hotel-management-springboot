package com.mapoto.HotelManagement.Servi;

import com.mapoto.HotelManagement.Entity.FilesAnoucements;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FilesAnoucements uploadFiles(MultipartFile file) throws Exception;

    FilesAnoucements getFiles(String fileId) throws Exception;
}
