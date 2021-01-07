package com.cndatacom.xjhui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cndatacom.campus.jscportal.MyApplication;
import com.cndatacom.e.m;
import com.cndatacom.e.o;
import com.cndatacom.xjhui.b.b;
import com.cndatacom.xjhui.b.f;
import com.cndatacom.xjhui.model.Device;
import com.google.a.e;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.List;

public class TerminalManagerActivity extends Activity
  implements View.OnClickListener
{
  private ImageView a;
  private ImageView b;
  private LinearLayout c;
  private ListView d;
  private RelativeLayout e;
  private TextView f;
  private o g;
  private com.cndatacom.xjhui.a.a h;
  private List<Device> i = new ArrayList();
  private AsyncTask<Object, Object, Object> j = null;
  private Context k;
  private Handler l = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      int i = paramAnonymousMessage.what;
      if (i != 0)
      {
        if (i == 404)
        {
          Context localContext2 = TerminalManagerActivity.a(TerminalManagerActivity.this);
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("下线失败，原因是：");
          localStringBuilder2.append(b.a(((Integer)paramAnonymousMessage.obj).intValue()));
          o.a(localContext2, "温馨提示", localStringBuilder2.toString());
        }
      }
      else
      {
        int j = ((Integer)paramAnonymousMessage.obj).intValue();
        Context localContext1 = TerminalManagerActivity.a(TerminalManagerActivity.this);
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("设备");
        localStringBuilder1.append(((Device)TerminalManagerActivity.b(TerminalManagerActivity.this).get(j)).getHostName());
        localStringBuilder1.append("下线成功");
        Toast.makeText(localContext1, localStringBuilder1.toString(), 0).show();
        TerminalManagerActivity.b(TerminalManagerActivity.this).remove(j);
        TerminalManagerActivity.c(TerminalManagerActivity.this).notifyDataSetChanged();
      }
      super.handleMessage(paramAnonymousMessage);
    }
  };

  static
  {
    StubApp.interface11(1265);
  }

  @SuppressLint({"NewApi"})
  private void a()
  {
    this.j = new AsyncTask()
    {
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        String str = m.b(TerminalManagerActivity.a(TerminalManagerActivity.this), "UID", "");
        int i = f.a(TerminalManagerActivity.a(TerminalManagerActivity.this), str);
        if ((i == 13) || (i == 200))
          i = f.a(TerminalManagerActivity.a(TerminalManagerActivity.this), str);
        return Integer.valueOf(i);
      }

      protected void onCancelled()
      {
        TerminalManagerActivity.a(TerminalManagerActivity.this, null);
        TerminalManagerActivity.d(TerminalManagerActivity.this).b();
      }

      protected void onPostExecute(Object paramAnonymousObject)
      {
        super.onPostExecute(paramAnonymousObject);
        int i = Integer.parseInt(paramAnonymousObject.toString());
        TerminalManagerActivity.d(TerminalManagerActivity.this).b();
        if (i == 0)
        {
          TerminalManagerActivity.e(TerminalManagerActivity.this).setVisibility(0);
          TerminalManagerActivity.f(TerminalManagerActivity.this).setVisibility(8);
          String str2 = m.a(TerminalManagerActivity.a(TerminalManagerActivity.this), "terminalManagerList");
          e locale = new e();
          TerminalManagerActivity.a(TerminalManagerActivity.this, (List)locale.a(str2, new com.google.a.c.a()
          {
          }
          .b()));
          TerminalManagerActivity.a(TerminalManagerActivity.this, new com.cndatacom.xjhui.a.a(TerminalManagerActivity.a(TerminalManagerActivity.this), TerminalManagerActivity.b(TerminalManagerActivity.this), TerminalManagerActivity.g(TerminalManagerActivity.this), TerminalManagerActivity.d(TerminalManagerActivity.this)));
          TerminalManagerActivity.h(TerminalManagerActivity.this).setAdapter(TerminalManagerActivity.c(TerminalManagerActivity.this));
        }
        else
        {
          TerminalManagerActivity.e(TerminalManagerActivity.this).setVisibility(8);
          TerminalManagerActivity.f(TerminalManagerActivity.this).setVisibility(0);
          String str1 = b.a(i);
          if ("".equals(str1))
            TerminalManagerActivity.i(TerminalManagerActivity.this).setText("获取数据失败，请检查网络后重试");
          else
            TerminalManagerActivity.i(TerminalManagerActivity.this).setText(str1);
        }
        TerminalManagerActivity.a(TerminalManagerActivity.this, null);
      }

      protected void onPreExecute()
      {
        super.onPreExecute();
        TerminalManagerActivity.d(TerminalManagerActivity.this).a();
      }
    };
    this.j.executeOnExecutor(MyApplication.a().b(), new Object[0]);
  }

  public void onClick(View paramView)
  {
    int m = paramView.getId();
    if (m != 2131034184)
    {
      if (m == 2131034186)
        if (this.j == null)
          a();
        else
          this.g.a();
    }
    else
      finish();
  }

  protected native void onCreate(Bundle paramBundle);

  protected void onDestroy()
  {
    m.a(this.k, "terminalManagerList", "");
    if ((this.j != null) && (this.j.getStatus() != AsyncTask.Status.FINISHED))
      this.j.cancel(true);
    this.g.b();
    super.onDestroy();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.TerminalManagerActivity
 * JD-Core Version:    0.6.1
 */