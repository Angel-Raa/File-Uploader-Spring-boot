package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.models.FileEntity;
import com.github.angel.raa.modules.service.FileService;
import com.github.angel.raa.modules.utils.Response;
import com.github.angel.raa.modules.utils.ResponseFile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/file")
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        fileService.store(file);
        Response res = Response.builder()
                .message("file uploaded successfully")
                .code(200)
                .build();
        return ResponseEntity.ok(res);
    }
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) throws FileNotFoundException {
        FileEntity file = fileService.getFile(id).orElseThrow(() -> new FileNotFoundException("Not found File") );
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, file.getType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; Filename=\" " + file.getName() + "\"")
                .body(file.getData());
    }


    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getFileAll(){
        List<ResponseFile> files = fileService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(files);
    }
}
