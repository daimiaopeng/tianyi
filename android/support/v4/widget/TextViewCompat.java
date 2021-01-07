package android.support.v4.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.v4.text.PrecomputedTextCompat;
import android.support.v4.text.PrecomputedTextCompat.Params;
import android.support.v4.text.PrecomputedTextCompat.Params.Builder;
import android.support.v4.util.Preconditions;
import android.text.Editable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class TextViewCompat
{
  public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
  public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
  private static final int LINES = 1;
  private static final String LOG_TAG = "TextViewCompat";
  private static Field sMaxModeField;
  private static boolean sMaxModeFieldFetched;
  private static Field sMaximumField;
  private static boolean sMaximumFieldFetched;
  private static Field sMinModeField;
  private static boolean sMinModeFieldFetched;
  private static Field sMinimumField;
  private static boolean sMinimumFieldFetched;

  public static int getAutoSizeMaxTextSize(@NonNull TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 27)
      return paramTextView.getAutoSizeMaxTextSize();
    if ((paramTextView instanceof AutoSizeableTextView))
      return ((AutoSizeableTextView)paramTextView).getAutoSizeMaxTextSize();
    return -1;
  }

  public static int getAutoSizeMinTextSize(@NonNull TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 27)
      return paramTextView.getAutoSizeMinTextSize();
    if ((paramTextView instanceof AutoSizeableTextView))
      return ((AutoSizeableTextView)paramTextView).getAutoSizeMinTextSize();
    return -1;
  }

  public static int getAutoSizeStepGranularity(@NonNull TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 27)
      return paramTextView.getAutoSizeStepGranularity();
    if ((paramTextView instanceof AutoSizeableTextView))
      return ((AutoSizeableTextView)paramTextView).getAutoSizeStepGranularity();
    return -1;
  }

  @NonNull
  public static int[] getAutoSizeTextAvailableSizes(@NonNull TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 27)
      return paramTextView.getAutoSizeTextAvailableSizes();
    if ((paramTextView instanceof AutoSizeableTextView))
      return ((AutoSizeableTextView)paramTextView).getAutoSizeTextAvailableSizes();
    return new int[0];
  }

  public static int getAutoSizeTextType(@NonNull TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 27)
      return paramTextView.getAutoSizeTextType();
    if ((paramTextView instanceof AutoSizeableTextView))
      return ((AutoSizeableTextView)paramTextView).getAutoSizeTextType();
    return 0;
  }

  @NonNull
  public static Drawable[] getCompoundDrawablesRelative(@NonNull TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 18)
      return paramTextView.getCompoundDrawablesRelative();
    if (Build.VERSION.SDK_INT >= 17)
    {
      int i = paramTextView.getLayoutDirection();
      int j = 1;
      if (i != j)
        j = 0;
      Drawable[] arrayOfDrawable = paramTextView.getCompoundDrawables();
      if (j != 0)
      {
        Drawable localDrawable1 = arrayOfDrawable[2];
        Drawable localDrawable2 = arrayOfDrawable[0];
        arrayOfDrawable[0] = localDrawable1;
        arrayOfDrawable[2] = localDrawable2;
      }
      return arrayOfDrawable;
    }
    return paramTextView.getCompoundDrawables();
  }

  public static int getFirstBaselineToTopHeight(@NonNull TextView paramTextView)
  {
    return paramTextView.getPaddingTop() - paramTextView.getPaint().getFontMetricsInt().top;
  }

  public static int getLastBaselineToBottomHeight(@NonNull TextView paramTextView)
  {
    return paramTextView.getPaddingBottom() + paramTextView.getPaint().getFontMetricsInt().bottom;
  }

  public static int getMaxLines(@NonNull TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 16)
      return paramTextView.getMaxLines();
    if (!sMaxModeFieldFetched)
    {
      sMaxModeField = retrieveField("mMaxMode");
      sMaxModeFieldFetched = true;
    }
    if ((sMaxModeField != null) && (retrieveIntFromField(sMaxModeField, paramTextView) == 1))
    {
      if (!sMaximumFieldFetched)
      {
        sMaximumField = retrieveField("mMaximum");
        sMaximumFieldFetched = true;
      }
      if (sMaximumField != null)
        return retrieveIntFromField(sMaximumField, paramTextView);
    }
    return -1;
  }

  public static int getMinLines(@NonNull TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 16)
      return paramTextView.getMinLines();
    if (!sMinModeFieldFetched)
    {
      sMinModeField = retrieveField("mMinMode");
      sMinModeFieldFetched = true;
    }
    if ((sMinModeField != null) && (retrieveIntFromField(sMinModeField, paramTextView) == 1))
    {
      if (!sMinimumFieldFetched)
      {
        sMinimumField = retrieveField("mMinimum");
        sMinimumFieldFetched = true;
      }
      if (sMinimumField != null)
        return retrieveIntFromField(sMinimumField, paramTextView);
    }
    return -1;
  }

  @RequiresApi(18)
  private static int getTextDirection(@NonNull TextDirectionHeuristic paramTextDirectionHeuristic)
  {
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL)
      return 1;
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR)
      return 1;
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR)
      return 2;
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.LTR)
      return 3;
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.RTL)
      return 4;
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.LOCALE)
      return 5;
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR)
      return 6;
    if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL)
      return 7;
    return 1;
  }

  @RequiresApi(18)
  private static TextDirectionHeuristic getTextDirectionHeuristic(@NonNull TextView paramTextView)
  {
    if ((paramTextView.getTransformationMethod() instanceof PasswordTransformationMethod))
      return TextDirectionHeuristics.LTR;
    int i = Build.VERSION.SDK_INT;
    int j = 1;
    if ((i >= 28) && ((0xF & paramTextView.getInputType()) == 3))
    {
      int k = Character.getDirectionality(android.icu.text.DecimalFormatSymbols.getInstance(paramTextView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
      if ((k != j) && (k != 2))
        return TextDirectionHeuristics.LTR;
      return TextDirectionHeuristics.RTL;
    }
    if (paramTextView.getLayoutDirection() != j)
      j = 0;
    switch (paramTextView.getTextDirection())
    {
    default:
      if (j != 0)
        localTextDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
      break;
    case 7:
      return TextDirectionHeuristics.FIRSTSTRONG_RTL;
    case 6:
      return TextDirectionHeuristics.FIRSTSTRONG_LTR;
    case 5:
      return TextDirectionHeuristics.LOCALE;
    case 4:
      return TextDirectionHeuristics.RTL;
    case 3:
      return TextDirectionHeuristics.LTR;
    case 2:
      return TextDirectionHeuristics.ANYRTL_LTR;
    }
    TextDirectionHeuristic localTextDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
    return localTextDirectionHeuristic;
  }

  @NonNull
  public static PrecomputedTextCompat.Params getTextMetricsParams(@NonNull TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return new PrecomputedTextCompat.Params(paramTextView.getTextMetricsParams());
    PrecomputedTextCompat.Params.Builder localBuilder = new PrecomputedTextCompat.Params.Builder(new TextPaint(paramTextView.getPaint()));
    if (Build.VERSION.SDK_INT >= 23)
    {
      localBuilder.setBreakStrategy(paramTextView.getBreakStrategy());
      localBuilder.setHyphenationFrequency(paramTextView.getHyphenationFrequency());
    }
    if (Build.VERSION.SDK_INT >= 18)
      localBuilder.setTextDirection(getTextDirectionHeuristic(paramTextView));
    return localBuilder.build();
  }

  // ERROR //
  private static Field retrieveField(String paramString)
  {
    // Byte code:
    //   0: ldc 38
    //   2: aload_0
    //   3: invokevirtual 250	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   6: astore_1
    //   7: aload_1
    //   8: iconst_1
    //   9: invokevirtual 256	java/lang/reflect/Field:setAccessible	(Z)V
    //   12: goto +49 -> 61
    //   15: aconst_null
    //   16: astore_1
    //   17: goto +4 -> 21
    //   20: pop
    //   21: new 258	java/lang/StringBuilder
    //   24: dup
    //   25: invokespecial 259	java/lang/StringBuilder:<init>	()V
    //   28: astore_2
    //   29: aload_2
    //   30: ldc_w 261
    //   33: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: pop
    //   37: aload_2
    //   38: aload_0
    //   39: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: aload_2
    //   44: ldc_w 267
    //   47: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: pop
    //   51: ldc 14
    //   53: aload_2
    //   54: invokevirtual 271	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: invokestatic 277	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   60: pop
    //   61: aload_1
    //   62: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	7	15	java/lang/NoSuchFieldException
    //   7	12	20	java/lang/NoSuchFieldException
  }

  // ERROR //
  private static int retrieveIntFromField(Field paramField, TextView paramTextView)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 283	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
    //   5: istore 7
    //   7: iload 7
    //   9: ireturn
    //   10: new 258	java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial 259	java/lang/StringBuilder:<init>	()V
    //   17: astore_2
    //   18: aload_2
    //   19: ldc_w 285
    //   22: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: pop
    //   26: aload_2
    //   27: aload_0
    //   28: invokevirtual 288	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   31: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: pop
    //   35: aload_2
    //   36: ldc_w 267
    //   39: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: ldc 14
    //   45: aload_2
    //   46: invokevirtual 271	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: invokestatic 291	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   52: pop
    //   53: iconst_m1
    //   54: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	7	10	java/lang/IllegalAccessException
  }

  public static void setAutoSizeTextTypeUniformWithConfiguration(@NonNull TextView paramTextView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 27)
      paramTextView.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
    else if ((paramTextView instanceof AutoSizeableTextView))
      ((AutoSizeableTextView)paramTextView).setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public static void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull TextView paramTextView, @NonNull int[] paramArrayOfInt, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 27)
      paramTextView.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfInt, paramInt);
    else if ((paramTextView instanceof AutoSizeableTextView))
      ((AutoSizeableTextView)paramTextView).setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfInt, paramInt);
  }

  public static void setAutoSizeTextTypeWithDefaults(@NonNull TextView paramTextView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 27)
      paramTextView.setAutoSizeTextTypeWithDefaults(paramInt);
    else if ((paramTextView instanceof AutoSizeableTextView))
      ((AutoSizeableTextView)paramTextView).setAutoSizeTextTypeWithDefaults(paramInt);
  }

  public static void setCompoundDrawablesRelative(@NonNull TextView paramTextView, @Nullable Drawable paramDrawable1, @Nullable Drawable paramDrawable2, @Nullable Drawable paramDrawable3, @Nullable Drawable paramDrawable4)
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      paramTextView.setCompoundDrawablesRelative(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
    }
    else if (Build.VERSION.SDK_INT >= 17)
    {
      int i = paramTextView.getLayoutDirection();
      int j = 1;
      if (i != j)
        j = 0;
      Drawable localDrawable;
      if (j != 0)
        localDrawable = paramDrawable3;
      else
        localDrawable = paramDrawable1;
      if (j == 0)
        paramDrawable1 = paramDrawable3;
      paramTextView.setCompoundDrawables(localDrawable, paramDrawable2, paramDrawable1, paramDrawable4);
    }
    else
    {
      paramTextView.setCompoundDrawables(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
    }
  }

  public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView paramTextView, @DrawableRes int paramInt1, @DrawableRes int paramInt2, @DrawableRes int paramInt3, @DrawableRes int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      paramTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    else if (Build.VERSION.SDK_INT >= 17)
    {
      int i = paramTextView.getLayoutDirection();
      int j = 1;
      if (i != j)
        j = 0;
      int k;
      if (j != 0)
        k = paramInt3;
      else
        k = paramInt1;
      if (j == 0)
        paramInt1 = paramInt3;
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(k, paramInt2, paramInt1, paramInt4);
    }
    else
    {
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }

  public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView paramTextView, @Nullable Drawable paramDrawable1, @Nullable Drawable paramDrawable2, @Nullable Drawable paramDrawable3, @Nullable Drawable paramDrawable4)
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      paramTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
    }
    else if (Build.VERSION.SDK_INT >= 17)
    {
      int i = paramTextView.getLayoutDirection();
      int j = 1;
      if (i != j)
        j = 0;
      Drawable localDrawable;
      if (j != 0)
        localDrawable = paramDrawable3;
      else
        localDrawable = paramDrawable1;
      if (j == 0)
        paramDrawable1 = paramDrawable3;
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(localDrawable, paramDrawable2, paramDrawable1, paramDrawable4);
    }
    else
    {
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
    }
  }

  public static void setCustomSelectionActionModeCallback(@NonNull TextView paramTextView, @NonNull ActionMode.Callback paramCallback)
  {
    paramTextView.setCustomSelectionActionModeCallback(wrapCustomSelectionActionModeCallback(paramTextView, paramCallback));
  }

  public static void setFirstBaselineToTopHeight(@NonNull TextView paramTextView, @IntRange(from=0L) @Px int paramInt)
  {
    Preconditions.checkArgumentNonnegative(paramInt);
    if (Build.VERSION.SDK_INT >= 28)
    {
      paramTextView.setFirstBaselineToTopHeight(paramInt);
      return;
    }
    Paint.FontMetricsInt localFontMetricsInt = paramTextView.getPaint().getFontMetricsInt();
    int i;
    if ((Build.VERSION.SDK_INT >= 16) && (!paramTextView.getIncludeFontPadding()))
      i = localFontMetricsInt.ascent;
    else
      i = localFontMetricsInt.top;
    if (paramInt > Math.abs(i))
    {
      int j = paramInt - -i;
      paramTextView.setPadding(paramTextView.getPaddingLeft(), j, paramTextView.getPaddingRight(), paramTextView.getPaddingBottom());
    }
  }

  public static void setLastBaselineToBottomHeight(@NonNull TextView paramTextView, @IntRange(from=0L) @Px int paramInt)
  {
    Preconditions.checkArgumentNonnegative(paramInt);
    Paint.FontMetricsInt localFontMetricsInt = paramTextView.getPaint().getFontMetricsInt();
    int i;
    if ((Build.VERSION.SDK_INT >= 16) && (!paramTextView.getIncludeFontPadding()))
      i = localFontMetricsInt.descent;
    else
      i = localFontMetricsInt.bottom;
    if (paramInt > Math.abs(i))
    {
      int j = paramInt - i;
      paramTextView.setPadding(paramTextView.getPaddingLeft(), paramTextView.getPaddingTop(), paramTextView.getPaddingRight(), j);
    }
  }

  public static void setLineHeight(@NonNull TextView paramTextView, @IntRange(from=0L) @Px int paramInt)
  {
    Preconditions.checkArgumentNonnegative(paramInt);
    int i = paramTextView.getPaint().getFontMetricsInt(null);
    if (paramInt != i)
      paramTextView.setLineSpacing(paramInt - i, 1.0F);
  }

  public static void setPrecomputedText(@NonNull TextView paramTextView, @NonNull PrecomputedTextCompat paramPrecomputedTextCompat)
  {
    if (Build.VERSION.SDK_INT >= 28)
    {
      paramTextView.setText(paramPrecomputedTextCompat.getPrecomputedText());
    }
    else
    {
      if (!getTextMetricsParams(paramTextView).equals(paramPrecomputedTextCompat.getParams()))
        throw new IllegalArgumentException("Given text can not be applied to TextView.");
      paramTextView.setText(paramPrecomputedTextCompat);
    }
  }

  public static void setTextAppearance(@NonNull TextView paramTextView, @StyleRes int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23)
      paramTextView.setTextAppearance(paramInt);
    else
      paramTextView.setTextAppearance(paramTextView.getContext(), paramInt);
  }

  public static void setTextMetricsParams(@NonNull TextView paramTextView, @NonNull PrecomputedTextCompat.Params paramParams)
  {
    if (Build.VERSION.SDK_INT >= 18)
      paramTextView.setTextDirection(getTextDirection(paramParams.getTextDirection()));
    if (Build.VERSION.SDK_INT < 23)
    {
      float f = paramParams.getTextPaint().getTextScaleX();
      paramTextView.getPaint().set(paramParams.getTextPaint());
      if (f == paramTextView.getTextScaleX())
        paramTextView.setTextScaleX(1.0F + f / 2.0F);
      paramTextView.setTextScaleX(f);
    }
    else
    {
      paramTextView.getPaint().set(paramParams.getTextPaint());
      paramTextView.setBreakStrategy(paramParams.getBreakStrategy());
      paramTextView.setHyphenationFrequency(paramParams.getHyphenationFrequency());
    }
  }

  @NonNull
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static ActionMode.Callback wrapCustomSelectionActionModeCallback(@NonNull TextView paramTextView, @NonNull ActionMode.Callback paramCallback)
  {
    if ((Build.VERSION.SDK_INT >= 26) && (Build.VERSION.SDK_INT <= 27) && (!(paramCallback instanceof OreoCallback)))
      return new OreoCallback(paramCallback, paramTextView);
    return paramCallback;
  }

  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface AutoSizeTextType
  {
  }

  @RequiresApi(26)
  private static class OreoCallback
    implements ActionMode.Callback
  {
    private static final int MENU_ITEM_ORDER_PROCESS_TEXT_INTENT_ACTIONS_START = 100;
    private final ActionMode.Callback mCallback;
    private boolean mCanUseMenuBuilderReferences;
    private boolean mInitializedMenuBuilderReferences;
    private Class mMenuBuilderClass;
    private Method mMenuBuilderRemoveItemAtMethod;
    private final TextView mTextView;

    OreoCallback(ActionMode.Callback paramCallback, TextView paramTextView)
    {
      this.mCallback = paramCallback;
      this.mTextView = paramTextView;
      this.mInitializedMenuBuilderReferences = false;
    }

    private Intent createProcessTextIntent()
    {
      return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
    }

    private Intent createProcessTextIntentForResolveInfo(ResolveInfo paramResolveInfo, TextView paramTextView)
    {
      return createProcessTextIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", true ^ isEditable(paramTextView)).setClassName(paramResolveInfo.activityInfo.packageName, paramResolveInfo.activityInfo.name);
    }

    private List<ResolveInfo> getSupportedActivities(Context paramContext, PackageManager paramPackageManager)
    {
      ArrayList localArrayList = new ArrayList();
      if (!(paramContext instanceof Activity))
        return localArrayList;
      Iterator localIterator = paramPackageManager.queryIntentActivities(createProcessTextIntent(), 0).iterator();
      while (localIterator.hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
        if (isSupportedActivity(localResolveInfo, paramContext))
          localArrayList.add(localResolveInfo);
      }
      return localArrayList;
    }

    private boolean isEditable(TextView paramTextView)
    {
      boolean bool;
      if (((paramTextView instanceof Editable)) && (paramTextView.onCheckIsTextEditor()) && (paramTextView.isEnabled()))
        bool = true;
      else
        bool = false;
      return bool;
    }

    private boolean isSupportedActivity(ResolveInfo paramResolveInfo, Context paramContext)
    {
      boolean bool1 = paramContext.getPackageName().equals(paramResolveInfo.activityInfo.packageName);
      boolean bool2 = true;
      if (bool1)
        return bool2;
      if (!paramResolveInfo.activityInfo.exported)
        return false;
      if ((paramResolveInfo.activityInfo.permission != null) && (paramContext.checkSelfPermission(paramResolveInfo.activityInfo.permission) != 0))
        bool2 = false;
      return bool2;
    }

    // ERROR //
    private void recomputeProcessTextMenuItems(Menu paramMenu)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 32	android/support/v4/widget/TextViewCompat$OreoCallback:mTextView	Landroid/widget/TextView;
      //   4: invokevirtual 165	android/widget/TextView:getContext	()Landroid/content/Context;
      //   7: astore_2
      //   8: aload_2
      //   9: invokevirtual 169	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   12: astore_3
      //   13: aload_0
      //   14: getfield 34	android/support/v4/widget/TextViewCompat$OreoCallback:mInitializedMenuBuilderReferences	Z
      //   17: ifne +72 -> 89
      //   20: aload_0
      //   21: iconst_1
      //   22: putfield 34	android/support/v4/widget/TextViewCompat$OreoCallback:mInitializedMenuBuilderReferences	Z
      //   25: aload_0
      //   26: ldc 171
      //   28: invokestatic 177	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   31: putfield 179	android/support/v4/widget/TextViewCompat$OreoCallback:mMenuBuilderClass	Ljava/lang/Class;
      //   34: aload_0
      //   35: getfield 179	android/support/v4/widget/TextViewCompat$OreoCallback:mMenuBuilderClass	Ljava/lang/Class;
      //   38: astore 14
      //   40: iconst_1
      //   41: anewarray 173	java/lang/Class
      //   44: astore 15
      //   46: aload 15
      //   48: iconst_0
      //   49: getstatic 184	java/lang/Integer:TYPE	Ljava/lang/Class;
      //   52: aastore
      //   53: aload_0
      //   54: aload 14
      //   56: ldc 186
      //   58: aload 15
      //   60: invokevirtual 190	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   63: putfield 192	android/support/v4/widget/TextViewCompat$OreoCallback:mMenuBuilderRemoveItemAtMethod	Ljava/lang/reflect/Method;
      //   66: aload_0
      //   67: iconst_1
      //   68: putfield 194	android/support/v4/widget/TextViewCompat$OreoCallback:mCanUseMenuBuilderReferences	Z
      //   71: goto +18 -> 89
      //   74: aload_0
      //   75: aconst_null
      //   76: putfield 179	android/support/v4/widget/TextViewCompat$OreoCallback:mMenuBuilderClass	Ljava/lang/Class;
      //   79: aload_0
      //   80: aconst_null
      //   81: putfield 192	android/support/v4/widget/TextViewCompat$OreoCallback:mMenuBuilderRemoveItemAtMethod	Ljava/lang/reflect/Method;
      //   84: aload_0
      //   85: iconst_0
      //   86: putfield 194	android/support/v4/widget/TextViewCompat$OreoCallback:mCanUseMenuBuilderReferences	Z
      //   89: aload_0
      //   90: getfield 194	android/support/v4/widget/TextViewCompat$OreoCallback:mCanUseMenuBuilderReferences	Z
      //   93: ifeq +23 -> 116
      //   96: aload_0
      //   97: getfield 179	android/support/v4/widget/TextViewCompat$OreoCallback:mMenuBuilderClass	Ljava/lang/Class;
      //   100: aload_1
      //   101: invokevirtual 197	java/lang/Class:isInstance	(Ljava/lang/Object;)Z
      //   104: ifeq +12 -> 116
      //   107: aload_0
      //   108: getfield 192	android/support/v4/widget/TextViewCompat$OreoCallback:mMenuBuilderRemoveItemAtMethod	Ljava/lang/reflect/Method;
      //   111: astore 6
      //   113: goto +33 -> 146
      //   116: aload_1
      //   117: invokevirtual 201	java/lang/Object:getClass	()Ljava/lang/Class;
      //   120: astore 4
      //   122: iconst_1
      //   123: anewarray 173	java/lang/Class
      //   126: astore 5
      //   128: aload 5
      //   130: iconst_0
      //   131: getstatic 184	java/lang/Integer:TYPE	Ljava/lang/Class;
      //   134: aastore
      //   135: aload 4
      //   137: ldc 186
      //   139: aload 5
      //   141: invokevirtual 190	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   144: astore 6
      //   146: aload_1
      //   147: invokeinterface 207 1 0
      //   152: iconst_1
      //   153: isub
      //   154: istore 7
      //   156: iload 7
      //   158: iflt +71 -> 229
      //   161: aload_1
      //   162: iload 7
      //   164: invokeinterface 211 2 0
      //   169: astore 11
      //   171: aload 11
      //   173: invokeinterface 216 1 0
      //   178: ifnull +45 -> 223
      //   181: ldc 41
      //   183: aload 11
      //   185: invokeinterface 216 1 0
      //   190: invokevirtual 219	android/content/Intent:getAction	()Ljava/lang/String;
      //   193: invokevirtual 141	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   196: ifeq +27 -> 223
      //   199: iconst_1
      //   200: anewarray 4	java/lang/Object
      //   203: astore 12
      //   205: aload 12
      //   207: iconst_0
      //   208: iload 7
      //   210: invokestatic 223	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   213: aastore
      //   214: aload 6
      //   216: aload_1
      //   217: aload 12
      //   219: invokevirtual 229	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   222: pop
      //   223: iinc 7 255
      //   226: goto -70 -> 156
      //   229: aload_0
      //   230: aload_2
      //   231: aload_3
      //   232: invokespecial 231	android/support/v4/widget/TextViewCompat$OreoCallback:getSupportedActivities	(Landroid/content/Context;Landroid/content/pm/PackageManager;)Ljava/util/List;
      //   235: astore 8
      //   237: iconst_0
      //   238: istore 9
      //   240: iload 9
      //   242: aload 8
      //   244: invokeinterface 232 1 0
      //   249: if_icmpge +63 -> 312
      //   252: aload 8
      //   254: iload 9
      //   256: invokeinterface 236 2 0
      //   261: checkcast 66	android/content/pm/ResolveInfo
      //   264: astore 10
      //   266: aload_1
      //   267: iconst_0
      //   268: iconst_0
      //   269: iload 9
      //   271: bipush 100
      //   273: iadd
      //   274: aload 10
      //   276: aload_3
      //   277: invokevirtual 240	android/content/pm/ResolveInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
      //   280: invokeinterface 243 5 0
      //   285: aload_0
      //   286: aload 10
      //   288: aload_0
      //   289: getfield 32	android/support/v4/widget/TextViewCompat$OreoCallback:mTextView	Landroid/widget/TextView;
      //   292: invokespecial 245	android/support/v4/widget/TextViewCompat$OreoCallback:createProcessTextIntentForResolveInfo	(Landroid/content/pm/ResolveInfo;Landroid/widget/TextView;)Landroid/content/Intent;
      //   295: invokeinterface 249 2 0
      //   300: iconst_1
      //   301: invokeinterface 253 2 0
      //   306: iinc 9 1
      //   309: goto -69 -> 240
      //   312: return
      //   313: return
      //
      // Exception table:
      //   from	to	target	type
      //   25	71	74	java/lang/ClassNotFoundException
      //   25	71	74	java/lang/NoSuchMethodException
      //   89	223	313	java/lang/NoSuchMethodException
      //   89	223	313	java/lang/IllegalAccessException
      //   89	223	313	java/lang/reflect/InvocationTargetException
    }

    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      return this.mCallback.onActionItemClicked(paramActionMode, paramMenuItem);
    }

    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return this.mCallback.onCreateActionMode(paramActionMode, paramMenu);
    }

    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      this.mCallback.onDestroyActionMode(paramActionMode);
    }

    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      recomputeProcessTextMenuItems(paramMenu);
      return this.mCallback.onPrepareActionMode(paramActionMode, paramMenu);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.TextViewCompat
 * JD-Core Version:    0.6.1
 */