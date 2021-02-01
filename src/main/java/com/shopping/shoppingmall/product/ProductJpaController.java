package com.shopping.shoppingmall.product;

import com.shopping.shoppingmall.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping("/jpa")
public class ProductJpaController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> retrieveAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Resource<Product> retrieveProduct(@PathVariable int id){
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()){
            throw new ProductNotFoundException(String.format("ID[%s] not found", id));
        }

        Resource<Product> resource = new Resource<>(product.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllProducts());
        resource.add(linkTo.withRel("all-products"));

        return resource;
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id){
        productRepository.deleteById(id);
    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        Product savedProduct = productRepository.save(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Product product, @PathVariable int id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (!productOptional.isPresent())
            return ResponseEntity.notFound().build();

        product.setId(id);

        productRepository.save(product);

        return ResponseEntity.noContent().build();
    }

}