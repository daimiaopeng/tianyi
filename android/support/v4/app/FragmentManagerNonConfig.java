package android.support.v4.app;

import android.arch.lifecycle.p;
import java.util.List;

public class FragmentManagerNonConfig
{
  private final List<FragmentManagerNonConfig> mChildNonConfigs;
  private final List<Fragment> mFragments;
  private final List<p> mViewModelStores;

  FragmentManagerNonConfig(List<Fragment> paramList, List<FragmentManagerNonConfig> paramList1, List<p> paramList2)
  {
    this.mFragments = paramList;
    this.mChildNonConfigs = paramList1;
    this.mViewModelStores = paramList2;
  }

  List<FragmentManagerNonConfig> getChildNonConfigs()
  {
    return this.mChildNonConfigs;
  }

  List<Fragment> getFragments()
  {
    return this.mFragments;
  }

  List<p> getViewModelStores()
  {
    return this.mViewModelStores;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.FragmentManagerNonConfig
 * JD-Core Version:    0.6.1
 */