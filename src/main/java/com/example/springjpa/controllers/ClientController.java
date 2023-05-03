package com.example.springjpa.controllers;

import com.example.springjpa.models.dao.IClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientController {
    @Autowired
    private IClientDao iClientDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listClients(Model model) {
        model.addAttribute("title", "list of clients");
        model.addAttribute("clients", iClientDao.findAll());
        return "list";
    }
}