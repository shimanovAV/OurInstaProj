package com.bsu.ourInstaProj.service;

import com.bsu.ourInstaProj.dao.BoardRepository;
import com.bsu.ourInstaProj.dao.UserRepository;
import com.bsu.ourInstaProj.entity.Board;
import com.bsu.ourInstaProj.entity.User;
import com.bsu.ourInstaProj.entity.response.BoardVO;
import com.bsu.ourInstaProj.entity.response.UserVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private ModelMapper modelMapper = new ModelMapper();


    public BoardService(BoardRepository boardRepository, UserRepository userRepository, UserService userService) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    public List<BoardVO> getAllBoards() {
        User user = userService.findCurrentUser();
        return boardRepository.findBoardsByUserId(user.getId()).stream()
                .map(board -> convertToVO(board)).collect(Collectors.toList());
    }

    @Transactional
    public BoardVO getBoard(Long boardId) {
        return convertToVO(boardRepository.getBoardById(boardId));
    }

    @Transactional
    public List<UserVO> getAllUsersByBoardId(Long boardId) {
        return boardRepository.getBoardById(boardId).getUsers().stream()
                .map(user -> userService.convertToVO(user)).collect(Collectors.toList());
    }

    public BoardVO addBoard(Board newBoard) {
        newBoard.setUserId(userService.findCurrentUser().getId());
        return convertToVO(boardRepository.save(newBoard));
    }

    @Transactional
    public UserVO addUserToBoard(Long boardId, String username) {
        Board board = boardRepository.getBoardById(boardId);
        List<User> users = board.getUsers();
        User user = userRepository.findByUsername(username);
        users.add(user);
        board.setUsers(users);
        return userService.convertToVO(user);
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

    public BoardVO convertToVO(Board board) {
        return modelMapper.map(board, BoardVO.class);
    }
}
