package com.verde.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.verde.service.QRGenerator;

@RestController
public class QRCodeController {
	@Autowired
	private QRGenerator qrGenerator;

	@GetMapping(path = "generateQRcode",produces= {MediaType.IMAGE_PNG_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody byte[] qrCodeGenerator() {

		return qrGenerator.generateQrCode("First Test ሙከራ");
	}
	

}
