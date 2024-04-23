package com.project.facturacion.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.facturacion.errors.DataBaseException;
import com.project.facturacion.models.entity.Factura;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class FacturaDaoImp implements iFacturaDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Factura> finAll() {
		return em.createQuery("from Factura").getResultList();
	}

	@Override
	@Transactional
	public void save(Factura factura) {
		
		
		if(factura.getId() != null && factura.getId() > 0) {
			try {
				em.merge(factura);
			}catch (DataBaseException e){
				throw new DataBaseException();
			}
		}else {
			try {
				em.persist(factura);
			}catch (DataBaseException e){
				throw new DataBaseException();
			}
		}

	}

	@Override
	@Transactional
	public Factura findOne(Long id) {
		return em.find(Factura.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		try {
			em.remove(this.findOne(id));
		}catch(DataBaseException e) {
			throw new DataBaseException();			
		}

	}

}
