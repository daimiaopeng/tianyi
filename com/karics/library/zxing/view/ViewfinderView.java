package com.karics.library.zxing.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.google.zxing.ResultPoint;
import com.karics.library.zxing.a.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ViewfinderView extends View
{
  private static final int[] a = { 0, 64, 128, 192, 255, 192, 128, 64 };
  private d b;
  private final Paint c = new Paint(1);
  private Bitmap d;
  private final int e;
  private final int f;
  private final int g;
  private final int h;
  private final int i;
  private int j;
  private List<ResultPoint> k;
  private List<ResultPoint> l;

  public ViewfinderView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    Resources localResources = getResources();
    this.e = localResources.getColor(2130837542);
    this.f = localResources.getColor(2130837533);
    this.g = localResources.getColor(2130837541);
    this.h = localResources.getColor(2130837528);
    this.i = localResources.getColor(2130837537);
    this.j = 0;
    this.k = new ArrayList(5);
    this.l = null;
  }

  private void a(Canvas paramCanvas, Rect paramRect)
  {
    this.c.setColor(-1);
    this.c.setStrokeWidth(2.0F);
    this.c.setStyle(Paint.Style.STROKE);
    paramCanvas.drawRect(paramRect, this.c);
    this.c.setColor(getResources().getColor(2130837542));
    this.c.setStyle(Paint.Style.FILL);
    paramCanvas.drawRect(-10 + paramRect.left, paramRect.top, paramRect.left, 30 + paramRect.top, this.c);
    paramCanvas.drawRect(-10 + paramRect.left, -10 + paramRect.top, 30 + paramRect.left, paramRect.top, this.c);
    paramCanvas.drawRect(paramRect.right, paramRect.top, 10 + paramRect.right, 30 + paramRect.top, this.c);
    paramCanvas.drawRect(-30 + paramRect.right, -10 + paramRect.top, 10 + paramRect.right, paramRect.top, this.c);
    paramCanvas.drawRect(-10 + paramRect.left, -30 + paramRect.bottom, paramRect.left, paramRect.bottom, this.c);
    paramCanvas.drawRect(-10 + paramRect.left, paramRect.bottom, 30 + paramRect.left, 10 + paramRect.bottom, this.c);
    paramCanvas.drawRect(paramRect.right, -30 + paramRect.bottom, 10 + paramRect.right, paramRect.bottom, this.c);
    paramCanvas.drawRect(-30 + paramRect.right, paramRect.bottom, 10 + paramRect.right, 10 + paramRect.bottom, this.c);
  }

  private void a(Canvas paramCanvas, Rect paramRect, int paramInt)
  {
    String str1 = getResources().getString(2131296382);
    String str2 = getResources().getString(2131296383);
    this.c.setColor(this.i);
    this.c.setTextSize(45);
    paramCanvas.drawText(str1, (paramInt - (int)this.c.measureText(str1)) / 2, -180 + paramRect.top, this.c);
    paramCanvas.drawText(str2, (paramInt - (int)this.c.measureText(str2)) / 2, 60 + (-180 + paramRect.top), this.c);
  }

  public void a(ResultPoint paramResultPoint)
  {
    synchronized (this.k)
    {
      ???.add(paramResultPoint);
      int m = ???.size();
      if (m > 20)
        ???.subList(0, m - 10).clear();
      return;
    }
  }

  @SuppressLint({"DrawAllocation"})
  public void onDraw(Canvas paramCanvas)
  {
    if (this.b == null)
      return;
    Rect localRect1 = this.b.e();
    Rect localRect2 = this.b.f();
    if ((localRect1 != null) && (localRect2 != null))
    {
      int m = paramCanvas.getWidth();
      int n = paramCanvas.getHeight();
      Paint localPaint = this.c;
      int i1;
      if (this.d != null)
        i1 = this.f;
      else
        i1 = this.e;
      localPaint.setColor(i1);
      float f1 = m;
      paramCanvas.drawRect(0.0F, 0.0F, f1, localRect1.top, this.c);
      paramCanvas.drawRect(0.0F, localRect1.top, localRect1.left, 1 + localRect1.bottom, this.c);
      paramCanvas.drawRect(1 + localRect1.right, localRect1.top, f1, 1 + localRect1.bottom, this.c);
      paramCanvas.drawRect(0.0F, 1 + localRect1.bottom, f1, n, this.c);
      float f2;
      float f3;
      List localList1;
      List localList2;
      int i2;
      int i3;
      if (this.d != null)
      {
        this.c.setAlpha(160);
        paramCanvas.drawBitmap(this.d, null, localRect1, this.c);
      }
      else
      {
        a(paramCanvas, localRect1);
        a(paramCanvas, localRect1, m);
        f2 = localRect1.width() / localRect2.width();
        f3 = localRect1.height() / localRect2.height();
        localList1 = this.k;
        localList2 = this.l;
        i2 = localRect1.left;
        i3 = localRect1.top;
        if (localList1.isEmpty())
        {
          this.l = null;
        }
        else
        {
          this.k = new ArrayList(5);
          this.l = localList1;
          this.c.setAlpha(160);
          this.c.setColor(this.h);
        }
      }
      try
      {
        Iterator localIterator1 = localList1.iterator();
        while (localIterator1.hasNext())
        {
          ResultPoint localResultPoint2 = (ResultPoint)localIterator1.next();
          paramCanvas.drawCircle(i2 + (int)(f2 * localResultPoint2.getX()), i3 + (int)(f3 * localResultPoint2.getY()), 6.0F, this.c);
        }
        if (localList2 != null)
        {
          this.c.setAlpha(80);
          this.c.setColor(this.h);
          try
          {
            Iterator localIterator2 = localList2.iterator();
            while (localIterator2.hasNext())
            {
              ResultPoint localResultPoint1 = (ResultPoint)localIterator2.next();
              paramCanvas.drawCircle(i2 + (int)(f2 * localResultPoint1.getX()), i3 + (int)(f3 * localResultPoint1.getY()), 3.0F, this.c);
            }
          }
          finally
          {
            localObject2 = finally;
            throw localObject2;
          }
        }
        postInvalidateDelayed(80L, -6 + localRect1.left, -6 + localRect1.top, 6 + localRect1.right, 6 + localRect1.bottom);
        return;
      }
      finally
      {
      }
    }
  }

  public void setCameraManager(d paramd)
  {
    this.b = paramd;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.view.ViewfinderView
 * JD-Core Version:    0.6.1
 */