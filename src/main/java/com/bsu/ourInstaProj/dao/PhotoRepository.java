package com.bsu.ourInstaProj.dao;

import com.bsu.ourInstaProj.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("SELECT u FROM Photo u WHERE u.boardId = :board_id")
    List<Photo> findAllByBoardId(@Param("board_id") Long boardId);

    @Modifying
    @Query("update Photo t set t.description = :description where t.id = :id")
    int updatePhoto(@Param("id") Long id, @Param("description") String description);

}
