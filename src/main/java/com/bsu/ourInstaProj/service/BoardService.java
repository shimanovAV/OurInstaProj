package com.bsu.ourInstaProj.service;

import com.bsu.ourInstaProj.dao.BoardRepository;
import com.bsu.ourInstaProj.dao.UserRepository;
import com.bsu.ourInstaProj.entity.Board;
import com.bsu.ourInstaProj.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public List<Board> getAllBoards() {
        //should be findAllBoardsWhereUserId
        return boardRepository.findAll();
    }

    public List<User> getAllUsersByBoardId(Long boardId) {
        return boardRepository.getBoardById(boardId).getUsers();
    }

    public Board addBoard(Board newBoard) {
        //add to newBoard id of currentUser
        return boardRepository.save(newBoard);
    }

    public Board addUserToBoard(Long boardId, String username) {
        Board board = boardRepository.getBoardById(boardId);
        List<User> users = board.getUsers();
        users.add(userRepository.getByUsername(username));
        board.setUsers(users);
        return board;
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

    public void deleteUserFromBoard(Long boardId,Long userId) {
        Board board = boardRepository.getBoardById(boardId);
        List<User> users = board.getUsers();
        users.remove(userRepository.getById(userId));
        board.setUsers(users);
    }
}
