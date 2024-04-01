package dao.impl;

import dao.DepartmentDao;
import db.DataBase;
import model.Department;
import model.Doctor;
import model.Hospital;
import service.GenericService;

import java.util.List;
import java.util.Optional;

import static model.GenericId.departmentId;

public class DepartmentDaoImpl implements DepartmentDao , GenericService<Department> {
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
//        for (Hospital hospital: DataBase.hospitals){
//            if (hospital.getId().equals(id)){
//               return hospital.getDepartments();
//            }
//        }
//        return null;
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(Hospital::getDepartments)
                .orElse(null);
    }


    @Override
    public Department findDepartmentByName(String name) {
//        for (Hospital hospital : DataBase.hospitals){
//            for (Department department :hospital.getDepartments() ){
//                if (department.getDepartmentName().equals(name)){
//                    return department;
//                }
//
//            }
//        }
//        return null;
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getDepartmentName().equals(name))
                .findFirst()
                .orElse(null);

    }

    @Override
    public String add(Long hospitalId, Department department) {
//       for (Hospital hospital:DataBase.hospitals){
//           if (hospital.getId().equals(hospitalId)){
//               hospital.getDepartments().add(department);
//           return "added";
//           }
//       }
//        return "Not found";
        Optional<Hospital> hospitalOptional = DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(hospitalId))
                .findFirst();

        if (hospitalOptional.isPresent()) {
            hospitalOptional.get().getDepartments().add(department);
            return "added";
        } else {
            return "Not found";
        }
    }



    @Override
    public String removeById(Long id, Long department) {
//        for (Hospital getHospital : DataBase.hospitals) {
//            if (getHospital.getId().equals(id)){
//                for (Department department1 : getHospital.getDepartments()) {
//                    if (department1.getId().equals(department)){
//                        getHospital.getDepartments().remove(department1);
//                        return "Success deleted!";
//                    }
//                }
//            }
//        }
//        return "Hospital Not-Found!";
        Optional<Hospital> hospitalOptional = DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst();

        if (hospitalOptional.isPresent()) {
            List<Department> departments = hospitalOptional.get().getDepartments();
            Optional<Department> department1Optional = departments.stream()
                    .filter(department1 -> department1.getId().equals(departmentId))
                    .findFirst();

            if (department1Optional.isPresent()) {
                departments.remove(department1Optional.get());
                return "deleted";
            }
        }
        return null;
    }




    @Override
    public String updateById(Long id, Department departmentNew) {
//        for (Hospital getHospital : DataBase.hospitals) {
//            for (Department department : getHospital.getDepartments()) {
//               if (department.getId().equals(id)){
//                   department.setDepartmentName(departmentNew.getDepartmentName());
//                   System.out.println("success upd");
//               }
//            }
//        }
//        System.out.println("Hospital Not-Found!");
//        return null;
        Optional<Hospital> hospitalOptional = DataBase.hospitals.stream()
                .filter(hospital -> hospital.getDepartments().stream().anyMatch(department -> department.getId().equals(id)))
                .findFirst();

        if (hospitalOptional.isPresent()) {
            List<Department> departments = hospitalOptional.get().getDepartments();
            Department targetDepart = departments.stream()
                    .filter(dep -> dep.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (targetDepart != null) {
                targetDepart.setDepartmentName(departmentNew.getDepartmentName());
                return "success upd";
            }
        }
        return null;

    }
    }

