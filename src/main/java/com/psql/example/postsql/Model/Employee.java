package com.psql.example.postsql.Model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @Column(name = "emp_name")
    private String empName;
    @Column(name = "emp_last_name")
    private String empLastName;
    @Column(name = "emp_mail")
    private String empMail;


    public Employee(String empName, String empLastName, String empMail) {
        this.empName = empName;
        this.empLastName = empLastName;
        this.empMail = empMail;
    }

    public Employee() {

    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public String getEmpMail() {
        return empMail;
    }

    public void setEmpMail(String empMail) {
        this.empMail = empMail;
    }
}
