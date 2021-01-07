package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.util.SparseIntArray;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
class b extends a
{
  private final SparseIntArray a = new SparseIntArray();
  private final Parcel b;
  private final int c;
  private final int d;
  private final String e;
  private int f = -1;
  private int g = 0;

  b(Parcel paramParcel)
  {
    this(paramParcel, paramParcel.dataPosition(), paramParcel.dataSize(), "");
  }

  b(Parcel paramParcel, int paramInt1, int paramInt2, String paramString)
  {
    this.b = paramParcel;
    this.c = paramInt1;
    this.d = paramInt2;
    this.g = this.c;
    this.e = paramString;
  }

  private int d(int paramInt)
  {
    while (this.g < this.d)
    {
      this.b.setDataPosition(this.g);
      int i = this.b.readInt();
      int j = this.b.readInt();
      this.g = (i + this.g);
      if (j == paramInt)
        return this.b.dataPosition();
    }
    return -1;
  }

  public void a(int paramInt)
  {
    this.b.writeInt(paramInt);
  }

  public void a(Parcelable paramParcelable)
  {
    this.b.writeParcelable(paramParcelable, 0);
  }

  public void a(String paramString)
  {
    this.b.writeString(paramString);
  }

  public void a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
    {
      this.b.writeInt(paramArrayOfByte.length);
      this.b.writeByteArray(paramArrayOfByte);
    }
    else
    {
      this.b.writeInt(-1);
    }
  }

  public void b()
  {
    if (this.f >= 0)
    {
      int i = this.a.get(this.f);
      int j = this.b.dataPosition();
      int k = j - i;
      this.b.setDataPosition(i);
      this.b.writeInt(k);
      this.b.setDataPosition(j);
    }
  }

  public boolean b(int paramInt)
  {
    int i = d(paramInt);
    if (i == -1)
      return false;
    this.b.setDataPosition(i);
    return true;
  }

  protected a c()
  {
    Parcel localParcel = this.b;
    int i = this.b.dataPosition();
    int j;
    if (this.g == this.c)
      j = this.d;
    else
      j = this.g;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.e);
    localStringBuilder.append("  ");
    return new b(localParcel, i, j, localStringBuilder.toString());
  }

  public void c(int paramInt)
  {
    b();
    this.f = paramInt;
    this.a.put(paramInt, this.b.dataPosition());
    a(0);
    a(paramInt);
  }

  public int d()
  {
    return this.b.readInt();
  }

  public String e()
  {
    return this.b.readString();
  }

  public byte[] f()
  {
    int i = this.b.readInt();
    if (i < 0)
      return null;
    byte[] arrayOfByte = new byte[i];
    this.b.readByteArray(arrayOfByte);
    return arrayOfByte;
  }

  public <T extends Parcelable> T g()
  {
    return this.b.readParcelable(getClass().getClassLoader());
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     androidx.versionedparcelable.b
 * JD-Core Version:    0.6.1
 */