package com.mapoto.HotelManagement.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@Entity
@NoArgsConstructor

public class FilesAnoucements {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String fileId;
    private String fileName;
    private String fileType;
    @Lob
    private byte [] data;

    public FilesAnoucements(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public FilesAnoucements(byte[] data) {
        this.data = data;
    }
}
