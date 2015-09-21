/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.GPEDSCVP.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Willys
 */
public class Criptografia {
    
    //Método para criptografar senhas
    public static String criptografarMD5(String original) {
        if (original == null || original.isEmpty()) {
            return null;
        }
        // Cria a classe com o algorítmo de criptografia
        StringBuilder criptografado = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Criptografa a String recebida
            md.update(original.getBytes());
            // Recupera os bytes já criptografados
            byte[] digest = md.digest();
            // Converte os bytes para uma String em hexadecimal
            for (byte b : digest) {
                String hexaDecimal = String.format("%02x", b & 0xff);
                criptografado.append(hexaDecimal);
            }
            System.out.println("original: " + original);
            System.out.println("criptografado: " + criptografado.toString());
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        // Converte o StringBuilder para String e retorna
        return criptografado.toString();
    }
    
}
