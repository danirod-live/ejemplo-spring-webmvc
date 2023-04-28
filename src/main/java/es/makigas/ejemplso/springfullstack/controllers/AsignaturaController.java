package es.makigas.ejemplso.springfullstack.controllers;

import es.makigas.ejemplso.springfullstack.models.Asignatura;
import es.makigas.ejemplso.springfullstack.services.AsignaturaService;
import es.makigas.ejemplso.springfullstack.services.ProfesorService;
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
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturas;
    
    @Autowired
    private ProfesorService profesores;
    
    @GetMapping("/asignaturas")
    public String list(Model model) {
        List<Asignatura> asigs = asignaturas.list();
        model.addAttribute("asignaturas", asigs);
        return "asignaturas/list";
    }
    
    @GetMapping("/asignaturas/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("asignatura", new Asignatura());
        model.addAttribute("profesores", profesores.profesorList().entrySet());
        return "asignaturas/form";
    }
    
    @GetMapping("/asignaturas/{id}/editar")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<Asignatura> asig = asignaturas.get(id);
        if (asig.isPresent()) {
            model.addAttribute("asignatura", asig.get());
            model.addAttribute("profesores", profesores.profesorList().entrySet());
            return "asignaturas/form";
        } else {
            model.addAttribute("error", "No tenemos esa asignatura");
            return "error";
        }
    }
    
    @PostMapping("/asignaturas/editar")
    public String save(Asignatura asig, @RequestParam("action") String action, Model model) {
        switch (action) {
            case "insert":
                asignaturas.insert(asig);
                return "redirect:/asignaturas";
            case "update":
                asignaturas.update(asig);
                return "redirect:/asignaturas";
            default:
                model.addAttribute("error", "No sé qué te pasa");
                return "error";
        }
    }

    @GetMapping("/asignaturas/{id}/borrar")
    public String confirmDelete(@PathVariable("id") long id, Model model) {
        Optional<Asignatura> asig = asignaturas.get(id);
        if (asig.isPresent()) {
            model.addAttribute("asignatura", asig.get());
            return "asignaturas/delete";
        } else {
            model.addAttribute("error", "No tenemos esa asignatura");
            return "error";
        }
    }
    
    @PostMapping("/asignaturas/{id}/borrar")
    public String delete(@PathVariable("id") long id, Model model) {
        Optional<Asignatura> asig = asignaturas.get(id);
        if (asig.isPresent()) {
            asignaturas.delete(asig.get());
            return "redirect:/asignaturas";
        } else {
            model.addAttribute("error", "No tenemos esa asignatura");
            return "error";
        }
    }
}
