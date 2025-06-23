package com.todotic.bookstoreapi.controller;

import com.todotic.bookstoreapi.model.dto.UploadResponse;
import com.todotic.bookstoreapi.service.FileSystemStorageService;
import com.todotic.bookstoreapi.service.StorageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@AllArgsConstructor
@RequestMapping("/api/media")
@RestController
public class MediaController {

    private StorageService storageService;

    @PostMapping("/upload")
    public UploadResponse Upload(@RequestParam(name = "file")  MultipartFile multipartFile) {
        String path = storageService.store(multipartFile);
        return new UploadResponse(path);
    }

    @GetMapping("/{filename}")
     public ResponseEntity<Resource> download(@PathVariable String filename) throws IOException {
        Resource resource =  storageService.loadAsResource(filename);
        String contenType = Files.probeContentType(resource.getFile().toPath());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contenType).body(resource);
    }
}
