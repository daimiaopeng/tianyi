package android.support.v4.graphics.drawable;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.util.Preconditions;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class IconCompat extends CustomVersionedParcelable
{
  private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25F;
  private static final int AMBIENT_SHADOW_ALPHA = 30;
  private static final float BLUR_FACTOR = 0.01041667F;
  static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
  private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667F;
  private static final String EXTRA_INT1 = "int1";
  private static final String EXTRA_INT2 = "int2";
  private static final String EXTRA_OBJ = "obj";
  private static final String EXTRA_TINT_LIST = "tint_list";
  private static final String EXTRA_TINT_MODE = "tint_mode";
  private static final String EXTRA_TYPE = "type";
  private static final float ICON_DIAMETER_FACTOR = 0.9166667F;
  private static final int KEY_SHADOW_ALPHA = 61;
  private static final float KEY_SHADOW_OFFSET_FACTOR = 0.02083333F;
  private static final String TAG = "IconCompat";
  public static final int TYPE_UNKNOWN = -1;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public byte[] mData;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public int mInt1;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public int mInt2;
  Object mObj1;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public Parcelable mParcelable;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public ColorStateList mTintList = null;
  PorterDuff.Mode mTintMode = DEFAULT_TINT_MODE;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public String mTintModeStr;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public int mType;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public IconCompat()
  {
  }

  private IconCompat(int paramInt)
  {
    this.mType = paramInt;
  }

  @Nullable
  public static IconCompat createFromBundle(@NonNull Bundle paramBundle)
  {
    int i = paramBundle.getInt("type");
    IconCompat localIconCompat = new IconCompat(i);
    localIconCompat.mInt1 = paramBundle.getInt("int1");
    localIconCompat.mInt2 = paramBundle.getInt("int2");
    if (paramBundle.containsKey("tint_list"))
      localIconCompat.mTintList = ((ColorStateList)paramBundle.getParcelable("tint_list"));
    if (paramBundle.containsKey("tint_mode"))
      localIconCompat.mTintMode = PorterDuff.Mode.valueOf(paramBundle.getString("tint_mode"));
    if (i != -1)
      switch (i)
      {
      default:
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unknown type ");
        localStringBuilder.append(i);
        Log.w("IconCompat", localStringBuilder.toString());
        return null;
      case 3:
        localIconCompat.mObj1 = paramBundle.getByteArray("obj");
        break;
      case 2:
      case 4:
        localIconCompat.mObj1 = paramBundle.getString("obj");
        break;
      case 1:
      case 5:
      }
    else
      localIconCompat.mObj1 = paramBundle.getParcelable("obj");
    return localIconCompat;
  }

  // ERROR //
  @Nullable
  @RequiresApi(23)
  public static IconCompat createFromIcon(@NonNull Context paramContext, @NonNull Icon paramIcon)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 156	android/support/v4/util/Preconditions:checkNotNull	(Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: aload_1
    //   6: invokestatic 160	android/support/v4/graphics/drawable/IconCompat:getType	(Landroid/graphics/drawable/Icon;)I
    //   9: istore_3
    //   10: iload_3
    //   11: iconst_2
    //   12: if_icmpeq +35 -> 47
    //   15: iload_3
    //   16: iconst_4
    //   17: if_icmpeq +22 -> 39
    //   20: new 2	android/support/v4/graphics/drawable/IconCompat
    //   23: dup
    //   24: iconst_m1
    //   25: invokespecial 94	android/support/v4/graphics/drawable/IconCompat:<init>	(I)V
    //   28: astore 6
    //   30: aload 6
    //   32: aload_1
    //   33: putfield 144	android/support/v4/graphics/drawable/IconCompat:mObj1	Ljava/lang/Object;
    //   36: aload 6
    //   38: areturn
    //   39: aload_1
    //   40: invokestatic 164	android/support/v4/graphics/drawable/IconCompat:getUri	(Landroid/graphics/drawable/Icon;)Landroid/net/Uri;
    //   43: invokestatic 168	android/support/v4/graphics/drawable/IconCompat:createWithContentUri	(Landroid/net/Uri;)Landroid/support/v4/graphics/drawable/IconCompat;
    //   46: areturn
    //   47: aload_1
    //   48: invokestatic 172	android/support/v4/graphics/drawable/IconCompat:getResPackage	(Landroid/graphics/drawable/Icon;)Ljava/lang/String;
    //   51: astore 4
    //   53: aload_0
    //   54: aload 4
    //   56: invokestatic 176	android/support/v4/graphics/drawable/IconCompat:getResources	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/res/Resources;
    //   59: aload 4
    //   61: aload_1
    //   62: invokestatic 179	android/support/v4/graphics/drawable/IconCompat:getResId	(Landroid/graphics/drawable/Icon;)I
    //   65: invokestatic 183	android/support/v4/graphics/drawable/IconCompat:createWithResource	(Landroid/content/res/Resources;Ljava/lang/String;I)Landroid/support/v4/graphics/drawable/IconCompat;
    //   68: astore 5
    //   70: aload 5
    //   72: areturn
    //   73: new 185	java/lang/IllegalArgumentException
    //   76: dup
    //   77: ldc 187
    //   79: invokespecial 190	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   82: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   53	70	73	android/content/res/Resources$NotFoundException
  }

  @Nullable
  @RequiresApi(23)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static IconCompat createFromIcon(@NonNull Icon paramIcon)
  {
    Preconditions.checkNotNull(paramIcon);
    int i = getType(paramIcon);
    if (i != 2)
    {
      if (i != 4)
      {
        IconCompat localIconCompat = new IconCompat(-1);
        localIconCompat.mObj1 = paramIcon;
        return localIconCompat;
      }
      return createWithContentUri(getUri(paramIcon));
    }
    return createWithResource(null, getResPackage(paramIcon), getResId(paramIcon));
  }

  @VisibleForTesting
  static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap paramBitmap, boolean paramBoolean)
  {
    int i = (int)(0.6666667F * Math.min(paramBitmap.getWidth(), paramBitmap.getHeight()));
    Bitmap localBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint(3);
    float f1 = i;
    float f2 = 0.5F * f1;
    float f3 = 0.9166667F * f2;
    if (paramBoolean)
    {
      float f4 = 0.01041667F * f1;
      localPaint.setColor(0);
      localPaint.setShadowLayer(f4, 0.0F, f1 * 0.02083333F, 1023410176);
      localCanvas.drawCircle(f2, f2, f3, localPaint);
      localPaint.setShadowLayer(f4, 0.0F, 0.0F, 503316480);
      localCanvas.drawCircle(f2, f2, f3, localPaint);
      localPaint.clearShadowLayer();
    }
    localPaint.setColor(-16777216);
    BitmapShader localBitmapShader = new BitmapShader(paramBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    Matrix localMatrix = new Matrix();
    localMatrix.setTranslate(-(paramBitmap.getWidth() - i) / 2, -(paramBitmap.getHeight() - i) / 2);
    localBitmapShader.setLocalMatrix(localMatrix);
    localPaint.setShader(localBitmapShader);
    localCanvas.drawCircle(f2, f2, f3, localPaint);
    localCanvas.setBitmap(null);
    return localBitmap;
  }

  public static IconCompat createWithAdaptiveBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap == null)
      throw new IllegalArgumentException("Bitmap must not be null.");
    IconCompat localIconCompat = new IconCompat(5);
    localIconCompat.mObj1 = paramBitmap;
    return localIconCompat;
  }

  public static IconCompat createWithBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap == null)
      throw new IllegalArgumentException("Bitmap must not be null.");
    IconCompat localIconCompat = new IconCompat(1);
    localIconCompat.mObj1 = paramBitmap;
    return localIconCompat;
  }

  public static IconCompat createWithContentUri(Uri paramUri)
  {
    if (paramUri == null)
      throw new IllegalArgumentException("Uri must not be null.");
    return createWithContentUri(paramUri.toString());
  }

  public static IconCompat createWithContentUri(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Uri must not be null.");
    IconCompat localIconCompat = new IconCompat(4);
    localIconCompat.mObj1 = paramString;
    return localIconCompat;
  }

  public static IconCompat createWithData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Data must not be null.");
    IconCompat localIconCompat = new IconCompat(3);
    localIconCompat.mObj1 = paramArrayOfByte;
    localIconCompat.mInt1 = paramInt1;
    localIconCompat.mInt2 = paramInt2;
    return localIconCompat;
  }

  public static IconCompat createWithResource(Context paramContext, @DrawableRes int paramInt)
  {
    if (paramContext == null)
      throw new IllegalArgumentException("Context must not be null.");
    return createWithResource(paramContext.getResources(), paramContext.getPackageName(), paramInt);
  }

  // ERROR //
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static IconCompat createWithResource(Resources paramResources, String paramString, @DrawableRes int paramInt)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +14 -> 15
    //   4: new 185	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc_w 306
    //   11: invokespecial 190	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   14: athrow
    //   15: iload_2
    //   16: ifne +14 -> 30
    //   19: new 185	java/lang/IllegalArgumentException
    //   22: dup
    //   23: ldc_w 308
    //   26: invokespecial 190	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   29: athrow
    //   30: new 2	android/support/v4/graphics/drawable/IconCompat
    //   33: dup
    //   34: iconst_2
    //   35: invokespecial 94	android/support/v4/graphics/drawable/IconCompat:<init>	(I)V
    //   38: astore_3
    //   39: aload_3
    //   40: iload_2
    //   41: putfield 96	android/support/v4/graphics/drawable/IconCompat:mInt1	I
    //   44: aload_0
    //   45: ifnull +25 -> 70
    //   48: aload_3
    //   49: aload_0
    //   50: iload_2
    //   51: invokevirtual 314	android/content/res/Resources:getResourceName	(I)Ljava/lang/String;
    //   54: putfield 144	android/support/v4/graphics/drawable/IconCompat:mObj1	Ljava/lang/Object;
    //   57: goto +18 -> 75
    //   60: new 185	java/lang/IllegalArgumentException
    //   63: dup
    //   64: ldc 187
    //   66: invokespecial 190	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   69: athrow
    //   70: aload_3
    //   71: aload_1
    //   72: putfield 144	android/support/v4/graphics/drawable/IconCompat:mObj1	Ljava/lang/Object;
    //   75: aload_3
    //   76: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   48	57	60	android/content/res/Resources$NotFoundException
  }

  @DrawableRes
  @IdRes
  @RequiresApi(23)
  private static int getResId(@NonNull Icon paramIcon)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramIcon.getResId();
    try
    {
      int i = ((Integer)paramIcon.getClass().getMethod("getResId", new Class[0]).invoke(paramIcon, new Object[0])).intValue();
      return i;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      Log.e("IconCompat", "Unable to get icon resource", localNoSuchMethodException);
      return 0;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Log.e("IconCompat", "Unable to get icon resource", localInvocationTargetException);
      return 0;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Log.e("IconCompat", "Unable to get icon resource", localIllegalAccessException);
    }
    return 0;
  }

  @Nullable
  @RequiresApi(23)
  private static String getResPackage(@NonNull Icon paramIcon)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramIcon.getResPackage();
    try
    {
      String str = (String)paramIcon.getClass().getMethod("getResPackage", new Class[0]).invoke(paramIcon, new Object[0]);
      return str;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      Log.e("IconCompat", "Unable to get icon package", localNoSuchMethodException);
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Log.e("IconCompat", "Unable to get icon package", localInvocationTargetException);
      return null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Log.e("IconCompat", "Unable to get icon package", localIllegalAccessException);
    }
    return null;
  }

  private static Resources getResources(Context paramContext, String paramString)
  {
    if ("android".equals(paramString))
      return Resources.getSystem();
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(paramString, 8192);
      if (localApplicationInfo != null)
      {
        Resources localResources = localPackageManager.getResourcesForApplication(localApplicationInfo);
        return localResources;
      }
      return null;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Log.e("IconCompat", String.format("Unable to find pkg=%s for icon", new Object[] { paramString }), localNameNotFoundException);
    }
    return null;
  }

  @RequiresApi(23)
  private static int getType(@NonNull Icon paramIcon)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramIcon.getType();
    try
    {
      int i = ((Integer)paramIcon.getClass().getMethod("getType", new Class[0]).invoke(paramIcon, new Object[0])).intValue();
      return i;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("Unable to get icon type ");
      localStringBuilder3.append(paramIcon);
      Log.e("IconCompat", localStringBuilder3.toString(), localNoSuchMethodException);
      return -1;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Unable to get icon type ");
      localStringBuilder2.append(paramIcon);
      Log.e("IconCompat", localStringBuilder2.toString(), localInvocationTargetException);
      return -1;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Unable to get icon type ");
      localStringBuilder1.append(paramIcon);
      Log.e("IconCompat", localStringBuilder1.toString(), localIllegalAccessException);
    }
    return -1;
  }

  @Nullable
  @RequiresApi(23)
  private static Uri getUri(@NonNull Icon paramIcon)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramIcon.getUri();
    try
    {
      Uri localUri = (Uri)paramIcon.getClass().getMethod("getUri", new Class[0]).invoke(paramIcon, new Object[0]);
      return localUri;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      Log.e("IconCompat", "Unable to get icon uri", localNoSuchMethodException);
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Log.e("IconCompat", "Unable to get icon uri", localInvocationTargetException);
      return null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Log.e("IconCompat", "Unable to get icon uri", localIllegalAccessException);
    }
    return null;
  }

  private Drawable loadDrawableInner(Context paramContext)
  {
    switch (this.mType)
    {
    default:
      break;
    case 5:
      return new BitmapDrawable(paramContext.getResources(), createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, false));
    case 4:
      Uri localUri = Uri.parse((String)this.mObj1);
      String str2 = localUri.getScheme();
      if ((!"content".equals(str2)) && (!"file".equals(str2)))
        try
        {
          localObject = new FileInputStream(new File((String)this.mObj1));
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("Unable to load image from path: ");
          localStringBuilder2.append(localUri);
          Log.w("IconCompat", localStringBuilder2.toString(), localFileNotFoundException);
          break label235;
        }
      else
        try
        {
          localObject = paramContext.getContentResolver().openInputStream(localUri);
        }
        catch (Exception localException)
        {
          StringBuilder localStringBuilder1 = new StringBuilder();
          localStringBuilder1.append("Unable to load image from URI: ");
          localStringBuilder1.append(localUri);
          Log.w("IconCompat", localStringBuilder1.toString(), localException);
        }
      Object localObject = null;
      if (localObject != null)
        return new BitmapDrawable(paramContext.getResources(), BitmapFactory.decodeStream((InputStream)localObject));
      break;
    case 3:
      return new BitmapDrawable(paramContext.getResources(), BitmapFactory.decodeByteArray((byte[])this.mObj1, this.mInt1, this.mInt2));
    case 2:
      String str1 = getResPackage();
      if (TextUtils.isEmpty(str1))
        str1 = paramContext.getPackageName();
      Resources localResources = getResources(paramContext, str1);
      try
      {
        Drawable localDrawable = ResourcesCompat.getDrawable(localResources, this.mInt1, paramContext.getTheme());
        return localDrawable;
      }
      catch (RuntimeException localRuntimeException)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(this.mInt1);
        arrayOfObject[1] = this.mObj1;
        Log.e("IconCompat", String.format("Unable to load resource 0x%08x from pkg=%s", arrayOfObject), localRuntimeException);
      }
    case 1:
      label235: return new BitmapDrawable(paramContext.getResources(), (Bitmap)this.mObj1);
    }
    return null;
  }

  private static String typeToString(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "UNKNOWN";
    case 5:
      return "BITMAP_MASKABLE";
    case 4:
      return "URI";
    case 3:
      return "DATA";
    case 2:
      return "RESOURCE";
    case 1:
    }
    return "BITMAP";
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void addToShortcutIntent(@NonNull Intent paramIntent, @Nullable Drawable paramDrawable, @NonNull Context paramContext)
  {
    checkResource(paramContext);
    int i = this.mType;
    Bitmap localBitmap;
    if (i != 5)
      switch (i)
      {
      default:
        throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
      case 2:
        try
        {
          Context localContext = paramContext.createPackageContext(getResPackage(), 0);
          if (paramDrawable == null)
          {
            paramIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(localContext, this.mInt1));
            return;
          }
          Drawable localDrawable = ContextCompat.getDrawable(localContext, this.mInt1);
          if ((localDrawable.getIntrinsicWidth() > 0) && (localDrawable.getIntrinsicHeight() > 0))
          {
            localBitmap = Bitmap.createBitmap(localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
          }
          else
          {
            int m = ((ActivityManager)localContext.getSystemService("activity")).getLauncherLargeIconSize();
            localBitmap = Bitmap.createBitmap(m, m, Bitmap.Config.ARGB_8888);
          }
          localDrawable.setBounds(0, 0, localBitmap.getWidth(), localBitmap.getHeight());
          localDrawable.draw(new Canvas(localBitmap));
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Can't find package ");
          localStringBuilder.append(this.mObj1);
          throw new IllegalArgumentException(localStringBuilder.toString(), localNameNotFoundException);
        }
      case 1:
        localBitmap = (Bitmap)this.mObj1;
        if (paramDrawable == null)
          break;
        localBitmap = localBitmap.copy(localBitmap.getConfig(), true);
        break;
      }
    else
      localBitmap = createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, true);
    if (paramDrawable != null)
    {
      int j = localBitmap.getWidth();
      int k = localBitmap.getHeight();
      paramDrawable.setBounds(j / 2, k / 2, j, k);
      paramDrawable.draw(new Canvas(localBitmap));
    }
    paramIntent.putExtra("android.intent.extra.shortcut.ICON", localBitmap);
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void checkResource(Context paramContext)
  {
    if (this.mType == 2)
    {
      String str1 = (String)this.mObj1;
      if (!str1.contains(":"))
        return;
      String str2 = str1.split(":", -1)[1];
      String str3 = str2.split("/", -1)[0];
      String str4 = str2.split("/", -1)[1];
      String str5 = str1.split(":", -1)[0];
      int i = getResources(paramContext, str5).getIdentifier(str4, str3, str5);
      if (this.mInt1 != i)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Id has changed for ");
        localStringBuilder.append(str5);
        localStringBuilder.append("/");
        localStringBuilder.append(str4);
        Log.i("IconCompat", localStringBuilder.toString());
        this.mInt1 = i;
      }
    }
  }

  @IdRes
  public int getResId()
  {
    if ((this.mType == -1) && (Build.VERSION.SDK_INT >= 23))
      return getResId((Icon)this.mObj1);
    if (this.mType != 2)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("called getResId() on ");
      localStringBuilder.append(this);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    return this.mInt1;
  }

  @NonNull
  public String getResPackage()
  {
    if ((this.mType == -1) && (Build.VERSION.SDK_INT >= 23))
      return getResPackage((Icon)this.mObj1);
    if (this.mType != 2)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("called getResPackage() on ");
      localStringBuilder.append(this);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    return ((String)this.mObj1).split(":", -1)[0];
  }

  public int getType()
  {
    if ((this.mType == -1) && (Build.VERSION.SDK_INT >= 23))
      return getType((Icon)this.mObj1);
    return this.mType;
  }

  @NonNull
  public Uri getUri()
  {
    if ((this.mType == -1) && (Build.VERSION.SDK_INT >= 23))
      return getUri((Icon)this.mObj1);
    return Uri.parse((String)this.mObj1);
  }

  public Drawable loadDrawable(Context paramContext)
  {
    checkResource(paramContext);
    if (Build.VERSION.SDK_INT >= 23)
      return toIcon().loadDrawable(paramContext);
    Drawable localDrawable = loadDrawableInner(paramContext);
    if ((localDrawable != null) && ((this.mTintList != null) || (this.mTintMode != DEFAULT_TINT_MODE)))
    {
      localDrawable.mutate();
      DrawableCompat.setTintList(localDrawable, this.mTintList);
      DrawableCompat.setTintMode(localDrawable, this.mTintMode);
    }
    return localDrawable;
  }

  public void onPostParceling()
  {
    this.mTintMode = PorterDuff.Mode.valueOf(this.mTintModeStr);
    int i = this.mType;
    if (i != -1)
    {
      switch (i)
      {
      default:
        break;
      case 3:
        this.mObj1 = this.mData;
        break;
      case 2:
      case 4:
        this.mObj1 = new String(this.mData, Charset.forName("UTF-16"));
        break;
      case 1:
      case 5:
        if (this.mParcelable != null)
        {
          this.mObj1 = this.mParcelable;
        }
        else
        {
          this.mObj1 = this.mData;
          this.mType = 3;
          this.mInt1 = 0;
          this.mInt2 = this.mData.length;
        }
        break;
      }
    }
    else
    {
      if (this.mParcelable == null)
        break label158;
      this.mObj1 = this.mParcelable;
    }
    return;
    label158: throw new IllegalArgumentException("Invalid icon");
  }

  public void onPreParceling(boolean paramBoolean)
  {
    this.mTintModeStr = this.mTintMode.name();
    int i = this.mType;
    if (i != -1)
    {
      switch (i)
      {
      default:
        break;
      case 4:
        this.mData = this.mObj1.toString().getBytes(Charset.forName("UTF-16"));
        break;
      case 3:
        this.mData = ((byte[])this.mObj1);
        break;
      case 2:
        this.mData = ((String)this.mObj1).getBytes(Charset.forName("UTF-16"));
        break;
      case 1:
      case 5:
        if (paramBoolean)
        {
          Bitmap localBitmap = (Bitmap)this.mObj1;
          ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
          localBitmap.compress(Bitmap.CompressFormat.PNG, 90, localByteArrayOutputStream);
          this.mData = localByteArrayOutputStream.toByteArray();
        }
        else
        {
          this.mParcelable = ((Parcelable)this.mObj1);
        }
        break;
      }
    }
    else
    {
      if (paramBoolean)
        throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
      this.mParcelable = ((Parcelable)this.mObj1);
    }
  }

  public IconCompat setTint(@ColorInt int paramInt)
  {
    return setTintList(ColorStateList.valueOf(paramInt));
  }

  public IconCompat setTintList(ColorStateList paramColorStateList)
  {
    this.mTintList = paramColorStateList;
    return this;
  }

  public IconCompat setTintMode(PorterDuff.Mode paramMode)
  {
    this.mTintMode = paramMode;
    return this;
  }

  public Bundle toBundle()
  {
    Bundle localBundle = new Bundle();
    int i = this.mType;
    if (i != -1)
      switch (i)
      {
      default:
        throw new IllegalArgumentException("Invalid icon");
      case 3:
        localBundle.putByteArray("obj", (byte[])this.mObj1);
        break;
      case 2:
      case 4:
        localBundle.putString("obj", (String)this.mObj1);
        break;
      case 1:
      case 5:
        localBundle.putParcelable("obj", (Bitmap)this.mObj1);
        break;
      }
    else
      localBundle.putParcelable("obj", (Parcelable)this.mObj1);
    localBundle.putInt("type", this.mType);
    localBundle.putInt("int1", this.mInt1);
    localBundle.putInt("int2", this.mInt2);
    if (this.mTintList != null)
      localBundle.putParcelable("tint_list", this.mTintList);
    if (this.mTintMode != DEFAULT_TINT_MODE)
      localBundle.putString("tint_mode", this.mTintMode.name());
    return localBundle;
  }

  @RequiresApi(23)
  public Icon toIcon()
  {
    int i = this.mType;
    if (i != -1)
    {
      Icon localIcon;
      switch (i)
      {
      default:
        throw new IllegalArgumentException("Unknown type");
      case 5:
        if (Build.VERSION.SDK_INT >= 26)
          localIcon = Icon.createWithAdaptiveBitmap((Bitmap)this.mObj1);
        else
          localIcon = Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, false));
        break;
      case 4:
        localIcon = Icon.createWithContentUri((String)this.mObj1);
        break;
      case 3:
        localIcon = Icon.createWithData((byte[])this.mObj1, this.mInt1, this.mInt2);
        break;
      case 2:
        localIcon = Icon.createWithResource(getResPackage(), this.mInt1);
        break;
      case 1:
        localIcon = Icon.createWithBitmap((Bitmap)this.mObj1);
      }
      if (this.mTintList != null)
        localIcon.setTintList(this.mTintList);
      if (this.mTintMode != DEFAULT_TINT_MODE)
        localIcon.setTintMode(this.mTintMode);
      return localIcon;
    }
    return (Icon)this.mObj1;
  }

  public String toString()
  {
    if (this.mType == -1)
      return String.valueOf(this.mObj1);
    StringBuilder localStringBuilder = new StringBuilder("Icon(typ=");
    localStringBuilder.append(typeToString(this.mType));
    switch (this.mType)
    {
    default:
      break;
    case 4:
      localStringBuilder.append(" uri=");
      localStringBuilder.append(this.mObj1);
      break;
    case 3:
      localStringBuilder.append(" len=");
      localStringBuilder.append(this.mInt1);
      if (this.mInt2 != 0)
      {
        localStringBuilder.append(" off=");
        localStringBuilder.append(this.mInt2);
      }
      break;
    case 2:
      localStringBuilder.append(" pkg=");
      localStringBuilder.append(getResPackage());
      localStringBuilder.append(" id=");
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(getResId());
      localStringBuilder.append(String.format("0x%08x", arrayOfObject));
      break;
    case 1:
    case 5:
      localStringBuilder.append(" size=");
      localStringBuilder.append(((Bitmap)this.mObj1).getWidth());
      localStringBuilder.append("x");
      localStringBuilder.append(((Bitmap)this.mObj1).getHeight());
    }
    if (this.mTintList != null)
    {
      localStringBuilder.append(" tint=");
      localStringBuilder.append(this.mTintList);
    }
    if (this.mTintMode != DEFAULT_TINT_MODE)
    {
      localStringBuilder.append(" mode=");
      localStringBuilder.append(this.mTintMode);
    }
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }

  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
  public static @interface IconType
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.drawable.IconCompat
 * JD-Core Version:    0.6.1
 */