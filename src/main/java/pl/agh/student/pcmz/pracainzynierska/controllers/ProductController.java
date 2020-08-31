package pl.agh.student.pcmz.pracainzynierska.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.student.pcmz.pracainzynierska.exception.ResourceNotFoundException;
import pl.agh.student.pcmz.pracainzynierska.models.Product;
import pl.agh.student.pcmz.pracainzynierska.repositories.ProductRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        String url = "https://rejestr.io/api/v1/krs/5239";
//        HttpGet httpPost = new HttpGet (url);
//
//        httpPost.addHeader("Authorization" , "a1236708-27c8-49ef-ad84-88ba951dd908");
//        String result = null;
//        try {
//            HttpResponse response = httpclient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            InputStream instream = entity.getContent();
//            result = convertStreamToString(instream);
//            JSONObject myObject = new JSONObject(result);
//
//            System.out.println(myObject);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId,
                                                 @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("productId not found for this id :: " + productId));

        product.setProductName(productDetails.getProductName());
        product.setUnit(productDetails.getUnit());
        product.setQuantityPerUnit(productDetails.getQuantityPerUnit());
        product.setUnitPrice(productDetails.getUnitPrice());
        product.setQuantity(productDetails.getQuantity());
        product.setAvailableColours(productDetails.getAvailableColours());
        product.setIpath(productDetails.getIpath());

        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
