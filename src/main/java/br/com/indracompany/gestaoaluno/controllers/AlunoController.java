package br.com.indracompany.gestaoaluno.controllers;

import org.springframework.ui.Model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.indracompany.gestaoaluno.model.Aluno;
import br.com.indracompany.gestaoaluno.repository.Alunos;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private Alunos alunos;

    @PostMapping
    public String salvar(Aluno aluno) {
        this.alunos.save(aluno);
        return "redirect:/alunos";
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView retorno = new ModelAndView("ListarAlunos");
        retorno.addObject("alunos", alunos.findAll());
        retorno.addObject(new Aluno());
        return retorno;
    }
    
    @DeleteMapping
    public void deleteStudent(long id) {
    	alunos.deleteById(id);
    }
    
   @PutMapping
    public ResponseEntity<Aluno> list(Long id, String nome, String email, String nota){
    	Optional<Aluno> obj = alunos.findById(id);
    	Aluno aluno = obj.orElse(null);
    	aluno.setNome(nome);
    	aluno.setEmail(email);
    	aluno.setNota(nota);
    	alunos.save(aluno);
    	return ResponseEntity.ok(aluno);
    }
   
   @PostMapping(value = "/alunos")
   public String delete(Aluno aluno) {
       this.alunos.save(aluno);
       return "redirect:/alunos";
   }
   
   
	@GetMapping("/alunos/{id}")
	public String deleteAluno(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.alunos.deleteById(id);
		return "redirect:/alunos";
	}
	
	@GetMapping("/alunos/update/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Aluno aluno = alunos.findById(id).get();
		//Employee employee = employeeService.getEmployeeById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("aluno", aluno);
		
		return "new_aluno";
		
		/*ModelAndView retorno = new ModelAndView("new_aluno");
		return retorno;*/
		//return "redirect:/alunos";
	}
	
	
	@PostMapping("/updateAlunos")
	public String saveEmployee(@ModelAttribute("aluno") Aluno aluno) {
		// save employee to database
		alunos.save(aluno);
		return "redirect:/alunos";
	}
}
