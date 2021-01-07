package org.apache.commons.io;

import java.io.File;

public class FileCleaner
{
  static final FileCleaningTracker theInstance = new FileCleaningTracker();

  public static void exitWhenFinished()
  {
    try
    {
      theInstance.exitWhenFinished();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static FileCleaningTracker getInstance()
  {
    return theInstance;
  }

  public static int getTrackCount()
  {
    return theInstance.getTrackCount();
  }

  public static void track(File paramFile, Object paramObject)
  {
    theInstance.track(paramFile, paramObject);
  }

  public static void track(File paramFile, Object paramObject, FileDeleteStrategy paramFileDeleteStrategy)
  {
    theInstance.track(paramFile, paramObject, paramFileDeleteStrategy);
  }

  public static void track(String paramString, Object paramObject)
  {
    theInstance.track(paramString, paramObject);
  }

  public static void track(String paramString, Object paramObject, FileDeleteStrategy paramFileDeleteStrategy)
  {
    theInstance.track(paramString, paramObject, paramFileDeleteStrategy);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.FileCleaner
 * JD-Core Version:    0.6.1
 */