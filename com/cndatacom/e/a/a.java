package com.cndatacom.e.a;

import android.util.Base64;
import com.cndatacom.e.j;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class a
{
  public static String a(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
    String str;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte);
      byte[] arrayOfByte = localMessageDigest.digest();
      char[] arrayOfChar2 = new char[32];
      int i = 0;
      int j = 0;
      while (i < 16)
      {
        int k = arrayOfByte[i];
        int m = j + 1;
        arrayOfChar2[j] = arrayOfChar1[(0xF & k >>> 4)];
        j = m + 1;
        arrayOfChar2[m] = arrayOfChar1[(k & 0xF)];
        i++;
      }
      str = new String(arrayOfChar2);
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "CryptoHelper encryptMD5 Exception");
      str = null;
    }
    return str;
  }

  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    DESedeKeySpec localDESedeKeySpec = new DESedeKeySpec(paramArrayOfByte1);
    SecretKey localSecretKey = SecretKeyFactory.getInstance("desede").generateSecret(localDESedeKeySpec);
    Cipher localCipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
    localCipher.init(1, localSecretKey);
    return localCipher.doFinal(paramArrayOfByte2);
  }

  public static String b(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = { -69, 113, 39, -127, -57, 0, 67, 10, -63, -121, -52, -46, -3, -85, 24, 120, 71, -54, -5, 63, -110, 54, 9, -105 };
    arrayOfByte[4] = (byte)(0xAA ^ arrayOfByte[4]);
    arrayOfByte[1] = (byte)(0x15 ^ arrayOfByte[1]);
    arrayOfByte[19] = (byte)(0x50 ^ arrayOfByte[19]);
    arrayOfByte[20] = (byte)(0xDE ^ arrayOfByte[20]);
    arrayOfByte[15] = (byte)(0x1F ^ arrayOfByte[15]);
    arrayOfByte[9] = (byte)(0xDE ^ arrayOfByte[9]);
    arrayOfByte[22] = (byte)(0x45 ^ arrayOfByte[22]);
    arrayOfByte[6] = (byte)(0x24 ^ arrayOfByte[6]);
    arrayOfByte[0] = (byte)(0x8A ^ arrayOfByte[0]);
    arrayOfByte[7] = (byte)(0x6B ^ arrayOfByte[7]);
    arrayOfByte[17] = (byte)(0xFB ^ arrayOfByte[17]);
    arrayOfByte[8] = (byte)(0x93 ^ arrayOfByte[8]);
    arrayOfByte[5] = (byte)(0x34 ^ arrayOfByte[5]);
    arrayOfByte[12] = (byte)(0xB4 ^ arrayOfByte[12]);
    arrayOfByte[23] = (byte)(0xD5 ^ arrayOfByte[23]);
    arrayOfByte[13] = (byte)(0xE5 ^ arrayOfByte[13]);
    arrayOfByte[11] = (byte)(0x85 ^ arrayOfByte[11]);
    arrayOfByte[16] = (byte)(0x11 ^ arrayOfByte[16]);
    arrayOfByte[2] = (byte)(0x44 ^ arrayOfByte[2]);
    arrayOfByte[3] = (byte)(0xF3 ^ arrayOfByte[3]);
    arrayOfByte[10] = (byte)(0xF4 ^ arrayOfByte[10]);
    arrayOfByte[21] = (byte)(0x7E ^ arrayOfByte[21]);
    arrayOfByte[18] = (byte)(0xB7 ^ arrayOfByte[18]);
    arrayOfByte[14] = (byte)(0x6B ^ arrayOfByte[14]);
    return Base64.encodeToString(a(arrayOfByte, paramArrayOfByte), 2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.a.a
 * JD-Core Version:    0.6.1
 */