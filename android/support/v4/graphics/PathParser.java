package android.support.v4.graphics;

import android.graphics.Path;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.util.ArrayList;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class PathParser
{
  private static final String LOGTAG = "PathParser";

  private static void addNode(ArrayList<PathDataNode> paramArrayList, char paramChar, float[] paramArrayOfFloat)
  {
    paramArrayList.add(new PathDataNode(paramChar, paramArrayOfFloat));
  }

  public static boolean canMorph(PathDataNode[] paramArrayOfPathDataNode1, PathDataNode[] paramArrayOfPathDataNode2)
  {
    if ((paramArrayOfPathDataNode1 != null) && (paramArrayOfPathDataNode2 != null))
    {
      if (paramArrayOfPathDataNode1.length != paramArrayOfPathDataNode2.length)
        return false;
      int i = 0;
      while (i < paramArrayOfPathDataNode1.length)
        if ((paramArrayOfPathDataNode1[i].mType == paramArrayOfPathDataNode2[i].mType) && (paramArrayOfPathDataNode1[i].mParams.length == paramArrayOfPathDataNode2[i].mParams.length))
          i++;
        else
          return false;
      return true;
    }
    return false;
  }

  static float[] copyOfRange(float[] paramArrayOfFloat, int paramInt1, int paramInt2)
  {
    if (paramInt1 > paramInt2)
      throw new IllegalArgumentException();
    int i = paramArrayOfFloat.length;
    if ((paramInt1 >= 0) && (paramInt1 <= i))
    {
      int j = paramInt2 - paramInt1;
      int k = Math.min(j, i - paramInt1);
      float[] arrayOfFloat = new float[j];
      System.arraycopy(paramArrayOfFloat, paramInt1, arrayOfFloat, 0, k);
      return arrayOfFloat;
    }
    throw new ArrayIndexOutOfBoundsException();
  }

  public static PathDataNode[] createNodesFromPathData(String paramString)
  {
    if (paramString == null)
      return null;
    ArrayList localArrayList = new ArrayList();
    int i = 1;
    int j = 0;
    while (i < paramString.length())
    {
      int k = nextStart(paramString, i);
      String str = paramString.substring(j, k).trim();
      if (str.length() > 0)
      {
        float[] arrayOfFloat = getFloats(str);
        addNode(localArrayList, str.charAt(0), arrayOfFloat);
      }
      int m = k + 1;
      j = k;
      i = m;
    }
    if ((i - j == 1) && (j < paramString.length()))
      addNode(localArrayList, paramString.charAt(j), new float[0]);
    return (PathDataNode[])localArrayList.toArray(new PathDataNode[localArrayList.size()]);
  }

  public static Path createPathFromPathData(String paramString)
  {
    Path localPath = new Path();
    PathDataNode[] arrayOfPathDataNode = createNodesFromPathData(paramString);
    if (arrayOfPathDataNode != null)
      try
      {
        PathDataNode.nodesToPath(arrayOfPathDataNode, localPath);
        return localPath;
      }
      catch (RuntimeException localRuntimeException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error in parsing ");
        localStringBuilder.append(paramString);
        throw new RuntimeException(localStringBuilder.toString(), localRuntimeException);
      }
    return null;
  }

  public static PathDataNode[] deepCopyNodes(PathDataNode[] paramArrayOfPathDataNode)
  {
    if (paramArrayOfPathDataNode == null)
      return null;
    PathDataNode[] arrayOfPathDataNode = new PathDataNode[paramArrayOfPathDataNode.length];
    for (int i = 0; i < paramArrayOfPathDataNode.length; i++)
      arrayOfPathDataNode[i] = new PathDataNode(paramArrayOfPathDataNode[i]);
    return arrayOfPathDataNode;
  }

  private static void extract(String paramString, int paramInt, ExtractFloatResult paramExtractFloatResult)
  {
    paramExtractFloatResult.mEndWithNegOrDot = false;
    int i = paramInt;
    int j = 0;
    int k = 0;
    int m = 0;
    while (i < paramString.length())
    {
      int n = paramString.charAt(i);
      if (n != 32)
      {
        if ((n != 69) && (n != 101));
        switch (n)
        {
        default:
          break;
        case 46:
          if (k == 0)
          {
            k = 1;
            j = 0;
            break label141;
          }
          paramExtractFloatResult.mEndWithNegOrDot = true;
          break;
        case 45:
          if ((i != paramInt) && (j == 0))
          {
            paramExtractFloatResult.mEndWithNegOrDot = true;
          }
          else
          {
            j = 0;
            break label141;
            j = 1;
          }
          break;
        case 44:
        }
      }
      j = 0;
      m = 1;
      label141: if (m != 0)
        break;
      i++;
    }
    paramExtractFloatResult.mEndPosition = i;
  }

  private static float[] getFloats(String paramString)
  {
    if ((paramString.charAt(0) != 'z') && (paramString.charAt(0) != 'Z'));
    while (true)
    {
      int m;
      try
      {
        float[] arrayOfFloat1 = new float[paramString.length()];
        ExtractFloatResult localExtractFloatResult = new ExtractFloatResult();
        int i = paramString.length();
        j = 1;
        int k = 0;
        if (j < i)
        {
          extract(paramString, j, localExtractFloatResult);
          m = localExtractFloatResult.mEndPosition;
          if (j < m)
          {
            int n = k + 1;
            arrayOfFloat1[k] = Float.parseFloat(paramString.substring(j, m));
            k = n;
          }
          if (!localExtractFloatResult.mEndWithNegOrDot)
            break label181;
          j = m;
          continue;
        }
        float[] arrayOfFloat2 = copyOfRange(arrayOfFloat1, 0, k);
        return arrayOfFloat2;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("error in parsing \"");
        localStringBuilder.append(paramString);
        localStringBuilder.append("\"");
        throw new RuntimeException(localStringBuilder.toString(), localNumberFormatException);
      }
      return new float[0];
      label181: int j = m + 1;
    }
  }

  private static int nextStart(String paramString, int paramInt)
  {
    while (paramInt < paramString.length())
    {
      int i = paramString.charAt(paramInt);
      if ((((i - 65) * (i - 90) <= 0) || ((i - 97) * (i - 122) <= 0)) && (i != 101) && (i != 69))
        return paramInt;
      paramInt++;
    }
    return paramInt;
  }

  public static void updateNodes(PathDataNode[] paramArrayOfPathDataNode1, PathDataNode[] paramArrayOfPathDataNode2)
  {
    for (int i = 0; i < paramArrayOfPathDataNode2.length; i++)
    {
      paramArrayOfPathDataNode1[i].mType = paramArrayOfPathDataNode2[i].mType;
      for (int j = 0; j < paramArrayOfPathDataNode2[i].mParams.length; j++)
        paramArrayOfPathDataNode1[i].mParams[j] = paramArrayOfPathDataNode2[i].mParams[j];
    }
  }

  private static class ExtractFloatResult
  {
    int mEndPosition;
    boolean mEndWithNegOrDot;
  }

  public static class PathDataNode
  {

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public float[] mParams;

    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public char mType;

    PathDataNode(char paramChar, float[] paramArrayOfFloat)
    {
      this.mType = paramChar;
      this.mParams = paramArrayOfFloat;
    }

    PathDataNode(PathDataNode paramPathDataNode)
    {
      this.mType = paramPathDataNode.mType;
      this.mParams = PathParser.copyOfRange(paramPathDataNode.mParams, 0, paramPathDataNode.mParams.length);
    }

    private static void addCommand(Path paramPath, float[] paramArrayOfFloat1, char paramChar1, char paramChar2, float[] paramArrayOfFloat2)
    {
      float f1 = paramArrayOfFloat1[0];
      float f2 = paramArrayOfFloat1[1];
      float f3 = paramArrayOfFloat1[2];
      float f4 = paramArrayOfFloat1[3];
      float f5 = paramArrayOfFloat1[4];
      float f6 = paramArrayOfFloat1[5];
      int i;
      switch (paramChar2)
      {
      case 'L':
      case 'M':
      case 'T':
      case 'l':
      case 'm':
      case 't':
      default:
      case 'Z':
      case 'z':
        while (true)
        {
          i = 2;
          break;
          paramPath.close();
          paramPath.moveTo(f5, f6);
          f1 = f5;
          f3 = f1;
          f2 = f6;
          f4 = f2;
        }
      case 'Q':
      case 'S':
      case 'q':
      case 's':
        i = 4;
        break;
      case 'H':
      case 'V':
      case 'h':
      case 'v':
        i = 1;
        break;
      case 'C':
      case 'c':
        i = 6;
        break;
      case 'A':
      case 'a':
        i = 7;
      }
      float f7 = f1;
      float f8 = f2;
      float f9 = f5;
      float f10 = f6;
      int j = 0;
      for (int k = paramChar1; j < paramArrayOfFloat2.length; k = paramChar2)
      {
        label1170: float f32;
        float f33;
        switch (paramChar2)
        {
        default:
        case 'v':
        case 't':
        case 's':
        case 'q':
        case 'm':
        case 'l':
        case 'h':
        case 'c':
          while (true)
          {
            m = j;
            break;
            int i43 = j + 0;
            paramPath.rLineTo(0.0F, paramArrayOfFloat2[i43]);
            f8 += paramArrayOfFloat2[i43];
            continue;
            float f74;
            float f73;
            if ((k != 113) && (k != 116) && (k != 81) && (k != 84))
            {
              f74 = 0.0F;
              f73 = 0.0F;
            }
            else
            {
              f73 = f7 - f3;
              f74 = f8 - f4;
            }
            int i41 = j + 0;
            float f75 = paramArrayOfFloat2[i41];
            int i42 = j + 1;
            paramPath.rQuadTo(f73, f74, f75, paramArrayOfFloat2[i42]);
            float f76 = f73 + f7;
            float f77 = f74 + f8;
            f7 += paramArrayOfFloat2[i41];
            f8 += paramArrayOfFloat2[i42];
            f4 = f77;
            f3 = f76;
            continue;
            float f69;
            float f68;
            if ((k != 99) && (k != 115) && (k != 67) && (k != 83))
            {
              f69 = 0.0F;
              f68 = 0.0F;
            }
            else
            {
              float f67 = f7 - f3;
              f68 = f8 - f4;
              f69 = f67;
            }
            int i37 = j + 0;
            float f70 = paramArrayOfFloat2[i37];
            int i38 = j + 1;
            float f71 = paramArrayOfFloat2[i38];
            int i39 = j + 2;
            float f72 = paramArrayOfFloat2[i39];
            int i40 = j + 3;
            paramPath.rCubicTo(f69, f68, f70, f71, f72, paramArrayOfFloat2[i40]);
            float f61 = f7 + paramArrayOfFloat2[i37];
            float f62 = f8 + paramArrayOfFloat2[i38];
            f7 += paramArrayOfFloat2[i39];
            f8 += paramArrayOfFloat2[i40];
            break label1170;
            int i33 = j + 0;
            float f64 = paramArrayOfFloat2[i33];
            int i34 = j + 1;
            float f65 = paramArrayOfFloat2[i34];
            int i35 = j + 2;
            float f66 = paramArrayOfFloat2[i35];
            int i36 = j + 3;
            paramPath.rQuadTo(f64, f65, f66, paramArrayOfFloat2[i36]);
            f61 = f7 + paramArrayOfFloat2[i33];
            f62 = f8 + paramArrayOfFloat2[i34];
            f7 += paramArrayOfFloat2[i35];
            f8 += paramArrayOfFloat2[i36];
            break label1170;
            int i31 = j + 0;
            f7 += paramArrayOfFloat2[i31];
            int i32 = j + 1;
            f8 += paramArrayOfFloat2[i32];
            if (j > 0)
            {
              paramPath.rLineTo(paramArrayOfFloat2[i31], paramArrayOfFloat2[i32]);
            }
            else
            {
              paramPath.rMoveTo(paramArrayOfFloat2[i31], paramArrayOfFloat2[i32]);
              f10 = f8;
              f9 = f7;
              continue;
              int i29 = j + 0;
              float f63 = paramArrayOfFloat2[i29];
              int i30 = j + 1;
              paramPath.rLineTo(f63, paramArrayOfFloat2[i30]);
              f7 += paramArrayOfFloat2[i29];
              f8 += paramArrayOfFloat2[i30];
              continue;
              int i28 = j + 0;
              paramPath.rLineTo(paramArrayOfFloat2[i28], 0.0F);
              f7 += paramArrayOfFloat2[i28];
              continue;
              float f56 = paramArrayOfFloat2[(j + 0)];
              float f57 = paramArrayOfFloat2[(j + 1)];
              int i24 = j + 2;
              float f58 = paramArrayOfFloat2[i24];
              int i25 = j + 3;
              float f59 = paramArrayOfFloat2[i25];
              int i26 = j + 4;
              float f60 = paramArrayOfFloat2[i26];
              int i27 = j + 5;
              paramPath.rCubicTo(f56, f57, f58, f59, f60, paramArrayOfFloat2[i27]);
              f61 = f7 + paramArrayOfFloat2[i24];
              f62 = f8 + paramArrayOfFloat2[i25];
              f7 += paramArrayOfFloat2[i26];
              f8 += paramArrayOfFloat2[i27];
              f3 = f61;
              f4 = f62;
            }
          }
        case 'a':
          int i22 = j + 5;
          float f47 = f7 + paramArrayOfFloat2[i22];
          int i23 = j + 6;
          float f48 = f8 + paramArrayOfFloat2[i23];
          float f49 = paramArrayOfFloat2[(j + 0)];
          float f50 = paramArrayOfFloat2[(j + 1)];
          float f51 = paramArrayOfFloat2[(j + 2)];
          boolean bool3;
          if (paramArrayOfFloat2[(j + 3)] != 0.0F)
            bool3 = true;
          else
            bool3 = false;
          boolean bool4;
          if (paramArrayOfFloat2[(j + 4)] != 0.0F)
            bool4 = true;
          else
            bool4 = false;
          float f52 = f7;
          float f53 = f8;
          float f54 = f8;
          float f55 = f7;
          boolean bool5 = bool3;
          m = j;
          drawArc(paramPath, f52, f53, f47, f48, f49, f50, f51, bool5, bool4);
          f7 = f55 + paramArrayOfFloat2[i22];
          f8 = f54 + paramArrayOfFloat2[i23];
          break;
        case 'V':
          float f46 = f7;
          m = j;
          int i21 = m + 0;
          paramPath.lineTo(f46, paramArrayOfFloat2[i21]);
          f8 = paramArrayOfFloat2[i21];
          break;
        case 'T':
          float f42 = f8;
          float f43 = f7;
          m = j;
          if ((k == 113) || (k == 116) || (k == 81) || (k == 84))
          {
            float f44 = f43 * 2.0F - f3;
            f42 = f42 * 2.0F - f4;
            f43 = f44;
          }
          int i19 = m + 0;
          float f45 = paramArrayOfFloat2[i19];
          int i20 = m + 1;
          paramPath.quadTo(f43, f42, f45, paramArrayOfFloat2[i20]);
          f7 = paramArrayOfFloat2[i19];
          f8 = paramArrayOfFloat2[i20];
          f3 = f43;
          f4 = f42;
          break;
        case 'S':
          float f34 = f8;
          float f35 = f7;
          m = j;
          float f38;
          float f37;
          if ((k != 99) && (k != 115) && (k != 67) && (k != 83))
          {
            f38 = f35;
            f37 = f34;
          }
          else
          {
            float f36 = f35 * 2.0F - f3;
            f37 = f34 * 2.0F - f4;
            f38 = f36;
          }
          int i15 = m + 0;
          float f39 = paramArrayOfFloat2[i15];
          int i16 = m + 1;
          float f40 = paramArrayOfFloat2[i16];
          int i17 = m + 2;
          float f41 = paramArrayOfFloat2[i17];
          int i18 = m + 3;
          paramPath.cubicTo(f38, f37, f39, f40, f41, paramArrayOfFloat2[i18]);
          f32 = paramArrayOfFloat2[i15];
          f33 = paramArrayOfFloat2[i16];
          f7 = paramArrayOfFloat2[i17];
          f8 = paramArrayOfFloat2[i18];
          break;
        case 'Q':
          m = j;
          int i11 = m + 0;
          float f29 = paramArrayOfFloat2[i11];
          int i12 = m + 1;
          float f30 = paramArrayOfFloat2[i12];
          int i13 = m + 2;
          float f31 = paramArrayOfFloat2[i13];
          int i14 = m + 3;
          paramPath.quadTo(f29, f30, f31, paramArrayOfFloat2[i14]);
          f32 = paramArrayOfFloat2[i11];
          f33 = paramArrayOfFloat2[i12];
          f7 = paramArrayOfFloat2[i13];
          f8 = paramArrayOfFloat2[i14];
          f3 = f32;
          f4 = f33;
          break;
        case 'M':
          m = j;
          int i9 = m + 0;
          f7 = paramArrayOfFloat2[i9];
          int i10 = m + 1;
          f8 = paramArrayOfFloat2[i10];
          if (m > 0)
          {
            paramPath.lineTo(paramArrayOfFloat2[i9], paramArrayOfFloat2[i10]);
          }
          else
          {
            paramPath.moveTo(paramArrayOfFloat2[i9], paramArrayOfFloat2[i10]);
            f10 = f8;
            f9 = f7;
          }
          break;
        case 'L':
          m = j;
          int i7 = m + 0;
          float f28 = paramArrayOfFloat2[i7];
          int i8 = m + 1;
          paramPath.lineTo(f28, paramArrayOfFloat2[i8]);
          f7 = paramArrayOfFloat2[i7];
          f8 = paramArrayOfFloat2[i8];
          break;
        case 'H':
          float f27 = f8;
          m = j;
          int i6 = m + 0;
          paramPath.lineTo(paramArrayOfFloat2[i6], f27);
          f7 = paramArrayOfFloat2[i6];
          break;
        case 'C':
          m = j;
          float f19 = paramArrayOfFloat2[(m + 0)];
          float f20 = paramArrayOfFloat2[(m + 1)];
          int i2 = m + 2;
          float f21 = paramArrayOfFloat2[i2];
          int i3 = m + 3;
          float f22 = paramArrayOfFloat2[i3];
          int i4 = m + 4;
          float f23 = paramArrayOfFloat2[i4];
          int i5 = m + 5;
          paramPath.cubicTo(f19, f20, f21, f22, f23, paramArrayOfFloat2[i5]);
          f7 = paramArrayOfFloat2[i4];
          float f24 = paramArrayOfFloat2[i5];
          float f25 = paramArrayOfFloat2[i2];
          float f26 = paramArrayOfFloat2[i3];
          f8 = f24;
          f4 = f26;
          f3 = f25;
          break;
        case 'A':
        }
        float f12 = f8;
        float f13 = f7;
        int m = j;
        int n = m + 5;
        float f14 = paramArrayOfFloat2[n];
        int i1 = m + 6;
        float f15 = paramArrayOfFloat2[i1];
        float f16 = paramArrayOfFloat2[(m + 0)];
        float f17 = paramArrayOfFloat2[(m + 1)];
        float f18 = paramArrayOfFloat2[(m + 2)];
        boolean bool1;
        if (paramArrayOfFloat2[(m + 3)] != 0.0F)
          bool1 = true;
        else
          bool1 = false;
        boolean bool2;
        if (paramArrayOfFloat2[(m + 4)] != 0.0F)
          bool2 = true;
        else
          bool2 = false;
        drawArc(paramPath, f13, f12, f14, f15, f16, f17, f18, bool1, bool2);
        f7 = paramArrayOfFloat2[n];
        f8 = paramArrayOfFloat2[i1];
        f4 = f8;
        f3 = f7;
        j = m + i;
      }
      float f11 = f8;
      paramArrayOfFloat1[0] = f7;
      paramArrayOfFloat1[1] = f11;
      paramArrayOfFloat1[2] = f3;
      paramArrayOfFloat1[3] = f4;
      paramArrayOfFloat1[4] = f9;
      paramArrayOfFloat1[5] = f10;
    }

    private static void arcToBezier(Path paramPath, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8, double paramDouble9)
    {
      double d1 = paramDouble3;
      int i = (int)Math.ceil(Math.abs(paramDouble9 * 4.0D / 3.141592653589793D));
      double d2 = Math.cos(paramDouble7);
      double d3 = Math.sin(paramDouble7);
      double d4 = Math.cos(paramDouble8);
      double d5 = Math.sin(paramDouble8);
      double d6 = -d1;
      double d7 = d6 * d2;
      double d8 = d7 * d5;
      double d9 = paramDouble4 * d3;
      double d10 = d8 - d9 * d4;
      double d11 = d6 * d3;
      double d12 = d5 * d11;
      double d13 = paramDouble4 * d2;
      double d14 = d12 + d4 * d13;
      double d15 = paramDouble9 / i;
      int j = 0;
      double d16 = paramDouble6;
      double d17 = d14;
      double d18 = d10;
      double d19 = paramDouble5;
      double d20 = paramDouble8;
      while (j < i)
      {
        double d21 = d11;
        double d22 = d20 + d15;
        double d23 = Math.sin(d22);
        double d24 = Math.cos(d22);
        double d25 = paramDouble1 + d24 * (d1 * d2);
        double d26 = d9 * d23;
        double d27 = d15;
        double d28 = d25 - d26;
        double d29 = paramDouble2 + d24 * (d1 * d3) + d13 * d23;
        double d30 = d7 * d23 - d9 * d24;
        double d31 = d23 * d21 + d24 * d13;
        double d32 = d22 - d20;
        double d33 = d13;
        double d34 = Math.tan(d32 / 2.0D);
        double d35 = Math.sin(d32) * (Math.sqrt(4.0D + d34 * (d34 * 3.0D)) - 1.0D) / 3.0D;
        double d36 = d19 + d18 * d35;
        double d37 = d16 + d17 * d35;
        double d38 = d35 * d30;
        int k = i;
        double d39 = d2;
        double d40 = d28 - d38;
        double d41 = d29 - d35 * d31;
        double d42 = d3;
        paramPath.rLineTo(0.0F, 0.0F);
        paramPath.cubicTo((float)d36, (float)d37, (float)d40, (float)d41, (float)d28, (float)d29);
        j++;
        d16 = d29;
        d19 = d28;
        d11 = d21;
        d17 = d31;
        d18 = d30;
        d15 = d27;
        d13 = d33;
        d20 = d22;
        i = k;
        d2 = d39;
        d3 = d42;
        d1 = paramDouble3;
      }
    }

    private static void drawArc(Path paramPath, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean1, boolean paramBoolean2)
    {
      double d1 = Math.toRadians(paramFloat7);
      double d2 = Math.cos(d1);
      double d3 = Math.sin(d1);
      double d4 = paramFloat1;
      double d5 = d4 * d2;
      double d6 = paramFloat2;
      double d7 = d5 + d6 * d3;
      double d8 = paramFloat5;
      double d9 = d7 / d8;
      double d10 = d3 * -paramFloat1 + d6 * d2;
      double d11 = paramFloat6;
      double d12 = d10 / d11;
      double d13 = d2 * paramFloat3;
      double d14 = paramFloat4;
      double d15 = (d13 + d14 * d3) / d8;
      double d16 = (d3 * -paramFloat3 + d14 * d2) / d11;
      double d17 = d9 - d15;
      double d18 = d12 - d16;
      double d19 = (d9 + d15) / 2.0D;
      double d20 = (d12 + d16) / 2.0D;
      double d21 = d17 * d17 + d18 * d18;
      if (d21 == 0.0D)
      {
        Log.w("PathParser", " Points are coincident");
        return;
      }
      double d22 = 1.0D / d21 - 0.25D;
      if (d22 < 0.0D)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Points are too far apart ");
        localStringBuilder.append(d21);
        Log.w("PathParser", localStringBuilder.toString());
        float f = (float)(Math.sqrt(d21) / 1.99999D);
        drawArc(paramPath, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5 * f, paramFloat6 * f, paramFloat7, paramBoolean1, paramBoolean2);
        return;
      }
      double d23 = Math.sqrt(d22);
      double d24 = d17 * d23;
      double d25 = d23 * d18;
      double d26;
      double d27;
      if (paramBoolean1 == paramBoolean2)
      {
        d26 = d19 - d25;
        d27 = d20 + d24;
      }
      else
      {
        d26 = d19 + d25;
        d27 = d20 - d24;
      }
      double d28 = Math.atan2(d12 - d27, d9 - d26);
      double d29 = Math.atan2(d16 - d27, d15 - d26) - d28;
      boolean bool;
      if (d29 >= 0.0D)
        bool = true;
      else
        bool = false;
      if (paramBoolean2 != bool)
        if (d29 > 0.0D)
          d29 -= 6.283185307179586D;
        else
          d29 += 6.283185307179586D;
      double d30 = d29;
      double d31 = d26 * d8;
      double d32 = d27 * d11;
      arcToBezier(paramPath, d31 * d2 - d32 * d3, d31 * d3 + d32 * d2, d8, d11, d4, d6, d1, d28, d30);
    }

    public static void nodesToPath(PathDataNode[] paramArrayOfPathDataNode, Path paramPath)
    {
      float[] arrayOfFloat = new float[6];
      char c = 'm';
      for (int i = 0; i < paramArrayOfPathDataNode.length; i++)
      {
        addCommand(paramPath, arrayOfFloat, c, paramArrayOfPathDataNode[i].mType, paramArrayOfPathDataNode[i].mParams);
        c = paramArrayOfPathDataNode[i].mType;
      }
    }

    public void interpolatePathDataNode(PathDataNode paramPathDataNode1, PathDataNode paramPathDataNode2, float paramFloat)
    {
      for (int i = 0; i < paramPathDataNode1.mParams.length; i++)
        this.mParams[i] = (paramPathDataNode1.mParams[i] * (1.0F - paramFloat) + paramFloat * paramPathDataNode2.mParams[i]);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.graphics.PathParser
 * JD-Core Version:    0.6.1
 */