package dao.impl;

import dao.DoctorDao;
import db.DataBase;
import model.Department;
import model.Doctor;
import model.Hospital;
import service.GenericService;

import javax.print.Doc;
import javax.xml.crypto.Data;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static model.GenericId.doctorId;

public class DoctorDaoImpl implements DoctorDao , GenericService<Doctor>  {
    @Override
    public Doctor findDoctorById(Long id) {
//      for (Hospital hospital: DataBase.hospitals){
//          for (Doctor doctor : hospital.getDoctors()){
//              if (doctor.getId().equals(id)){
//                  return doctor;
//              }
//          }
//      }
//        return null;
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDoctors().stream())
                .filter(doctor -> doctor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(departmentId)) {
                    for (Doctor doctor : hospital.getDoctors()) {
                        if (doctorsId.contains(doctor.getId())) {
                            department.getDoctors().add(doctor);
                            hospital.getDoctors().remove(doctor);
                            return "Success assign";
                        }
                    }
                }
            }
        }
        return "Not-Found!";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
//       for (Hospital hospital : DataBase.hospitals){
//           if (hospital.getId().equals(id)){
//               return hospital.getDoctors();
//           }
//       }
//        return null;
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(Hospital::getDoctors)
                .orElse(null);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
//        for (Hospital hospital : DataBase.hospitals){
//            for (Department department: hospital.getDepartments()){
//                if (department.getId().equals(id)){
//                    return hospital.getDoctors();
//                }
//            }
//        }
       // return null;
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getId().equals(id))
                .findFirst()
                .map(Department::getDoctors)
                .orElse(Collections.emptyList());
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
//        for (Hospital hospital : DataBase.hospitals){
//            if (hospital.getId().equals(hospitalId)){
//                hospital.getDoctors().add(doctor);
//                return "added";
//            }
//        }
//        return null;
        Optional<Hospital> hospitalOptional = DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(hospitalId))
                .findFirst();

        if (hospitalOptional.isPresent()) {
            hospitalOptional.get().getDoctors().add(doctor);
            return "added";
        } else {
            return null;
        }
    }

    @Override
    public String removeById(Long id, Long doctorId) {
//        for (Hospital hospital : DataBase.hospitals) {
//            if (hospital.getId().equals(id)) {
//                for (Doctor doctor1 : hospital.getDoctors()) {
//                    if (doctor1.getId().equals(doctorId)) {
//                        hospital.getDoctors().remove(doctor1);
//                        return "deleted";
//                    }
//                }
//            }
//        }return null;
        Optional<Hospital> hospitalOptional = DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst();

        if (hospitalOptional.isPresent()) {
            List<Doctor> doctors = hospitalOptional.get().getDoctors();
            Optional<Doctor> doctorOptional = doctors.stream()
                    .filter(doctor -> doctor.getId().equals(doctorId))
                    .findFirst();

            if (doctorOptional.isPresent()) {
                doctors.remove(doctorOptional.get());
                return "deleted";
            }
        }
        return null;
    }

    @Override
    public String updateById(Long id, Doctor doctor){
//        for (Hospital hospital : DataBase.hospitals){
//
//            for (Doctor doctor1 : hospital.getDoctors()){
//                if (doctor1.getId().equals(id)){
//                    doctor1.setExperienceYear(doctor.getExperienceYear());
//                    doctor1.setGender(doctor.getGender());
//                    doctor1.setLastName(doctor.getLastName());
//                    doctor1.setFirstName(doctor.getFirstName());
//                    return "success upd";
//                }
//            }
//        }
//        return null;
        Optional<Hospital> hospitalOptional = DataBase.hospitals.stream()
                .filter(hospital -> hospital.getDoctors().stream().anyMatch(doc -> doc.getId().equals(id)))
                .findFirst();

        if (hospitalOptional.isPresent()) {
            List<Doctor> doctors = hospitalOptional.get().getDoctors();
            Doctor targetDoctor = doctors.stream()
                    .filter(doc -> doc.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (targetDoctor != null) {
                targetDoctor.setExperienceYear(doctor.getExperienceYear());
                targetDoctor.setGender(doctor.getGender());
                targetDoctor.setLastName(doctor.getLastName());
                targetDoctor.setFirstName(doctor.getFirstName());
                return "success upd";
            }
        }
        return null;
    }
}
