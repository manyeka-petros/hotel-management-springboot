package com.mapoto.HotelManagement.Filez.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilesModels {
    private String filename;
    private String downloadUrl;
    private String filetype;
    private long filesize;
    private String category;

    public FilesModels(String filename, String downloadUrl, String filetype, long filesize) {
        this.filename = filename;
        this.downloadUrl = downloadUrl;
        this.filetype = filetype;
        this.filesize = filesize;
    }
}
