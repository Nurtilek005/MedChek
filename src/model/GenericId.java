package model;

public class GenericId {
    public static Long departmentId = 0L;
    public static Long doctorId = 0L;
    public static Long hospitalId = 0L;
    public static Long patientId = 0L;
    public static long genericDepartmentId(){
        return ++departmentId;
    }
    public static long genericDoctorId(){
        return ++doctorId;
    }
    public static long genericHospitalId(){
        return ++hospitalId;
    }
    public static long genericPatientId(){
        return ++patientId;
    }
}
