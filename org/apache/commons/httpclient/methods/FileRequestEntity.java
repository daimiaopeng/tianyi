package org.apache.commons.httpclient.methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileRequestEntity
  implements RequestEntity
{
  final String contentType;
  final File file;

  public FileRequestEntity(File paramFile, String paramString)
  {
    if (paramFile == null)
      throw new IllegalArgumentException("File may not be null");
    this.file = paramFile;
    this.contentType = paramString;
  }

  public long getContentLength()
  {
    return this.file.length();
  }

  public String getContentType()
  {
    return this.contentType;
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public void writeRequest(OutputStream paramOutputStream)
  {
    byte[] arrayOfByte = new byte[4096];
    FileInputStream localFileInputStream = new FileInputStream(this.file);
    try
    {
      while (true)
      {
        int i = localFileInputStream.read(arrayOfByte);
        if (i < 0)
          break;
        paramOutputStream.write(arrayOfByte, 0, i);
      }
      return;
    }
    finally
    {
      localFileInputStream.close();
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.FileRequestEntity
 * JD-Core Version:    0.6.1
 */