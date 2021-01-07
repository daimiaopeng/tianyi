package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.proguard.z;
import java.util.Map;

public class StrategyBean
  implements Parcelable
{
  public static final Parcelable.Creator<StrategyBean> CREATOR = new Parcelable.Creator()
  {
  };
  public static String a = "http://rqd.uu.qq.com/rqd/sync";
  public static String b = "http://android.bugly.qq.com/rqd/async";
  public static String c = "http://android.bugly.qq.com/rqd/async";
  public static String d;
  public long e = -1L;
  public long f = -1L;
  public boolean g;
  public boolean h;
  public boolean i;
  public boolean j;
  public boolean k;
  public boolean l;
  public boolean m;
  public boolean n;
  public boolean o;
  public long p;
  public long q;
  public String r;
  public String s;
  public String t;
  public String u;
  public Map<String, String> v;
  public int w;
  public long x;
  public long y;

  public StrategyBean()
  {
    this.g = true;
    this.h = true;
    this.i = true;
    this.j = true;
    this.k = false;
    this.l = true;
    this.m = true;
    this.n = true;
    this.o = true;
    this.q = 30000L;
    this.r = b;
    this.s = c;
    this.t = a;
    this.w = 10;
    this.x = 300000L;
    this.y = -1L;
    this.f = System.currentTimeMillis();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("S(@L@L");
    localStringBuilder.append("@)");
    d = localStringBuilder.toString();
    localStringBuilder.setLength(0);
    localStringBuilder.append("*^@K#K");
    localStringBuilder.append("@!");
    this.u = localStringBuilder.toString();
  }

  public StrategyBean(Parcel paramParcel)
  {
    int i1 = 1;
    this.g = i1;
    this.h = i1;
    this.i = i1;
    this.j = i1;
    this.k = false;
    this.l = i1;
    this.m = i1;
    this.n = i1;
    this.o = i1;
    this.q = 30000L;
    this.r = b;
    this.s = c;
    this.t = a;
    this.w = 10;
    this.x = 300000L;
    this.y = -1L;
    while (true)
    {
      try
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("S(@L@L");
        localStringBuilder.append("@)");
        d = localStringBuilder.toString();
        this.f = paramParcel.readLong();
        if (paramParcel.readByte() == i1)
        {
          bool1 = true;
          this.g = bool1;
          if (paramParcel.readByte() != i1)
            break label413;
          bool2 = true;
          this.h = bool2;
          if (paramParcel.readByte() != i1)
            break label419;
          bool3 = true;
          this.i = bool3;
          this.r = paramParcel.readString();
          this.s = paramParcel.readString();
          this.u = paramParcel.readString();
          this.v = z.b(paramParcel);
          if (paramParcel.readByte() != i1)
            break label425;
          bool4 = true;
          this.j = bool4;
          if (paramParcel.readByte() != i1)
            break label431;
          bool5 = true;
          this.k = bool5;
          if (paramParcel.readByte() != i1)
            break label437;
          bool6 = true;
          this.n = bool6;
          if (paramParcel.readByte() != i1)
            break label443;
          bool7 = true;
          this.o = bool7;
          this.q = paramParcel.readLong();
          if (paramParcel.readByte() != i1)
            break label449;
          bool8 = true;
          this.l = bool8;
          if (paramParcel.readByte() != i1)
            break label455;
          this.m = i1;
          this.p = paramParcel.readLong();
          this.w = paramParcel.readInt();
          this.x = paramParcel.readLong();
          this.y = paramParcel.readLong();
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      boolean bool1 = false;
      continue;
      label413: boolean bool2 = false;
      continue;
      label419: boolean bool3 = false;
      continue;
      label425: boolean bool4 = false;
      continue;
      label431: boolean bool5 = false;
      continue;
      label437: boolean bool6 = false;
      continue;
      label443: boolean bool7 = false;
      continue;
      label449: boolean bool8 = false;
      continue;
      label455: i1 = 0;
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(this.f);
    paramParcel.writeByte((byte)this.g);
    paramParcel.writeByte((byte)this.h);
    paramParcel.writeByte((byte)this.i);
    paramParcel.writeString(this.r);
    paramParcel.writeString(this.s);
    paramParcel.writeString(this.u);
    z.b(paramParcel, this.v);
    paramParcel.writeByte((byte)this.j);
    paramParcel.writeByte((byte)this.k);
    paramParcel.writeByte((byte)this.n);
    paramParcel.writeByte((byte)this.o);
    paramParcel.writeLong(this.q);
    paramParcel.writeByte((byte)this.l);
    paramParcel.writeByte((byte)this.m);
    paramParcel.writeLong(this.p);
    paramParcel.writeInt(this.w);
    paramParcel.writeLong(this.x);
    paramParcel.writeLong(this.y);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.common.strategy.StrategyBean
 * JD-Core Version:    0.6.1
 */