package com.tencent.bugly.proguard;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public final class af
  implements ag
{
  private String a = null;

  public final void a(String paramString)
  {
    if (paramString != null)
      this.a = paramString;
  }

  public final byte[] a(byte[] paramArrayOfByte)
  {
    if ((this.a != null) && (paramArrayOfByte != null))
    {
      Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      DESKeySpec localDESKeySpec = new DESKeySpec(this.a.getBytes("UTF-8"));
      localCipher.init(2, SecretKeyFactory.getInstance("DES").generateSecret(localDESKeySpec), new IvParameterSpec(this.a.getBytes("UTF-8")));
      return localCipher.doFinal(paramArrayOfByte);
    }
    return null;
  }

  public final byte[] b(byte[] paramArrayOfByte)
  {
    if ((this.a != null) && (paramArrayOfByte != null))
    {
      Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      DESKeySpec localDESKeySpec = new DESKeySpec(this.a.getBytes("UTF-8"));
      localCipher.init(1, SecretKeyFactory.getInstance("DES").generateSecret(localDESKeySpec), new IvParameterSpec(this.a.getBytes("UTF-8")));
      return localCipher.doFinal(paramArrayOfByte);
    }
    return null;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.af
 * JD-Core Version:    0.6.1
 */