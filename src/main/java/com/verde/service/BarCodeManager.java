package com.verde.service;

public interface BarCodeManager {
    byte[] generateBarCode(String content) throws Exception;
}
