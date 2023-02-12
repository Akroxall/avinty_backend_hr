package com.avinty.hr.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "employees")@TypeDef(name = "positionType", typeClass = PosititonEnumConversion.class)
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Type(type = "positionType")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "created_at")
    private Instant createdAt;

    @OneToOne
    @JoinColumn(name = "created_by")
    private Employee createdBy;

    @Column(name = "modified_at")
    private Instant modifiedAt;

    @OneToOne
    @JoinColumn(name = "modified_by")
    private Employee modifiedBy;

}
