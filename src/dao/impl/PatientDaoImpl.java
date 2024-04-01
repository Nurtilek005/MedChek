package dao.impl;

import dao.PatientDao;
import db.DataBase;
import model.Hospital;
import model.Patient;
import service.GenericService;

import java.util.*;
import java.util.stream.Collectors;

import static model.GenericId.patientId;

public class PatientDaoImpl implements PatientDao , GenericService<Patient> {
    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
//     for (Hospital hospital: DataBase.hospitals){
//         if (hospital.getId().equals(id)){
//             hospital.getPatients().addAll(patients);
//         }
//     }
//        return null;
        DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .forEach(hospital -> hospital.getPatients().addAll(patients));
        return null;

    }

    @Override
    public Patient getPatientById(Long id) {
//      for (Hospital hospital : DataBase.hospitals){
//          for (Patient patient : hospital.getPatients()){
//              if (patient.getId().equals(id)){
//                  return patient;
//              }
//          }
//      }
//        return null;
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .filter(patient -> patient.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Map<Integer, Patient> getPatientByAge(int age) {
//        Map<Integer,Patient> map = new HashMap<>();
//        for (Hospital hospital:DataBase.hospitals){
//            for (Patient patient : hospital.getPatients()){
//                if (patient.getAge()==age){
//                    map.put(patient.getAge(),patient);
//                    return map;
//                }
//            }
//        }
//        return null;
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .filter(patient -> patient.getAge() == age)
                .collect(Collectors.toMap(Patient::getAge, patient -> patient));
    }


    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
//        for (Hospital hospital : DataBase.hospitals) {
//
//
//            if (ascOrDesc.equalsIgnoreCase("asc")) {
//                Comparator<Patient> personComparator = Comparator.comparing(Patient::getAge);
//                hospital.getPatients().sort(personComparator);
//                return hospital.getPatients();
//            } else if (ascOrDesc.equalsIgnoreCase("desc")) {
//                Comparator<Patient> personComparator = Comparator.comparing(Patient::getAge).reversed();
//                hospital.getPatients().sort(personComparator);
//                return hospital.getPatients();
//            }
//            System.out.println("asc or desc");
//            return null;
//
//        } return null;
        if (ascOrDesc.equalsIgnoreCase("asc") || ascOrDesc.equalsIgnoreCase("desc")) {
            Comparator<Patient> personComparator = ascOrDesc.equalsIgnoreCase("asc") ?
                    Comparator.comparing(Patient::getAge) :
                    Comparator.comparing(Patient::getAge).reversed();

            return DataBase.hospitals.stream()
                    .flatMap(hospital -> hospital.getPatients().stream())
                    .sorted(personComparator)
                    .collect(Collectors.toList());
        } else {
            System.out.println("asc or desc");
            return null;
        }

    }

    @Override
    public String add(Long hospitalId, Patient patient) {
//for (Hospital hospital: DataBase.hospitals){
//    if (hospital.getId().equals(hospitalId)){
//        hospital.getPatients().add(patient);
//        return "added";
//    }
//}
//        return null;
        Optional<Hospital> hospitalOptional = DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(hospitalId))
                .findFirst();

        if (hospitalOptional.isPresent()) {
            Hospital hospital = hospitalOptional.get();
            hospital.getPatients().add(patient);
            return "added";
        } else {
            return null;
        }
    }

    @Override
    public String removeById(Long id, Long patient) {
//for (Hospital hospital : DataBase.hospitals){
//        for (Patient patient1 : hospital.getPatients()){
//            if (patient1.getId().equals(patient)){
//                hospital.getPatients().remove(patient1);
//                System.out.println("sc");
//            return "deleted";
//            }
//        }
//    }return null;
        for (Hospital hospital : DataBase.hospitals) {
            Iterator<Patient> iterator = hospital.getPatients().iterator();
            while (iterator.hasNext()) {
                Patient patient1 = iterator.next();
                if (patient1.getId().equals(patientId)) {
                    iterator.remove();
                    System.out.println("sc");
                    return "deleted";
                }
            }
        }
        return null;
}



    @Override
    public String updateById(Long id, Patient patient) {
//        for (Hospital hospital : DataBase.hospitals) {
//            for (Patient patient1 : hospital.getPatients()) {
//                if (patient1.getId().equals(id)) {
//                    patient1.setFirstName(patient.getFirstName());
//                    patient1.setAge(patient.getAge());
//                    patient1.setGender(patient.getGender());
//                    patient1.setLastName(patient.getLastName());
//                    return "sc update";
//                }
//            }
//        }return null;
        for (Hospital hospital : DataBase.hospitals) {
            for (Patient patient1 : hospital.getPatients()) {
                if (patient1.getId().equals(id)) {
                    patient1.setFirstName(patient.getFirstName());
                    patient1.setAge(patient.getAge());
                    patient1.setGender(patient.getGender());
                    patient1.setLastName(patient.getLastName());
                    return "sc update";
                }
            }
        }
        return null;
    }
      }



