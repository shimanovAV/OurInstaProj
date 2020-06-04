package com.bsu.ourInstaProj.dao;

import com.bsu.ourInstaProj.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findBoardsByUserId(Long userId);

    @Query("select b from Board b left join fetch b.users bu where bu.id = :user_id")
    List<Board> findBoardsByUsers(@Param("user_id")Long userId);

    Board getBoardById(Long id);

    void deleteBoardById(Long id);

    @Modifying
    @Query("update Board t set t.name = :name where t.id = :id")
    int updateBoard(@Param("id") Long id, @Param("name") String name);

    @Modifying
    @Query("update Board t set t.boardPicture = :board_picture where t.id = :id")
    int updateBoardPicture(@Param("id") Long id, @Param("board_picture") String boardPicture);

}
