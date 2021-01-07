package android.support.v4.graphics;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import java.util.Objects;

public final class ColorUtils
{
  private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
  private static final int MIN_ALPHA_SEARCH_PRECISION = 1;
  private static final ThreadLocal<double[]> TEMP_ARRAY = new ThreadLocal();
  private static final double XYZ_EPSILON = 0.008855999999999999D;
  private static final double XYZ_KAPPA = 903.29999999999995D;
  private static final double XYZ_WHITE_REFERENCE_X = 95.046999999999997D;
  private static final double XYZ_WHITE_REFERENCE_Y = 100.0D;
  private static final double XYZ_WHITE_REFERENCE_Z = 108.883D;

  @ColorInt
  public static int HSLToColor(@NonNull float[] paramArrayOfFloat)
  {
    float f1 = paramArrayOfFloat[0];
    float f2 = paramArrayOfFloat[1];
    float f3 = paramArrayOfFloat[2];
    float f4 = f2 * (1.0F - Math.abs(f3 * 2.0F - 1.0F));
    float f5 = f3 - 0.5F * f4;
    float f6 = f4 * (1.0F - Math.abs(f1 / 60.0F % 2.0F - 1.0F));
    int i;
    int j;
    int k;
    switch ((int)f1 / 60)
    {
    default:
      i = 0;
      j = 0;
      k = 0;
      break;
    case 5:
    case 6:
      i = Math.round(255.0F * (f4 + f5));
      j = Math.round(f5 * 255.0F);
      k = Math.round(255.0F * (f6 + f5));
      break;
    case 4:
      i = Math.round(255.0F * (f6 + f5));
      j = Math.round(f5 * 255.0F);
      k = Math.round(255.0F * (f4 + f5));
      break;
    case 3:
      i = Math.round(f5 * 255.0F);
      j = Math.round(255.0F * (f6 + f5));
      k = Math.round(255.0F * (f4 + f5));
      break;
    case 2:
      i = Math.round(f5 * 255.0F);
      j = Math.round(255.0F * (f4 + f5));
      k = Math.round(255.0F * (f6 + f5));
      break;
    case 1:
      i = Math.round(255.0F * (f6 + f5));
      j = Math.round(255.0F * (f4 + f5));
      k = Math.round(f5 * 255.0F);
      break;
    case 0:
      i = Math.round(255.0F * (f4 + f5));
      j = Math.round(255.0F * (f6 + f5));
      k = Math.round(f5 * 255.0F);
    }
    return Color.rgb(constrain(i, 0, 255), constrain(j, 0, 255), constrain(k, 0, 255));
  }

  @ColorInt
  public static int LABToColor(@FloatRange(from=0.0D, to=100.0D) double paramDouble1, @FloatRange(from=-128.0D, to=127.0D) double paramDouble2, @FloatRange(from=-128.0D, to=127.0D) double paramDouble3)
  {
    double[] arrayOfDouble = getTempDouble3Array();
    LABToXYZ(paramDouble1, paramDouble2, paramDouble3, arrayOfDouble);
    return XYZToColor(arrayOfDouble[0], arrayOfDouble[1], arrayOfDouble[2]);
  }

  public static void LABToXYZ(@FloatRange(from=0.0D, to=100.0D) double paramDouble1, @FloatRange(from=-128.0D, to=127.0D) double paramDouble2, @FloatRange(from=-128.0D, to=127.0D) double paramDouble3, @NonNull double[] paramArrayOfDouble)
  {
    double d1 = (paramDouble1 + 16.0D) / 116.0D;
    double d2 = d1 + paramDouble2 / 500.0D;
    double d3 = d1 - paramDouble3 / 200.0D;
    double d4 = Math.pow(d2, 3.0D);
    if (d4 <= 0.008855999999999999D)
      d4 = (d2 * 116.0D - 16.0D) / 903.29999999999995D;
    double d5;
    if (paramDouble1 > 7.999624799999999D)
      d5 = Math.pow(d1, 3.0D);
    else
      d5 = paramDouble1 / 903.29999999999995D;
    double d6 = Math.pow(d3, 3.0D);
    if (d6 <= 0.008855999999999999D)
      d6 = (d3 * 116.0D - 16.0D) / 903.29999999999995D;
    paramArrayOfDouble[0] = (d4 * 95.046999999999997D);
    paramArrayOfDouble[1] = (d5 * 100.0D);
    paramArrayOfDouble[2] = (d6 * 108.883D);
  }

  public static void RGBToHSL(@IntRange(from=0L, to=255L) int paramInt1, @IntRange(from=0L, to=255L) int paramInt2, @IntRange(from=0L, to=255L) int paramInt3, @NonNull float[] paramArrayOfFloat)
  {
    float f1 = paramInt1 / 255.0F;
    float f2 = paramInt2 / 255.0F;
    float f3 = paramInt3 / 255.0F;
    float f4 = Math.max(f1, Math.max(f2, f3));
    float f5 = Math.min(f1, Math.min(f2, f3));
    float f6 = f4 - f5;
    float f7 = (f4 + f5) / 2.0F;
    float f8;
    float f9;
    if (f4 == f5)
    {
      f8 = 0.0F;
      f9 = 0.0F;
    }
    else
    {
      if (f4 == f1)
        f8 = (f2 - f3) / f6 % 6.0F;
      else if (f4 == f2)
        f8 = 2.0F + (f3 - f1) / f6;
      else
        f8 = 4.0F + (f1 - f2) / f6;
      f9 = f6 / (1.0F - Math.abs(2.0F * f7 - 1.0F));
    }
    float f10 = f8 * 60.0F % 360.0F;
    if (f10 < 0.0F)
      f10 += 360.0F;
    paramArrayOfFloat[0] = constrain(f10, 0.0F, 360.0F);
    paramArrayOfFloat[1] = constrain(f9, 0.0F, 1.0F);
    paramArrayOfFloat[2] = constrain(f7, 0.0F, 1.0F);
  }

  public static void RGBToLAB(@IntRange(from=0L, to=255L) int paramInt1, @IntRange(from=0L, to=255L) int paramInt2, @IntRange(from=0L, to=255L) int paramInt3, @NonNull double[] paramArrayOfDouble)
  {
    RGBToXYZ(paramInt1, paramInt2, paramInt3, paramArrayOfDouble);
    XYZToLAB(paramArrayOfDouble[0], paramArrayOfDouble[1], paramArrayOfDouble[2], paramArrayOfDouble);
  }

  public static void RGBToXYZ(@IntRange(from=0L, to=255L) int paramInt1, @IntRange(from=0L, to=255L) int paramInt2, @IntRange(from=0L, to=255L) int paramInt3, @NonNull double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble.length != 3)
      throw new IllegalArgumentException("outXyz must have a length of 3.");
    double d1 = paramInt1 / 255.0D;
    if (d1 < 0.04045D);
    double d3;
    for (double d2 = d1 / 12.92D; ; d2 = Math.pow((d1 + 0.055D) / 1.055D, 2.4D))
    {
      d3 = d2;
      break;
    }
    double d4 = paramInt2 / 255.0D;
    if (d4 < 0.04045D);
    double d6;
    for (double d5 = d4 / 12.92D; ; d5 = Math.pow((d4 + 0.055D) / 1.055D, 2.4D))
    {
      d6 = d5;
      break;
    }
    double d7 = paramInt3 / 255.0D;
    double d8;
    if (d7 < 0.04045D)
      d8 = d7 / 12.92D;
    else
      d8 = Math.pow((d7 + 0.055D) / 1.055D, 2.4D);
    paramArrayOfDouble[0] = (100.0D * (0.4124D * d3 + 0.3576D * d6 + 0.1805D * d8));
    paramArrayOfDouble[1] = (100.0D * (0.2126D * d3 + 0.7152D * d6 + 0.0722D * d8));
    paramArrayOfDouble[2] = (100.0D * (d3 * 0.0193D + d6 * 0.1192D + d8 * 0.9505D));
  }

  @ColorInt
  public static int XYZToColor(@FloatRange(from=0.0D, to=95.046999999999997D) double paramDouble1, @FloatRange(from=0.0D, to=100.0D) double paramDouble2, @FloatRange(from=0.0D, to=108.883D) double paramDouble3)
  {
    double d1 = (3.2406D * paramDouble1 + -1.5372D * paramDouble2 + -0.4986D * paramDouble3) / 100.0D;
    double d2 = (-0.9689D * paramDouble1 + 1.8758D * paramDouble2 + 0.0415D * paramDouble3) / 100.0D;
    double d3 = (paramDouble1 * 0.0557D + paramDouble2 * -0.204D + 1.057D * paramDouble3) / 100.0D;
    double d4;
    if (d1 > 0.0031308D)
      d4 = 1.055D * Math.pow(d1, 0.4166666666666667D) - 0.055D;
    else
      d4 = d1 * 12.92D;
    double d5;
    if (d2 > 0.0031308D)
      d5 = 1.055D * Math.pow(d2, 0.4166666666666667D) - 0.055D;
    else
      d5 = d2 * 12.92D;
    double d6;
    if (d3 > 0.0031308D)
      d6 = 1.055D * Math.pow(d3, 0.4166666666666667D) - 0.055D;
    else
      d6 = d3 * 12.92D;
    return Color.rgb(constrain((int)Math.round(d4 * 255.0D), 0, 255), constrain((int)Math.round(d5 * 255.0D), 0, 255), constrain((int)Math.round(d6 * 255.0D), 0, 255));
  }

  public static void XYZToLAB(@FloatRange(from=0.0D, to=95.046999999999997D) double paramDouble1, @FloatRange(from=0.0D, to=100.0D) double paramDouble2, @FloatRange(from=0.0D, to=108.883D) double paramDouble3, @NonNull double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble.length != 3)
      throw new IllegalArgumentException("outLab must have a length of 3.");
    double d1 = pivotXyzComponent(paramDouble1 / 95.046999999999997D);
    double d2 = pivotXyzComponent(paramDouble2 / 100.0D);
    double d3 = pivotXyzComponent(paramDouble3 / 108.883D);
    paramArrayOfDouble[0] = Math.max(0.0D, 116.0D * d2 - 16.0D);
    paramArrayOfDouble[1] = (500.0D * (d1 - d2));
    paramArrayOfDouble[2] = (200.0D * (d2 - d3));
  }

  @ColorInt
  public static int blendARGB(@ColorInt int paramInt1, @ColorInt int paramInt2, @FloatRange(from=0.0D, to=1.0D) float paramFloat)
  {
    float f1 = 1.0F - paramFloat;
    float f2 = f1 * Color.alpha(paramInt1) + paramFloat * Color.alpha(paramInt2);
    float f3 = f1 * Color.red(paramInt1) + paramFloat * Color.red(paramInt2);
    float f4 = f1 * Color.green(paramInt1) + paramFloat * Color.green(paramInt2);
    float f5 = f1 * Color.blue(paramInt1) + paramFloat * Color.blue(paramInt2);
    return Color.argb((int)f2, (int)f3, (int)f4, (int)f5);
  }

  public static void blendHSL(@NonNull float[] paramArrayOfFloat1, @NonNull float[] paramArrayOfFloat2, @FloatRange(from=0.0D, to=1.0D) float paramFloat, @NonNull float[] paramArrayOfFloat3)
  {
    if (paramArrayOfFloat3.length != 3)
      throw new IllegalArgumentException("result must have a length of 3.");
    float f = 1.0F - paramFloat;
    paramArrayOfFloat3[0] = circularInterpolate(paramArrayOfFloat1[0], paramArrayOfFloat2[0], paramFloat);
    paramArrayOfFloat3[1] = (f * paramArrayOfFloat1[1] + paramFloat * paramArrayOfFloat2[1]);
    paramArrayOfFloat3[2] = (f * paramArrayOfFloat1[2] + paramFloat * paramArrayOfFloat2[2]);
  }

  public static void blendLAB(@NonNull double[] paramArrayOfDouble1, @NonNull double[] paramArrayOfDouble2, @FloatRange(from=0.0D, to=1.0D) double paramDouble, @NonNull double[] paramArrayOfDouble3)
  {
    if (paramArrayOfDouble3.length != 3)
      throw new IllegalArgumentException("outResult must have a length of 3.");
    double d = 1.0D - paramDouble;
    paramArrayOfDouble3[0] = (d * paramArrayOfDouble1[0] + paramDouble * paramArrayOfDouble2[0]);
    paramArrayOfDouble3[1] = (d * paramArrayOfDouble1[1] + paramDouble * paramArrayOfDouble2[1]);
    paramArrayOfDouble3[2] = (d * paramArrayOfDouble1[2] + paramDouble * paramArrayOfDouble2[2]);
  }

  public static double calculateContrast(@ColorInt int paramInt1, @ColorInt int paramInt2)
  {
    if (Color.alpha(paramInt2) != 255)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("background can not be translucent: #");
      localStringBuilder.append(Integer.toHexString(paramInt2));
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    if (Color.alpha(paramInt1) < 255)
      paramInt1 = compositeColors(paramInt1, paramInt2);
    double d1 = 0.05D + calculateLuminance(paramInt1);
    double d2 = 0.05D + calculateLuminance(paramInt2);
    return Math.max(d1, d2) / Math.min(d1, d2);
  }

  @FloatRange(from=0.0D, to=1.0D)
  public static double calculateLuminance(@ColorInt int paramInt)
  {
    double[] arrayOfDouble = getTempDouble3Array();
    colorToXYZ(paramInt, arrayOfDouble);
    return arrayOfDouble[1] / 100.0D;
  }

  public static int calculateMinimumAlpha(@ColorInt int paramInt1, @ColorInt int paramInt2, float paramFloat)
  {
    int i = Color.alpha(paramInt2);
    int j = 255;
    if (i != j)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("background can not be translucent: #");
      localStringBuilder.append(Integer.toHexString(paramInt2));
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    double d1 = calculateContrast(setAlphaComponent(paramInt1, j), paramInt2);
    double d2 = paramFloat;
    if (d1 < d2)
      return -1;
    int k = 0;
    int m = 0;
    while ((k <= 10) && (j - m > 1))
    {
      int n = (m + j) / 2;
      if (calculateContrast(setAlphaComponent(paramInt1, n), paramInt2) < d2)
        m = n;
      else
        j = n;
      k++;
    }
    return j;
  }

  @VisibleForTesting
  static float circularInterpolate(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (Math.abs(paramFloat2 - paramFloat1) > 180.0F)
      if (paramFloat2 > paramFloat1)
        paramFloat1 += 360.0F;
      else
        paramFloat2 += 360.0F;
    return (paramFloat1 + paramFloat3 * (paramFloat2 - paramFloat1)) % 360.0F;
  }

  public static void colorToHSL(@ColorInt int paramInt, @NonNull float[] paramArrayOfFloat)
  {
    RGBToHSL(Color.red(paramInt), Color.green(paramInt), Color.blue(paramInt), paramArrayOfFloat);
  }

  public static void colorToLAB(@ColorInt int paramInt, @NonNull double[] paramArrayOfDouble)
  {
    RGBToLAB(Color.red(paramInt), Color.green(paramInt), Color.blue(paramInt), paramArrayOfDouble);
  }

  public static void colorToXYZ(@ColorInt int paramInt, @NonNull double[] paramArrayOfDouble)
  {
    RGBToXYZ(Color.red(paramInt), Color.green(paramInt), Color.blue(paramInt), paramArrayOfDouble);
  }

  private static int compositeAlpha(int paramInt1, int paramInt2)
  {
    return 255 - (255 - paramInt2) * (255 - paramInt1) / 255;
  }

  public static int compositeColors(@ColorInt int paramInt1, @ColorInt int paramInt2)
  {
    int i = Color.alpha(paramInt2);
    int j = Color.alpha(paramInt1);
    int k = compositeAlpha(j, i);
    return Color.argb(k, compositeComponent(Color.red(paramInt1), j, Color.red(paramInt2), i, k), compositeComponent(Color.green(paramInt1), j, Color.green(paramInt2), i, k), compositeComponent(Color.blue(paramInt1), j, Color.blue(paramInt2), i, k));
  }

  @NonNull
  @RequiresApi(26)
  public static Color compositeColors(@NonNull Color paramColor1, @NonNull Color paramColor2)
  {
    if (!Objects.equals(paramColor1.getModel(), paramColor2.getModel()))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Color models must match (");
      localStringBuilder.append(paramColor1.getModel());
      localStringBuilder.append(" vs. ");
      localStringBuilder.append(paramColor2.getModel());
      localStringBuilder.append(")");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    if (!Objects.equals(paramColor2.getColorSpace(), paramColor1.getColorSpace()))
      paramColor1 = paramColor1.convert(paramColor2.getColorSpace());
    float[] arrayOfFloat1 = paramColor1.getComponents();
    float[] arrayOfFloat2 = paramColor2.getComponents();
    float f1 = paramColor1.alpha();
    float f2 = paramColor2.alpha() * (1.0F - f1);
    int i = -1 + paramColor2.getComponentCount();
    arrayOfFloat2[i] = (f1 + f2);
    if (arrayOfFloat2[i] > 0.0F)
    {
      f1 /= arrayOfFloat2[i];
      f2 /= arrayOfFloat2[i];
    }
    for (int j = 0; j < i; j++)
      arrayOfFloat2[j] = (f1 * arrayOfFloat1[j] + f2 * arrayOfFloat2[j]);
    return Color.valueOf(arrayOfFloat2, paramColor2.getColorSpace());
  }

  private static int compositeComponent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if (paramInt5 == 0)
      return 0;
    return (paramInt2 * (paramInt1 * 255) + paramInt3 * paramInt4 * (255 - paramInt2)) / (paramInt5 * 255);
  }

  private static float constrain(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat1 < paramFloat2)
      paramFloat1 = paramFloat2;
    else if (paramFloat1 > paramFloat3)
      paramFloat1 = paramFloat3;
    return paramFloat1;
  }

  private static int constrain(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 < paramInt2)
      paramInt1 = paramInt2;
    else if (paramInt1 > paramInt3)
      paramInt1 = paramInt3;
    return paramInt1;
  }

  public static double distanceEuclidean(@NonNull double[] paramArrayOfDouble1, @NonNull double[] paramArrayOfDouble2)
  {
    return Math.sqrt(Math.pow(paramArrayOfDouble1[0] - paramArrayOfDouble2[0], 2.0D) + Math.pow(paramArrayOfDouble1[1] - paramArrayOfDouble2[1], 2.0D) + Math.pow(paramArrayOfDouble1[2] - paramArrayOfDouble2[2], 2.0D));
  }

  private static double[] getTempDouble3Array()
  {
    double[] arrayOfDouble = (double[])TEMP_ARRAY.get();
    if (arrayOfDouble == null)
    {
      arrayOfDouble = new double[3];
      TEMP_ARRAY.set(arrayOfDouble);
    }
    return arrayOfDouble;
  }

  private static double pivotXyzComponent(double paramDouble)
  {
    double d;
    if (paramDouble > 0.008855999999999999D)
      d = Math.pow(paramDouble, 0.3333333333333333D);
    else
      d = (16.0D + paramDouble * 903.29999999999995D) / 116.0D;
    return d;
  }

  @ColorInt
  public static int setAlphaComponent(@ColorInt int paramInt1, @IntRange(from=0L, to=255L) int paramInt2)
  {
    if ((paramInt2 >= 0) && (paramInt2 <= 255))
      return paramInt1 & 0xFFFFFF | paramInt2 << 24;
    throw new IllegalArgumentException("alpha must be between 0 and 255.");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.ColorUtils
 * JD-Core Version:    0.6.1
 */