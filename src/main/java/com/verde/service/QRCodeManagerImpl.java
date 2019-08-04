package com.verde.service;

import java.util.EnumMap;
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
public class QRCodeManagerImpl implements QRCodeManager {
	private static final Logger logger = LoggerFactory.getLogger(QRCodeManagerImpl.class);
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;

	private Map<EncodeHintType, String> hints;

	public QRCodeManagerImpl() {
		hints = new EnumMap<>(EncodeHintType.class);

		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	}

	@Override
	public byte[] generateQRcode(String payload) {

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		try {

			BitMatrix bitMatrix = qrCodeWriter.encode(payload, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);

			return outputStream.toByteArray();

		} catch (Exception e) {
			logger.error("Error in Generating QR Code ", e);

		}

		return new byte[0];
	}

	@Override
	public byte[] generateQRcode(String payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decodeQRcode(byte[] qrcode) {
		// TODO Auto-generated method stub
		return null;
	}

}
