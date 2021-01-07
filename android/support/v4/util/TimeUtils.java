package android.support.v4.util;

import android.support.annotation.RestrictTo;
import java.io.PrintWriter;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public final class TimeUtils
{

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static final int HUNDRED_DAY_FIELD_LEN = 19;
  private static final int SECONDS_PER_DAY = 86400;
  private static final int SECONDS_PER_HOUR = 3600;
  private static final int SECONDS_PER_MINUTE = 60;
  private static char[] sFormatStr = new char[24];
  private static final Object sFormatSync = new Object();

  private static int accumField(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    if ((paramInt1 <= 99) && ((!paramBoolean) || (paramInt3 < 3)))
    {
      if ((paramInt1 <= 9) && ((!paramBoolean) || (paramInt3 < 2)))
      {
        if ((!paramBoolean) && (paramInt1 <= 0))
          return 0;
        return paramInt2 + 1;
      }
      return paramInt2 + 2;
    }
    return paramInt2 + 3;
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static void formatDuration(long paramLong1, long paramLong2, PrintWriter paramPrintWriter)
  {
    if (paramLong1 == 0L)
    {
      paramPrintWriter.print("--");
      return;
    }
    formatDuration(paramLong1 - paramLong2, paramPrintWriter, 0);
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static void formatDuration(long paramLong, PrintWriter paramPrintWriter)
  {
    formatDuration(paramLong, paramPrintWriter, 0);
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static void formatDuration(long paramLong, PrintWriter paramPrintWriter, int paramInt)
  {
    synchronized (sFormatSync)
    {
      int i = formatDurationLocked(paramLong, paramInt);
      paramPrintWriter.print(new String(sFormatStr, 0, i));
      return;
    }
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static void formatDuration(long paramLong, StringBuilder paramStringBuilder)
  {
    synchronized (sFormatSync)
    {
      int i = formatDurationLocked(paramLong, 0);
      paramStringBuilder.append(sFormatStr, 0, i);
      return;
    }
  }

  private static int formatDurationLocked(long paramLong, int paramInt)
  {
    long l = paramLong;
    if (sFormatStr.length < paramInt)
      sFormatStr = new char[paramInt];
    char[] arrayOfChar = sFormatStr;
    if (l == 0L)
    {
      int i22 = paramInt - 1;
      while (i22 > 0)
        arrayOfChar[0] = ' ';
      arrayOfChar[0] = '0';
      return 1;
    }
    int i;
    if (l > 0L)
    {
      i = 43;
    }
    else
    {
      i = 45;
      l = -l;
    }
    int j = (int)(l % 1000L);
    int k = (int)Math.floor(l / 1000L);
    int m;
    if (k > 86400)
    {
      m = k / 86400;
      k -= 86400 * m;
    }
    else
    {
      m = 0;
    }
    int n;
    if (k > 3600)
    {
      n = k / 3600;
      k -= n * 3600;
    }
    else
    {
      n = 0;
    }
    int i1;
    int i2;
    if (k > 60)
    {
      int i21 = k / 60;
      i1 = k - i21 * 60;
      i2 = i21;
    }
    else
    {
      i1 = k;
      i2 = 0;
    }
    if (paramInt != 0)
    {
      int i15 = accumField(m, 1, false, 0);
      boolean bool4;
      if (i15 > 0)
        bool4 = true;
      else
        bool4 = false;
      int i16 = i15 + accumField(n, 1, bool4, 2);
      boolean bool5;
      if (i16 > 0)
        bool5 = true;
      else
        bool5 = false;
      int i17 = i16 + accumField(i2, 1, bool5, 2);
      boolean bool6;
      if (i17 > 0)
        bool6 = true;
      else
        bool6 = false;
      int i18 = i17 + accumField(i1, 1, bool6, 2);
      int i19;
      if (i18 > 0)
        i19 = 3;
      else
        i19 = 0;
      int i20 = i18 + (1 + accumField(j, 2, true, i19));
      i3 = 0;
      while (i20 < paramInt)
      {
        arrayOfChar[i3] = ' ';
        i3++;
        i20++;
      }
    }
    int i3 = 0;
    arrayOfChar[i3] = i;
    int i4 = i3 + 1;
    int i5;
    if (paramInt != 0)
      i5 = 1;
    else
      i5 = 0;
    int i6 = printField(arrayOfChar, m, 'd', i4, false, 0);
    boolean bool1;
    if (i6 != i4)
      bool1 = true;
    else
      bool1 = false;
    int i7;
    if (i5 != 0)
      i7 = 2;
    else
      i7 = 0;
    int i8 = printField(arrayOfChar, n, 'h', i6, bool1, i7);
    boolean bool2;
    if (i8 != i4)
      bool2 = true;
    else
      bool2 = false;
    int i9;
    if (i5 != 0)
      i9 = 2;
    else
      i9 = 0;
    int i10 = printField(arrayOfChar, i2, 'm', i8, bool2, i9);
    boolean bool3;
    if (i10 != i4)
      bool3 = true;
    else
      bool3 = false;
    int i11;
    if (i5 != 0)
      i11 = 2;
    else
      i11 = 0;
    int i12 = printField(arrayOfChar, i1, 's', i10, bool3, i11);
    int i13;
    if ((i5 != 0) && (i12 != i4))
      i13 = 3;
    else
      i13 = 0;
    int i14 = printField(arrayOfChar, j, 'm', i12, true, i13);
    arrayOfChar[i14] = 's';
    return i14 + 1;
  }

  private static int printField(char[] paramArrayOfChar, int paramInt1, char paramChar, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    if ((paramBoolean) || (paramInt1 > 0))
    {
      int i;
      if (((paramBoolean) && (paramInt3 >= 3)) || (paramInt1 > 99))
      {
        int m = paramInt1 / 100;
        paramArrayOfChar[paramInt2] = (char)(m + 48);
        i = paramInt2 + 1;
        paramInt1 -= m * 100;
      }
      else
      {
        i = paramInt2;
      }
      if (((paramBoolean) && (paramInt3 >= 2)) || (paramInt1 > 9) || (paramInt2 != i))
      {
        int j = paramInt1 / 10;
        paramArrayOfChar[i] = (char)(j + 48);
        i++;
        paramInt1 -= j * 10;
      }
      paramArrayOfChar[i] = (char)(paramInt1 + 48);
      int k = i + 1;
      paramArrayOfChar[k] = paramChar;
      paramInt2 = k + 1;
    }
    return paramInt2;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.util.TimeUtils
 * JD-Core Version:    0.6.1
 */