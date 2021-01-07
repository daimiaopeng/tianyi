package org.apache.commons.io;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

public class CopyUtils
{
  private static final int DEFAULT_BUFFER_SIZE = 4096;

  public static int copy(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    byte[] arrayOfByte = new byte[4096];
    int i = 0;
    while (true)
    {
      int j = paramInputStream.read(arrayOfByte);
      if (-1 == j)
        break;
      paramOutputStream.write(arrayOfByte, 0, j);
      i += j;
    }
    return i;
  }

  public static int copy(Reader paramReader, Writer paramWriter)
  {
    char[] arrayOfChar = new char[4096];
    int i = 0;
    while (true)
    {
      int j = paramReader.read(arrayOfChar);
      if (-1 == j)
        break;
      paramWriter.write(arrayOfChar, 0, j);
      i += j;
    }
    return i;
  }

  public static void copy(InputStream paramInputStream, Writer paramWriter)
  {
    copy(new InputStreamReader(paramInputStream), paramWriter);
  }

  public static void copy(InputStream paramInputStream, Writer paramWriter, String paramString)
  {
    copy(new InputStreamReader(paramInputStream, paramString), paramWriter);
  }

  public static void copy(Reader paramReader, OutputStream paramOutputStream)
  {
    OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramOutputStream);
    copy(paramReader, localOutputStreamWriter);
    localOutputStreamWriter.flush();
  }

  public static void copy(String paramString, OutputStream paramOutputStream)
  {
    StringReader localStringReader = new StringReader(paramString);
    OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramOutputStream);
    copy(localStringReader, localOutputStreamWriter);
    localOutputStreamWriter.flush();
  }

  public static void copy(String paramString, Writer paramWriter)
  {
    paramWriter.write(paramString);
  }

  public static void copy(byte[] paramArrayOfByte, OutputStream paramOutputStream)
  {
    paramOutputStream.write(paramArrayOfByte);
  }

  public static void copy(byte[] paramArrayOfByte, Writer paramWriter)
  {
    copy(new ByteArrayInputStream(paramArrayOfByte), paramWriter);
  }

  public static void copy(byte[] paramArrayOfByte, Writer paramWriter, String paramString)
  {
    copy(new ByteArrayInputStream(paramArrayOfByte), paramWriter, paramString);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.CopyUtils
 * JD-Core Version:    0.6.1
 */