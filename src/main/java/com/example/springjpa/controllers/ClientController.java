package com.example.springjpa.controllers;

import com.example.springjpa.models.dao.IClientDao;
import com.example.springjpa.models.entity.Client;
import com.example.springjpa.pages.PageRender;
import com.example.springjpa.services.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Controller
@SessionAttributes("client")
public class ClientController {
    @Autowired
    private IClientService iClientService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listClients(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Client> clients = iClientService.findAll(pageable);
        PageRender<Client> pageRender = new PageRender<>("/list", clients);
        model.addAttribute("title", "list of clients");
        model.addAttribute("clients", clients);
        model.addAttribute("clients", clients);
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
    public String saveClient(@Valid Client client, BindingResult result, Model model, @RequestParam("file") MultipartFile photo, SessionStatus status, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Client Form");
            return "form";
        }
        if (!photo.isEmpty()) {
            Path resourceDirectory = Paths.get("src//main//resources//static/images");
            String rootPath = resourceDirectory.toFile().getAbsolutePath();
            try {
                byte[] bytes = photo.getBytes();
                Path completePath = Paths.get(rootPath + "//" + photo.getOriginalFilename());
                Files.write(completePath, bytes);
                redirectAttributes.addFlashAttribute("info", photo.getOriginalFilename() + "Uploaded successfully");
                client.setPhoto(photo.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        iClientService.save(client);
        status.setComplete();
        redirectAttributes.addFlashAttribute("success", "Client created successfully");
        return "redirect:/list";
    }

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes redirectAttributes) {
        Client client = null;
        if (id > 0) {
            client = iClientService.findOne(id);
            if (client == null) {
                redirectAttributes.addFlashAttribute("error", "Client ID doesn't exists");
                return "redirect:/list";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Client ID cannot be 0");
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
