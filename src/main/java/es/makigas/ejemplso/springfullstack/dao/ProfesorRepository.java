package es.makigas.ejemplso.springfullstack.dao;

import es.makigas.ejemplso.springfullstack.models.Profesor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    List<ProfesorFullNameAndId> findAllProjectedBy();
}
