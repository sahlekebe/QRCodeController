package com.verde;


import com.verde.service.BarCodeManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Files;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BarCoderManagerTest {
    @Autowired
    private BarCodeManager barCodeManager;
    @Test
    public  void generateBarCode() throws  Exception{

        Files.write(new File("test.png").toPath(),barCodeManager.generateBarCode("12345678974141414"));
    }
}
