package com.iesjuanbosco.ejemploweb.service;

import com.iesjuanbosco.ejemploweb.entity.Producto;
import com.iesjuanbosco.ejemploweb.repository.CategoriaRepository;
import com.iesjuanbosco.ejemploweb.repository.ComentarioRepository;
import com.iesjuanbosco.ejemploweb.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {



    private ProductoRepository productoRepository;
    private CategoriaRepository categoriaRepository;
    private ComentarioRepository comentarioRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, ComentarioRepository comentarioRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.comentarioRepository = comentarioRepository;
    }
   public List<Producto> obtenerProductos(){
        try{
            List<Producto> productos = this.productoRepository.findAll();
            return productos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

   }

    public Optional<Producto> obtenerProducto(Long id){
        try {
            Optional<Producto> producto = productoRepository.findById(id);
            return producto;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void insertarProducto(Producto producto){
        try {

            //Si no ha habido errores de validación insertamos los datos en la BD
            productoRepository.save(producto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void editarProducto(Long id, Producto producto){
        try {
            producto.setId(id);
            productoRepository.save(producto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void eliminarProducto(Long id){
        try {
            productoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
