package android.arch.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class m extends Fragment
{
  private a a;

  public static void a(Activity paramActivity)
  {
    FragmentManager localFragmentManager = paramActivity.getFragmentManager();
    if (localFragmentManager.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null)
    {
      localFragmentManager.beginTransaction().add(new m(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
      localFragmentManager.executePendingTransactions();
    }
  }

  private void a(c.a parama)
  {
    Activity localActivity = getActivity();
    if ((localActivity instanceof g))
    {
      ((g)localActivity).a().a(parama);
      return;
    }
    if ((localActivity instanceof e))
    {
      c localc = ((e)localActivity).getLifecycle();
      if ((localc instanceof f))
        ((f)localc).a(parama);
    }
  }

  private void a(a parama)
  {
    if (parama != null)
      parama.a();
  }

  private void b(a parama)
  {
    if (parama != null)
      parama.b();
  }

  private void c(a parama)
  {
    if (parama != null)
      parama.c();
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    a(this.a);
    a(c.a.ON_CREATE);
  }

  public void onDestroy()
  {
    super.onDestroy();
    a(c.a.ON_DESTROY);
    this.a = null;
  }

  public void onPause()
  {
    super.onPause();
    a(c.a.ON_PAUSE);
  }

  public void onResume()
  {
    super.onResume();
    c(this.a);
    a(c.a.ON_RESUME);
  }

  public void onStart()
  {
    super.onStart();
    b(this.a);
    a(c.a.ON_START);
  }

  public void onStop()
  {
    super.onStop();
    a(c.a.ON_STOP);
  }

  static abstract interface a
  {
    public abstract void a();

    public abstract void b();

    public abstract void c();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.arch.lifecycle.m
 * JD-Core Version:    0.6.1
 */