import dao.impl.DepartmentDaoImpl;
import model.*;
import service.*;
import service.impl.DepartmentServiceImpl;
import service.impl.DoctorServiceImpl;
import service.impl.HospitalServiceImpl;
import service.impl.PatientServiceImpl;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System .in);

        HospitalService hospitalService = new HospitalServiceImpl();

        DepartmentService departmentService = new DepartmentServiceImpl();
        GenericService<Department> departamentGenericService = new DepartmentServiceImpl();

        DoctorService doctorService = new DoctorServiceImpl();
        GenericService<Doctor> doctorGenericService = new DoctorServiceImpl();

        PatientService patientService = new PatientServiceImpl();
        GenericService<Patient> patientGenericService = new PatientServiceImpl();


        while (true){
            System.out.println("""
                    1.Hospital
                    2.Department
                    3.Doctor
                    4.Patient
                    5.Exit
                    """);
            switch (scanner.nextInt()){
                case 1:
                    boolean while1 = true;
                    while (while1) {
                        System.out.println("""
                                1.Add hospital
                                2.Find hospital by id
                                3.Get all hospital
                                4.Get all patient from hospital
                                5.Delete hospital by id
                                6.Get all hospital by address
                                7.Exit
                                       """);
                        switch (scanner.nextInt()) {
                            case 1:
                                Hospital hospital = new Hospital();
                                hospital.setId(GenericId.genericHospitalId());
                                System.out.println("Enter hospital name: ");
                                String name = scanner.next();
                                hospital.setHospitalName(name);
                                System.out.println("Enter hospital address: ");
                                String addres = scanner.next();
                                hospital.setAddress(addres);
                                System.out.println(hospitalService.addHospital(hospital));
                                break;

                            case 2:
                                System.out.println("Enter hospital id: ");
                                Long id = scanner.nextLong();
                                System.out.println(hospitalService.findHospitalById(id));
                                break;
                            case 3: System.out.println(hospitalService.getAllHospital());break;
                            case 4:
                                System.out.println("Enter hospital id: ");
                                Long iddd = scanner.nextLong();
                                System.out.println(hospitalService.getAllPatientFromHospital(iddd));
                                break;
                            case 5:
                                System.out.println("Enter hospital id: ");
                                Long idd = scanner.nextLong();
                                System.out.println(hospitalService.deleteHospitalById(idd));
                                break;
                            case 6:
                                System.out.println("Enter hospital address: ");
                                String address = scanner.next();
                                System.out.println(hospitalService.getAllHospitalByAddress(address));
                                break;
                            case 7: while1 = false; break;
                            default: System.out.println("number not correct!");break;
                        }
                    }
                    break;
                case 2:
                    boolean while2 = true;
                    while (while2){
                        System.out.println("""
                                1.Add department by hospital id
                                2.Delete department by hospital id
                                3.Update department by hospital id
                                4.Get all department by hospital id
                                5.Find department by name
                                6.Exit
                                """);
                        switch (scanner.nextInt()){
                            case 1->{
                                Department department  = new Department();
                                System.out.println("Enter hospital id: ");
                                Long id = scanner.nextLong();
                                department.setId(GenericId.genericDepartmentId());
                                System.out.println("Enter department name: ");
                                String name = scanner.next();
                                department.setDepartmentName(name);
                                System.out.println(departamentGenericService.add(id, department));
                            }
                            case 2->{
                                System.out.println("Enter hospital id: ");
                                Long id = scanner.nextLong();
                                System.out.println("Enter department id: ");
                                Long idd = scanner.nextLong();
                                System.out.println(departamentGenericService.removeById(id, idd));
                            }
                            case 3->{
                                Department department = new Department();
                                System.out.println("Enter department id: ");
                                Long did = scanner.nextLong();
                                System.out.println("Enter department new name: ");
                                String  name = scanner.next();
                                department.setDepartmentName(name);
                                System.out.println(departamentGenericService.updateById( did, department));
                            }
                            case 4->{
                                System.out.println("Enter hospital id: ");
                                Long id = scanner.nextLong();
                                System.out.println(departmentService.getAllDepartmentByHospital(id));
                            }
                            case 5->{
                                System.out.println("Enter department name: ");
                                String name  = scanner.next();
                                System.out.println(departmentService.findDepartmentByName(name));
                            }
                            case 6-> while2= false;
                            default -> System.out.println("number not found!");
                        }
                    }
                    break;
                case 3:
                    boolean while3 = true;
                    while (while3){
                        System.out.println("""
                              1.Add doctor by hospital id
                              2.Delete doctor by hospital id
                              3.Update doctor by hospital id
                              4.Find doctor by id
                              5.Assign doctor to department by id
                              6.Get all doctor by hospital id
                              7.Get all doctor by department id
                              8.Exit
                                """);
                        switch (scanner.nextInt()){
                            case 1->{
                                Doctor doctor = new Doctor();
                                doctor.setId(GenericId.genericDoctorId());
                                System.out.println("Enter hospital id: ");
                                Long id = scanner.nextLong();
                                System.out.println("Enter doctor first name: ");
                                String fname =scanner.next();
                                doctor.setFirstName(fname);
                                System.out.println("Enter doctor last name: ");
                                String lname =scanner.next();
                                doctor.setLastName(lname);
                                System.out.println("Enter gender:(male/female)");
                                Gender gender = Gender.valueOf(scanner.next());
                                doctor.setGender(gender);
                                System.out.println("Enter doctor experience year: ");
                                int year = scanner.nextInt();
                                doctor.setExperienceYear(year);
                                System.out.println(doctorGenericService.add(id, doctor));
                            }
                            case 2->{
                                System.out.println("Enter hospital id: ");
                                Long hid = scanner.nextLong();
                                System.out.println("Enter doctor id: ");
                                Long did= scanner.nextLong();
                                System.out.println(doctorGenericService.removeById(hid, did));
                            }
                            case 3->{
                                Doctor doctor= new Doctor();
                                System.out.println("Enter doctor id: ");
                                Long did = scanner.nextLong();
                                System.out.println("Enter doctor new first name: ");
                                String name = scanner.next();
                                doctor.setFirstName(name);
                                System.out.println("Enter doctor new last name: ");
                                String lname= scanner.next();
                                doctor.setLastName(lname);
                                System.out.println("Enter new gender(male/female)");
                                Gender gender = Gender.valueOf(scanner.next());
                                doctor.setGender(gender);
                                System.out.println("Enter new experience year:  ");
                                int y = scanner.nextInt();
                                doctor.setExperienceYear(y);
                                System.out.println(doctorGenericService.updateById(did, doctor));
                            }
                            case 4->{
                                System.out.println("Enter doctor id: ");
                                Long id = scanner.nextLong();
                                System.out.println(doctorService.findDoctorById(id));
                            }
                            case 5->{
                                System.out.println("Enter department id: ");
                                Long id = scanner.nextLong();
                                System.out.println("Enter doctor id: ");
                                Long idd= scanner.nextLong();
                                System.out.println(doctorService.assignDoctorToDepartment(id, Collections.singletonList(idd)));
                            }
                            case 6->{
                                System.out.println("Enter hospital id: ");
                                Long id = scanner.nextLong();
                                System.out.println(doctorService.getAllDoctorsByHospitalId(id));
                            }
                            case 7->{
                                System.out.println("Enter department id: ");
                                Long id = scanner.nextLong();
                                System.out.println(doctorService.getAllDoctorsByDepartmentId(id));
                            }
                            case 8-> while3 = false;
                            default -> System.out.println("Rodnoy 1 den 8 ge chein tanda");
                        }
                    }
                    break;
                case 4:
                    boolean while4 = true;
                    while (while4){
                        System.out.println("""
                                1.Add patient by hospital id
                                2.delete patient by hospital id
                                3.Update patient by hospital id
                                4.Add patients to hospital id
                                5.Get patient by id
                                6.Get patient by age
                                7.Sort patient by age
                                8.Exit
                                """);
                        switch (scanner.nextInt()){
                            case 1 ->{
                                Patient patient = new Patient();
                                System.out.println("Enter hospital id: ");
                                Long id = scanner.nextLong();
                                patient.setId(GenericId.genericPatientId());
                                System.out.println("Enter patient first name: ");
                                String fname =  scanner.next();
                                patient.setFirstName(fname);
                                System.out.println("Enter patient last name: ");
                                String lname = scanner.next();
                                patient.setLastName(lname);
                                System.out.println("Enter patient age: ");
                                int age = scanner.nextInt();
                                patient.setAge(age);
                                System.out.println("Enter patient gender:(male/female)");
                                Gender gender = Gender.valueOf(scanner.next());
                                patient.setGender(gender);
                                System.out.println(patientGenericService.add(id, patient));
                            }
                            case 2->{
                                System.out.println("Enter hospital id: ");
                                Long id = scanner.nextLong();
                                System.out.println("Enter patient id: ");
                                Long pid = scanner.nextLong();
                                System.out.println(patientGenericService.removeById(id, pid));
                            }
                            case 3->{
                                Patient patient = new Patient();
                                System.out.println("Enter patient id: ");
                                Long pid = scanner.nextLong();
                                System.out.println("Enter patient new first name: ");
                                String fname = scanner.next();
                                patient.setFirstName(fname);
                                System.out.println("Enter patient new last name: ");
                                String lname = scanner.next();
                                patient.setLastName(lname);
                                System.out.println("Enter patient new age: ");
                                int  age = scanner.nextInt();
                                patient.setAge(age);
                                System.out.println("Enter patient new gender:(male/female)");
                                Gender gender = Gender.valueOf(scanner.next());
                                patient.setGender(gender);
                                System.out.println(patientGenericService.updateById( pid, patient));
                            }
                            case 4->{
                                Patient patient1 = new Patient();
                                Patient patient2= new Patient();
                                List<Patient> list = new ArrayList<>(Arrays.asList(patient1,patient2));
                                System.out.println("Enter hospital id: ");
                                Long idd = scanner.nextLong();

                                patient1.setId(GenericId.genericPatientId());
                                System.out.println("Enter patient first name: ");
                                String fname =  scanner.next();
                                patient1.setFirstName(fname);
                                System.out.println("Enter patient last name: ");
                                String lname = scanner.next();
                                patient1.setLastName(lname);
                                System.out.println("Enter patient age: ");
                                int age = scanner.nextInt();
                                patient1.setAge(age);
                                System.out.println("Enter patient gender:(male/female)");
                                Gender gender = Gender.valueOf(scanner.next());
                                patient1.setGender(gender);


                                patient2.setId(GenericId.genericPatientId());
                                System.out.println("Enter patient first name: ");
                                String fname2 =  scanner.next();
                                patient2.setFirstName(fname2);
                                System.out.println("Enter patient last name: ");
                                String lname2 = scanner.next();
                                patient2.setLastName(lname2);
                                System.out.println("Enter patient age: ");
                                int age2 = scanner.nextInt();
                                patient2.setAge(age2);
                                System.out.println("Enter patient gender:(male/female)");
                                Gender gender2 = Gender.valueOf(scanner.next());
                                patient2.setGender(gender2);
                                System.out.println(patientService.addPatientsToHospital(idd, list));
                            }
                            case 5->{
                                System.out.println("Enter patient id: ");
                                Long id = scanner.nextLong();
                                System.out.println(patientService.getPatientById(id));
                            }
                            case 6->{
                                System.out.println("Enter patient age: ");
                                int age = scanner.nextInt();
                                System.out.println(patientService.getPatientByAge(age));
                            }
                            case 7->{
                                System.out.println("Enter patient age sort :(asc/desc)");
                                String asrorDesc = scanner.next();
                                System.out.println(patientService.sortPatientsByAge(asrorDesc));
                            }
                            case 8->while4 = false;
                            default -> System.out.println("Not-Found!");
                        }
                    }
                    break;
                case 5:return;
            }
        }

    }
}