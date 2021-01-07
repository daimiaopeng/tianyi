package android.support.v4.graphics;

import android.graphics.Typeface;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(28)
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatApi28Impl extends TypefaceCompatApi26Impl
{
  private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
  private static final String DEFAULT_FAMILY = "sans-serif";
  private static final int RESOLVE_BY_FONT_TABLE = -1;
  private static final String TAG = "TypefaceCompatApi28Impl";

  protected Typeface createFromFamiliesWithDefault(Object paramObject)
  {
    try
    {
      Object localObject = Array.newInstance(this.mFontFamily, 1);
      Array.set(localObject, 0, paramObject);
      Method localMethod = this.mCreateFromFamiliesWithDefault;
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = localObject;
      arrayOfObject[1] = "sans-serif";
      arrayOfObject[2] = Integer.valueOf(-1);
      arrayOfObject[3] = Integer.valueOf(-1);
      Typeface localTypeface = (Typeface)localMethod.invoke(null, arrayOfObject);
      return localTypeface;
    }
    catch (IllegalAccessException|InvocationTargetException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
  }

  protected Method obtainCreateFromFamiliesWithDefaultMethod(Class paramClass)
  {
    Object localObject = Array.newInstance(paramClass, 1);
    Class[] arrayOfClass = new Class[4];
    arrayOfClass[0] = localObject.getClass();
    arrayOfClass[1] = String.class;
    arrayOfClass[2] = Integer.TYPE;
    arrayOfClass[3] = Integer.TYPE;
    Method localMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", arrayOfClass);
    localMethod.setAccessible(true);
    return localMethod;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.TypefaceCompatApi28Impl
 * JD-Core Version:    0.6.1
 */