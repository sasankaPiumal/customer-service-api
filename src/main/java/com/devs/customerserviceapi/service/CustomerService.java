package com.devs.customerserviceapi.service;

import com.devs.customerserviceapi.dto.request.RequestCustomerDto;
import com.devs.customerserviceapi.dto.response.ResponseCustomerDto;

public interface CustomerService {
    public ResponseCustomerDto createCustomer(RequestCustomerDto customerRequestDto);

    public ResponseCustomerDto findCustomer(long id);

    public ResponseCustomerDto updateCustomer(long id, RequestCustomerDto customerRequestDto);

    public void deleteCustomer(long id);
}
