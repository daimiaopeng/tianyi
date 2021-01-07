package org.apache.commons.httpclient.methods.multipart;

import java.io.OutputStream;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringPart extends PartBase
{
  public static final String DEFAULT_CHARSET = "US-ASCII";
  public static final String DEFAULT_CONTENT_TYPE = "text/plain";
  public static final String DEFAULT_TRANSFER_ENCODING = "8bit";
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$methods$multipart$StringPart;
  private byte[] content;
  private String value;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$methods$multipart$StringPart == null)
    {
      localClass = class$("org.apache.commons.httpclient.methods.multipart.StringPart");
      class$org$apache$commons$httpclient$methods$multipart$StringPart = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$methods$multipart$StringPart;
    }
  }

  public StringPart(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, null);
  }

  public StringPart(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1, "text/plain", paramString3, "8bit");
    if (paramString2 == null)
      throw new IllegalArgumentException("Value may not be null");
    if (paramString2.indexOf(0) != -1)
      throw new IllegalArgumentException("NULs may not be present in string parts");
    this.value = paramString2;
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

  private byte[] getContent()
  {
    if (this.content == null)
      this.content = EncodingUtil.getBytes(this.value, getCharSet());
    return this.content;
  }

  protected long lengthOfData()
  {
    LOG.trace("enter lengthOfData()");
    return getContent().length;
  }

  protected void sendData(OutputStream paramOutputStream)
  {
    LOG.trace("enter sendData(OutputStream)");
    paramOutputStream.write(getContent());
  }

  public void setCharSet(String paramString)
  {
    super.setCharSet(paramString);
    this.content = null;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.multipart.StringPart
 * JD-Core Version:    0.6.1
 */