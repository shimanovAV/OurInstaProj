package com.bsu.ourInstaProj.dao;

import com.bsu.ourInstaProj.entity.Board;
import com.bsu.ourInstaProj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

   /* @Query("SELECT u FROM User u LEFT JOIN FETCH u.boards b WHERE b.user_id = :id")
    List<User> findBoardsByUserId(@Param("id") Long id);
    @Query("SELECT u FROM Board u WHERE u.Id = :id")
    List<User> findUsersByBoardId(@Param("id") Long id);*/

    Board getBoardById(Long id);

    @Modifying
    @Query("update Board t set t.name = :name where t.id = :id")
    int updateBoard(@Param("id") Long id, @Param("name") String name);

    @Modifying
    @Query("update Board t set t.boardPicture = :board_picture where t.id = :id")
    int updateBoardPicture(@Param("id") Long id, @Param("board_picture") String boardPicture);

}
