package android.support.v4.os;

import android.os.Build.VERSION;

public final class CancellationSignal
{
  private boolean mCancelInProgress;
  private Object mCancellationSignalObj;
  private boolean mIsCanceled;
  private OnCancelListener mOnCancelListener;

  // ERROR //
  private void waitForCancelFinishedLocked()
  {
    // Byte code:
    //   0: goto +4 -> 4
    //   3: pop
    //   4: aload_0
    //   5: getfield 20	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   8: ifeq +10 -> 18
    //   11: aload_0
    //   12: invokevirtual 23	java/lang/Object:wait	()V
    //   15: goto -12 -> 3
    //   18: return
    //
    // Exception table:
    //   from	to	target	type
    //   11	15	3	java/lang/InterruptedException
  }

  // ERROR //
  public void cancel()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 26	android/support/v4/os/CancellationSignal:mIsCanceled	Z
    //   6: ifeq +6 -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: iconst_1
    //   14: putfield 26	android/support/v4/os/CancellationSignal:mIsCanceled	Z
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield 20	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   22: aload_0
    //   23: getfield 28	android/support/v4/os/CancellationSignal:mOnCancelListener	Landroid/support/v4/os/CancellationSignal$OnCancelListener;
    //   26: astore_2
    //   27: aload_0
    //   28: getfield 30	android/support/v4/os/CancellationSignal:mCancellationSignalObj	Ljava/lang/Object;
    //   31: astore_3
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_2
    //   35: ifnull +12 -> 47
    //   38: aload_2
    //   39: invokeinterface 35 1 0
    //   44: goto +3 -> 47
    //   47: aload_3
    //   48: ifnull +44 -> 92
    //   51: getstatic 41	android/os/Build$VERSION:SDK_INT	I
    //   54: bipush 16
    //   56: if_icmplt +36 -> 92
    //   59: aload_3
    //   60: checkcast 43	android/os/CancellationSignal
    //   63: invokevirtual 45	android/os/CancellationSignal:cancel	()V
    //   66: goto +26 -> 92
    //   69: aload_0
    //   70: monitorenter
    //   71: aload_0
    //   72: iconst_0
    //   73: putfield 20	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   76: aload_0
    //   77: invokevirtual 48	java/lang/Object:notifyAll	()V
    //   80: aload_0
    //   81: monitorexit
    //   82: aload 5
    //   84: athrow
    //   85: astore 6
    //   87: aload_0
    //   88: monitorexit
    //   89: aload 6
    //   91: athrow
    //   92: aload_0
    //   93: monitorenter
    //   94: aload_0
    //   95: iconst_0
    //   96: putfield 20	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   99: aload_0
    //   100: invokevirtual 48	java/lang/Object:notifyAll	()V
    //   103: aload_0
    //   104: monitorexit
    //   105: return
    //   106: astore 4
    //   108: aload_0
    //   109: monitorexit
    //   110: aload 4
    //   112: athrow
    //   113: astore_1
    //   114: aload_0
    //   115: monitorexit
    //   116: aload_1
    //   117: athrow
    //   118: astore 5
    //   120: goto -51 -> 69
    //
    // Exception table:
    //   from	to	target	type
    //   71	82	85	finally
    //   87	89	85	finally
    //   94	110	106	finally
    //   2	34	113	finally
    //   114	116	113	finally
    //   38	66	118	finally
  }

  public Object getCancellationSignalObject()
  {
    if (Build.VERSION.SDK_INT < 16)
      return null;
    try
    {
      if (this.mCancellationSignalObj == null)
      {
        this.mCancellationSignalObj = new android.os.CancellationSignal();
        if (this.mIsCanceled)
          ((android.os.CancellationSignal)this.mCancellationSignalObj).cancel();
      }
      Object localObject2 = this.mCancellationSignalObj;
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw localObject1;
    }
  }

  public boolean isCanceled()
  {
    try
    {
      boolean bool = this.mIsCanceled;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setOnCancelListener(OnCancelListener paramOnCancelListener)
  {
    try
    {
      waitForCancelFinishedLocked();
      if (this.mOnCancelListener == paramOnCancelListener)
        return;
      this.mOnCancelListener = paramOnCancelListener;
      if ((this.mIsCanceled) && (paramOnCancelListener != null))
      {
        paramOnCancelListener.onCancel();
        return;
      }
      return;
    }
    finally
    {
    }
  }

  public void throwIfCanceled()
  {
    if (isCanceled())
      throw new OperationCanceledException();
  }

  public static abstract interface OnCancelListener
  {
    public abstract void onCancel();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.os.CancellationSignal
 * JD-Core Version:    0.6.1
 */