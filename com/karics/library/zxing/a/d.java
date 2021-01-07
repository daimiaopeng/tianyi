package com.karics.library.zxing.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.zxing.PlanarYUVLuminanceSource;

public final class d
{
  private static final String a = "d";
  private final Context b;
  private final b c;
  private Camera d;
  private a e;
  private Rect f;
  private Rect g;
  private boolean h;
  private boolean i;
  private int j = -1;
  private int k;
  private int l;
  private final f m;

  public d(Context paramContext)
  {
    this.b = paramContext;
    this.c = new b(paramContext);
    this.m = new f(this.c);
  }

  public PlanarYUVLuminanceSource a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Rect localRect = f();
    if (localRect == null)
      return null;
    PlanarYUVLuminanceSource localPlanarYUVLuminanceSource = new PlanarYUVLuminanceSource(paramArrayOfByte, paramInt1, paramInt2, localRect.left, localRect.top, localRect.width(), localRect.height(), false);
    return localPlanarYUVLuminanceSource;
  }

  public void a(int paramInt1, int paramInt2)
  {
    try
    {
      if (this.h)
      {
        Point localPoint = this.c.b();
        if (paramInt1 > localPoint.x)
          paramInt1 = localPoint.x;
        if (paramInt2 > localPoint.y)
          paramInt2 = localPoint.y;
        int n = (localPoint.x - paramInt1) / 2;
        int i1 = (localPoint.y - paramInt2) / 2;
        this.f = new Rect(n, i1, paramInt1 + n, paramInt2 + i1);
        String str = a;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Calculated manual framing rect: ");
        localStringBuilder.append(this.f);
        Log.d(str, localStringBuilder.toString());
        this.g = null;
      }
      else
      {
        this.k = paramInt1;
        this.l = paramInt2;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(Handler paramHandler, int paramInt)
  {
    try
    {
      Camera localCamera = this.d;
      if ((localCamera != null) && (this.i))
      {
        this.m.a(paramHandler, paramInt);
        localCamera.setOneShotPreviewCallback(this.m);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  public void a(android.view.SurfaceHolder paramSurfaceHolder)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 125	com/karics/library/zxing/a/d:d	Landroid/hardware/Camera;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnonnull +42 -> 50
    //   11: aload_0
    //   12: getfield 35	com/karics/library/zxing/a/d:j	I
    //   15: iflt +14 -> 29
    //   18: aload_0
    //   19: getfield 35	com/karics/library/zxing/a/d:j	I
    //   22: invokestatic 143	com/karics/library/zxing/a/e:a	(I)Landroid/hardware/Camera;
    //   25: astore_3
    //   26: goto +7 -> 33
    //   29: invokestatic 146	com/karics/library/zxing/a/e:a	()Landroid/hardware/Camera;
    //   32: astore_3
    //   33: aload_3
    //   34: ifnonnull +11 -> 45
    //   37: new 148	java/io/IOException
    //   40: dup
    //   41: invokespecial 149	java/io/IOException:<init>	()V
    //   44: athrow
    //   45: aload_0
    //   46: aload_3
    //   47: putfield 125	com/karics/library/zxing/a/d:d	Landroid/hardware/Camera;
    //   50: aload_3
    //   51: aload_1
    //   52: invokevirtual 152	android/hardware/Camera:setPreviewDisplay	(Landroid/view/SurfaceHolder;)V
    //   55: aload_0
    //   56: getfield 77	com/karics/library/zxing/a/d:h	Z
    //   59: ifne +52 -> 111
    //   62: aload_0
    //   63: iconst_1
    //   64: putfield 77	com/karics/library/zxing/a/d:h	Z
    //   67: aload_0
    //   68: getfield 43	com/karics/library/zxing/a/d:c	Lcom/karics/library/zxing/a/b;
    //   71: aload_3
    //   72: invokevirtual 155	com/karics/library/zxing/a/b:a	(Landroid/hardware/Camera;)V
    //   75: aload_0
    //   76: getfield 120	com/karics/library/zxing/a/d:k	I
    //   79: ifle +32 -> 111
    //   82: aload_0
    //   83: getfield 122	com/karics/library/zxing/a/d:l	I
    //   86: ifle +25 -> 111
    //   89: aload_0
    //   90: aload_0
    //   91: getfield 120	com/karics/library/zxing/a/d:k	I
    //   94: aload_0
    //   95: getfield 122	com/karics/library/zxing/a/d:l	I
    //   98: invokevirtual 157	com/karics/library/zxing/a/d:a	(II)V
    //   101: aload_0
    //   102: iconst_0
    //   103: putfield 120	com/karics/library/zxing/a/d:k	I
    //   106: aload_0
    //   107: iconst_0
    //   108: putfield 122	com/karics/library/zxing/a/d:l	I
    //   111: aload_3
    //   112: invokevirtual 161	android/hardware/Camera:getParameters	()Landroid/hardware/Camera$Parameters;
    //   115: astore 4
    //   117: aload 4
    //   119: ifnonnull +9 -> 128
    //   122: aconst_null
    //   123: astore 5
    //   125: goto +10 -> 135
    //   128: aload 4
    //   130: invokevirtual 166	android/hardware/Camera$Parameters:flatten	()Ljava/lang/String;
    //   133: astore 5
    //   135: aload_0
    //   136: getfield 43	com/karics/library/zxing/a/d:c	Lcom/karics/library/zxing/a/b;
    //   139: aload_3
    //   140: iconst_0
    //   141: invokevirtual 169	com/karics/library/zxing/a/b:a	(Landroid/hardware/Camera;Z)V
    //   144: goto +98 -> 242
    //   147: getstatic 95	com/karics/library/zxing/a/d:a	Ljava/lang/String;
    //   150: ldc 171
    //   152: invokestatic 174	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   155: pop
    //   156: getstatic 95	com/karics/library/zxing/a/d:a	Ljava/lang/String;
    //   159: astore 7
    //   161: new 97	java/lang/StringBuilder
    //   164: dup
    //   165: invokespecial 98	java/lang/StringBuilder:<init>	()V
    //   168: astore 8
    //   170: aload 8
    //   172: ldc 176
    //   174: invokevirtual 104	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: pop
    //   178: aload 8
    //   180: aload 5
    //   182: invokevirtual 104	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: pop
    //   186: aload 7
    //   188: aload 8
    //   190: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: invokestatic 178	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   196: pop
    //   197: aload 5
    //   199: ifnull +43 -> 242
    //   202: aload_3
    //   203: invokevirtual 161	android/hardware/Camera:getParameters	()Landroid/hardware/Camera$Parameters;
    //   206: astore 12
    //   208: aload 12
    //   210: aload 5
    //   212: invokevirtual 182	android/hardware/Camera$Parameters:unflatten	(Ljava/lang/String;)V
    //   215: aload_3
    //   216: aload 12
    //   218: invokevirtual 186	android/hardware/Camera:setParameters	(Landroid/hardware/Camera$Parameters;)V
    //   221: aload_0
    //   222: getfield 43	com/karics/library/zxing/a/d:c	Lcom/karics/library/zxing/a/b;
    //   225: aload_3
    //   226: iconst_1
    //   227: invokevirtual 169	com/karics/library/zxing/a/b:a	(Landroid/hardware/Camera;Z)V
    //   230: goto +12 -> 242
    //   233: getstatic 95	com/karics/library/zxing/a/d:a	Ljava/lang/String;
    //   236: ldc 188
    //   238: invokestatic 174	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   241: pop
    //   242: aload_0
    //   243: monitorexit
    //   244: return
    //   245: astore_2
    //   246: aload_0
    //   247: monitorexit
    //   248: aload_2
    //   249: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   135	144	147	java/lang/RuntimeException
    //   215	230	233	java/lang/RuntimeException
    //   2	135	245	finally
    //   135	144	245	finally
    //   147	215	245	finally
    //   215	230	245	finally
    //   233	242	245	finally
  }

  public boolean a()
  {
    try
    {
      Camera localCamera = this.d;
      boolean bool;
      if (localCamera != null)
        bool = true;
      else
        bool = false;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void b()
  {
    try
    {
      if (this.d != null)
      {
        this.d.release();
        this.d = null;
        this.f = null;
        this.g = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void c()
  {
    try
    {
      Camera localCamera = this.d;
      if ((localCamera != null) && (!this.i))
      {
        localCamera.startPreview();
        this.i = true;
        this.e = new a(this.b, this.d);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void d()
  {
    try
    {
      if (this.e != null)
      {
        this.e.b();
        this.e = null;
      }
      if ((this.d != null) && (this.i))
      {
        this.d.stopPreview();
        this.m.a(null, 0);
        this.i = false;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Rect e()
  {
    try
    {
      if (this.f == null)
      {
        Camera localCamera = this.d;
        if (localCamera == null)
          return null;
        Point localPoint = this.c.b();
        if (localPoint == null)
          return null;
        int n = (int)(0.6D * this.b.getResources().getDisplayMetrics().widthPixels);
        int i1 = (int)(0.9D * n);
        int i2 = (localPoint.x - n) / 2;
        int i3 = (localPoint.y - i1) / 2;
        this.f = new Rect(i2, i3, n + i2, i1 + i3);
        String str = a;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Calculated framing rect: ");
        localStringBuilder.append(this.f);
        Log.d(str, localStringBuilder.toString());
      }
      Rect localRect = this.f;
      return localRect;
    }
    finally
    {
    }
  }

  public Rect f()
  {
    try
    {
      if (this.g == null)
      {
        Rect localRect2 = e();
        if (localRect2 == null)
          return null;
        Rect localRect3 = new Rect(localRect2);
        Point localPoint1 = this.c.a();
        Point localPoint2 = this.c.b();
        if ((localPoint1 != null) && (localPoint2 != null))
        {
          localRect3.left = (localRect3.left * localPoint1.y / localPoint2.x);
          localRect3.right = (localRect3.right * localPoint1.y / localPoint2.x);
          localRect3.top = (localRect3.top * localPoint1.x / localPoint2.y);
          localRect3.bottom = (localRect3.bottom * localPoint1.x / localPoint2.y);
          this.g = localRect3;
        }
        else
        {
          return null;
        }
      }
      Rect localRect1 = this.g;
      return localRect1;
    }
    finally
    {
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.a.d
 * JD-Core Version:    0.6.1
 */