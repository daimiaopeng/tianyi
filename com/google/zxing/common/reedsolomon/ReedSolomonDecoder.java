package com.google.zxing.common.reedsolomon;

public final class ReedSolomonDecoder
{
  private final GenericGF field;

  public ReedSolomonDecoder(GenericGF paramGenericGF)
  {
    this.field = paramGenericGF;
  }

  private int[] findErrorLocations(GenericGFPoly paramGenericGFPoly)
  {
    int i = paramGenericGFPoly.getDegree();
    int j = 0;
    int k = 1;
    if (i == k)
    {
      int[] arrayOfInt2 = new int[k];
      arrayOfInt2[0] = paramGenericGFPoly.getCoefficient(k);
      return arrayOfInt2;
    }
    int[] arrayOfInt1 = new int[i];
    while ((k < this.field.getSize()) && (j < i))
    {
      if (paramGenericGFPoly.evaluateAt(k) == 0)
      {
        arrayOfInt1[j] = this.field.inverse(k);
        j++;
      }
      k++;
    }
    if (j != i)
      throw new ReedSolomonException("Error locator degree does not match number of roots");
    return arrayOfInt1;
  }

  private int[] findErrorMagnitudes(GenericGFPoly paramGenericGFPoly, int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    int[] arrayOfInt = new int[i];
    for (int j = 0; j < i; j++)
    {
      int k = this.field.inverse(paramArrayOfInt[j]);
      int m = 0;
      int n = 1;
      while (m < i)
      {
        if (j != m)
        {
          int i1 = this.field.multiply(paramArrayOfInt[m], k);
          int i2;
          if ((i1 & 0x1) == 0)
            i2 = i1 | 0x1;
          else
            i2 = i1 & 0xFFFFFFFE;
          n = this.field.multiply(n, i2);
        }
        m++;
      }
      arrayOfInt[j] = this.field.multiply(paramGenericGFPoly.evaluateAt(k), this.field.inverse(n));
      if (this.field.getGeneratorBase() != 0)
        arrayOfInt[j] = this.field.multiply(arrayOfInt[j], k);
    }
    return arrayOfInt;
  }

  private GenericGFPoly[] runEuclideanAlgorithm(GenericGFPoly paramGenericGFPoly1, GenericGFPoly paramGenericGFPoly2, int paramInt)
  {
    if (paramGenericGFPoly1.getDegree() < paramGenericGFPoly2.getDegree())
    {
      GenericGFPoly localGenericGFPoly4 = paramGenericGFPoly2;
      paramGenericGFPoly2 = paramGenericGFPoly1;
      paramGenericGFPoly1 = localGenericGFPoly4;
    }
    Object localObject1 = this.field.getZero();
    Object localObject2 = this.field.getOne();
    GenericGFPoly localGenericGFPoly1 = paramGenericGFPoly2;
    Object localObject3 = paramGenericGFPoly1;
    Object localObject4 = localGenericGFPoly1;
    while (((GenericGFPoly)localObject4).getDegree() >= paramInt / 2)
    {
      if (((GenericGFPoly)localObject4).isZero())
        throw new ReedSolomonException("r_{i-1} was zero");
      GenericGFPoly localGenericGFPoly2 = this.field.getZero();
      int k = ((GenericGFPoly)localObject4).getCoefficient(((GenericGFPoly)localObject4).getDegree());
      int m = this.field.inverse(k);
      while ((((GenericGFPoly)localObject3).getDegree() >= ((GenericGFPoly)localObject4).getDegree()) && (!((GenericGFPoly)localObject3).isZero()))
      {
        int n = ((GenericGFPoly)localObject3).getDegree() - ((GenericGFPoly)localObject4).getDegree();
        int i1 = this.field.multiply(((GenericGFPoly)localObject3).getCoefficient(((GenericGFPoly)localObject3).getDegree()), m);
        localGenericGFPoly2 = localGenericGFPoly2.addOrSubtract(this.field.buildMonomial(n, i1));
        localObject3 = ((GenericGFPoly)localObject3).addOrSubtract(((GenericGFPoly)localObject4).multiplyByMonomial(n, i1));
      }
      GenericGFPoly localGenericGFPoly3 = localGenericGFPoly2.multiply((GenericGFPoly)localObject2).addOrSubtract((GenericGFPoly)localObject1);
      Object localObject5 = localObject3;
      localObject3 = localObject4;
      localObject4 = localObject5;
      Object localObject6 = localObject2;
      localObject2 = localGenericGFPoly3;
      localObject1 = localObject6;
    }
    int i = ((GenericGFPoly)localObject2).getCoefficient(0);
    if (i == 0)
      throw new ReedSolomonException("sigmaTilde(0) was zero");
    int j = this.field.inverse(i);
    return new GenericGFPoly[] { ((GenericGFPoly)localObject2).multiply(j), ((GenericGFPoly)localObject4).multiply(j) };
  }

  public void decode(int[] paramArrayOfInt, int paramInt)
  {
    GenericGFPoly localGenericGFPoly1 = new GenericGFPoly(this.field, paramArrayOfInt);
    int[] arrayOfInt1 = new int[paramInt];
    int i = 0;
    int j = 0;
    int k = 1;
    while (j < paramInt)
    {
      int n = localGenericGFPoly1.evaluateAt(this.field.exp(j + this.field.getGeneratorBase()));
      arrayOfInt1[(arrayOfInt1.length - 1 - j)] = n;
      if (n != 0)
        k = 0;
      j++;
    }
    if (k != 0)
      return;
    GenericGFPoly localGenericGFPoly2 = new GenericGFPoly(this.field, arrayOfInt1);
    GenericGFPoly[] arrayOfGenericGFPoly = runEuclideanAlgorithm(this.field.buildMonomial(paramInt, 1), localGenericGFPoly2, paramInt);
    GenericGFPoly localGenericGFPoly3 = arrayOfGenericGFPoly[0];
    GenericGFPoly localGenericGFPoly4 = arrayOfGenericGFPoly[1];
    int[] arrayOfInt2 = findErrorLocations(localGenericGFPoly3);
    int[] arrayOfInt3 = findErrorMagnitudes(localGenericGFPoly4, arrayOfInt2);
    while (i < arrayOfInt2.length)
    {
      int m = paramArrayOfInt.length - 1 - this.field.log(arrayOfInt2[i]);
      if (m < 0)
        throw new ReedSolomonException("Bad error location");
      paramArrayOfInt[m] = GenericGF.addOrSubtract(paramArrayOfInt[m], arrayOfInt3[i]);
      i++;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.common.reedsolomon.ReedSolomonDecoder
 * JD-Core Version:    0.6.1
 */