package com.example.sweater.Controllers;

import com.example.sweater.Repositories.EventRepository;
import com.example.sweater.Repositories.GuestRepository;
import com.example.sweater.Repositories.RoleRepository;
import com.example.sweater.Repositories.UserRepository;
import com.example.sweater.Services.GuestService;
import com.example.sweater.Services.UserService;
import com.example.sweater.domain.Event;
import com.example.sweater.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    @Value("${upload.path}")
    String uploadPath;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public String adminMenu()
    {
        return "userlist";
    }
    @GetMapping("/users")
    public String userList(Model model)
    {
        model.addAttribute("users",userRepo.findAll() );
        return "deleteusers";
    }
    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id, Model model)
    {
        User user = userRepo.findById(id);
        userService.deleteUser(user);
        model.addAttribute("users", userRepo.findAll() );
        return "userlist";
    }
    @GetMapping("/events")
    public String eventList(Model model)
    {
        model.addAttribute("events",eventRepository.findAll() );
        return "deleteevents";
    }
    @GetMapping("/events/{name}")
    public String deleteEvent(@PathVariable String name, Model model)
    {
        Event event = eventRepository.findByname(name);
        eventRepository.delete(event);
        model.addAttribute("events", eventRepository.findAll());
        return "deleteevents";
    }
    @GetMapping("/events/update/{name}")
    public String updateEvent(@PathVariable String name, Model model)
    {
        Event event = eventRepository.findByname(name);
        model.addAttribute("event", event);
        return "updateevent";
    }
    @PostMapping("/events/update/{name}")
    public String addNewUser(@Valid Event event,@PathVariable String name, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) throws IOException {

        Event thisevent = eventRepository.findByname(name);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFileName));
                thisevent.setFilename(resultFileName);
            }

            thisevent.setPlace(event.getPlace());
            thisevent.setRequiredage(event.getRequiredage());
            thisevent.setName(event.getName());
            eventRepository.save(thisevent);
        }
        model.addAttribute("events", eventRepository.findAll());
        return "redirect:/main";
    }



}
