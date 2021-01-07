package org.apache.commons.codec.net;

@Deprecated
abstract class RFC1522Codec
{
  RFC1522Codec()
  {
    throw new RuntimeException("Stub!");
  }

  protected String decodeText(String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  protected abstract byte[] doDecoding(byte[] paramArrayOfByte);

  protected abstract byte[] doEncoding(byte[] paramArrayOfByte);

  protected String encodeText(String paramString1, String paramString2)
  {
    throw new RuntimeException("Stub!");
  }

  protected abstract String getEncoding();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.codec.net.RFC1522Codec
 * JD-Core Version:    0.6.1
 */