package com.example.spring.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 1 (id), "nazwa", "opis", "link do obrazka produktu", cena
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Product { // klasa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nazwa produktu nie może byc pusta")
    @Size(min = 3, max = 255, message = "Nazwa produktu powinna mieścić się w przedziale od 3 do 255 znaków")
    private String name;

    @NotBlank(message = "Opis produktu nie może byc pusty")
    @Size(min = 10, max = 1023, message = "Nazwa produktu powinna mieścić się w przedziale od 10 do 1023 znaków")
    @Column(length = 1023, name = "description")
    private String desc;

    @NotBlank(message = "Link obraza dla produktu nie może byc pusty")
    @Size(min = 10, max = 1025, message = "Nazwa produktu powinna mieścić się w przedziale od 10 do 1025 znaków")
    @Column(length = 1025, name = "image")
    @Pattern(regexp = "[-a-zA-Z0-9@:%_\\+.~#?&//=]{2,256}\\.[a-z]{2,4}\\b(\\/[-a-zA-Z0-9@:%_\\+.~#?&//=]*)?")
    private String imgUri;

    @NotNull
    private BigDecimal price; // obiekty

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

}

// String -> null <- @NotNull
// String -> "" <- @NotEmpty
// String -> " Test" <- @NotBlank
