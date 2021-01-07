package android.support.v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

@RequiresApi(26)
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl
{
  private static final String ABORT_CREATION_METHOD = "abortCreation";
  private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
  private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
  private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
  private static final String DEFAULT_FAMILY = "sans-serif";
  private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
  private static final String FREEZE_METHOD = "freeze";
  private static final int RESOLVE_BY_FONT_TABLE = -1;
  private static final String TAG = "TypefaceCompatApi26Impl";
  protected final Method mAbortCreation;
  protected final Method mAddFontFromAssetManager;
  protected final Method mAddFontFromBuffer;
  protected final Method mCreateFromFamiliesWithDefault;
  protected final Class mFontFamily;
  protected final Constructor mFontFamilyCtor;
  protected final Method mFreeze;

  public TypefaceCompatApi26Impl()
  {
    Object localObject = null;
    Constructor localConstructor;
    Method localMethod1;
    Method localMethod2;
    Method localMethod3;
    Method localMethod4;
    Method localMethod5;
    try
    {
      Class localClass = obtainFontFamily();
      localConstructor = obtainFontFamilyCtor(localClass);
      localMethod1 = obtainAddFontFromAssetManagerMethod(localClass);
      localMethod2 = obtainAddFontFromBufferMethod(localClass);
      localMethod3 = obtainFreezeMethod(localClass);
      localMethod4 = obtainAbortCreationMethod(localClass);
      localMethod5 = obtainCreateFromFamiliesWithDefaultMethod(localClass);
      localObject = localClass;
    }
    catch (ClassNotFoundException|NoSuchMethodException localClassNotFoundException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unable to collect necessary methods for class ");
      localStringBuilder.append(localClassNotFoundException.getClass().getName());
      Log.e("TypefaceCompatApi26Impl", localStringBuilder.toString(), localClassNotFoundException);
      localConstructor = null;
      localMethod1 = null;
      localMethod2 = null;
      localMethod3 = null;
      localMethod4 = null;
      localMethod5 = null;
    }
    this.mFontFamily = localObject;
    this.mFontFamilyCtor = localConstructor;
    this.mAddFontFromAssetManager = localMethod1;
    this.mAddFontFromBuffer = localMethod2;
    this.mFreeze = localMethod3;
    this.mAbortCreation = localMethod4;
    this.mCreateFromFamiliesWithDefault = localMethod5;
  }

  private void abortCreation(Object paramObject)
  {
    try
    {
      this.mAbortCreation.invoke(paramObject, new Object[0]);
      return;
    }
    catch (IllegalAccessException|InvocationTargetException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
  }

  private boolean addFontFromAssetManager(Context paramContext, Object paramObject, String paramString, int paramInt1, int paramInt2, int paramInt3, @Nullable FontVariationAxis[] paramArrayOfFontVariationAxis)
  {
    try
    {
      Method localMethod = this.mAddFontFromAssetManager;
      Object[] arrayOfObject = new Object[8];
      arrayOfObject[0] = paramContext.getAssets();
      arrayOfObject[1] = paramString;
      arrayOfObject[2] = Integer.valueOf(0);
      arrayOfObject[3] = Boolean.valueOf(false);
      arrayOfObject[4] = Integer.valueOf(paramInt1);
      arrayOfObject[5] = Integer.valueOf(paramInt2);
      arrayOfObject[6] = Integer.valueOf(paramInt3);
      arrayOfObject[7] = paramArrayOfFontVariationAxis;
      boolean bool = ((Boolean)localMethod.invoke(paramObject, arrayOfObject)).booleanValue();
      return bool;
    }
    catch (IllegalAccessException|InvocationTargetException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
  }

  private boolean addFontFromBuffer(Object paramObject, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      Method localMethod = this.mAddFontFromBuffer;
      Object[] arrayOfObject = new Object[5];
      arrayOfObject[0] = paramByteBuffer;
      arrayOfObject[1] = Integer.valueOf(paramInt1);
      arrayOfObject[2] = null;
      arrayOfObject[3] = Integer.valueOf(paramInt2);
      arrayOfObject[4] = Integer.valueOf(paramInt3);
      boolean bool = ((Boolean)localMethod.invoke(paramObject, arrayOfObject)).booleanValue();
      return bool;
    }
    catch (IllegalAccessException|InvocationTargetException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
  }

  private boolean freeze(Object paramObject)
  {
    try
    {
      boolean bool = ((Boolean)this.mFreeze.invoke(paramObject, new Object[0])).booleanValue();
      return bool;
    }
    catch (IllegalAccessException|InvocationTargetException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
  }

  private boolean isFontFamilyPrivateAPIAvailable()
  {
    if (this.mAddFontFromAssetManager == null)
      Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
    boolean bool;
    if (this.mAddFontFromAssetManager != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  private Object newFamily()
  {
    try
    {
      Object localObject = this.mFontFamilyCtor.newInstance(new Object[0]);
      return localObject;
    }
    catch (IllegalAccessException|InstantiationException|InvocationTargetException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
  }

  protected Typeface createFromFamiliesWithDefault(Object paramObject)
  {
    try
    {
      Object localObject = Array.newInstance(this.mFontFamily, 1);
      Array.set(localObject, 0, paramObject);
      Method localMethod = this.mCreateFromFamiliesWithDefault;
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = localObject;
      arrayOfObject[1] = Integer.valueOf(-1);
      arrayOfObject[2] = Integer.valueOf(-1);
      Typeface localTypeface = (Typeface)localMethod.invoke(null, arrayOfObject);
      return localTypeface;
    }
    catch (IllegalAccessException|InvocationTargetException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
  }

  public Typeface createFromFontFamilyFilesResourceEntry(Context paramContext, FontResourcesParserCompat.FontFamilyFilesResourceEntry paramFontFamilyFilesResourceEntry, Resources paramResources, int paramInt)
  {
    if (!isFontFamilyPrivateAPIAvailable())
      return super.createFromFontFamilyFilesResourceEntry(paramContext, paramFontFamilyFilesResourceEntry, paramResources, paramInt);
    Object localObject = newFamily();
    for (FontResourcesParserCompat.FontFileResourceEntry localFontFileResourceEntry : paramFontFamilyFilesResourceEntry.getEntries())
      if (!addFontFromAssetManager(paramContext, localObject, localFontFileResourceEntry.getFileName(), localFontFileResourceEntry.getTtcIndex(), localFontFileResourceEntry.getWeight(), localFontFileResourceEntry.isItalic(), FontVariationAxis.fromFontVariationSettings(localFontFileResourceEntry.getVariationSettings())))
      {
        abortCreation(localObject);
        return null;
      }
    if (!freeze(localObject))
      return null;
    return createFromFamiliesWithDefault(localObject);
  }

  // ERROR //
  public Typeface createFromFontInfo(Context paramContext, @Nullable android.os.CancellationSignal paramCancellationSignal, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt)
  {
    // Byte code:
    //   0: aload_3
    //   1: arraylength
    //   2: iconst_1
    //   3: if_icmpge +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: aload_0
    //   9: invokespecial 197	android/support/v4/graphics/TypefaceCompatApi26Impl:isFontFamilyPrivateAPIAvailable	()Z
    //   12: ifne +160 -> 172
    //   15: aload_0
    //   16: aload_3
    //   17: iload 4
    //   19: invokevirtual 250	android/support/v4/graphics/TypefaceCompatApi26Impl:findBestInfo	([Landroid/support/v4/provider/FontsContractCompat$FontInfo;I)Landroid/support/v4/provider/FontsContractCompat$FontInfo;
    //   22: astore 12
    //   24: aload_1
    //   25: invokevirtual 254	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   28: astore 13
    //   30: aload 13
    //   32: aload 12
    //   34: invokevirtual 260	android/support/v4/provider/FontsContractCompat$FontInfo:getUri	()Landroid/net/Uri;
    //   37: ldc_w 262
    //   40: aload_2
    //   41: invokevirtual 268	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   44: astore 14
    //   46: aload 14
    //   48: ifnonnull +15 -> 63
    //   51: aload 14
    //   53: ifnull +8 -> 61
    //   56: aload 14
    //   58: invokevirtual 273	android/os/ParcelFileDescriptor:close	()V
    //   61: aconst_null
    //   62: areturn
    //   63: new 275	android/graphics/Typeface$Builder
    //   66: dup
    //   67: aload 14
    //   69: invokevirtual 279	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   72: invokespecial 282	android/graphics/Typeface$Builder:<init>	(Ljava/io/FileDescriptor;)V
    //   75: aload 12
    //   77: invokevirtual 283	android/support/v4/provider/FontsContractCompat$FontInfo:getWeight	()I
    //   80: invokevirtual 287	android/graphics/Typeface$Builder:setWeight	(I)Landroid/graphics/Typeface$Builder;
    //   83: aload 12
    //   85: invokevirtual 288	android/support/v4/provider/FontsContractCompat$FontInfo:isItalic	()Z
    //   88: invokevirtual 292	android/graphics/Typeface$Builder:setItalic	(Z)Landroid/graphics/Typeface$Builder;
    //   91: invokevirtual 296	android/graphics/Typeface$Builder:build	()Landroid/graphics/Typeface;
    //   94: astore 20
    //   96: aload 14
    //   98: ifnull +8 -> 106
    //   101: aload 14
    //   103: invokevirtual 273	android/os/ParcelFileDescriptor:close	()V
    //   106: aload 20
    //   108: areturn
    //   109: astore 18
    //   111: aconst_null
    //   112: astore 17
    //   114: goto +18 -> 132
    //   117: astore 15
    //   119: aload 15
    //   121: athrow
    //   122: astore 16
    //   124: aload 15
    //   126: astore 17
    //   128: aload 16
    //   130: astore 18
    //   132: aload 14
    //   134: ifnull +33 -> 167
    //   137: aload 17
    //   139: ifnull +23 -> 162
    //   142: aload 14
    //   144: invokevirtual 273	android/os/ParcelFileDescriptor:close	()V
    //   147: goto +20 -> 167
    //   150: astore 19
    //   152: aload 17
    //   154: aload 19
    //   156: invokevirtual 299	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   159: goto +8 -> 167
    //   162: aload 14
    //   164: invokevirtual 273	android/os/ParcelFileDescriptor:close	()V
    //   167: aload 18
    //   169: athrow
    //   170: aconst_null
    //   171: areturn
    //   172: aload_1
    //   173: aload_3
    //   174: aload_2
    //   175: invokestatic 305	android/support/v4/provider/FontsContractCompat:prepareFontData	(Landroid/content/Context;[Landroid/support/v4/provider/FontsContractCompat$FontInfo;Landroid/os/CancellationSignal;)Ljava/util/Map;
    //   178: astore 5
    //   180: aload_0
    //   181: invokespecial 201	android/support/v4/graphics/TypefaceCompatApi26Impl:newFamily	()Ljava/lang/Object;
    //   184: astore 6
    //   186: aload_3
    //   187: arraylength
    //   188: istore 7
    //   190: iconst_0
    //   191: istore 8
    //   193: iconst_0
    //   194: istore 9
    //   196: iload 9
    //   198: iload 7
    //   200: if_icmpge +77 -> 277
    //   203: aload_3
    //   204: iload 9
    //   206: aaload
    //   207: astore 10
    //   209: aload 5
    //   211: aload 10
    //   213: invokevirtual 260	android/support/v4/provider/FontsContractCompat$FontInfo:getUri	()Landroid/net/Uri;
    //   216: invokeinterface 311 2 0
    //   221: checkcast 313	java/nio/ByteBuffer
    //   224: astore 11
    //   226: aload 11
    //   228: ifnonnull +6 -> 234
    //   231: goto +40 -> 271
    //   234: aload_0
    //   235: aload 6
    //   237: aload 11
    //   239: aload 10
    //   241: invokevirtual 314	android/support/v4/provider/FontsContractCompat$FontInfo:getTtcIndex	()I
    //   244: aload 10
    //   246: invokevirtual 283	android/support/v4/provider/FontsContractCompat$FontInfo:getWeight	()I
    //   249: aload 10
    //   251: invokevirtual 288	android/support/v4/provider/FontsContractCompat$FontInfo:isItalic	()Z
    //   254: invokespecial 316	android/support/v4/graphics/TypefaceCompatApi26Impl:addFontFromBuffer	(Ljava/lang/Object;Ljava/nio/ByteBuffer;III)Z
    //   257: ifne +11 -> 268
    //   260: aload_0
    //   261: aload 6
    //   263: invokespecial 235	android/support/v4/graphics/TypefaceCompatApi26Impl:abortCreation	(Ljava/lang/Object;)V
    //   266: aconst_null
    //   267: areturn
    //   268: iconst_1
    //   269: istore 8
    //   271: iinc 9 1
    //   274: goto -78 -> 196
    //   277: iload 8
    //   279: ifne +11 -> 290
    //   282: aload_0
    //   283: aload 6
    //   285: invokespecial 235	android/support/v4/graphics/TypefaceCompatApi26Impl:abortCreation	(Ljava/lang/Object;)V
    //   288: aconst_null
    //   289: areturn
    //   290: aload_0
    //   291: aload 6
    //   293: invokespecial 237	android/support/v4/graphics/TypefaceCompatApi26Impl:freeze	(Ljava/lang/Object;)Z
    //   296: ifne +5 -> 301
    //   299: aconst_null
    //   300: areturn
    //   301: aload_0
    //   302: aload 6
    //   304: invokevirtual 239	android/support/v4/graphics/TypefaceCompatApi26Impl:createFromFamiliesWithDefault	(Ljava/lang/Object;)Landroid/graphics/Typeface;
    //   307: iload 4
    //   309: invokestatic 320	android/graphics/Typeface:create	(Landroid/graphics/Typeface;I)Landroid/graphics/Typeface;
    //   312: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   63	96	109	finally
    //   63	96	117	java/lang/Throwable
    //   119	122	122	finally
    //   142	147	150	java/lang/Throwable
    //   30	61	170	java/io/IOException
    //   101	106	170	java/io/IOException
    //   142	147	170	java/io/IOException
    //   152	170	170	java/io/IOException
  }

  @Nullable
  public Typeface createFromResourcesFontFile(Context paramContext, Resources paramResources, int paramInt1, String paramString, int paramInt2)
  {
    if (!isFontFamilyPrivateAPIAvailable())
      return super.createFromResourcesFontFile(paramContext, paramResources, paramInt1, paramString, paramInt2);
    Object localObject = newFamily();
    if (!addFontFromAssetManager(paramContext, localObject, paramString, 0, -1, -1, null))
    {
      abortCreation(localObject);
      return null;
    }
    if (!freeze(localObject))
      return null;
    return createFromFamiliesWithDefault(localObject);
  }

  protected Method obtainAbortCreationMethod(Class paramClass)
  {
    return paramClass.getMethod("abortCreation", new Class[0]);
  }

  protected Method obtainAddFontFromAssetManagerMethod(Class paramClass)
  {
    Class[] arrayOfClass = new Class[8];
    arrayOfClass[0] = AssetManager.class;
    arrayOfClass[1] = String.class;
    arrayOfClass[2] = Integer.TYPE;
    arrayOfClass[3] = Boolean.TYPE;
    arrayOfClass[4] = Integer.TYPE;
    arrayOfClass[5] = Integer.TYPE;
    arrayOfClass[6] = Integer.TYPE;
    arrayOfClass[7] = [Landroid.graphics.fonts.FontVariationAxis.class;
    return paramClass.getMethod("addFontFromAssetManager", arrayOfClass);
  }

  protected Method obtainAddFontFromBufferMethod(Class paramClass)
  {
    Class[] arrayOfClass = new Class[5];
    arrayOfClass[0] = ByteBuffer.class;
    arrayOfClass[1] = Integer.TYPE;
    arrayOfClass[2] = [Landroid.graphics.fonts.FontVariationAxis.class;
    arrayOfClass[3] = Integer.TYPE;
    arrayOfClass[4] = Integer.TYPE;
    return paramClass.getMethod("addFontFromBuffer", arrayOfClass);
  }

  protected Method obtainCreateFromFamiliesWithDefaultMethod(Class paramClass)
  {
    Object localObject = Array.newInstance(paramClass, 1);
    Class[] arrayOfClass = new Class[3];
    arrayOfClass[0] = localObject.getClass();
    arrayOfClass[1] = Integer.TYPE;
    arrayOfClass[2] = Integer.TYPE;
    Method localMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", arrayOfClass);
    localMethod.setAccessible(true);
    return localMethod;
  }

  protected Class obtainFontFamily()
  {
    return Class.forName("android.graphics.FontFamily");
  }

  protected Constructor obtainFontFamilyCtor(Class paramClass)
  {
    return paramClass.getConstructor(new Class[0]);
  }

  protected Method obtainFreezeMethod(Class paramClass)
  {
    return paramClass.getMethod("freeze", new Class[0]);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.TypefaceCompatApi26Impl
 * JD-Core Version:    0.6.1
 */