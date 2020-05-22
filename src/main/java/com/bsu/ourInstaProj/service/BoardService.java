package com.bsu.ourInstaProj.service;

import com.bsu.ourInstaProj.dao.BoardRepository;
import com.bsu.ourInstaProj.dao.UserRepository;
import com.bsu.ourInstaProj.entity.Board;
import com.bsu.ourInstaProj.entity.User;
import com.bsu.ourInstaProj.entity.response.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    public BoardService(BoardRepository boardRepository, UserRepository userRepository, UserService userService) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    public List<Board> getAllBoards() {
        User user = userService.findCurrentUser();
        return boardRepository.findBoardsByUserId(user.getId());
    }

    @Transactional
    public Board getBoard(Long boardId) {
        return boardRepository.getBoardById(boardId);
    }

    @Transactional
    public List<UserVO> getAllUsersByBoardId(Long boardId) {
        List<User> users = boardRepository.getBoardById(boardId).getUsers();
        return users.stream().map(user -> userService.convertToVO(user)).collect(Collectors.toList());

    }

    public Board addBoard(Board newBoard) {
        User user = userService.findCurrentUser();
        newBoard.setUserId(user.getId());
        return boardRepository.save(newBoard);
    }

    @Transactional
    public Board addUserToBoard(Long boardId, String username) {
        Board board = boardRepository.getBoardById(boardId);
        List<User> users = board.getUsers();
        users.add(userRepository.findByUsername(username));
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

    @Transactional
    public void deleteUserFromBoard(Long boardId, Long userId) {
        Board board = boardRepository.getBoardById(boardId);
        List<User> users = board.getUsers();
        users.remove(userRepository.getById(userId));
        board.setUsers(users);
    }
}
