package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blog.dto.ResponseDto;
import shop.mtcoding.blog.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blog.dto.board.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.blog.handler.ex.CustomApiException;
import shop.mtcoding.blog.handler.ex.CustomException;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.BoardRepository;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private HttpSession session;


    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("Login Required", HttpStatus.UNAUTHORIZED);
        }
        Board boardPS = boardRepository.findById(id);
        if(boardPS == null){
            throw new CustomException("Board Not Found");
        }
        if(boardPS.getUserId() != principal.getId()){
            throw new CustomException("", HttpStatus.FORBIDDEN);
        }
        model.addAttribute("board",boardPS);
        return "board/updateForm";
    }

    @PutMapping("/board/{id}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable int id, @RequestBody BoardUpdateReqDto boardUpdateReqDto, HttpServletResponse response){
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("Login Required", HttpStatus.UNAUTHORIZED);
        }
        if (boardUpdateReqDto.getTitle() == null || boardUpdateReqDto.getTitle().isEmpty()) {
            throw new CustomApiException("Please Enter Title");
        }
        if (boardUpdateReqDto.getContent() == null || boardUpdateReqDto.getContent().isEmpty()) {
            throw new CustomApiException("Please Enter Content");
        }
        boardService.update(id, boardUpdateReqDto, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "Update Complete!", null), HttpStatus.OK);
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @PostMapping("/board")
    public @ResponseBody ResponseEntity<?> save(@RequestBody BoardSaveReqDto BoardSaveReqDto) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("Login Required", HttpStatus.UNAUTHORIZED);
        }
        if (BoardSaveReqDto.getTitle() == null || BoardSaveReqDto.getTitle().isEmpty()) {
            throw new CustomApiException("Please Enter Title");
        }
        if (BoardSaveReqDto.getContent() == null || BoardSaveReqDto.getContent().isEmpty()) {
            throw new CustomApiException("Please Enter Content");
        }
        if (BoardSaveReqDto.getTitle().length() > 100) {
            throw new CustomApiException("Title must no longer than 100");
        }

        boardService.save(BoardSaveReqDto, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "Save Done!", null), HttpStatus.CREATED);
    }


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
