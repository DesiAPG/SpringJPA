package com.example.springjpa.models.dao;

import com.example.springjpa.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IClientDao extends JpaRepository<Client, Long> {
    
}
