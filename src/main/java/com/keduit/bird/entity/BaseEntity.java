package com.keduit.bird.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//시간에 대한 클래스
@MappedSuperclass
@EntityListeners(AbstractMethodError.class)
@Getter
public class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime BoardCreatedTime;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime BoardUpDatedTime;
}
