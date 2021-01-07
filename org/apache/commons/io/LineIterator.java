package org.apache.commons.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LineIterator
  implements Iterator
{
  private final BufferedReader bufferedReader;
  private String cachedLine;
  private boolean finished = false;

  public LineIterator(Reader paramReader)
  {
    if (paramReader == null)
      throw new IllegalArgumentException("Reader must not be null");
    if ((paramReader instanceof BufferedReader))
      this.bufferedReader = ((BufferedReader)paramReader);
    else
      this.bufferedReader = new BufferedReader(paramReader);
  }

  public static void closeQuietly(LineIterator paramLineIterator)
  {
    if (paramLineIterator != null)
      paramLineIterator.close();
  }

  public void close()
  {
    this.finished = true;
    IOUtils.closeQuietly(this.bufferedReader);
    this.cachedLine = null;
  }

  public boolean hasNext()
  {
    if (this.cachedLine != null)
      return true;
    if (this.finished)
      return false;
    try
    {
      String str;
      do
      {
        str = this.bufferedReader.readLine();
        if (str == null)
        {
          this.finished = true;
          return false;
        }
      }
      while (!isValidLine(str));
      this.cachedLine = str;
      return true;
    }
    catch (IOException localIOException)
    {
      close();
      throw new IllegalStateException(localIOException.toString());
    }
  }

  protected boolean isValidLine(String paramString)
  {
    return true;
  }

  public Object next()
  {
    return nextLine();
  }

  public String nextLine()
  {
    if (!hasNext())
      throw new NoSuchElementException("No more lines");
    String str = this.cachedLine;
    this.cachedLine = null;
    return str;
  }

  public void remove()
  {
    throw new UnsupportedOperationException("Remove unsupported on LineIterator");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.LineIterator
 * JD-Core Version:    0.6.1
 */