package com.verde.service;

import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeManagerImpl implements QRCodeManager {
	private static final Logger logger = LoggerFactory.getLogger(QRCodeManagerImpl.class);
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;

	private Map<EncodeHintType, String> hints;
	private Map<DecodeHintType, String> decodeHints;

	public QRCodeManagerImpl() {

		hints = new EnumMap<>(EncodeHintType.class);
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		decodeHints = new EnumMap<>(DecodeHintType.class);
		decodeHints.put(DecodeHintType.CHARACTER_SET, "utf-8");
	}

	@Override
	public byte[] generateQRcode(String payload) {
		try {

			QRCodeWriter qrCodeWriter = new QRCodeWriter();
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
	public String decodeQRcode(MultipartFile file) {
		try {

			QRCodeReader qrCodeReader = new QRCodeReader();
			BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
			BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
			BinaryBitmap image = new BinaryBitmap(new HybridBinarizer(source));
			Result result = qrCodeReader.decode(image,decodeHints);
			return result.getText();

		} catch (Exception e) {
			logger.error("Error during decoding QRCode ", e);
		}
		return "KO";
	}

}
