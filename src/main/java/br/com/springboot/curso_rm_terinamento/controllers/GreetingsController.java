package br.com.springboot.curso_rm_terinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_rm_terinamento.repository.UsuarioRepository;
import br.com.springboot.curso_rm_treinamento.model.Usuario;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    
    @GetMapping(value = "ListaTodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listUsuario(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    	
    }
    
   @PostMapping(value = "salvar")
   @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
	   
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
   
   @PutMapping(value = "atualizar")
   @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
	   
	   if (usuario.getId() == null) {
	    return new ResponseEntity<String>("Id n??o foi informado para atualiza????o. ", HttpStatus.OK);
	}
	   
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
   
   @DeleteMapping(value = "delete")
   @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){
	   
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
    	
    }
   
   @GetMapping(value = "buscarUserId")
   @ResponseBody
    public ResponseEntity<Usuario> buscarUserId(@RequestParam(name = "iduser") Long iduser){
	   
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
   
   @GetMapping(value = "buscarPorNome")
   @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){
	   
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    	
    }
   
   
   

}
