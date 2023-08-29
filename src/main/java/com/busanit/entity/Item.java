package com.busanit.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itmeNm;
    private int price;              //가격
    private  int stockNumber;       //재고수량
    private  String itemDetial;     // 상품 상세 설명

}
