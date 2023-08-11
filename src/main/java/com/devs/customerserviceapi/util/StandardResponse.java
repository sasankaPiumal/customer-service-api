package com.devs.customerserviceapi.util;

import lombok.*;
import org.springframework.stereotype.Service;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse {
    private int code;
    private String message;
    private Object data;

}
