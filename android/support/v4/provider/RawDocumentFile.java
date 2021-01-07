package android.support.v4.provider;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class RawDocumentFile extends DocumentFile
{
  private File mFile;

  RawDocumentFile(@Nullable DocumentFile paramDocumentFile, File paramFile)
  {
    super(paramDocumentFile);
    this.mFile = paramFile;
  }

  private static boolean deleteContents(File paramFile)
  {
    File[] arrayOfFile = paramFile.listFiles();
    boolean bool1 = true;
    if (arrayOfFile != null)
    {
      int i = arrayOfFile.length;
      int j = 0;
      boolean bool2 = true;
      while (j < i)
      {
        File localFile = arrayOfFile[j];
        if (localFile.isDirectory())
          bool2 &= deleteContents(localFile);
        if (!localFile.delete())
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Failed to delete ");
          localStringBuilder.append(localFile);
          Log.w("DocumentFile", localStringBuilder.toString());
          bool2 = false;
        }
        j++;
      }
      bool1 = bool2;
    }
    return bool1;
  }

  private static String getTypeForName(String paramString)
  {
    int i = paramString.lastIndexOf('.');
    if (i >= 0)
    {
      String str1 = paramString.substring(i + 1).toLowerCase();
      String str2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str1);
      if (str2 != null)
        return str2;
    }
    return "application/octet-stream";
  }

  public boolean canRead()
  {
    return this.mFile.canRead();
  }

  public boolean canWrite()
  {
    return this.mFile.canWrite();
  }

  @Nullable
  public DocumentFile createDirectory(String paramString)
  {
    File localFile = new File(this.mFile, paramString);
    if ((!localFile.isDirectory()) && (!localFile.mkdir()))
      return null;
    return new RawDocumentFile(this, localFile);
  }

  @Nullable
  public DocumentFile createFile(String paramString1, String paramString2)
  {
    String str = MimeTypeMap.getSingleton().getExtensionFromMimeType(paramString1);
    if (str != null)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(paramString2);
      localStringBuilder1.append(".");
      localStringBuilder1.append(str);
      paramString2 = localStringBuilder1.toString();
    }
    File localFile = new File(this.mFile, paramString2);
    try
    {
      localFile.createNewFile();
      RawDocumentFile localRawDocumentFile = new RawDocumentFile(this, localFile);
      return localRawDocumentFile;
    }
    catch (IOException localIOException)
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Failed to createFile: ");
      localStringBuilder2.append(localIOException);
      Log.w("DocumentFile", localStringBuilder2.toString());
    }
    return null;
  }

  public boolean delete()
  {
    deleteContents(this.mFile);
    return this.mFile.delete();
  }

  public boolean exists()
  {
    return this.mFile.exists();
  }

  public String getName()
  {
    return this.mFile.getName();
  }

  @Nullable
  public String getType()
  {
    if (this.mFile.isDirectory())
      return null;
    return getTypeForName(this.mFile.getName());
  }

  public Uri getUri()
  {
    return Uri.fromFile(this.mFile);
  }

  public boolean isDirectory()
  {
    return this.mFile.isDirectory();
  }

  public boolean isFile()
  {
    return this.mFile.isFile();
  }

  public boolean isVirtual()
  {
    return false;
  }

  public long lastModified()
  {
    return this.mFile.lastModified();
  }

  public long length()
  {
    return this.mFile.length();
  }

  public DocumentFile[] listFiles()
  {
    ArrayList localArrayList = new ArrayList();
    File[] arrayOfFile = this.mFile.listFiles();
    if (arrayOfFile != null)
    {
      int i = arrayOfFile.length;
      for (int j = 0; j < i; j++)
        localArrayList.add(new RawDocumentFile(this, arrayOfFile[j]));
    }
    return (DocumentFile[])localArrayList.toArray(new DocumentFile[localArrayList.size()]);
  }

  public boolean renameTo(String paramString)
  {
    File localFile = new File(this.mFile.getParentFile(), paramString);
    if (this.mFile.renameTo(localFile))
    {
      this.mFile = localFile;
      return true;
    }
    return false;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.provider.RawDocumentFile
 * JD-Core Version:    0.6.1
 */