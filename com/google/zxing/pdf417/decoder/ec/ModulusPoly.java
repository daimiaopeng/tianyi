package com.google.zxing.pdf417.decoder.ec;

final class ModulusPoly
{
  private final int[] coefficients;
  private final ModulusGF field;

  ModulusPoly(ModulusGF paramModulusGF, int[] paramArrayOfInt)
  {
    if (paramArrayOfInt.length == 0)
      throw new IllegalArgumentException();
    this.field = paramModulusGF;
    int i = paramArrayOfInt.length;
    int j = 1;
    if ((i > j) && (paramArrayOfInt[0] == 0))
    {
      while ((j < i) && (paramArrayOfInt[j] == 0))
        j++;
      if (j == i)
      {
        this.coefficients = paramModulusGF.getZero().coefficients;
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

  ModulusPoly add(ModulusPoly paramModulusPoly)
  {
    if (!this.field.equals(paramModulusPoly.field))
      throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    if (isZero())
      return paramModulusPoly;
    if (paramModulusPoly.isZero())
      return this;
    Object localObject1 = this.coefficients;
    Object localObject2 = paramModulusPoly.coefficients;
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
      arrayOfInt[j] = this.field.add(localObject1[(j - i)], localObject2[j]);
    return new ModulusPoly(this.field, arrayOfInt);
  }

  ModulusPoly[] divide(ModulusPoly paramModulusPoly)
  {
    if (!this.field.equals(paramModulusPoly.field))
      throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    if (paramModulusPoly.isZero())
      throw new IllegalArgumentException("Divide by 0");
    ModulusPoly localModulusPoly1 = this.field.getZero();
    int i = paramModulusPoly.getCoefficient(paramModulusPoly.getDegree());
    int j = this.field.inverse(i);
    ModulusPoly localModulusPoly2 = localModulusPoly1;
    ModulusPoly localModulusPoly4;
    for (ModulusPoly localModulusPoly3 = this; (localModulusPoly3.getDegree() >= paramModulusPoly.getDegree()) && (!localModulusPoly3.isZero()); localModulusPoly3 = localModulusPoly3.subtract(localModulusPoly4))
    {
      int k = localModulusPoly3.getDegree() - paramModulusPoly.getDegree();
      int m = this.field.multiply(localModulusPoly3.getCoefficient(localModulusPoly3.getDegree()), j);
      localModulusPoly4 = paramModulusPoly.multiplyByMonomial(k, m);
      localModulusPoly2 = localModulusPoly2.add(this.field.buildMonomial(k, m));
    }
    return new ModulusPoly[] { localModulusPoly2, localModulusPoly3 };
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
        int i2 = arrayOfInt[i];
        i1 = this.field.add(i1, i2);
        i++;
      }
      return i1;
    }
    int m = this.coefficients[0];
    while (k < j)
    {
      m = this.field.add(this.field.multiply(paramInt, m), this.coefficients[k]);
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

  ModulusPoly multiply(int paramInt)
  {
    if (paramInt == 0)
      return this.field.getZero();
    if (paramInt == 1)
      return this;
    int i = this.coefficients.length;
    int[] arrayOfInt = new int[i];
    for (int j = 0; j < i; j++)
      arrayOfInt[j] = this.field.multiply(this.coefficients[j], paramInt);
    return new ModulusPoly(this.field, arrayOfInt);
  }

  ModulusPoly multiply(ModulusPoly paramModulusPoly)
  {
    if (!this.field.equals(paramModulusPoly.field))
      throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    if ((!isZero()) && (!paramModulusPoly.isZero()))
    {
      int[] arrayOfInt1 = this.coefficients;
      int i = arrayOfInt1.length;
      int[] arrayOfInt2 = paramModulusPoly.coefficients;
      int j = arrayOfInt2.length;
      int[] arrayOfInt3 = new int[-1 + (i + j)];
      for (int k = 0; k < i; k++)
      {
        int m = arrayOfInt1[k];
        for (int n = 0; n < j; n++)
        {
          int i1 = k + n;
          arrayOfInt3[i1] = this.field.add(arrayOfInt3[i1], this.field.multiply(m, arrayOfInt2[n]));
        }
      }
      return new ModulusPoly(this.field, arrayOfInt3);
    }
    return this.field.getZero();
  }

  ModulusPoly multiplyByMonomial(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IllegalArgumentException();
    if (paramInt2 == 0)
      return this.field.getZero();
    int i = this.coefficients.length;
    int[] arrayOfInt = new int[paramInt1 + i];
    for (int j = 0; j < i; j++)
      arrayOfInt[j] = this.field.multiply(this.coefficients[j], paramInt2);
    return new ModulusPoly(this.field, arrayOfInt);
  }

  ModulusPoly negative()
  {
    int i = this.coefficients.length;
    int[] arrayOfInt = new int[i];
    for (int j = 0; j < i; j++)
      arrayOfInt[j] = this.field.subtract(0, this.coefficients[j]);
    return new ModulusPoly(this.field, arrayOfInt);
  }

  ModulusPoly subtract(ModulusPoly paramModulusPoly)
  {
    if (!this.field.equals(paramModulusPoly.field))
      throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    if (paramModulusPoly.isZero())
      return this;
    return add(paramModulusPoly.negative());
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
          localStringBuilder.append(j);
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
 * Qualified Name:     com.google.zxing.pdf417.decoder.ec.ModulusPoly
 * JD-Core Version:    0.6.1
 */