package com.bsu.ourInstaProj.service;

import com.bsu.ourInstaProj.dao.BoardRepository;
import com.bsu.ourInstaProj.entity.Board;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        //should be findAllBoardsWhereUserId
        return boardRepository.findAll();
    }

    public Board addBoard(Board newBoard) {
        //add to newBoard id of currentUser
        return boardRepository.save(newBoard);
    }

    @Transactional
    public int changeBoard(Board newBoard) {
        return boardRepository.updateBoard(newBoard.getId(), newBoard.getName());
    }

    @Transactional
    public int changeBoardPicture(Long boardId, String newPictureUrl) {
        return boardRepository.updateBoardPicture(boardId, newPictureUrl);
    }

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
