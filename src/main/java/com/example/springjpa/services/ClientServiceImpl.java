package com.example.springjpa.services;

import com.example.springjpa.models.dao.IClientDao;
import com.example.springjpa.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientDao iClientDao;

    @Override
    public List<Client> findAll() {
        return (List<Client>) iClientDao.findAll();
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return iClientDao.findAll(pageable);
    }

    @Override
    public void save(Client client) {
        iClientDao.save(client);
    }

    @Override
    public Client findOne(Long id) {
        return iClientDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        iClientDao.deleteById(id);
    }
}
