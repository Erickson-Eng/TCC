package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.api.dto.response.ImageResponse;
import br.com.quatty.backend.api.dto.response.MessageResponse;
import br.com.quatty.backend.business.entity.Image;
import br.com.quatty.backend.business.service.ImageService;
import br.com.quatty.backend.business.utils.ImageUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/image")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ImageController {

    private ImageService imageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        MessageResponse messageResponse = new MessageResponse();
        ImageResponse imageResponse = new ImageResponse();
        URI uri;
        try {
            if (!isImageFile(file)) {
                messageResponse.setContent("Só é permitido realizar upload de imagens");
                return ResponseEntity.badRequest().body(messageResponse);
            }
            Image image = imageService.uploadImage(file);
            imageResponse = ImageResponse.builder()
                    .id(image.getId())
                    .fileName(image.getName())
                    .fileType(image.getType())
                    .build();
            uri = URI.create(image.getId().toString());
        } catch (Exception e) {
            messageResponse.setContent("Ocorreu um erro durante o processamento da imagem");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(messageResponse);
        }
        return ResponseEntity.created(uri).body(imageResponse);

    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getImageForCommunity(@PathVariable("id") Long imageId) {
        byte[] image = null;
        Image dbImage = imageService.getImageById(imageId);
        if (dbImage == null){
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setContent("Recurso não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(messageResponse);

        }
        image = ImageUtil.decompressImage(dbImage.getImageData());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(dbImage.getType()))
                .body(image);
    }


    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
}
