package com.example.springjpa.services;

import com.example.springjpa.models.entity.Client;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IClientService {

    @Transactional(readOnly = true)
    List<Client> findAll();

    @Transactional
    void save(Client client);

    @Transactional
    Client findOne(Long id);

    @Transactional
    void delete(Long id);

}
