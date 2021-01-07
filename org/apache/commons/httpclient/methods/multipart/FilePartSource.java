package org.apache.commons.httpclient.methods.multipart;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FilePartSource
  implements PartSource
{
  private File file = null;
  private String fileName = null;

  public FilePartSource(File paramFile)
  {
    this.file = paramFile;
    if (paramFile != null)
    {
      if (!paramFile.isFile())
        throw new FileNotFoundException("File is not a normal file.");
      if (!paramFile.canRead())
        throw new FileNotFoundException("File is not readable.");
      this.fileName = paramFile.getName();
    }
  }

  public FilePartSource(String paramString, File paramFile)
  {
    this(paramFile);
    if (paramString != null)
      this.fileName = paramString;
  }

  public InputStream createInputStream()
  {
    if (this.file != null)
      return new FileInputStream(this.file);
    return new ByteArrayInputStream(new byte[0]);
  }

  public String getFileName()
  {
    String str;
    if (this.fileName == null)
      str = "noname";
    else
      str = this.fileName;
    return str;
  }

  public long getLength()
  {
    if (this.file != null)
      return this.file.length();
    return 0L;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.multipart.FilePartSource
 * JD-Core Version:    0.6.1
 */