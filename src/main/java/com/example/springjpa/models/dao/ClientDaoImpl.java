package com.example.springjpa.models.dao;

import com.example.springjpa.models.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClientDaoImpl implements IClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return entityManager.createQuery("from Client").getResultList();
    }
}
