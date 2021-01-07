package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

@RequiresApi(19)
class SingleDocumentFile extends DocumentFile
{
  private Context mContext;
  private Uri mUri;

  SingleDocumentFile(@Nullable DocumentFile paramDocumentFile, Context paramContext, Uri paramUri)
  {
    super(paramDocumentFile);
    this.mContext = paramContext;
    this.mUri = paramUri;
  }

  public boolean canRead()
  {
    return DocumentsContractApi19.canRead(this.mContext, this.mUri);
  }

  public boolean canWrite()
  {
    return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
  }

  public DocumentFile createDirectory(String paramString)
  {
    throw new UnsupportedOperationException();
  }

  public DocumentFile createFile(String paramString1, String paramString2)
  {
    throw new UnsupportedOperationException();
  }

  // ERROR //
  public boolean delete()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 19	android/support/v4/provider/SingleDocumentFile:mContext	Landroid/content/Context;
    //   4: invokevirtual 49	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: aload_0
    //   8: getfield 21	android/support/v4/provider/SingleDocumentFile:mUri	Landroid/net/Uri;
    //   11: invokestatic 55	android/provider/DocumentsContract:deleteDocument	(Landroid/content/ContentResolver;Landroid/net/Uri;)Z
    //   14: istore_1
    //   15: iload_1
    //   16: ireturn
    //   17: iconst_0
    //   18: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	15	17	java/lang/Exception
  }

  public boolean exists()
  {
    return DocumentsContractApi19.exists(this.mContext, this.mUri);
  }

  @Nullable
  public String getName()
  {
    return DocumentsContractApi19.getName(this.mContext, this.mUri);
  }

  @Nullable
  public String getType()
  {
    return DocumentsContractApi19.getType(this.mContext, this.mUri);
  }

  public Uri getUri()
  {
    return this.mUri;
  }

  public boolean isDirectory()
  {
    return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
  }

  public boolean isFile()
  {
    return DocumentsContractApi19.isFile(this.mContext, this.mUri);
  }

  public boolean isVirtual()
  {
    return DocumentsContractApi19.isVirtual(this.mContext, this.mUri);
  }

  public long lastModified()
  {
    return DocumentsContractApi19.lastModified(this.mContext, this.mUri);
  }

  public long length()
  {
    return DocumentsContractApi19.length(this.mContext, this.mUri);
  }

  public DocumentFile[] listFiles()
  {
    throw new UnsupportedOperationException();
  }

  public boolean renameTo(String paramString)
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.provider.SingleDocumentFile
 * JD-Core Version:    0.6.1
 */