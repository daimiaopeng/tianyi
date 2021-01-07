package org.apache.commons.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.output.ByteArrayOutputStream;

public class IOUtils
{
  private static final int DEFAULT_BUFFER_SIZE = 4096;
  public static final char DIR_SEPARATOR = '\000';
  public static final char DIR_SEPARATOR_UNIX = '/';
  public static final char DIR_SEPARATOR_WINDOWS = '\\';
  public static final String LINE_SEPARATOR = localStringWriter.toString();
  public static final String LINE_SEPARATOR_UNIX = "\n";
  public static final String LINE_SEPARATOR_WINDOWS = "\r\n";

  static
  {
    StringWriter localStringWriter = new StringWriter(4);
    new PrintWriter(localStringWriter).println();
  }

  public static void closeQuietly(InputStream paramInputStream)
  {
    if (paramInputStream != null);
    try
    {
      paramInputStream.close();
    }
    catch (IOException localIOException)
    {
    }
  }

  public static void closeQuietly(OutputStream paramOutputStream)
  {
    if (paramOutputStream != null);
    try
    {
      paramOutputStream.close();
    }
    catch (IOException localIOException)
    {
    }
  }

  public static void closeQuietly(Reader paramReader)
  {
    if (paramReader != null);
    try
    {
      paramReader.close();
    }
    catch (IOException localIOException)
    {
    }
  }

  public static void closeQuietly(Writer paramWriter)
  {
    if (paramWriter != null);
    try
    {
      paramWriter.close();
    }
    catch (IOException localIOException)
    {
    }
  }

  public static boolean contentEquals(InputStream paramInputStream1, InputStream paramInputStream2)
  {
    if (!(paramInputStream1 instanceof BufferedInputStream))
      paramInputStream1 = new BufferedInputStream(paramInputStream1);
    if (!(paramInputStream2 instanceof BufferedInputStream))
      paramInputStream2 = new BufferedInputStream(paramInputStream2);
    for (int i = paramInputStream1.read(); -1 != i; i = paramInputStream1.read())
      if (i != paramInputStream2.read())
        return false;
    int j = paramInputStream2.read();
    boolean bool = false;
    if (j == -1)
      bool = true;
    return bool;
  }

  public static boolean contentEquals(Reader paramReader1, Reader paramReader2)
  {
    if (!(paramReader1 instanceof BufferedReader))
      paramReader1 = new BufferedReader(paramReader1);
    if (!(paramReader2 instanceof BufferedReader))
      paramReader2 = new BufferedReader(paramReader2);
    for (int i = paramReader1.read(); -1 != i; i = paramReader1.read())
      if (i != paramReader2.read())
        return false;
    int j = paramReader2.read();
    boolean bool = false;
    if (j == -1)
      bool = true;
    return bool;
  }

  public static int copy(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    long l = copyLarge(paramInputStream, paramOutputStream);
    if (l > 2147483647L)
      return -1;
    return (int)l;
  }

  public static int copy(Reader paramReader, Writer paramWriter)
  {
    long l = copyLarge(paramReader, paramWriter);
    if (l > 2147483647L)
      return -1;
    return (int)l;
  }

  public static void copy(InputStream paramInputStream, Writer paramWriter)
  {
    copy(new InputStreamReader(paramInputStream), paramWriter);
  }

  public static void copy(InputStream paramInputStream, Writer paramWriter, String paramString)
  {
    if (paramString == null)
      copy(paramInputStream, paramWriter);
    else
      copy(new InputStreamReader(paramInputStream, paramString), paramWriter);
  }

  public static void copy(Reader paramReader, OutputStream paramOutputStream)
  {
    OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramOutputStream);
    copy(paramReader, localOutputStreamWriter);
    localOutputStreamWriter.flush();
  }

  public static void copy(Reader paramReader, OutputStream paramOutputStream, String paramString)
  {
    if (paramString == null)
    {
      copy(paramReader, paramOutputStream);
    }
    else
    {
      OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramOutputStream, paramString);
      copy(paramReader, localOutputStreamWriter);
      localOutputStreamWriter.flush();
    }
  }

  public static long copyLarge(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    byte[] arrayOfByte = new byte[4096];
    int i;
    for (long l = 0L; ; l += i)
    {
      i = paramInputStream.read(arrayOfByte);
      if (-1 == i)
        break;
      paramOutputStream.write(arrayOfByte, 0, i);
    }
    return l;
  }

  public static long copyLarge(Reader paramReader, Writer paramWriter)
  {
    char[] arrayOfChar = new char[4096];
    int i;
    for (long l = 0L; ; l += i)
    {
      i = paramReader.read(arrayOfChar);
      if (-1 == i)
        break;
      paramWriter.write(arrayOfChar, 0, i);
    }
    return l;
  }

  public static LineIterator lineIterator(InputStream paramInputStream, String paramString)
  {
    InputStreamReader localInputStreamReader;
    if (paramString == null)
      localInputStreamReader = new InputStreamReader(paramInputStream);
    else
      localInputStreamReader = new InputStreamReader(paramInputStream, paramString);
    return new LineIterator(localInputStreamReader);
  }

  public static LineIterator lineIterator(Reader paramReader)
  {
    return new LineIterator(paramReader);
  }

  public static List readLines(InputStream paramInputStream)
  {
    return readLines(new InputStreamReader(paramInputStream));
  }

  public static List readLines(InputStream paramInputStream, String paramString)
  {
    if (paramString == null)
      return readLines(paramInputStream);
    return readLines(new InputStreamReader(paramInputStream, paramString));
  }

  public static List readLines(Reader paramReader)
  {
    BufferedReader localBufferedReader = new BufferedReader(paramReader);
    ArrayList localArrayList = new ArrayList();
    for (String str = localBufferedReader.readLine(); str != null; str = localBufferedReader.readLine())
      localArrayList.add(str);
    return localArrayList;
  }

  public static byte[] toByteArray(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    copy(paramInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static byte[] toByteArray(Reader paramReader)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    copy(paramReader, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static byte[] toByteArray(Reader paramReader, String paramString)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    copy(paramReader, localByteArrayOutputStream, paramString);
    return localByteArrayOutputStream.toByteArray();
  }

  public static byte[] toByteArray(String paramString)
  {
    return paramString.getBytes();
  }

  public static char[] toCharArray(InputStream paramInputStream)
  {
    CharArrayWriter localCharArrayWriter = new CharArrayWriter();
    copy(paramInputStream, localCharArrayWriter);
    return localCharArrayWriter.toCharArray();
  }

  public static char[] toCharArray(InputStream paramInputStream, String paramString)
  {
    CharArrayWriter localCharArrayWriter = new CharArrayWriter();
    copy(paramInputStream, localCharArrayWriter, paramString);
    return localCharArrayWriter.toCharArray();
  }

  public static char[] toCharArray(Reader paramReader)
  {
    CharArrayWriter localCharArrayWriter = new CharArrayWriter();
    copy(paramReader, localCharArrayWriter);
    return localCharArrayWriter.toCharArray();
  }

  public static InputStream toInputStream(String paramString)
  {
    return new ByteArrayInputStream(paramString.getBytes());
  }

  public static InputStream toInputStream(String paramString1, String paramString2)
  {
    byte[] arrayOfByte;
    if (paramString2 != null)
      arrayOfByte = paramString1.getBytes(paramString2);
    else
      arrayOfByte = paramString1.getBytes();
    return new ByteArrayInputStream(arrayOfByte);
  }

  public static String toString(InputStream paramInputStream)
  {
    StringWriter localStringWriter = new StringWriter();
    copy(paramInputStream, localStringWriter);
    return localStringWriter.toString();
  }

  public static String toString(InputStream paramInputStream, String paramString)
  {
    StringWriter localStringWriter = new StringWriter();
    copy(paramInputStream, localStringWriter, paramString);
    return localStringWriter.toString();
  }

  public static String toString(Reader paramReader)
  {
    StringWriter localStringWriter = new StringWriter();
    copy(paramReader, localStringWriter);
    return localStringWriter.toString();
  }

  public static String toString(byte[] paramArrayOfByte)
  {
    return new String(paramArrayOfByte);
  }

  public static String toString(byte[] paramArrayOfByte, String paramString)
  {
    if (paramString == null)
      return new String(paramArrayOfByte);
    return new String(paramArrayOfByte, paramString);
  }

  public static void write(String paramString, OutputStream paramOutputStream)
  {
    if (paramString != null)
      paramOutputStream.write(paramString.getBytes());
  }

  public static void write(String paramString1, OutputStream paramOutputStream, String paramString2)
  {
    if (paramString1 != null)
      if (paramString2 == null)
        write(paramString1, paramOutputStream);
      else
        paramOutputStream.write(paramString1.getBytes(paramString2));
  }

  public static void write(String paramString, Writer paramWriter)
  {
    if (paramString != null)
      paramWriter.write(paramString);
  }

  public static void write(StringBuffer paramStringBuffer, OutputStream paramOutputStream)
  {
    if (paramStringBuffer != null)
      paramOutputStream.write(paramStringBuffer.toString().getBytes());
  }

  public static void write(StringBuffer paramStringBuffer, OutputStream paramOutputStream, String paramString)
  {
    if (paramStringBuffer != null)
      if (paramString == null)
        write(paramStringBuffer, paramOutputStream);
      else
        paramOutputStream.write(paramStringBuffer.toString().getBytes(paramString));
  }

  public static void write(StringBuffer paramStringBuffer, Writer paramWriter)
  {
    if (paramStringBuffer != null)
      paramWriter.write(paramStringBuffer.toString());
  }

  public static void write(byte[] paramArrayOfByte, OutputStream paramOutputStream)
  {
    if (paramArrayOfByte != null)
      paramOutputStream.write(paramArrayOfByte);
  }

  public static void write(byte[] paramArrayOfByte, Writer paramWriter)
  {
    if (paramArrayOfByte != null)
      paramWriter.write(new String(paramArrayOfByte));
  }

  public static void write(byte[] paramArrayOfByte, Writer paramWriter, String paramString)
  {
    if (paramArrayOfByte != null)
      if (paramString == null)
        write(paramArrayOfByte, paramWriter);
      else
        paramWriter.write(new String(paramArrayOfByte, paramString));
  }

  public static void write(char[] paramArrayOfChar, OutputStream paramOutputStream)
  {
    if (paramArrayOfChar != null)
      paramOutputStream.write(new String(paramArrayOfChar).getBytes());
  }

  public static void write(char[] paramArrayOfChar, OutputStream paramOutputStream, String paramString)
  {
    if (paramArrayOfChar != null)
      if (paramString == null)
        write(paramArrayOfChar, paramOutputStream);
      else
        paramOutputStream.write(new String(paramArrayOfChar).getBytes(paramString));
  }

  public static void write(char[] paramArrayOfChar, Writer paramWriter)
  {
    if (paramArrayOfChar != null)
      paramWriter.write(paramArrayOfChar);
  }

  public static void writeLines(Collection paramCollection, String paramString, OutputStream paramOutputStream)
  {
    if (paramCollection == null)
      return;
    if (paramString == null)
      paramString = LINE_SEPARATOR;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (localObject != null)
        paramOutputStream.write(localObject.toString().getBytes());
      paramOutputStream.write(paramString.getBytes());
    }
  }

  public static void writeLines(Collection paramCollection, String paramString1, OutputStream paramOutputStream, String paramString2)
  {
    if (paramString2 == null)
    {
      writeLines(paramCollection, paramString1, paramOutputStream);
    }
    else
    {
      if (paramCollection == null)
        return;
      if (paramString1 == null)
        paramString1 = LINE_SEPARATOR;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        if (localObject != null)
          paramOutputStream.write(localObject.toString().getBytes(paramString2));
        paramOutputStream.write(paramString1.getBytes(paramString2));
      }
    }
  }

  public static void writeLines(Collection paramCollection, String paramString, Writer paramWriter)
  {
    if (paramCollection == null)
      return;
    if (paramString == null)
      paramString = LINE_SEPARATOR;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (localObject != null)
        paramWriter.write(localObject.toString());
      paramWriter.write(paramString);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.IOUtils
 * JD-Core Version:    0.6.1
 */