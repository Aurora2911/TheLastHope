package com.adventure.crypto;

/**
 * It encrypts a string with the XOR operation by using an one byte key
 */
public class XorCrypter {
    public static String hexCrypto(String s, int key) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i += 2) {
            sb.append((char) Integer.parseInt(s.substring(i, i + 2), 16));
        }

        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, ((char) (sb.charAt(i) ^ (char) key)));
        }

        return sb.toString();
    }
}
