package com.bigdata.hadoop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
@Entity
public class Employee {

    @Id
    @Column(name = "emp_no")
    private Long empNo;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "job")
    private String job;

    @Column(name = "mgr")
    private Long mgr;

    @Column(name = "hire_date")
    private LocalDateTime hireDate;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "comm")
    private Long comm;

    @Column(name = "img")
    private String img;

    @ManyToOne
    @JoinColumn(name = "dept_no")
    @ToString.Exclude
    private Department department;
}
