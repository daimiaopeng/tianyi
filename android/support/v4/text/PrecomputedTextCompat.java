package android.support.v4.text;

import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.UiThread;
import android.support.v4.os.TraceCompat;
import android.support.v4.util.ObjectsCompat;
import android.support.v4.util.Preconditions;
import android.text.Layout.Alignment;
import android.text.PrecomputedText;
import android.text.PrecomputedText.Params;
import android.text.PrecomputedText.Params.Builder;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.StaticLayout.Builder;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class PrecomputedTextCompat
  implements Spannable
{
  private static final char LINE_FEED = '\n';

  @GuardedBy("sLock")
  @NonNull
  private static Executor sExecutor;
  private static final Object sLock = new Object();

  @NonNull
  private final int[] mParagraphEnds;

  @NonNull
  private final Params mParams;

  @NonNull
  private final Spannable mText;

  @Nullable
  private final PrecomputedText mWrapped;

  @RequiresApi(28)
  private PrecomputedTextCompat(@NonNull PrecomputedText paramPrecomputedText, @NonNull Params paramParams)
  {
    this.mText = paramPrecomputedText;
    this.mParams = paramParams;
    this.mParagraphEnds = null;
    this.mWrapped = paramPrecomputedText;
  }

  private PrecomputedTextCompat(@NonNull CharSequence paramCharSequence, @NonNull Params paramParams, @NonNull int[] paramArrayOfInt)
  {
    this.mText = new SpannableString(paramCharSequence);
    this.mParams = paramParams;
    this.mParagraphEnds = paramArrayOfInt;
    this.mWrapped = null;
  }

  public static PrecomputedTextCompat create(@NonNull CharSequence paramCharSequence, @NonNull Params paramParams)
  {
    Preconditions.checkNotNull(paramCharSequence);
    Preconditions.checkNotNull(paramParams);
    while (true)
    {
      int m;
      try
      {
        TraceCompat.beginSection("PrecomputedText");
        if ((Build.VERSION.SDK_INT >= 28) && (paramParams.mWrapped != null))
        {
          PrecomputedTextCompat localPrecomputedTextCompat2 = new PrecomputedTextCompat(PrecomputedText.create(paramCharSequence, paramParams.mWrapped), paramParams);
          return localPrecomputedTextCompat2;
        }
        ArrayList localArrayList = new ArrayList();
        int i = paramCharSequence.length();
        j = 0;
        if (j < i)
        {
          m = TextUtils.indexOf(paramCharSequence, '\n', j, i);
          if (m >= 0)
            break label274;
          j = i;
          localArrayList.add(Integer.valueOf(j));
          continue;
        }
        int[] arrayOfInt = new int[localArrayList.size()];
        int k = 0;
        if (k < localArrayList.size())
        {
          arrayOfInt[k] = ((Integer)localArrayList.get(k)).intValue();
          k++;
          continue;
        }
        if (Build.VERSION.SDK_INT >= 23)
          StaticLayout.Builder.obtain(paramCharSequence, 0, paramCharSequence.length(), paramParams.getTextPaint(), 2147483647).setBreakStrategy(paramParams.getBreakStrategy()).setHyphenationFrequency(paramParams.getHyphenationFrequency()).setTextDirection(paramParams.getTextDirection()).build();
        else if (Build.VERSION.SDK_INT >= 21)
          new StaticLayout(paramCharSequence, paramParams.getTextPaint(), 2147483647, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
        PrecomputedTextCompat localPrecomputedTextCompat1 = new PrecomputedTextCompat(paramCharSequence, paramParams, arrayOfInt);
        return localPrecomputedTextCompat1;
      }
      finally
      {
        TraceCompat.endSection();
      }
      label274: int j = m + 1;
    }
  }

  private int findParaIndex(@IntRange(from=0L) int paramInt)
  {
    for (int i = 0; i < this.mParagraphEnds.length; i++)
      if (paramInt < this.mParagraphEnds[i])
        return i;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("pos must be less than ");
    localStringBuilder.append(this.mParagraphEnds[(-1 + this.mParagraphEnds.length)]);
    localStringBuilder.append(", gave ");
    localStringBuilder.append(paramInt);
    throw new IndexOutOfBoundsException(localStringBuilder.toString());
  }

  @UiThread
  public static Future<PrecomputedTextCompat> getTextFuture(@NonNull CharSequence paramCharSequence, @NonNull Params paramParams, @Nullable Executor paramExecutor)
  {
    PrecomputedTextFutureTask localPrecomputedTextFutureTask = new PrecomputedTextFutureTask(paramParams, paramCharSequence);
    if (paramExecutor == null)
      synchronized (sLock)
      {
        if (sExecutor == null)
          sExecutor = Executors.newFixedThreadPool(1);
        paramExecutor = sExecutor;
      }
    paramExecutor.execute(localPrecomputedTextFutureTask);
    return localPrecomputedTextFutureTask;
  }

  public char charAt(int paramInt)
  {
    return this.mText.charAt(paramInt);
  }

  @IntRange(from=0L)
  public int getParagraphCount()
  {
    if (Build.VERSION.SDK_INT >= 28)
      return this.mWrapped.getParagraphCount();
    return this.mParagraphEnds.length;
  }

  @IntRange(from=0L)
  public int getParagraphEnd(@IntRange(from=0L) int paramInt)
  {
    Preconditions.checkArgumentInRange(paramInt, 0, getParagraphCount(), "paraIndex");
    if (Build.VERSION.SDK_INT >= 28)
      return this.mWrapped.getParagraphEnd(paramInt);
    return this.mParagraphEnds[paramInt];
  }

  @IntRange(from=0L)
  public int getParagraphStart(@IntRange(from=0L) int paramInt)
  {
    Preconditions.checkArgumentInRange(paramInt, 0, getParagraphCount(), "paraIndex");
    if (Build.VERSION.SDK_INT >= 28)
      return this.mWrapped.getParagraphStart(paramInt);
    int i;
    if (paramInt == 0)
      i = 0;
    else
      i = this.mParagraphEnds[(paramInt - 1)];
    return i;
  }

  @NonNull
  public Params getParams()
  {
    return this.mParams;
  }

  @Nullable
  @RequiresApi(28)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public PrecomputedText getPrecomputedText()
  {
    if ((this.mText instanceof PrecomputedText))
      return (PrecomputedText)this.mText;
    return null;
  }

  public int getSpanEnd(Object paramObject)
  {
    return this.mText.getSpanEnd(paramObject);
  }

  public int getSpanFlags(Object paramObject)
  {
    return this.mText.getSpanFlags(paramObject);
  }

  public int getSpanStart(Object paramObject)
  {
    return this.mText.getSpanStart(paramObject);
  }

  public <T> T[] getSpans(int paramInt1, int paramInt2, Class<T> paramClass)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return this.mWrapped.getSpans(paramInt1, paramInt2, paramClass);
    return this.mText.getSpans(paramInt1, paramInt2, paramClass);
  }

  public int length()
  {
    return this.mText.length();
  }

  public int nextSpanTransition(int paramInt1, int paramInt2, Class paramClass)
  {
    return this.mText.nextSpanTransition(paramInt1, paramInt2, paramClass);
  }

  public void removeSpan(Object paramObject)
  {
    if ((paramObject instanceof MetricAffectingSpan))
      throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
    if (Build.VERSION.SDK_INT >= 28)
      this.mWrapped.removeSpan(paramObject);
    else
      this.mText.removeSpan(paramObject);
  }

  public void setSpan(Object paramObject, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramObject instanceof MetricAffectingSpan))
      throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
    if (Build.VERSION.SDK_INT >= 28)
      this.mWrapped.setSpan(paramObject, paramInt1, paramInt2, paramInt3);
    else
      this.mText.setSpan(paramObject, paramInt1, paramInt2, paramInt3);
  }

  public CharSequence subSequence(int paramInt1, int paramInt2)
  {
    return this.mText.subSequence(paramInt1, paramInt2);
  }

  public String toString()
  {
    return this.mText.toString();
  }

  public static final class Params
  {
    private final int mBreakStrategy;
    private final int mHyphenationFrequency;

    @NonNull
    private final TextPaint mPaint;

    @Nullable
    private final TextDirectionHeuristic mTextDir;
    final PrecomputedText.Params mWrapped;

    @RequiresApi(28)
    public Params(@NonNull PrecomputedText.Params paramParams)
    {
      this.mPaint = paramParams.getTextPaint();
      this.mTextDir = paramParams.getTextDirection();
      this.mBreakStrategy = paramParams.getBreakStrategy();
      this.mHyphenationFrequency = paramParams.getHyphenationFrequency();
      this.mWrapped = paramParams;
    }

    Params(@NonNull TextPaint paramTextPaint, @NonNull TextDirectionHeuristic paramTextDirectionHeuristic, int paramInt1, int paramInt2)
    {
      if (Build.VERSION.SDK_INT >= 28)
        this.mWrapped = new PrecomputedText.Params.Builder(paramTextPaint).setBreakStrategy(paramInt1).setHyphenationFrequency(paramInt2).setTextDirection(paramTextDirectionHeuristic).build();
      else
        this.mWrapped = null;
      this.mPaint = paramTextPaint;
      this.mTextDir = paramTextDirectionHeuristic;
      this.mBreakStrategy = paramInt1;
      this.mHyphenationFrequency = paramInt2;
    }

    public boolean equals(@Nullable Object paramObject)
    {
      if (paramObject == this)
        return true;
      if ((paramObject != null) && ((paramObject instanceof Params)))
      {
        Params localParams = (Params)paramObject;
        if (this.mWrapped != null)
          return this.mWrapped.equals(localParams.mWrapped);
        if (Build.VERSION.SDK_INT >= 23)
        {
          if (this.mBreakStrategy != localParams.getBreakStrategy())
            return false;
          if (this.mHyphenationFrequency != localParams.getHyphenationFrequency())
            return false;
        }
        if ((Build.VERSION.SDK_INT >= 18) && (this.mTextDir != localParams.getTextDirection()))
          return false;
        if (this.mPaint.getTextSize() != localParams.getTextPaint().getTextSize())
          return false;
        if (this.mPaint.getTextScaleX() != localParams.getTextPaint().getTextScaleX())
          return false;
        if (this.mPaint.getTextSkewX() != localParams.getTextPaint().getTextSkewX())
          return false;
        if (Build.VERSION.SDK_INT >= 21)
        {
          if (this.mPaint.getLetterSpacing() != localParams.getTextPaint().getLetterSpacing())
            return false;
          if (!TextUtils.equals(this.mPaint.getFontFeatureSettings(), localParams.getTextPaint().getFontFeatureSettings()))
            return false;
        }
        if (this.mPaint.getFlags() != localParams.getTextPaint().getFlags())
          return false;
        if (Build.VERSION.SDK_INT >= 24)
        {
          if (!this.mPaint.getTextLocales().equals(localParams.getTextPaint().getTextLocales()))
            return false;
        }
        else if ((Build.VERSION.SDK_INT >= 17) && (!this.mPaint.getTextLocale().equals(localParams.getTextPaint().getTextLocale())))
          return false;
        if (this.mPaint.getTypeface() == null)
        {
          if (localParams.getTextPaint().getTypeface() != null)
            return false;
        }
        else if (!this.mPaint.getTypeface().equals(localParams.getTextPaint().getTypeface()))
          return false;
        return true;
      }
      return false;
    }

    @RequiresApi(23)
    public int getBreakStrategy()
    {
      return this.mBreakStrategy;
    }

    @RequiresApi(23)
    public int getHyphenationFrequency()
    {
      return this.mHyphenationFrequency;
    }

    @Nullable
    @RequiresApi(18)
    public TextDirectionHeuristic getTextDirection()
    {
      return this.mTextDir;
    }

    @NonNull
    public TextPaint getTextPaint()
    {
      return this.mPaint;
    }

    public int hashCode()
    {
      if (Build.VERSION.SDK_INT >= 24)
      {
        Object[] arrayOfObject5 = new Object[11];
        arrayOfObject5[0] = Float.valueOf(this.mPaint.getTextSize());
        arrayOfObject5[1] = Float.valueOf(this.mPaint.getTextScaleX());
        arrayOfObject5[2] = Float.valueOf(this.mPaint.getTextSkewX());
        arrayOfObject5[3] = Float.valueOf(this.mPaint.getLetterSpacing());
        arrayOfObject5[4] = Integer.valueOf(this.mPaint.getFlags());
        arrayOfObject5[5] = this.mPaint.getTextLocales();
        arrayOfObject5[6] = this.mPaint.getTypeface();
        arrayOfObject5[7] = Boolean.valueOf(this.mPaint.isElegantTextHeight());
        arrayOfObject5[8] = this.mTextDir;
        arrayOfObject5[9] = Integer.valueOf(this.mBreakStrategy);
        arrayOfObject5[10] = Integer.valueOf(this.mHyphenationFrequency);
        return ObjectsCompat.hash(arrayOfObject5);
      }
      if (Build.VERSION.SDK_INT >= 21)
      {
        Object[] arrayOfObject4 = new Object[11];
        arrayOfObject4[0] = Float.valueOf(this.mPaint.getTextSize());
        arrayOfObject4[1] = Float.valueOf(this.mPaint.getTextScaleX());
        arrayOfObject4[2] = Float.valueOf(this.mPaint.getTextSkewX());
        arrayOfObject4[3] = Float.valueOf(this.mPaint.getLetterSpacing());
        arrayOfObject4[4] = Integer.valueOf(this.mPaint.getFlags());
        arrayOfObject4[5] = this.mPaint.getTextLocale();
        arrayOfObject4[6] = this.mPaint.getTypeface();
        arrayOfObject4[7] = Boolean.valueOf(this.mPaint.isElegantTextHeight());
        arrayOfObject4[8] = this.mTextDir;
        arrayOfObject4[9] = Integer.valueOf(this.mBreakStrategy);
        arrayOfObject4[10] = Integer.valueOf(this.mHyphenationFrequency);
        return ObjectsCompat.hash(arrayOfObject4);
      }
      if (Build.VERSION.SDK_INT >= 18)
      {
        Object[] arrayOfObject3 = new Object[9];
        arrayOfObject3[0] = Float.valueOf(this.mPaint.getTextSize());
        arrayOfObject3[1] = Float.valueOf(this.mPaint.getTextScaleX());
        arrayOfObject3[2] = Float.valueOf(this.mPaint.getTextSkewX());
        arrayOfObject3[3] = Integer.valueOf(this.mPaint.getFlags());
        arrayOfObject3[4] = this.mPaint.getTextLocale();
        arrayOfObject3[5] = this.mPaint.getTypeface();
        arrayOfObject3[6] = this.mTextDir;
        arrayOfObject3[7] = Integer.valueOf(this.mBreakStrategy);
        arrayOfObject3[8] = Integer.valueOf(this.mHyphenationFrequency);
        return ObjectsCompat.hash(arrayOfObject3);
      }
      if (Build.VERSION.SDK_INT >= 17)
      {
        Object[] arrayOfObject2 = new Object[9];
        arrayOfObject2[0] = Float.valueOf(this.mPaint.getTextSize());
        arrayOfObject2[1] = Float.valueOf(this.mPaint.getTextScaleX());
        arrayOfObject2[2] = Float.valueOf(this.mPaint.getTextSkewX());
        arrayOfObject2[3] = Integer.valueOf(this.mPaint.getFlags());
        arrayOfObject2[4] = this.mPaint.getTextLocale();
        arrayOfObject2[5] = this.mPaint.getTypeface();
        arrayOfObject2[6] = this.mTextDir;
        arrayOfObject2[7] = Integer.valueOf(this.mBreakStrategy);
        arrayOfObject2[8] = Integer.valueOf(this.mHyphenationFrequency);
        return ObjectsCompat.hash(arrayOfObject2);
      }
      Object[] arrayOfObject1 = new Object[8];
      arrayOfObject1[0] = Float.valueOf(this.mPaint.getTextSize());
      arrayOfObject1[1] = Float.valueOf(this.mPaint.getTextScaleX());
      arrayOfObject1[2] = Float.valueOf(this.mPaint.getTextSkewX());
      arrayOfObject1[3] = Integer.valueOf(this.mPaint.getFlags());
      arrayOfObject1[4] = this.mPaint.getTypeface();
      arrayOfObject1[5] = this.mTextDir;
      arrayOfObject1[6] = Integer.valueOf(this.mBreakStrategy);
      arrayOfObject1[7] = Integer.valueOf(this.mHyphenationFrequency);
      return ObjectsCompat.hash(arrayOfObject1);
    }

    public String toString()
    {
      StringBuilder localStringBuilder1 = new StringBuilder("{");
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("textSize=");
      localStringBuilder2.append(this.mPaint.getTextSize());
      localStringBuilder1.append(localStringBuilder2.toString());
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append(", textScaleX=");
      localStringBuilder3.append(this.mPaint.getTextScaleX());
      localStringBuilder1.append(localStringBuilder3.toString());
      StringBuilder localStringBuilder4 = new StringBuilder();
      localStringBuilder4.append(", textSkewX=");
      localStringBuilder4.append(this.mPaint.getTextSkewX());
      localStringBuilder1.append(localStringBuilder4.toString());
      if (Build.VERSION.SDK_INT >= 21)
      {
        StringBuilder localStringBuilder5 = new StringBuilder();
        localStringBuilder5.append(", letterSpacing=");
        localStringBuilder5.append(this.mPaint.getLetterSpacing());
        localStringBuilder1.append(localStringBuilder5.toString());
        StringBuilder localStringBuilder6 = new StringBuilder();
        localStringBuilder6.append(", elegantTextHeight=");
        localStringBuilder6.append(this.mPaint.isElegantTextHeight());
        localStringBuilder1.append(localStringBuilder6.toString());
      }
      if (Build.VERSION.SDK_INT >= 24)
      {
        StringBuilder localStringBuilder7 = new StringBuilder();
        localStringBuilder7.append(", textLocale=");
        localStringBuilder7.append(this.mPaint.getTextLocales());
        localStringBuilder1.append(localStringBuilder7.toString());
      }
      else if (Build.VERSION.SDK_INT >= 17)
      {
        StringBuilder localStringBuilder13 = new StringBuilder();
        localStringBuilder13.append(", textLocale=");
        localStringBuilder13.append(this.mPaint.getTextLocale());
        localStringBuilder1.append(localStringBuilder13.toString());
      }
      StringBuilder localStringBuilder8 = new StringBuilder();
      localStringBuilder8.append(", typeface=");
      localStringBuilder8.append(this.mPaint.getTypeface());
      localStringBuilder1.append(localStringBuilder8.toString());
      if (Build.VERSION.SDK_INT >= 26)
      {
        StringBuilder localStringBuilder9 = new StringBuilder();
        localStringBuilder9.append(", variationSettings=");
        localStringBuilder9.append(this.mPaint.getFontVariationSettings());
        localStringBuilder1.append(localStringBuilder9.toString());
      }
      StringBuilder localStringBuilder10 = new StringBuilder();
      localStringBuilder10.append(", textDir=");
      localStringBuilder10.append(this.mTextDir);
      localStringBuilder1.append(localStringBuilder10.toString());
      StringBuilder localStringBuilder11 = new StringBuilder();
      localStringBuilder11.append(", breakStrategy=");
      localStringBuilder11.append(this.mBreakStrategy);
      localStringBuilder1.append(localStringBuilder11.toString());
      StringBuilder localStringBuilder12 = new StringBuilder();
      localStringBuilder12.append(", hyphenationFrequency=");
      localStringBuilder12.append(this.mHyphenationFrequency);
      localStringBuilder1.append(localStringBuilder12.toString());
      localStringBuilder1.append("}");
      return localStringBuilder1.toString();
    }

    public static class Builder
    {
      private int mBreakStrategy;
      private int mHyphenationFrequency;

      @NonNull
      private final TextPaint mPaint;
      private TextDirectionHeuristic mTextDir;

      public Builder(@NonNull TextPaint paramTextPaint)
      {
        this.mPaint = paramTextPaint;
        if (Build.VERSION.SDK_INT >= 23)
        {
          this.mBreakStrategy = 1;
          this.mHyphenationFrequency = 1;
        }
        else
        {
          this.mHyphenationFrequency = 0;
          this.mBreakStrategy = 0;
        }
        if (Build.VERSION.SDK_INT >= 18)
          this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        else
          this.mTextDir = null;
      }

      @NonNull
      public PrecomputedTextCompat.Params build()
      {
        return new PrecomputedTextCompat.Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
      }

      @RequiresApi(23)
      public Builder setBreakStrategy(int paramInt)
      {
        this.mBreakStrategy = paramInt;
        return this;
      }

      @RequiresApi(23)
      public Builder setHyphenationFrequency(int paramInt)
      {
        this.mHyphenationFrequency = paramInt;
        return this;
      }

      @RequiresApi(18)
      public Builder setTextDirection(@NonNull TextDirectionHeuristic paramTextDirectionHeuristic)
      {
        this.mTextDir = paramTextDirectionHeuristic;
        return this;
      }
    }
  }

  private static class PrecomputedTextFutureTask extends FutureTask<PrecomputedTextCompat>
  {
    PrecomputedTextFutureTask(@NonNull PrecomputedTextCompat.Params paramParams, @NonNull CharSequence paramCharSequence)
    {
      super();
    }

    private static class PrecomputedTextCallback
      implements Callable<PrecomputedTextCompat>
    {
      private PrecomputedTextCompat.Params mParams;
      private CharSequence mText;

      PrecomputedTextCallback(@NonNull PrecomputedTextCompat.Params paramParams, @NonNull CharSequence paramCharSequence)
      {
        this.mParams = paramParams;
        this.mText = paramCharSequence;
      }

      public PrecomputedTextCompat call()
      {
        return PrecomputedTextCompat.create(this.mText, this.mParams);
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.text.PrecomputedTextCompat
 * JD-Core Version:    0.6.1
 */