package com.mapoto.HotelManagement.Servi;
import com.mapoto.HotelManagement.Entity.FilesAnoucements;
import com.mapoto.HotelManagement.Repositor.FilesAnoucementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImplement implements FileService{

    @Autowired
    private FilesAnoucementRepository filesAnoucementRepository;
    @Override
    public FilesAnoucements uploadFiles(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("...")){
                throw new Exception("the file contains invalide contents" +fileName);

            }
            FilesAnoucements filesAnoucements = new FilesAnoucements(fileName,
                   file.getContentType(), file.getBytes());
            return filesAnoucementRepository.save(filesAnoucements);

        }catch (Exception e){
            throw new Exception("we dont have that file");
        }


    }

    @Override
    public FilesAnoucements getFiles(String fileId) throws Exception {

        return filesAnoucementRepository.findById(fileId).orElseThrow(()->new Exception("we dont have that file"));
    }
}
