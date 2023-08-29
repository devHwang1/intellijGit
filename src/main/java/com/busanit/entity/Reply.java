package com.busanit.entity;

import com.busanit.service.ReplyService;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
@Builder
@AllArgsConstructor
@Getter
@ToString(exclude = "board")
@NoArgsConstructor
@Setter
public class Reply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String text;
    private String replyer;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Board board;

}
