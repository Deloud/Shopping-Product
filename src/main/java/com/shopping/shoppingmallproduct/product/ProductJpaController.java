package com.shopping.shoppingmallproduct.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;


//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping("")
public class ProductJpaController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public List<Product> retrieveAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> retrieveProduct(@PathVariable int id){
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()){
            throw new ProductNotFoundException(String.format("ID[%s] not found", id));
        }

//        Resource<Product> resource = new Resource<>(product.get());
//        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllProducts());
//        resource.add(linkTo.withRel("all-products"));

        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){
        productRepository.deleteById(id);
    }


    @PostMapping("")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        Product savedProduct = productRepository.save(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Product product, @PathVariable int id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (!productOptional.isPresent())
            return ResponseEntity.notFound().build();

        product.setId(id);

        productRepository.save(product);

        return ResponseEntity.noContent().build();
    }

}