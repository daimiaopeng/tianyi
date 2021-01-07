package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import java.lang.reflect.Method;

@Deprecated
public class ActionBarDrawerToggle
  implements DrawerLayout.DrawerListener
{
  private static final int ID_HOME = 16908332;
  private static final String TAG = "ActionBarDrawerToggle";
  private static final int[] THEME_ATTRS = { 16843531 };
  private static final float TOGGLE_DRAWABLE_OFFSET = 0.3333333F;
  final Activity mActivity;
  private final Delegate mActivityImpl;
  private final int mCloseDrawerContentDescRes;
  private Drawable mDrawerImage;
  private final int mDrawerImageResource;
  private boolean mDrawerIndicatorEnabled = true;
  private final DrawerLayout mDrawerLayout;
  private boolean mHasCustomUpIndicator;
  private Drawable mHomeAsUpIndicator;
  private final int mOpenDrawerContentDescRes;
  private SetIndicatorInfo mSetIndicatorInfo;
  private SlideDrawable mSlider;

  public ActionBarDrawerToggle(Activity paramActivity, DrawerLayout paramDrawerLayout, @DrawableRes int paramInt1, @StringRes int paramInt2, @StringRes int paramInt3)
  {
    this(paramActivity, paramDrawerLayout, true ^ assumeMaterial(paramActivity), paramInt1, paramInt2, paramInt3);
  }

  public ActionBarDrawerToggle(Activity paramActivity, DrawerLayout paramDrawerLayout, boolean paramBoolean, @DrawableRes int paramInt1, @StringRes int paramInt2, @StringRes int paramInt3)
  {
    this.mActivity = paramActivity;
    if ((paramActivity instanceof DelegateProvider))
      this.mActivityImpl = ((DelegateProvider)paramActivity).getDrawerToggleDelegate();
    else
      this.mActivityImpl = null;
    this.mDrawerLayout = paramDrawerLayout;
    this.mDrawerImageResource = paramInt1;
    this.mOpenDrawerContentDescRes = paramInt2;
    this.mCloseDrawerContentDescRes = paramInt3;
    this.mHomeAsUpIndicator = getThemeUpIndicator();
    this.mDrawerImage = ContextCompat.getDrawable(paramActivity, paramInt1);
    this.mSlider = new SlideDrawable(this.mDrawerImage);
    SlideDrawable localSlideDrawable = this.mSlider;
    float f;
    if (paramBoolean)
      f = 0.3333333F;
    else
      f = 0.0F;
    localSlideDrawable.setOffset(f);
  }

  private static boolean assumeMaterial(Context paramContext)
  {
    boolean bool;
    if ((paramContext.getApplicationInfo().targetSdkVersion >= 21) && (Build.VERSION.SDK_INT >= 21))
      bool = true;
    else
      bool = false;
    return bool;
  }

  private Drawable getThemeUpIndicator()
  {
    if (this.mActivityImpl != null)
      return this.mActivityImpl.getThemeUpIndicator();
    if (Build.VERSION.SDK_INT >= 18)
    {
      ActionBar localActionBar = this.mActivity.getActionBar();
      Object localObject;
      if (localActionBar != null)
        localObject = localActionBar.getThemedContext();
      else
        localObject = this.mActivity;
      TypedArray localTypedArray2 = ((Context)localObject).obtainStyledAttributes(null, THEME_ATTRS, 16843470, 0);
      Drawable localDrawable2 = localTypedArray2.getDrawable(0);
      localTypedArray2.recycle();
      return localDrawable2;
    }
    TypedArray localTypedArray1 = this.mActivity.obtainStyledAttributes(THEME_ATTRS);
    Drawable localDrawable1 = localTypedArray1.getDrawable(0);
    localTypedArray1.recycle();
    return localDrawable1;
  }

  private void setActionBarDescription(int paramInt)
  {
    if (this.mActivityImpl != null)
    {
      this.mActivityImpl.setActionBarDescription(paramInt);
      return;
    }
    if (Build.VERSION.SDK_INT >= 18)
    {
      ActionBar localActionBar2 = this.mActivity.getActionBar();
      if (localActionBar2 != null)
        localActionBar2.setHomeActionContentDescription(paramInt);
    }
    else
    {
      if (this.mSetIndicatorInfo == null)
        this.mSetIndicatorInfo = new SetIndicatorInfo(this.mActivity);
      if (this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null)
        try
        {
          ActionBar localActionBar1 = this.mActivity.getActionBar();
          Method localMethod = this.mSetIndicatorInfo.mSetHomeActionContentDescription;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(paramInt);
          localMethod.invoke(localActionBar1, arrayOfObject);
          localActionBar1.setSubtitle(localActionBar1.getSubtitle());
        }
        catch (Exception localException)
        {
          Log.w("ActionBarDrawerToggle", "Couldn't set content description via JB-MR2 API", localException);
        }
    }
  }

  private void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
  {
    if (this.mActivityImpl != null)
    {
      this.mActivityImpl.setActionBarUpIndicator(paramDrawable, paramInt);
      return;
    }
    if (Build.VERSION.SDK_INT >= 18)
    {
      ActionBar localActionBar2 = this.mActivity.getActionBar();
      if (localActionBar2 != null)
      {
        localActionBar2.setHomeAsUpIndicator(paramDrawable);
        localActionBar2.setHomeActionContentDescription(paramInt);
      }
    }
    else
    {
      if (this.mSetIndicatorInfo == null)
        this.mSetIndicatorInfo = new SetIndicatorInfo(this.mActivity);
      if (this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null)
        try
        {
          ActionBar localActionBar1 = this.mActivity.getActionBar();
          this.mSetIndicatorInfo.mSetHomeAsUpIndicator.invoke(localActionBar1, new Object[] { paramDrawable });
          Method localMethod = this.mSetIndicatorInfo.mSetHomeActionContentDescription;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(paramInt);
          localMethod.invoke(localActionBar1, arrayOfObject);
        }
        catch (Exception localException)
        {
          Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator via JB-MR2 API", localException);
        }
      else if (this.mSetIndicatorInfo.mUpIndicatorView != null)
        this.mSetIndicatorInfo.mUpIndicatorView.setImageDrawable(paramDrawable);
      else
        Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator");
    }
  }

  public boolean isDrawerIndicatorEnabled()
  {
    return this.mDrawerIndicatorEnabled;
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (!this.mHasCustomUpIndicator)
      this.mHomeAsUpIndicator = getThemeUpIndicator();
    this.mDrawerImage = ContextCompat.getDrawable(this.mActivity, this.mDrawerImageResource);
    syncState();
  }

  public void onDrawerClosed(View paramView)
  {
    this.mSlider.setPosition(0.0F);
    if (this.mDrawerIndicatorEnabled)
      setActionBarDescription(this.mOpenDrawerContentDescRes);
  }

  public void onDrawerOpened(View paramView)
  {
    this.mSlider.setPosition(1.0F);
    if (this.mDrawerIndicatorEnabled)
      setActionBarDescription(this.mCloseDrawerContentDescRes);
  }

  public void onDrawerSlide(View paramView, float paramFloat)
  {
    float f1 = this.mSlider.getPosition();
    float f2;
    if (paramFloat > 0.5F)
      f2 = Math.max(f1, 2.0F * Math.max(0.0F, paramFloat - 0.5F));
    else
      f2 = Math.min(f1, paramFloat * 2.0F);
    this.mSlider.setPosition(f2);
  }

  public void onDrawerStateChanged(int paramInt)
  {
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if ((paramMenuItem != null) && (paramMenuItem.getItemId() == 16908332) && (this.mDrawerIndicatorEnabled))
    {
      if (this.mDrawerLayout.isDrawerVisible(8388611))
        this.mDrawerLayout.closeDrawer(8388611);
      else
        this.mDrawerLayout.openDrawer(8388611);
      return true;
    }
    return false;
  }

  public void setDrawerIndicatorEnabled(boolean paramBoolean)
  {
    if (paramBoolean != this.mDrawerIndicatorEnabled)
    {
      if (paramBoolean)
      {
        SlideDrawable localSlideDrawable = this.mSlider;
        int i;
        if (this.mDrawerLayout.isDrawerOpen(8388611))
          i = this.mCloseDrawerContentDescRes;
        else
          i = this.mOpenDrawerContentDescRes;
        setActionBarUpIndicator(localSlideDrawable, i);
      }
      else
      {
        setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
      }
      this.mDrawerIndicatorEnabled = paramBoolean;
    }
  }

  public void setHomeAsUpIndicator(int paramInt)
  {
    Drawable localDrawable;
    if (paramInt != 0)
      localDrawable = ContextCompat.getDrawable(this.mActivity, paramInt);
    else
      localDrawable = null;
    setHomeAsUpIndicator(localDrawable);
  }

  public void setHomeAsUpIndicator(Drawable paramDrawable)
  {
    if (paramDrawable == null)
    {
      this.mHomeAsUpIndicator = getThemeUpIndicator();
      this.mHasCustomUpIndicator = false;
    }
    else
    {
      this.mHomeAsUpIndicator = paramDrawable;
      this.mHasCustomUpIndicator = true;
    }
    if (!this.mDrawerIndicatorEnabled)
      setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
  }

  public void syncState()
  {
    if (this.mDrawerLayout.isDrawerOpen(8388611))
      this.mSlider.setPosition(1.0F);
    else
      this.mSlider.setPosition(0.0F);
    if (this.mDrawerIndicatorEnabled)
    {
      SlideDrawable localSlideDrawable = this.mSlider;
      int i;
      if (this.mDrawerLayout.isDrawerOpen(8388611))
        i = this.mCloseDrawerContentDescRes;
      else
        i = this.mOpenDrawerContentDescRes;
      setActionBarUpIndicator(localSlideDrawable, i);
    }
  }

  @Deprecated
  public static abstract interface Delegate
  {
    @Nullable
    public abstract Drawable getThemeUpIndicator();

    public abstract void setActionBarDescription(@StringRes int paramInt);

    public abstract void setActionBarUpIndicator(Drawable paramDrawable, @StringRes int paramInt);
  }

  @Deprecated
  public static abstract interface DelegateProvider
  {
    @Nullable
    public abstract ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();
  }

  private static class SetIndicatorInfo
  {
    Method mSetHomeActionContentDescription;
    Method mSetHomeAsUpIndicator;
    ImageView mUpIndicatorView;

    SetIndicatorInfo(Activity paramActivity)
    {
      try
      {
        this.mSetHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", new Class[] { Drawable.class });
        Class[] arrayOfClass = new Class[1];
        arrayOfClass[0] = Integer.TYPE;
        this.mSetHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", arrayOfClass);
        return;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        View localView1 = paramActivity.findViewById(16908332);
        if (localView1 == null)
          return;
        ViewGroup localViewGroup = (ViewGroup)localView1.getParent();
        if (localViewGroup.getChildCount() != 2)
          return;
        View localView2 = localViewGroup.getChildAt(0);
        View localView3 = localViewGroup.getChildAt(1);
        localView3 = localView2.getId() == 16908332 ? localNoSuchMethodException : localView2;
        if ((localView3 instanceof ImageView))
          this.mUpIndicatorView = ((ImageView)localView3);
      }
    }
  }

  private class SlideDrawable extends InsetDrawable
    implements Drawable.Callback
  {
    private final boolean mHasMirroring;
    private float mOffset;
    private float mPosition;
    private final Rect mTmpRect;

    SlideDrawable(Drawable arg2)
    {
      super(0);
      int i = Build.VERSION.SDK_INT;
      boolean bool = false;
      if (i > 18)
        bool = true;
      this.mHasMirroring = bool;
      this.mTmpRect = new Rect();
    }

    public void draw(@NonNull Canvas paramCanvas)
    {
      copyBounds(this.mTmpRect);
      paramCanvas.save();
      int i = ViewCompat.getLayoutDirection(ActionBarDrawerToggle.this.mActivity.getWindow().getDecorView());
      int j = 1;
      int k;
      if (i == j)
        k = 1;
      else
        k = 0;
      if (k != 0)
        j = -1;
      int m = this.mTmpRect.width();
      float f1 = -this.mOffset;
      float f2 = m;
      paramCanvas.translate(f1 * f2 * this.mPosition * j, 0.0F);
      if ((k != 0) && (!this.mHasMirroring))
      {
        paramCanvas.translate(f2, 0.0F);
        paramCanvas.scale(-1.0F, 1.0F);
      }
      super.draw(paramCanvas);
      paramCanvas.restore();
    }

    public float getPosition()
    {
      return this.mPosition;
    }

    public void setOffset(float paramFloat)
    {
      this.mOffset = paramFloat;
      invalidateSelf();
    }

    public void setPosition(float paramFloat)
    {
      this.mPosition = paramFloat;
      invalidateSelf();
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.ActionBarDrawerToggle
 * JD-Core Version:    0.6.1
 */