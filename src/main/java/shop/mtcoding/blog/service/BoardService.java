package shop.mtcoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.handler.ex.CustomApiException;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.BoardRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    
    @Transactional
    public void delete(int id, int userId) {
        Board boardPS = boardRepository.findById(id);
        if(boardPS == null){
            throw new CustomApiException("Board Not Found");
        }
        if(boardPS.getUserId() != userId){
            throw new CustomApiException("You Have No permission", HttpStatus.FORBIDDEN);
        }
        try {
            boardRepository.deleteById(id);
        }catch (Exception e){
            throw new CustomApiException("Fail to Delete", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
