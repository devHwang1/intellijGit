package com.busanit.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass // 공통 매핑 정보가 필요할 때 사용하는 어노테이션
// 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
@Setter
@Getter
public class BaseEntity extends BaseTimeEntity{

    @CreatedBy
    @Column(updatable = false)
    private  String createBy;

    @LastModifiedBy
    private  String modifyBy;

}
