package com.mapoto.HotelManagement.FileMane;

import com.mapoto.HotelManagement.Entity.FilesAnoucements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@RestController
public class FileContr {
    @Autowired
    private FileServImpleme fileServImpleme;
    @PostMapping( value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<ResponsMessage> uploadfiles(@RequestParam("file") MultipartFile file){
        String massage = "";
        try {
            fileServImpleme.uploadFiles(file);
            massage = "uploaded successfully " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponsMessage(massage));
        } catch (IOException e) {
            massage = "The files could not upload " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponsMessage(massage));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileResponse>> getFiles(){
        List<FileResponse> filez = fileServImpleme.getAllFiles().map(filesInfor -> {
            String fileDownload = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(filesInfor.getId())
                    .toString();
            return new FileResponse(
                    fileDownload,
                    filesInfor.getFileType(),
                    filesInfor.getFileName(),
                    filesInfor.getData().length

            );
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(filez);
    }



    @GetMapping("/files/{id}")
    public ResponseEntity<Resource> getFiles(@PathVariable String id) throws Exception {
        FilesInfor filesInfor = null;
        filesInfor = fileServImpleme.getFile(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(filesInfor.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"filesAnoucements; fileName=\"" + filesInfor.getFileName() + "\"").body(new ByteArrayResource(filesInfor.getData()));
    }
}
