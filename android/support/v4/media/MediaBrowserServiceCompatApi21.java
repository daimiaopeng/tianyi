package android.support.v4.media;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser.MediaItem;
import android.media.session.MediaSession.Token;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.service.media.MediaBrowserService;
import android.service.media.MediaBrowserService.BrowserRoot;
import android.service.media.MediaBrowserService.Result;
import android.support.annotation.RequiresApi;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(21)
class MediaBrowserServiceCompatApi21
{
  public static Object createService(Context paramContext, ServiceCompatProxy paramServiceCompatProxy)
  {
    return new MediaBrowserServiceAdaptor(paramContext, paramServiceCompatProxy);
  }

  public static void notifyChildrenChanged(Object paramObject, String paramString)
  {
    ((MediaBrowserService)paramObject).notifyChildrenChanged(paramString);
  }

  public static IBinder onBind(Object paramObject, Intent paramIntent)
  {
    return ((MediaBrowserService)paramObject).onBind(paramIntent);
  }

  public static void onCreate(Object paramObject)
  {
    ((MediaBrowserService)paramObject).onCreate();
  }

  public static void setSessionToken(Object paramObject1, Object paramObject2)
  {
    ((MediaBrowserService)paramObject1).setSessionToken((MediaSession.Token)paramObject2);
  }

  static class BrowserRoot
  {
    final Bundle mExtras;
    final String mRootId;

    BrowserRoot(String paramString, Bundle paramBundle)
    {
      this.mRootId = paramString;
      this.mExtras = paramBundle;
    }
  }

  static class MediaBrowserServiceAdaptor extends MediaBrowserService
  {
    final MediaBrowserServiceCompatApi21.ServiceCompatProxy mServiceProxy;

    MediaBrowserServiceAdaptor(Context paramContext, MediaBrowserServiceCompatApi21.ServiceCompatProxy paramServiceCompatProxy)
    {
      attachBaseContext(paramContext);
      this.mServiceProxy = paramServiceCompatProxy;
    }

    public MediaBrowserService.BrowserRoot onGetRoot(String paramString, int paramInt, Bundle paramBundle)
    {
      MediaSessionCompat.ensureClassLoader(paramBundle);
      MediaBrowserServiceCompatApi21.ServiceCompatProxy localServiceCompatProxy = this.mServiceProxy;
      Bundle localBundle;
      if (paramBundle == null)
        localBundle = null;
      else
        localBundle = new Bundle(paramBundle);
      MediaBrowserServiceCompatApi21.BrowserRoot localBrowserRoot = localServiceCompatProxy.onGetRoot(paramString, paramInt, localBundle);
      MediaBrowserService.BrowserRoot localBrowserRoot1;
      if (localBrowserRoot == null)
        localBrowserRoot1 = null;
      else
        localBrowserRoot1 = new MediaBrowserService.BrowserRoot(localBrowserRoot.mRootId, localBrowserRoot.mExtras);
      return localBrowserRoot1;
    }

    public void onLoadChildren(String paramString, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> paramResult)
    {
      this.mServiceProxy.onLoadChildren(paramString, new MediaBrowserServiceCompatApi21.ResultWrapper(paramResult));
    }
  }

  static class ResultWrapper<T>
  {
    MediaBrowserService.Result mResultObj;

    ResultWrapper(MediaBrowserService.Result paramResult)
    {
      this.mResultObj = paramResult;
    }

    public void detach()
    {
      this.mResultObj.detach();
    }

    List<MediaBrowser.MediaItem> parcelListToItemList(List<Parcel> paramList)
    {
      if (paramList == null)
        return null;
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        Parcel localParcel = (Parcel)localIterator.next();
        localParcel.setDataPosition(0);
        localArrayList.add(MediaBrowser.MediaItem.CREATOR.createFromParcel(localParcel));
        localParcel.recycle();
      }
      return localArrayList;
    }

    public void sendResult(T paramT)
    {
      if ((paramT instanceof List))
      {
        this.mResultObj.sendResult(parcelListToItemList((List)paramT));
      }
      else if ((paramT instanceof Parcel))
      {
        Parcel localParcel = (Parcel)paramT;
        localParcel.setDataPosition(0);
        this.mResultObj.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(localParcel));
        localParcel.recycle();
      }
      else
      {
        this.mResultObj.sendResult(null);
      }
    }
  }

  public static abstract interface ServiceCompatProxy
  {
    public abstract MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(String paramString, int paramInt, Bundle paramBundle);

    public abstract void onLoadChildren(String paramString, MediaBrowserServiceCompatApi21.ResultWrapper<List<Parcel>> paramResultWrapper);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompatApi21
 * JD-Core Version:    0.6.1
 */