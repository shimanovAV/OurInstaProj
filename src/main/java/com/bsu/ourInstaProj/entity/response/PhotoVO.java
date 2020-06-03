package com.bsu.ourInstaProj.entity.response;

import com.bsu.ourInstaProj.entity.request.PhotoRequest;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoVO {

    private Long id;

    private String url;

    public PhotoVO(PhotoRequest photoRequest) {
        String accountName = "bsulab7shimanovichav";
        String accountKey = "3jL6HKnjuYs4p8sLbBtcyE6bYc92gRXfzcKzGRPeOuD5Zy7HYVHE7vprsHnv70EO6sQIB/MS7qhoJxQbFlaYIg==";
        String connectionString = "DefaultEndpointsProtocol=http;" + "AccountName=" + accountName + ";" + "AccountKey=" + accountKey;
        String containerName = "bsu";
        int i = photoRequest.getUrl().indexOf("image");
        String blobName = photoRequest.getUrl().substring(i);
        try {
            CloudStorageAccount account = CloudStorageAccount.parse(connectionString);
            CloudBlobClient client = account.createCloudBlobClient();
            CloudBlobContainer container = client.getContainerReference(containerName);
            CloudBlockBlob blob = container.getBlockBlobReference(blobName);
            String url = blob.getUri().toString().replace("http", "https");
            URL urlOfImage = new URL(url);
            BufferedImage buffimage = ImageIO.read(urlOfImage);
            buffimage = Thumbnails.of(buffimage).size(photoRequest.getWidth(), photoRequest.getHeight()).asBufferedImage();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(buffimage, "jpg", os);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            blob.upload(is, os.toByteArray().length);
        }catch(InvalidKeyException | URISyntaxException | StorageException | IOException ex) {
            ex.printStackTrace();
        }
    }

}
