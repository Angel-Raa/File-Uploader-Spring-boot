package com.github.angel.raa.modules.service;

import com.github.angel.raa.modules.models.FileEntity;
import com.github.angel.raa.modules.utils.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileService {
    /**
     *
     * Permite almacenar o carga archivo
     * @param file
     * @return
     * @throws IOException
     */
    FileEntity store(MultipartFile file) throws IOException;

    Optional<FileEntity> getFile(UUID id) throws FileNotFoundException;

    List<ResponseFile> findAll();
}
