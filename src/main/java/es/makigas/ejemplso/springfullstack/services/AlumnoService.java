package es.makigas.ejemplso.springfullstack.services;

import es.makigas.ejemplso.springfullstack.dao.AlumnoRepository;
import es.makigas.ejemplso.springfullstack.models.Alumno;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {
    
    @Autowired
    private AlumnoRepository repository;
    
    public List<Alumno> find() {
        return repository.findAll();
    }
    
    public Optional<Alumno> get(long id) {
        return repository.findById(id);
    }
    
    public Alumno insert(Alumno alu) {
        if (alu.getId() != 0)
            throw new RuntimeException("E?");
        return repository.save(alu);
    }
    
    public Alumno update(Alumno alu) {
        if (alu.getId() == 0)
            throw new RuntimeException("Lo cualo");
        return repository.save(alu);
    }
    
    public void delete(Alumno alu) {
        repository.delete(alu);
    }
}
