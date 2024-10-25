package com.iesjuanbosco.ejemploweb.service;

import com.iesjuanbosco.ejemploweb.entity.Categoria;
import com.iesjuanbosco.ejemploweb.entity.Fotos;
import com.iesjuanbosco.ejemploweb.entity.Producto;
import com.iesjuanbosco.ejemploweb.repository.CategoriaRepository;
import com.iesjuanbosco.ejemploweb.repository.FotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FotoService {

    @Autowired
    private FotosRepository fotosRepository;
    private static final List<String> TIPOS_PERMITIDOS = List.of("image/jpeg", "image/png", "image/gif", "image/avif", "image/webp");
    private static final long MAX_FILE_SIZE = 10000000;
    private static final String UPLOADS_DIRECTORY = "uploads/imagesProducto";

    public List<Fotos> obtenerFotos(){
        try{
            List<Fotos> fotos = this.fotosRepository.findAll();
            return fotos;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Fotos> obtenerFotos(Long id){
        try{
            Optional<Fotos> fotosSeleccionada = fotosRepository.findById(id);
            return fotosSeleccionada;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void eliminarCategoria(Long id) {
        fotosRepository.deleteById(id);
    }

    public void guardarFoto(Fotos foto, Producto producto, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Archivo no seleccionado");
        }
        if (!TIPOS_PERMITIDOS.contains(file.getContentType())) {
            throw new IllegalArgumentException("El archivo seleccionado no es una imagen.");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Archivo demasiado grande. Sólo se admiten archivos < 10MB");
        }

        UUID nombreUnico = UUID.randomUUID();
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String nuevoNombreFoto = nombreUnico + extension;
        Path ruta = Paths.get(UPLOADS_DIRECTORY + File.separator + nuevoNombreFoto);

        try {
            byte[] contenido = file.getBytes();

            if (!Files.exists(ruta)){
                Path crearRuta = Paths.get(UPLOADS_DIRECTORY);
                Files.createDirectories(crearRuta);
            }
            Files.write(ruta, contenido);
            foto.setRuta(UPLOADS_DIRECTORY + File.separator + nuevoNombreFoto);
            foto.setProducto(producto);
            fotosRepository.save(foto);
        } catch (
                IOException e) {
            throw new RuntimeException("Error al guardar archivo", e);
        }
    }

}
