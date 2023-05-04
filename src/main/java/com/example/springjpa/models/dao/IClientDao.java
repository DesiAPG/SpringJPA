package com.example.springjpa.models.dao;

import com.example.springjpa.models.entity.Client;

import java.util.List;

public interface IClientDao {
    List<Client> findAll();

    void save(Client client);
}
