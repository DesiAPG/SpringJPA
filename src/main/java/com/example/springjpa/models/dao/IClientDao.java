package com.example.springjpa.models.dao;

import com.example.springjpa.models.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientDao extends CrudRepository<Client, Long> {
 
}
