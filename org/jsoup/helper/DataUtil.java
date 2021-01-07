package org.jsoup.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class DataUtil
{
  private static final int bufferSize = 131072;
  private static final Pattern charsetPattern = Pattern.compile("(?i)\\bcharset=\\s*(?:\"|')?([^\\s,;\"']*)");
  static final String defaultCharset = "UTF-8";

  // ERROR //
  static String getCharsetFromContentType(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: getstatic 25	org/jsoup/helper/DataUtil:charsetPattern	Ljava/util/regex/Pattern;
    //   9: aload_0
    //   10: invokevirtual 36	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   13: astore_1
    //   14: aload_1
    //   15: invokevirtual 42	java/util/regex/Matcher:find	()Z
    //   18: ifeq +60 -> 78
    //   21: aload_1
    //   22: iconst_1
    //   23: invokevirtual 46	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   26: invokevirtual 52	java/lang/String:trim	()Ljava/lang/String;
    //   29: ldc 54
    //   31: ldc 56
    //   33: invokevirtual 60	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   36: astore_2
    //   37: aload_2
    //   38: invokevirtual 64	java/lang/String:length	()I
    //   41: ifne +5 -> 46
    //   44: aconst_null
    //   45: areturn
    //   46: aload_2
    //   47: invokestatic 70	java/nio/charset/Charset:isSupported	(Ljava/lang/String;)Z
    //   50: ifeq +5 -> 55
    //   53: aload_2
    //   54: areturn
    //   55: aload_2
    //   56: getstatic 76	java/util/Locale:ENGLISH	Ljava/util/Locale;
    //   59: invokevirtual 80	java/lang/String:toUpperCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   62: astore_3
    //   63: aload_3
    //   64: invokestatic 70	java/nio/charset/Charset:isSupported	(Ljava/lang/String;)Z
    //   67: istore 4
    //   69: iload 4
    //   71: ifeq +7 -> 78
    //   74: aload_3
    //   75: areturn
    //   76: aconst_null
    //   77: areturn
    //   78: aconst_null
    //   79: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   46	69	76	java/nio/charset/IllegalCharsetNameException
  }

  public static Document load(File paramFile, String paramString1, String paramString2)
  {
    return parseByteData(readFileToByteBuffer(paramFile), paramString1, paramString2, Parser.htmlParser());
  }

  public static Document load(InputStream paramInputStream, String paramString1, String paramString2)
  {
    return parseByteData(readToByteBuffer(paramInputStream), paramString1, paramString2, Parser.htmlParser());
  }

  public static Document load(InputStream paramInputStream, String paramString1, String paramString2, Parser paramParser)
  {
    return parseByteData(readToByteBuffer(paramInputStream), paramString1, paramString2, paramParser);
  }

  static Document parseByteData(ByteBuffer paramByteBuffer, String paramString1, String paramString2, Parser paramParser)
  {
    String str1;
    if (paramString1 == null)
    {
      str1 = Charset.forName("UTF-8").decode(paramByteBuffer).toString();
      localDocument1 = paramParser.parseInput(str1, paramString2);
      Element localElement = localDocument1.select("meta[http-equiv=content-type], meta[charset]").first();
      if (localElement == null)
        break label207;
      String str2;
      if (localElement.hasAttr("http-equiv"))
      {
        String str3 = getCharsetFromContentType(localElement.attr("content"));
        if ((str3 == null) && (localElement.hasAttr("charset")))
          try
          {
            if (Charset.isSupported(localElement.attr("charset")))
              str2 = localElement.attr("charset");
          }
          catch (IllegalCharsetNameException localIllegalCharsetNameException)
          {
            str2 = null;
            tmpTernaryOp = localIllegalCharsetNameException;
          }
        str2 = str3;
      }
      else
      {
        str2 = localElement.attr("charset");
      }
      if ((str2 == null) || (str2.length() == 0) || (str2.equals("UTF-8")))
        break label207;
      paramString1 = str2.trim().replaceAll("[\"']", "");
      paramByteBuffer.rewind();
      str1 = Charset.forName(paramString1).decode(paramByteBuffer).toString();
    }
    else
    {
      Validate.notEmpty(paramString1, "Must set charset arg to character set of file to parse. Set to null to attempt to detect from HTML");
      str1 = Charset.forName(paramString1).decode(paramByteBuffer).toString();
    }
    Document localDocument1 = null;
    label207: Document localDocument2;
    if ((str1.length() > 0) && (str1.charAt(0) == 65279))
    {
      paramByteBuffer.rewind();
      str1 = Charset.forName("UTF-8").decode(paramByteBuffer).toString().substring(1);
      paramString1 = "UTF-8";
      localDocument2 = null;
    }
    else
    {
      localDocument2 = localDocument1;
    }
    if (localDocument2 == null)
    {
      localDocument2 = paramParser.parseInput(str1, paramString2);
      localDocument2.outputSettings().charset(paramString1);
    }
    return localDocument2;
  }

  // ERROR //
  static ByteBuffer readFileToByteBuffer(File paramFile)
  {
    // Byte code:
    //   0: new 192	java/io/RandomAccessFile
    //   3: dup
    //   4: aload_0
    //   5: ldc 194
    //   7: invokespecial 197	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   10: astore_1
    //   11: aload_1
    //   12: invokevirtual 200	java/io/RandomAccessFile:length	()J
    //   15: l2i
    //   16: newarray byte
    //   18: astore_3
    //   19: aload_1
    //   20: aload_3
    //   21: invokevirtual 204	java/io/RandomAccessFile:readFully	([B)V
    //   24: aload_3
    //   25: invokestatic 208	java/nio/ByteBuffer:wrap	([B)Ljava/nio/ByteBuffer;
    //   28: astore 4
    //   30: aload_1
    //   31: ifnull +7 -> 38
    //   34: aload_1
    //   35: invokevirtual 211	java/io/RandomAccessFile:close	()V
    //   38: aload 4
    //   40: areturn
    //   41: astore_2
    //   42: goto +6 -> 48
    //   45: astore_2
    //   46: aconst_null
    //   47: astore_1
    //   48: aload_1
    //   49: ifnull +7 -> 56
    //   52: aload_1
    //   53: invokevirtual 211	java/io/RandomAccessFile:close	()V
    //   56: aload_2
    //   57: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   11	30	41	finally
    //   0	11	45	finally
  }

  static ByteBuffer readToByteBuffer(InputStream paramInputStream)
  {
    return readToByteBuffer(paramInputStream, 0);
  }

  static ByteBuffer readToByteBuffer(InputStream paramInputStream, int paramInt)
  {
    int i = 1;
    boolean bool;
    if (paramInt >= 0)
      bool = true;
    else
      bool = false;
    Validate.isTrue(bool, "maxSize must be 0 (unlimited) or larger");
    if (paramInt <= 0)
      i = 0;
    byte[] arrayOfByte = new byte[131072];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(131072);
    while (true)
    {
      int j = paramInputStream.read(arrayOfByte);
      if (j != -1)
      {
        if (i == 0)
          break label95;
        if (j > paramInt)
          localByteArrayOutputStream.write(arrayOfByte, 0, paramInt);
      }
      else
      {
        return ByteBuffer.wrap(localByteArrayOutputStream.toByteArray());
      }
      paramInt -= j;
      label95: localByteArrayOutputStream.write(arrayOfByte, 0, j);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.helper.DataUtil
 * JD-Core Version:    0.6.1
 */