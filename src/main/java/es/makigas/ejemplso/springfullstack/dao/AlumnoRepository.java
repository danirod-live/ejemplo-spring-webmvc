package es.makigas.ejemplso.springfullstack.dao;

import es.makigas.ejemplso.springfullstack.models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    
}
