package shop.mtcoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.blog.model.BoardRepository;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping({ "/", "/board" })
    public String main(Model model) {
        model.addAttribute("dtos", boardRepository.findAllWithUser());
        return "board/main";
    }
    
    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("boardDto", boardRepository.findByIdWithUser(id));
        return "board/detail";
    }
}
