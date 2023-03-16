package com.example.sfs.controller;

import com.example.sfs.config.CommonConfig;
import com.example.sfs.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    @GetMapping(value = "/{productName}/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> userSearch(@PathVariable("productName") String productName, @PathVariable("imageName") String imageName) throws IOException {
        String destPath = CommonConfig.BASE_IMG_FILE_PATH + "/" + CommonUtil.changeSpaceToUnderBar(productName) + "/" + CommonUtil.changeSpaceToUnderBar(imageName);
        InputStream imageStream = new FileInputStream(destPath);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }
}
