package com.busanit.entity;

import com.busanit.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime orderDate;        //주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;       //주문상태

    private LocalDateTime regTime;

    private LocalDateTime updateTime;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private  Member member;
}
