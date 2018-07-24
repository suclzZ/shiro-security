package com.code;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.io.UnsupportedEncodingException;

public class CipherKeyTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String base64Str = Base64.encodeToString( "sucl".getBytes() );

        System.out.println(base64Str);

        System.out.println(Base64.decodeToString(base64Str));

        //2AvVhdsgUs0FSA3SDFAdag==
        byte[] var = Base64.decode("4AvVhmFLUs0KTA3Kprsdag==");

        System.out.println(new String(var,"utf-8"));

        byte[] aes = new AesCipherService().generateNewKey().getEncoded();

        System.out.println( Base64.encodeToString(aes)); //03RvItlW1tAE6QOkgaVtjA==

    }
}
