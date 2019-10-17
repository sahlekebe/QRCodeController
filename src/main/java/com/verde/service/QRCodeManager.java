package com.verde.service;

import org.springframework.web.multipart.MultipartFile;

public interface QRCodeManager {
	byte[] generateQRcode(String payload);

	String decodeQRcode(MultipartFile file);
}
