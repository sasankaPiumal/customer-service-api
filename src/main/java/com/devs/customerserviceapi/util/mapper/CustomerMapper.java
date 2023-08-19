package com.devs.customerserviceapi.util.mapper;

import com.devs.customerserviceapi.dto.core.CustomerDto;
import com.devs.customerserviceapi.dto.response.ResponseCustomerDto;
import com.devs.customerserviceapi.entity.Customer;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerDto dto);

    ResponseCustomerDto toResponseCustomerDto(Customer customer);

    List<ResponseCustomerDto> toResponseCustomerDtoList(Page<Customer> customers);
}
