package com.verde;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.verde.service.BarCodeManager;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class BarCoderManagerTest {

	private static final String FILE_LOCATION = "src/main/resources/sampleDoc/";
	@Autowired
	private BarCodeManager barCodeManager;
	@Autowired
	private MockMvc mvc;

	// @Test
	public void generateBarCode() throws Exception {

		Files.write(new File("test.png").toPath(), barCodeManager.generateBarCode("12345678974141414"));
	}

	@Test
	public void barcodeControllerTest() throws Exception {
		File file = new File(FILE_LOCATION, "barCodeValue.csv");
		InputStream inputStream = new FileInputStream(file);
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", inputStream);
		this.mvc.perform(multipart("/uploadXls").file(mockMultipartFile)).andExpect(status().is(200));
	}
}
