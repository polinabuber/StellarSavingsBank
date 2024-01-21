package com.ssb.web;



import com.ssb.data.model.*;
import com.ssb.service.*;
import com.ssb.service.security.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
public class UserController {

    private final UserService userService;
    private final UserAccountService userAccountService;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(UserService userService, UserAccountService userAccountService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.userAccountService = userAccountService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("account", new UserAccountDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto, HttpServletRequest request, Model model) {
        try {
            UserDto user = authenticationService.register(userDto, request.getParameter("phone_number"));
            model.addAttribute("user", user);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        UserDto userDto = authenticationService.getUser();
        if (userDto != null) {
            model.addAttribute("user", userDto);
            List<UserAccountDto> accounts = userService.getUserAccounts(userDto.getId());
            if (!accounts.isEmpty()) {
                model.addAttribute("account", accounts.get(0));
            }
        }
        return "home";
    }

}




