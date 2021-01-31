package com.shopping.shoppingmall.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin") //여기서는 앞에 무조건 /admin이 붙음
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service){
        this.service = service;
    }

    //모든 유저 보기
    @GetMapping("/users") //localhost:8088/users 하면 이게 실행됨
    public MappingJacksonValue retrieveAllUsers(){
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","phone_number","address","email");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter); //Filtering

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }

    //GET /users/1 or /users/10 or /users/이름
    @GetMapping("/users/{name}") //뒤에 이름 입력하면 그사람 아이디 검색
    public MappingJacksonValue retrieveUser_name(@PathVariable String name){
        User user = service.findOne(name);

        if (user ==null){ //존재하지 않는 데이터 추가하면 예외 처리
            throw new UserNotFoundException(String.format("%s is not found",name ));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","phone_number","address","email");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter); //Filtering

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);
        return mapping;
    }
}

