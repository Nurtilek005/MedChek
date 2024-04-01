package service.impl;

import dao.DepartmentDao;
import dao.impl.DepartmentDaoImpl;
import dao.impl.DoctorDaoImpl;
import model.Department;
import service.DepartmentService;
import service.GenericService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService, GenericService<Department> {
    DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return departmentDao.getAllDepartmentByHospital(id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return departmentDao.findDepartmentByName(name);
    }

    @Override
    public String add(Long hospitalId, Department department) {
        return departmentDao.add(hospitalId, department);
    }

    @Override
    public String removeById(Long id, Long department) {
departmentDao.removeById(id, department);
  return "deleted";
    }

    @Override
    public String updateById(Long id, Department department) {
        return departmentDao.updateById(id, department);
    }
}
