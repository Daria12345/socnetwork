package com.example.sweater.Controllers;

import com.example.sweater.Repositories.*;
import com.example.sweater.Services.GuestService;
import com.example.sweater.Services.UserService;
import com.example.sweater.config.MailSender;
import com.example.sweater.domain.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class GreetingController {
    @Value("${upload.path}")
    String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        boolean isMain = true;
        Iterable<Event> events = eventRepository.findAll();
        if (filter != null && !filter.isEmpty()) {
            events = eventRepository.findByplace(filter);
        } else {
            events = eventRepository.findAll();
        }
        model.addAttribute("isMain", isMain);
        model.addAttribute("events", events);
        model.addAttribute("filter", filter);
        return "main";
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserService userService;
    @Autowired
    private ListOfOrgRepository listOfOrgRepository;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    FavoritesRepository favoritesRepository;

    @GetMapping("/main/{name}")
    public String aboutEvent(@PathVariable String name, Model model, Principal user) {
        model.addAttribute("categories", personalRepository.findByevent(eventRepository.findByname(name)));
        Event event = eventRepository.findByname(name);
        int currentNumber = event.getNumberofguests();
        int newnumber = currentNumber + 1;
        event.setNumberofguests(newnumber);
        eventRepository.save(event);
        boolean isUser = false;
        boolean isFavorite = false;
        Favorites favorites = favoritesRepository.findByUserAndAndEvent(user.getName(), name);
        if (favorites == null) {
            isFavorite = true;
        }
        if (event.getUser().equals(user.getName())) isUser = true;
        model.addAttribute("isFavorite", isFavorite);
        model.addAttribute("event", event);
        model.addAttribute("isUser", isUser);
        return "event";
    }

    @PostMapping("/main/{name}")
    public String addGuest(@PathVariable String name, @Valid Guest guest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("guest", guest);
            Event event = eventRepository.findByname(name);
            model.addAttribute("event", event);
            return "event";
        } else {
            Event thisevent = eventRepository.findByname(name);
            mailSender.send(guest.getEmail(), "Новый пост: " + name, "Посмотрите новый пост " + name + " ! От: " + thisevent.getDate());
            model.addAttribute("message", "Сообщение успешно отправлено");
            return "acceptguest";
        }

    }

    @PostMapping("/main")
    public String addNewUser(@Valid Event event, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file, Principal user) throws IOException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("event", event);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFileName));
                event.setFilename(resultFileName);
            }
            event.setUser(user.getName());
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            event.setDate(dateFormat.format(currentDate));
            event.setNumberofguests(0);
            eventRepository.save(event);
        }
        model.addAttribute("events", eventRepository.findAll());
        boolean isMain = true;
        model.addAttribute("isMain", isMain);
        return "main";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal user) {
        model.addAttribute("username", user.getName());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(Principal user, @RequestParam String password, @RequestParam String password2, Model model) {
        if (password.equals(password2)) userService.updateUser(user.getName(), password);
        else model.addAttribute("message", "Пароли не совпадают");
        return "redirect:/profile";
    }

    @GetMapping("/addpersonal/{name}")
    public String getPersonal(Model model) {
        Iterable<Category> ohrOrg = listOfOrgRepository.findByKindOfCategory("kind");
        Iterable<Category> reklOrg = listOfOrgRepository.findByKindOfCategory("theme");
        Iterable<Category> cleanOrg = listOfOrgRepository.findByKindOfCategory("category");
        model.addAttribute("kind", ohrOrg);
        model.addAttribute("theme", reklOrg);
        model.addAttribute("category", cleanOrg);
        return "addpersonal";
    }

    @PostMapping("/addpersonal/{name}")
    public String setPersonal(Model model, @PathVariable String name, @RequestParam(defaultValue = "null") String[] kinds, @RequestParam(defaultValue = "null") String[] themes, @RequestParam(defaultValue = "null") String[] categories) {
        Event event = eventRepository.findByname(name);

        if (!kinds[0].equals("null")) {
            for (int i = 0; i < kinds.length; i++) {
                PostCategories postCategories = new PostCategories();
                postCategories.setEvent(event);
                postCategories.setKindOfCategory("Виды");
                postCategories.setNameOfCategory(kinds[i]);
                personalRepository.save(postCategories);
            }
        }
        if (!categories[0].equals("null")) {
            for (int i = 0; i < categories.length; i++) {
                PostCategories postCategories = new PostCategories();
                postCategories.setEvent(event);
                postCategories.setKindOfCategory("Темы");
                postCategories.setNameOfCategory(categories[i]);
                personalRepository.save(postCategories);
            }
        }
        if (!themes[0].equals("null")) {
            for (int i = 0; i < themes.length; i++) {
                PostCategories postCategories = new PostCategories();
                postCategories.setEvent(event);
                postCategories.setKindOfCategory("Категории");
                postCategories.setNameOfCategory(themes[i]);
                personalRepository.save(postCategories);
            }
        }
        model.addAttribute("message", "Категории успешно добавлены");
        return "acceptguest";
    }

    @GetMapping("/listOfpersonal/{name}")
    public String getListOfpersonal(Model model, @PathVariable String name) {
        model.addAttribute("categories", personalRepository.findByevent(eventRepository.findByname(name)));
        return "listofpersonal";
    }

    @PostMapping("/listOfpersonal/{name}")
    public String getListOfpersonal(Model model, @PathVariable String name, @RequestParam String[] delOrgs) {
        Event event = eventRepository.findByname(name);
        for (int i = 0; i < delOrgs.length; i++) {

            List<PostCategories> postCategories = personalRepository.findByNameOfCategoryAndEvent(delOrgs[i], event);
            for (PostCategories categories : postCategories
            ) {
                personalRepository.delete(categories);
            }

        }
        model.addAttribute("categories", personalRepository.findByevent(eventRepository.findByname(name)));
        return "listofpersonal";
    }

    @GetMapping("/events/update/{name}")
    public String updateEvent(@PathVariable String name, Model model) {
        Event event = eventRepository.findByname(name);
        model.addAttribute("event", event);
        return "updateevent";
    }

    @PostMapping("/events/update/{name}")
    public String addNewUser(@Valid Event event, @PathVariable String name, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) throws IOException {

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

    @GetMapping("/favorites")
    public String favorites(@RequestParam(required = false, defaultValue = "") String filter, Model model, Principal user) {
        boolean isMain = false;
        Iterable<Favorites> favorites = favoritesRepository.findByUser(user.getName());
        List<Event> events = new ArrayList<>();
        for (Favorites favorite : favorites) {
            Event event = eventRepository.findByname(favorite.getEvent());
            events.add(event);
        }
        model.addAttribute("events", events);
        model.addAttribute("filter", filter);
        model.addAttribute("isMain", isMain);
        return "main";
    }

    @PostMapping("/favorites/add/{name}")
    public String addFavorite(@PathVariable String name, Principal user) {
        Favorites favorites = new Favorites();
        favorites.setEvent(name);
        favorites.setUser(user.getName());
        favoritesRepository.save(favorites);
        return "redirect:/main";
    }
    @PostMapping("/favorites/delete/{name}")
    public String deleteFavorites(@PathVariable String name, Principal user) {
        Favorites favorites = favoritesRepository.findByUserAndAndEvent(user.getName(), name);
        favoritesRepository.delete(favorites);
        return "redirect:/main";
    }
}

