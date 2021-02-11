package com.shopping.shoppingmallproduct.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(description = "물건 정보를 위한 도메인 객체")
public class Product {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message="Name은 2글자 이상 입력해 주세요")
    @ApiModelProperty(notes = "물건 이름을 입력해주세요") //SWAGGER
    private String name;
    
    @ApiModelProperty(notes = "가격을 입력해주세요")
    private Integer price;

}