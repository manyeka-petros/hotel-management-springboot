package com.mapoto.HotelManagement.Filez.Servi;


import com.mapoto.HotelManagement.Filez.Entitys.FilesResource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesResourceService {
    FilesResource uploadFiles(MultipartFile file , String category,String roomType,String cost) throws Exception;

    FilesResource downloadFile(String fileId) throws Exception;
}
