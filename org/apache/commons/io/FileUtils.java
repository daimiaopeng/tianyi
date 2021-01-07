package org.apache.commons.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.CRC32;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class FileUtils
{
  public static final File[] EMPTY_FILE_ARRAY = new File[0];
  public static final long ONE_GB = 1073741824L;
  public static final long ONE_KB = 1024L;
  public static final long ONE_MB = 1048576L;

  public static String byteCountToDisplaySize(long paramLong)
  {
    long l1 = paramLong / 1073741824L;
    String str;
    if (l1 > 0L)
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(String.valueOf(l1));
      localStringBuffer1.append(" GB");
      str = localStringBuffer1.toString();
    }
    else
    {
      long l2 = paramLong / 1048576L;
      if (l2 > 0L)
      {
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append(String.valueOf(l2));
        localStringBuffer2.append(" MB");
        str = localStringBuffer2.toString();
      }
      else
      {
        long l3 = paramLong / 1024L;
        if (l3 > 0L)
        {
          StringBuffer localStringBuffer3 = new StringBuffer();
          localStringBuffer3.append(String.valueOf(l3));
          localStringBuffer3.append(" KB");
          str = localStringBuffer3.toString();
        }
        else
        {
          StringBuffer localStringBuffer4 = new StringBuffer();
          localStringBuffer4.append(String.valueOf(paramLong));
          localStringBuffer4.append(" bytes");
          str = localStringBuffer4.toString();
        }
      }
    }
    return str;
  }

  // ERROR //
  public static java.util.zip.Checksum checksum(File paramFile, java.util.zip.Checksum paramChecksum)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 57	java/io/File:isDirectory	()Z
    //   4: ifeq +13 -> 17
    //   7: new 59	java/lang/IllegalArgumentException
    //   10: dup
    //   11: ldc 61
    //   13: invokespecial 64	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aconst_null
    //   18: astore_2
    //   19: new 66	java/util/zip/CheckedInputStream
    //   22: dup
    //   23: new 68	java/io/FileInputStream
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 71	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   31: aload_1
    //   32: invokespecial 74	java/util/zip/CheckedInputStream:<init>	(Ljava/io/InputStream;Ljava/util/zip/Checksum;)V
    //   35: astore_3
    //   36: aload_3
    //   37: new 76	org/apache/commons/io/output/NullOutputStream
    //   40: dup
    //   41: invokespecial 77	org/apache/commons/io/output/NullOutputStream:<init>	()V
    //   44: invokestatic 83	org/apache/commons/io/IOUtils:copy	(Ljava/io/InputStream;Ljava/io/OutputStream;)I
    //   47: pop
    //   48: aload_3
    //   49: invokestatic 87	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   52: aload_1
    //   53: areturn
    //   54: astore 4
    //   56: aload_3
    //   57: astore_2
    //   58: goto +5 -> 63
    //   61: astore 4
    //   63: aload_2
    //   64: invokestatic 87	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   67: aload 4
    //   69: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   36	48	54	finally
    //   19	36	61	finally
  }

  public static long checksumCRC32(File paramFile)
  {
    CRC32 localCRC32 = new CRC32();
    checksum(paramFile, localCRC32);
    return localCRC32.getValue();
  }

  public static void cleanDirectory(File paramFile)
  {
    if (!paramFile.exists())
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(paramFile);
      localStringBuffer1.append(" does not exist");
      throw new IllegalArgumentException(localStringBuffer1.toString());
    }
    if (!paramFile.isDirectory())
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append(paramFile);
      localStringBuffer2.append(" is not a directory");
      throw new IllegalArgumentException(localStringBuffer2.toString());
    }
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null)
    {
      StringBuffer localStringBuffer3 = new StringBuffer();
      localStringBuffer3.append("Failed to list contents of ");
      localStringBuffer3.append(paramFile);
      throw new IOException(localStringBuffer3.toString());
    }
    Object localObject = null;
    for (int i = 0; i < arrayOfFile.length; i++)
    {
      File localFile = arrayOfFile[i];
      try
      {
        forceDelete(localFile);
      }
      catch (IOException localIOException)
      {
      }
    }
    if (localIOException != null)
      throw localIOException;
  }

  private static void cleanDirectoryOnExit(File paramFile)
  {
    if (!paramFile.exists())
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(paramFile);
      localStringBuffer1.append(" does not exist");
      throw new IllegalArgumentException(localStringBuffer1.toString());
    }
    if (!paramFile.isDirectory())
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append(paramFile);
      localStringBuffer2.append(" is not a directory");
      throw new IllegalArgumentException(localStringBuffer2.toString());
    }
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null)
    {
      StringBuffer localStringBuffer3 = new StringBuffer();
      localStringBuffer3.append("Failed to list contents of ");
      localStringBuffer3.append(paramFile);
      throw new IOException(localStringBuffer3.toString());
    }
    Object localObject = null;
    for (int i = 0; i < arrayOfFile.length; i++)
    {
      File localFile = arrayOfFile[i];
      try
      {
        forceDeleteOnExit(localFile);
      }
      catch (IOException localIOException)
      {
      }
    }
    if (localIOException != null)
      throw localIOException;
  }

  // ERROR //
  public static boolean contentEquals(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 104	java/io/File:exists	()Z
    //   4: istore_2
    //   5: iload_2
    //   6: aload_1
    //   7: invokevirtual 104	java/io/File:exists	()Z
    //   10: if_icmpeq +5 -> 15
    //   13: iconst_0
    //   14: ireturn
    //   15: iload_2
    //   16: ifne +5 -> 21
    //   19: iconst_1
    //   20: ireturn
    //   21: aload_0
    //   22: invokevirtual 57	java/io/File:isDirectory	()Z
    //   25: ifne +119 -> 144
    //   28: aload_1
    //   29: invokevirtual 57	java/io/File:isDirectory	()Z
    //   32: ifeq +6 -> 38
    //   35: goto +109 -> 144
    //   38: aload_0
    //   39: invokevirtual 130	java/io/File:length	()J
    //   42: aload_1
    //   43: invokevirtual 130	java/io/File:length	()J
    //   46: lcmp
    //   47: ifeq +5 -> 52
    //   50: iconst_0
    //   51: ireturn
    //   52: aload_0
    //   53: invokevirtual 134	java/io/File:getCanonicalFile	()Ljava/io/File;
    //   56: aload_1
    //   57: invokevirtual 134	java/io/File:getCanonicalFile	()Ljava/io/File;
    //   60: invokevirtual 138	java/io/File:equals	(Ljava/lang/Object;)Z
    //   63: ifeq +5 -> 68
    //   66: iconst_1
    //   67: ireturn
    //   68: aconst_null
    //   69: astore_3
    //   70: new 68	java/io/FileInputStream
    //   73: dup
    //   74: aload_0
    //   75: invokespecial 71	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   78: astore 4
    //   80: new 68	java/io/FileInputStream
    //   83: dup
    //   84: aload_1
    //   85: invokespecial 71	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   88: astore 5
    //   90: aload 4
    //   92: aload 5
    //   94: invokestatic 141	org/apache/commons/io/IOUtils:contentEquals	(Ljava/io/InputStream;Ljava/io/InputStream;)Z
    //   97: istore 7
    //   99: aload 4
    //   101: invokestatic 87	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   104: aload 5
    //   106: invokestatic 87	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   109: iload 7
    //   111: ireturn
    //   112: astore 6
    //   114: aload 5
    //   116: astore_3
    //   117: goto +15 -> 132
    //   120: astore 6
    //   122: aconst_null
    //   123: astore_3
    //   124: goto +8 -> 132
    //   127: astore 6
    //   129: aconst_null
    //   130: astore 4
    //   132: aload 4
    //   134: invokestatic 87	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   137: aload_3
    //   138: invokestatic 87	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   141: aload 6
    //   143: athrow
    //   144: new 101	java/io/IOException
    //   147: dup
    //   148: ldc 143
    //   150: invokespecial 118	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   153: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   90	99	112	finally
    //   80	90	120	finally
    //   70	80	127	finally
  }

  public static File[] convertFileCollectionToFileArray(Collection paramCollection)
  {
    return (File[])paramCollection.toArray(new File[paramCollection.size()]);
  }

  public static void copyDirectory(File paramFile1, File paramFile2)
  {
    copyDirectory(paramFile1, paramFile2, true);
  }

  public static void copyDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
  {
    if (paramFile1 == null)
      throw new NullPointerException("Source must not be null");
    if (paramFile2 == null)
      throw new NullPointerException("Destination must not be null");
    if (!paramFile1.exists())
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("Source '");
      localStringBuffer1.append(paramFile1);
      localStringBuffer1.append("' does not exist");
      throw new FileNotFoundException(localStringBuffer1.toString());
    }
    if (!paramFile1.isDirectory())
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append("Source '");
      localStringBuffer2.append(paramFile1);
      localStringBuffer2.append("' exists but is not a directory");
      throw new IOException(localStringBuffer2.toString());
    }
    if (paramFile1.getCanonicalPath().equals(paramFile2.getCanonicalPath()))
    {
      StringBuffer localStringBuffer3 = new StringBuffer();
      localStringBuffer3.append("Source '");
      localStringBuffer3.append(paramFile1);
      localStringBuffer3.append("' and destination '");
      localStringBuffer3.append(paramFile2);
      localStringBuffer3.append("' are the same");
      throw new IOException(localStringBuffer3.toString());
    }
    doCopyDirectory(paramFile1, paramFile2, paramBoolean);
  }

  public static void copyDirectoryToDirectory(File paramFile1, File paramFile2)
  {
    if (paramFile1 == null)
      throw new NullPointerException("Source must not be null");
    if ((paramFile1.exists()) && (!paramFile1.isDirectory()))
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append("Source '");
      localStringBuffer2.append(paramFile2);
      localStringBuffer2.append("' is not a directory");
      throw new IllegalArgumentException(localStringBuffer2.toString());
    }
    if (paramFile2 == null)
      throw new NullPointerException("Destination must not be null");
    if ((paramFile2.exists()) && (!paramFile2.isDirectory()))
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("Destination '");
      localStringBuffer1.append(paramFile2);
      localStringBuffer1.append("' is not a directory");
      throw new IllegalArgumentException(localStringBuffer1.toString());
    }
    copyDirectory(paramFile1, new File(paramFile2, paramFile1.getName()), true);
  }

  public static void copyFile(File paramFile1, File paramFile2)
  {
    copyFile(paramFile1, paramFile2, true);
  }

  public static void copyFile(File paramFile1, File paramFile2, boolean paramBoolean)
  {
    if (paramFile1 == null)
      throw new NullPointerException("Source must not be null");
    if (paramFile2 == null)
      throw new NullPointerException("Destination must not be null");
    if (!paramFile1.exists())
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("Source '");
      localStringBuffer1.append(paramFile1);
      localStringBuffer1.append("' does not exist");
      throw new FileNotFoundException(localStringBuffer1.toString());
    }
    if (paramFile1.isDirectory())
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append("Source '");
      localStringBuffer2.append(paramFile1);
      localStringBuffer2.append("' exists but is a directory");
      throw new IOException(localStringBuffer2.toString());
    }
    if (paramFile1.getCanonicalPath().equals(paramFile2.getCanonicalPath()))
    {
      StringBuffer localStringBuffer3 = new StringBuffer();
      localStringBuffer3.append("Source '");
      localStringBuffer3.append(paramFile1);
      localStringBuffer3.append("' and destination '");
      localStringBuffer3.append(paramFile2);
      localStringBuffer3.append("' are the same");
      throw new IOException(localStringBuffer3.toString());
    }
    if ((paramFile2.getParentFile() != null) && (!paramFile2.getParentFile().exists()) && (!paramFile2.getParentFile().mkdirs()))
    {
      StringBuffer localStringBuffer5 = new StringBuffer();
      localStringBuffer5.append("Destination '");
      localStringBuffer5.append(paramFile2);
      localStringBuffer5.append("' directory cannot be created");
      throw new IOException(localStringBuffer5.toString());
    }
    if ((paramFile2.exists()) && (!paramFile2.canWrite()))
    {
      StringBuffer localStringBuffer4 = new StringBuffer();
      localStringBuffer4.append("Destination '");
      localStringBuffer4.append(paramFile2);
      localStringBuffer4.append("' exists but is read-only");
      throw new IOException(localStringBuffer4.toString());
    }
    doCopyFile(paramFile1, paramFile2, paramBoolean);
  }

  public static void copyFileToDirectory(File paramFile1, File paramFile2)
  {
    copyFileToDirectory(paramFile1, paramFile2, true);
  }

  public static void copyFileToDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
  {
    if (paramFile2 == null)
      throw new NullPointerException("Destination must not be null");
    if ((paramFile2.exists()) && (!paramFile2.isDirectory()))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Destination '");
      localStringBuffer.append(paramFile2);
      localStringBuffer.append("' is not a directory");
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    copyFile(paramFile1, new File(paramFile2, paramFile1.getName()), paramBoolean);
  }

  public static void copyURLToFile(URL paramURL, File paramFile)
  {
    InputStream localInputStream = paramURL.openStream();
    try
    {
      FileOutputStream localFileOutputStream = openOutputStream(paramFile);
      try
      {
        IOUtils.copy(localInputStream, localFileOutputStream);
        return;
      }
      finally
      {
      }
    }
    finally
    {
      IOUtils.closeQuietly(localInputStream);
    }
  }

  public static void deleteDirectory(File paramFile)
  {
    if (!paramFile.exists())
      return;
    cleanDirectory(paramFile);
    if (!paramFile.delete())
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Unable to delete directory ");
      localStringBuffer.append(paramFile);
      localStringBuffer.append(".");
      throw new IOException(localStringBuffer.toString());
    }
  }

  private static void deleteDirectoryOnExit(File paramFile)
  {
    if (!paramFile.exists())
      return;
    cleanDirectoryOnExit(paramFile);
    paramFile.deleteOnExit();
  }

  private static void doCopyDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
  {
    if (paramFile2.exists())
    {
      if (!paramFile2.isDirectory())
      {
        StringBuffer localStringBuffer4 = new StringBuffer();
        localStringBuffer4.append("Destination '");
        localStringBuffer4.append(paramFile2);
        localStringBuffer4.append("' exists but is not a directory");
        throw new IOException(localStringBuffer4.toString());
      }
    }
    else
    {
      if (!paramFile2.mkdirs())
      {
        StringBuffer localStringBuffer1 = new StringBuffer();
        localStringBuffer1.append("Destination '");
        localStringBuffer1.append(paramFile2);
        localStringBuffer1.append("' directory cannot be created");
        throw new IOException(localStringBuffer1.toString());
      }
      if (paramBoolean)
        paramFile2.setLastModified(paramFile1.lastModified());
    }
    if (!paramFile2.canWrite())
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append("Destination '");
      localStringBuffer2.append(paramFile2);
      localStringBuffer2.append("' cannot be written to");
      throw new IOException(localStringBuffer2.toString());
    }
    File[] arrayOfFile = paramFile1.listFiles();
    if (arrayOfFile == null)
    {
      StringBuffer localStringBuffer3 = new StringBuffer();
      localStringBuffer3.append("Failed to list contents of ");
      localStringBuffer3.append(paramFile1);
      throw new IOException(localStringBuffer3.toString());
    }
    for (int i = 0; i < arrayOfFile.length; i++)
    {
      File localFile = new File(paramFile2, arrayOfFile[i].getName());
      if (arrayOfFile[i].isDirectory())
        doCopyDirectory(arrayOfFile[i], localFile, paramBoolean);
      else
        doCopyFile(arrayOfFile[i], localFile, paramBoolean);
    }
  }

  private static void doCopyFile(File paramFile1, File paramFile2, boolean paramBoolean)
  {
    if ((paramFile2.exists()) && (paramFile2.isDirectory()))
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append("Destination '");
      localStringBuffer2.append(paramFile2);
      localStringBuffer2.append("' exists but is a directory");
      throw new IOException(localStringBuffer2.toString());
    }
    FileInputStream localFileInputStream = new FileInputStream(paramFile1);
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(paramFile2);
      try
      {
        IOUtils.copy(localFileInputStream, localFileOutputStream);
        IOUtils.closeQuietly(localFileOutputStream);
        IOUtils.closeQuietly(localFileInputStream);
        if (paramFile1.length() != paramFile2.length())
        {
          StringBuffer localStringBuffer1 = new StringBuffer();
          localStringBuffer1.append("Failed to copy full contents from '");
          localStringBuffer1.append(paramFile1);
          localStringBuffer1.append("' to '");
          localStringBuffer1.append(paramFile2);
          localStringBuffer1.append("'");
          throw new IOException(localStringBuffer1.toString());
        }
        if (paramBoolean)
          paramFile2.setLastModified(paramFile1.lastModified());
        return;
      }
      finally
      {
      }
    }
    finally
    {
      IOUtils.closeQuietly(localFileInputStream);
    }
  }

  public static void forceDelete(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      deleteDirectory(paramFile);
    }
    else
    {
      if (!paramFile.exists())
      {
        StringBuffer localStringBuffer1 = new StringBuffer();
        localStringBuffer1.append("File does not exist: ");
        localStringBuffer1.append(paramFile);
        throw new FileNotFoundException(localStringBuffer1.toString());
      }
      if (!paramFile.delete())
      {
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("Unable to delete file: ");
        localStringBuffer2.append(paramFile);
        throw new IOException(localStringBuffer2.toString());
      }
    }
  }

  public static void forceDeleteOnExit(File paramFile)
  {
    if (paramFile.isDirectory())
      deleteDirectoryOnExit(paramFile);
    else
      paramFile.deleteOnExit();
  }

  public static void forceMkdir(File paramFile)
  {
    if (paramFile.exists())
    {
      if (paramFile.isFile())
      {
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("File ");
        localStringBuffer2.append(paramFile);
        localStringBuffer2.append(" exists and is ");
        localStringBuffer2.append("not a directory. Unable to create directory.");
        throw new IOException(localStringBuffer2.toString());
      }
    }
    else if (!paramFile.mkdirs())
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append("Unable to create directory ");
      localStringBuffer1.append(paramFile);
      throw new IOException(localStringBuffer1.toString());
    }
  }

  private static void innerListFiles(Collection paramCollection, File paramFile, IOFileFilter paramIOFileFilter)
  {
    File[] arrayOfFile = paramFile.listFiles(paramIOFileFilter);
    if (arrayOfFile != null)
      for (int i = 0; i < arrayOfFile.length; i++)
        if (arrayOfFile[i].isDirectory())
          innerListFiles(paramCollection, arrayOfFile[i], paramIOFileFilter);
        else
          paramCollection.add(arrayOfFile[i]);
  }

  public static boolean isFileNewer(File paramFile, long paramLong)
  {
    if (paramFile == null)
      throw new IllegalArgumentException("No specified file");
    if (!paramFile.exists())
      return false;
    boolean bool1 = paramFile.lastModified() < paramLong;
    boolean bool2 = false;
    if (bool1)
      bool2 = true;
    return bool2;
  }

  public static boolean isFileNewer(File paramFile1, File paramFile2)
  {
    if (paramFile2 == null)
      throw new IllegalArgumentException("No specified reference file");
    if (!paramFile2.exists())
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("The reference file '");
      localStringBuffer.append(paramFile1);
      localStringBuffer.append("' doesn't exist");
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    return isFileNewer(paramFile1, paramFile2.lastModified());
  }

  public static boolean isFileNewer(File paramFile, Date paramDate)
  {
    if (paramDate == null)
      throw new IllegalArgumentException("No specified date");
    return isFileNewer(paramFile, paramDate.getTime());
  }

  public static boolean isFileOlder(File paramFile, long paramLong)
  {
    if (paramFile == null)
      throw new IllegalArgumentException("No specified file");
    if (!paramFile.exists())
      return false;
    boolean bool1 = paramFile.lastModified() < paramLong;
    boolean bool2 = false;
    if (bool1)
      bool2 = true;
    return bool2;
  }

  public static boolean isFileOlder(File paramFile1, File paramFile2)
  {
    if (paramFile2 == null)
      throw new IllegalArgumentException("No specified reference file");
    if (!paramFile2.exists())
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("The reference file '");
      localStringBuffer.append(paramFile1);
      localStringBuffer.append("' doesn't exist");
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    return isFileOlder(paramFile1, paramFile2.lastModified());
  }

  public static boolean isFileOlder(File paramFile, Date paramDate)
  {
    if (paramDate == null)
      throw new IllegalArgumentException("No specified date");
    return isFileOlder(paramFile, paramDate.getTime());
  }

  public static Iterator iterateFiles(File paramFile, IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    return listFiles(paramFile, paramIOFileFilter1, paramIOFileFilter2).iterator();
  }

  public static Iterator iterateFiles(File paramFile, String[] paramArrayOfString, boolean paramBoolean)
  {
    return listFiles(paramFile, paramArrayOfString, paramBoolean).iterator();
  }

  public static LineIterator lineIterator(File paramFile)
  {
    return lineIterator(paramFile, null);
  }

  public static LineIterator lineIterator(File paramFile, String paramString)
  {
    FileInputStream localFileInputStream;
    try
    {
      localFileInputStream = openInputStream(paramFile);
      try
      {
        LineIterator localLineIterator = IOUtils.lineIterator(localFileInputStream, paramString);
        return localLineIterator;
      }
      catch (RuntimeException localRuntimeException1)
      {
      }
      catch (IOException localIOException1)
      {
      }
    }
    catch (RuntimeException localRuntimeException2)
    {
      localFileInputStream = null;
      IOUtils.closeQuietly(localFileInputStream);
      throw localRuntimeException2;
    }
    catch (IOException localIOException2)
    {
      localFileInputStream = null;
    }
    IOUtils.closeQuietly(localFileInputStream);
    throw localIOException2;
  }

  public static Collection listFiles(File paramFile, IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    if (!paramFile.isDirectory())
      throw new IllegalArgumentException("Parameter 'directory' is not a directory");
    if (paramIOFileFilter1 == null)
      throw new NullPointerException("Parameter 'fileFilter' is null");
    IOFileFilter localIOFileFilter1 = FileFilterUtils.andFileFilter(paramIOFileFilter1, FileFilterUtils.notFileFilter(DirectoryFileFilter.INSTANCE));
    IOFileFilter localIOFileFilter2;
    if (paramIOFileFilter2 == null)
      localIOFileFilter2 = FalseFileFilter.INSTANCE;
    else
      localIOFileFilter2 = FileFilterUtils.andFileFilter(paramIOFileFilter2, DirectoryFileFilter.INSTANCE);
    LinkedList localLinkedList = new LinkedList();
    innerListFiles(localLinkedList, paramFile, FileFilterUtils.orFileFilter(localIOFileFilter1, localIOFileFilter2));
    return localLinkedList;
  }

  public static Collection listFiles(File paramFile, String[] paramArrayOfString, boolean paramBoolean)
  {
    Object localObject;
    if (paramArrayOfString == null)
      localObject = TrueFileFilter.INSTANCE;
    else
      localObject = new SuffixFileFilter(toSuffixes(paramArrayOfString));
    IOFileFilter localIOFileFilter;
    if (paramBoolean)
      localIOFileFilter = TrueFileFilter.INSTANCE;
    else
      localIOFileFilter = FalseFileFilter.INSTANCE;
    return listFiles(paramFile, (IOFileFilter)localObject, localIOFileFilter);
  }

  public static FileInputStream openInputStream(File paramFile)
  {
    if (paramFile.exists())
    {
      if (paramFile.isDirectory())
      {
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("File '");
        localStringBuffer2.append(paramFile);
        localStringBuffer2.append("' exists but is a directory");
        throw new IOException(localStringBuffer2.toString());
      }
      if (!paramFile.canRead())
      {
        StringBuffer localStringBuffer3 = new StringBuffer();
        localStringBuffer3.append("File '");
        localStringBuffer3.append(paramFile);
        localStringBuffer3.append("' cannot be read");
        throw new IOException(localStringBuffer3.toString());
      }
      return new FileInputStream(paramFile);
    }
    StringBuffer localStringBuffer1 = new StringBuffer();
    localStringBuffer1.append("File '");
    localStringBuffer1.append(paramFile);
    localStringBuffer1.append("' does not exist");
    throw new FileNotFoundException(localStringBuffer1.toString());
  }

  public static FileOutputStream openOutputStream(File paramFile)
  {
    if (paramFile.exists())
    {
      if (paramFile.isDirectory())
      {
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append("File '");
        localStringBuffer2.append(paramFile);
        localStringBuffer2.append("' exists but is a directory");
        throw new IOException(localStringBuffer2.toString());
      }
      if (!paramFile.canWrite())
      {
        StringBuffer localStringBuffer3 = new StringBuffer();
        localStringBuffer3.append("File '");
        localStringBuffer3.append(paramFile);
        localStringBuffer3.append("' cannot be written to");
        throw new IOException(localStringBuffer3.toString());
      }
    }
    else
    {
      File localFile = paramFile.getParentFile();
      if ((localFile != null) && (!localFile.exists()) && (!localFile.mkdirs()))
      {
        StringBuffer localStringBuffer1 = new StringBuffer();
        localStringBuffer1.append("File '");
        localStringBuffer1.append(paramFile);
        localStringBuffer1.append("' could not be created");
        throw new IOException(localStringBuffer1.toString());
      }
    }
    return new FileOutputStream(paramFile);
  }

  // ERROR //
  public static byte[] readFileToByteArray(File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 349	org/apache/commons/io/FileUtils:openInputStream	(Ljava/io/File;)Ljava/io/FileInputStream;
    //   4: astore_2
    //   5: aload_2
    //   6: invokestatic 408	org/apache/commons/io/IOUtils:toByteArray	(Ljava/io/InputStream;)[B
    //   9: astore_3
    //   10: aload_2
    //   11: invokestatic 87	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   14: aload_3
    //   15: areturn
    //   16: astore_1
    //   17: goto +6 -> 23
    //   20: astore_1
    //   21: aconst_null
    //   22: astore_2
    //   23: aload_2
    //   24: invokestatic 87	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   27: aload_1
    //   28: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   5	10	16	finally
    //   0	5	20	finally
  }

  public static String readFileToString(File paramFile)
  {
    return readFileToString(paramFile, null);
  }

  // ERROR //
  public static String readFileToString(File paramFile, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 349	org/apache/commons/io/FileUtils:openInputStream	(Ljava/io/File;)Ljava/io/FileInputStream;
    //   4: astore_3
    //   5: aload_3
    //   6: aload_1
    //   7: invokestatic 416	org/apache/commons/io/IOUtils:toString	(Ljava/io/InputStream;Ljava/lang/String;)Ljava/lang/String;
    //   10: astore 4
    //   12: aload_3
    //   13: invokestatic 87	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   16: aload 4
    //   18: areturn
    //   19: astore_2
    //   20: goto +6 -> 26
    //   23: astore_2
    //   24: aconst_null
    //   25: astore_3
    //   26: aload_3
    //   27: invokestatic 87	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   30: aload_2
    //   31: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   5	12	19	finally
    //   0	5	23	finally
  }

  public static List readLines(File paramFile)
  {
    return readLines(paramFile, null);
  }

  // ERROR //
  public static List readLines(File paramFile, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 349	org/apache/commons/io/FileUtils:openInputStream	(Ljava/io/File;)Ljava/io/FileInputStream;
    //   4: astore_3
    //   5: aload_3
    //   6: aload_1
    //   7: invokestatic 424	org/apache/commons/io/IOUtils:readLines	(Ljava/io/InputStream;Ljava/lang/String;)Ljava/util/List;
    //   10: astore 4
    //   12: aload_3
    //   13: invokestatic 87	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   16: aload 4
    //   18: areturn
    //   19: astore_2
    //   20: goto +6 -> 26
    //   23: astore_2
    //   24: aconst_null
    //   25: astore_3
    //   26: aload_3
    //   27: invokestatic 87	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   30: aload_2
    //   31: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   5	12	19	finally
    //   0	5	23	finally
  }

  public static long sizeOfDirectory(File paramFile)
  {
    if (!paramFile.exists())
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(paramFile);
      localStringBuffer1.append(" does not exist");
      throw new IllegalArgumentException(localStringBuffer1.toString());
    }
    if (!paramFile.isDirectory())
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append(paramFile);
      localStringBuffer2.append(" is not a directory");
      throw new IllegalArgumentException(localStringBuffer2.toString());
    }
    File[] arrayOfFile = paramFile.listFiles();
    long l1 = 0L;
    if (arrayOfFile == null)
      return l1;
    for (int i = 0; i < arrayOfFile.length; i++)
    {
      File localFile = arrayOfFile[i];
      if (localFile.isDirectory());
      for (long l2 = l1 + sizeOfDirectory(localFile); ; l2 = l1 + localFile.length())
      {
        l1 = l2;
        break;
      }
    }
    return l1;
  }

  public static File toFile(URL paramURL)
  {
    if ((paramURL != null) && (paramURL.getProtocol().equals("file")))
    {
      String str = paramURL.getFile().replace('/', File.separatorChar);
      int i = 0;
      while (true)
      {
        i = str.indexOf('%', i);
        if (i < 0)
          break;
        if (i + 2 < str.length())
        {
          int j = i + 1;
          int k = i + 3;
          char c = (char)Integer.parseInt(str.substring(j, k), 16);
          StringBuffer localStringBuffer = new StringBuffer();
          localStringBuffer.append(str.substring(0, i));
          localStringBuffer.append(c);
          localStringBuffer.append(str.substring(k));
          str = localStringBuffer.toString();
        }
      }
      return new File(str);
    }
    return null;
  }

  public static File[] toFiles(URL[] paramArrayOfURL)
  {
    if ((paramArrayOfURL != null) && (paramArrayOfURL.length != 0))
    {
      File[] arrayOfFile = new File[paramArrayOfURL.length];
      for (int i = 0; i < paramArrayOfURL.length; i++)
      {
        URL localURL = paramArrayOfURL[i];
        if (localURL != null)
        {
          if (!localURL.getProtocol().equals("file"))
          {
            StringBuffer localStringBuffer = new StringBuffer();
            localStringBuffer.append("URL could not be converted to a File: ");
            localStringBuffer.append(localURL);
            throw new IllegalArgumentException(localStringBuffer.toString());
          }
          arrayOfFile[i] = toFile(localURL);
        }
      }
      return arrayOfFile;
    }
    return EMPTY_FILE_ARRAY;
  }

  private static String[] toSuffixes(String[] paramArrayOfString)
  {
    String[] arrayOfString = new String[paramArrayOfString.length];
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(".");
      localStringBuffer.append(paramArrayOfString[i]);
      arrayOfString[i] = localStringBuffer.toString();
    }
    return arrayOfString;
  }

  public static URL[] toURLs(File[] paramArrayOfFile)
  {
    URL[] arrayOfURL = new URL[paramArrayOfFile.length];
    for (int i = 0; i < arrayOfURL.length; i++)
      arrayOfURL[i] = paramArrayOfFile[i].toURL();
    return arrayOfURL;
  }

  public static void touch(File paramFile)
  {
    if (!paramFile.exists())
      IOUtils.closeQuietly(openOutputStream(paramFile));
    if (!paramFile.setLastModified(System.currentTimeMillis()))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Unable to set the last modification time for ");
      localStringBuffer.append(paramFile);
      throw new IOException(localStringBuffer.toString());
    }
  }

  // ERROR //
  public static boolean waitFor(File paramFile, int paramInt)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: iconst_0
    //   3: istore_3
    //   4: goto +4 -> 8
    //   7: pop
    //   8: aload_0
    //   9: invokevirtual 104	java/io/File:exists	()Z
    //   12: ifne +46 -> 58
    //   15: iload_2
    //   16: iconst_1
    //   17: iadd
    //   18: istore 4
    //   20: iload_2
    //   21: bipush 10
    //   23: if_icmplt +23 -> 46
    //   26: iload_3
    //   27: iconst_1
    //   28: iadd
    //   29: istore 6
    //   31: iload_3
    //   32: iload_1
    //   33: if_icmple +5 -> 38
    //   36: iconst_0
    //   37: ireturn
    //   38: iload 6
    //   40: istore_3
    //   41: iconst_0
    //   42: istore_2
    //   43: goto +6 -> 49
    //   46: iload 4
    //   48: istore_2
    //   49: ldc2_w 495
    //   52: invokestatic 502	java/lang/Thread:sleep	(J)V
    //   55: goto -48 -> 7
    //   58: iconst_1
    //   59: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   49	55	7	java/lang/InterruptedException
    //   49	55	58	java/lang/Exception
  }

  // ERROR //
  public static void writeByteArrayToFile(File paramFile, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 235	org/apache/commons/io/FileUtils:openOutputStream	(Ljava/io/File;)Ljava/io/FileOutputStream;
    //   4: astore_3
    //   5: aload_3
    //   6: aload_1
    //   7: invokevirtual 510	java/io/OutputStream:write	([B)V
    //   10: aload_3
    //   11: invokestatic 238	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   14: return
    //   15: astore_2
    //   16: goto +6 -> 22
    //   19: astore_2
    //   20: aconst_null
    //   21: astore_3
    //   22: aload_3
    //   23: invokestatic 238	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   26: aload_2
    //   27: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   5	10	15	finally
    //   0	5	19	finally
  }

  public static void writeLines(File paramFile, String paramString, Collection paramCollection)
  {
    writeLines(paramFile, paramString, paramCollection, null);
  }

  // ERROR //
  public static void writeLines(File paramFile, String paramString1, Collection paramCollection, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 235	org/apache/commons/io/FileUtils:openOutputStream	(Ljava/io/File;)Ljava/io/FileOutputStream;
    //   4: astore 5
    //   6: aload_2
    //   7: aload_3
    //   8: aload 5
    //   10: aload_1
    //   11: invokestatic 518	org/apache/commons/io/IOUtils:writeLines	(Ljava/util/Collection;Ljava/lang/String;Ljava/io/OutputStream;Ljava/lang/String;)V
    //   14: aload 5
    //   16: invokestatic 238	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   19: return
    //   20: astore 4
    //   22: goto +8 -> 30
    //   25: astore 4
    //   27: aconst_null
    //   28: astore 5
    //   30: aload 5
    //   32: invokestatic 238	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   35: aload 4
    //   37: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   6	14	20	finally
    //   0	6	25	finally
  }

  public static void writeLines(File paramFile, Collection paramCollection)
  {
    writeLines(paramFile, null, paramCollection, null);
  }

  public static void writeLines(File paramFile, Collection paramCollection, String paramString)
  {
    writeLines(paramFile, null, paramCollection, paramString);
  }

  public static void writeStringToFile(File paramFile, String paramString)
  {
    writeStringToFile(paramFile, paramString, null);
  }

  // ERROR //
  public static void writeStringToFile(File paramFile, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 235	org/apache/commons/io/FileUtils:openOutputStream	(Ljava/io/File;)Ljava/io/FileOutputStream;
    //   4: astore 4
    //   6: aload_1
    //   7: aload 4
    //   9: aload_2
    //   10: invokestatic 527	org/apache/commons/io/IOUtils:write	(Ljava/lang/String;Ljava/io/OutputStream;Ljava/lang/String;)V
    //   13: aload 4
    //   15: invokestatic 238	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   18: return
    //   19: astore_3
    //   20: goto +7 -> 27
    //   23: astore_3
    //   24: aconst_null
    //   25: astore 4
    //   27: aload 4
    //   29: invokestatic 238	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   32: aload_3
    //   33: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   6	13	19	finally
    //   0	6	23	finally
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.FileUtils
 * JD-Core Version:    0.6.1
 */