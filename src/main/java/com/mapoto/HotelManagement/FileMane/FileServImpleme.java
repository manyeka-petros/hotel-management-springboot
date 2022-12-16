package com.mapoto.HotelManagement.FileMane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;


@Service

public class FileServImpleme {
    @Autowired
    private FileRepo fileRepo;

    public FilesInfor uploadFiles(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FilesInfor filesInfor = new FilesInfor(fileName,file.getContentType(),file.getBytes());
        return fileRepo.save(filesInfor);
    }

    public FilesInfor getFile(String id){
        return fileRepo.findById(id).get();
    }

    public Stream<FilesInfor> getAllFiles(){
        return fileRepo.findAll().stream();
    }

}
