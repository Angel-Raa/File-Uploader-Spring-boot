package com.github.angel.raa.modules.repository;

import com.github.angel.raa.modules.models.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<FileEntity, UUID> { }
