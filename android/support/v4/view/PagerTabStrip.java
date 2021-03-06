package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.widget.TextView;

public class PagerTabStrip extends PagerTitleStrip
{
  private static final int FULL_UNDERLINE_HEIGHT = 1;
  private static final int INDICATOR_HEIGHT = 3;
  private static final int MIN_PADDING_BOTTOM = 6;
  private static final int MIN_STRIP_HEIGHT = 32;
  private static final int MIN_TEXT_SPACING = 64;
  private static final int TAB_PADDING = 16;
  private static final int TAB_SPACING = 32;
  private static final String TAG = "PagerTabStrip";
  private boolean mDrawFullUnderline = false;
  private boolean mDrawFullUnderlineSet = false;
  private int mFullUnderlineHeight;
  private boolean mIgnoreTap;
  private int mIndicatorColor = this.mTextColor;
  private int mIndicatorHeight;
  private float mInitialMotionX;
  private float mInitialMotionY;
  private int mMinPaddingBottom;
  private int mMinStripHeight;
  private int mMinTextSpacing;
  private int mTabAlpha = 255;
  private int mTabPadding;
  private final Paint mTabPaint = new Paint();
  private final Rect mTempRect = new Rect();
  private int mTouchSlop;

  public PagerTabStrip(@NonNull Context paramContext)
  {
    this(paramContext, null);
  }

  public PagerTabStrip(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mTabPaint.setColor(this.mIndicatorColor);
    float f = paramContext.getResources().getDisplayMetrics().density;
    this.mIndicatorHeight = (int)(0.5F + 3.0F * f);
    this.mMinPaddingBottom = (int)(0.5F + 6.0F * f);
    this.mMinTextSpacing = (int)(64.0F * f);
    this.mTabPadding = (int)(0.5F + 16.0F * f);
    this.mFullUnderlineHeight = (int)(0.5F + 1.0F * f);
    this.mMinStripHeight = (int)(0.5F + f * 32.0F);
    this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
    setTextSpacing(getTextSpacing());
    setWillNotDraw(false);
    this.mPrevText.setFocusable(true);
    this.mPrevText.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PagerTabStrip.this.mPager.setCurrentItem(-1 + PagerTabStrip.this.mPager.getCurrentItem());
      }
    });
    this.mNextText.setFocusable(true);
    this.mNextText.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PagerTabStrip.this.mPager.setCurrentItem(1 + PagerTabStrip.this.mPager.getCurrentItem());
      }
    });
    if (getBackground() == null)
      this.mDrawFullUnderline = true;
  }

  public boolean getDrawFullUnderline()
  {
    return this.mDrawFullUnderline;
  }

  int getMinHeight()
  {
    return Math.max(super.getMinHeight(), this.mMinStripHeight);
  }

  @ColorInt
  public int getTabIndicatorColor()
  {
    return this.mIndicatorColor;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i = getHeight();
    int j = this.mCurrText.getLeft() - this.mTabPadding;
    int k = this.mCurrText.getRight() + this.mTabPadding;
    int m = i - this.mIndicatorHeight;
    this.mTabPaint.setColor(this.mTabAlpha << 24 | 0xFFFFFF & this.mIndicatorColor);
    float f1 = j;
    float f2 = m;
    float f3 = k;
    float f4 = i;
    paramCanvas.drawRect(f1, f2, f3, f4, this.mTabPaint);
    if (this.mDrawFullUnderline)
    {
      this.mTabPaint.setColor(0xFF000000 | 0xFFFFFF & this.mIndicatorColor);
      paramCanvas.drawRect(getPaddingLeft(), i - this.mFullUnderlineHeight, getWidth() - getPaddingRight(), f4, this.mTabPaint);
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if ((i != 0) && (this.mIgnoreTap))
      return false;
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    switch (i)
    {
    default:
      break;
    case 2:
      if ((Math.abs(f1 - this.mInitialMotionX) > this.mTouchSlop) || (Math.abs(f2 - this.mInitialMotionY) > this.mTouchSlop))
        this.mIgnoreTap = true;
      break;
    case 1:
      if (f1 < this.mCurrText.getLeft() - this.mTabPadding)
        this.mPager.setCurrentItem(this.mPager.getCurrentItem() - 1);
      else if (f1 > this.mCurrText.getRight() + this.mTabPadding)
        this.mPager.setCurrentItem(1 + this.mPager.getCurrentItem());
      break;
    case 0:
      this.mInitialMotionX = f1;
      this.mInitialMotionY = f2;
      this.mIgnoreTap = false;
    }
    return true;
  }

  public void setBackgroundColor(@ColorInt int paramInt)
  {
    super.setBackgroundColor(paramInt);
    if (!this.mDrawFullUnderlineSet)
    {
      boolean bool;
      if ((paramInt & 0xFF000000) == 0)
        bool = true;
      else
        bool = false;
      this.mDrawFullUnderline = bool;
    }
  }

  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    super.setBackgroundDrawable(paramDrawable);
    if (!this.mDrawFullUnderlineSet)
    {
      boolean bool;
      if (paramDrawable == null)
        bool = true;
      else
        bool = false;
      this.mDrawFullUnderline = bool;
    }
  }

  public void setBackgroundResource(@DrawableRes int paramInt)
  {
    super.setBackgroundResource(paramInt);
    if (!this.mDrawFullUnderlineSet)
    {
      boolean bool;
      if (paramInt == 0)
        bool = true;
      else
        bool = false;
      this.mDrawFullUnderline = bool;
    }
  }

  public void setDrawFullUnderline(boolean paramBoolean)
  {
    this.mDrawFullUnderline = paramBoolean;
    this.mDrawFullUnderlineSet = true;
    invalidate();
  }

  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt4 < this.mMinPaddingBottom)
      paramInt4 = this.mMinPaddingBottom;
    super.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setTabIndicatorColor(@ColorInt int paramInt)
  {
    this.mIndicatorColor = paramInt;
    this.mTabPaint.setColor(this.mIndicatorColor);
    invalidate();
  }

  public void setTabIndicatorColorResource(@ColorRes int paramInt)
  {
    setTabIndicatorColor(ContextCompat.getColor(getContext(), paramInt));
  }

  public void setTextSpacing(int paramInt)
  {
    if (paramInt < this.mMinTextSpacing)
      paramInt = this.mMinTextSpacing;
    super.setTextSpacing(paramInt);
  }

  void updateTextPositions(int paramInt, float paramFloat, boolean paramBoolean)
  {
    Rect localRect = this.mTempRect;
    int i = getHeight();
    int j = this.mCurrText.getLeft() - this.mTabPadding;
    int k = this.mCurrText.getRight() + this.mTabPadding;
    int m = i - this.mIndicatorHeight;
    localRect.set(j, m, k, i);
    super.updateTextPositions(paramInt, paramFloat, paramBoolean);
    this.mTabAlpha = (int)(255.0F * (2.0F * Math.abs(paramFloat - 0.5F)));
    localRect.union(this.mCurrText.getLeft() - this.mTabPadding, m, this.mCurrText.getRight() + this.mTabPadding, i);
    invalidate(localRect);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.PagerTabStrip
 * JD-Core Version:    0.6.1
 */