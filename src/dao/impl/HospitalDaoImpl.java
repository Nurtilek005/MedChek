package dao.impl;

import dao.HospitalDao;
import db.DataBase;
import model.Hospital;
import model.Patient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HospitalDaoImpl implements HospitalDao {
    @Override
    public String addHospital(Hospital hospital) {
//        DataBase.hospitals.add(hospital);
//        return "added";
        if (DataBase.hospitals.stream().anyMatch(h -> h.getId().equals(hospital.getId()))) {
            return "Госпиталь уже сушествует";
        } else {
            DataBase.hospitals.add(hospital);
            return "added";
        }
    }

    @Override
    public Hospital findHospitalById(Long id) {
//       for (Hospital hospital : DataBase.hospitals){
//           if (hospital.getId().equals(id)){
//               return hospital;
//           }
//       }
//        return null;
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Hospital> getAllHospital() {

        return DataBase.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
//       for (Hospital hospital : DataBase.hospitals){
//           if (hospital.getId().equals(id)){
//              return hospital.getPatients();
//           }
//       }
//        return null;
     return DataBase.hospitals.stream()
             .filter(hospital -> hospital.getId().equals(id))
            .findFirst()
        .map(Hospital::getPatients)
        .orElse(null);
    }

    @Override
    public String deleteHospitalById(Long id) {
//       for (Hospital hospital : DataBase.hospitals){
//           if (hospital.getId().equals(id)){
//               DataBase.hospitals.remove(hospital);
//               return "deleted";
//           }
//       }
//
//        return null;
        Optional<Hospital> hospitalOptional = DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst();

        if (hospitalOptional.isPresent()) {
            DataBase.hospitals.remove(hospitalOptional.get());
            return "deleted";
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
//        Map<String,Hospital> map = new HashMap<>();
//        for (Hospital hospital : DataBase.hospitals){
//            if (hospital.getAddress().equalsIgnoreCase(address)){
//                map.put(hospital.getAddress(),hospital);
//            }
//        }
//        return null;
//    }
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getAddress().equalsIgnoreCase(address))
                .collect(Collectors.toMap(Hospital::getAddress, Function.identity()));

}
}
