package com.devs.customerserviceapi.api;

import com.devs.customerserviceapi.dto.request.RequestUserDto;
import com.devs.customerserviceapi.service.UserService;
import com.devs.customerserviceapi.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(params = "role")
    public ResponseEntity<StandardResponse> createUser(@RequestBody RequestUserDto dto,@RequestParam String role) {
        userService.createUser(dto,role);
   return new ResponseEntity<>(
           new StandardResponse(201,"User Saved Successfully!",null), HttpStatus.CREATED
   );
    }

}
