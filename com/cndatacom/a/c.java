package com.cndatacom.a;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import com.cndatacom.e.e;
import com.cndatacom.e.j;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.http.conn.ConnectTimeoutException;

public class c extends AsyncTask<String, Integer, String>
{
  boolean a = true;
  private Context b;
  private String c;
  private a d;
  private String e = null;
  private Map<String, String> f;
  private Map<String, String> g;
  private boolean h = false;
  private final int i = 60000;
  private final int j = 2131165184;
  private final int k = 2131165186;
  private final int l = 2131165187;
  private final int m = 2131165188;
  private Handler n = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      case 2131165185:
      default:
        break;
      case 2131165188:
        c.b(c.this).b("服务响应错误！请稍后重试.");
        break;
      case 2131165187:
        c.b(c.this).b("网络连接超时！请稍后重试.");
        break;
      case 2131165186:
        c.b(c.this).b("服务无响应！请稍后重试.");
        break;
      case 2131165184:
        c.b(c.this).a(c.a(c.this));
      }
    }
  };

  public c(Context paramContext, a parama, Map<String, String> paramMap1, Map<String, String> paramMap2, boolean paramBoolean)
  {
    this.b = paramContext;
    this.d = parama;
    this.f = paramMap1;
    this.g = paramMap2;
    this.h = paramBoolean;
  }

  protected String a(String[] paramArrayOfString)
  {
    String str1 = paramArrayOfString[0];
    this.c = paramArrayOfString[1];
    String str2 = this.c;
    String str3 = paramArrayOfString[2];
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str3);
    localStringBuilder.append("TrineaAndroidCommon");
    localStringBuilder.append(".zip");
    String str4 = localStringBuilder.toString();
    try
    {
      e.a(str2, str3, "TrineaAndroidCommon");
      File localFile = new File(str4);
      PostMethod localPostMethod = new PostMethod(str1);
      Part[] arrayOfPart = new Part[1];
      arrayOfPart[0] = new FilePart(localFile.getName(), localFile);
      localPostMethod.setRequestEntity(new MultipartRequestEntity(arrayOfPart, localPostMethod.getParams()));
      HttpClient localHttpClient = new HttpClient();
      localHttpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
      int i1 = localHttpClient.executeMethod(localPostMethod);
      int i2 = 0;
      if (i1 == 200)
        i2 = 1;
      if (i2 != 0)
        return "success";
      return null;
    }
    catch (Exception localException)
    {
      j.a("TrineaAndroidCommon", localException, "HttpAsyncTaskUpload2 doInBackground Exception");
      this.n.sendEmptyMessage(2131165188);
      return null;
    }
    catch (IOException localIOException)
    {
      j.a("TrineaAndroidCommon", localIOException, "HttpAsyncTaskUpload2 doInBackground IOException");
      this.n.sendEmptyMessage(2131165188);
      return null;
    }
    catch (ConnectTimeoutException localConnectTimeoutException)
    {
      j.a("TrineaAndroidCommon", localConnectTimeoutException, "HttpAsyncTaskUpload2 doInBackground ConnectTimeoutException");
      this.n.sendEmptyMessage(2131165187);
    }
    return null;
  }

  protected void a(String paramString)
  {
    super.onPostExecute(this.e);
    if (paramString != null)
    {
      this.e = paramString;
      this.n.sendEmptyMessage(2131165184);
    }
    else
    {
      this.e = paramString;
      this.n.sendEmptyMessage(2131165186);
    }
  }

  protected void a(Integer[] paramArrayOfInteger)
  {
  }

  protected void onCancelled()
  {
  }

  protected void onPreExecute()
  {
    this.d.a();
  }

  public static abstract interface a
  {
    public abstract void a();

    public abstract void a(String paramString);

    public abstract void b(String paramString);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.a.c
 * JD-Core Version:    0.6.1
 */