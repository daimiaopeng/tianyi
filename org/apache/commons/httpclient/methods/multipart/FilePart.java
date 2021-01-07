package org.apache.commons.httpclient.methods.multipart;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FilePart extends PartBase
{
  public static final String DEFAULT_CHARSET = "ISO-8859-1";
  public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
  public static final String DEFAULT_TRANSFER_ENCODING = "binary";
  protected static final String FILE_NAME = "; filename=";
  private static final byte[] FILE_NAME_BYTES = EncodingUtil.getAsciiBytes("; filename=");
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$methods$multipart$FilePart;
  private PartSource source;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$methods$multipart$FilePart == null)
    {
      localClass = class$("org.apache.commons.httpclient.methods.multipart.FilePart");
      class$org$apache$commons$httpclient$methods$multipart$FilePart = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$methods$multipart$FilePart;
    }
    LOG = LogFactory.getLog(localClass);
  }

  public FilePart(String paramString, File paramFile)
  {
    this(paramString, new FilePartSource(paramFile), null, null);
  }

  public FilePart(String paramString1, File paramFile, String paramString2, String paramString3)
  {
    this(paramString1, new FilePartSource(paramFile), paramString2, paramString3);
  }

  public FilePart(String paramString1, String paramString2, File paramFile)
  {
    this(paramString1, new FilePartSource(paramString2, paramFile), null, null);
  }

  public FilePart(String paramString1, String paramString2, File paramFile, String paramString3, String paramString4)
  {
    this(paramString1, new FilePartSource(paramString2, paramFile), paramString3, paramString4);
  }

  public FilePart(String paramString, PartSource paramPartSource)
  {
    this(paramString, paramPartSource, null, null);
  }

  public FilePart(String paramString1, PartSource paramPartSource, String paramString2, String paramString3)
  {
    super(paramString1, paramString2, paramString3, "binary");
    if (paramPartSource == null)
      throw new IllegalArgumentException("Source may not be null");
    this.source = paramPartSource;
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

  protected PartSource getSource()
  {
    LOG.trace("enter getSource()");
    return this.source;
  }

  protected long lengthOfData()
  {
    LOG.trace("enter lengthOfData()");
    return this.source.getLength();
  }

  protected void sendData(OutputStream paramOutputStream)
  {
    LOG.trace("enter sendData(OutputStream out)");
    if (lengthOfData() == 0L)
    {
      LOG.debug("No data to send.");
      return;
    }
    byte[] arrayOfByte = new byte[4096];
    InputStream localInputStream = this.source.createInputStream();
    try
    {
      while (true)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i < 0)
          break;
        paramOutputStream.write(arrayOfByte, 0, i);
      }
      return;
    }
    finally
    {
      localInputStream.close();
    }
  }

  protected void sendDispositionHeader(OutputStream paramOutputStream)
  {
    LOG.trace("enter sendDispositionHeader(OutputStream out)");
    super.sendDispositionHeader(paramOutputStream);
    String str = this.source.getFileName();
    if (str != null)
    {
      paramOutputStream.write(FILE_NAME_BYTES);
      paramOutputStream.write(QUOTE_BYTES);
      paramOutputStream.write(EncodingUtil.getAsciiBytes(str));
      paramOutputStream.write(QUOTE_BYTES);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.multipart.FilePart
 * JD-Core Version:    0.6.1
 */