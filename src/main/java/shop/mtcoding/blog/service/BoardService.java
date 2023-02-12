package shop.mtcoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blog.handler.ex.CustomApiException;
import shop.mtcoding.blog.model.BoardRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void save(BoardSaveReqDto boardSaveReqDto, int userId) {
        int result = boardRepository.insert(
                boardSaveReqDto.getTitle(),
                boardSaveReqDto.getContent(),
                "thumbnail",
                userId);
        if (result != 1) {
            throw new CustomApiException("글쓰기 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}