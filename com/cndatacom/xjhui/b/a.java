package com.cndatacom.xjhui.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.widget.Toast;
import com.cndatacom.campus.jscportal.MyApplication;
import com.cndatacom.e.j;
import com.cndatacom.e.m;
import com.cndatacom.e.o;
import com.cndatacom.xjhui.MainUiActivity;

@SuppressLint({"NewApi"})
public class a
{
  private static AsyncTask<Object, Object, Object> a;
  private static AsyncTask<Object, Object, Object> b;

  public static int a(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(paramString, 0);
    com.cndatacom.xjhui.b.a.c.a(paramContext, paramString);
    com.cndatacom.xjhui.b.a.c.a(paramContext, paramString, true);
    if (com.cndatacom.xjhui.b.a.c.b(paramContext, paramString))
    {
      MainUiActivity.b();
      g.c(paramContext);
      com.cndatacom.xjhui.b.a.a.a(paramContext);
      com.cndatacom.xjhui.b.a.a.c(paramContext);
      if (com.cndatacom.xjhui.b.a.a.b(paramContext))
        return 0;
      j.b("TrineaAndroidCommon", "ConfigUtils.isGetConfigSuccessfully : false");
      return 992;
    }
    if (localSharedPreferences.getBoolean("getConfigException", false))
      return 303;
    if (com.cndatacom.xjhui.b.a.b.c(paramContext))
      return 991;
    j.b("TrineaAndroidCommon", "EveryThingsUtils.isHaveNet : false");
    return 992;
  }

  public static long a(Context paramContext)
  {
    long l1 = c(paramContext);
    long l2 = 5000L;
    if (l1 >= l2)
      return 1L;
    if (l1 > 0L)
      l2 -= l1;
    return l2;
  }

  public static void a(final Context paramContext, final String paramString1, final String paramString2, final String paramString3, o paramo)
  {
    if (a != null)
    {
      paramo.a();
      return;
    }
    c.e(paramContext);
    AsyncTask local1 = new AsyncTask()
    {
      // ERROR //
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        // Byte code:
        //   0: sipush 333
        //   3: istore_2
        //   4: aload_0
        //   5: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   8: invokestatic 41	com/cndatacom/xjhui/b/g:b	(Landroid/content/Context;)Z
        //   11: istore 4
        //   13: new 43	java/lang/StringBuilder
        //   16: dup
        //   17: invokespecial 44	java/lang/StringBuilder:<init>	()V
        //   20: astore 5
        //   22: aload 5
        //   24: ldc 46
        //   26: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   29: pop
        //   30: aload 5
        //   32: iload 4
        //   34: invokevirtual 53	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
        //   37: pop
        //   38: ldc 55
        //   40: aload 5
        //   42: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   45: invokestatic 64	com/cndatacom/e/j:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   48: iload 4
        //   50: ifeq +10 -> 60
        //   53: aload_0
        //   54: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   57: invokestatic 67	com/cndatacom/xjhui/b/g:e	(Landroid/content/Context;)V
        //   60: aload_0
        //   61: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   64: invokestatic 70	com/cndatacom/xjhui/b/a:a	(Landroid/content/Context;)J
        //   67: lstore 8
        //   69: new 43	java/lang/StringBuilder
        //   72: dup
        //   73: invokespecial 44	java/lang/StringBuilder:<init>	()V
        //   76: astore 10
        //   78: aload 10
        //   80: ldc 72
        //   82: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   85: pop
        //   86: aload 10
        //   88: lload 8
        //   90: invokevirtual 75	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
        //   93: pop
        //   94: aload 10
        //   96: ldc 77
        //   98: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   101: pop
        //   102: ldc 55
        //   104: aload 10
        //   106: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   109: invokestatic 64	com/cndatacom/e/j:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   112: lload 8
        //   114: invokestatic 83	java/lang/Thread:sleep	(J)V
        //   117: aload_0
        //   118: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   121: ldc 85
        //   123: invokestatic 88	com/cndatacom/xjhui/b/a:a	(Landroid/content/Context;Ljava/lang/String;)I
        //   126: istore 14
        //   128: iload 14
        //   130: sipush 303
        //   133: if_icmpeq +366 -> 499
        //   136: iload 14
        //   138: tableswitch	default:+22 -> 160, 991:+354->492, 992:+347->485
        //   161: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   164: invokestatic 92	com/cndatacom/xjhui/b/a/b:e	(Landroid/content/Context;)Z
        //   167: ifeq +304 -> 471
        //   170: aload_0
        //   171: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   174: aload_0
        //   175: getfield 25	com/cndatacom/xjhui/b/a$1:c	Ljava/lang/String;
        //   178: iconst_0
        //   179: invokestatic 97	com/cndatacom/xjhui/b/i:a	(Landroid/content/Context;Ljava/lang/String;Z)I
        //   182: istore 16
        //   184: iload 16
        //   186: sipush 200
        //   189: if_icmpeq +10 -> 199
        //   192: iload 16
        //   194: bipush 13
        //   196: if_icmpne +17 -> 213
        //   199: aload_0
        //   200: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   203: aload_0
        //   204: getfield 25	com/cndatacom/xjhui/b/a$1:c	Ljava/lang/String;
        //   207: iconst_0
        //   208: invokestatic 97	com/cndatacom/xjhui/b/i:a	(Landroid/content/Context;Ljava/lang/String;Z)I
        //   211: istore 16
        //   213: iload 16
        //   215: ifne +316 -> 531
        //   218: aload_0
        //   219: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   222: invokestatic 99	com/cndatacom/xjhui/b/a/b:a	(Landroid/content/Context;)Z
        //   225: ifeq +306 -> 531
        //   228: aload_0
        //   229: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   232: aload_0
        //   233: getfield 25	com/cndatacom/xjhui/b/a$1:c	Ljava/lang/String;
        //   236: aload_0
        //   237: getfield 27	com/cndatacom/xjhui/b/a$1:d	Ljava/lang/String;
        //   240: aload_0
        //   241: getfield 29	com/cndatacom/xjhui/b/a$1:e	Ljava/lang/String;
        //   244: invokestatic 104	com/cndatacom/xjhui/b/d:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
        //   247: istore 19
        //   249: iload 19
        //   251: sipush 200
        //   254: if_icmpeq +19 -> 273
        //   257: iload 19
        //   259: bipush 13
        //   261: if_icmpne +6 -> 267
        //   264: goto +9 -> 273
        //   267: iload 19
        //   269: istore_2
        //   270: goto +23 -> 293
        //   273: aload_0
        //   274: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   277: aload_0
        //   278: getfield 25	com/cndatacom/xjhui/b/a$1:c	Ljava/lang/String;
        //   281: aload_0
        //   282: getfield 27	com/cndatacom/xjhui/b/a$1:d	Ljava/lang/String;
        //   285: aload_0
        //   286: getfield 29	com/cndatacom/xjhui/b/a$1:e	Ljava/lang/String;
        //   289: invokestatic 104	com/cndatacom/xjhui/b/d:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
        //   292: istore_2
        //   293: iload_2
        //   294: ifne +225 -> 519
        //   297: aload_0
        //   298: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   301: invokestatic 105	com/cndatacom/xjhui/b/a/b:b	(Landroid/content/Context;)Z
        //   304: ifeq +215 -> 519
        //   307: aload_0
        //   308: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   311: ldc 55
        //   313: iconst_0
        //   314: invokevirtual 111	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //   317: astore 20
        //   319: aload 20
        //   321: invokeinterface 117 1 0
        //   326: astore 21
        //   328: aload 21
        //   330: ldc 119
        //   332: ldc 121
        //   334: invokeinterface 127 3 0
        //   339: pop
        //   340: aload 21
        //   342: ldc 129
        //   344: aload_0
        //   345: getfield 25	com/cndatacom/xjhui/b/a$1:c	Ljava/lang/String;
        //   348: invokeinterface 127 3 0
        //   353: pop
        //   354: aload 21
        //   356: ldc 131
        //   358: aload_0
        //   359: getfield 27	com/cndatacom/xjhui/b/a$1:d	Ljava/lang/String;
        //   362: invokeinterface 127 3 0
        //   367: pop
        //   368: aload 21
        //   370: ldc 133
        //   372: aload_0
        //   373: getfield 27	com/cndatacom/xjhui/b/a$1:d	Ljava/lang/String;
        //   376: invokeinterface 127 3 0
        //   381: pop
        //   382: aload 21
        //   384: ldc 135
        //   386: invokestatic 141	java/lang/System:currentTimeMillis	()J
        //   389: invokeinterface 145 4 0
        //   394: pop
        //   395: aload 20
        //   397: ldc 147
        //   399: iconst_0
        //   400: invokeinterface 151 3 0
        //   405: ifeq +20 -> 425
        //   408: aload 21
        //   410: ldc 153
        //   412: aload_0
        //   413: getfield 27	com/cndatacom/xjhui/b/a$1:d	Ljava/lang/String;
        //   416: invokeinterface 127 3 0
        //   421: pop
        //   422: goto +15 -> 437
        //   425: aload 21
        //   427: ldc 153
        //   429: ldc 155
        //   431: invokeinterface 127 3 0
        //   436: pop
        //   437: aload 21
        //   439: invokeinterface 159 1 0
        //   444: pop
        //   445: aload_0
        //   446: getfield 23	com/cndatacom/xjhui/b/a$1:b	Landroid/content/Context;
        //   449: new 161	android/content/Intent
        //   452: dup
        //   453: ldc 163
        //   455: invokespecial 166	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   458: ldc 168
        //   460: ldc 170
        //   462: invokevirtual 174	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   465: invokevirtual 178	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
        //   468: goto +51 -> 519
        //   471: ldc 55
        //   473: ldc 180
        //   475: invokestatic 64	com/cndatacom/e/j:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   478: sipush 992
        //   481: istore_2
        //   482: goto +37 -> 519
        //   485: sipush 992
        //   488: invokestatic 186	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   491: areturn
        //   492: sipush 991
        //   495: invokestatic 186	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   498: areturn
        //   499: sipush 303
        //   502: invokestatic 186	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   505: astore 15
        //   507: aload 15
        //   509: areturn
        //   510: astore_3
        //   511: ldc 55
        //   513: aload_3
        //   514: ldc 188
        //   516: invokestatic 191	com/cndatacom/e/j:a	(Ljava/lang/String;Ljava/lang/Exception;Ljava/lang/String;)V
        //   519: iload_2
        //   520: invokestatic 186	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   523: areturn
        //   524: astore_3
        //   525: iload 19
        //   527: istore_2
        //   528: goto -17 -> 511
        //   531: iload 16
        //   533: istore_2
        //   534: goto -15 -> 519
        //   537: astore 17
        //   539: iload 16
        //   541: istore 18
        //   543: aload 17
        //   545: astore_3
        //   546: iload 18
        //   548: istore_2
        //   549: goto -38 -> 511
        //
        // Exception table:
        //   from	to	target	type
        //   4	184	510	java/lang/Exception
        //   297	507	510	java/lang/Exception
        //   273	293	524	java/lang/Exception
        //   199	249	537	java/lang/Exception
      }

      protected void onCancelled()
      {
        a.a(null);
        this.a.b();
      }

      protected void onPostExecute(Object paramAnonymousObject)
      {
        a.a(paramAnonymousObject, this.a, paramContext);
      }

      protected void onPreExecute()
      {
        super.onPreExecute();
        this.a.a();
      }
    };
    a = local1;
    a.executeOnExecutor(MyApplication.a().b(), new Object[0]);
  }

  private static int b(Context paramContext, String paramString1, String paramString2)
  {
    if (com.cndatacom.xjhui.b.a.a.b(paramContext))
    {
      int i = i.a(paramContext, paramString1, false);
      if ((i == 200) || (i == 13))
        i = i.a(paramContext, paramString1, false);
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("toAutoReconnect ticket result : ");
      localStringBuilder1.append(i);
      j.b("TrineaAndroidCommon", localStringBuilder1.toString());
      if (i == 0)
      {
        String str = m.b(paramContext, "verificatecode", "");
        int j = d.a(paramContext, paramString1, paramString2, str);
        if ((j == 200) || (j == 13))
          j = d.a(paramContext, paramString1, paramString2, str);
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("toAutoReconnect auth result : ");
        localStringBuilder2.append(j);
        j.b("TrineaAndroidCommon", localStringBuilder2.toString());
        if (j == 0)
        {
          SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("TrineaAndroidCommon", 0).edit();
          localEditor.putString("SID", "1");
          localEditor.putLong("preTimeMillis", System.currentTimeMillis());
          localEditor.commit();
          return 0;
        }
      }
    }
    return 2;
  }

  public static void b(Context paramContext)
  {
    boolean bool1 = m.b(paramContext, "iscandozdcl", false);
    boolean bool2 = m.b(paramContext, "iszdlj", true);
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("doAutoReconnect status : ");
    localStringBuilder1.append(bool1);
    j.b("TrineaAndroidCommon", localStringBuilder1.toString());
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("doAutoReconnect auto reconnect : ");
    localStringBuilder2.append(bool2);
    j.b("TrineaAndroidCommon", localStringBuilder2.toString());
    if ((bool1) && (bool2))
    {
      if (b != null)
      {
        b.cancel(true);
        b = null;
      }
      if ((com.cndatacom.xjhui.b.a.b.g(paramContext)) && (com.cndatacom.xjhui.b.a.b.e(paramContext)))
      {
        j.b("TrineaAndroidCommon", "doAutoReconnect the last WiFi");
        d(paramContext);
      }
      else
      {
        j.b("TrineaAndroidCommon", "doAutoReconnect the WiFi ssid or ip is different");
        com.cndatacom.b.a.b(paramContext, "com.cndatacom.jscportal.ACTION_STATE", "STATE_CHANGED");
      }
      return;
    }
    com.cndatacom.b.a.b(paramContext, "com.cndatacom.jscportal.ACTION_STATE", "STATE_CHANGED");
  }

  private static void b(Object paramObject, o paramo, Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("doAuthAction callbackRequest : ");
    localStringBuilder.append(paramObject);
    j.b("TrineaAndroidCommon", localStringBuilder.toString());
    a = null;
    paramo.b();
    int i = Integer.parseInt(paramObject.toString());
    if (i == 0)
    {
      Toast.makeText(paramContext, "登录成功", 0).show();
      m.a(paramContext, "iscandozdcl", true);
      com.cndatacom.xjhui.b.a.b.d(paramContext);
      c.a(paramContext);
      paramContext.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "STATE_CHANGED"));
      MainUiActivity.a();
    }
    else
    {
      c.a(paramContext, b.a(i), i);
    }
  }

  private static long c(Context paramContext)
  {
    long l = m.b(paramContext, "sCTT", 666L);
    if (l == 666L)
      return 9001L;
    return System.currentTimeMillis() - l;
  }

  @SuppressLint({"NewApi"})
  private static void d(Context paramContext)
  {
    b = new AsyncTask()
    {
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        try
        {
          if (m.b(this.a, "autoReconnectCount", 0) == 0)
            Thread.sleep(5000L);
          else
            Thread.sleep(10000L);
        }
        catch (InterruptedException localInterruptedException)
        {
          j.a("TrineaAndroidCommon", localInterruptedException, "toAutoReconnect InterruptedException");
        }
        boolean bool = c.a("toAutoReconnect", this.a);
        int i = 1;
        if (bool)
        {
          j.b("TrineaAndroidCommon", "toAutoReconnect isOnline : true");
          if (com.cndatacom.xjhui.b.a.b.c(this.a))
          {
            j.b("TrineaAndroidCommon", "toAutoReconnect isHaveNet : true");
            m.a(this.a, "SID", "1");
          }
          else
          {
            j.b("TrineaAndroidCommon", "toAutoReconnect isHaveNet : false");
          }
        }
        else
        {
          j.b("TrineaAndroidCommon", "toAutoReconnect isOnline : false");
          String str1 = m.b(this.a, "ticket", "");
          String str2 = m.b(this.a, "UID", "");
          if (!"".equals(str1))
          {
            int j = h.a(this.a, i, str2, str1);
            if ((j == 200) || (j == 13))
              j = h.a(this.a, i, str2, str1);
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("toAutoReconnect term result : ");
            localStringBuilder.append(j);
            j.b("TrineaAndroidCommon", localStringBuilder.toString());
          }
          com.cndatacom.xjhui.b.a.a.a(this.a);
          com.cndatacom.xjhui.b.a.a.c(this.a);
          String str3 = m.b(this.a, "UID", "");
          String str4 = m.b(this.a, "zdlj_password", "");
          i = a.a(this.a, str3, str4);
        }
        return Integer.valueOf(i);
      }

      protected void onCancelled()
      {
        super.onCancelled();
        a.b(null);
      }

      protected void onPostExecute(Object paramAnonymousObject)
      {
        super.onPostExecute(paramAnonymousObject);
        int i = Integer.parseInt(paramAnonymousObject.toString());
        a.b(null);
        if (i == 0)
        {
          j.b("TrineaAndroidCommon", "toAutoReconnect auth successfully");
          m.a(this.a, "autoReconnectCount", 0);
          this.a.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "AUTO_RECONNECT_SUCCESSFULLY"));
        }
        else if (i == 1)
        {
          j.b("TrineaAndroidCommon", "toAutoReconnect WiFi successfully");
          m.a(this.a, "autoReconnectCount", 0);
          com.cndatacom.b.a.b(this.a, "com.cndatacom.jscportal.ACTION_STATE", "STATE_CHANGED");
          MainUiActivity.b();
          MainUiActivity.a();
        }
        else
        {
          int j = 1 + m.b(this.a, "autoReconnectCount", 0);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("toAutoReconnect unsuccessfully count : ");
          localStringBuilder.append(j);
          j.b("TrineaAndroidCommon", localStringBuilder.toString());
          if (j < 3)
          {
            m.a(this.a, "autoReconnectCount", j);
            a.b(this.a);
          }
          else
          {
            m.a(this.a, "autoReconnectCount", 0);
            this.a.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "AUTO_RECONNECT_UNSUCCESSFULLY"));
          }
        }
      }

      protected void onPreExecute()
      {
        super.onPreExecute();
      }
    };
    b.executeOnExecutor(MyApplication.a().b(), new Object[0]);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.b.a
 * JD-Core Version:    0.6.1
 */