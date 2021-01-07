package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.support.annotation.RequiresApi;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RequiresApi(21)
class ParceledListSliceAdapterApi21
{
  private static Constructor sConstructor;

  static
  {
    try
    {
      sConstructor = Class.forName("android.content.pm.ParceledListSlice").getConstructor(new Class[] { List.class });
    }
    catch (ClassNotFoundException|NoSuchMethodException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
    }
  }

  static Object newInstance(List<MediaBrowser.MediaItem> paramList)
  {
    Object localObject;
    try
    {
      localObject = sConstructor.newInstance(new Object[] { paramList });
    }
    catch (InstantiationException|IllegalAccessException|InvocationTargetException localInstantiationException)
    {
      localInstantiationException.printStackTrace();
      localObject = null;
    }
    return localObject;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.ParceledListSliceAdapterApi21
 * JD-Core Version:    0.6.1
 */