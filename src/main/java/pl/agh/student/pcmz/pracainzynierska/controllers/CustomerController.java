package pl.agh.student.pcmz.pracainzynierska.controllers;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.student.pcmz.pracainzynierska.exception.ResourceNotFoundException;
import pl.agh.student.pcmz.pracainzynierska.models.Customer;
import pl.agh.student.pcmz.pracainzynierska.models.Product;
import pl.agh.student.pcmz.pracainzynierska.repositories.CustomerRepository;
import pl.agh.student.pcmz.pracainzynierska.repositories.ProductRepository;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customertId)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customertId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customertId));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
                                                 @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        customer.setName(customerDetails.getName());
        final Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/customerFromAPI/{id}")
    public ResponseEntity<String> customerFromAPI(@PathVariable(value = "id") Long krsId) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String url = "https://rejestr.io/api/v1/krs/"+krsId;
        HttpGet httpPost = new HttpGet (url);
        httpPost.addHeader("Authorization" , "a1236708-27c8-49ef-ad84-88ba951dd908");
        String result = null;
        HttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        InputStream instream = entity.getContent();
        result = convertStreamToString(instream);
        return new ResponseEntity<>(
                result,
                HttpStatus.OK);
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
