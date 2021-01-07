package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

@RequiresApi(19)
class DocumentsContractApi19
{
  private static final int FLAG_VIRTUAL_DOCUMENT = 512;
  private static final String TAG = "DocumentFile";

  public static boolean canRead(Context paramContext, Uri paramUri)
  {
    if (paramContext.checkCallingOrSelfUriPermission(paramUri, 1) != 0)
      return false;
    return !TextUtils.isEmpty(getRawType(paramContext, paramUri));
  }

  public static boolean canWrite(Context paramContext, Uri paramUri)
  {
    if (paramContext.checkCallingOrSelfUriPermission(paramUri, 2) != 0)
      return false;
    String str = getRawType(paramContext, paramUri);
    int i = queryForInt(paramContext, paramUri, "flags", 0);
    if (TextUtils.isEmpty(str))
      return false;
    if ((i & 0x4) != 0)
      return true;
    if (("vnd.android.document/directory".equals(str)) && ((i & 0x8) != 0))
      return true;
    return (!TextUtils.isEmpty(str)) && ((i & 0x2) != 0);
  }

  // ERROR //
  private static void closeQuietly(@Nullable java.lang.AutoCloseable paramAutoCloseable)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +15 -> 16
    //   4: aload_0
    //   5: invokeinterface 63 1 0
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
  public static boolean exists(Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 68	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore_2
    //   5: iconst_1
    //   6: istore_3
    //   7: aconst_null
    //   8: astore 4
    //   10: iload_3
    //   11: anewarray 47	java/lang/String
    //   14: astore 11
    //   16: aload 11
    //   18: iconst_0
    //   19: ldc 70
    //   21: aastore
    //   22: aload_2
    //   23: aload_1
    //   24: aload 11
    //   26: aconst_null
    //   27: aconst_null
    //   28: aconst_null
    //   29: invokevirtual 76	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   32: astore 12
    //   34: aload 12
    //   36: invokeinterface 82 1 0
    //   41: istore 13
    //   43: iload 13
    //   45: ifle +6 -> 51
    //   48: goto +5 -> 53
    //   51: iconst_0
    //   52: istore_3
    //   53: aload 12
    //   55: invokestatic 84	android/support/v4/provider/DocumentsContractApi19:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   58: iload_3
    //   59: ireturn
    //   60: astore 10
    //   62: aload 12
    //   64: astore 4
    //   66: goto +62 -> 128
    //   69: astore 5
    //   71: aload 12
    //   73: astore 4
    //   75: goto +10 -> 85
    //   78: astore 10
    //   80: goto +48 -> 128
    //   83: astore 5
    //   85: new 86	java/lang/StringBuilder
    //   88: dup
    //   89: invokespecial 87	java/lang/StringBuilder:<init>	()V
    //   92: astore 6
    //   94: aload 6
    //   96: ldc 89
    //   98: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: aload 6
    //   104: aload 5
    //   106: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   109: pop
    //   110: ldc 14
    //   112: aload 6
    //   114: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: invokestatic 106	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   120: pop
    //   121: aload 4
    //   123: invokestatic 84	android/support/v4/provider/DocumentsContractApi19:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   126: iconst_0
    //   127: ireturn
    //   128: aload 4
    //   130: invokestatic 84	android/support/v4/provider/DocumentsContractApi19:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   133: aload 10
    //   135: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   34	43	60	finally
    //   34	43	69	java/lang/Exception
    //   10	34	78	finally
    //   85	121	78	finally
    //   10	34	83	java/lang/Exception
  }

  public static long getFlags(Context paramContext, Uri paramUri)
  {
    return queryForLong(paramContext, paramUri, "flags", 0L);
  }

  @Nullable
  public static String getName(Context paramContext, Uri paramUri)
  {
    return queryForString(paramContext, paramUri, "_display_name", null);
  }

  @Nullable
  private static String getRawType(Context paramContext, Uri paramUri)
  {
    return queryForString(paramContext, paramUri, "mime_type", null);
  }

  @Nullable
  public static String getType(Context paramContext, Uri paramUri)
  {
    String str = getRawType(paramContext, paramUri);
    if ("vnd.android.document/directory".equals(str))
      return null;
    return str;
  }

  public static boolean isDirectory(Context paramContext, Uri paramUri)
  {
    return "vnd.android.document/directory".equals(getRawType(paramContext, paramUri));
  }

  public static boolean isFile(Context paramContext, Uri paramUri)
  {
    String str = getRawType(paramContext, paramUri);
    return (!"vnd.android.document/directory".equals(str)) && (!TextUtils.isEmpty(str));
  }

  public static boolean isVirtual(Context paramContext, Uri paramUri)
  {
    if (!DocumentsContract.isDocumentUri(paramContext, paramUri))
      return false;
    boolean bool1 = (0x200 & getFlags(paramContext, paramUri)) < 0L;
    boolean bool2 = false;
    if (bool1)
      bool2 = true;
    return bool2;
  }

  public static long lastModified(Context paramContext, Uri paramUri)
  {
    return queryForLong(paramContext, paramUri, "last_modified", 0L);
  }

  public static long length(Context paramContext, Uri paramUri)
  {
    return queryForLong(paramContext, paramUri, "_size", 0L);
  }

  private static int queryForInt(Context paramContext, Uri paramUri, String paramString, int paramInt)
  {
    return (int)queryForLong(paramContext, paramUri, paramString, paramInt);
  }

  // ERROR //
  private static long queryForLong(Context paramContext, Uri paramUri, String paramString, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 68	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore 5
    //   6: aconst_null
    //   7: astore 6
    //   9: aload 5
    //   11: aload_1
    //   12: iconst_1
    //   13: anewarray 47	java/lang/String
    //   16: dup
    //   17: iconst_0
    //   18: aload_2
    //   19: aastore
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: invokevirtual 76	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   26: astore 13
    //   28: aload 13
    //   30: invokeinterface 144 1 0
    //   35: ifeq +32 -> 67
    //   38: aload 13
    //   40: iconst_0
    //   41: invokeinterface 148 2 0
    //   46: ifne +21 -> 67
    //   49: aload 13
    //   51: iconst_0
    //   52: invokeinterface 152 2 0
    //   57: lstore 14
    //   59: aload 13
    //   61: invokestatic 84	android/support/v4/provider/DocumentsContractApi19:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   64: lload 14
    //   66: lreturn
    //   67: aload 13
    //   69: invokestatic 84	android/support/v4/provider/DocumentsContractApi19:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   72: lload_3
    //   73: lreturn
    //   74: astore 12
    //   76: aload 13
    //   78: astore 6
    //   80: goto +62 -> 142
    //   83: astore 7
    //   85: aload 13
    //   87: astore 6
    //   89: goto +10 -> 99
    //   92: astore 12
    //   94: goto +48 -> 142
    //   97: astore 7
    //   99: new 86	java/lang/StringBuilder
    //   102: dup
    //   103: invokespecial 87	java/lang/StringBuilder:<init>	()V
    //   106: astore 8
    //   108: aload 8
    //   110: ldc 89
    //   112: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: pop
    //   116: aload 8
    //   118: aload 7
    //   120: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   123: pop
    //   124: ldc 14
    //   126: aload 8
    //   128: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   131: invokestatic 106	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   134: pop
    //   135: aload 6
    //   137: invokestatic 84	android/support/v4/provider/DocumentsContractApi19:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   140: lload_3
    //   141: lreturn
    //   142: aload 6
    //   144: invokestatic 84	android/support/v4/provider/DocumentsContractApi19:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   147: aload 12
    //   149: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   28	59	74	finally
    //   28	59	83	java/lang/Exception
    //   9	28	92	finally
    //   99	135	92	finally
    //   9	28	97	java/lang/Exception
  }

  // ERROR //
  @Nullable
  private static String queryForString(Context paramContext, Uri paramUri, String paramString1, @Nullable String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 68	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore 4
    //   6: aconst_null
    //   7: astore 5
    //   9: aload 4
    //   11: aload_1
    //   12: iconst_1
    //   13: anewarray 47	java/lang/String
    //   16: dup
    //   17: iconst_0
    //   18: aload_2
    //   19: aastore
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: invokevirtual 76	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   26: astore 12
    //   28: aload 12
    //   30: invokeinterface 144 1 0
    //   35: ifeq +32 -> 67
    //   38: aload 12
    //   40: iconst_0
    //   41: invokeinterface 148 2 0
    //   46: ifne +21 -> 67
    //   49: aload 12
    //   51: iconst_0
    //   52: invokeinterface 156 2 0
    //   57: astore 13
    //   59: aload 12
    //   61: invokestatic 84	android/support/v4/provider/DocumentsContractApi19:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   64: aload 13
    //   66: areturn
    //   67: aload 12
    //   69: invokestatic 84	android/support/v4/provider/DocumentsContractApi19:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   72: aload_3
    //   73: areturn
    //   74: astore 11
    //   76: aload 12
    //   78: astore 5
    //   80: goto +62 -> 142
    //   83: astore 6
    //   85: aload 12
    //   87: astore 5
    //   89: goto +10 -> 99
    //   92: astore 11
    //   94: goto +48 -> 142
    //   97: astore 6
    //   99: new 86	java/lang/StringBuilder
    //   102: dup
    //   103: invokespecial 87	java/lang/StringBuilder:<init>	()V
    //   106: astore 7
    //   108: aload 7
    //   110: ldc 89
    //   112: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: pop
    //   116: aload 7
    //   118: aload 6
    //   120: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   123: pop
    //   124: ldc 14
    //   126: aload 7
    //   128: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   131: invokestatic 106	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   134: pop
    //   135: aload 5
    //   137: invokestatic 84	android/support/v4/provider/DocumentsContractApi19:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   140: aload_3
    //   141: areturn
    //   142: aload 5
    //   144: invokestatic 84	android/support/v4/provider/DocumentsContractApi19:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   147: aload 11
    //   149: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   28	59	74	finally
    //   28	59	83	java/lang/Exception
    //   9	28	92	finally
    //   99	135	92	finally
    //   9	28	97	java/lang/Exception
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.provider.DocumentsContractApi19
 * JD-Core Version:    0.6.1
 */