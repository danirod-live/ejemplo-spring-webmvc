package es.makigas.ejemplso.springfullstack.controllers;

import es.makigas.ejemplso.springfullstack.models.Alumno;
import es.makigas.ejemplso.springfullstack.services.AlumnoService;
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
public class AlumnosController {
    
    @Autowired
    AlumnoService alumnos;
    
    @GetMapping("/alumnos")
    public String list(Model model) {
        List<Alumno> todos = alumnos.find();
        model.addAttribute("alumnos", todos);
        return "alumnos/list";
    }
    
    @GetMapping("/alumnos/{id}/eliminar")
    public String requestDelete(@PathVariable("id") long id, Model model) {
        Optional<Alumno> alumno = alumnos.get(id);
        if (alumno.isPresent()) {
            model.addAttribute("alumno", alumno.get());
            return "alumnos/delete";
        } else {
            model.addAttribute("error", "Alumno no encontrado");
            return "error";
        }
    }
    
    @GetMapping("/alumnos/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "alumnos/form";
    }
    
    @GetMapping("/alumnos/{id}/editar")
    public String nuevo(@PathVariable("id") long id, Model model) {
        Optional<Alumno> alumno = alumnos.get(id);
        if (alumno.isPresent()) {
            model.addAttribute("alumno", alumno.get());
            return "alumnos/form";
        } else {
            model.addAttribute("error", "Alumno no encontrado");
            return "error";
        }
    }
    
    @PostMapping("/alumnos/editar")
    public String editar(Alumno alu, @RequestParam("action") String action, Model model) {
        switch (action) {
            case "insert":
                alumnos.insert(alu);
                return "redirect:/alumnos";
            case "update":
                alumnos.update(alu);
                return "redirect:/alumnos";
            default:
                model.addAttribute("error", "Acción no definida");
                return "error";
        }
     } 
    
    
    @PostMapping("/alumnos/{id}/eliminar")
    public String delete(@PathVariable("id") long id, Model model) {
        Optional<Alumno> alumno = alumnos.get(id);
        if (alumno.isPresent()) {
            alumnos.delete(alumno.get());
            return "redirect:/alumnos";
        } else {
            model.addAttribute("error", "Alumno no encontrado");
            return "error";
        }
    }
}
