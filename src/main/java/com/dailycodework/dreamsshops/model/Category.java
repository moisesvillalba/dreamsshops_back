package com.dailycodework.dreamsshops.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Entity

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Relación inversa, opcional, si necesitas acceder a los productos desde la categoría
    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
