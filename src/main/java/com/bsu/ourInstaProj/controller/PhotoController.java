package com.bsu.ourInstaProj.controller;

import com.bsu.ourInstaProj.entity.Photo;
import com.bsu.ourInstaProj.entity.response.PhotoResponse;
import com.bsu.ourInstaProj.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhotoController {

    private PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @RequestMapping(path = {"/photos/{boardId}"}, method = RequestMethod.GET)
    public ResponseEntity<PhotoResponse> getAllPhotos(final @PathVariable Long boardId) {
        List<Photo> photos = photoService.getAllPhotos(boardId);
        return new ResponseEntity<>(new PhotoResponse(photos), HttpStatus.OK);
    }

    @RequestMapping(path = {"/photo"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> changeBoard(@RequestBody Photo newPhoto) {
        Integer success = photoService.changePhoto(newPhoto);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @RequestMapping(path = {"/photo"}, method = RequestMethod.POST)
    public ResponseEntity<Photo> addPhoto(@RequestBody Photo newPhoto) {
        Photo photo =  photoService.addPhoto(newPhoto);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @RequestMapping(path = {"/photo/{photoId}"}, method = RequestMethod.DELETE)
    public ResponseEntity<Photo> deletePhoto(final @PathVariable Long photoId) {
        photoService.deletePhoto(photoId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
