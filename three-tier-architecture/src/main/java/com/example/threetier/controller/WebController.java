package com.example.threetier.controller;

import com.example.threetier.dto.ConferenceDto;
import com.example.threetier.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    private final ConferenceService conferenceService;

    @Autowired
    public WebController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("registrations", conferenceService.getAllRegistrations());
        return "home";
    }

    @PostMapping("/register")
    public String registerParticipant(@RequestParam String email,
                                      RedirectAttributes redirectAttributes) {
        try {
            if (email == null || email.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Email is required");
                return "redirect:/register";
            }

            ConferenceDto registeredConference = conferenceService.registerParticipant(email);
            redirectAttributes.addFlashAttribute("success", "Registration successful!");
            redirectAttributes.addFlashAttribute("conference", registeredConference);
            return "redirect:/register";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "An error occurred during registration: " + e.getMessage());
            return "redirect:/register";
        }
    }
}
