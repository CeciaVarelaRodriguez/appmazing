package com.campusdual.appmazing.controller;

import com.campusdual.appmazing.api.IProductService;
import com.campusdual.appmazing.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController()
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public String testController(){
        return "Products controller works!";
    }

    @PostMapping
    public String testController(@RequestBody String name){
        return "Products controller works, " + name + "!";
    }

    @GetMapping(value = "/testMethod")
    public String testControllerMethod(){
        return "Products controller method works!";
    }

    @PostMapping(value = "/get")
    public ProductDto queryProduct(@RequestBody ProductDto productDto){
        return productService.queryProduct(productDto);
    }

    @GetMapping(value = "/getAll")
    public List<ProductDto> queryAllProducts(){
        return productService.queryAllProducts();
    }

    @PostMapping(value = "/add")
    public int addProduct(@RequestBody ProductDto product){
        return productService.insertProduct(product);
    }

    @PostMapping(value = "/addAndShow")
    public ProductDto addProductAndShow(@RequestBody ProductDto product){
        int idProduct = productService.insertProduct(product);
        ProductDto newProduct = new ProductDto();
        newProduct.setId(idProduct);
        return productService.queryProduct(newProduct);
    }

    @PutMapping(value = "/update")
    public int updateProduct(@RequestBody ProductDto productDto){
        return productService.updateProduct(productDto);
    }

    @DeleteMapping(value = "/delete")
    public int deleteProduct(@RequestBody ProductDto productDto){
        return productService.deleteProduct(productDto);
    }
}
