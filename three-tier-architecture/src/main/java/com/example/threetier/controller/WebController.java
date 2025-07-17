package com.example.threetier.controller;

import com.example.threetier.controller.dto.ConferenceDTO;
import com.example.threetier.controller.dto.ConferenceResponseDTO;
import com.example.threetier.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Presentation Layer
 */
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
        List<ConferenceResponseDTO> registrations = conferenceService.getAllRegistrations().stream()
                .map(ConferenceResponseDTO::fromDto)
                .collect(Collectors.toList());
        model.addAttribute("registrations", registrations);
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

            ConferenceDTO registeredConference = conferenceService.registerParticipant(email);
            redirectAttributes.addFlashAttribute("success", "Registration successful!");
            redirectAttributes.addFlashAttribute("conference", ConferenceResponseDTO.fromDto(registeredConference));
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
