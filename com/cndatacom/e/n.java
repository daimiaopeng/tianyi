package com.cndatacom.e;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import com.cndatacom.b.a;
import com.cndatacom.xjhui.b.g;
import java.io.File;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;

public class n
{
  static ProgressDialog a;

  protected static void a()
  {
    if ((a != null) && (a.isShowing()))
    {
      a.setProgress(0);
      a.dismiss();
    }
  }

  protected static void a(int paramInt, Context paramContext)
  {
    if (a == null)
    {
      a = new ProgressDialog(paramContext);
      a.setCanceledOnTouchOutside(false);
      a.setMax(100);
      a.setProgressStyle(1);
      a.setTitle("正在下载");
    }
    if ((a != null) && (!a.isShowing()))
      a.show();
    a.setProgress(paramInt);
  }

  protected static void a(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    paramContext.startActivity(localIntent);
  }

  protected static void a(String paramString, Context paramContext)
  {
    Toast localToast = Toast.makeText(paramContext, paramString, 0);
    localToast.setGravity(17, 0, 0);
    localToast.show();
  }

  public static void a(String paramString, final Context paramContext, boolean paramBoolean)
  {
    String str1 = paramString.replaceAll("<Mandatory>0</Mandatory>", "").replaceAll("<Mandatory>1</Mandatory>", "");
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    Object localObject1;
    Object localObject2;
    final Object localObject3;
    try
    {
      StringReader localStringReader = new StringReader(str1);
      localXmlPullParser.setInput(localStringReader);
      int i = localXmlPullParser.getEventType();
      localObject1 = null;
      localObject2 = null;
      localObject3 = null;
      while (true)
      {
        if (i != 1)
          if (i != 0)
            switch (i)
            {
            default:
              break;
            case 3:
            case 2:
            }
        try
        {
          localXmlPullParser.getName().equalsIgnoreCase("pkg");
          break label271;
          Object localObject5;
          Object localObject6;
          Object localObject7;
          if (localXmlPullParser.getName().equalsIgnoreCase("pkg"))
          {
            localXmlPullParser.getAttributeValue(null, "verno");
            localObject5 = localXmlPullParser.getAttributeValue(null, "version");
            try
            {
              localXmlPullParser.getAttributeValue(null, "size");
              localObject6 = localXmlPullParser.getAttributeValue(null, "url");
              try
              {
                localObject7 = localXmlPullParser.getAttributeValue(null, "siteurl");
              }
              catch (Exception localException4)
              {
                break label256;
              }
            }
            catch (Exception localException5)
            {
            }
          }
          else
          {
            localObject5 = localObject1;
            localObject6 = localObject2;
            localObject7 = localObject3;
          }
          try
          {
            if (localXmlPullParser.getName().equalsIgnoreCase("mandatory"))
              localXmlPullParser.getText().trim();
            localObject3 = localObject7;
            localObject2 = localObject6;
            localObject1 = localObject5;
          }
          catch (Exception localException6)
          {
            localObject3 = localObject7;
          }
          label256: localObject2 = localObject6;
          localObject1 = localObject5;
          Exception localException1 = localException6;
          break label307;
          label271: i = localXmlPullParser.next();
          continue;
          localStringReader.close();
        }
        catch (Exception localException2)
        {
        }
      }
    }
    catch (Exception localException3)
    {
      localObject1 = null;
      localObject2 = null;
      localObject3 = null;
    }
    label307: j.a("TrineaAndroidCommon", localException3, "UpdateUtils parseXML Exception");
    if ((localObject1 != null) && (!localObject1.equals("")))
    {
      Object localObject4;
      if (localObject1.endsWith(".apk"))
      {
        localObject4 = localObject1;
      }
      else
      {
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append(localObject1);
        localStringBuilder1.append(".apk");
        localObject4 = localStringBuilder1.toString();
      }
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("UpdateUtils  apk_ : ");
      localStringBuilder2.append((String)localObject4);
      j.b("TrineaAndroidCommon", localStringBuilder2.toString());
      String str2 = String.format("检测到有新版本%s，是否升级？", new Object[] { localObject1 });
      AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext).create();
      localAlertDialog.setCancelable(false);
      View localView = LayoutInflater.from(paramContext).inflate(2131165196, null);
      localView.getBackground().setAlpha(100);
      ((TextView)localView.findViewById(2131034235)).setText(str2);
      localView.findViewById(2131034130).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            if ((this.a != null) && (!this.a.equals("")))
            {
              if (n.a(paramContext))
              {
                String str1 = Environment.getExternalStorageDirectory().getAbsolutePath();
                StringBuilder localStringBuilder1 = new StringBuilder();
                localStringBuilder1.append(str1);
                localStringBuilder1.append("/");
                localStringBuilder1.append(this.c);
                String str2 = localStringBuilder1.toString();
                StringBuilder localStringBuilder2 = new StringBuilder();
                localStringBuilder2.append("UpdateUtils  downAddress : ");
                localStringBuilder2.append(str2);
                j.b("TrineaAndroidCommon", localStringBuilder2.toString());
                n.a(this.a, str2, paramContext);
              }
              else
              {
                n.a(paramContext, this.a);
              }
            }
            else
              n.a(paramContext, localObject3);
          }
          catch (Exception localException)
          {
            j.a("TrineaAndroidCommon", localException, "UpdateUtils parseXML dialog Exception");
          }
        }
      });
      final boolean bool = paramString.contains("<Mandatory>0</Mandatory>");
      localView.findViewById(2131034129).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          this.a.cancel();
          if (bool)
            n.b(paramContext);
        }
      });
      localAlertDialog.show();
      localAlertDialog.getWindow().setContentView(localView);
      localAlertDialog.getWindow().setLayout(-1, -1);
      return;
    }
    if (paramBoolean)
      Toast.makeText(paramContext, "当前已是最新版本", 0).show();
  }

  protected static boolean a(Context paramContext)
  {
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      j.b("TrineaAndroidCommon", "sdcard 可用");
      return true;
    }
    j.b("TrineaAndroidCommon", "sdcard 不可用");
    return false;
  }

  private static void b(String paramString1, String paramString2, Context paramContext)
  {
    h.a(new h.a()
    {
      public void a(int paramAnonymousInt)
      {
        n.a(paramAnonymousInt, this.a);
      }

      // ERROR //
      public void a(String paramAnonymousString)
      {
        // Byte code:
        //   0: invokestatic 29	com/cndatacom/e/n:a	()V
        //   3: new 31	android/content/Intent
        //   6: dup
        //   7: ldc 33
        //   9: invokespecial 35	android/content/Intent:<init>	(Ljava/lang/String;)V
        //   12: astore_2
        //   13: aload_2
        //   14: new 37	java/io/File
        //   17: dup
        //   18: aload_1
        //   19: invokespecial 38	java/io/File:<init>	(Ljava/lang/String;)V
        //   22: invokestatic 44	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
        //   25: ldc 46
        //   27: invokevirtual 50	android/content/Intent:setDataAndType	(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;
        //   30: pop
        //   31: aload_0
        //   32: getfield 17	com/cndatacom/e/n$3:a	Landroid/content/Context;
        //   35: aload_2
        //   36: invokevirtual 56	android/content/Context:startActivity	(Landroid/content/Intent;)V
        //   39: goto +12 -> 51
        //   42: ldc 58
        //   44: aload_0
        //   45: getfield 17	com/cndatacom/e/n$3:a	Landroid/content/Context;
        //   48: invokestatic 61	com/cndatacom/e/n:a	(Ljava/lang/String;Landroid/content/Context;)V
        //   51: return
        //
        // Exception table:
        //   from	to	target	type
        //   3	39	42	java/lang/Exception
      }

      public void b(String paramAnonymousString)
      {
        n.a(paramAnonymousString, this.a);
        n.a();
      }
    }
    , paramString1, paramContext, paramString2);
  }

  private static void c(Context paramContext)
  {
    try
    {
      a.a(paramContext, 1);
      if (paramContext.getSharedPreferences("TrineaAndroidCommon", 0).getString("SID", "0").equals("1"))
        g.a(paramContext, 2);
      else
        System.exit(0);
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "UpdateUtils appQuit Exception");
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.e.n
 * JD-Core Version:    0.6.1
 */