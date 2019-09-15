package com.verde.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Component;

@Component
public class BarCodeManagerImpl  implements BarCodeManager{
    @Override

    public  byte[] generateBarCode(String content) throws  Exception{

        MultiFormatWriter writer=new MultiFormatWriter();
        BitMatrix bitMatrix =  writer.encode(content,BarcodeFormat.CODE_128,200,200);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
        return outputStream.toByteArray();
    }
}
