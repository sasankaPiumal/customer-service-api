package com.devs.customerserviceapi.service.impl;

import com.devs.customerserviceapi.dto.core.CustomerDto;
import com.devs.customerserviceapi.dto.request.RequestCustomerDto;
import com.devs.customerserviceapi.dto.response.ResponseCustomerDto;
import com.devs.customerserviceapi.dto.response.paginated.model.CustomerPaginatedDto;
import com.devs.customerserviceapi.entity.Customer;
import com.devs.customerserviceapi.exception.ClassNotFoundException;
import com.devs.customerserviceapi.exception.EntryNotFoundException;
import com.devs.customerserviceapi.repo.CustomerRepo;
import com.devs.customerserviceapi.service.CustomerService;
import com.devs.customerserviceapi.util.mapper.CustomerMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepo customerRepo, CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
    }


    @Override
    public ResponseCustomerDto createCustomer(RequestCustomerDto customerRequestDto) {
        CustomerDto customerDto = new CustomerDto(
                new Random().nextInt(1001),
                new Random().nextInt(10001),
                customerRequestDto.getName(),
                customerRequestDto.getAddressId(),
                customerRequestDto.getSalary(),
                true
        );

        Customer customer = customerMapper.toCustomer(customerDto);
        customerRepo.save(customer);

        return customerMapper.toResponseCustomerDto(customer);
    }

    @Override
    public ResponseCustomerDto findCustomer(long id) {
        Optional<Customer> selectedCustomer = customerRepo.findByPublicId(id);
        if (selectedCustomer.isPresent()) {
            return customerMapper.toResponseCustomerDto(selectedCustomer.get());
        }
        throw new EntryNotFoundException("Not Found!");
    }

    @Override
    public ResponseCustomerDto updateCustomer(long id, RequestCustomerDto customerRequestDto) {
        Optional<Customer> selectedCustomer = customerRepo.findByPublicId(id);
        if (selectedCustomer.isPresent()) {
            selectedCustomer.get().setName(customerRequestDto.getName());
            selectedCustomer.get().setSalary(customerRequestDto.getSalary());

            customerRepo.save(selectedCustomer.get());
            return customerMapper.toResponseCustomerDto(selectedCustomer.get());
        }
        throw new ClassNotFoundException("Customer Not Found!");
    }

    @Override
    public void deleteCustomer(long id) {
        customerRepo.deleteByPublicId(id);
    }

    @Override
    public CustomerPaginatedDto getAllCustomers(int page, int size, String searchText) {
        Page<Customer> customers = customerRepo.searchAllByNameOrAddressId(searchText, PageRequest.of(page, size));
        System.out.println("Customers Entity : " + customers);
        List<ResponseCustomerDto> selectedCustomerList = customerMapper.toResponseCustomerDtoList(customers);
        long searchedCount = customerRepo.CountDataSearchText(searchText);
        for (Customer cust : customers) {
            selectedCustomerList.add(new ResponseCustomerDto(
                    cust.getPublicId(),
                    cust.getName(),
                    cust.getAddressId(),
                    cust.getSalary(),
                    cust.isActiveState()
            ));
        }
        return new CustomerPaginatedDto(searchedCount, selectedCustomerList);
    }


}
