package com.bsu.ourInstaProj;

import com.bsu.ourInstaProj.dao.BoardRepository;
import com.bsu.ourInstaProj.dao.PhotoRepository;
import com.bsu.ourInstaProj.entity.Board;
import com.bsu.ourInstaProj.entity.Photo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class OurInstaProjApplicationTests {

    @Mock
    private BoardRepository boardRepository;
    @Mock
    private PhotoRepository photoRepository;

    @BeforeEach
    void boardSave(){
        Board board = new Board();
        boardRepository.save(board);
        Photo photo = new Photo();
        photoRepository.save(photo);
    }

    @Test
    void assertNameEquals() {
        Board result = boardRepository.getBoardById(1L);
        assertEquals(null, result);
    }

    @Test
    void assertDescriptionEquals() {
        List<Photo> result = photoRepository.findAllByBoardId(1L);
        List<Photo> expected = new ArrayList<>();
        assertEquals(expected, result);
    }

}