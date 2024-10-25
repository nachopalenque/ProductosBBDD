package com.iesjuanbosco.ejemploweb.repository;
import com.iesjuanbosco.ejemploweb.entity.Comentario;
import com.iesjuanbosco.ejemploweb.entity.Fotos;
import com.iesjuanbosco.ejemploweb.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotosRepository extends JpaRepository<Fotos,Long> {
    List<Producto> findByProducto(Producto producto);


}
