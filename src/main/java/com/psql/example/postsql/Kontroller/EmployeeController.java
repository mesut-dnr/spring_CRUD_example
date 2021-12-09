package com.psql.example.postsql.Kontroller;

import com.psql.example.postsql.Exception.ResourceNotFoundException;
import com.psql.example.postsql.Model.Employee;
import com.psql.example.postsql.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //GET
    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        return this.employeeRepository.findAll();
    }

    //GET EMPLOYEE BY ID
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long empId)
    throws ResourceNotFoundException {
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(()-> new ResourceNotFoundException("Bu ID'de Çalışan bulunamadı.::"+empId));

        return ResponseEntity.ok().body(employee);
    }
    // SAVE EMPLOYEE
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return this.employeeRepository.save(employee);
    }
    // UPDATE EMPLOYEE
    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id")Long empId
                            , @Validated @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'de Çalışan bulunamadı.::"+empId));
        employee.setEmpName(employeeDetails.getEmpName());
        employee.setEmpLastName(employeeDetails.getEmpLastName());
        employee.setEmpMail(employeeDetails.getEmpMail());
        return ResponseEntity.ok(this.employeeRepository.save(employee));
    }
    @DeleteMapping("employees/{id}")
    public Map<String,Boolean> deleteEmployee(@PathVariable(value = "id") Long empId) throws ResourceNotFoundException {
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'de Çalışan bulunamadı.::"+empId));
        this.employeeRepository.delete(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }


}
