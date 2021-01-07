package com.google.zxing.pdf417.detector;

import F;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.decoder.BitMatrixParser;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class LinesSampler
{
  private static final int BARCODE_START_OFFSET = 2;
  private static final int BARS_IN_SYMBOL = 8;
  private static final int MODULES_IN_SYMBOL = 17;
  private static final float[] RATIOS_TABLE;
  private final int dimension;
  private final BitMatrix linesMatrix;
  private final int symbolsPerLine;

  static
  {
    float[][] arrayOfFloat = (float[][])Array.newInstance(F.class, new int[] { BitMatrixParser.SYMBOL_TABLE.length, 8 });
    RATIOS_TABLE = new float[8 * BitMatrixParser.SYMBOL_TABLE.length];
    int i = 0;
    int i2;
    for (int j = 0; i < BitMatrixParser.SYMBOL_TABLE.length; j = i2)
    {
      int k = BitMatrixParser.SYMBOL_TABLE[i];
      int m = k & 0x1;
      int n = k;
      int i1 = 0;
      while (i1 < 8)
      {
        float f = 0.0F;
        int i4;
        while (true)
        {
          i4 = n & 0x1;
          if (i4 != m)
            break;
          f += 1.0F;
          n >>= 1;
        }
        arrayOfFloat[i][(-1 + (8 - i1))] = (f / 17.0F);
        i1++;
        m = i4;
      }
      i2 = j;
      for (int i3 = 0; i3 < 8; i3++)
      {
        RATIOS_TABLE[i2] = arrayOfFloat[i][i3];
        i2++;
      }
      i++;
    }
  }

  public LinesSampler(BitMatrix paramBitMatrix, int paramInt)
  {
    this.linesMatrix = paramBitMatrix;
    this.symbolsPerLine = (paramInt / 17);
    this.dimension = paramInt;
  }

  private static int calculateClusterNumber(int paramInt)
  {
    if (paramInt == 0)
      return -1;
    int i = 0;
    int j = 0;
    int k = 1;
    int m = 0;
    while (i < 17)
    {
      if ((paramInt & 1 << i) > 0)
      {
        if (k == 0)
        {
          m++;
          k = 1;
        }
        if (m % 2 == 0)
          j++;
        else
          j--;
      }
      else if (k != 0)
      {
        k = 0;
      }
      i++;
    }
    return (j + 9) % 9;
  }

  private static BitMatrix codewordsToBitMatrix(List<List<Integer>> paramList, int paramInt1, int paramInt2)
  {
    BitMatrix localBitMatrix = new BitMatrix(paramInt1, paramInt2);
    for (int i = 0; i < paramList.size(); i++)
      for (int j = 0; j < ((List)paramList.get(i)).size(); j++)
      {
        int k = j * 17;
        for (int m = 0; m < 17; m++)
          if ((((Integer)((List)paramList.get(i)).get(j)).intValue() & 1 << 17 - m - 1) > 0)
            localBitMatrix.set(k + m, i);
      }
    return localBitMatrix;
  }

  private int decodeRowCount(List<List<Integer>> paramList, List<Integer> paramList1)
  {
    List<List<Integer>> localList = paramList;
    List<Integer> localList1 = paramList1;
    paramList1.clear();
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap3 = new HashMap();
    int i = 0;
    int j = -1;
    while (true)
    {
      int k = i + 2;
      if (k >= paramList.size())
        break;
      localHashMap3.clear();
      int i1;
      if (((Integer)((List)localList.get(i)).get(0)).intValue() != 0)
        i1 = BitMatrixParser.getCodeword(((Integer)((List)localList.get(i)).get(0)).intValue());
      else
        i1 = -1;
      int i2 = i + 1;
      int i3;
      if (((Integer)((List)localList.get(i2)).get(0)).intValue() != 0)
        i3 = BitMatrixParser.getCodeword(((Integer)((List)localList.get(i2)).get(0)).intValue());
      else
        i3 = -1;
      int i4;
      if (((Integer)((List)localList.get(k)).get(0)).intValue() != 0)
        i4 = BitMatrixParser.getCodeword(((Integer)((List)localList.get(k)).get(0)).intValue());
      else
        i4 = -1;
      int i5;
      if (((Integer)((List)localList.get(i)).get(-1 + ((List)localList.get(i)).size())).intValue() != 0)
        i5 = BitMatrixParser.getCodeword(((Integer)((List)localList.get(i)).get(-1 + ((List)localList.get(i)).size())).intValue());
      else
        i5 = -1;
      int i6;
      if (((Integer)((List)localList.get(i2)).get(-1 + ((List)localList.get(i2)).size())).intValue() != 0)
        i6 = BitMatrixParser.getCodeword(((Integer)((List)localList.get(i2)).get(-1 + ((List)localList.get(i2)).size())).intValue());
      else
        i6 = -1;
      int i7;
      if (((Integer)((List)localList.get(k)).get(-1 + ((List)localList.get(k)).size())).intValue() != 0)
        i7 = BitMatrixParser.getCodeword(((Integer)((List)localList.get(k)).get(-1 + ((List)localList.get(k)).size())).intValue());
      else
        i7 = -1;
      int i8 = -1;
      if ((i1 != i8) && (i3 != i8))
      {
        int i21 = 3 * (i1 % 30);
        int i22 = i3 % 30;
        int i23 = i21 + i22 % 3;
        int i24 = i22 / 3;
        localHashMap1.put(Integer.valueOf(i23), Integer.valueOf(1 + ((Integer)defaultValue(localHashMap1.get(Integer.valueOf(i23)), Integer.valueOf(0))).intValue()));
        localHashMap2.put(Integer.valueOf(i24), Integer.valueOf(1 + ((Integer)defaultValue(localHashMap2.get(Integer.valueOf(i24)), Integer.valueOf(0))).intValue()));
        i8 = -1;
      }
      if ((i6 != i8) && (i7 != i8))
      {
        int i17 = 3 * (i6 % 30);
        int i18 = i7 % 30;
        int i19 = i17 + i18 % 3;
        int i20 = i18 / 3;
        localHashMap1.put(Integer.valueOf(i19), Integer.valueOf(1 + ((Integer)defaultValue(localHashMap1.get(Integer.valueOf(i19)), Integer.valueOf(0))).intValue()));
        localHashMap2.put(Integer.valueOf(i20), Integer.valueOf(1 + ((Integer)defaultValue(localHashMap2.get(Integer.valueOf(i20)), Integer.valueOf(0))).intValue()));
        i8 = -1;
      }
      if (i1 != i8)
      {
        int i16 = i1 / 30;
        localHashMap3.put(Integer.valueOf(i16), Integer.valueOf(1 + ((Integer)defaultValue(localHashMap3.get(Integer.valueOf(i16)), Integer.valueOf(0))).intValue()));
        i8 = -1;
      }
      if (i3 != i8)
      {
        int i15 = i3 / 30;
        localHashMap3.put(Integer.valueOf(i15), Integer.valueOf(1 + ((Integer)defaultValue(localHashMap3.get(Integer.valueOf(i15)), Integer.valueOf(0))).intValue()));
        i8 = -1;
      }
      if (i4 != i8)
      {
        int i14 = i4 / 30;
        localHashMap3.put(Integer.valueOf(i14), Integer.valueOf(1 + ((Integer)defaultValue(localHashMap3.get(Integer.valueOf(i14)), Integer.valueOf(0))).intValue()));
        i8 = -1;
      }
      if (i5 != i8)
      {
        int i13 = i5 / 30;
        localHashMap3.put(Integer.valueOf(i13), Integer.valueOf(1 + ((Integer)defaultValue(localHashMap3.get(Integer.valueOf(i13)), Integer.valueOf(0))).intValue()));
        i8 = -1;
      }
      if (i6 != i8)
      {
        int i12 = i6 / 30;
        localHashMap3.put(Integer.valueOf(i12), Integer.valueOf(1 + ((Integer)defaultValue(localHashMap3.get(Integer.valueOf(i12)), Integer.valueOf(0))).intValue()));
        i8 = -1;
      }
      if (i7 != i8)
      {
        int i11 = i7 / 30;
        localHashMap3.put(Integer.valueOf(i11), Integer.valueOf(1 + ((Integer)defaultValue(localHashMap3.get(Integer.valueOf(i11)), Integer.valueOf(0))).intValue()));
      }
      int i9 = getValueWithMaxVotes(localHashMap3).getVote();
      int i10 = j + 1;
      if (i10 < i9)
        while (i10 < i9)
        {
          paramList1.add(Integer.valueOf(i));
          paramList1.add(Integer.valueOf(i));
          paramList1.add(Integer.valueOf(i));
          i10++;
        }
      i += 3;
      j = i9;
      localList1 = paramList1;
      localList = paramList;
    }
    List<Integer> localList2 = localList1;
    for (int m = 0; m < paramList1.size(); m++)
    {
      ArrayList localArrayList = new ArrayList();
      for (int n = 0; n < this.symbolsPerLine; n++)
        localArrayList.add(Integer.valueOf(0));
      paramList.add(m + ((Integer)localList2.get(m)).intValue(), localArrayList);
    }
    return 1 + getValueWithMaxVotes(localHashMap1).getVote();
  }

  private static <T> T defaultValue(T paramT1, T paramT2)
  {
    if (paramT1 == null)
      paramT1 = paramT2;
    return paramT1;
  }

  private List<List<Map<Integer, Integer>>> distributeVotes(int[][] paramArrayOfInt1, int[][] paramArrayOfInt2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new ArrayList());
    resize2((List)localArrayList.get(0), this.symbolsPerLine);
    HashMap localHashMap = new HashMap();
    int i = 0;
    int j = -1;
    int k = 0;
    while (i < paramArrayOfInt1.length)
    {
      localHashMap.clear();
      for (int m = 0; m < paramArrayOfInt1[i].length; m++)
        if (paramArrayOfInt2[i][m] != -1)
          localHashMap.put(Integer.valueOf(paramArrayOfInt2[i][m]), Integer.valueOf(1 + ((Integer)defaultValue(localHashMap.get(Integer.valueOf(paramArrayOfInt2[i][m])), Integer.valueOf(0))).intValue()));
      if (!localHashMap.isEmpty())
      {
        VoteResult localVoteResult = getValueWithMaxVotes(localHashMap);
        boolean bool = localVoteResult.isIndecisive();
        int n = localVoteResult.getVote();
        if (bool)
          n = j;
        int i1 = (j + 3) % 9;
        if ((n != i1) && (j != -1))
          n = j;
        if (((n == 0) && (j == -1)) || (j != -1))
        {
          if ((n == i1) && (j != -1))
          {
            k++;
            int i7 = localArrayList.size();
            int i8 = k + 1;
            if (i7 < i8)
            {
              resize1(localArrayList, i8);
              resize2((List)localArrayList.get(k), this.symbolsPerLine);
            }
          }
          if ((n == (j + 6) % 9) && (j != -1))
          {
            k += 2;
            int i5 = localArrayList.size();
            int i6 = k + 1;
            if (i5 < i6)
            {
              resize1(localArrayList, i6);
              resize2((List)localArrayList.get(k), this.symbolsPerLine);
            }
          }
          for (int i2 = 0; i2 < paramArrayOfInt1[i].length; i2++)
            if (paramArrayOfInt2[i][i2] != -1)
              if (paramArrayOfInt2[i][i2] == n)
              {
                Map localMap3 = (Map)((List)localArrayList.get(k)).get(i2);
                localMap3.put(Integer.valueOf(paramArrayOfInt1[i][i2]), Integer.valueOf(1 + ((Integer)defaultValue(localMap3.get(Integer.valueOf(paramArrayOfInt1[i][i2])), Integer.valueOf(0))).intValue()));
              }
              else if (paramArrayOfInt2[i][i2] == (n + 3) % 9)
              {
                int i3 = localArrayList.size();
                int i4 = k + 2;
                if (i3 < i4)
                {
                  resize1(localArrayList, i4);
                  resize2((List)localArrayList.get(k + 1), this.symbolsPerLine);
                }
                Map localMap2 = (Map)((List)localArrayList.get(k + 1)).get(i2);
                localMap2.put(Integer.valueOf(paramArrayOfInt1[i][i2]), Integer.valueOf(1 + ((Integer)defaultValue(localMap2.get(Integer.valueOf(paramArrayOfInt1[i][i2])), Integer.valueOf(0))).intValue()));
              }
              else if ((paramArrayOfInt2[i][i2] == (n + 6) % 9) && (k > 0))
              {
                Map localMap1 = (Map)((List)localArrayList.get(k - 1)).get(i2);
                localMap1.put(Integer.valueOf(paramArrayOfInt1[i][i2]), Integer.valueOf(1 + ((Integer)defaultValue(localMap1.get(Integer.valueOf(paramArrayOfInt1[i][i2])), Integer.valueOf(0))).intValue()));
              }
          j = n;
        }
      }
      i++;
    }
    return localArrayList;
  }

  private List<Integer> findMissingLines(List<List<Integer>> paramList)
  {
    ArrayList localArrayList1 = new ArrayList();
    if (paramList.size() > 1)
    {
      int i3;
      for (int k = 0; k < paramList.size() - 1; k = i3)
      {
        int m = 0;
        int n = -1;
        while ((m < ((List)paramList.get(k)).size()) && (n == -1))
        {
          int i5 = calculateClusterNumber(((Integer)((List)paramList.get(k)).get(m)).intValue());
          if (i5 != -1)
            n = i5;
          m++;
        }
        if ((k == 0) && (n > 0))
        {
          localArrayList1.add(Integer.valueOf(0));
          if (n > 3)
            localArrayList1.add(Integer.valueOf(0));
        }
        int i1 = 0;
        int i2 = -1;
        while (true)
        {
          i3 = k + 1;
          if ((i1 >= ((List)paramList.get(i3)).size()) || (i2 != -1))
            break;
          int i4 = calculateClusterNumber(((Integer)((List)paramList.get(i3)).get(i1)).intValue());
          if (i4 != -1)
            i2 = i4;
          i1++;
        }
        if (((n + 3) % 9 != i2) && (n != -1) && (i2 != -1))
        {
          localArrayList1.add(Integer.valueOf(i3));
          if (n == i2)
            localArrayList1.add(Integer.valueOf(i3));
        }
      }
    }
    for (int i = 0; i < localArrayList1.size(); i++)
    {
      ArrayList localArrayList2 = new ArrayList();
      for (int j = 0; j < this.symbolsPerLine; j++)
        localArrayList2.add(Integer.valueOf(0));
      paramList.add(i + ((Integer)localArrayList1.get(i)).intValue(), localArrayList2);
    }
    return localArrayList1;
  }

  private List<Float> findSymbolWidths()
  {
    float f1;
    if (this.symbolsPerLine > 0)
      f1 = this.linesMatrix.getWidth() / this.symbolsPerLine;
    else
      f1 = this.linesMatrix.getWidth();
    ArrayList localArrayList = new ArrayList();
    int[] arrayOfInt = new int[this.linesMatrix.getWidth()];
    int i = 2;
    int j = 1;
    int k = 0;
    int m = 1;
    while (i < this.linesMatrix.getWidth())
    {
      for (int n = 0; n < this.linesMatrix.getHeight(); n++)
        if (this.linesMatrix.get(i, n))
          arrayOfInt[i] = (j + arrayOfInt[i]);
      if (arrayOfInt[i] == this.linesMatrix.getHeight())
      {
        if (m == 0)
        {
          float f3 = i - k;
          double d1 = f3;
          double d2 = f1;
          int i1;
          if (d1 > 0.75D * d2)
          {
            while (f3 > d2 * 1.5D)
            {
              localArrayList.add(Float.valueOf(f1));
              f3 -= f1;
            }
            localArrayList.add(Float.valueOf(f3));
            k = i;
            i1 = 1;
          }
          else
          {
            i1 = m;
          }
          m = i1;
        }
      }
      else if (m != 0)
        m = 0;
      i++;
      j = 1;
    }
    for (float f2 = this.linesMatrix.getWidth() - k; f2 > 1.5D * f1; f2 -= f1)
      localArrayList.add(Float.valueOf(f1));
    localArrayList.add(Float.valueOf(f2));
    return localArrayList;
  }

  private static VoteResult getValueWithMaxVotes(Map<Integer, Integer> paramMap)
  {
    VoteResult localVoteResult = new VoteResult(null);
    Iterator localIterator = paramMap.entrySet().iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (((Integer)localEntry.getValue()).intValue() > i)
      {
        i = ((Integer)localEntry.getValue()).intValue();
        localVoteResult.setVote(((Integer)localEntry.getKey()).intValue());
        localVoteResult.setIndecisive(false);
      }
      else if (((Integer)localEntry.getValue()).intValue() == i)
      {
        localVoteResult.setIndecisive(true);
      }
    }
    return localVoteResult;
  }

  private void linesMatrixToCodewords(int[][] paramArrayOfInt1, int[][] paramArrayOfInt2, List<Float> paramList)
  {
    if (this.symbolsPerLine > paramList.size())
      throw NotFoundException.getNotFoundInstance();
    int i = 0;
    int j = 0;
    while (j < this.linesMatrix.getHeight())
    {
      paramArrayOfInt1[j] = new int[this.symbolsPerLine];
      paramArrayOfInt2[j] = new int[this.symbolsPerLine];
      Arrays.fill(paramArrayOfInt2[j], 0, paramArrayOfInt2[j].length, -1);
      ArrayList localArrayList = new ArrayList();
      int k = 2;
      localArrayList.add(Integer.valueOf(k));
      int m = 1;
      int n = 1;
      while (k < this.linesMatrix.getWidth())
      {
        if (this.linesMatrix.get(k, j))
        {
          if (n == 0)
          {
            localArrayList.add(Integer.valueOf(0));
            n = 1;
          }
        }
        else if (n != 0)
        {
          localArrayList.add(Integer.valueOf(0));
          n = 0;
        }
        int i15 = localArrayList.size() - m;
        localArrayList.set(i15, Integer.valueOf(m + ((Integer)localArrayList.get(i15)).intValue()));
        k++;
      }
      int[] arrayOfInt = new int[this.symbolsPerLine];
      arrayOfInt[0] = 0;
      int i1 = 0;
      int i2 = 1;
      int i3 = 0;
      while ((i1 < localArrayList.size()) && (i2 < this.symbolsPerLine))
      {
        i3 += ((Integer)localArrayList.get(i1)).intValue();
        if (i3 > ((Float)paramList.get(i2 - 1)).floatValue())
        {
          if (i1 % 2 == m)
            i1++;
          if (i1 < localArrayList.size())
            i3 = ((Integer)localArrayList.get(i1)).intValue();
          arrayOfInt[i2] = i1;
          i2++;
        }
        i1 += m;
      }
      int i4 = this.symbolsPerLine;
      int i5 = 8;
      float[][] arrayOfFloat = (float[][])Array.newInstance(F.class, new int[] { i4, i5 });
      int i6 = 0;
      while (i6 < this.symbolsPerLine)
      {
        int i7 = arrayOfInt[i6];
        int i8;
        if (i6 == this.symbolsPerLine - m)
          i8 = localArrayList.size();
        else
          i8 = arrayOfInt[(i6 + 1)];
        int i9 = i8 - i7;
        if ((i9 >= 7) && (i9 <= 9))
        {
          float f1 = 0.0F;
          while (i < Math.min(i5, i9))
          {
            f1 += ((Integer)localArrayList.get(i7 + i)).intValue();
            i++;
          }
          if (i9 == 7)
          {
            for (int i14 = 0; i14 < i9; i14++)
              arrayOfFloat[i6][i14] = (((Integer)localArrayList.get(i7 + i14)).intValue() / ((Float)paramList.get(i6)).floatValue());
            arrayOfFloat[i6][7] = ((((Float)paramList.get(i6)).floatValue() - f1) / ((Float)paramList.get(i6)).floatValue());
          }
          else
          {
            for (int i10 = 0; i10 < arrayOfFloat[i6].length; i10++)
              arrayOfFloat[i6][i10] = (((Integer)localArrayList.get(i7 + i10)).intValue() / f1);
          }
          int i11 = 0;
          int i12 = 0;
          float f2 = 3.4028235E+38F;
          while (i11 < BitMatrixParser.SYMBOL_TABLE.length)
          {
            int i13 = 0;
            float f3 = 0.0F;
            while (i13 < 8)
            {
              float f4 = RATIOS_TABLE[(i13 + i11 * 8)] - arrayOfFloat[i6][i13];
              f3 += f4 * f4;
              i13++;
            }
            if (f3 < f2)
            {
              i12 = BitMatrixParser.SYMBOL_TABLE[i11];
              f2 = f3;
            }
            i11++;
          }
          paramArrayOfInt1[j][i6] = i12;
          paramArrayOfInt2[j][i6] = calculateClusterNumber(i12);
        }
        i6++;
        i = 0;
        m = 1;
        i5 = 8;
      }
      j++;
      i = 0;
    }
  }

  private static void resize1(List<List<Map<Integer, Integer>>> paramList, int paramInt)
  {
    for (int i = paramInt; i < paramList.size(); i++)
      paramList.remove(i);
    for (int j = paramList.size(); j < paramInt; j++)
      paramList.add(new ArrayList());
  }

  private static void resize2(List<Map<Integer, Integer>> paramList, int paramInt)
  {
    for (int i = paramInt; i < paramList.size(); i++)
      paramList.remove(i);
    for (int j = paramList.size(); j < paramInt; j++)
      paramList.add(new HashMap());
  }

  private static void resize3(List<List<Integer>> paramList, int paramInt)
  {
    for (int i = paramInt; i < paramList.size(); i++)
      paramList.remove(i);
    for (int j = paramList.size(); j < paramInt; j++)
      paramList.add(new ArrayList());
  }

  private static void resize4(List<Integer> paramList, int paramInt)
  {
    for (int i = paramInt; i < paramList.size(); i++)
      paramList.remove(i);
    for (int j = paramList.size(); j < paramInt; j++)
      paramList.add(Integer.valueOf(0));
  }

  public BitMatrix sample()
  {
    List localList1 = findSymbolWidths();
    int[][] arrayOfInt1 = new int[this.linesMatrix.getHeight()][];
    int[][] arrayOfInt2 = new int[this.linesMatrix.getHeight()][];
    linesMatrixToCodewords(arrayOfInt1, arrayOfInt2, localList1);
    List localList2 = distributeVotes(arrayOfInt1, arrayOfInt2);
    ArrayList localArrayList = new ArrayList();
    resize3(localArrayList, localList2.size());
    for (int i = 0; i < localList2.size(); i++)
    {
      resize4((List)localArrayList.get(i), ((List)localList2.get(i)).size());
      for (int j = 0; j < ((List)localList2.get(i)).size(); j++)
        if (!((Map)((List)localList2.get(i)).get(j)).isEmpty())
          ((List)localArrayList.get(i)).set(j, Integer.valueOf(getValueWithMaxVotes((Map)((List)localList2.get(i)).get(j)).getVote()));
    }
    resize3(localArrayList, decodeRowCount(localArrayList, findMissingLines(localArrayList)));
    return codewordsToBitMatrix(localArrayList, this.dimension, localArrayList.size());
  }

  private static class VoteResult
  {
    private boolean indecisive;
    private int vote;

    int getVote()
    {
      return this.vote;
    }

    boolean isIndecisive()
    {
      return this.indecisive;
    }

    void setIndecisive(boolean paramBoolean)
    {
      this.indecisive = paramBoolean;
    }

    void setVote(int paramInt)
    {
      this.vote = paramInt;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.detector.LinesSampler
 * JD-Core Version:    0.6.1
 */