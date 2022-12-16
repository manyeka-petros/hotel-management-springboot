package com.mapoto.HotelManagement.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilesModels {
    private String fileName;
    private String downloadingPath;
    private String fileType;
    private Long fileSize;




}
