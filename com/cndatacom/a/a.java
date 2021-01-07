package com.cndatacom.a;

import android.content.Context;
import android.os.Build.VERSION;
import com.cndatacom.e.m;
import com.cndatacom.xjhui.b.a.d;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class a
{
  public Map<String, String> a(Context paramContext, String paramString1, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Account", paramString2);
    localHashMap.put("UpdateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date()));
    localHashMap.put("IsFirstTime", "1");
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(d.d(paramContext));
    localStringBuilder1.append("");
    localHashMap.put("Versions", localStringBuilder1.toString());
    localHashMap.put("Language", "2052_936");
    localHashMap.put("DialUptype", "3");
    localHashMap.put("ClientID", d.e(paramContext));
    localHashMap.put("CID", paramString1);
    localHashMap.put("areaCode", "zs");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("android");
    localStringBuilder2.append(Build.VERSION.RELEASE);
    localHashMap.put("osType", localStringBuilder2.toString());
    localHashMap.put("AID", m.b(paramContext, "GoogleAdvertisingId", ""));
    return localHashMap;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.a.a
 * JD-Core Version:    0.6.1
 */