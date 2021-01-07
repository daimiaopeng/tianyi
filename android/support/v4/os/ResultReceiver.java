package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class ResultReceiver
  implements Parcelable
{
  public static final Parcelable.Creator<ResultReceiver> CREATOR = new Parcelable.Creator()
  {
    public ResultReceiver createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ResultReceiver(paramAnonymousParcel);
    }

    public ResultReceiver[] newArray(int paramAnonymousInt)
    {
      return new ResultReceiver[paramAnonymousInt];
    }
  };
  final Handler mHandler;
  final boolean mLocal;
  IResultReceiver mReceiver;

  public ResultReceiver(Handler paramHandler)
  {
    this.mLocal = true;
    this.mHandler = paramHandler;
  }

  ResultReceiver(Parcel paramParcel)
  {
    this.mLocal = false;
    this.mHandler = null;
    this.mReceiver = IResultReceiver.Stub.asInterface(paramParcel.readStrongBinder());
  }

  public int describeContents()
  {
    return 0;
  }

  protected void onReceiveResult(int paramInt, Bundle paramBundle)
  {
  }

  public void send(int paramInt, Bundle paramBundle)
  {
    if (this.mLocal)
    {
      if (this.mHandler != null)
        this.mHandler.post(new MyRunnable(paramInt, paramBundle));
      else
        onReceiveResult(paramInt, paramBundle);
      return;
    }
    if (this.mReceiver != null);
    try
    {
      this.mReceiver.send(paramInt, paramBundle);
    }
    catch (RemoteException localRemoteException)
    {
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    try
    {
      if (this.mReceiver == null)
        this.mReceiver = new MyResultReceiver();
      paramParcel.writeStrongBinder(this.mReceiver.asBinder());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  class MyResultReceiver extends IResultReceiver.Stub
  {
    MyResultReceiver()
    {
    }

    public void send(int paramInt, Bundle paramBundle)
    {
      if (ResultReceiver.this.mHandler != null)
        ResultReceiver.this.mHandler.post(new ResultReceiver.MyRunnable(ResultReceiver.this, paramInt, paramBundle));
      else
        ResultReceiver.this.onReceiveResult(paramInt, paramBundle);
    }
  }

  class MyRunnable
    implements Runnable
  {
    final int mResultCode;
    final Bundle mResultData;

    MyRunnable(int paramBundle, Bundle arg3)
    {
      this.mResultCode = paramBundle;
      Object localObject;
      this.mResultData = localObject;
    }

    public void run()
    {
      ResultReceiver.this.onReceiveResult(this.mResultCode, this.mResultData);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.os.ResultReceiver
 * JD-Core Version:    0.6.1
 */