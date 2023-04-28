package es.makigas.ejemplso.springfullstack.dao;

import es.makigas.ejemplso.springfullstack.models.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    
}
