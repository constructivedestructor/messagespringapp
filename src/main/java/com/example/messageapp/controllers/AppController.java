package com.example.messageapp.controllers;

import com.example.messageapp.models.CurrentUser;
import com.example.messageapp.models.Message;
import com.example.messageapp.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AppController {
    private CurrentUser currentUser = new CurrentUser("Guest");
    private List<Message> filteredList;

    @Autowired
    MessageRepo repo;

    @GetMapping("/index")
    public String getIndexPage(){
        return "index";
    }

    @PostMapping("/index")
    public String postUsername(@RequestParam(name = "username") CurrentUser currentUser){
        this.currentUser = currentUser;
        return "redirect:/messages";
    }

    @GetMapping("/messages")
    public String getMessagePage(Model model, String filter){
        model.addAttribute("name", currentUser.getName());
        if (filter != null)
            model.addAttribute("messages", repo.findMessagesByAuthor(filter));
        else
            model.addAttribute("messages", repo.findAll());
        model.addAttribute("message", new Message());
        return "messages";
    }

    @PostMapping("/messages")
    public String postMessage(Message message, Model model){
        message.setAuthor(currentUser.getName());
        repo.save(message);
        return "redirect:/messages";
    }
}
