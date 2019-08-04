package com.verde.service;

public interface QRCodeManager {
   byte [] generateQRcode(String payload);
   String decodeQRcode(byte []qrcode);
}
