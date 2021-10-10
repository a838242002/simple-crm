package com.example.crm.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "company")
public class Company implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    String address;

    @CreatedBy
    @Column(name = "created_by")
    String createdBy;

    @CreatedDate
    @Column(name = "created_at")
    Timestamp createdAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    String updatedBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    Timestamp updatedAt;
}
