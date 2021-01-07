package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

@Deprecated
public abstract interface LayoutInflaterFactory
{
  public abstract View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.LayoutInflaterFactory
 * JD-Core Version:    0.6.1
 */