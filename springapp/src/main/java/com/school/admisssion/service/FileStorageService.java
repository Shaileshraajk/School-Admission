package com.school.admisssion.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) throws IOException{
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		Files.createDirectories(this.fileStorageLocation);
    }


    //function to store the file
	public String storeFile(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path targetLocation = this.fileStorageLocation.resolve(fileName);
		Files.copy(file.getInputStream(), targetLocation,StandardCopyOption.REPLACE_EXISTING);
		return fileName;
	}

    //function to load the file
	public Resource loadFileAsResource(String fileName) throws MalformedURLException {
		Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
		Resource resource = new UrlResource(filePath.toUri());
		if(resource.exists()) {
			return resource;
		}
		return null;
	}
    
}
