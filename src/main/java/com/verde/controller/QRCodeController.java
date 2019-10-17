package com.verde.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.verde.service.BarCodeManager;
import com.verde.service.QRCodeManager;;

@RestController
public class QRCodeController {
	private static Logger logger = LoggerFactory.getLogger(QRCodeController.class);
	@Autowired
	private QRCodeManager qrManager;

	@Autowired
	private BarCodeManager barCodeManager;

	@GetMapping(path = "generateQRcode", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody byte[] qrCodeGenerator(String content) {

		return qrManager.generateQRcode(content);
	}

	@PostMapping(path = "decode")
	public String decodeQRCode(MultipartFile file) {
		return qrManager.decodeQRcode(file);
	}

	@PostMapping(path = "uploadXls", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void uploadXlsFile(@RequestBody MultipartFile file) {
		BufferedReader bufferedReader = null;
		String imageFileName = "image%s.png";
		if (file != null) {
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String line;
				int counter = 0;
				while ((line = bufferedReader.readLine()) != null) {
					if (counter == 0) {
						counter++;
					} else {

						barCodeManager.crateImage(barCodeManager.generateBarCode(line),
								imageFileName.replace("%s", String.valueOf(counter)));
						counter++;
					}
				}
			} catch (Exception e) {
				logger.error("Error in uploading File ", e);
			}
		}
	}
}
