package es.makigas.ejemplso.springfullstack.dao;

import es.makigas.ejemplso.springfullstack.models.Alumno;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AlumnoTest
{
    @Autowired
    private AlumnoRepository repo;
    
    @Test
    public void testGetAll() {
        List<Alumno> alumnos = new ArrayList<>();
        repo.findAll().forEach(alumnos::add);
        Assertions.assertFalse(alumnos.isEmpty());
    }
}
