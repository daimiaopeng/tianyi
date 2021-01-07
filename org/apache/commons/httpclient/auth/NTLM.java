package org.apache.commons.httpclient.auth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.util.EncodingUtil;

final class NTLM
{
  public static final String DEFAULT_CHARSET = "ASCII";
  private String credentialCharset = "ASCII";
  private int currentPosition = 0;
  private byte[] currentResponse;

  private void addByte(byte paramByte)
  {
    this.currentResponse[this.currentPosition] = paramByte;
    this.currentPosition = (1 + this.currentPosition);
  }

  private void addBytes(byte[] paramArrayOfByte)
  {
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      this.currentResponse[this.currentPosition] = paramArrayOfByte[i];
      this.currentPosition = (1 + this.currentPosition);
    }
  }

  private void calcResp(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    byte[] arrayOfByte1 = new byte[7];
    byte[] arrayOfByte2 = new byte[7];
    byte[] arrayOfByte3 = new byte[7];
    for (int i = 0; i < 7; i++)
      arrayOfByte1[i] = paramArrayOfByte1[i];
    for (int j = 0; j < 7; j++)
      arrayOfByte2[j] = paramArrayOfByte1[(j + 7)];
    for (int k = 0; k < 7; k++)
      arrayOfByte3[k] = paramArrayOfByte1[(k + 14)];
    byte[] arrayOfByte4 = encrypt(arrayOfByte1, paramArrayOfByte2);
    byte[] arrayOfByte5 = encrypt(arrayOfByte2, paramArrayOfByte2);
    byte[] arrayOfByte6 = encrypt(arrayOfByte3, paramArrayOfByte2);
    for (int m = 0; m < 8; m++)
      paramArrayOfByte3[m] = arrayOfByte4[m];
    int i1;
    for (int n = 0; ; n++)
    {
      i1 = 0;
      if (n >= 8)
        break;
      paramArrayOfByte3[(n + 8)] = arrayOfByte5[n];
    }
    while (i1 < 8)
    {
      paramArrayOfByte3[(i1 + 16)] = arrayOfByte6[i1];
      i1++;
    }
  }

  private byte[] convertShort(int paramInt)
  {
    byte[] arrayOfByte = new byte[2];
    StringBuffer localStringBuffer;
    for (String str1 = Integer.toString(paramInt, 16); str1.length() < 4; str1 = localStringBuffer.toString())
    {
      localStringBuffer = new StringBuffer();
      localStringBuffer.append("0");
      localStringBuffer.append(str1);
    }
    String str2 = str1.substring(2, 4);
    String str3 = str1.substring(0, 2);
    arrayOfByte[0] = (byte)Integer.parseInt(str2, 16);
    arrayOfByte[1] = (byte)Integer.parseInt(str3, 16);
    return arrayOfByte;
  }

  private byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    Cipher localCipher = getCipher(paramArrayOfByte1);
    try
    {
      byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte2);
      return arrayOfByte;
    }
    catch (BadPaddingException localBadPaddingException)
    {
      throw new AuthenticationException("Data not padded correctly for DES encryption.", localBadPaddingException);
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      throw new AuthenticationException("Invalid block size for DES encryption.", localIllegalBlockSizeException);
    }
  }

  private Cipher getCipher(byte[] paramArrayOfByte)
  {
    try
    {
      Cipher localCipher = Cipher.getInstance("DES/ECB/NoPadding");
      localCipher.init(1, new SecretKeySpec(setupKey(paramArrayOfByte), "DES"));
      return localCipher;
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      throw new AuthenticationException("NoPadding option for DES is not available.", localNoSuchPaddingException);
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      throw new AuthenticationException("Invalid key for DES encryption.", localInvalidKeyException);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new AuthenticationException("DES encryption is not available.", localNoSuchAlgorithmException);
    }
  }

  private String getResponse()
  {
    if (this.currentResponse.length > this.currentPosition)
    {
      arrayOfByte = new byte[this.currentPosition];
      for (int i = 0; i < this.currentPosition; i++)
        arrayOfByte[i] = this.currentResponse[i];
    }
    byte[] arrayOfByte = this.currentResponse;
    return EncodingUtil.getAsciiString(Base64.encodeBase64(arrayOfByte));
  }

  private byte[] hashPassword(String paramString, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte1 = EncodingUtil.getBytes(paramString.toUpperCase(), this.credentialCharset);
    int i = 7;
    byte[] arrayOfByte2 = new byte[i];
    byte[] arrayOfByte3 = new byte[i];
    int j = arrayOfByte1.length;
    if (j > i)
      j = 7;
    for (int k = 0; k < j; k++)
      arrayOfByte2[k] = arrayOfByte1[k];
    while (k < i)
    {
      arrayOfByte2[k] = 0;
      k++;
    }
    int m = arrayOfByte1.length;
    if (m > 14)
      m = 14;
    while (i < m)
    {
      arrayOfByte3[(i - 7)] = arrayOfByte1[i];
      i++;
    }
    while (i < 14)
    {
      arrayOfByte3[(i - 7)] = 0;
      i++;
    }
    byte[] arrayOfByte4 = { 75, 71, 83, 33, 64, 35, 36, 37 };
    byte[] arrayOfByte5 = encrypt(arrayOfByte2, arrayOfByte4);
    byte[] arrayOfByte6 = encrypt(arrayOfByte3, arrayOfByte4);
    byte[] arrayOfByte7 = new byte[21];
    for (int n = 0; n < arrayOfByte5.length; n++)
      arrayOfByte7[n] = arrayOfByte5[n];
    for (int i1 = 0; i1 < arrayOfByte6.length; i1++)
      arrayOfByte7[(i1 + 8)] = arrayOfByte6[i1];
    for (int i2 = 0; i2 < 5; i2++)
      arrayOfByte7[(i2 + 16)] = 0;
    byte[] arrayOfByte8 = new byte[24];
    calcResp(arrayOfByte7, paramArrayOfByte, arrayOfByte8);
    return arrayOfByte8;
  }

  private void prepareResponse(int paramInt)
  {
    this.currentResponse = new byte[paramInt];
    this.currentPosition = 0;
  }

  private byte[] setupKey(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[8];
    int i = 0;
    arrayOfByte[0] = (byte)(0xFF & paramArrayOfByte[0] >> 1);
    arrayOfByte[1] = (byte)(0xFF & ((0x1 & paramArrayOfByte[0]) << 6 | 0xFF & (0xFF & paramArrayOfByte[1]) >> 2));
    arrayOfByte[2] = (byte)(0xFF & ((0x3 & paramArrayOfByte[1]) << 5 | 0xFF & (0xFF & paramArrayOfByte[2]) >> 3));
    arrayOfByte[3] = (byte)(0xFF & ((0x7 & paramArrayOfByte[2]) << 4 | 0xFF & (0xFF & paramArrayOfByte[3]) >> 4));
    arrayOfByte[4] = (byte)(0xFF & ((0xF & paramArrayOfByte[3]) << 3 | 0xFF & (0xFF & paramArrayOfByte[4]) >> 5));
    arrayOfByte[5] = (byte)(0xFF & ((0x1F & paramArrayOfByte[4]) << 2 | 0xFF & (0xFF & paramArrayOfByte[5]) >> 6));
    arrayOfByte[6] = (byte)(0xFF & ((0x3F & paramArrayOfByte[5]) << 1 | 0xFF & (0xFF & paramArrayOfByte[6]) >> 7));
    arrayOfByte[7] = (byte)(0x7F & paramArrayOfByte[6]);
    while (i < arrayOfByte.length)
    {
      arrayOfByte[i] = (byte)(arrayOfByte[i] << 1);
      i++;
    }
    return arrayOfByte;
  }

  public String getCredentialCharset()
  {
    return this.credentialCharset;
  }

  public final String getResponseFor(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    String str;
    if ((paramString1 != null) && (!paramString1.trim().equals("")))
      str = getType3Message(paramString2, paramString3, paramString4, paramString5, parseType2Message(paramString1));
    else
      str = getType1Message(paramString4, paramString5);
    return str;
  }

  public String getType1Message(String paramString1, String paramString2)
  {
    String str1 = paramString1.toUpperCase();
    String str2 = paramString2.toUpperCase();
    byte[] arrayOfByte1 = EncodingUtil.getBytes(str1, "ASCII");
    byte[] arrayOfByte2 = EncodingUtil.getBytes(str2, "ASCII");
    prepareResponse(32 + arrayOfByte1.length + arrayOfByte2.length);
    addBytes(EncodingUtil.getBytes("NTLMSSP", "ASCII"));
    addByte((byte)0);
    addByte((byte)1);
    addByte((byte)0);
    addByte((byte)0);
    addByte((byte)0);
    addByte((byte)6);
    addByte((byte)82);
    addByte((byte)0);
    addByte((byte)0);
    byte[] arrayOfByte3 = convertShort(arrayOfByte2.length);
    addByte(arrayOfByte3[0]);
    addByte(arrayOfByte3[1]);
    addByte(arrayOfByte3[0]);
    addByte(arrayOfByte3[1]);
    byte[] arrayOfByte4 = convertShort(32 + arrayOfByte1.length);
    addByte(arrayOfByte4[0]);
    addByte(arrayOfByte4[1]);
    addByte((byte)0);
    addByte((byte)0);
    byte[] arrayOfByte5 = convertShort(arrayOfByte1.length);
    addByte(arrayOfByte5[0]);
    addByte(arrayOfByte5[1]);
    addByte(arrayOfByte5[0]);
    addByte(arrayOfByte5[1]);
    byte[] arrayOfByte6 = convertShort(32);
    addByte(arrayOfByte6[0]);
    addByte(arrayOfByte6[1]);
    addByte((byte)0);
    addByte((byte)0);
    addBytes(arrayOfByte1);
    addBytes(arrayOfByte2);
    return getResponse();
  }

  public String getType3Message(String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte)
  {
    String str1 = paramString4.toUpperCase();
    String str2 = paramString3.toUpperCase();
    String str3 = paramString1.toUpperCase();
    byte[] arrayOfByte1 = EncodingUtil.getBytes(str1, "ASCII");
    byte[] arrayOfByte2 = EncodingUtil.getBytes(str2, "ASCII");
    byte[] arrayOfByte3 = EncodingUtil.getBytes(str3, this.credentialCharset);
    int i = arrayOfByte1.length;
    int j = arrayOfByte2.length;
    int k = arrayOfByte3.length;
    int m = j + (k + (88 + i));
    prepareResponse(m);
    addBytes(EncodingUtil.getBytes("NTLMSSP", "ASCII"));
    addByte((byte)0);
    addByte((byte)3);
    addByte((byte)0);
    addByte((byte)0);
    addByte((byte)0);
    addBytes(convertShort(24));
    addBytes(convertShort(24));
    addBytes(convertShort(m - 24));
    addByte((byte)0);
    addByte((byte)0);
    addBytes(convertShort(0));
    addBytes(convertShort(0));
    addBytes(convertShort(m));
    addByte((byte)0);
    addByte((byte)0);
    addBytes(convertShort(i));
    addBytes(convertShort(i));
    addBytes(convertShort(64));
    addByte((byte)0);
    addByte((byte)0);
    addBytes(convertShort(k));
    addBytes(convertShort(k));
    int n = i + 64;
    addBytes(convertShort(n));
    addByte((byte)0);
    addByte((byte)0);
    addBytes(convertShort(j));
    addBytes(convertShort(j));
    addBytes(convertShort(n + k));
    for (int i1 = 0; i1 < 6; i1++)
      addByte((byte)0);
    addBytes(convertShort(m));
    addByte((byte)0);
    addByte((byte)0);
    addByte((byte)6);
    addByte((byte)82);
    addByte((byte)0);
    addByte((byte)0);
    addBytes(arrayOfByte1);
    addBytes(arrayOfByte3);
    addBytes(arrayOfByte2);
    addBytes(hashPassword(paramString2, paramArrayOfByte));
    return getResponse();
  }

  public byte[] parseType2Message(String paramString)
  {
    byte[] arrayOfByte1 = Base64.decodeBase64(EncodingUtil.getBytes(paramString, "ASCII"));
    byte[] arrayOfByte2 = new byte[8];
    for (int i = 0; i < 8; i++)
      arrayOfByte2[i] = arrayOfByte1[(i + 24)];
    return arrayOfByte2;
  }

  public void setCredentialCharset(String paramString)
  {
    this.credentialCharset = paramString;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.NTLM
 * JD-Core Version:    0.6.1
 */