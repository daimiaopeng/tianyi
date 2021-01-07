package org.apache.commons.httpclient.methods;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InputStreamRequestEntity
  implements RequestEntity
{
  public static final int CONTENT_LENGTH_AUTO = -2;
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$methods$InputStreamRequestEntity;
  private byte[] buffer = null;
  private InputStream content;
  private long contentLength;
  private String contentType;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$methods$InputStreamRequestEntity == null)
    {
      localClass = class$("org.apache.commons.httpclient.methods.InputStreamRequestEntity");
      class$org$apache$commons$httpclient$methods$InputStreamRequestEntity = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$methods$InputStreamRequestEntity;
    }
  }

  public InputStreamRequestEntity(InputStream paramInputStream)
  {
    this(paramInputStream, null);
  }

  public InputStreamRequestEntity(InputStream paramInputStream, long paramLong)
  {
    this(paramInputStream, paramLong, null);
  }

  public InputStreamRequestEntity(InputStream paramInputStream, long paramLong, String paramString)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("The content cannot be null");
    this.content = paramInputStream;
    this.contentLength = paramLong;
    this.contentType = paramString;
  }

  public InputStreamRequestEntity(InputStream paramInputStream, String paramString)
  {
    this(paramInputStream, -2L, paramString);
  }

  private void bufferContent()
  {
    if (this.buffer != null)
      return;
    if (this.content != null)
      try
      {
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        byte[] arrayOfByte = new byte[4096];
        while (true)
        {
          int i = this.content.read(arrayOfByte);
          if (i < 0)
            break;
          localByteArrayOutputStream.write(arrayOfByte, 0, i);
        }
        this.buffer = localByteArrayOutputStream.toByteArray();
        this.content = null;
        this.contentLength = this.buffer.length;
      }
      catch (IOException localIOException)
      {
        LOG.error(localIOException.getMessage(), localIOException);
        this.buffer = null;
        this.content = null;
        this.contentLength = 0L;
      }
  }

  static Class class$(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
  }

  public InputStream getContent()
  {
    return this.content;
  }

  public long getContentLength()
  {
    if ((this.contentLength == -2L) && (this.buffer == null))
      bufferContent();
    return this.contentLength;
  }

  public String getContentType()
  {
    return this.contentType;
  }

  public boolean isRepeatable()
  {
    boolean bool;
    if (this.buffer != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public void writeRequest(OutputStream paramOutputStream)
  {
    if (this.content != null)
    {
      byte[] arrayOfByte = new byte[4096];
      while (true)
      {
        int i = this.content.read(arrayOfByte);
        if (i < 0)
          break;
        paramOutputStream.write(arrayOfByte, 0, i);
      }
    }
    if (this.buffer != null)
    {
      paramOutputStream.write(this.buffer);
      return;
    }
    throw new IllegalStateException("Content must be set before entity is written");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.InputStreamRequestEntity
 * JD-Core Version:    0.6.1
 */