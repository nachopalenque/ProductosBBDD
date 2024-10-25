package com.iesjuanbosco.ejemploweb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Fotos {
    @Id     //Esta anotación especifica que este campo va a ser la clave principal de la tabla en la base de datos
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ruta;
    @ManyToOne(targetEntity = Producto.class)
    @JoinColumn(name = "id_producto")
    @NotNull
    private Producto producto;
}
