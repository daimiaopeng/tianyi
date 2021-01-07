package com.cndatacom.xjhui.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.cndatacom.campus.jscportal.MyApplication;
import com.cndatacom.e.j;
import com.cndatacom.e.m;
import com.cndatacom.e.o;
import com.cndatacom.xjhui.b.h;
import com.cndatacom.xjhui.model.Device;
import java.util.ArrayList;
import java.util.List;

public class a extends BaseAdapter
{
  private List<Device> a = new ArrayList();
  private Context b;
  private Handler c;
  private o d;
  private AsyncTask<Object, Object, Object> e = null;

  public a(Context paramContext, List<Device> paramList, Handler paramHandler, o paramo)
  {
    this.b = paramContext;
    this.a = paramList;
    this.c = paramHandler;
    this.d = paramo;
  }

  @SuppressLint({"NewApi"})
  private void a(final String paramString, final int paramInt)
  {
    this.e = new AsyncTask()
    {
      protected Object doInBackground(Object[] paramAnonymousArrayOfObject)
      {
        j.b("TrineaAndroidCommon", "TerminalManagerAdapter logoutAction");
        String str = m.b(a.d(a.this), "UID", "");
        int i = h.a(a.d(a.this), 8, str, paramString);
        if ((i == 200) || (i == 13))
          i = h.a(a.d(a.this), 8, str, paramString);
        return Integer.valueOf(i);
      }

      protected void onCancelled()
      {
        a.a(a.this, null);
        a.c(a.this).b();
      }

      protected void onPostExecute(Object paramAnonymousObject)
      {
        super.onPostExecute(paramAnonymousObject);
        int i = Integer.parseInt(paramAnonymousObject.toString());
        a.c(a.this).b();
        if (i == 0)
        {
          Message localMessage1 = new Message();
          localMessage1.what = 0;
          localMessage1.obj = Integer.valueOf(paramInt);
          a.e(a.this).sendMessage(localMessage1);
        }
        else
        {
          Message localMessage2 = new Message();
          localMessage2.what = 404;
          localMessage2.obj = Integer.valueOf(i);
          a.e(a.this).sendMessage(localMessage2);
        }
        a.a(a.this, null);
      }

      protected void onPreExecute()
      {
        super.onPreExecute();
        a.c(a.this).a();
      }
    };
    this.e.executeOnExecutor(MyApplication.a().b(), new Object[0]);
  }

  public int getCount()
  {
    return this.a.size();
  }

  public Object getItem(int paramInt)
  {
    return null;
  }

  public long getItemId(int paramInt)
  {
    return 0L;
  }

  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject;
    View localView;
    if (paramView == null)
    {
      localObject = new a();
      localView = LayoutInflater.from(this.b).inflate(2131165193, null);
      ((a)localObject).a = ((ImageView)localView.findViewById(2131034185));
      ((a)localObject).b = ((TextView)localView.findViewById(2131034245));
      ((a)localObject).c = ((TextView)localView.findViewById(2131034246));
      ((a)localObject).d = ((TextView)localView.findViewById(2131034244));
      ((a)localObject).e = ((Button)localView.findViewById(2131034141));
      localView.setTag(localObject);
    }
    else
    {
      a locala = (a)paramView.getTag();
      localView = paramView;
      localObject = locala;
    }
    int i;
    if (((Device)this.a.get(paramInt)).getType() == 1)
      i = 2130968627;
    else
      i = 2130968628;
    ((a)localObject).a.setImageResource(i);
    ((a)localObject).b.setText(((Device)this.a.get(paramInt)).getHostName());
    ((a)localObject).c.setText(((Device)this.a.get(paramInt)).getLoginTime());
    if (((Device)this.a.get(paramInt)).getLocaltype() == 1)
    {
      ((a)localObject).d.setVisibility(0);
      ((a)localObject).e.setVisibility(8);
    }
    else
    {
      ((a)localObject).d.setVisibility(8);
      ((a)localObject).e.setVisibility(0);
      ((a)localObject).e.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (a.a(a.this) == null)
            a.a(a.this, ((Device)a.b(a.this).get(paramInt)).getTicket(), paramInt);
          else
            a.c(a.this).a();
        }
      });
    }
    return localView;
  }

  class a
  {
    ImageView a;
    TextView b;
    TextView c;
    TextView d;
    Button e;

    a()
    {
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.a.a
 * JD-Core Version:    0.6.1
 */