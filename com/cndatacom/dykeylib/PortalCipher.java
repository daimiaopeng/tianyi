package com.cndatacom.dykeylib;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

public class PortalCipher
{
  private boolean isInitialed = false;
  private Context mContext = null;
  private String mUser = null;
  private String malgoId = null;
  private String mkey = null;
  private String mkeyId = null;

  public void copyAssetFile(InputStream paramInputStream, String paramString)
  {
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
      byte[] arrayOfByte = new byte[8192];
      while (true)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1)
          break;
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
      localFileOutputStream.close();
      paramInputStream.close();
    }
    catch (Exception localException)
    {
    }
  }

  public void copyFile(String paramString1, String paramString2)
  {
    try
    {
      if (new File(paramString1).exists())
      {
        FileInputStream localFileInputStream = new FileInputStream(paramString1);
        FileOutputStream localFileOutputStream = new FileOutputStream(paramString2);
        byte[] arrayOfByte = new byte[8192];
        while (true)
        {
          int i = localFileInputStream.read(arrayOfByte);
          if (i == -1)
            break;
          localFileOutputStream.write(arrayOfByte, 0, i);
        }
        localFileOutputStream.close();
        localFileInputStream.close();
      }
    }
    catch (Exception localException)
    {
    }
  }

  public String getAlgoId()
  {
    return this.malgoId;
  }

  public String getKey()
  {
    return this.mkey;
  }

  public String getKeyId()
  {
    return this.mkeyId;
  }

  public String getUser()
  {
    return this.mUser;
  }

  public void getZsmInfo()
  {
    if (this.mkey == null)
      this.mkey = new String("testKey");
    String str = this.mContext.getFilesDir().getPath();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append("/");
    if (DykeyJNILib.dyKeySetDir(localStringBuilder.toString()) != 0)
      return;
    if (this.mUser == null)
      return;
    DyKeyInfo localDyKeyInfo = DykeyJNILib.dyKeyInfo(this.mUser);
    if (localDyKeyInfo.ret == 0)
    {
      this.mkeyId = localDyKeyInfo.keyID;
      this.malgoId = localDyKeyInfo.algoID;
    }
  }

  // ERROR //
  public boolean isFileExists(String paramString)
  {
    // Byte code:
    //   0: new 56	java/io/File
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 57	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: invokevirtual 61	java/io/File:exists	()Z
    //   11: istore_2
    //   12: iload_2
    //   13: ireturn
    //   14: iconst_0
    //   15: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	12	14	java/lang/Exception
  }

  public boolean isInitial()
  {
    return this.isInitialed;
  }

  public void setAlgoId(String paramString)
  {
    this.malgoId = paramString;
  }

  public void setContext(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public void setKey(String paramString)
  {
    this.mkey = paramString;
  }

  public void setKeyId(String paramString)
  {
    this.mkeyId = paramString;
  }

  public void setUser(String paramString)
  {
    CRC32 localCRC32 = new CRC32();
    localCRC32.update(paramString.getBytes());
    this.mUser = Long.toString(localCRC32.getValue());
  }

  public byte[] zsmDecrypt(byte[] paramArrayOfByte)
  {
    if (this.mUser == null)
      return null;
    String str = this.mContext.getFilesDir().getPath();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append("/");
    if (DykeyJNILib.dyKeySetDir(localStringBuilder.toString()) != 0)
      return null;
    DyKeyDecodeInfo localDyKeyDecodeInfo = DykeyJNILib.dyKeyDecode(this.mUser, new String(paramArrayOfByte));
    if (localDyKeyDecodeInfo.ret == 0)
      return localDyKeyDecodeInfo.strOut.getBytes();
    return null;
  }

  public byte[] zsmEncrypt(byte[] paramArrayOfByte)
  {
    if (this.mUser == null)
      return null;
    String str = this.mContext.getFilesDir().getPath();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append("/");
    if (DykeyJNILib.dyKeySetDir(localStringBuilder.toString()) != 0)
      return null;
    DyKeyCodeInfo localDyKeyCodeInfo = DykeyJNILib.dyKeyCode(this.mUser, new String(paramArrayOfByte));
    if (localDyKeyCodeInfo.ret == 0)
      return localDyKeyCodeInfo.strOut.getBytes();
    return null;
  }

  public int zsmInitial()
  {
    if (!this.isInitialed)
    {
      String str1 = this.mContext.getFilesDir().getPath();
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(str1);
      localStringBuilder1.append("/initial.zsm");
      String str2 = localStringBuilder1.toString();
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(str1);
      localStringBuilder2.append("/zsm/");
      File localFile = new File(localStringBuilder2.toString());
      if (!localFile.exists())
        localFile.mkdir();
      AssetManager localAssetManager;
      if (!isFileExists(str2))
        localAssetManager = this.mContext.getAssets();
      try
      {
        copyAssetFile(localAssetManager.open("libzsm.so"), str2);
      }
      catch (IOException localIOException)
      {
      }
      this.isInitialed = true;
    }
    return 0;
  }

  public int zsmUpdate(byte[] paramArrayOfByte)
  {
    if (this.mUser == null)
      return -1;
    if (paramArrayOfByte == null)
      return -2;
    String str1 = this.mContext.getFilesDir().getPath();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str1);
    localStringBuilder.append("/zsm/");
    localStringBuilder.append(this.mUser);
    localStringBuilder.append(".zsm");
    String str2 = localStringBuilder.toString();
    copyAssetFile(new ByteArrayInputStream(paramArrayOfByte), str2);
    return 0;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.dykeylib.PortalCipher
 * JD-Core Version:    0.6.1
 */