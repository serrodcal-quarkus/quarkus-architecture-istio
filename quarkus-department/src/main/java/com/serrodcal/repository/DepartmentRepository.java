package com.serrodcal.repository;

import com.serrodcal.dao.DepartmentDao;
import com.serrodcal.domain.Department;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DepartmentRepository {

    @Inject
    DepartmentDao departmentDao;

    public Multi<Department> getDepartments() {
        return departmentDao.findAll();
    }

    public Uni<Department> getDepartment(Long id) {
        return departmentDao.findById(id);
    }

    public Uni<Long> saveDepartment(Department department) { return departmentDao.save(department.name); }

    public Uni<Boolean> updateDepartment(Department department) { return departmentDao.update(department.id, department.name); }

    public Uni<Boolean> deleteDepartment(Long id) { return departmentDao.delete(id); }

}
