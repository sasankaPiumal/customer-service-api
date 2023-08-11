package com.devs.customerserviceapi.dto.response.paginated.model;

import com.devs.customerserviceapi.dto.response.ResponseCustomerDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPaginatedDto {

    private long dataCount;
    private List<ResponseCustomerDto> list;

}
