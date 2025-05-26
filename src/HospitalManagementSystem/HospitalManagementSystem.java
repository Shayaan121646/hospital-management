package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "Shayaan@786";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);

            while (true) {
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book appointment");
                System.out.println("5. exit");
                System.out.println("Enter your choice");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        patient.addPatient();
                    case 2:
                        patient.ViewPatient();
                    case 3:
                        doctor.ViewDoctors();
                    case 4:
                        bookAppointment(patient, doctor, connection, scanner);
                    case 5:
                        return;
                    default:
                        System.out.println("Enter valid choice");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner) {
        System.out.println("Enter patient Id :");
        int patientId = scanner.nextInt();
        System.out.println("Enter Doctor Id");
        int doctorId = scanner.nextInt();
        System.out.println("Enter appointment date (YYYY-MM-DD)");
        String appointmentDate = scanner.next();
        if (patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)) {

            if (checkDoctorAvaibality(doctorId, appointmentDate, connection)) {
                String appointmentQuery = "INSERT INTO appointments(patient_id,doctor_id,appointment_date) VALUES(?,?,?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointmentDate);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("APPOINTMENT BOOKED");
                    } else {
                        System.out.println("FAILED TO BOOK APPOINTMENT");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Doctor are not available on this date!!");
            }
        }else
        {
            System.out.println("Either Doctor or Patient docent exist!! ");
        }
    }

}
public  static boolean checkDoctorAvaibality(int doctorId, String appointmentDate,Connection connection) {
    String query ="SELECT COUNT(*) FROM appointments WHERE doctor_id  = ? AND appointmnet_date = ?";
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,doctorId);
        preparedStatement.setString(2,appointmentDate);
        ResultSet resultset  = preparedStatement.executeQuery();
        if(resultset.next()) {
            int count = resultset.getInt(1);
            if(count==0){
                return true;
            }else{
                return false;

            }


        } }catch (Exception e) {
        e.printStackTrace();
    }
    return false;
            }



