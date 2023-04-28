package es.makigas.ejemplso.springfullstack.controllers;

import es.makigas.ejemplso.springfullstack.models.Profesor;
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
public class ProfesorController {
    
    @Autowired
    ProfesorService profesores;
    
    @GetMapping("/profesores")
    public String list(Model model) {
        List<Profesor> todos = profesores.find();
        model.addAttribute("profesores", todos);
        return "profesores/list";
    }
    
    @GetMapping("/profesores/{id}/eliminar")
    public String requestDelete(@PathVariable("id") long id, Model model) {
        Optional<Profesor> profesor = profesores.get(id);
        if (profesor.isPresent()) {
            model.addAttribute("profesor", profesor.get());
            return "profesores/delete";
        } else {
            model.addAttribute("error", "Profesor no encontrado");
            return "error";
        }
    }
    
    @GetMapping("/profesores/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("profesor", new Profesor());
        return "profesores/form";
    }
    
    @GetMapping("/profesores/{id}/editar")
    public String nuevo(@PathVariable("id") long id, Model model) {
        Optional<Profesor> profesor = profesores.get(id);
        if (profesor.isPresent()) {
            model.addAttribute("profesor", profesor.get());
            return "profesores/form";
        } else {
            model.addAttribute("error", "Profesor no encontrado");
            return "error";
        }
    }
    
    @PostMapping("/profesores/editar")
    public String editar(Profesor alu, @RequestParam("action") String action, Model model) {
        switch (action) {
            case "insert":
                profesores.insert(alu);
                return "redirect:/profesores";
            case "update":
                profesores.update(alu);
                return "redirect:/profesores";
            default:
                model.addAttribute("error", "Acción no definida");
                return "error";
        }
     } 
    
    
    @PostMapping("/profesores/{id}/eliminar")
    public String delete(@PathVariable("id") long id, Model model) {
        Optional<Profesor> profesor = profesores.get(id);
        if (profesor.isPresent()) {
            profesores.delete(profesor.get());
            return "redirect:/profesores";
        } else {
            model.addAttribute("error", "Profesor no encontrado");
            return "error";
        }
    }
}
