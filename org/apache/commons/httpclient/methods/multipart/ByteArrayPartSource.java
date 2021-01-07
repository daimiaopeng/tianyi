package org.apache.commons.httpclient.methods.multipart;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayPartSource
  implements PartSource
{
  private byte[] bytes;
  private String fileName;

  public ByteArrayPartSource(String paramString, byte[] paramArrayOfByte)
  {
    this.fileName = paramString;
    this.bytes = paramArrayOfByte;
  }

  public InputStream createInputStream()
  {
    return new ByteArrayInputStream(this.bytes);
  }

  public String getFileName()
  {
    return this.fileName;
  }

  public long getLength()
  {
    return this.bytes.length;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource
 * JD-Core Version:    0.6.1
 */