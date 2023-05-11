package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.business.entity.Image;
import br.com.quatty.backend.business.service.ImageService;
import br.com.quatty.backend.business.utils.ImageUtil;
import br.com.quatty.backend.infra.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ImageServicePostgres implements ImageService {

    private ImageRepository imageRepository;

    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes()))
                .build();
        return imageRepository.save(image);
    }

    @Override
    public Image getImageById(Long imageId) {
        Optional<Image> dbImage = imageRepository.findById(imageId);
        return dbImage.orElse(null);
    }

    @Override
    public byte[] getImage(Long imageId) {
        Optional<Image> dbImage = imageRepository.findById(imageId);
        return dbImage.map(image -> ImageUtil.decompressImage(image.getImageData())).orElseGet(() -> new byte[0]);
    }
}
