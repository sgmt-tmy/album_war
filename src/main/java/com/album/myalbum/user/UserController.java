package com.album.myalbum.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    //全ユーザ一覧
    @GetMapping
    public String index(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/index";
    }

    //追加画面遷移
    @GetMapping("new")
    public String newUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "users/new";
    }

    //編集画面遷移
    @GetMapping("{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        return "users/edit";
    }

    //ユーザ表示
    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model){
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        return "users/show";
    }

    //新規登録
    @PostMapping
    public String create(
            @Valid @ModelAttribute User user,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()) return "users/new";

        userService.save(user);
        return "redirect:/users";
    }

    //編集
    @PutMapping("{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute User user,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()) return "users/edit";

        user.setId(id);
        userService.save(user);
        return "redirect:/users";
    }

    //削除
    @DeleteMapping("{id}")
    public String destroy(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/users";
    }
}