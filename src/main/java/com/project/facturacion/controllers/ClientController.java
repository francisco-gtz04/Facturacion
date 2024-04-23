package com.project.facturacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.facturacion.models.dao.iClienteDao;
import com.project.facturacion.models.dao.iFacturaDao;
import com.project.facturacion.models.entity.Cliente;
import com.project.facturacion.validator.ClienteValidator;

@RestController
@SessionAttributes("cliente")
public class ClientController {

	@Autowired
	private iClienteDao clienteDao;
	
	@Autowired
	private ClienteValidator clienteValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.clienteValidator);
	}
	
	
	
	@RequestMapping(path = "/clientes", method = RequestMethod.GET)
	public List<Cliente> clientesList(){
		return clienteDao.finAll();
	}
	
	//@RequestMapping(path = "/guardar-cliente", method = RequestMethod.POST)
	@PostMapping(path = "/guardar-cliente")
	public ResponseEntity<?> createCliente(@RequestBody Cliente cliente, BindingResult result) {
		clienteValidator.validate(cliente, result);
		
		if(result.hasErrors()) {
			result.getAllErrors();
			return ResponseEntity.badRequest().body("Error en la validacion de datos"
														+result.getAllErrors());
		}
		
		clienteDao.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado exitosamente");
	}
	
	
	@GetMapping(path = "/cliente/{id}")
	public Cliente getClienteById(@PathVariable(name = "id") Long id) {
		return clienteDao.findOne(id);
	}
	
	
	@DeleteMapping("/cliente/{id}")
	public void deleteCliente(@PathVariable(name = "id") Long id) {
		try {
		clienteDao.delete(id);
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	
	
	
}
