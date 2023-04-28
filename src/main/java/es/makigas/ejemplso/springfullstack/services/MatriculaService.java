package es.makigas.ejemplso.springfullstack.services;

import es.makigas.ejemplso.springfullstack.dao.MatriculaRepository;
import es.makigas.ejemplso.springfullstack.models.Matricula;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {
    
    @Autowired
    private MatriculaRepository matriculas;
   
    public List<Matricula> list() {
        return matriculas.findAll();
    }
    
    public Optional<Matricula> get(long id) {
        return matriculas.findById(id);
    }
    
    public void delete(Matricula asig) {
        matriculas.delete(asig);
    }
    
    public void insert(Matricula asig) {
        if (asig.getId() != 0)
            throw new Error("NAH");
        matriculas.save(asig);
    }
    public void update(Matricula asig) {
        if (asig.getId() == 0)
            throw new Error("NAH");
        matriculas.save(asig);
    }
    
}
