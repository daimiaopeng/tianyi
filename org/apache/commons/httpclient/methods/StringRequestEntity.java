package org.apache.commons.httpclient.methods;

import java.io.OutputStream;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.NameValuePair;

public class StringRequestEntity
  implements RequestEntity
{
  private String charset;
  private byte[] content;
  private String contentType;

  public StringRequestEntity(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("The content cannot be null");
    this.contentType = null;
    this.charset = null;
    this.content = paramString.getBytes();
  }

  public StringRequestEntity(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("The content cannot be null");
    this.contentType = paramString2;
    this.charset = paramString3;
    if (paramString2 != null)
    {
      HeaderElement[] arrayOfHeaderElement = HeaderElement.parseElements(paramString2);
      NameValuePair localNameValuePair = null;
      for (int i = 0; i < arrayOfHeaderElement.length; i++)
      {
        localNameValuePair = arrayOfHeaderElement[i].getParameterByName("charset");
        if (localNameValuePair != null)
          break;
      }
      if ((paramString3 == null) && (localNameValuePair != null))
      {
        this.charset = localNameValuePair.getValue();
      }
      else if ((paramString3 != null) && (localNameValuePair == null))
      {
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append(paramString2);
        localStringBuffer.append("; charset=");
        localStringBuffer.append(paramString3);
        this.contentType = localStringBuffer.toString();
      }
    }
    if (this.charset != null)
      this.content = paramString1.getBytes(this.charset);
    else
      this.content = paramString1.getBytes();
  }

  public String getCharset()
  {
    return this.charset;
  }

  // ERROR //
  public String getContent()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 26	org/apache/commons/httpclient/methods/StringRequestEntity:charset	Ljava/lang/String;
    //   4: ifnull +33 -> 37
    //   7: new 28	java/lang/String
    //   10: dup
    //   11: aload_0
    //   12: getfield 34	org/apache/commons/httpclient/methods/StringRequestEntity:content	[B
    //   15: aload_0
    //   16: getfield 26	org/apache/commons/httpclient/methods/StringRequestEntity:charset	Ljava/lang/String;
    //   19: invokespecial 74	java/lang/String:<init>	([BLjava/lang/String;)V
    //   22: astore_1
    //   23: aload_1
    //   24: areturn
    //   25: new 28	java/lang/String
    //   28: dup
    //   29: aload_0
    //   30: getfield 34	org/apache/commons/httpclient/methods/StringRequestEntity:content	[B
    //   33: invokespecial 77	java/lang/String:<init>	([B)V
    //   36: areturn
    //   37: new 28	java/lang/String
    //   40: dup
    //   41: aload_0
    //   42: getfield 34	org/apache/commons/httpclient/methods/StringRequestEntity:content	[B
    //   45: invokespecial 77	java/lang/String:<init>	([B)V
    //   48: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   7	23	25	java/io/UnsupportedEncodingException
  }

  public long getContentLength()
  {
    return this.content.length;
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
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    paramOutputStream.write(this.content);
    paramOutputStream.flush();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.StringRequestEntity
 * JD-Core Version:    0.6.1
 */