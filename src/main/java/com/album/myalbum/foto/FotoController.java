package com.album.myalbum.foto;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/fotos")
public class FotoController {

    @Autowired
    private FotoService fotoService;

    @GetMapping("new")
    public String newFoto(Model model) {

        List<Foto> allfotos = fotoService.findAll();
        model.addAttribute("allFotos", allfotos);

        Foto foto = new Foto();
        model.addAttribute("foto", foto);
        
        return "fotos/upload";
    }

    @PostMapping
    public String create(@RequestParam MultipartFile imageFile,
                        @Valid @ModelAttribute Foto foto,
                        BindingResult bindingResult
                        ) {

        if(bindingResult.hasErrors()) return "fotos/upload";

        fotoService.saveFoto(foto, imageFile);

        return "redirect:/fotos/new";
    }
    
}
