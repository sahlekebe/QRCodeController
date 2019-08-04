package com.verde.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QRCodeController {
	
	  @GetMapping(path="generateQRcode")
	 public String qrCodeGenerator() {
		 return "Ok";
	 }
}
