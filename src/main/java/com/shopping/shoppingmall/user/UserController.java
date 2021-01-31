package com.shopping.shoppingmall.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.Console;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;
    }

    //모든 유저 보기
    @GetMapping("/users") //localhost:8088/users 하면 이게 실행됨
    public MappingJacksonValue retrieveAllUsers(){
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter); //Filtering

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }
    

    //GET /users/1 or /users/10 or /users/이름
    @GetMapping("/users/{name}") //뒤에 이름 입력하면 그사람 아이디 검색
    public Resource<User> retrieveUser(@PathVariable String name){
        User user = service.findOne(name);

        if (user ==null){ //존재하지 않는 데이터 추가하면 예외 처리
            throw new UserNotFoundException(String.format("%s is not found",name ));
        }

        //나중에 보여주고 싶은 정보만 보여줄때 사용① public 뒤에 인자 바꿔야댐
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
//                .filterOutAllExcept("id","name");
//
//        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter); //Filtering
//
//        MappingJacksonValue mapping = new MappingJacksonValue(user);
//        mapping.setFilters(filters);
//        return mapping;

        //Hateoas
        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users")); //삭제하기나 추가적으로 할 수 있는 기능을 보여줄 수 있음음

       return resource;
    }

    @PostMapping("/users") // 유저 추가해주는 것
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if (user == null){
            throw new UserNotFoundException(String.format("id[%s] not found",id));
        }
    }

//    @PutMapping("/users/{id}") //링크에 숫자를 더해줘야하는 버젼
//    public void editUser(@RequestBody User user, @PathVariable int id){
//        editUser(user,id);
//
//        if (user == null){
//            throw new UserNotFoundException(String.format("id[%s] not found",id));
//        }
//    }
//    PUT - http://localhost:8088/users/2
//    {
//        "name": "New User",
//            "joinDate": "2021-01-14T10:55:11.877+00:00"
//    }
//    이렇게 해주면 2번 자료가 New User로 바뀜

//    @PutMapping("/users/{id}")
//    public ResponseEntity<Object> updateStudent(@RequestBody User user, @PathVariable int id) {
//        Optional<User> userOptional = userRepository.findById(id);
//
//        if (!userOptional.isPresent())
//            return ResponseEntity.notFound().build();
//
//        user.setId(id);
//
//        userRepository.save(user);
//
//        return ResponseEntity.noContent().build();
//    }
}

