package com.shopping.shoppingmall.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.List;
//bd3b0212-7a5a-4c0f-be45-3ac4bd106d83

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonFilter("UserInfo") //보여주고 싶은 정보만 패키징해서 사용①
//@JsonIgnoreProperties(value={"phone_number","address","email"}) //일반 유저가 검색하면 이름이랑 id만 보여지게

@ApiModel(description = "사용자-상세 정보를 위한 도메인 객체")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message="Name은 2글자 이상 입력해 주세요")
    @ApiModelProperty(notes = "사용자 이름을 입력해주세요") //SWAGGER
    private String name;
    
    @ApiModelProperty(notes = "사용자 휴대폰번호를 입력해주세요")
    private String phone_number;
    
    @ApiModelProperty(notes = "사용자 주소를 입력해주세요")
    private String address;
    
    @ApiModelProperty(notes = "사용자 이메일을 입력해주세요")
    private String email;

    //1대 다 속성 주기
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(int i, String name, String phone_number, String address, String email) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.email = email;
    }
}
