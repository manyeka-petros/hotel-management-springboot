package com.mapoto.HotelManagement.Filez.Entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

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
    private String roomNumber;
    private String cost;

    public FilesResource(String filename, String filetype, byte[] data, String category, String roomNumber, String cost) {
        this.filename = filename;
        this.filetype = filetype;
        this.data = data;
        this.category = category;
        this.roomNumber = roomNumber;
        this.cost = cost;
    }
}
