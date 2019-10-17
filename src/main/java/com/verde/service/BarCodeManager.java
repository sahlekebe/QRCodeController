package com.verde.service;

public interface BarCodeManager {
	byte[] generateBarCode(String content) throws Exception;
	boolean crateImage(byte []data,String fileName);
}
