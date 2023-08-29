package com.busanit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class BoardAttach  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ano;
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}
