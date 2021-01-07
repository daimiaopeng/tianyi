package org.apache.commons.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpParser
{
  private static final Log LOG = LogFactory.getLog(localClass);
  static Class class$org$apache$commons$httpclient$HttpParser;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$HttpParser == null)
    {
      localClass = class$("org.apache.commons.httpclient.HttpParser");
      class$org$apache$commons$httpclient$HttpParser = localClass;
    }
    else
    {
      localClass = class$org$apache$commons$httpclient$HttpParser;
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

  public static Header[] parseHeaders(InputStream paramInputStream)
  {
    LOG.trace("enter HeaderParser.parseHeaders(InputStream, String)");
    return parseHeaders(paramInputStream, "US-ASCII");
  }

  public static Header[] parseHeaders(InputStream paramInputStream, String paramString)
  {
    LOG.trace("enter HeaderParser.parseHeaders(InputStream, String)");
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = null;
    Object localObject2 = null;
    while (true)
    {
      String str1 = readLine(paramInputStream, paramString);
      if ((str1 == null) || (str1.trim().length() < 1))
        break;
      if ((str1.charAt(0) != ' ') && (str1.charAt(0) != '\t'))
      {
        if (localObject1 != null)
          localArrayList.add(new Header((String)localObject1, localObject2.toString()));
        int i = str1.indexOf(":");
        if (i < 0)
        {
          StringBuffer localStringBuffer1 = new StringBuffer();
          localStringBuffer1.append("Unable to parse header: ");
          localStringBuffer1.append(str1);
          throw new ProtocolException(localStringBuffer1.toString());
        }
        String str2 = str1.substring(0, i).trim();
        StringBuffer localStringBuffer2 = new StringBuffer(str1.substring(i + 1).trim());
        localObject1 = str2;
        localObject2 = localStringBuffer2;
      }
      else if (localObject2 != null)
      {
        localObject2.append(' ');
        localObject2.append(str1.trim());
      }
    }
    if (localObject1 != null)
      localArrayList.add(new Header((String)localObject1, localObject2.toString()));
    return (Header[])localArrayList.toArray(new Header[localArrayList.size()]);
  }

  public static String readLine(InputStream paramInputStream)
  {
    LOG.trace("enter HttpParser.readLine(InputStream)");
    return readLine(paramInputStream, "US-ASCII");
  }

  public static String readLine(InputStream paramInputStream, String paramString)
  {
    LOG.trace("enter HttpParser.readLine(InputStream, String)");
    byte[] arrayOfByte = readRawLine(paramInputStream);
    if (arrayOfByte == null)
      return null;
    int i = arrayOfByte.length;
    int j;
    if ((i > 0) && (arrayOfByte[(i - 1)] == 10))
    {
      if ((i > 1) && (arrayOfByte[(i - 2)] == 13))
        j = 2;
      else
        j = 1;
    }
    else
      j = 0;
    String str1 = EncodingUtil.getString(arrayOfByte, 0, i - j, paramString);
    if (Wire.HEADER_WIRE.enabled())
    {
      String str2;
      if (j == 2)
      {
        StringBuffer localStringBuffer1 = new StringBuffer();
        localStringBuffer1.append(str1);
        localStringBuffer1.append("\r\n");
        str2 = localStringBuffer1.toString();
      }
      else if (j == 1)
      {
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append(str1);
        localStringBuffer2.append("\n");
        str2 = localStringBuffer2.toString();
      }
      else
      {
        str2 = str1;
      }
      Wire.HEADER_WIRE.input(str2);
    }
    return str1;
  }

  public static byte[] readRawLine(InputStream paramInputStream)
  {
    LOG.trace("enter HttpParser.readRawLine()");
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int i;
    do
    {
      i = paramInputStream.read();
      if (i < 0)
        break;
      localByteArrayOutputStream.write(i);
    }
    while (i != 10);
    if (localByteArrayOutputStream.size() == 0)
      return null;
    return localByteArrayOutputStream.toByteArray();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpParser
 * JD-Core Version:    0.6.1
 */