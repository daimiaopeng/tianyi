package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import android.support.v4.provider.FontsContractCompat.FontInfo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
class TypefaceCompatBaseImpl
{
  private static final String CACHE_FILE_PREFIX = "cached_font_";
  private static final String TAG = "TypefaceCompatBaseImpl";

  private FontResourcesParserCompat.FontFileResourceEntry findBestEntry(FontResourcesParserCompat.FontFamilyFilesResourceEntry paramFontFamilyFilesResourceEntry, int paramInt)
  {
    return (FontResourcesParserCompat.FontFileResourceEntry)findBestFont(paramFontFamilyFilesResourceEntry.getEntries(), paramInt, new StyleExtractor()
    {
      public int getWeight(FontResourcesParserCompat.FontFileResourceEntry paramAnonymousFontFileResourceEntry)
      {
        return paramAnonymousFontFileResourceEntry.getWeight();
      }

      public boolean isItalic(FontResourcesParserCompat.FontFileResourceEntry paramAnonymousFontFileResourceEntry)
      {
        return paramAnonymousFontFileResourceEntry.isItalic();
      }
    });
  }

  private static <T> T findBestFont(T[] paramArrayOfT, int paramInt, StyleExtractor<T> paramStyleExtractor)
  {
    int i;
    if ((paramInt & 0x1) == 0)
      i = 400;
    else
      i = 700;
    int j;
    if ((paramInt & 0x2) != 0)
      j = 1;
    else
      j = 0;
    int k = paramArrayOfT.length;
    Object localObject = null;
    int m = 0;
    int n = 2147483647;
    while (m < k)
    {
      T ? = paramArrayOfT[m];
      int i1 = 2 * Math.abs(paramStyleExtractor.getWeight(?) - i);
      int i2;
      if (paramStyleExtractor.isItalic(?) == j)
        i2 = 0;
      else
        i2 = 1;
      int i3 = i1 + i2;
      if ((localObject == null) || (n > i3))
      {
        localObject = ?;
        n = i3;
      }
      m++;
    }
    return localObject;
  }

  @Nullable
  public Typeface createFromFontFamilyFilesResourceEntry(Context paramContext, FontResourcesParserCompat.FontFamilyFilesResourceEntry paramFontFamilyFilesResourceEntry, Resources paramResources, int paramInt)
  {
    FontResourcesParserCompat.FontFileResourceEntry localFontFileResourceEntry = findBestEntry(paramFontFamilyFilesResourceEntry, paramInt);
    if (localFontFileResourceEntry == null)
      return null;
    return TypefaceCompat.createFromResourcesFontFile(paramContext, paramResources, localFontFileResourceEntry.getResourceId(), localFontFileResourceEntry.getFileName(), paramInt);
  }

  // ERROR //
  public Typeface createFromFontInfo(Context paramContext, @Nullable android.os.CancellationSignal paramCancellationSignal, @android.support.annotation.NonNull FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt)
  {
    // Byte code:
    //   0: aload_3
    //   1: arraylength
    //   2: istore 5
    //   4: aconst_null
    //   5: astore 6
    //   7: iload 5
    //   9: iconst_1
    //   10: if_icmpge +5 -> 15
    //   13: aconst_null
    //   14: areturn
    //   15: aload_0
    //   16: aload_3
    //   17: iload 4
    //   19: invokevirtual 83	android/support/v4/graphics/TypefaceCompatBaseImpl:findBestInfo	([Landroid/support/v4/provider/FontsContractCompat$FontInfo;I)Landroid/support/v4/provider/FontsContractCompat$FontInfo;
    //   22: astore 7
    //   24: aload_1
    //   25: invokevirtual 89	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   28: aload 7
    //   30: invokevirtual 95	android/support/v4/provider/FontsContractCompat$FontInfo:getUri	()Landroid/net/Uri;
    //   33: invokevirtual 101	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   36: astore 8
    //   38: aload_0
    //   39: aload_1
    //   40: aload 8
    //   42: invokevirtual 105	android/support/v4/graphics/TypefaceCompatBaseImpl:createFromInputStream	(Landroid/content/Context;Ljava/io/InputStream;)Landroid/graphics/Typeface;
    //   45: astore 11
    //   47: aload 8
    //   49: invokestatic 111	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   52: aload 11
    //   54: areturn
    //   55: astore 9
    //   57: aload 8
    //   59: astore 6
    //   61: goto +5 -> 66
    //   64: astore 9
    //   66: aload 6
    //   68: invokestatic 111	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   71: aload 9
    //   73: athrow
    //   74: aconst_null
    //   75: astore 8
    //   77: goto +4 -> 81
    //   80: pop
    //   81: aload 8
    //   83: invokestatic 111	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   86: aconst_null
    //   87: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   38	47	55	finally
    //   24	38	64	finally
    //   24	38	74	java/io/IOException
    //   38	47	80	java/io/IOException
  }

  // ERROR //
  protected Typeface createFromInputStream(Context paramContext, java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 117	android/support/v4/graphics/TypefaceCompatUtil:getTempFile	(Landroid/content/Context;)Ljava/io/File;
    //   4: astore_3
    //   5: aload_3
    //   6: ifnonnull +5 -> 11
    //   9: aconst_null
    //   10: areturn
    //   11: aload_3
    //   12: aload_2
    //   13: invokestatic 121	android/support/v4/graphics/TypefaceCompatUtil:copyToFile	(Ljava/io/File;Ljava/io/InputStream;)Z
    //   16: istore 7
    //   18: iload 7
    //   20: ifne +10 -> 30
    //   23: aload_3
    //   24: invokevirtual 127	java/io/File:delete	()Z
    //   27: pop
    //   28: aconst_null
    //   29: areturn
    //   30: aload_3
    //   31: invokevirtual 130	java/io/File:getPath	()Ljava/lang/String;
    //   34: invokestatic 136	android/graphics/Typeface:createFromFile	(Ljava/lang/String;)Landroid/graphics/Typeface;
    //   37: astore 8
    //   39: aload_3
    //   40: invokevirtual 127	java/io/File:delete	()Z
    //   43: pop
    //   44: aload 8
    //   46: areturn
    //   47: astore 5
    //   49: aload_3
    //   50: invokevirtual 127	java/io/File:delete	()Z
    //   53: pop
    //   54: aload 5
    //   56: athrow
    //   57: aload_3
    //   58: invokevirtual 127	java/io/File:delete	()Z
    //   61: pop
    //   62: aconst_null
    //   63: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   11	18	47	finally
    //   30	39	47	finally
    //   11	18	57	java/lang/RuntimeException
    //   30	39	57	java/lang/RuntimeException
  }

  // ERROR //
  @Nullable
  public Typeface createFromResourcesFontFile(Context paramContext, Resources paramResources, int paramInt1, String paramString, int paramInt2)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 117	android/support/v4/graphics/TypefaceCompatUtil:getTempFile	(Landroid/content/Context;)Ljava/io/File;
    //   4: astore 6
    //   6: aload 6
    //   8: ifnonnull +5 -> 13
    //   11: aconst_null
    //   12: areturn
    //   13: aload 6
    //   15: aload_2
    //   16: iload_3
    //   17: invokestatic 139	android/support/v4/graphics/TypefaceCompatUtil:copyToFile	(Ljava/io/File;Landroid/content/res/Resources;I)Z
    //   20: istore 10
    //   22: iload 10
    //   24: ifne +11 -> 35
    //   27: aload 6
    //   29: invokevirtual 127	java/io/File:delete	()Z
    //   32: pop
    //   33: aconst_null
    //   34: areturn
    //   35: aload 6
    //   37: invokevirtual 130	java/io/File:getPath	()Ljava/lang/String;
    //   40: invokestatic 136	android/graphics/Typeface:createFromFile	(Ljava/lang/String;)Landroid/graphics/Typeface;
    //   43: astore 11
    //   45: aload 6
    //   47: invokevirtual 127	java/io/File:delete	()Z
    //   50: pop
    //   51: aload 11
    //   53: areturn
    //   54: astore 8
    //   56: aload 6
    //   58: invokevirtual 127	java/io/File:delete	()Z
    //   61: pop
    //   62: aload 8
    //   64: athrow
    //   65: aload 6
    //   67: invokevirtual 127	java/io/File:delete	()Z
    //   70: pop
    //   71: aconst_null
    //   72: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   13	22	54	finally
    //   35	45	54	finally
    //   13	22	65	java/lang/RuntimeException
    //   35	45	65	java/lang/RuntimeException
  }

  protected FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt)
  {
    return (FontsContractCompat.FontInfo)findBestFont(paramArrayOfFontInfo, paramInt, new StyleExtractor()
    {
      public int getWeight(FontsContractCompat.FontInfo paramAnonymousFontInfo)
      {
        return paramAnonymousFontInfo.getWeight();
      }

      public boolean isItalic(FontsContractCompat.FontInfo paramAnonymousFontInfo)
      {
        return paramAnonymousFontInfo.isItalic();
      }
    });
  }

  private static abstract interface StyleExtractor<T>
  {
    public abstract int getWeight(T paramT);

    public abstract boolean isItalic(T paramT);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.TypefaceCompatBaseImpl
 * JD-Core Version:    0.6.1
 */