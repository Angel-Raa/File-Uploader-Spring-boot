package com.github.angel.raa.modules.service;

import com.github.angel.raa.modules.models.FileEntity;
import com.github.angel.raa.modules.repository.FileRepository;
import com.github.angel.raa.modules.utils.ResponseFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    private final FileRepository fileRepository;
    @Override
    public FileEntity store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileEntity fileEntity = FileEntity.builder()
                .name(fileName)
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
        return fileRepository.save(fileEntity);
    }

    @Override
    public Optional<FileEntity> getFile(UUID id) throws FileNotFoundException {
        Optional<FileEntity> file = fileRepository.findById(id);
        if(file.isPresent()){
            return file;
        }
        throw  new FileNotFoundException();
    }
    @Transactional(readOnly = true)
    @Override
    public List<ResponseFile> findAll() {
        return fileRepository.findAll().stream()
                .map((file) -> {
                    String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("api/file/")
                            .path(file.getId().toString())
                            .toUriString();
                    return ResponseFile.builder()
                            .name(file.getName())
                            .type(file.getType())
                            .url(fileDownloadUrl)
                            .size(file.getData().length)
                            .build();
                }).toList();
    }



}
