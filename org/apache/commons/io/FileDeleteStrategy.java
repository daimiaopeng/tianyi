package org.apache.commons.io;

import java.io.File;
import java.io.IOException;

public class FileDeleteStrategy
{
  public static final FileDeleteStrategy FORCE = new ForceFileDeleteStrategy();
  public static final FileDeleteStrategy NORMAL = new FileDeleteStrategy("Normal");
  private final String name;

  protected FileDeleteStrategy(String paramString)
  {
    this.name = paramString;
  }

  public void delete(File paramFile)
  {
    if ((paramFile.exists()) && (!doDelete(paramFile)))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Deletion failed: ");
      localStringBuffer.append(paramFile);
      throw new IOException(localStringBuffer.toString());
    }
  }

  // ERROR //
  public boolean deleteQuietly(File paramFile)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +23 -> 24
    //   4: aload_1
    //   5: invokevirtual 36	java/io/File:exists	()Z
    //   8: ifne +6 -> 14
    //   11: goto +13 -> 24
    //   14: aload_0
    //   15: aload_1
    //   16: invokevirtual 40	org/apache/commons/io/FileDeleteStrategy:doDelete	(Ljava/io/File;)Z
    //   19: istore_2
    //   20: iload_2
    //   21: ireturn
    //   22: iconst_0
    //   23: ireturn
    //   24: iconst_1
    //   25: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   14	20	22	java/io/IOException
  }

  protected boolean doDelete(File paramFile)
  {
    return paramFile.delete();
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("FileDeleteStrategy[");
    localStringBuffer.append(this.name);
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }

  static class ForceFileDeleteStrategy extends FileDeleteStrategy
  {
    ForceFileDeleteStrategy()
    {
      super();
    }

    protected boolean doDelete(File paramFile)
    {
      FileUtils.forceDelete(paramFile);
      return true;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.FileDeleteStrategy
 * JD-Core Version:    0.6.1
 */