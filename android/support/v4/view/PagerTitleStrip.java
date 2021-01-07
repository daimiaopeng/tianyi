package android.support.v4.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils.TruncateAt;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Locale;

@ViewPager.DecorView
public class PagerTitleStrip extends ViewGroup
{
  private static final int[] ATTRS = { 16842804, 16842901, 16842904, 16842927 };
  private static final float SIDE_ALPHA = 0.6F;
  private static final int[] TEXT_ATTRS = { 16843660 };
  private static final int TEXT_SPACING = 16;
  TextView mCurrText;
  private int mGravity;
  private int mLastKnownCurrentPage = -1;
  float mLastKnownPositionOffset = -1.0F;
  TextView mNextText;
  private int mNonPrimaryAlpha;
  private final PageListener mPageListener = new PageListener();
  ViewPager mPager;
  TextView mPrevText;
  private int mScaledTextSpacing;
  int mTextColor;
  private boolean mUpdatingPositions;
  private boolean mUpdatingText;
  private WeakReference<PagerAdapter> mWatchingAdapter;

  public PagerTitleStrip(@NonNull Context paramContext)
  {
    this(paramContext, null);
  }

  public PagerTitleStrip(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TextView localTextView1 = new TextView(paramContext);
    this.mPrevText = localTextView1;
    addView(localTextView1);
    TextView localTextView2 = new TextView(paramContext);
    this.mCurrText = localTextView2;
    addView(localTextView2);
    TextView localTextView3 = new TextView(paramContext);
    this.mNextText = localTextView3;
    addView(localTextView3);
    TypedArray localTypedArray1 = paramContext.obtainStyledAttributes(paramAttributeSet, ATTRS);
    int i = localTypedArray1.getResourceId(0, 0);
    if (i != 0)
    {
      TextViewCompat.setTextAppearance(this.mPrevText, i);
      TextViewCompat.setTextAppearance(this.mCurrText, i);
      TextViewCompat.setTextAppearance(this.mNextText, i);
    }
    int j = localTypedArray1.getDimensionPixelSize(1, 0);
    if (j != 0)
      setTextSize(0, j);
    if (localTypedArray1.hasValue(2))
    {
      int k = localTypedArray1.getColor(2, 0);
      this.mPrevText.setTextColor(k);
      this.mCurrText.setTextColor(k);
      this.mNextText.setTextColor(k);
    }
    this.mGravity = localTypedArray1.getInteger(3, 80);
    localTypedArray1.recycle();
    this.mTextColor = this.mCurrText.getTextColors().getDefaultColor();
    setNonPrimaryAlpha(0.6F);
    this.mPrevText.setEllipsize(TextUtils.TruncateAt.END);
    this.mCurrText.setEllipsize(TextUtils.TruncateAt.END);
    this.mNextText.setEllipsize(TextUtils.TruncateAt.END);
    boolean bool = false;
    if (i != 0)
    {
      TypedArray localTypedArray2 = paramContext.obtainStyledAttributes(i, TEXT_ATTRS);
      bool = localTypedArray2.getBoolean(0, false);
      localTypedArray2.recycle();
    }
    if (bool)
    {
      setSingleLineAllCaps(this.mPrevText);
      setSingleLineAllCaps(this.mCurrText);
      setSingleLineAllCaps(this.mNextText);
    }
    else
    {
      this.mPrevText.setSingleLine();
      this.mCurrText.setSingleLine();
      this.mNextText.setSingleLine();
    }
    this.mScaledTextSpacing = (int)(16.0F * paramContext.getResources().getDisplayMetrics().density);
  }

  private static void setSingleLineAllCaps(TextView paramTextView)
  {
    paramTextView.setTransformationMethod(new SingleLineAllCapsTransform(paramTextView.getContext()));
  }

  int getMinHeight()
  {
    Drawable localDrawable = getBackground();
    int i;
    if (localDrawable != null)
      i = localDrawable.getIntrinsicHeight();
    else
      i = 0;
    return i;
  }

  public int getTextSpacing()
  {
    return this.mScaledTextSpacing;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    ViewParent localViewParent = getParent();
    if (!(localViewParent instanceof ViewPager))
      throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
    ViewPager localViewPager = (ViewPager)localViewParent;
    PagerAdapter localPagerAdapter1 = localViewPager.getAdapter();
    localViewPager.setInternalPageChangeListener(this.mPageListener);
    localViewPager.addOnAdapterChangeListener(this.mPageListener);
    this.mPager = localViewPager;
    PagerAdapter localPagerAdapter2;
    if (this.mWatchingAdapter != null)
      localPagerAdapter2 = (PagerAdapter)this.mWatchingAdapter.get();
    else
      localPagerAdapter2 = null;
    updateAdapter(localPagerAdapter2, localPagerAdapter1);
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mPager != null)
    {
      updateAdapter(this.mPager.getAdapter(), null);
      this.mPager.setInternalPageChangeListener(null);
      this.mPager.removeOnAdapterChangeListener(this.mPageListener);
      this.mPager = null;
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.mPager != null)
    {
      boolean bool = this.mLastKnownPositionOffset < 0.0F;
      float f = 0.0F;
      if (!bool)
        f = this.mLastKnownPositionOffset;
      updateTextPositions(this.mLastKnownCurrentPage, f, true);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (View.MeasureSpec.getMode(paramInt1) != 1073741824)
      throw new IllegalStateException("Must measure with an exact width");
    int i = getPaddingTop() + getPaddingBottom();
    int j = getChildMeasureSpec(paramInt2, i, -2);
    int k = View.MeasureSpec.getSize(paramInt1);
    int m = getChildMeasureSpec(paramInt1, (int)(0.2F * k), -2);
    this.mPrevText.measure(m, j);
    this.mCurrText.measure(m, j);
    this.mNextText.measure(m, j);
    int i1;
    if (View.MeasureSpec.getMode(paramInt2) == 1073741824)
    {
      i1 = View.MeasureSpec.getSize(paramInt2);
    }
    else
    {
      int n = this.mCurrText.getMeasuredHeight();
      i1 = Math.max(getMinHeight(), n + i);
    }
    setMeasuredDimension(k, View.resolveSizeAndState(i1, paramInt2, this.mCurrText.getMeasuredState() << 16));
  }

  public void requestLayout()
  {
    if (!this.mUpdatingText)
      super.requestLayout();
  }

  public void setGravity(int paramInt)
  {
    this.mGravity = paramInt;
    requestLayout();
  }

  public void setNonPrimaryAlpha(@FloatRange(from=0.0D, to=1.0D) float paramFloat)
  {
    this.mNonPrimaryAlpha = (0xFF & (int)(paramFloat * 255.0F));
    int i = this.mNonPrimaryAlpha << 24 | 0xFFFFFF & this.mTextColor;
    this.mPrevText.setTextColor(i);
    this.mNextText.setTextColor(i);
  }

  public void setTextColor(@ColorInt int paramInt)
  {
    this.mTextColor = paramInt;
    this.mCurrText.setTextColor(paramInt);
    int i = this.mNonPrimaryAlpha << 24 | 0xFFFFFF & this.mTextColor;
    this.mPrevText.setTextColor(i);
    this.mNextText.setTextColor(i);
  }

  public void setTextSize(int paramInt, float paramFloat)
  {
    this.mPrevText.setTextSize(paramInt, paramFloat);
    this.mCurrText.setTextSize(paramInt, paramFloat);
    this.mNextText.setTextSize(paramInt, paramFloat);
  }

  public void setTextSpacing(int paramInt)
  {
    this.mScaledTextSpacing = paramInt;
    requestLayout();
  }

  void updateAdapter(PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2)
  {
    if (paramPagerAdapter1 != null)
    {
      paramPagerAdapter1.unregisterDataSetObserver(this.mPageListener);
      this.mWatchingAdapter = null;
    }
    if (paramPagerAdapter2 != null)
    {
      paramPagerAdapter2.registerDataSetObserver(this.mPageListener);
      this.mWatchingAdapter = new WeakReference(paramPagerAdapter2);
    }
    if (this.mPager != null)
    {
      this.mLastKnownCurrentPage = -1;
      this.mLastKnownPositionOffset = -1.0F;
      updateText(this.mPager.getCurrentItem(), paramPagerAdapter2);
      requestLayout();
    }
  }

  void updateText(int paramInt, PagerAdapter paramPagerAdapter)
  {
    int i;
    if (paramPagerAdapter != null)
      i = paramPagerAdapter.getCount();
    else
      i = 0;
    this.mUpdatingText = true;
    CharSequence localCharSequence1;
    if ((paramInt >= 1) && (paramPagerAdapter != null))
      localCharSequence1 = paramPagerAdapter.getPageTitle(paramInt - 1);
    else
      localCharSequence1 = null;
    this.mPrevText.setText(localCharSequence1);
    TextView localTextView = this.mCurrText;
    CharSequence localCharSequence2;
    if ((paramPagerAdapter != null) && (paramInt < i))
      localCharSequence2 = paramPagerAdapter.getPageTitle(paramInt);
    else
      localCharSequence2 = null;
    localTextView.setText(localCharSequence2);
    int j = paramInt + 1;
    CharSequence localCharSequence3 = null;
    if (j < i)
    {
      localCharSequence3 = null;
      if (paramPagerAdapter != null)
        localCharSequence3 = paramPagerAdapter.getPageTitle(j);
    }
    this.mNextText.setText(localCharSequence3);
    int k = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int)(0.8F * getWidth() - getPaddingLeft() - getPaddingRight())), -2147483648);
    int m = View.MeasureSpec.makeMeasureSpec(Math.max(0, getHeight() - getPaddingTop() - getPaddingBottom()), -2147483648);
    this.mPrevText.measure(k, m);
    this.mCurrText.measure(k, m);
    this.mNextText.measure(k, m);
    this.mLastKnownCurrentPage = paramInt;
    if (!this.mUpdatingPositions)
      updateTextPositions(paramInt, this.mLastKnownPositionOffset, false);
    this.mUpdatingText = false;
  }

  void updateTextPositions(int paramInt, float paramFloat, boolean paramBoolean)
  {
    if (paramInt != this.mLastKnownCurrentPage)
      updateText(paramInt, this.mPager.getAdapter());
    else if ((!paramBoolean) && (paramFloat == this.mLastKnownPositionOffset))
      return;
    this.mUpdatingPositions = true;
    int i = this.mPrevText.getMeasuredWidth();
    int j = this.mCurrText.getMeasuredWidth();
    int k = this.mNextText.getMeasuredWidth();
    int m = j / 2;
    int n = getWidth();
    int i1 = getHeight();
    int i2 = getPaddingLeft();
    int i3 = getPaddingRight();
    int i4 = getPaddingTop();
    int i5 = getPaddingBottom();
    int i6 = i2 + m;
    int i7 = i3 + m;
    int i8 = n - i6 - i7;
    float f = 0.5F + paramFloat;
    if (f > 1.0F)
      f -= 1.0F;
    int i9 = n - i7 - (int)(f * i8) - m;
    int i10 = j + i9;
    int i11 = this.mPrevText.getBaseline();
    int i12 = this.mCurrText.getBaseline();
    int i13 = this.mNextText.getBaseline();
    int i14 = Math.max(Math.max(i11, i12), i13);
    int i15 = i14 - i11;
    int i16 = i14 - i12;
    int i17 = i14 - i13;
    int i18 = i15 + this.mPrevText.getMeasuredHeight();
    int i19 = i16 + this.mCurrText.getMeasuredHeight();
    int i20 = i17 + this.mNextText.getMeasuredHeight();
    int i21 = Math.max(Math.max(i18, i19), i20);
    int i22 = 0x70 & this.mGravity;
    int i24;
    int i25;
    int i26;
    if (i22 != 16)
    {
      if (i22 != 80)
      {
        i24 = i15 + i4;
        i25 = i16 + i4;
        i26 = i4 + i17;
      }
      else
      {
        int i29 = i1 - i5 - i21;
        i24 = i15 + i29;
        i25 = i16 + i29;
        i26 = i29 + i17;
      }
    }
    else
    {
      int i23 = (i1 - i4 - i5 - i21) / 2;
      i24 = i15 + i23;
      i25 = i16 + i23;
      i26 = i23 + i17;
    }
    this.mCurrText.layout(i9, i25, i10, i25 + this.mCurrText.getMeasuredHeight());
    int i27 = Math.min(i2, i9 - this.mScaledTextSpacing - i);
    this.mPrevText.layout(i27, i24, i + i27, i24 + this.mPrevText.getMeasuredHeight());
    int i28 = Math.max(n - i3 - k, i10 + this.mScaledTextSpacing);
    this.mNextText.layout(i28, i26, i28 + k, i26 + this.mNextText.getMeasuredHeight());
    this.mLastKnownPositionOffset = paramFloat;
    this.mUpdatingPositions = false;
  }

  private class PageListener extends DataSetObserver
    implements ViewPager.OnAdapterChangeListener, ViewPager.OnPageChangeListener
  {
    private int mScrollState;

    PageListener()
    {
    }

    public void onAdapterChanged(ViewPager paramViewPager, PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2)
    {
      PagerTitleStrip.this.updateAdapter(paramPagerAdapter1, paramPagerAdapter2);
    }

    public void onChanged()
    {
      PagerTitleStrip.this.updateText(PagerTitleStrip.this.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
      boolean bool = PagerTitleStrip.this.mLastKnownPositionOffset < 0.0F;
      float f = 0.0F;
      if (!bool)
        f = PagerTitleStrip.this.mLastKnownPositionOffset;
      PagerTitleStrip.this.updateTextPositions(PagerTitleStrip.this.mPager.getCurrentItem(), f, true);
    }

    public void onPageScrollStateChanged(int paramInt)
    {
      this.mScrollState = paramInt;
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      if (paramFloat > 0.5F)
        paramInt1++;
      PagerTitleStrip.this.updateTextPositions(paramInt1, paramFloat, false);
    }

    public void onPageSelected(int paramInt)
    {
      if (this.mScrollState == 0)
      {
        PagerTitleStrip.this.updateText(PagerTitleStrip.this.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
        boolean bool = PagerTitleStrip.this.mLastKnownPositionOffset < 0.0F;
        float f = 0.0F;
        if (!bool)
          f = PagerTitleStrip.this.mLastKnownPositionOffset;
        PagerTitleStrip.this.updateTextPositions(PagerTitleStrip.this.mPager.getCurrentItem(), f, true);
      }
    }
  }

  private static class SingleLineAllCapsTransform extends SingleLineTransformationMethod
  {
    private Locale mLocale;

    SingleLineAllCapsTransform(Context paramContext)
    {
      this.mLocale = paramContext.getResources().getConfiguration().locale;
    }

    public CharSequence getTransformation(CharSequence paramCharSequence, View paramView)
    {
      CharSequence localCharSequence = super.getTransformation(paramCharSequence, paramView);
      String str;
      if (localCharSequence != null)
        str = localCharSequence.toString().toUpperCase(this.mLocale);
      else
        str = null;
      return str;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.PagerTitleStrip
 * JD-Core Version:    0.6.1
 */