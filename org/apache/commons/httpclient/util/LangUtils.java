package org.apache.commons.httpclient.util;

public class LangUtils
{
  public static final int HASH_OFFSET = 37;
  public static final int HASH_SEED = 17;

  public static boolean equals(Object paramObject1, Object paramObject2)
  {
    boolean bool;
    if (paramObject1 == null)
    {
      if (paramObject2 == null)
        bool = true;
      else
        bool = false;
    }
    else
      bool = paramObject1.equals(paramObject2);
    return bool;
  }

  public static int hashCode(int paramInt1, int paramInt2)
  {
    return paramInt2 + paramInt1 * 37;
  }

  public static int hashCode(int paramInt, Object paramObject)
  {
    int i;
    if (paramObject != null)
      i = paramObject.hashCode();
    else
      i = 0;
    return hashCode(paramInt, i);
  }

  public static int hashCode(int paramInt, boolean paramBoolean)
  {
    return hashCode(paramInt, paramBoolean);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.LangUtils
 * JD-Core Version:    0.6.1
 */