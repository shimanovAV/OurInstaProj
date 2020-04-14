package com.bsu.ourInstaProj.service;

import com.bsu.ourInstaProj.dao.PhotoRepository;
import com.bsu.ourInstaProj.entity.Photo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final BoardService boardService;

    public PhotoService(PhotoRepository photoRepository, BoardService boardService) {
        this.photoRepository = photoRepository;
        this.boardService = boardService;
    }

    public List<Photo> getAllPhotos(Long boardId) {
        return photoRepository.findAllByBoardId(boardId);
    }

    public Photo addPhoto(Photo newPhoto) {
        Photo photo = photoRepository.save(newPhoto);
        if(photo!=null){
            boardService.changeBoardPicture(photo.getBoardId(), photo.getUrl());
        }
        return photo;
    }

    @Transactional
    public int changePhoto(Photo newPhoto) {
        return photoRepository.updatePhoto(newPhoto.getId(), newPhoto.getDescription());
    }

    public void deletePhoto(Long boardId) {
        photoRepository.deleteById(boardId);
    }

}
