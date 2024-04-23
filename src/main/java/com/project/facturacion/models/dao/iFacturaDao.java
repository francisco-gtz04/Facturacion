package com.project.facturacion.models.dao;

import java.util.List;

import com.project.facturacion.models.entity.Factura;

public interface iFacturaDao {
	
	public List<Factura> finAll();
	public void save(Factura factura);
	public Factura findOne(Long id);
	public void delete(Long id);

}
