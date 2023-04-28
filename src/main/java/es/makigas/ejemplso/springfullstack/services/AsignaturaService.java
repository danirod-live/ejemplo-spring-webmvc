package es.makigas.ejemplso.springfullstack.services;

import es.makigas.ejemplso.springfullstack.dao.AsignaturaRepository;
import es.makigas.ejemplso.springfullstack.models.Asignatura;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaService {
    
    @Autowired
    private AsignaturaRepository asignaturas;
   
    public List<Asignatura> list() {
        return asignaturas.findAll();
    }
    
    public Optional<Asignatura> get(long id) {
        return asignaturas.findById(id);
    }
    
    public void delete(Asignatura asig) {
        asignaturas.delete(asig);
    }
    
    public void insert(Asignatura asig) {
        if (asig.getId() != 0)
            throw new Error("NAH");
        asignaturas.save(asig);
    }
    public void update(Asignatura asig) {
        if (asig.getId() == 0)
            throw new Error("NAH");
        asignaturas.save(asig);
    }
    
}
