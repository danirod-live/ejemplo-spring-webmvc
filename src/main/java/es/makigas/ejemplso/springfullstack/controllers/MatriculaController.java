package es.makigas.ejemplso.springfullstack.controllers;

import es.makigas.ejemplso.springfullstack.models.Matricula;
import es.makigas.ejemplso.springfullstack.services.AlumnoService;
import es.makigas.ejemplso.springfullstack.services.AsignaturaService;
import es.makigas.ejemplso.springfullstack.services.MatriculaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MatriculaController {

    @Autowired
    private MatriculaService matriculas;
    
    @Autowired
    private AlumnoService alumnos;
    
    @Autowired
    private AsignaturaService asignaturas;
    
    @GetMapping("/matriculas")
    public String list(Model model) {
        List<Matricula> matris = matriculas.list();
        model.addAttribute("matriculas", matris);
        return "matriculas/list";
    }
    
    @GetMapping("/matriculas/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("matricula", new Matricula());
        model.addAttribute("asignaturas", asignaturas.list());
        model.addAttribute("alumnos", alumnos.find());
        return "matriculas/form";
    }
    
    @GetMapping("/matriculas/{id}/editar")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<Matricula> matri = matriculas.get(id);
        if (matri.isPresent()) {
            model.addAttribute("matricula", matri.get());
            model.addAttribute("asignaturas", asignaturas.list());
            model.addAttribute("alumnos", alumnos.find());
            return "matriculas/form";
        } else {
            model.addAttribute("error", "No tenemos esa matricula");
            return "error";
        }
    }
    
    @PostMapping("/matriculas/editar")
    public String save(Matricula matri, @RequestParam("action") String action, Model model) {
        switch (action) {
            case "insert":
                matriculas.insert(matri);
                return "redirect:/matriculas";
            case "update":
                matriculas.update(matri);
                return "redirect:/matriculas";
            default:
                model.addAttribute("error", "No sé qué te pasa");
                return "error";
        }
    }

    @GetMapping("/matriculas/{id}/borrar")
    public String confirmDelete(@PathVariable("id") long id, Model model) {
        Optional<Matricula> matri = matriculas.get(id);
        if (matri.isPresent()) {
            model.addAttribute("matricula", matri.get());
            return "matriculas/delete";
        } else {
            model.addAttribute("error", "No tenemos esa matricula");
            return "error";
        }
    }
    
    @PostMapping("/matriculas/{id}/borrar")
    public String delete(@PathVariable("id") long id, Model model) {
        Optional<Matricula> matri = matriculas.get(id);
        if (matri.isPresent()) {
            matriculas.delete(matri.get());
            return "redirect:/matriculas";
        } else {
            model.addAttribute("error", "No tenemos esa matricula");
            return "error";
        }
    }
}
