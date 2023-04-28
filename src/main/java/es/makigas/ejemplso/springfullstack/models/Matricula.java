package es.makigas.ejemplso.springfullstack.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name = "matriculas")
public class Matricula {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;
    
    @OneToOne
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;
    
    @Column
    private int curso;
    
    @Column
    private int calificacion;
}
