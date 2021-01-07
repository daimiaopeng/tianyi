package org.jsoup.helper;

public final class Validate
{
  public static void fail(String paramString)
  {
    throw new IllegalArgumentException(paramString);
  }

  public static void isFalse(boolean paramBoolean)
  {
    if (paramBoolean)
      throw new IllegalArgumentException("Must be false");
  }

  public static void isFalse(boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
      throw new IllegalArgumentException(paramString);
  }

  public static void isTrue(boolean paramBoolean)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException("Must be true");
  }

  public static void isTrue(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException(paramString);
  }

  public static void noNullElements(Object[] paramArrayOfObject)
  {
    noNullElements(paramArrayOfObject, "Array must not contain any null objects");
  }

  public static void noNullElements(Object[] paramArrayOfObject, String paramString)
  {
    int i = paramArrayOfObject.length;
    for (int j = 0; j < i; j++)
      if (paramArrayOfObject[j] == null)
        throw new IllegalArgumentException(paramString);
  }

  public static void notEmpty(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
      return;
    throw new IllegalArgumentException("String must not be empty");
  }

  public static void notEmpty(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString1.length() != 0))
      return;
    throw new IllegalArgumentException(paramString2);
  }

  public static void notNull(Object paramObject)
  {
    if (paramObject == null)
      throw new IllegalArgumentException("Object must not be null");
  }

  public static void notNull(Object paramObject, String paramString)
  {
    if (paramObject == null)
      throw new IllegalArgumentException(paramString);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.helper.Validate
 * JD-Core Version:    0.6.1
 */