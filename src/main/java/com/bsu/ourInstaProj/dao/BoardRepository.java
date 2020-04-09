package com.bsu.ourInstaProj.dao;

import com.bsu.ourInstaProj.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying
    @Query("update Board t set t.name = :name where t.id = :id")
    int updateBoard(@Param("id") Long id, @Param("name") String name);

}
