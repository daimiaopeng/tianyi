package com.google.a.d;

final class f
{
  private final String[] a = new String[512];

  public String a(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    int j = 0;
    while (i < paramInt1 + paramInt2)
    {
      j = j * 31 + paramArrayOfChar[i];
      i++;
    }
    int k = j ^ (j >>> 20 ^ j >>> 12);
    int m = (k ^ (k >>> 7 ^ k >>> 4)) & -1 + this.a.length;
    String str1 = this.a[m];
    if (str1 != null)
    {
      int n = str1.length();
      int i1 = 0;
      if (n == paramInt2)
      {
        while (i1 < paramInt2)
        {
          if (str1.charAt(i1) != paramArrayOfChar[(paramInt1 + i1)])
          {
            String str3 = new String(paramArrayOfChar, paramInt1, paramInt2);
            this.a[m] = str3;
            return str3;
          }
          i1++;
        }
        return str1;
      }
    }
    String str2 = new String(paramArrayOfChar, paramInt1, paramInt2);
    this.a[m] = str2;
    return str2;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.a.d.f
 * JD-Core Version:    0.6.1
 */