package com.cndatacom.xjhui.model;

import java.io.Serializable;

public class Device
  implements Serializable
{
  private static final long serialVersionUID = 20170801L;
  private String hostName;
  private int localType;
  private String loginTime;
  private String ticket;
  private int type;

  public String getHostName()
  {
    return this.hostName;
  }

  public int getLocaltype()
  {
    return this.localType;
  }

  public String getLoginTime()
  {
    return this.loginTime;
  }

  public String getTicket()
  {
    return this.ticket;
  }

  public int getType()
  {
    return this.type;
  }

  public void setHostName(String paramString)
  {
    this.hostName = paramString;
  }

  public void setLocaltype(int paramInt)
  {
    this.localType = paramInt;
  }

  public void setLoginTime(String paramString)
  {
    this.loginTime = paramString;
  }

  public void setTicket(String paramString)
  {
    this.ticket = paramString;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.xjhui.model.Device
 * JD-Core Version:    0.6.1
 */