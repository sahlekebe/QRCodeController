package com.verde.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeGeneratorImpl implements QRGenerator {
	private static final Logger logger = LoggerFactory.getLogger(QRCodeGeneratorImpl.class);
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	private Map<EncodeHintType, String> hints = new HashMap<>();

	public QRCodeGeneratorImpl() {
    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	}

	@Override
	public byte[] generateQrCode(String payload) {

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		try {

			BitMatrix bitMatrix = qrCodeWriter.encode(payload, BarcodeFormat.QR_CODE, WIDTH, HEIGHT,hints);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);

			return outputStream.toByteArray();

		} catch (Exception e) {
			logger.error("Error in Generating QR Code ", e);

		}

		return new byte[0];
	}

}
