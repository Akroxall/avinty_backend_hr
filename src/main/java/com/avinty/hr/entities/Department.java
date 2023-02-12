package com.avinty.hr.entities;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.avinty.hr.validators.ManagerPositonValidation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "departments")
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private @ManagerPositonValidation Employee manager;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Employee> employees;

}
