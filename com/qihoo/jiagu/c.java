package com.qihoo.jiagu;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import com.qihoo.bugreport.Protocol.TrackerDataField;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipFile;
import org.json.JSONArray;
import org.json.JSONObject;

public class c
{
  public static String a(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte);
      byte[] arrayOfByte = localMessageDigest.digest();
      StringBuilder localStringBuilder = new StringBuilder(2 * arrayOfByte.length);
      int i = arrayOfByte.length;
      for (int j = 0; j < i; j++)
      {
        int k = arrayOfByte[j];
        if ((k & 0xFF) < 16)
          localStringBuilder.append("0");
        localStringBuilder.append(Integer.toHexString(k & 0xFF));
      }
      String str2 = localStringBuilder.toString();
      str1 = str2;
      return str1;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        String str1 = null;
    }
  }

  static JSONArray a()
  {
    int i = 0;
    JSONArray localJSONArray1 = new JSONArray();
    JSONArray localJSONArray2;
    try
    {
      String[] arrayOfString1 = (String[])Class.forName("com.qihoo.jiagutracker.TrackDataManager").getDeclaredMethod("getTrackData", new Class[0]).invoke(null, new Object[0]);
      int j = arrayOfString1.length;
      while (i < j)
      {
        String[] arrayOfString2 = arrayOfString1[i].split("->");
        if (Protocol.TrackerDataField.values().length != arrayOfString2.length)
        {
          localJSONArray2 = null;
          break label179;
        }
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.accumulate(Protocol.TrackerDataField.cn.name(), arrayOfString2[0]);
        localJSONObject.accumulate(Protocol.TrackerDataField.mn.name(), arrayOfString2[1]);
        localJSONObject.accumulate(Protocol.TrackerDataField.vi.name(), arrayOfString2[2]);
        localJSONObject.accumulate(Protocol.TrackerDataField.vt.name(), arrayOfString2[3]);
        localJSONObject.accumulate(Protocol.TrackerDataField.st.name(), arrayOfString2[4]);
        localJSONArray1.put(localJSONObject);
        i++;
      }
    }
    catch (Throwable localThrowable)
    {
      localJSONArray2 = localJSONArray1;
    }
    label179: return localJSONArray2;
  }

  // ERROR //
  public static JSONObject a(java.util.EnumMap<com.qihoo.bugreport.javacrash.ReportField, String> paramEnumMap, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_1
    //   3: ifnonnull +5 -> 8
    //   6: aload_2
    //   7: areturn
    //   8: new 93	org/json/JSONObject
    //   11: dup
    //   12: aload_1
    //   13: invokespecial 127	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   16: astore_3
    //   17: aload_3
    //   18: ldc 129
    //   20: invokevirtual 133	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   23: ifne +14 -> 37
    //   26: aload_3
    //   27: ldc 129
    //   29: iconst_1
    //   30: invokestatic 137	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   33: invokevirtual 105	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   36: pop
    //   37: aload_0
    //   38: invokevirtual 143	java/util/EnumMap:keySet	()Ljava/util/Set;
    //   41: invokeinterface 149 1 0
    //   46: astore 4
    //   48: aload 4
    //   50: invokeinterface 155 1 0
    //   55: ifeq +142 -> 197
    //   58: aload 4
    //   60: invokeinterface 159 1 0
    //   65: checkcast 161	com/qihoo/bugreport/javacrash/ReportField
    //   68: astore 8
    //   70: aload 8
    //   72: invokevirtual 162	com/qihoo/bugreport/javacrash/ReportField:name	()Ljava/lang/String;
    //   75: getstatic 166	com/qihoo/bugreport/javacrash/ReportField:t	Lcom/qihoo/bugreport/javacrash/ReportField;
    //   78: invokevirtual 162	com/qihoo/bugreport/javacrash/ReportField:name	()Ljava/lang/String;
    //   81: invokevirtual 170	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   84: ifne +37 -> 121
    //   87: aload 8
    //   89: invokevirtual 162	com/qihoo/bugreport/javacrash/ReportField:name	()Ljava/lang/String;
    //   92: getstatic 173	com/qihoo/bugreport/javacrash/ReportField:cpv	Lcom/qihoo/bugreport/javacrash/ReportField;
    //   95: invokevirtual 162	com/qihoo/bugreport/javacrash/ReportField:name	()Ljava/lang/String;
    //   98: invokevirtual 170	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   101: ifne +20 -> 121
    //   104: aload 8
    //   106: invokevirtual 162	com/qihoo/bugreport/javacrash/ReportField:name	()Ljava/lang/String;
    //   109: getstatic 176	com/qihoo/bugreport/javacrash/ReportField:jc	Lcom/qihoo/bugreport/javacrash/ReportField;
    //   112: invokevirtual 162	com/qihoo/bugreport/javacrash/ReportField:name	()Ljava/lang/String;
    //   115: invokevirtual 170	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   118: ifeq +60 -> 178
    //   121: aload_0
    //   122: aload 8
    //   124: invokevirtual 180	java/util/EnumMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   127: checkcast 81	java/lang/String
    //   130: astore 10
    //   132: aload 8
    //   134: invokevirtual 162	com/qihoo/bugreport/javacrash/ReportField:name	()Ljava/lang/String;
    //   137: astore 11
    //   139: ldc 182
    //   141: invokestatic 188	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   144: aload 10
    //   146: invokevirtual 192	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   149: invokevirtual 197	java/util/regex/Matcher:matches	()Z
    //   152: iconst_1
    //   153: if_icmpne +94 -> 247
    //   156: aload 10
    //   158: invokestatic 201	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   161: invokestatic 137	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   164: astore 12
    //   166: aload_3
    //   167: aload 11
    //   169: aload 12
    //   171: invokevirtual 105	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   174: pop
    //   175: goto -127 -> 48
    //   178: aload_3
    //   179: aload 8
    //   181: invokevirtual 162	com/qihoo/bugreport/javacrash/ReportField:name	()Ljava/lang/String;
    //   184: aload_0
    //   185: aload 8
    //   187: invokevirtual 180	java/util/EnumMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   190: invokevirtual 105	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   193: pop
    //   194: goto -146 -> 48
    //   197: invokestatic 203	com/qihoo/jiagu/c:a	()Lorg/json/JSONArray;
    //   200: astore 5
    //   202: aload 5
    //   204: ifnull +16 -> 220
    //   207: aload_3
    //   208: getstatic 206	com/qihoo/bugreport/javacrash/ReportField:rt	Lcom/qihoo/bugreport/javacrash/ReportField;
    //   211: invokevirtual 162	com/qihoo/bugreport/javacrash/ReportField:name	()Ljava/lang/String;
    //   214: aload 5
    //   216: invokevirtual 105	org/json/JSONObject:accumulate	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   219: pop
    //   220: aload_3
    //   221: astore_2
    //   222: goto -216 -> 6
    //   225: astore 6
    //   227: goto -7 -> 220
    //   230: astore 15
    //   232: goto -195 -> 37
    //   235: astore 17
    //   237: aconst_null
    //   238: astore_2
    //   239: goto -233 -> 6
    //   242: astore 9
    //   244: goto -196 -> 48
    //   247: ldc 208
    //   249: astore 12
    //   251: goto -85 -> 166
    //
    // Exception table:
    //   from	to	target	type
    //   207	220	225	org/json/JSONException
    //   26	37	230	org/json/JSONException
    //   8	17	235	org/json/JSONException
    //   70	194	242	org/json/JSONException
  }

  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool1 = false;
    if (paramContext != null)
    {
      boolean bool2 = paramString.isEmpty();
      bool1 = false;
      if (!bool2)
        break label19;
    }
    while (true)
    {
      return bool1;
      label19: if (Build.VERSION.SDK_INT >= 23)
      {
        bool1 = b(paramContext, paramString);
      }
      else
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        bool1 = false;
        if (localPackageManager != null)
          try
          {
            int i = localPackageManager.checkPermission(paramString, paramContext.getPackageName());
            bool1 = false;
            if (i == 0)
              bool1 = true;
          }
          catch (RuntimeException localRuntimeException)
          {
            bool1 = false;
          }
      }
    }
  }

  public static boolean b(Context paramContext, String paramString)
  {
    boolean bool = true;
    if ((paramContext == null) || (paramString.isEmpty()))
    {
      bool = false;
      return bool;
    }
    while (true)
    {
      try
      {
        int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.targetSdkVersion;
        if (i >= 23)
        {
          if (ContextCompat.checkSelfPermission(paramContext, paramString) != 0)
            break label109;
          new StringBuilder("targetSdkVersion01:").append(i);
          break;
        }
        if (PermissionChecker.checkSelfPermission(paramContext, paramString) == 0)
        {
          new StringBuilder("targetSdkVersion02:").append(i);
          break;
        }
        bool = false;
        continue;
      }
      catch (Throwable localThrowable)
      {
        bool = false;
      }
      break;
      label109: bool = false;
    }
  }

  public static boolean c(Context paramContext, String paramString)
  {
    String str = paramContext.getApplicationInfo().sourceDir;
    try
    {
      ZipFile localZipFile = new ZipFile(str);
      if (localZipFile.getEntry("META-INF/" + paramString) != null)
      {
        localZipFile.close();
        bool = true;
      }
      while (true)
      {
        return bool;
        try
        {
          localZipFile.close();
          bool = false;
        }
        catch (Exception localException2)
        {
          bool = false;
        }
      }
    }
    catch (Exception localException1)
    {
      while (true)
        boolean bool = false;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.jiagu.c
 * JD-Core Version:    0.6.1
 */