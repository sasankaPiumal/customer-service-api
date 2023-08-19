package com.devs.customerserviceapi.api;

import com.devs.customerserviceapi.dto.request.RequestCustomerDto;
import com.devs.customerserviceapi.dto.response.paginated.model.CustomerPaginatedDto;
import com.devs.customerserviceapi.service.CustomerService;
import com.devs.customerserviceapi.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/customers")
public class CustomerController {

    protected final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody RequestCustomerDto customerRequestDto) {

        var savedCustomer = customerService.createCustomer(customerRequestDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "Customer Saved!", savedCustomer), HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> findCustomer(@PathVariable long id) {

        var searchedCustomer = customerService.findCustomer(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success!", searchedCustomer), HttpStatus.OK
        );
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody RequestCustomerDto customerRequestDto,
                                                           @RequestParam long id) {

        var updatedCustomer = customerService.updateCustomer(id, customerRequestDto);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Customer updated successfully!", updatedCustomer), HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Deleted Successful!", null), HttpStatus.OK

        );
    }

    @GetMapping(
            path = "/all",
            params = {"page","size","searchText"}

    )
    public ResponseEntity<StandardResponse> getAllCustomerData(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String searchText){
        CustomerPaginatedDto allCustomers = customerService.getAllCustomers(page,size,searchText);

        return new ResponseEntity<>(
            new StandardResponse(200,"Success!",allCustomers),HttpStatus.OK

        );

    }

}

