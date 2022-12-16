package com.mapoto.HotelManagement.FileMane;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {
    private long fileSize;
    private String  fileName;
    private String urls;
    private String fileType;


    public FileResponse(String urls, String fileType, String fileName, long fileSize) {
        this.urls = urls;
        this.fileType = fileType;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }
}
