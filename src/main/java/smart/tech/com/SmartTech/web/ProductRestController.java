package smart.tech.com.SmartTech.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smart.tech.com.SmartTech.model.DTO.ProductDTO;
import smart.tech.com.SmartTech.model.domain.Product;
import smart.tech.com.SmartTech.services.interfaces.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> listProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productService.getProduct(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/admin/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id,productDTO)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
