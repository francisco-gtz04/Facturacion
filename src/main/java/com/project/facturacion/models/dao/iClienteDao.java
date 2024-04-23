package com.project.facturacion.models.dao;

import java.util.List;

import com.project.facturacion.models.entity.Cliente;

public interface iClienteDao {
	
	public List<Cliente> finAll();
	public void save(Cliente cliente);
	public Cliente findOne(Long id);
	public void delete(Long id);

}
