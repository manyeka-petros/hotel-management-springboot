package com.mapoto.HotelManagement.Filez.Servi;


import com.mapoto.HotelManagement.Filez.Entitys.FilesResource;
import com.mapoto.HotelManagement.Filez.Reposit.FilesResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

@Service
public class FilesResourceImplement implements FilesResourceService{
    @Autowired
    private FilesResourceRepository filesResourceRepository;

    @Override
    public FilesResource uploadFiles(MultipartFile file, String category,String roomNumber,String cost) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(filename.contains("...")){
                throw new Exception("The file contains invalide content");
            }
            FilesResource filesResource = new FilesResource(filename,file.getContentType(),file.getBytes(),category,
                    roomNumber,cost);

            return filesResourceRepository.save(filesResource);

        }catch (Exception e){
            throw new Exception("the System cant save the file " + filename);
        }



    }

    @Override
    public FilesResource downloadFile(String fileId) throws Exception {
        return filesResourceRepository.findById(fileId).orElseThrow(
                () ->new  Exception("file not found")
        );
    }

    public Stream<FilesResource> getAllFiles()
    {
        return filesResourceRepository.findAll().stream();
    }
}
