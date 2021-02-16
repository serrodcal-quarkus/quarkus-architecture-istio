package com.serrodcal.service;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HRService {

    @Inject
    @RestClient
    EmployeeService employeeService;

    @Inject
    @RestClient
    DepartmentService departmentService;

    public Uni<Boolean> assignEmployeeToDept(Long employeeId, Long deptId) {

        return this.departmentService.getDepartment(deptId).onItem().transformToUni( dept -> {
            return this.employeeService.getEmployee(employeeId).onItem().transformToUni(empl -> {
                if (empl.deptId != dept.id) {
                    empl.deptId = dept.id;
                    return this.employeeService.updateEmployee(empl).onItem().transformToUni(result -> {
                        if (result.getStatus() == 200) {
                            return Uni.createFrom().item(true);
                        } else {
                            return Uni.createFrom().item(false);
                        }
                    });
                } else {
                    return Uni.createFrom().item(false);
                }
            });
        });

    }

    public Uni<Boolean> unassignEmployee(Long id) {
        return this.employeeService.getEmployee(id).onItem().transformToUni(empl -> {
            empl.deptId = null;
            return this.employeeService.updateEmployee(empl).onItem().transformToUni(result -> {
                if (result.getStatus() == 200) {
                    return Uni.createFrom().item(true);
                } else {
                    return Uni.createFrom().item(false);
                }
            });
        });
    }

    public Uni<Boolean> unassignEmployees(Long deptId) {
        return this.departmentService.getDepartment(deptId).onItem().transformToUni(dept -> {
            return this.employeeService.getEmployeesByDept(dept.id). onItem().transformToUni(employees ->{
                if (employees.size() > 0){
                    return this.employeeService.unassignEmployees(deptId).onItem().transformToUni(result -> {
                        if (result.getStatus() == 200) {
                            return Uni.createFrom().item(true);
                        } else {
                            return Uni.createFrom().item(false);
                        }
                    });
                } else {
                    return Uni.createFrom().item(false);
                }
            });
        });
    }

}
