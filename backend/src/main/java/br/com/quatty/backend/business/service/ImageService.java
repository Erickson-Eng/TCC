package br.com.quatty.backend.business.service;

import br.com.quatty.backend.business.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Image uploadImage(MultipartFile file) throws IOException;

    Image getImageById(Long imageId);
    byte[] getImage(Long imageId);
}
