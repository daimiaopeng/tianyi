package com.cndatacom.xjhui.b;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.cndatacom.a.c.a;
import com.cndatacom.campus.jscportal.MyApplication;
import com.cndatacom.e.g;
import com.cndatacom.e.g.a;
import com.cndatacom.e.j;
import com.cndatacom.e.l;
import com.cndatacom.e.m;
import com.cndatacom.e.n;
import com.cndatacom.e.o;
import com.cndatacom.xjhui.AccountDataActivity;
import com.cndatacom.xjhui.HelpGuideActivity;
import com.cndatacom.xjhui.LoginActivity;
import com.cndatacom.xjhui.TerminalManagerActivity;
import com.cndatacom.xjhui.b.a.a;
import com.cndatacom.xjhui.b.a.d;
import com.karics.library.zxing.android.CaptureActivity;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressLint({"DefaultLocale", "NewApi"})
public class c
{
  private static AsyncTask<Object, Object, Object> a;
  private static com.cndatacom.a.c b;

  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat / paramContext.getResources().getDisplayMetrics().density);
  }

  public static void a(Context paramContext)
  {
    try
    {
      com.cndatacom.a.b.a(paramContext, m.b(paramContext, "schoolid", ""), m.b(paramContext, "UID", ""));
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "sendPhoneMarketingData Exception");
    }
  }

  public static void a(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent(paramContext, HelpGuideActivity.class);
    Bundle localBundle = new Bundle();
    localBundle.putInt("code", paramInt);
    localIntent.putExtras(localBundle);
    paramContext.startActivity(localIntent);
  }

  public static void a(Context paramContext, final o paramo)
  {
    if (System.currentTimeMillis() - m.b(paramContext, "uploadLastTime", 0L) < 300000L)
      o.a(paramContext, "温馨提示", "亲，您已经上传过日志，请不要重复上传，谢谢。");
    else
      o.a(paramContext, "温馨提示", "确定要上传日志吗？", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          c.b(this.a, paramo);
        }
      });
  }

  public static void a(final Context paramContext, String paramString)
  {
    new AsyncTask()
    {
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        if (!c.a(this.a, paramContext))
          a.c(paramContext);
        return Integer.valueOf(0);
      }

      protected void onPostExecute(Object paramAnonymousObject)
      {
        super.onPostExecute(paramAnonymousObject);
      }

      protected void onPreExecute()
      {
        super.onPreExecute();
      }
    }
    .executeOnExecutor(MyApplication.a().b(), new Object[0]);
  }

  public static void a(final Context paramContext, String paramString, final int paramInt)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext).create();
    View localView = LayoutInflater.from(paramContext).inflate(2131165194, null);
    ((TextView)localView.findViewById(2131034234)).setText(paramString);
    localView.findViewById(2131034125).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        this.a.dismiss();
      }
    });
    ((TextView)localView.findViewById(2131034126)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        this.a.dismiss();
        c.a(paramContext, paramInt);
      }
    });
    localAlertDialog.show();
    localAlertDialog.getWindow().setContentView(localView);
    localAlertDialog.getWindow().setLayout(-1, -1);
  }

  public static void a(Context paramContext, String paramString1, String paramString2, final boolean paramBoolean)
  {
    String str = m.a(paramContext, "UpdateCheckURL");
    if (TextUtils.isEmpty(str))
    {
      j.b("TrineaAndroidCommon", "checkUpdate == null");
      str = "http://218.77.121.111/EsurfingClient/Other/GetUpdatePkg.aspx";
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("checkUpdate account : ");
    localStringBuilder1.append(paramString1);
    localStringBuilder1.append(" schoolId : ");
    localStringBuilder1.append(paramString2);
    j.b("TrineaAndroidCommon", localStringBuilder1.toString());
    HashMap localHashMap = new HashMap();
    localHashMap.put("areaCode", "zs");
    localHashMap.put("osType", "androidphone");
    localHashMap.put("dialType", "3");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(d.d(paramContext));
    localStringBuilder2.append("");
    localHashMap.put("verno", localStringBuilder2.toString());
    localHashMap.put("CID", paramString2);
    localHashMap.put("account", paramString1);
    g.a(str, localHashMap, new g.a()
    {
      public void a(String paramAnonymousString)
      {
        n.a(paramAnonymousString, this.a, paramBoolean);
      }

      public void b(String paramAnonymousString)
      {
      }
    });
  }

  public static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("TrineaAndroidCommon", 0);
    String str1 = localSharedPreferences.getString("schoolid", "");
    String str2 = localSharedPreferences.getString(paramString1, "");
    String str3 = localSharedPreferences.getString(paramString2, "");
    String str4 = localSharedPreferences.getString(paramString3, "");
    if (str2.equals("1"))
    {
      if (!TextUtils.isEmpty(str4))
      {
        String[] arrayOfString2 = str4.split(",");
        int k = arrayOfString2.length;
        for (int m = 0; m < k; m++)
          if (str1.equals(arrayOfString2[m]))
            return false;
        return true;
      }
      if (!TextUtils.isEmpty(str3))
      {
        String[] arrayOfString1 = str3.split(",");
        int i = arrayOfString1.length;
        for (int j = 0; j < i; j++)
          if (str1.equals(arrayOfString1[j]))
            return true;
        return false;
      }
      return true;
    }
    return false;
  }

  public static boolean a(String paramString, Context paramContext)
  {
    com.cndatacom.xjhui.b.a.c.a(paramContext, paramString);
    com.cndatacom.xjhui.b.a.c.a(paramContext, paramString, false);
    return true ^ com.cndatacom.xjhui.b.a.c.b(paramContext, paramString);
  }

  public static void b(final Context paramContext)
  {
    final AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext).create();
    View localView = LayoutInflater.from(paramContext).inflate(2131165198, null);
    EditText localEditText1 = (EditText)localView.findViewById(2131034161);
    final EditText localEditText2 = (EditText)localView.findViewById(2131034162);
    localEditText1.setText(m.b(paramContext, "PCAccount", ""));
    localEditText2.setText(m.b(paramContext, "PCPassword", ""));
    localView.findViewById(2131034131).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        this.a.dismiss();
      }
    });
    localView.findViewById(2131034132).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        String str1 = this.a.getText().toString();
        if (TextUtils.isEmpty(str1))
        {
          Toast.makeText(paramContext, "账号不能为空", 0).show();
          return;
        }
        String str2 = localEditText2.getText().toString();
        if (TextUtils.isEmpty(str2))
        {
          Toast.makeText(paramContext, "密码不能为空", 0).show();
          return;
        }
        Intent localIntent = new Intent(paramContext, CaptureActivity.class);
        Bundle localBundle = new Bundle();
        localBundle.putString("account", str1);
        localBundle.putString("password", str2);
        localIntent.putExtras(localBundle);
        paramContext.startActivity(localIntent);
        localAlertDialog.dismiss();
      }
    });
    localAlertDialog.setCanceledOnTouchOutside(false);
    localAlertDialog.show();
    localAlertDialog.setContentView(localView);
    localAlertDialog.getWindow().clearFlags(131072);
  }

  @SuppressLint({"NewApi"})
  public static void b(final Context paramContext, String paramString)
  {
    if (a != null)
      return;
    a = new AsyncTask()
    {
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        boolean bool1 = c.a(this.a, paramContext);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("isOnline : ");
        localStringBuilder.append(bool1);
        j.b("TrineaAndroidCommon", localStringBuilder.toString());
        boolean bool2;
        if (bool1)
        {
          if (com.cndatacom.xjhui.b.a.b.c(paramContext))
            bool2 = com.cndatacom.xjhui.b.a.b.f(paramContext);
          else
            bool2 = false;
        }
        else
          bool2 = true;
        return Boolean.valueOf(bool2);
      }

      protected void onPostExecute(Object paramAnonymousObject)
      {
        boolean bool = Boolean.parseBoolean(paramAnonymousObject.toString());
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("isPortalNetwork : ");
        localStringBuilder.append(bool);
        j.b("TrineaAndroidCommon", localStringBuilder.toString());
        c.a(null);
        if (!bool)
          paramContext.sendBroadcast(new Intent("com.cndatacom.jscportal.ACTION_STATE").putExtra("DATA", "CHECK_CHANGED"));
      }

      protected void onPreExecute()
      {
        super.onPreExecute();
      }
    };
    a.executeOnExecutor(MyApplication.a().b(), new Object[0]);
  }

  public static void c(Context paramContext)
  {
    if (l.a(paramContext) == 3)
    {
      if (m.b(paramContext, "SID", "0").equals("0"))
        o.a(paramContext, "温馨提示", "请先登录，再使用此功能。", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            this.a.startActivity(new Intent(this.a, LoginActivity.class));
          }
        });
      else
        paramContext.startActivity(new Intent(paramContext, TerminalManagerActivity.class));
    }
    else
      o.a(paramContext, "温馨提示", "无线网络不可用，请先检查无线网络设置，再使用此功能。", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          this.a.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
        }
      });
  }

  private static void c(final Context paramContext, o paramo)
  {
    if (b != null)
    {
      paramo.a();
      return;
    }
    j.b("TrineaAndroidCommon", "uploadErrorLog do");
    paramo.a();
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("ErrType", "android");
    localHashMap2.put("ErrCode", "10086");
    try
    {
      localHashMap2.put("Account", URLEncoder.encode(m.b(paramContext, "UID", ""), "UTF-8"));
      localHashMap2.put("IPAddr", URLEncoder.encode(d.f(paramContext), "UTF-8"));
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      j.a("TrineaAndroidCommon", localUnsupportedEncodingException, "uploadErrorLog UnsupportedEncodingException");
    }
    localHashMap2.put("CID", m.b(paramContext, "schoolid", "361"));
    localHashMap2.put("ClientID", Build.SERIAL);
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(d.d(paramContext));
    localStringBuilder1.append("");
    localHashMap2.put("Version", localStringBuilder1.toString());
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = localHashMap2.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str7 = (String)localIterator.next();
      String str8 = (String)localHashMap2.get(str7);
      localStringBuffer.append("&");
      localStringBuffer.append(str7);
      localStringBuffer.append("=");
      localStringBuffer.append(str8);
    }
    final String str1 = "not_exists";
    String str2 = "zip";
    String str3 = localStringBuffer.toString().replaceFirst("&", "");
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append(m.b(paramContext, "ManualSubErrorDataURL", ""));
    localStringBuilder2.append("?");
    localStringBuilder2.append(str3);
    String str4 = localStringBuilder2.toString();
    if ("mounted".equals(Environment.getExternalStorageState()))
    {
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append(Environment.getExternalStorageDirectory().getPath());
      localStringBuilder3.append(File.separator);
      localStringBuilder3.append("TrineaAndroidCommon");
      localStringBuilder3.append(File.separator);
      str1 = localStringBuilder3.toString();
      StringBuilder localStringBuilder4 = new StringBuilder();
      localStringBuilder4.append(Environment.getExternalStorageDirectory().getPath());
      localStringBuilder4.append(File.separator);
      str2 = localStringBuilder4.toString();
    }
    String str5 = str2;
    StringBuilder localStringBuilder5 = new StringBuilder();
    localStringBuilder5.append(str5);
    localStringBuilder5.append("TrineaAndroidCommon");
    localStringBuilder5.append(".zip");
    final String str6 = localStringBuilder5.toString();
    com.cndatacom.a.c localc = new com.cndatacom.a.c(paramContext, new c.a()
    {
      public void a()
      {
      }

      public void a(String paramAnonymousString)
      {
        this.a.b();
        j.b("TrineaAndroidCommon", "uploadErrorLog onSuccess");
        Context localContext = paramContext;
        int i = 0;
        Toast.makeText(localContext, "上传成功", 0).show();
        c.a(null);
        m.a(paramContext, "uploadLastTime", System.currentTimeMillis());
        File localFile1 = new File(str1);
        if (localFile1.exists())
        {
          File[] arrayOfFile = localFile1.listFiles();
          while (i < arrayOfFile.length)
          {
            if (arrayOfFile[i].exists())
              arrayOfFile[i].delete();
            i++;
          }
        }
        File localFile2 = new File(str6);
        if (localFile2.exists())
          localFile2.delete();
      }

      public void b(String paramAnonymousString)
      {
        this.a.b();
        j.b("TrineaAndroidCommon", "uploadErrorLog onError");
        c.a(null);
        o.a(paramContext, "温馨提示", "上传失败，请稍后重试。");
      }
    }
    , localHashMap1, localHashMap2, true);
    b = localc;
    String[] arrayOfString = { str4, str1, str5 };
    b.execute(arrayOfString);
  }

  public static void d(Context paramContext)
  {
    if (l.a(paramContext) == 3)
    {
      if (m.b(paramContext, "SID", "0").equals("0"))
        o.a(paramContext, "温馨提示", "请先登录，再使用此功能。", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            this.a.startActivity(new Intent(this.a, LoginActivity.class));
          }
        });
      else if (TextUtils.isEmpty(m.b(paramContext, "PackageQueryURL", "")))
        o.a(paramContext, "温馨提示", "暂时不支持此功能", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
          }
        });
      else
        paramContext.startActivity(new Intent(paramContext, AccountDataActivity.class));
    }
    else
      o.a(paramContext, "温馨提示", "无线网络不可用，请先检查无线网络设置，再使用此功能。", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          this.a.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
        }
      });
  }

  public static void e(Context paramContext)
  {
    new AsyncTask()
    {
      // ERROR //
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 15	com/cndatacom/xjhui/b/c$8:a	Landroid/content/Context;
        //   4: ldc 24
        //   6: invokestatic 29	com/cndatacom/e/m:a	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
        //   9: astore_2
        //   10: aload_2
        //   11: invokestatic 35	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
        //   14: ifeq +17 -> 31
        //   17: ldc 37
        //   19: ldc 39
        //   21: invokestatic 45	com/cndatacom/e/j:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   24: sipush 300
        //   27: invokestatic 51	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   30: areturn
        //   31: aload_0
        //   32: getfield 15	com/cndatacom/xjhui/b/c$8:a	Landroid/content/Context;
        //   35: ldc 53
        //   37: iconst_0
        //   38: invokestatic 56	com/cndatacom/e/m:b	(Landroid/content/Context;Ljava/lang/String;I)I
        //   41: istore_3
        //   42: aload_0
        //   43: getfield 15	com/cndatacom/xjhui/b/c$8:a	Landroid/content/Context;
        //   46: ldc 58
        //   48: iconst_0
        //   49: invokestatic 56	com/cndatacom/e/m:b	(Landroid/content/Context;Ljava/lang/String;I)I
        //   52: istore 4
        //   54: iload_3
        //   55: ifeq +487 -> 542
        //   58: iload 4
        //   60: ifne +6 -> 66
        //   63: goto +479 -> 542
        //   66: new 60	java/lang/StringBuilder
        //   69: dup
        //   70: invokespecial 61	java/lang/StringBuilder:<init>	()V
        //   73: astore 5
        //   75: aload 5
        //   77: ldc 63
        //   79: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   82: pop
        //   83: aload 5
        //   85: aload_0
        //   86: getfield 15	com/cndatacom/xjhui/b/c$8:a	Landroid/content/Context;
        //   89: invokestatic 73	com/cndatacom/xjhui/b/a/d:d	(Landroid/content/Context;)I
        //   92: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   95: pop
        //   96: aload 5
        //   98: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   101: astore 8
        //   103: new 60	java/lang/StringBuilder
        //   106: dup
        //   107: invokespecial 61	java/lang/StringBuilder:<init>	()V
        //   110: astore 9
        //   112: aload 9
        //   114: ldc 82
        //   116: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   119: pop
        //   120: aload 9
        //   122: aload_0
        //   123: getfield 15	com/cndatacom/xjhui/b/c$8:a	Landroid/content/Context;
        //   126: ldc 84
        //   128: ldc 86
        //   130: invokestatic 89	com/cndatacom/e/m:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   133: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   136: pop
        //   137: aload 9
        //   139: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   142: astore 12
        //   144: new 60	java/lang/StringBuilder
        //   147: dup
        //   148: invokespecial 61	java/lang/StringBuilder:<init>	()V
        //   151: astore 13
        //   153: aload 13
        //   155: ldc 91
        //   157: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: pop
        //   161: aload 13
        //   163: aload_0
        //   164: getfield 15	com/cndatacom/xjhui/b/c$8:a	Landroid/content/Context;
        //   167: ldc 93
        //   169: ldc 86
        //   171: invokestatic 89	com/cndatacom/e/m:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   174: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   177: pop
        //   178: aload 13
        //   180: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   183: astore 16
        //   185: new 60	java/lang/StringBuilder
        //   188: dup
        //   189: invokespecial 61	java/lang/StringBuilder:<init>	()V
        //   192: astore 17
        //   194: aload 17
        //   196: ldc 95
        //   198: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: pop
        //   202: aload 17
        //   204: iload 4
        //   206: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   209: pop
        //   210: aload 17
        //   212: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   215: astore 20
        //   217: new 60	java/lang/StringBuilder
        //   220: dup
        //   221: invokespecial 61	java/lang/StringBuilder:<init>	()V
        //   224: astore 21
        //   226: aload 21
        //   228: ldc 97
        //   230: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   233: pop
        //   234: aload 21
        //   236: iload_3
        //   237: invokevirtual 76	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   240: pop
        //   241: aload 21
        //   243: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   246: astore 24
        //   248: new 60	java/lang/StringBuilder
        //   251: dup
        //   252: invokespecial 61	java/lang/StringBuilder:<init>	()V
        //   255: astore 25
        //   257: aload 25
        //   259: aload_2
        //   260: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   263: pop
        //   264: aload 25
        //   266: ldc 99
        //   268: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   271: pop
        //   272: aload 25
        //   274: ldc 101
        //   276: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   279: pop
        //   280: aload 25
        //   282: ldc 103
        //   284: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   287: pop
        //   288: aload 25
        //   290: aload 8
        //   292: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   295: pop
        //   296: aload 25
        //   298: aload 12
        //   300: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   303: pop
        //   304: aload 25
        //   306: aload 16
        //   308: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   311: pop
        //   312: aload 25
        //   314: aload 20
        //   316: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   319: pop
        //   320: aload 25
        //   322: aload 24
        //   324: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   327: pop
        //   328: aload 25
        //   330: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   333: astore 35
        //   335: new 60	java/lang/StringBuilder
        //   338: dup
        //   339: invokespecial 61	java/lang/StringBuilder:<init>	()V
        //   342: astore 36
        //   344: aload 36
        //   346: ldc 105
        //   348: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   351: pop
        //   352: aload 36
        //   354: aload 35
        //   356: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   359: pop
        //   360: ldc 37
        //   362: aload 36
        //   364: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   367: invokestatic 45	com/cndatacom/e/j:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   370: aload 35
        //   372: aconst_null
        //   373: aconst_null
        //   374: invokestatic 110	com/cndatacom/a/b:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcom/cndatacom/c/a;
        //   377: astore 39
        //   379: aload 39
        //   381: invokevirtual 115	com/cndatacom/c/a:a	()I
        //   384: sipush 200
        //   387: if_icmpne +115 -> 502
        //   390: aload 39
        //   392: invokevirtual 117	com/cndatacom/c/a:b	()Ljava/lang/String;
        //   395: astore 42
        //   397: ldc 86
        //   399: astore 43
        //   401: new 60	java/lang/StringBuilder
        //   404: dup
        //   405: invokespecial 61	java/lang/StringBuilder:<init>	()V
        //   408: astore 44
        //   410: aload 44
        //   412: ldc 119
        //   414: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   417: pop
        //   418: aload 44
        //   420: aload 42
        //   422: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   425: pop
        //   426: ldc 37
        //   428: aload 44
        //   430: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   433: invokestatic 45	com/cndatacom/e/j:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   436: aload 42
        //   438: ldc 121
        //   440: invokevirtual 126	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
        //   443: ifeq +42 -> 485
        //   446: aload 42
        //   448: iconst_1
        //   449: aload 42
        //   451: ldc 128
        //   453: invokevirtual 132	java/lang/String:indexOf	(Ljava/lang/String;)I
        //   456: iadd
        //   457: aload 42
        //   459: ldc 134
        //   461: invokevirtual 132	java/lang/String:indexOf	(Ljava/lang/String;)I
        //   464: invokevirtual 138	java/lang/String:substring	(II)Ljava/lang/String;
        //   467: astore 48
        //   469: aload 48
        //   471: iconst_1
        //   472: aload 48
        //   474: ldc 128
        //   476: invokevirtual 132	java/lang/String:indexOf	(Ljava/lang/String;)I
        //   479: iadd
        //   480: invokevirtual 141	java/lang/String:substring	(I)Ljava/lang/String;
        //   483: astore 43
        //   485: aload_0
        //   486: getfield 15	com/cndatacom/xjhui/b/c$8:a	Landroid/content/Context;
        //   489: ldc 143
        //   491: aload 43
        //   493: invokestatic 146	com/cndatacom/e/m:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
        //   496: pop
        //   497: iconst_0
        //   498: invokestatic 51	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   501: areturn
        //   502: aload 39
        //   504: invokevirtual 115	com/cndatacom/c/a:a	()I
        //   507: istore 40
        //   509: sipush 303
        //   512: istore 41
        //   514: iload 40
        //   516: iload 41
        //   518: if_icmpne +6 -> 524
        //   521: goto +8 -> 529
        //   524: sipush 300
        //   527: istore 41
        //   529: iload 41
        //   531: invokestatic 51	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   534: areturn
        //   535: sipush 300
        //   538: invokestatic 51	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   541: areturn
        //   542: ldc 37
        //   544: ldc 148
        //   546: invokestatic 45	com/cndatacom/e/j:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   549: sipush 300
        //   552: invokestatic 51	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   555: areturn
        //
        // Exception table:
        //   from	to	target	type
        //   66	509	535	java/lang/Exception
      }

      protected void onPostExecute(Object paramAnonymousObject)
      {
        super.onPostExecute(paramAnonymousObject);
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("getAdvert resultCode : ");
        localStringBuilder1.append(paramAnonymousObject.toString());
        j.b("TrineaAndroidCommon", localStringBuilder1.toString());
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append("getAdvert advert_url : ");
        localStringBuilder2.append(m.b(this.a, "advertURL", ""));
        j.b("TrineaAndroidCommon", localStringBuilder2.toString());
      }

      protected void onPreExecute()
      {
        super.onPreExecute();
      }
    }
    .executeOnExecutor(MyApplication.a().b(), new Object[0]);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.b.c
 * JD-Core Version:    0.6.1
 */