package com.example.springjpa.controllers;

import com.example.springjpa.models.dao.IClientDao;
import com.example.springjpa.models.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

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

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String create(Map<String, Object> model) {
        Client client = new Client();
        model.put("client", client);
        model.put("title", "Client Form");
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String saveClient(Client client) {
        iClientDao.save(client);
        return "redirect:list";
    }
}
