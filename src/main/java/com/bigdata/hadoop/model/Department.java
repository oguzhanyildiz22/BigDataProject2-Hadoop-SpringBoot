package com.bigdata.hadoop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
@Entity
public class Department {

    @Id
    @Column(name = "dept_no")
    private Long deptNo;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "location")
    private String location;

    @JsonIgnore
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Employee> employeeList = new ArrayList<>();
}
