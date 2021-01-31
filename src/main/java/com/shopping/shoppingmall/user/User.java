package com.shopping.shoppingmall.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
//@JsonFilter("UserInfo") //보여주고 싶은 정보만 패키징해서 사용①
//@JsonIgnoreProperties(value={"phone_number","address","email"}) //일반 유저가 검색하면 이름이랑 id만 보여지게
public class User {
    private Integer id;

    @Size(min=2, message="Name은 2글자 이상 입력해 주세요")
    private String name;
    private String phone_number;
    private String address;
    private String email;
}
