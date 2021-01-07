package android.support.v4.widget;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

public class ListViewAutoScrollHelper extends AutoScrollHelper
{
  private final ListView mTarget;

  public ListViewAutoScrollHelper(@NonNull ListView paramListView)
  {
    super(paramListView);
    this.mTarget = paramListView;
  }

  public boolean canTargetScrollHorizontally(int paramInt)
  {
    return false;
  }

  public boolean canTargetScrollVertically(int paramInt)
  {
    ListView localListView = this.mTarget;
    int i = localListView.getCount();
    if (i == 0)
      return false;
    int j = localListView.getChildCount();
    int k = localListView.getFirstVisiblePosition();
    int m = k + j;
    if (paramInt > 0)
    {
      if ((m >= i) && (localListView.getChildAt(j - 1).getBottom() <= localListView.getHeight()))
        return false;
    }
    else
    {
      if (paramInt >= 0)
        break label89;
      if ((k <= 0) && (localListView.getChildAt(0).getTop() >= 0))
        return false;
    }
    return true;
    label89: return false;
  }

  public void scrollTargetBy(int paramInt1, int paramInt2)
  {
    ListViewCompat.scrollListBy(this.mTarget, paramInt2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.ListViewAutoScrollHelper
 * JD-Core Version:    0.6.1
 */