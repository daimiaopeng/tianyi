package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ListFragment extends Fragment
{
  static final int INTERNAL_EMPTY_ID = 16711681;
  static final int INTERNAL_LIST_CONTAINER_ID = 16711683;
  static final int INTERNAL_PROGRESS_CONTAINER_ID = 16711682;
  ListAdapter mAdapter;
  CharSequence mEmptyText;
  View mEmptyView;
  private final Handler mHandler = new Handler();
  ListView mList;
  View mListContainer;
  boolean mListShown;
  private final AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      ListFragment.this.onListItemClick((ListView)paramAnonymousAdapterView, paramAnonymousView, paramAnonymousInt, paramAnonymousLong);
    }
  };
  View mProgressContainer;
  private final Runnable mRequestFocus = new Runnable()
  {
    public void run()
    {
      ListFragment.this.mList.focusableViewAvailable(ListFragment.this.mList);
    }
  };
  TextView mStandardEmptyView;

  private void ensureList()
  {
    if (this.mList != null)
      return;
    View localView1 = getView();
    if (localView1 == null)
      throw new IllegalStateException("Content view not yet created");
    if ((localView1 instanceof ListView))
    {
      this.mList = ((ListView)localView1);
    }
    else
    {
      this.mStandardEmptyView = ((TextView)localView1.findViewById(16711681));
      if (this.mStandardEmptyView == null)
        this.mEmptyView = localView1.findViewById(16908292);
      else
        this.mStandardEmptyView.setVisibility(8);
      this.mProgressContainer = localView1.findViewById(16711682);
      this.mListContainer = localView1.findViewById(16711683);
      View localView2 = localView1.findViewById(16908298);
      if (!(localView2 instanceof ListView))
      {
        if (localView2 == null)
          throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
        throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
      }
      this.mList = ((ListView)localView2);
      if (this.mEmptyView != null)
      {
        this.mList.setEmptyView(this.mEmptyView);
      }
      else if (this.mEmptyText != null)
      {
        this.mStandardEmptyView.setText(this.mEmptyText);
        this.mList.setEmptyView(this.mStandardEmptyView);
      }
    }
    this.mListShown = true;
    this.mList.setOnItemClickListener(this.mOnClickListener);
    if (this.mAdapter != null)
    {
      ListAdapter localListAdapter = this.mAdapter;
      this.mAdapter = null;
      setListAdapter(localListAdapter);
    }
    else if (this.mProgressContainer != null)
    {
      setListShown(false, false);
    }
    this.mHandler.post(this.mRequestFocus);
  }

  private void setListShown(boolean paramBoolean1, boolean paramBoolean2)
  {
    ensureList();
    if (this.mProgressContainer == null)
      throw new IllegalStateException("Can't be used with a custom content view");
    if (this.mListShown == paramBoolean1)
      return;
    this.mListShown = paramBoolean1;
    if (paramBoolean1)
    {
      if (paramBoolean2)
      {
        this.mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
        this.mListContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
      }
      else
      {
        this.mProgressContainer.clearAnimation();
        this.mListContainer.clearAnimation();
      }
      this.mProgressContainer.setVisibility(8);
      this.mListContainer.setVisibility(0);
    }
    else
    {
      if (paramBoolean2)
      {
        this.mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
        this.mListContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
      }
      else
      {
        this.mProgressContainer.clearAnimation();
        this.mListContainer.clearAnimation();
      }
      this.mProgressContainer.setVisibility(0);
      this.mListContainer.setVisibility(8);
    }
  }

  public ListAdapter getListAdapter()
  {
    return this.mAdapter;
  }

  public ListView getListView()
  {
    ensureList();
    return this.mList;
  }

  public long getSelectedItemId()
  {
    ensureList();
    return this.mList.getSelectedItemId();
  }

  public int getSelectedItemPosition()
  {
    ensureList();
    return this.mList.getSelectedItemPosition();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    Context localContext = getContext();
    FrameLayout localFrameLayout1 = new FrameLayout(localContext);
    LinearLayout localLinearLayout = new LinearLayout(localContext);
    localLinearLayout.setId(16711682);
    localLinearLayout.setOrientation(1);
    localLinearLayout.setVisibility(8);
    localLinearLayout.setGravity(17);
    localLinearLayout.addView(new ProgressBar(localContext, null, 16842874), new FrameLayout.LayoutParams(-2, -2));
    localFrameLayout1.addView(localLinearLayout, new FrameLayout.LayoutParams(-1, -1));
    FrameLayout localFrameLayout2 = new FrameLayout(localContext);
    localFrameLayout2.setId(16711683);
    TextView localTextView = new TextView(localContext);
    localTextView.setId(16711681);
    localTextView.setGravity(17);
    localFrameLayout2.addView(localTextView, new FrameLayout.LayoutParams(-1, -1));
    ListView localListView = new ListView(localContext);
    localListView.setId(16908298);
    localListView.setDrawSelectorOnTop(false);
    localFrameLayout2.addView(localListView, new FrameLayout.LayoutParams(-1, -1));
    localFrameLayout1.addView(localFrameLayout2, new FrameLayout.LayoutParams(-1, -1));
    localFrameLayout1.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    return localFrameLayout1;
  }

  public void onDestroyView()
  {
    this.mHandler.removeCallbacks(this.mRequestFocus);
    this.mList = null;
    this.mListShown = false;
    this.mListContainer = null;
    this.mProgressContainer = null;
    this.mEmptyView = null;
    this.mStandardEmptyView = null;
    super.onDestroyView();
  }

  public void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
  }

  public void onViewCreated(@NonNull View paramView, @Nullable Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    ensureList();
  }

  public void setEmptyText(CharSequence paramCharSequence)
  {
    ensureList();
    if (this.mStandardEmptyView == null)
      throw new IllegalStateException("Can't be used with a custom content view");
    this.mStandardEmptyView.setText(paramCharSequence);
    if (this.mEmptyText == null)
      this.mList.setEmptyView(this.mStandardEmptyView);
    this.mEmptyText = paramCharSequence;
  }

  public void setListAdapter(ListAdapter paramListAdapter)
  {
    int i;
    if (this.mAdapter != null)
      i = 1;
    else
      i = 0;
    this.mAdapter = paramListAdapter;
    if (this.mList != null)
    {
      this.mList.setAdapter(paramListAdapter);
      if ((!this.mListShown) && (i == 0))
      {
        IBinder localIBinder = getView().getWindowToken();
        boolean bool = false;
        if (localIBinder != null)
          bool = true;
        setListShown(true, bool);
      }
    }
  }

  public void setListShown(boolean paramBoolean)
  {
    setListShown(paramBoolean, true);
  }

  public void setListShownNoAnimation(boolean paramBoolean)
  {
    setListShown(paramBoolean, false);
  }

  public void setSelection(int paramInt)
  {
    ensureList();
    this.mList.setSelection(paramInt);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.ListFragment
 * JD-Core Version:    0.6.1
 */