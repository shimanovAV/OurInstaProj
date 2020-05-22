package com.bsu.ourInstaProj.controller;

import com.bsu.ourInstaProj.entity.Board;
import com.bsu.ourInstaProj.entity.response.BoardResponse;
import com.bsu.ourInstaProj.entity.response.BoardVO;
import com.bsu.ourInstaProj.entity.response.UserResponse;
import com.bsu.ourInstaProj.entity.response.UserVO;
import com.bsu.ourInstaProj.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(path = {"/boards"}, method = RequestMethod.GET)
    public ResponseEntity<BoardResponse> getAllBoards() {
        List<BoardVO> boards = boardService.getAllBoards();
        return new ResponseEntity<>(new BoardResponse(boards), HttpStatus.OK);
    }

    @RequestMapping(path = {"/board/{boardId}"}, method = RequestMethod.GET)
    public ResponseEntity<BoardVO> getBoard(final @PathVariable Long boardId) {
        BoardVO board = boardService.getBoard(boardId);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @RequestMapping(path = {"/board"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> changeBoard(@RequestBody Board newBoard) {
        Integer success = boardService.changeBoard(newBoard);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @RequestMapping(path = {"/board"}, method = RequestMethod.POST)
    public ResponseEntity<BoardVO> addBoard(@RequestBody Board newBoard) {
        BoardVO board = boardService.addBoard(newBoard);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @RequestMapping(path = {"/board/{boardId}"}, method = RequestMethod.DELETE)
    public ResponseEntity<BoardVO> deleteBoard(final @PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = {"/board/{boardId}/users"}, method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getBoardUsers(final @PathVariable Long boardId) {
        List<UserVO> users = boardService.getAllUsersByBoardId(boardId);
        return new ResponseEntity<>(new UserResponse(users), HttpStatus.OK);
    }

    @RequestMapping(path = {"/board/{boardId}/user"}, method = RequestMethod.POST)
    public ResponseEntity<BoardVO> addUserToBoard(final @PathVariable Long boardId,
                                                @RequestBody String username) {
        BoardVO board = boardService.addUserToBoard(boardId, username);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @RequestMapping(path = {"/board/{boardId}/user/{userId}"}, method = RequestMethod.DELETE)
    public ResponseEntity<Board> deleteUserFromBoard(final @PathVariable Long boardId,
                                                     final @PathVariable Long userId) {
        boardService.deleteUserFromBoard(boardId, userId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
