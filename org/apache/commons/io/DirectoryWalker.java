package org.apache.commons.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collection;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public abstract class DirectoryWalker
{
  private final int depthLimit;
  private final FileFilter filter;

  protected DirectoryWalker()
  {
    this(null, -1);
  }

  protected DirectoryWalker(FileFilter paramFileFilter, int paramInt)
  {
    this.filter = paramFileFilter;
    this.depthLimit = paramInt;
  }

  protected DirectoryWalker(IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2, int paramInt)
  {
    if ((paramIOFileFilter1 == null) && (paramIOFileFilter2 == null))
    {
      this.filter = null;
    }
    else
    {
      if (paramIOFileFilter1 == null)
        paramIOFileFilter1 = TrueFileFilter.TRUE;
      if (paramIOFileFilter2 == null)
        paramIOFileFilter2 = TrueFileFilter.TRUE;
      this.filter = FileFilterUtils.orFileFilter(FileFilterUtils.makeDirectoryOnly(paramIOFileFilter1), FileFilterUtils.makeFileOnly(paramIOFileFilter2));
    }
    this.depthLimit = paramInt;
  }

  private void walk(File paramFile, int paramInt, Collection paramCollection)
  {
    checkIfCancelled(paramFile, paramInt, paramCollection);
    if (handleDirectory(paramFile, paramInt, paramCollection))
    {
      handleDirectoryStart(paramFile, paramInt, paramCollection);
      int i = paramInt + 1;
      if ((this.depthLimit < 0) || (i <= this.depthLimit))
      {
        checkIfCancelled(paramFile, paramInt, paramCollection);
        File[] arrayOfFile;
        if (this.filter == null)
          arrayOfFile = paramFile.listFiles();
        else
          arrayOfFile = paramFile.listFiles(this.filter);
        if (arrayOfFile == null)
          handleRestricted(paramFile, i, paramCollection);
        else
          for (int j = 0; j < arrayOfFile.length; j++)
          {
            File localFile = arrayOfFile[j];
            if (localFile.isDirectory())
            {
              walk(localFile, i, paramCollection);
            }
            else
            {
              checkIfCancelled(localFile, i, paramCollection);
              handleFile(localFile, i, paramCollection);
              checkIfCancelled(localFile, i, paramCollection);
            }
          }
      }
      handleDirectoryEnd(paramFile, paramInt, paramCollection);
    }
    checkIfCancelled(paramFile, paramInt, paramCollection);
  }

  protected final void checkIfCancelled(File paramFile, int paramInt, Collection paramCollection)
  {
    if (handleIsCancelled(paramFile, paramInt, paramCollection))
      throw new CancelException(paramFile, paramInt);
  }

  protected void handleCancelled(File paramFile, Collection paramCollection, CancelException paramCancelException)
  {
    throw paramCancelException;
  }

  protected boolean handleDirectory(File paramFile, int paramInt, Collection paramCollection)
  {
    return true;
  }

  protected void handleDirectoryEnd(File paramFile, int paramInt, Collection paramCollection)
  {
  }

  protected void handleDirectoryStart(File paramFile, int paramInt, Collection paramCollection)
  {
  }

  protected void handleEnd(Collection paramCollection)
  {
  }

  protected void handleFile(File paramFile, int paramInt, Collection paramCollection)
  {
  }

  protected boolean handleIsCancelled(File paramFile, int paramInt, Collection paramCollection)
  {
    return false;
  }

  protected void handleRestricted(File paramFile, int paramInt, Collection paramCollection)
  {
  }

  protected void handleStart(File paramFile, Collection paramCollection)
  {
  }

  protected final void walk(File paramFile, Collection paramCollection)
  {
    if (paramFile == null)
      throw new NullPointerException("Start Directory is null");
    try
    {
      handleStart(paramFile, paramCollection);
      walk(paramFile, 0, paramCollection);
      handleEnd(paramCollection);
    }
    catch (CancelException localCancelException)
    {
      handleCancelled(paramFile, paramCollection, localCancelException);
    }
  }

  public static class CancelException extends IOException
  {
    private static final long serialVersionUID = 1347339620135041008L;
    private int depth = -1;
    private File file;

    public CancelException(File paramFile, int paramInt)
    {
      this("Operation Cancelled", paramFile, paramInt);
    }

    public CancelException(String paramString, File paramFile, int paramInt)
    {
      super();
      this.file = paramFile;
      this.depth = paramInt;
    }

    public int getDepth()
    {
      return this.depth;
    }

    public File getFile()
    {
      return this.file;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.DirectoryWalker
 * JD-Core Version:    0.6.1
 */