package com.mapoto.HotelManagement.Filez.Entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FilesResource {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String fileId;
    private String filename;
    private String filetype;
    @Lob
    private byte[] data;
    private String category;

    public FilesResource(String filename, String filetype, byte[] data,String category) {
        this.filename = filename;
        this.filetype = filetype;
        this.data = data;
        this.category = category;
    }
}
