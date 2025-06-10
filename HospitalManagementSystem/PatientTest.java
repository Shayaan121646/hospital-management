
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PatientTest {

    @Test
    public void testValidPatientCreation() {
        Patient patient = new Patient("Jane Doe", 30, 1);
        assertEquals("Jane Doe", patient.getName());
        assertEquals(30, patient.getAge());
        assertEquals(1, patient.getPatientId());
    }

    @Test
    public void testInvalidPatientName() {
        assertThrows(IllegalArgumentException.class, () -> new Patient("", 30, 1));
    }

    @Test
    public void testInvalidPatientAge() {
        assertThrows(IllegalArgumentException.class, () -> new Patient("Jane Doe", -5, 1));
    }

    @Test
    public void testInvalidPatientId() {
        assertThrows(IllegalArgumentException.class, () -> new Patient("Jane Doe", 30, -1));
    }
}
