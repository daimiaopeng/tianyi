package com.tencent.bugly.proguard;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class ae
  implements ag
{
  private String a = null;

  public final void a(String paramString)
  {
    if (paramString != null)
    {
      for (int i = paramString.length(); i < 16; i++)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append("0");
        paramString = localStringBuilder.toString();
      }
      this.a = paramString.substring(0, 16);
    }
  }

  public final byte[] a(byte[] paramArrayOfByte)
  {
    if ((this.a != null) && (paramArrayOfByte != null))
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      int i = paramArrayOfByte.length;
      int j = 0;
      for (int k = 0; k < i; k++)
      {
        int i1 = paramArrayOfByte[k];
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(i1);
        localStringBuilder2.append(" ");
        localStringBuffer1.append(localStringBuilder2.toString());
      }
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(this.a.getBytes(), "AES");
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      localCipher.init(2, localSecretKeySpec, new IvParameterSpec(this.a.getBytes()));
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte);
      StringBuffer localStringBuffer2 = new StringBuffer();
      int m = arrayOfByte.length;
      while (j < m)
      {
        int n = arrayOfByte[j];
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append(n);
        localStringBuilder1.append(" ");
        localStringBuffer2.append(localStringBuilder1.toString());
        j++;
      }
      return arrayOfByte;
    }
    return null;
  }

  public final byte[] b(byte[] paramArrayOfByte)
  {
    if ((this.a != null) && (paramArrayOfByte != null))
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      int i = paramArrayOfByte.length;
      int j = 0;
      for (int k = 0; k < i; k++)
      {
        int i1 = paramArrayOfByte[k];
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(i1);
        localStringBuilder2.append(" ");
        localStringBuffer1.append(localStringBuilder2.toString());
      }
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(this.a.getBytes(), "AES");
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      localCipher.init(1, localSecretKeySpec, new IvParameterSpec(this.a.getBytes()));
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte);
      StringBuffer localStringBuffer2 = new StringBuffer();
      int m = arrayOfByte.length;
      while (j < m)
      {
        int n = arrayOfByte[j];
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append(n);
        localStringBuilder1.append(" ");
        localStringBuffer2.append(localStringBuilder1.toString());
        j++;
      }
      return arrayOfByte;
    }
    return null;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.ae
 * JD-Core Version:    0.6.1
 */