package com.dailycodework.dreamsshops.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;
@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String filePaht;
    @Lob
    private Blob blob;
    private String downloadUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
}
