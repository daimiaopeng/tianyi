package com.google.zxing.pdf417.decoder.ec;

import com.google.zxing.ChecksumException;

public final class ErrorCorrection
{
  private final ModulusGF field = ModulusGF.PDF417_GF;

  private int[] findErrorLocations(ModulusPoly paramModulusPoly)
  {
    int i = paramModulusPoly.getDegree();
    int[] arrayOfInt = new int[i];
    int j = 1;
    int k = 0;
    while ((j < this.field.getSize()) && (k < i))
    {
      if (paramModulusPoly.evaluateAt(j) == 0)
      {
        arrayOfInt[k] = this.field.inverse(j);
        k++;
      }
      j++;
    }
    if (k != i)
      throw ChecksumException.getChecksumInstance();
    return arrayOfInt;
  }

  private int[] findErrorMagnitudes(ModulusPoly paramModulusPoly1, ModulusPoly paramModulusPoly2, int[] paramArrayOfInt)
  {
    int i = paramModulusPoly2.getDegree();
    int[] arrayOfInt1 = new int[i];
    for (int j = 1; j <= i; j++)
      arrayOfInt1[(i - j)] = this.field.multiply(j, paramModulusPoly2.getCoefficient(j));
    ModulusPoly localModulusPoly = new ModulusPoly(this.field, arrayOfInt1);
    int k = paramArrayOfInt.length;
    int[] arrayOfInt2 = new int[k];
    for (int m = 0; m < k; m++)
    {
      int n = this.field.inverse(paramArrayOfInt[m]);
      int i1 = this.field.subtract(0, paramModulusPoly1.evaluateAt(n));
      int i2 = this.field.inverse(localModulusPoly.evaluateAt(n));
      arrayOfInt2[m] = this.field.multiply(i1, i2);
    }
    return arrayOfInt2;
  }

  private ModulusPoly[] runEuclideanAlgorithm(ModulusPoly paramModulusPoly1, ModulusPoly paramModulusPoly2, int paramInt)
  {
    if (paramModulusPoly1.getDegree() < paramModulusPoly2.getDegree())
    {
      ModulusPoly localModulusPoly4 = paramModulusPoly2;
      paramModulusPoly2 = paramModulusPoly1;
      paramModulusPoly1 = localModulusPoly4;
    }
    Object localObject1 = this.field.getZero();
    Object localObject2 = this.field.getOne();
    ModulusPoly localModulusPoly1 = paramModulusPoly2;
    Object localObject3 = paramModulusPoly1;
    Object localObject4 = localModulusPoly1;
    while (((ModulusPoly)localObject4).getDegree() >= paramInt / 2)
    {
      if (((ModulusPoly)localObject4).isZero())
        throw ChecksumException.getChecksumInstance();
      ModulusPoly localModulusPoly2 = this.field.getZero();
      int k = ((ModulusPoly)localObject4).getCoefficient(((ModulusPoly)localObject4).getDegree());
      int m = this.field.inverse(k);
      while ((((ModulusPoly)localObject3).getDegree() >= ((ModulusPoly)localObject4).getDegree()) && (!((ModulusPoly)localObject3).isZero()))
      {
        int n = ((ModulusPoly)localObject3).getDegree() - ((ModulusPoly)localObject4).getDegree();
        int i1 = this.field.multiply(((ModulusPoly)localObject3).getCoefficient(((ModulusPoly)localObject3).getDegree()), m);
        localModulusPoly2 = localModulusPoly2.add(this.field.buildMonomial(n, i1));
        localObject3 = ((ModulusPoly)localObject3).subtract(((ModulusPoly)localObject4).multiplyByMonomial(n, i1));
      }
      ModulusPoly localModulusPoly3 = localModulusPoly2.multiply((ModulusPoly)localObject2).subtract((ModulusPoly)localObject1).negative();
      Object localObject5 = localObject3;
      localObject3 = localObject4;
      localObject4 = localObject5;
      Object localObject6 = localObject2;
      localObject2 = localModulusPoly3;
      localObject1 = localObject6;
    }
    int i = ((ModulusPoly)localObject2).getCoefficient(0);
    if (i == 0)
      throw ChecksumException.getChecksumInstance();
    int j = this.field.inverse(i);
    return new ModulusPoly[] { ((ModulusPoly)localObject2).multiply(j), ((ModulusPoly)localObject4).multiply(j) };
  }

  public void decode(int[] paramArrayOfInt1, int paramInt, int[] paramArrayOfInt2)
  {
    ModulusPoly localModulusPoly1 = new ModulusPoly(this.field, paramArrayOfInt1);
    int[] arrayOfInt1 = new int[paramInt];
    int i = 0;
    int j = paramInt;
    int k = 0;
    while (j > 0)
    {
      int i4 = localModulusPoly1.evaluateAt(this.field.exp(j));
      arrayOfInt1[(paramInt - j)] = i4;
      if (i4 != 0)
        k = 1;
      j--;
    }
    if (k != 0)
    {
      ModulusPoly localModulusPoly2 = this.field.getOne();
      int m = paramArrayOfInt2.length;
      ModulusPoly localModulusPoly3 = localModulusPoly2;
      for (int n = 0; n < m; n++)
      {
        int i2 = paramArrayOfInt2[n];
        int i3 = this.field.exp(paramArrayOfInt1.length - 1 - i2);
        ModulusGF localModulusGF = this.field;
        int[] arrayOfInt4 = new int[2];
        arrayOfInt4[0] = this.field.subtract(0, i3);
        arrayOfInt4[1] = 1;
        localModulusPoly3 = localModulusPoly3.multiply(new ModulusPoly(localModulusGF, arrayOfInt4));
      }
      ModulusPoly localModulusPoly4 = new ModulusPoly(this.field, arrayOfInt1);
      ModulusPoly[] arrayOfModulusPoly = runEuclideanAlgorithm(this.field.buildMonomial(paramInt, 1), localModulusPoly4, paramInt);
      ModulusPoly localModulusPoly5 = arrayOfModulusPoly[0];
      ModulusPoly localModulusPoly6 = arrayOfModulusPoly[1];
      int[] arrayOfInt2 = findErrorLocations(localModulusPoly5);
      int[] arrayOfInt3 = findErrorMagnitudes(localModulusPoly6, localModulusPoly5, arrayOfInt2);
      while (i < arrayOfInt2.length)
      {
        int i1 = paramArrayOfInt1.length - 1 - this.field.log(arrayOfInt2[i]);
        if (i1 < 0)
          throw ChecksumException.getChecksumInstance();
        paramArrayOfInt1[i1] = this.field.subtract(paramArrayOfInt1[i1], arrayOfInt3[i]);
        i++;
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.decoder.ec.ErrorCorrection
 * JD-Core Version:    0.6.1
 */