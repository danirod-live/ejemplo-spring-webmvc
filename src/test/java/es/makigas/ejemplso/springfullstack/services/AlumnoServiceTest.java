package es.makigas.ejemplso.springfullstack.services;

import es.makigas.ejemplso.springfullstack.models.Alumno;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AlumnoServiceTest {

    @Autowired
    private AlumnoService service;

    @Test
    @Transactional
    public void testAlumno() {
        System.out.println("\n== LIST INICIAL ==");
        List<Alumno> inicial = service.find();
        System.out.println(inicial);

        System.out.println("\n== INSERTAR ==");
        Alumno nuevo = new Alumno();
        nuevo.setNombre("Liken");
        nuevo.setApellido("Dero");
        nuevo.setFechaNacimiento(LocalDate.of(1998, 06, 06));
        Alumno insertado = service.insert(nuevo);
        assertNotEquals(insertado.getId(), 0);

        System.out.println("\n== LIST ACTUALIZADO ==");
        List<Alumno> trasInsert = service.find();
        System.out.println(trasInsert);
        assertEquals(trasInsert.size(), inicial.size() + 1);

        System.out.println("\n == GET ==");
        Optional<Alumno> alu = service.get(insertado.getId());
        assertTrue(alu.isPresent());

        System.out.println("\n == GET NULL ==");
        Optional<Alumno> notFound = service.get(0L);
        assertTrue(notFound.isEmpty());

        System.out.println("\n == UPDATE ==");
        Alumno theAlumnoToUpdate = alu.get();
        theAlumnoToUpdate.setNombre("Laken");
        theAlumnoToUpdate.setNombre("Daro");

        System.out.println("\n == DELETE ==");
        service.delete(theAlumnoToUpdate);

        System.out.println("\n== LIST FINAL ==");
        List<Alumno> trasDelete = service.find();
        System.out.println(trasDelete);
        assertEquals(trasDelete.size(), inicial.size());
    }

}
