package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.proguard.z;
import java.util.Map;
import java.util.UUID;

public class CrashDetailBean
  implements Parcelable, Comparable<CrashDetailBean>
{
  public static final Parcelable.Creator<CrashDetailBean> CREATOR = new Parcelable.Creator()
  {
  };
  public String A = "";
  public long B = -1L;
  public long C = -1L;
  public long D = -1L;
  public long E = -1L;
  public long F = -1L;
  public long G = -1L;
  public String H = "";
  public String I = "";
  public String J = "";
  public String K = "";
  public long L = -1L;
  public boolean M = false;
  public Map<String, String> N = null;
  public int O = -1;
  public int P = -1;
  public Map<String, String> Q = null;
  public Map<String, String> R = null;
  public byte[] S = null;
  public String T = null;
  public String U = null;
  private String V = "";
  public long a = -1L;
  public int b = 0;
  public String c = UUID.randomUUID().toString();
  public boolean d = false;
  public String e = "";
  public String f = "";
  public String g = "";
  public Map<String, PlugInBean> h = null;
  public Map<String, PlugInBean> i = null;
  public boolean j = false;
  public boolean k = false;
  public int l = 0;
  public String m = "";
  public String n = "";
  public String o = "";
  public String p = "";
  public String q = "";
  public long r = -1L;
  public String s = null;
  public int t = 0;
  public String u = "";
  public String v = "";
  public String w = null;
  public byte[] x = null;
  public Map<String, String> y = null;
  public String z = "";

  public CrashDetailBean()
  {
  }

  public CrashDetailBean(Parcel paramParcel)
  {
    this.b = paramParcel.readInt();
    this.c = paramParcel.readString();
    int i1 = paramParcel.readByte();
    int i2 = 1;
    boolean bool1;
    if (i1 == i2)
      bool1 = true;
    else
      bool1 = false;
    this.d = bool1;
    this.e = paramParcel.readString();
    this.f = paramParcel.readString();
    this.g = paramParcel.readString();
    boolean bool2;
    if (paramParcel.readByte() == i2)
      bool2 = true;
    else
      bool2 = false;
    this.j = bool2;
    boolean bool3;
    if (paramParcel.readByte() == i2)
      bool3 = true;
    else
      bool3 = false;
    this.k = bool3;
    this.l = paramParcel.readInt();
    this.m = paramParcel.readString();
    this.n = paramParcel.readString();
    this.o = paramParcel.readString();
    this.p = paramParcel.readString();
    this.q = paramParcel.readString();
    this.r = paramParcel.readLong();
    this.s = paramParcel.readString();
    this.t = paramParcel.readInt();
    this.u = paramParcel.readString();
    this.v = paramParcel.readString();
    this.w = paramParcel.readString();
    this.y = z.b(paramParcel);
    this.z = paramParcel.readString();
    this.A = paramParcel.readString();
    this.B = paramParcel.readLong();
    this.C = paramParcel.readLong();
    this.D = paramParcel.readLong();
    this.E = paramParcel.readLong();
    this.F = paramParcel.readLong();
    this.G = paramParcel.readLong();
    this.H = paramParcel.readString();
    this.V = paramParcel.readString();
    this.I = paramParcel.readString();
    this.J = paramParcel.readString();
    this.K = paramParcel.readString();
    this.L = paramParcel.readLong();
    if (paramParcel.readByte() != i2)
      i2 = 0;
    this.M = i2;
    this.N = z.b(paramParcel);
    this.h = z.a(paramParcel);
    this.i = z.a(paramParcel);
    this.O = paramParcel.readInt();
    this.P = paramParcel.readInt();
    this.Q = z.b(paramParcel);
    this.R = z.b(paramParcel);
    this.S = paramParcel.createByteArray();
    this.x = paramParcel.createByteArray();
    this.T = paramParcel.readString();
    this.U = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.b);
    paramParcel.writeString(this.c);
    paramParcel.writeByte((byte)this.d);
    paramParcel.writeString(this.e);
    paramParcel.writeString(this.f);
    paramParcel.writeString(this.g);
    paramParcel.writeByte((byte)this.j);
    paramParcel.writeByte((byte)this.k);
    paramParcel.writeInt(this.l);
    paramParcel.writeString(this.m);
    paramParcel.writeString(this.n);
    paramParcel.writeString(this.o);
    paramParcel.writeString(this.p);
    paramParcel.writeString(this.q);
    paramParcel.writeLong(this.r);
    paramParcel.writeString(this.s);
    paramParcel.writeInt(this.t);
    paramParcel.writeString(this.u);
    paramParcel.writeString(this.v);
    paramParcel.writeString(this.w);
    z.b(paramParcel, this.y);
    paramParcel.writeString(this.z);
    paramParcel.writeString(this.A);
    paramParcel.writeLong(this.B);
    paramParcel.writeLong(this.C);
    paramParcel.writeLong(this.D);
    paramParcel.writeLong(this.E);
    paramParcel.writeLong(this.F);
    paramParcel.writeLong(this.G);
    paramParcel.writeString(this.H);
    paramParcel.writeString(this.V);
    paramParcel.writeString(this.I);
    paramParcel.writeString(this.J);
    paramParcel.writeString(this.K);
    paramParcel.writeLong(this.L);
    paramParcel.writeByte((byte)this.M);
    z.b(paramParcel, this.N);
    z.a(paramParcel, this.h);
    z.a(paramParcel, this.i);
    paramParcel.writeInt(this.O);
    paramParcel.writeInt(this.P);
    z.b(paramParcel, this.Q);
    z.b(paramParcel, this.R);
    paramParcel.writeByteArray(this.S);
    paramParcel.writeByteArray(this.x);
    paramParcel.writeString(this.T);
    paramParcel.writeString(this.U);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.CrashDetailBean
 * JD-Core Version:    0.6.1
 */