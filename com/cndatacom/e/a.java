package com.cndatacom.e;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.concurrent.LinkedBlockingQueue;

public class a
{
  // ERROR //
  public static a a(android.content.Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 14	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   3: invokestatic 17	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   6: if_acmpne +13 -> 19
    //   9: new 19	java/lang/IllegalStateException
    //   12: dup
    //   13: ldc 21
    //   15: invokespecial 25	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   18: athrow
    //   19: aload_0
    //   20: invokevirtual 31	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   23: ldc 33
    //   25: iconst_0
    //   26: invokevirtual 39	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   29: pop
    //   30: new 41	com/cndatacom/e/a$b
    //   33: dup
    //   34: aconst_null
    //   35: invokespecial 44	com/cndatacom/e/a$b:<init>	(Lcom/cndatacom/e/a$1;)V
    //   38: astore_3
    //   39: new 46	android/content/Intent
    //   42: dup
    //   43: ldc 48
    //   45: invokespecial 49	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   48: astore 4
    //   50: aload 4
    //   52: ldc 51
    //   54: invokevirtual 55	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   57: pop
    //   58: aload_0
    //   59: aload 4
    //   61: aload_3
    //   62: iconst_1
    //   63: invokevirtual 59	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   66: ifeq +62 -> 128
    //   69: new 61	com/cndatacom/e/a$c
    //   72: dup
    //   73: aload_3
    //   74: invokevirtual 64	com/cndatacom/e/a$b:a	()Landroid/os/IBinder;
    //   77: invokespecial 67	com/cndatacom/e/a$c:<init>	(Landroid/os/IBinder;)V
    //   80: astore 6
    //   82: new 69	com/cndatacom/e/a$a
    //   85: dup
    //   86: aload 6
    //   88: invokevirtual 72	com/cndatacom/e/a$c:a	()Ljava/lang/String;
    //   91: aload 6
    //   93: iconst_1
    //   94: invokevirtual 75	com/cndatacom/e/a$c:a	(Z)Z
    //   97: invokespecial 78	com/cndatacom/e/a$a:<init>	(Ljava/lang/String;Z)V
    //   100: astore 7
    //   102: aload_0
    //   103: aload_3
    //   104: invokevirtual 82	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   107: aload 7
    //   109: areturn
    //   110: astore 9
    //   112: goto +8 -> 120
    //   115: astore 8
    //   117: aload 8
    //   119: athrow
    //   120: aload_0
    //   121: aload_3
    //   122: invokevirtual 82	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   125: aload 9
    //   127: athrow
    //   128: new 84	java/io/IOException
    //   131: dup
    //   132: ldc 86
    //   134: invokespecial 87	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   137: athrow
    //   138: astore_1
    //   139: aload_1
    //   140: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   69	102	110	finally
    //   117	120	110	finally
    //   69	102	115	java/lang/Exception
    //   19	30	138	java/lang/Exception
  }

  public static final class a
  {
    private final String a;
    private final boolean b;

    a(String paramString, boolean paramBoolean)
    {
      this.a = paramString;
      this.b = paramBoolean;
    }

    public String a()
    {
      return this.a;
    }
  }

  private static final class b
    implements ServiceConnection
  {
    boolean a = false;
    private final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue(1);

    public IBinder a()
    {
      if (this.a)
        throw new IllegalStateException();
      this.a = true;
      return (IBinder)this.b.take();
    }

    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      try
      {
        this.b.put(paramIBinder);
      }
      catch (InterruptedException localInterruptedException)
      {
      }
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
    }
  }

  private static final class c
    implements IInterface
  {
    private IBinder a;

    public c(IBinder paramIBinder)
    {
      this.a = paramIBinder;
    }

    public String a()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        this.a.transact(1, localParcel1, localParcel2, 0);
        localParcel2.readException();
        String str = localParcel2.readString();
        return str;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }

    public boolean a(boolean paramBoolean)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        localParcel1.writeInt(paramBoolean);
        this.a.transact(2, localParcel1, localParcel2, 0);
        localParcel2.readException();
        int i = localParcel2.readInt();
        boolean bool = false;
        if (i != 0)
          bool = true;
        return bool;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }

    public IBinder asBinder()
    {
      return this.a;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.a
 * JD-Core Version:    0.6.1
 */