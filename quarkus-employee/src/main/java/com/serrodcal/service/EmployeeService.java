package com.serrodcal.service;

import com.serrodcal.domain.Employee;
import com.serrodcal.repository.EmployeeRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EmployeeService {

    @Inject
    EmployeeRepository employeeRepository;

    public Multi<Employee> getEmployees() { return employeeRepository.getEmployees(); }

    public Uni<Employee> getEmployee(Long id) { return employeeRepository.getEmployee(id); }

    public Multi<Employee> getEmployeesByDept(Long deptId) { return employeeRepository.getEmployeesByDept(deptId); }

    public Uni<Long> createEmployee(Employee employee) { return employeeRepository.saveEmployee(employee); }

    public Uni<Boolean> updateEmployee(Employee employee) { return employeeRepository.updateEmployee(employee); }

    public Uni<Boolean> unassignEmployees(Long deptId) { return employeeRepository.unassignEmployees(deptId); }

    public Uni<Boolean> deleteEmployee(Long id) { return employeeRepository.deleteEmployee(id); }

}
