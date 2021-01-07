package com.cndatacom.httppap;

import android.content.Context;
import com.cndatacom.dykeylib.PortalCipher;

public class PortalShared
{
  private static PortalShared mInstance;
  private Context mContext = null;
  private PortalInfoMgr mInfoMgr = null;
  private PortalCipher mPortalCipher = null;

  public static PortalShared getInstance()
  {
    if (mInstance == null)
      mInstance = new PortalShared();
    return mInstance;
  }

  public Context getContext()
  {
    return this.mContext;
  }

  public PortalInfoMgr getInfoMgr()
  {
    return this.mInfoMgr;
  }

  public PortalCipher getPortalCipher()
  {
    return this.mPortalCipher;
  }

  public void setContext(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public void setInfoMgr(PortalInfoMgr paramPortalInfoMgr)
  {
    this.mInfoMgr = paramPortalInfoMgr;
  }

  public void setPortalCipher(PortalCipher paramPortalCipher)
  {
    this.mPortalCipher = paramPortalCipher;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.httppap.PortalShared
 * JD-Core Version:    0.6.1
 */