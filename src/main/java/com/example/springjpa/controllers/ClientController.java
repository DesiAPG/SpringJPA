package com.example.springjpa.controllers;

import com.example.springjpa.models.dao.IClientDao;
import com.example.springjpa.models.entity.Client;
import com.example.springjpa.services.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("client")
public class ClientController {
    @Autowired
    private IClientService iClientService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listClients(Model model) {
        model.addAttribute("title", "list of clients");
        model.addAttribute("clients", iClientService.findAll());
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
    public String saveClient(@Valid Client client, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Client Form");
            return "form";
        }
        iClientService.save(client);
        status.setComplete();
        return "redirect:/list";
    }

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Client client = null;
        if (id > 0) {
            client = iClientService.findOne(id);
        } else {
            return "redirect:/list";
        }
        model.put("client", client);
        model.put("title", "Edit Client");
        return "form";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            iClientService.delete(id);
        }
        return "redirect:/list";
    }
}
