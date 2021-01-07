package com.qihoo.jiagutracker;

import com.qihoo.jiagutracker.utils.CircularBuffer;
import com.stub.StubApp;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@QVMProtect
public class TrackDataManager
{
  private static boolean INIT_Flag = false;
  private static CircularBuffer<Object> mCircularBuffer;
  private static JSONObject mShotScreen;

  static
  {
    StubApp.interface11(2737);
  }

  private static native JSONArray getArray(List<ViewInfo> paramList);

  private static native JSONObject getJsonObject(ViewInfo paramViewInfo);

  public static native String getString();

  public static native String[] getTrackData();

  public static native void init();

  public static native JSONObject parse(List<ViewInfo> paramList, String paramString, int paramInt1, int paramInt2, ViewInfo paramViewInfo);

  public static native boolean putElement(String paramString1, String paramString2, String paramString3, String paramString4);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagutracker.TrackDataManager
 * JD-Core Version:    0.6.1
 */