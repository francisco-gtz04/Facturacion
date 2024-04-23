package com.project.facturacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.facturacion.models.dao.iFacturaDao;
import com.project.facturacion.models.entity.Factura;

@RestController
@SessionAttributes("factura")
public class FacturaController {
	
	@Autowired
	private iFacturaDao facturaDao;
	
	@GetMapping(path = "/facturas")
	public  List<Factura> facturasList() {
	    return facturaDao.finAll();
	  }
	
	@PostMapping("/guardar-factura")
	 public void createFactura(@RequestBody Factura factura) {
	     facturaDao.save(factura);
	  }
	
	@GetMapping("/factura/{id}")
	public Factura getFacturaById(@PathVariable(name = "id") Long id) {
		return facturaDao.findOne(id);
	}
	
	@DeleteMapping("/factura/{id}")
	 public void deleteFactura(@PathVariable(name = "id") Long id) {
		facturaDao.delete(id);
	  }
	

}
