package es.makigas.ejemplso.springfullstack.services;

import es.makigas.ejemplso.springfullstack.dao.ProfesorFullNameAndId;
import es.makigas.ejemplso.springfullstack.dao.ProfesorRepository;
import es.makigas.ejemplso.springfullstack.models.Profesor;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService {
    
    @Autowired
    private ProfesorRepository repository;
    
    public List<Profesor> find() {
        return repository.findAll();
    }
    
    public Optional<Profesor> get(long id) {
        return repository.findById(id);
    }
    
    public Profesor insert(Profesor p) {
        if (p.getId() != 0)
            throw new RuntimeException("E?");
        return repository.save(p);
    }
    
    public Profesor update(Profesor p) {
        if (p.getId() == 0)
            throw new RuntimeException("Lo cualo");
        return repository.save(p);
    }
    
    public void delete(Profesor p) {
        repository.delete(p);
    }
    
    public Map<Long, String> profesorList() {
        return repository.findAllProjectedBy().stream()
                .collect(Collectors.toMap(ProfesorFullNameAndId::getId, (p) -> {
                    return p.getNombre() + " " + p.getApellido();
                }));
    }
}
