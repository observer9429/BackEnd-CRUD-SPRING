package com.security.basico.basic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.security.basico.basic.dto.ProductDto;
import com.security.basico.basic.dto.ProductRequestDto;
import com.security.basico.basic.dto.PublicProductDto;
import com.security.basico.basic.dto.UpdateStockDto;
import com.security.basico.basic.entities.Product;
import com.security.basico.basic.entities.PublicProduct;
import com.security.basico.basic.repositories.ProductRepository;
import com.security.basico.basic.repositories.PublicProductRepository;



//se usa este service para los productos privados y publicos


@Service
public class ProductService {

    private final ProductRepository productRepository;//REPOSITORIO DE PRODUCTO PRIVADO
    private final PublicProductRepository publicProductRepository;

    public ProductService(ProductRepository productRepository,
                          PublicProductRepository publicProductRepository) {
        this.productRepository = productRepository;
        this.publicProductRepository = publicProductRepository;
    }

    

    //los metodos se ejecutan de izquierda a derecha, findall devuelve un arrays
    //stream, les dice que va aprocesarlo uno por uno
    // cada elemento lo pasa a la funcion publicDto que retorna el dto


    // PUBLICO
    public List<PublicProductDto> findAllPublicProducts() {
        return publicProductRepository.findAll()//nos devuelve List<PublicProduct>
                .stream() //transformar datos, sin modificar la lista original
                .map(this::publicToDto)//.map(p -> publicToDto(p)) ** map --> trasnbforma cada elemen
                .toList();
    }


    //traspasamos la data del objeto publicproduct a la clase dto para que no se anide el json
    //y lo hacemos apra no enviar entidades
    private PublicProductDto publicToDto(PublicProduct p) {
        return new PublicProductDto(
                p.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock()
        );
    }



    // PRIVADO
    public List<ProductDto> findAllPrivateProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::privateToDto)
                .toList();
    }


    private ProductDto privateToDto(Product p) {//convierte el PRODCUCT a productDTO
    return new ProductDto(
        p.getId(),
        p.getNombre(),
        p.getPrecioCompra(),
        p.getPrecioVenta(),
        p.getStock(),
        p.getProveedor()
    );

    }

    //producto privado
    public Optional<ProductDto> findProductById(Long id) {
    return  productRepository.findById(id)
            .map(this::privateToDto);
    }

    //producto publico  publicProductRepository

    public Optional<PublicProductDto> findProductPublicById(Long id) {
    return  publicProductRepository.findById(id)
            .map(this::publicToDto);
    }


    //crear
    public ProductDto save(ProductRequestDto dto) {//usamos una clase dto para validar
        //antes que se envia a la ddbb, si se valida le asignamos esos valores
        //a un objeto Product
    Product p = new Product();
    p.setNombre(dto.getNombre());
    p.setPrecioCompra(dto.getPrecioCompra());
    p.setPrecioVenta(dto.getPrecioVenta());
    p.setStock(dto.getStock());
    p.setProveedor(dto.getProveedor());

    //luego lo enviamos a la ddbb con el metodo save
    Product saved = productRepository.save(p);//guardamos el objeto p
    //nos devuelve el mismo objeto pero con id, de la misma ddbb, por eso ya tiene id
    return privateToDto(saved);
    }

    //actualziar stock
    public ProductDto updateStock(Long id, UpdateStockDto dto) {

    Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    product.setStock(dto.getStock());

    Product updated = productRepository.save(product);
    return privateToDto(updated);
    }


    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


   

   


    


    

}


