package com.serrodcal.service;

import com.serrodcal.domain.Department;
import com.serrodcal.repository.DepartmentRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DepartmentService {

    @Inject
    DepartmentRepository departmentRepository;

    public Multi<Department> getDepartments() { return departmentRepository.getDepartments(); }

    public Uni<Department> getDepartment(Long id) { return departmentRepository.getDepartment(id); }

    public Uni<Long> createDepartment(Department department) { return departmentRepository.saveDepartment(department); }

    public Uni<Boolean> updateDepartment(Department department) { return departmentRepository.updateDepartment(department); }

    public Uni<Boolean> deleteDepartment(Long id) { return departmentRepository.deleteDepartment(id); }

}
