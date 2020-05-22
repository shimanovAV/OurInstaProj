package com.bsu.ourInstaProj.service;

import com.bsu.ourInstaProj.dao.PhotoRepository;
import com.bsu.ourInstaProj.entity.Photo;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final BoardService boardService;
    private final UserService userService;

    public PhotoService(PhotoRepository photoRepository, BoardService boardService, UserService userService) {
        this.photoRepository = photoRepository;
        this.boardService = boardService;
        this.userService = userService;
    }

    public List<Photo> getAllPhotos(Long boardId) {
        return photoRepository.findAllByBoardId(boardId);
    }

    public String addPhoto(MultipartFile newPhoto, String boardId) {
        String accountName = "bsulab7shimanovichav";
        String accountKey = "3jL6HKnjuYs4p8sLbBtcyE6bYc92gRXfzcKzGRPeOuD5Zy7HYVHE7vprsHnv70EO6sQIB/MS7qhoJxQbFlaYIg==";
        try {
            String storageConnectionString = "DefaultEndpointsProtocol=http;" + "AccountName=" + accountName + ";" + "AccountKey=" + accountKey;
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
            CloudBlobContainer container = blobClient.getContainerReference("bsu");
            InputStream is;
            String contentType = newPhoto.getContentType();
            if (contentType != null) {
                is = new DataInputStream(newPhoto.getInputStream());
                long length = newPhoto.getSize();
                Photo lastPhoto = photoRepository.findFirstByOrderByIdDesc();
                final String fileName;
                if (lastPhoto != null) {
                    Long uniquId = lastPhoto.getId() + 1;
                    fileName = "image" + uniquId + ".jpg";
                } else {
                    fileName = "image1.jpg";
                }
                CloudBlockBlob blob = container.getBlockBlobReference(fileName);
                blob.upload(is, length);
                photoRepository.save(new Photo(null, Long.valueOf(boardId), null, blob.getUri().toString(), userService.findCurrentUser().getUsername()));
                boardService.changeBoardPicture(Long.valueOf(boardId), blob.getUri().toString());
                return blob.getUri().toString();
            }
        } catch (InvalidKeyException | URISyntaxException | StorageException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Transactional
    public int changePhoto(Photo newPhoto) {
        return photoRepository.updatePhoto(newPhoto.getId(), newPhoto.getDescription());
    }

    public void deletePhoto(Long boardId) {
        photoRepository.deleteById(boardId);
    }

}
