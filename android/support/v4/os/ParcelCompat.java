package android.support.v4.os;

import android.os.Parcel;

public final class ParcelCompat
{
  public static boolean readBoolean(Parcel paramParcel)
  {
    boolean bool;
    if (paramParcel.readInt() != 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public static void writeBoolean(Parcel paramParcel, boolean paramBoolean)
  {
    paramParcel.writeInt(paramBoolean);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.os.ParcelCompat
 * JD-Core Version:    0.6.1
 */