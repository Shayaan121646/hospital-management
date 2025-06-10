
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DoctorTest {

    @Test
    public void testValidDoctorCreation() {
        Doctor doctor = new Doctor("John Doe", "Cardiology", 1);
        assertEquals("John Doe", doctor.getName());
        assertEquals("Cardiology", doctor.getSpecialization());
        assertEquals(1, doctor.getDoctorId());
    }

    @Test
    public void testInvalidDoctorName() {
        assertThrows(IllegalArgumentException.class, () -> new Doctor("", "Cardiology", 1));
    }

    @Test
    public void testInvalidDoctorSpecialization() {
        assertThrows(IllegalArgumentException.class, () -> new Doctor("John Doe", "", 1));
    }

    @Test
    public void testInvalidDoctorId() {
        assertThrows(IllegalArgumentException.class, () -> new Doctor("John Doe", "Cardiology", -1));
    }
}
