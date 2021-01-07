package android.support.v4.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

public class FileProvider extends ContentProvider
{
  private static final String ATTR_NAME = "name";
  private static final String ATTR_PATH = "path";
  private static final String[] COLUMNS = { "_display_name", "_size" };
  private static final File DEVICE_ROOT = new File("/");
  private static final String META_DATA_FILE_PROVIDER_PATHS = "android.support.FILE_PROVIDER_PATHS";
  private static final String TAG_CACHE_PATH = "cache-path";
  private static final String TAG_EXTERNAL = "external-path";
  private static final String TAG_EXTERNAL_CACHE = "external-cache-path";
  private static final String TAG_EXTERNAL_FILES = "external-files-path";
  private static final String TAG_EXTERNAL_MEDIA = "external-media-path";
  private static final String TAG_FILES_PATH = "files-path";
  private static final String TAG_ROOT_PATH = "root-path";

  @GuardedBy("sCache")
  private static HashMap<String, PathStrategy> sCache = new HashMap();
  private PathStrategy mStrategy;

  private static File buildPath(File paramFile, String[] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
    {
      String str = paramArrayOfString[j];
      if (str != null)
        paramFile = new File(paramFile, str);
    }
    return paramFile;
  }

  private static Object[] copyOf(Object[] paramArrayOfObject, int paramInt)
  {
    Object[] arrayOfObject = new Object[paramInt];
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramInt);
    return arrayOfObject;
  }

  private static String[] copyOf(String[] paramArrayOfString, int paramInt)
  {
    String[] arrayOfString = new String[paramInt];
    System.arraycopy(paramArrayOfString, 0, arrayOfString, 0, paramInt);
    return arrayOfString;
  }

  private static PathStrategy getPathStrategy(Context paramContext, String paramString)
  {
    synchronized (sCache)
    {
      PathStrategy localPathStrategy = (PathStrategy)sCache.get(paramString);
      if (localPathStrategy == null)
        try
        {
          localPathStrategy = parsePathStrategy(paramContext, paramString);
          sCache.put(paramString, localPathStrategy);
        }
        catch (XmlPullParserException localXmlPullParserException)
        {
          throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", localXmlPullParserException);
        }
        catch (IOException localIOException)
        {
          throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", localIOException);
        }
      return localPathStrategy;
    }
  }

  public static Uri getUriForFile(@NonNull Context paramContext, @NonNull String paramString, @NonNull File paramFile)
  {
    return getPathStrategy(paramContext, paramString).getUriForFile(paramFile);
  }

  private static int modeToMode(String paramString)
  {
    int i;
    if ("r".equals(paramString))
      i = 268435456;
    else if ((!"w".equals(paramString)) && (!"wt".equals(paramString)))
    {
      if ("wa".equals(paramString))
      {
        i = 704643072;
      }
      else if ("rw".equals(paramString))
      {
        i = 939524096;
      }
      else if ("rwt".equals(paramString))
      {
        i = 1006632960;
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Invalid mode: ");
        localStringBuilder.append(paramString);
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
    }
    else
      i = 738197504;
    return i;
  }

  private static PathStrategy parsePathStrategy(Context paramContext, String paramString)
  {
    SimplePathStrategy localSimplePathStrategy = new SimplePathStrategy(paramString);
    XmlResourceParser localXmlResourceParser = paramContext.getPackageManager().resolveContentProvider(paramString, 128).loadXmlMetaData(paramContext.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
    if (localXmlResourceParser == null)
      throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
    while (true)
    {
      int i = localXmlResourceParser.next();
      if (i == 1)
        break;
      if (i == 2)
      {
        String str1 = localXmlResourceParser.getName();
        String str2 = localXmlResourceParser.getAttributeValue(null, "name");
        String str3 = localXmlResourceParser.getAttributeValue(null, "path");
        File localFile;
        if ("root-path".equals(str1))
        {
          localFile = DEVICE_ROOT;
        }
        else if ("files-path".equals(str1))
        {
          localFile = paramContext.getFilesDir();
        }
        else if ("cache-path".equals(str1))
        {
          localFile = paramContext.getCacheDir();
        }
        else if ("external-path".equals(str1))
        {
          localFile = Environment.getExternalStorageDirectory();
        }
        else if ("external-files-path".equals(str1))
        {
          File[] arrayOfFile3 = ContextCompat.getExternalFilesDirs(paramContext, null);
          int n = arrayOfFile3.length;
          localFile = null;
          if (n > 0)
            localFile = arrayOfFile3[0];
        }
        else if ("external-cache-path".equals(str1))
        {
          File[] arrayOfFile2 = ContextCompat.getExternalCacheDirs(paramContext);
          int m = arrayOfFile2.length;
          localFile = null;
          if (m > 0)
            localFile = arrayOfFile2[0];
        }
        else
        {
          int j = Build.VERSION.SDK_INT;
          localFile = null;
          if (j >= 21)
          {
            boolean bool = "external-media-path".equals(str1);
            localFile = null;
            if (bool)
            {
              File[] arrayOfFile1 = paramContext.getExternalMediaDirs();
              int k = arrayOfFile1.length;
              localFile = null;
              if (k > 0)
                localFile = arrayOfFile1[0];
            }
          }
        }
        if (localFile != null)
          localSimplePathStrategy.addRoot(str2, buildPath(localFile, new String[] { str3 }));
      }
    }
    return localSimplePathStrategy;
  }

  public void attachInfo(@NonNull Context paramContext, @NonNull ProviderInfo paramProviderInfo)
  {
    super.attachInfo(paramContext, paramProviderInfo);
    if (paramProviderInfo.exported)
      throw new SecurityException("Provider must not be exported");
    if (!paramProviderInfo.grantUriPermissions)
      throw new SecurityException("Provider must grant uri permissions");
    this.mStrategy = getPathStrategy(paramContext, paramProviderInfo.authority);
  }

  public int delete(@NonNull Uri paramUri, @Nullable String paramString, @Nullable String[] paramArrayOfString)
  {
    return this.mStrategy.getFileForUri(paramUri).delete();
  }

  public String getType(@NonNull Uri paramUri)
  {
    File localFile = this.mStrategy.getFileForUri(paramUri);
    int i = localFile.getName().lastIndexOf('.');
    if (i >= 0)
    {
      String str1 = localFile.getName().substring(i + 1);
      String str2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str1);
      if (str2 != null)
        return str2;
    }
    return "application/octet-stream";
  }

  public Uri insert(@NonNull Uri paramUri, ContentValues paramContentValues)
  {
    throw new UnsupportedOperationException("No external inserts");
  }

  public boolean onCreate()
  {
    return true;
  }

  public ParcelFileDescriptor openFile(@NonNull Uri paramUri, @NonNull String paramString)
  {
    return ParcelFileDescriptor.open(this.mStrategy.getFileForUri(paramUri), modeToMode(paramString));
  }

  public Cursor query(@NonNull Uri paramUri, @Nullable String[] paramArrayOfString1, @Nullable String paramString1, @Nullable String[] paramArrayOfString2, @Nullable String paramString2)
  {
    File localFile = this.mStrategy.getFileForUri(paramUri);
    if (paramArrayOfString1 == null)
      paramArrayOfString1 = COLUMNS;
    String[] arrayOfString1 = new String[paramArrayOfString1.length];
    Object[] arrayOfObject1 = new Object[paramArrayOfString1.length];
    int i = paramArrayOfString1.length;
    int j = 0;
    int k = 0;
    while (j < i)
    {
      String str = paramArrayOfString1[j];
      int m;
      if ("_display_name".equals(str))
      {
        arrayOfString1[k] = "_display_name";
        m = k + 1;
        arrayOfObject1[k] = localFile.getName();
      }
      while (true)
      {
        k = m;
        break;
        if (!"_size".equals(str))
          break;
        arrayOfString1[k] = "_size";
        m = k + 1;
        arrayOfObject1[k] = Long.valueOf(localFile.length());
      }
      j++;
    }
    String[] arrayOfString2 = copyOf(arrayOfString1, k);
    Object[] arrayOfObject2 = copyOf(arrayOfObject1, k);
    MatrixCursor localMatrixCursor = new MatrixCursor(arrayOfString2, 1);
    localMatrixCursor.addRow(arrayOfObject2);
    return localMatrixCursor;
  }

  public int update(@NonNull Uri paramUri, ContentValues paramContentValues, @Nullable String paramString, @Nullable String[] paramArrayOfString)
  {
    throw new UnsupportedOperationException("No external updates");
  }

  static abstract interface PathStrategy
  {
    public abstract File getFileForUri(Uri paramUri);

    public abstract Uri getUriForFile(File paramFile);
  }

  static class SimplePathStrategy
    implements FileProvider.PathStrategy
  {
    private final String mAuthority;
    private final HashMap<String, File> mRoots = new HashMap();

    SimplePathStrategy(String paramString)
    {
      this.mAuthority = paramString;
    }

    void addRoot(String paramString, File paramFile)
    {
      if (TextUtils.isEmpty(paramString))
        throw new IllegalArgumentException("Name must not be empty");
      try
      {
        File localFile = paramFile.getCanonicalFile();
        this.mRoots.put(paramString, localFile);
        return;
      }
      catch (IOException localIOException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Failed to resolve canonical path for ");
        localStringBuilder.append(paramFile);
        throw new IllegalArgumentException(localStringBuilder.toString(), localIOException);
      }
    }

    // ERROR //
    public File getFileForUri(Uri paramUri)
    {
      // Byte code:
      //   0: aload_1
      //   1: invokevirtual 75	android/net/Uri:getEncodedPath	()Ljava/lang/String;
      //   4: astore_2
      //   5: aload_2
      //   6: bipush 47
      //   8: iconst_1
      //   9: invokevirtual 81	java/lang/String:indexOf	(II)I
      //   12: istore_3
      //   13: aload_2
      //   14: iconst_1
      //   15: iload_3
      //   16: invokevirtual 85	java/lang/String:substring	(II)Ljava/lang/String;
      //   19: invokestatic 89	android/net/Uri:decode	(Ljava/lang/String;)Ljava/lang/String;
      //   22: astore 4
      //   24: aload_2
      //   25: iload_3
      //   26: iconst_1
      //   27: iadd
      //   28: invokevirtual 92	java/lang/String:substring	(I)Ljava/lang/String;
      //   31: invokestatic 89	android/net/Uri:decode	(Ljava/lang/String;)Ljava/lang/String;
      //   34: astore 5
      //   36: aload_0
      //   37: getfield 21	android/support/v4/content/FileProvider$SimplePathStrategy:mRoots	Ljava/util/HashMap;
      //   40: aload 4
      //   42: invokevirtual 96	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   45: checkcast 41	java/io/File
      //   48: astore 6
      //   50: aload 6
      //   52: ifnonnull +40 -> 92
      //   55: new 51	java/lang/StringBuilder
      //   58: dup
      //   59: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   62: astore 7
      //   64: aload 7
      //   66: ldc 98
      //   68: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   71: pop
      //   72: aload 7
      //   74: aload_1
      //   75: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   78: pop
      //   79: new 35	java/lang/IllegalArgumentException
      //   82: dup
      //   83: aload 7
      //   85: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   88: invokespecial 39	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   91: athrow
      //   92: new 41	java/io/File
      //   95: dup
      //   96: aload 6
      //   98: aload 5
      //   100: invokespecial 101	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   103: astore 10
      //   105: aload 10
      //   107: invokevirtual 45	java/io/File:getCanonicalFile	()Ljava/io/File;
      //   110: astore 14
      //   112: aload 14
      //   114: invokevirtual 104	java/io/File:getPath	()Ljava/lang/String;
      //   117: aload 6
      //   119: invokevirtual 104	java/io/File:getPath	()Ljava/lang/String;
      //   122: invokevirtual 108	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   125: ifne +13 -> 138
      //   128: new 110	java/lang/SecurityException
      //   131: dup
      //   132: ldc 112
      //   134: invokespecial 113	java/lang/SecurityException:<init>	(Ljava/lang/String;)V
      //   137: athrow
      //   138: aload 14
      //   140: areturn
      //   141: new 51	java/lang/StringBuilder
      //   144: dup
      //   145: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   148: astore 11
      //   150: aload 11
      //   152: ldc 54
      //   154: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   157: pop
      //   158: aload 11
      //   160: aload 10
      //   162: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   165: pop
      //   166: new 35	java/lang/IllegalArgumentException
      //   169: dup
      //   170: aload 11
      //   172: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   175: invokespecial 39	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   178: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   105	112	141	java/io/IOException
    }

    // ERROR //
    public Uri getUriForFile(File paramFile)
    {
      // Byte code:
      //   0: aload_1
      //   1: invokevirtual 118	java/io/File:getCanonicalPath	()Ljava/lang/String;
      //   4: astore 5
      //   6: aconst_null
      //   7: astore 6
      //   9: aload_0
      //   10: getfield 21	android/support/v4/content/FileProvider$SimplePathStrategy:mRoots	Ljava/util/HashMap;
      //   13: invokevirtual 122	java/util/HashMap:entrySet	()Ljava/util/Set;
      //   16: invokeinterface 128 1 0
      //   21: astore 7
      //   23: aload 7
      //   25: invokeinterface 134 1 0
      //   30: ifeq +76 -> 106
      //   33: aload 7
      //   35: invokeinterface 138 1 0
      //   40: checkcast 140	java/util/Map$Entry
      //   43: astore 18
      //   45: aload 18
      //   47: invokeinterface 143 1 0
      //   52: checkcast 41	java/io/File
      //   55: invokevirtual 104	java/io/File:getPath	()Ljava/lang/String;
      //   58: astore 19
      //   60: aload 5
      //   62: aload 19
      //   64: invokevirtual 108	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   67: ifeq -44 -> 23
      //   70: aload 6
      //   72: ifnull +27 -> 99
      //   75: aload 19
      //   77: invokevirtual 147	java/lang/String:length	()I
      //   80: aload 6
      //   82: invokeinterface 143 1 0
      //   87: checkcast 41	java/io/File
      //   90: invokevirtual 104	java/io/File:getPath	()Ljava/lang/String;
      //   93: invokevirtual 147	java/lang/String:length	()I
      //   96: if_icmple -73 -> 23
      //   99: aload 18
      //   101: astore 6
      //   103: goto -80 -> 23
      //   106: aload 6
      //   108: ifnonnull +41 -> 149
      //   111: new 51	java/lang/StringBuilder
      //   114: dup
      //   115: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   118: astore 8
      //   120: aload 8
      //   122: ldc 149
      //   124: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   127: pop
      //   128: aload 8
      //   130: aload 5
      //   132: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   135: pop
      //   136: new 35	java/lang/IllegalArgumentException
      //   139: dup
      //   140: aload 8
      //   142: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   145: invokespecial 39	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   148: athrow
      //   149: aload 6
      //   151: invokeinterface 143 1 0
      //   156: checkcast 41	java/io/File
      //   159: invokevirtual 104	java/io/File:getPath	()Ljava/lang/String;
      //   162: astore 11
      //   164: aload 11
      //   166: ldc 151
      //   168: invokevirtual 154	java/lang/String:endsWith	(Ljava/lang/String;)Z
      //   171: ifeq +18 -> 189
      //   174: aload 5
      //   176: aload 11
      //   178: invokevirtual 147	java/lang/String:length	()I
      //   181: invokevirtual 92	java/lang/String:substring	(I)Ljava/lang/String;
      //   184: astore 12
      //   186: goto +17 -> 203
      //   189: aload 5
      //   191: iconst_1
      //   192: aload 11
      //   194: invokevirtual 147	java/lang/String:length	()I
      //   197: iadd
      //   198: invokevirtual 92	java/lang/String:substring	(I)Ljava/lang/String;
      //   201: astore 12
      //   203: new 51	java/lang/StringBuilder
      //   206: dup
      //   207: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   210: astore 13
      //   212: aload 13
      //   214: aload 6
      //   216: invokeinterface 157 1 0
      //   221: checkcast 77	java/lang/String
      //   224: invokestatic 160	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
      //   227: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   230: pop
      //   231: aload 13
      //   233: bipush 47
      //   235: invokevirtual 163	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
      //   238: pop
      //   239: aload 13
      //   241: aload 12
      //   243: ldc 151
      //   245: invokestatic 166	android/net/Uri:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
      //   248: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   251: pop
      //   252: aload 13
      //   254: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   257: astore 17
      //   259: new 168	android/net/Uri$Builder
      //   262: dup
      //   263: invokespecial 169	android/net/Uri$Builder:<init>	()V
      //   266: ldc 171
      //   268: invokevirtual 175	android/net/Uri$Builder:scheme	(Ljava/lang/String;)Landroid/net/Uri$Builder;
      //   271: aload_0
      //   272: getfield 23	android/support/v4/content/FileProvider$SimplePathStrategy:mAuthority	Ljava/lang/String;
      //   275: invokevirtual 178	android/net/Uri$Builder:authority	(Ljava/lang/String;)Landroid/net/Uri$Builder;
      //   278: aload 17
      //   280: invokevirtual 181	android/net/Uri$Builder:encodedPath	(Ljava/lang/String;)Landroid/net/Uri$Builder;
      //   283: invokevirtual 185	android/net/Uri$Builder:build	()Landroid/net/Uri;
      //   286: areturn
      //   287: new 51	java/lang/StringBuilder
      //   290: dup
      //   291: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   294: astore_2
      //   295: aload_2
      //   296: ldc 54
      //   298: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   301: pop
      //   302: aload_2
      //   303: aload_1
      //   304: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   307: pop
      //   308: new 35	java/lang/IllegalArgumentException
      //   311: dup
      //   312: aload_2
      //   313: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   316: invokespecial 39	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   319: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   0	6	287	java/io/IOException
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.FileProvider
 * JD-Core Version:    0.6.1
 */