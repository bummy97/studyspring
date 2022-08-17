package com.ybkim.springstudy.springboot.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA 클래스들이 BaseTimeEntity을 상속할 경우 필드들도 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createDate; //Entity가 생성되어 저장될 때 시간이 자동 저장된다.

    @LastModifiedDate
    private LocalDateTime modifiedDate; //조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
}
