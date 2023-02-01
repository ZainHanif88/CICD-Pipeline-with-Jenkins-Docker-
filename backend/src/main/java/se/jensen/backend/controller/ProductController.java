package se.jensen.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensen.backend.service.ProductService;
import se.jensen.backend.model.Product;
import se.jensen.backend.model.ColorVariant;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3010")
@RequestMapping("/api/v1")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){

        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping({"/products/{var}"})
    public ResponseEntity<Object> getProductByCategory(@PathVariable("var") String var){

        return switch (var) {
            case "hats", "tShirts", "jackets", "bags" ->
                    new ResponseEntity<>(productService.getProductByName(var), HttpStatus.OK);
            default -> new ResponseEntity<>(productService.getProductById(Integer.parseInt(var)), HttpStatus.OK);
        };

    }

    @PostMapping("/products/{category}")
    public ResponseEntity<Product> createProduct(@RequestBody Product product,
                                                 @PathVariable("category") String category){

        return new ResponseEntity<>(productService.addProduct(product, category),
                HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") int id ,
                                                @RequestBody Product product){

        int response = productService.updateProductMeta(id, product);
        return switch (response) {
            case -20 -> ResponseEntity.badRequest().body("Product wasn't fetched from the db");
            case 0 -> ResponseEntity.badRequest().body("Set product with empty title/description are not allowed");
            case -1 -> ResponseEntity.badRequest().body("Body is missing");
            case 1 -> ResponseEntity.ok().build();
            default -> ResponseEntity.internalServerError().body("Server error, might be some problem");
        };
    }

    @PostMapping("/products/{id}/variants")
    public ResponseEntity<ColorVariant> createColorVariant(@PathVariable("id") int id,
                                                           @RequestBody ColorVariant co){

        return new ResponseEntity<>(productService.addColorVariant(id, co),
                HttpStatus.OK);
    }

    @PutMapping("/products/{id}/variants/stock")
    public ResponseEntity<String> restockSizeVariant(@PathVariable("id") int id,
                                                     @RequestParam String size,
                                                     @RequestParam String color,
                                                     @RequestParam("quantity") int quantity){

        int response = productService.restockSizeVariant(id, size, color, quantity);
        return switch (response){
            case -2 -> ResponseEntity.badRequest().body("The sizes/colors can't be null or empty!");
            case -1 -> ResponseEntity.badRequest().body("Product wasn't fetched from the db!");
            case 1 -> ResponseEntity.ok().build();
            default -> ResponseEntity.internalServerError().body("Server error, might be some problem");
        };
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){

        int response = productService.deleteProduct(id);
        return switch (response){
            case -2 -> ResponseEntity.status(404).body("Product with this id not found!");
            case -1 -> ResponseEntity.badRequest().body("Product wasn't fetched from the db!");
            case 1 -> ResponseEntity.ok().build();
            default -> ResponseEntity.internalServerError().body("Server error, might be some problem");
        };
    }

    @DeleteMapping("/products/{productId}/variants/{colorV}")
    public ResponseEntity<String> deleteColorVariant(@PathVariable("productId") int productId,
                                                     @PathVariable("colorV") String colorV){

        int response = productService.deleteColorVariant(productId, colorV);
        return switch (response){
            case -1 -> ResponseEntity.badRequest().body("Product wasn't fetched from the db!");
            case 1 -> ResponseEntity.ok().build();
            default -> ResponseEntity.internalServerError().body("Server error, might be some problem");
        };
    }
}