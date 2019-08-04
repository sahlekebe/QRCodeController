package com.verde.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.verde.service.QRCodeManager;;

@RestController
public class QRCodeController {
	@Autowired
	private QRCodeManager qrManager;

	@GetMapping(path = "generateQRcode", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody byte[] qrCodeGenerator() {

		return qrManager.generateQRcode("First Test ሙከራ");
	}

	@PostMapping(path = "decode")
	public String decodeQRCode(MultipartFile file) {
		return qrManager.decodeQRcode(file);
	}
}
