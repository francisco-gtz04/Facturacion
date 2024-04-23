package com.project.facturacion.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.facturacion.models.entity.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ClienteDaoImp implements iClienteDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Cliente> finAll() {
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		if(cliente.getId() != null && cliente.getId() > 0) {
			em.merge(cliente);
		}else {
			em.persist(cliente);
		}
		
	}

	@Override
	@Transactional
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(this.findOne(id));
		
	}
	
}
