package com.verde.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.verde.utils.TimeUtils;

@Component
public class BarCodeManagerImpl implements BarCodeManager {
	private static final String BARCODE_LOCATION = "d:/data/images/";
	private static final Logger logger = LoggerFactory.getLogger(BarCodeManagerImpl.class);

	@Override
	public byte[] generateBarCode(String content) throws Exception {
		logger.debug("encoding {} to barcode", content);
		MultiFormatWriter writer = new MultiFormatWriter();
		BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.CODE_128, 200, 200);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
		return outputStream.toByteArray();
	}

	@Override
	public boolean crateImage(byte[] data, String fileName) {
		if (data != null) {
			String path = BARCODE_LOCATION + TimeUtils.getFolderFormattedDate();
			File directory = new File(path);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			File file = new File(path, fileName);
			try {
				Files.write(file.toPath(), data);
				return true;
			} catch (IOException e) {
				logger.error("Can not write the file ", e);
			}

		}
		return false;
	}

}
