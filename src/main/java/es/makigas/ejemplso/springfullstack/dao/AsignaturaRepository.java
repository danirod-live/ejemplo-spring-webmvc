package es.makigas.ejemplso.springfullstack.dao;

import es.makigas.ejemplso.springfullstack.models.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
    
}
