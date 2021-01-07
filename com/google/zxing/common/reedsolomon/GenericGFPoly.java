package com.google.zxing.common.reedsolomon;

final class GenericGFPoly
{
  private final int[] coefficients;
  private final GenericGF field;

  GenericGFPoly(GenericGF paramGenericGF, int[] paramArrayOfInt)
  {
    if (paramArrayOfInt.length == 0)
      throw new IllegalArgumentException();
    this.field = paramGenericGF;
    int i = paramArrayOfInt.length;
    int j = 1;
    if ((i > j) && (paramArrayOfInt[0] == 0))
    {
      while ((j < i) && (paramArrayOfInt[j] == 0))
        j++;
      if (j == i)
      {
        this.coefficients = paramGenericGF.getZero().coefficients;
      }
      else
      {
        this.coefficients = new int[i - j];
        System.arraycopy(paramArrayOfInt, j, this.coefficients, 0, this.coefficients.length);
      }
    }
    else
    {
      this.coefficients = paramArrayOfInt;
    }
  }

  GenericGFPoly addOrSubtract(GenericGFPoly paramGenericGFPoly)
  {
    if (!this.field.equals(paramGenericGFPoly.field))
      throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    if (isZero())
      return paramGenericGFPoly;
    if (paramGenericGFPoly.isZero())
      return this;
    Object localObject1 = this.coefficients;
    Object localObject2 = paramGenericGFPoly.coefficients;
    if (localObject1.length > localObject2.length)
    {
      Object localObject3 = localObject1;
      localObject1 = localObject2;
      localObject2 = localObject3;
    }
    int[] arrayOfInt = new int[localObject2.length];
    int i = localObject2.length - localObject1.length;
    System.arraycopy(localObject2, 0, arrayOfInt, 0, i);
    for (int j = i; j < localObject2.length; j++)
      arrayOfInt[j] = GenericGF.addOrSubtract(localObject1[(j - i)], localObject2[j]);
    return new GenericGFPoly(this.field, arrayOfInt);
  }

  GenericGFPoly[] divide(GenericGFPoly paramGenericGFPoly)
  {
    if (!this.field.equals(paramGenericGFPoly.field))
      throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    if (paramGenericGFPoly.isZero())
      throw new IllegalArgumentException("Divide by 0");
    GenericGFPoly localGenericGFPoly1 = this.field.getZero();
    int i = paramGenericGFPoly.getCoefficient(paramGenericGFPoly.getDegree());
    int j = this.field.inverse(i);
    GenericGFPoly localGenericGFPoly2 = localGenericGFPoly1;
    GenericGFPoly localGenericGFPoly4;
    for (GenericGFPoly localGenericGFPoly3 = this; (localGenericGFPoly3.getDegree() >= paramGenericGFPoly.getDegree()) && (!localGenericGFPoly3.isZero()); localGenericGFPoly3 = localGenericGFPoly3.addOrSubtract(localGenericGFPoly4))
    {
      int k = localGenericGFPoly3.getDegree() - paramGenericGFPoly.getDegree();
      int m = this.field.multiply(localGenericGFPoly3.getCoefficient(localGenericGFPoly3.getDegree()), j);
      localGenericGFPoly4 = paramGenericGFPoly.multiplyByMonomial(k, m);
      localGenericGFPoly2 = localGenericGFPoly2.addOrSubtract(this.field.buildMonomial(k, m));
    }
    return new GenericGFPoly[] { localGenericGFPoly2, localGenericGFPoly3 };
  }

  int evaluateAt(int paramInt)
  {
    int i = 0;
    if (paramInt == 0)
      return getCoefficient(0);
    int j = this.coefficients.length;
    int k = 1;
    if (paramInt == k)
    {
      int[] arrayOfInt = this.coefficients;
      int n = arrayOfInt.length;
      int i1 = 0;
      while (i < n)
      {
        i1 = GenericGF.addOrSubtract(i1, arrayOfInt[i]);
        i++;
      }
      return i1;
    }
    int m = this.coefficients[0];
    while (k < j)
    {
      m = GenericGF.addOrSubtract(this.field.multiply(paramInt, m), this.coefficients[k]);
      k++;
    }
    return m;
  }

  int getCoefficient(int paramInt)
  {
    return this.coefficients[(-1 + this.coefficients.length - paramInt)];
  }

  int[] getCoefficients()
  {
    return this.coefficients;
  }

  int getDegree()
  {
    return -1 + this.coefficients.length;
  }

  boolean isZero()
  {
    int i = this.coefficients[0];
    boolean bool = false;
    if (i == 0)
      bool = true;
    return bool;
  }

  GenericGFPoly multiply(int paramInt)
  {
    if (paramInt == 0)
      return this.field.getZero();
    if (paramInt == 1)
      return this;
    int i = this.coefficients.length;
    int[] arrayOfInt = new int[i];
    for (int j = 0; j < i; j++)
      arrayOfInt[j] = this.field.multiply(this.coefficients[j], paramInt);
    return new GenericGFPoly(this.field, arrayOfInt);
  }

  GenericGFPoly multiply(GenericGFPoly paramGenericGFPoly)
  {
    if (!this.field.equals(paramGenericGFPoly.field))
      throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    if ((!isZero()) && (!paramGenericGFPoly.isZero()))
    {
      int[] arrayOfInt1 = this.coefficients;
      int i = arrayOfInt1.length;
      int[] arrayOfInt2 = paramGenericGFPoly.coefficients;
      int j = arrayOfInt2.length;
      int[] arrayOfInt3 = new int[-1 + (i + j)];
      for (int k = 0; k < i; k++)
      {
        int m = arrayOfInt1[k];
        for (int n = 0; n < j; n++)
        {
          int i1 = k + n;
          arrayOfInt3[i1] = GenericGF.addOrSubtract(arrayOfInt3[i1], this.field.multiply(m, arrayOfInt2[n]));
        }
      }
      return new GenericGFPoly(this.field, arrayOfInt3);
    }
    return this.field.getZero();
  }

  GenericGFPoly multiplyByMonomial(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IllegalArgumentException();
    if (paramInt2 == 0)
      return this.field.getZero();
    int i = this.coefficients.length;
    int[] arrayOfInt = new int[paramInt1 + i];
    for (int j = 0; j < i; j++)
      arrayOfInt[j] = this.field.multiply(this.coefficients[j], paramInt2);
    return new GenericGFPoly(this.field, arrayOfInt);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(8 * getDegree());
    for (int i = getDegree(); i >= 0; i--)
    {
      int j = getCoefficient(i);
      if (j != 0)
      {
        if (j < 0)
        {
          localStringBuilder.append(" - ");
          j = -j;
        }
        else if (localStringBuilder.length() > 0)
        {
          localStringBuilder.append(" + ");
        }
        if ((i == 0) || (j != 1))
        {
          int k = this.field.log(j);
          if (k == 0)
          {
            localStringBuilder.append('1');
          }
          else if (k == 1)
          {
            localStringBuilder.append('a');
          }
          else
          {
            localStringBuilder.append("a^");
            localStringBuilder.append(k);
          }
        }
        if (i != 0)
          if (i == 1)
          {
            localStringBuilder.append('x');
          }
          else
          {
            localStringBuilder.append("x^");
            localStringBuilder.append(i);
          }
      }
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.google.zxing.common.reedsolomon.GenericGFPoly
 * JD-Core Version:    0.6.1
 */