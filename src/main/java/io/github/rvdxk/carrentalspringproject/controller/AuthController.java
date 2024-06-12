package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.UserDto;
import io.github.rvdxk.carrentalspringproject.entity.User;
import io.github.rvdxk.carrentalspringproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final UserService userService;

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult userResult,
                               Model model){

        User existingUser = userService.getUserByEmail(userDto.getEmail());
        if (existingUser != null &&
                existingUser.getEmail() != null &&
                !existingUser.getEmail().isEmpty()){
            userResult.rejectValue("email", null, "There is already an account with the same email address.");
        }

        if (existingUser!= null &&
                existingUser.getUsername() != null &&
                !existingUser.getUsername().isEmpty()){
            userResult.rejectValue("username", null, "There is already an account with the same username.");
        }

        if (userResult.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.addUser(userDto);

        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
