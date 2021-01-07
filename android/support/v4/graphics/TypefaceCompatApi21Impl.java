package android.support.v4.graphics;

import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;

@RequiresApi(21)
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl
{
  private static final String TAG = "TypefaceCompatApi21Impl";

  // ERROR //
  private java.io.File getFile(android.os.ParcelFileDescriptor paramParcelFileDescriptor)
  {
    // Byte code:
    //   0: new 24	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 25	java/lang/StringBuilder:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: ldc 27
    //   11: invokevirtual 31	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: pop
    //   15: aload_2
    //   16: aload_1
    //   17: invokevirtual 37	android/os/ParcelFileDescriptor:getFd	()I
    //   20: invokevirtual 40	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: aload_2
    //   25: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   28: invokestatic 50	android/system/Os:readlink	(Ljava/lang/String;)Ljava/lang/String;
    //   31: astore 5
    //   33: aload 5
    //   35: invokestatic 54	android/system/Os:stat	(Ljava/lang/String;)Landroid/system/StructStat;
    //   38: getfield 60	android/system/StructStat:st_mode	I
    //   41: invokestatic 66	android/system/OsConstants:S_ISREG	(I)Z
    //   44: ifeq +17 -> 61
    //   47: new 68	java/io/File
    //   50: dup
    //   51: aload 5
    //   53: invokespecial 71	java/io/File:<init>	(Ljava/lang/String;)V
    //   56: astore 6
    //   58: aload 6
    //   60: areturn
    //   61: aconst_null
    //   62: areturn
    //   63: aconst_null
    //   64: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	58	63	android/system/ErrnoException
  }

  // ERROR //
  public android.graphics.Typeface createFromFontInfo(android.content.Context paramContext, android.os.CancellationSignal paramCancellationSignal, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt)
  {
    // Byte code:
    //   0: aload_3
    //   1: arraylength
    //   2: iconst_1
    //   3: if_icmpge +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: aload_0
    //   9: aload_3
    //   10: iload 4
    //   12: invokevirtual 82	android/support/v4/graphics/TypefaceCompatApi21Impl:findBestInfo	([Landroid/support/v4/provider/FontsContractCompat$FontInfo;I)Landroid/support/v4/provider/FontsContractCompat$FontInfo;
    //   15: astore 5
    //   17: aload_1
    //   18: invokevirtual 88	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   21: astore 6
    //   23: aload 6
    //   25: aload 5
    //   27: invokevirtual 94	android/support/v4/provider/FontsContractCompat$FontInfo:getUri	()Landroid/net/Uri;
    //   30: ldc 96
    //   32: aload_2
    //   33: invokevirtual 102	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   36: astore 7
    //   38: aload_0
    //   39: aload 7
    //   41: invokespecial 104	android/support/v4/graphics/TypefaceCompatApi21Impl:getFile	(Landroid/os/ParcelFileDescriptor;)Ljava/io/File;
    //   44: astore 13
    //   46: aload 13
    //   48: ifnull +34 -> 82
    //   51: aload 13
    //   53: invokevirtual 108	java/io/File:canRead	()Z
    //   56: ifne +6 -> 62
    //   59: goto +23 -> 82
    //   62: aload 13
    //   64: invokestatic 114	android/graphics/Typeface:createFromFile	(Ljava/io/File;)Landroid/graphics/Typeface;
    //   67: astore 21
    //   69: aload 7
    //   71: ifnull +8 -> 79
    //   74: aload 7
    //   76: invokevirtual 117	android/os/ParcelFileDescriptor:close	()V
    //   79: aload 21
    //   81: areturn
    //   82: new 119	java/io/FileInputStream
    //   85: dup
    //   86: aload 7
    //   88: invokevirtual 123	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   91: invokespecial 126	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   94: astore 14
    //   96: aload_0
    //   97: aload_1
    //   98: aload 14
    //   100: invokespecial 130	android/support/v4/graphics/TypefaceCompatBaseImpl:createFromInputStream	(Landroid/content/Context;Ljava/io/InputStream;)Landroid/graphics/Typeface;
    //   103: astore 20
    //   105: aload 14
    //   107: ifnull +8 -> 115
    //   110: aload 14
    //   112: invokevirtual 131	java/io/FileInputStream:close	()V
    //   115: aload 7
    //   117: ifnull +8 -> 125
    //   120: aload 7
    //   122: invokevirtual 117	android/os/ParcelFileDescriptor:close	()V
    //   125: aload 20
    //   127: areturn
    //   128: astore 18
    //   130: aconst_null
    //   131: astore 17
    //   133: goto +18 -> 151
    //   136: astore 15
    //   138: aload 15
    //   140: athrow
    //   141: astore 16
    //   143: aload 15
    //   145: astore 17
    //   147: aload 16
    //   149: astore 18
    //   151: aload 14
    //   153: ifnull +33 -> 186
    //   156: aload 17
    //   158: ifnull +23 -> 181
    //   161: aload 14
    //   163: invokevirtual 131	java/io/FileInputStream:close	()V
    //   166: goto +20 -> 186
    //   169: astore 19
    //   171: aload 17
    //   173: aload 19
    //   175: invokevirtual 135	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   178: goto +8 -> 186
    //   181: aload 14
    //   183: invokevirtual 131	java/io/FileInputStream:close	()V
    //   186: aload 18
    //   188: athrow
    //   189: astore 11
    //   191: aconst_null
    //   192: astore 10
    //   194: goto +18 -> 212
    //   197: astore 8
    //   199: aload 8
    //   201: athrow
    //   202: astore 9
    //   204: aload 8
    //   206: astore 10
    //   208: aload 9
    //   210: astore 11
    //   212: aload 7
    //   214: ifnull +33 -> 247
    //   217: aload 10
    //   219: ifnull +23 -> 242
    //   222: aload 7
    //   224: invokevirtual 117	android/os/ParcelFileDescriptor:close	()V
    //   227: goto +20 -> 247
    //   230: astore 12
    //   232: aload 10
    //   234: aload 12
    //   236: invokevirtual 135	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   239: goto +8 -> 247
    //   242: aload 7
    //   244: invokevirtual 117	android/os/ParcelFileDescriptor:close	()V
    //   247: aload 11
    //   249: athrow
    //   250: aconst_null
    //   251: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   96	105	128	finally
    //   96	105	136	java/lang/Throwable
    //   138	141	141	finally
    //   161	166	169	java/lang/Throwable
    //   38	69	189	finally
    //   82	96	189	finally
    //   110	115	189	finally
    //   161	166	189	finally
    //   171	189	189	finally
    //   38	69	197	java/lang/Throwable
    //   82	96	197	java/lang/Throwable
    //   110	115	197	java/lang/Throwable
    //   171	189	197	java/lang/Throwable
    //   199	202	202	finally
    //   222	227	230	java/lang/Throwable
    //   23	38	250	java/io/IOException
    //   74	79	250	java/io/IOException
    //   120	125	250	java/io/IOException
    //   222	227	250	java/io/IOException
    //   232	250	250	java/io/IOException
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.TypefaceCompatApi21Impl
 * JD-Core Version:    0.6.1
 */