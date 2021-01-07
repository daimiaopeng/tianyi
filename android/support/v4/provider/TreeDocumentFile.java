package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
class TreeDocumentFile extends DocumentFile
{
  private Context mContext;
  private Uri mUri;

  TreeDocumentFile(@Nullable DocumentFile paramDocumentFile, Context paramContext, Uri paramUri)
  {
    super(paramDocumentFile);
    this.mContext = paramContext;
    this.mUri = paramUri;
  }

  // ERROR //
  private static void closeQuietly(@Nullable java.lang.AutoCloseable paramAutoCloseable)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +15 -> 16
    //   4: aload_0
    //   5: invokeinterface 33 1 0
    //   10: goto +6 -> 16
    //   13: astore_1
    //   14: aload_1
    //   15: athrow
    //   16: return
    //
    // Exception table:
    //   from	to	target	type
    //   4	10	13	java/lang/RuntimeException
    //   4	10	16	java/lang/Exception
  }

  // ERROR //
  @Nullable
  private static Uri createFile(Context paramContext, Uri paramUri, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 41	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: aload_1
    //   5: aload_2
    //   6: aload_3
    //   7: invokestatic 47	android/provider/DocumentsContract:createDocument	(Landroid/content/ContentResolver;Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri;
    //   10: astore 4
    //   12: aload 4
    //   14: areturn
    //   15: aconst_null
    //   16: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	12	15	java/lang/Exception
  }

  public boolean canRead()
  {
    return DocumentsContractApi19.canRead(this.mContext, this.mUri);
  }

  public boolean canWrite()
  {
    return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
  }

  @Nullable
  public DocumentFile createDirectory(String paramString)
  {
    Uri localUri = createFile(this.mContext, this.mUri, "vnd.android.document/directory", paramString);
    TreeDocumentFile localTreeDocumentFile;
    if (localUri != null)
      localTreeDocumentFile = new TreeDocumentFile(this, this.mContext, localUri);
    else
      localTreeDocumentFile = null;
    return localTreeDocumentFile;
  }

  @Nullable
  public DocumentFile createFile(String paramString1, String paramString2)
  {
    Uri localUri = createFile(this.mContext, this.mUri, paramString1, paramString2);
    TreeDocumentFile localTreeDocumentFile;
    if (localUri != null)
      localTreeDocumentFile = new TreeDocumentFile(this, this.mContext, localUri);
    else
      localTreeDocumentFile = null;
    return localTreeDocumentFile;
  }

  // ERROR //
  public boolean delete()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 19	android/support/v4/provider/TreeDocumentFile:mContext	Landroid/content/Context;
    //   4: invokevirtual 41	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: aload_0
    //   8: getfield 21	android/support/v4/provider/TreeDocumentFile:mUri	Landroid/net/Uri;
    //   11: invokestatic 71	android/provider/DocumentsContract:deleteDocument	(Landroid/content/ContentResolver;Landroid/net/Uri;)Z
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

  // ERROR //
  public DocumentFile[] listFiles()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 19	android/support/v4/provider/TreeDocumentFile:mContext	Landroid/content/Context;
    //   4: invokevirtual 41	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: astore_1
    //   8: aload_0
    //   9: getfield 21	android/support/v4/provider/TreeDocumentFile:mUri	Landroid/net/Uri;
    //   12: aload_0
    //   13: getfield 21	android/support/v4/provider/TreeDocumentFile:mUri	Landroid/net/Uri;
    //   16: invokestatic 107	android/provider/DocumentsContract:getDocumentId	(Landroid/net/Uri;)Ljava/lang/String;
    //   19: invokestatic 111	android/provider/DocumentsContract:buildChildDocumentsUriUsingTree	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   22: astore_2
    //   23: new 113	java/util/ArrayList
    //   26: dup
    //   27: invokespecial 115	java/util/ArrayList:<init>	()V
    //   30: astore_3
    //   31: iconst_0
    //   32: istore 4
    //   34: aconst_null
    //   35: astore 5
    //   37: aload_1
    //   38: aload_2
    //   39: iconst_1
    //   40: anewarray 117	java/lang/String
    //   43: dup
    //   44: iconst_0
    //   45: ldc 119
    //   47: aastore
    //   48: aconst_null
    //   49: aconst_null
    //   50: aconst_null
    //   51: invokevirtual 125	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   54: astore 14
    //   56: aload 14
    //   58: invokeinterface 130 1 0
    //   63: ifeq +30 -> 93
    //   66: aload 14
    //   68: iconst_0
    //   69: invokeinterface 134 2 0
    //   74: astore 15
    //   76: aload_3
    //   77: aload_0
    //   78: getfield 21	android/support/v4/provider/TreeDocumentFile:mUri	Landroid/net/Uri;
    //   81: aload 15
    //   83: invokestatic 137	android/provider/DocumentsContract:buildDocumentUriUsingTree	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   86: invokevirtual 141	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   89: pop
    //   90: goto -34 -> 56
    //   93: aload 14
    //   95: invokestatic 143	android/support/v4/provider/TreeDocumentFile:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   98: goto +69 -> 167
    //   101: astore 13
    //   103: aload 14
    //   105: astore 5
    //   107: goto +123 -> 230
    //   110: astore 6
    //   112: aload 14
    //   114: astore 5
    //   116: goto +10 -> 126
    //   119: astore 13
    //   121: goto +109 -> 230
    //   124: astore 6
    //   126: new 145	java/lang/StringBuilder
    //   129: dup
    //   130: invokespecial 146	java/lang/StringBuilder:<init>	()V
    //   133: astore 7
    //   135: aload 7
    //   137: ldc 148
    //   139: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: pop
    //   143: aload 7
    //   145: aload 6
    //   147: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   150: pop
    //   151: ldc 157
    //   153: aload 7
    //   155: invokevirtual 160	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   158: invokestatic 166	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   161: pop
    //   162: aload 5
    //   164: invokestatic 143	android/support/v4/provider/TreeDocumentFile:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   167: aload_3
    //   168: aload_3
    //   169: invokevirtual 170	java/util/ArrayList:size	()I
    //   172: anewarray 172	android/net/Uri
    //   175: invokevirtual 176	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   178: checkcast 178	[Landroid/net/Uri;
    //   181: astore 11
    //   183: aload 11
    //   185: arraylength
    //   186: anewarray 4	android/support/v4/provider/DocumentFile
    //   189: astore 12
    //   191: iload 4
    //   193: aload 11
    //   195: arraylength
    //   196: if_icmpge +31 -> 227
    //   199: aload 12
    //   201: iload 4
    //   203: new 2	android/support/v4/provider/TreeDocumentFile
    //   206: dup
    //   207: aload_0
    //   208: aload_0
    //   209: getfield 19	android/support/v4/provider/TreeDocumentFile:mContext	Landroid/content/Context;
    //   212: aload 11
    //   214: iload 4
    //   216: aaload
    //   217: invokespecial 65	android/support/v4/provider/TreeDocumentFile:<init>	(Landroid/support/v4/provider/DocumentFile;Landroid/content/Context;Landroid/net/Uri;)V
    //   220: aastore
    //   221: iinc 4 1
    //   224: goto -33 -> 191
    //   227: aload 12
    //   229: areturn
    //   230: aload 5
    //   232: invokestatic 143	android/support/v4/provider/TreeDocumentFile:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   235: aload 13
    //   237: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   56	90	101	finally
    //   56	90	110	java/lang/Exception
    //   37	56	119	finally
    //   126	162	119	finally
    //   37	56	124	java/lang/Exception
  }

  // ERROR //
  public boolean renameTo(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 19	android/support/v4/provider/TreeDocumentFile:mContext	Landroid/content/Context;
    //   4: invokevirtual 41	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: aload_0
    //   8: getfield 21	android/support/v4/provider/TreeDocumentFile:mUri	Landroid/net/Uri;
    //   11: aload_1
    //   12: invokestatic 184	android/provider/DocumentsContract:renameDocument	(Landroid/content/ContentResolver;Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   15: astore_2
    //   16: aload_2
    //   17: ifnull +10 -> 27
    //   20: aload_0
    //   21: aload_2
    //   22: putfield 21	android/support/v4/provider/TreeDocumentFile:mUri	Landroid/net/Uri;
    //   25: iconst_1
    //   26: ireturn
    //   27: iconst_0
    //   28: ireturn
    //   29: iconst_0
    //   30: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	25	29	java/lang/Exception
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.provider.TreeDocumentFile
 * JD-Core Version:    0.6.1
 */