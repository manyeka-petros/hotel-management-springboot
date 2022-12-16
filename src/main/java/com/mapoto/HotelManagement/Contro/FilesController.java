package com.mapoto.HotelManagement.Contro;

import com.mapoto.HotelManagement.Entity.FilesAnoucements;
import com.mapoto.HotelManagement.Models.FilesModels;
import com.mapoto.HotelManagement.Servi.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController

public class FilesController {

    @Autowired
    private FileService fileService;
    @PostMapping
    public FilesModels uploadFiles(@RequestParam("file")MultipartFile file) throws Exception {
        FilesAnoucements filesAnoucements = null;
        String downloadingPath = "";
        filesAnoucements = fileService.uploadFiles(file);
        downloadingPath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(filesAnoucements.getFileId()).toUriString();
        return new FilesModels(filesAnoucements.getFileName(),downloadingPath, file.getContentType(),file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> getFiles(@PathVariable String fileId) throws Exception {
        FilesAnoucements filesAnoucements = null;
        filesAnoucements = fileService.getFiles(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(filesAnoucements.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"filesAnoucements; fileName=\"" + filesAnoucements.getFileName() + "\"").body(new ByteArrayResource(filesAnoucements.getData()));
    }
}
