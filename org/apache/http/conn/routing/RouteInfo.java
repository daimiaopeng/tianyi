package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;

@Deprecated
public abstract interface RouteInfo
{
  public abstract int getHopCount();

  public abstract HttpHost getHopTarget(int paramInt);

  public abstract LayerType getLayerType();

  public abstract InetAddress getLocalAddress();

  public abstract HttpHost getProxyHost();

  public abstract HttpHost getTargetHost();

  public abstract TunnelType getTunnelType();

  public abstract boolean isLayered();

  public abstract boolean isSecure();

  public abstract boolean isTunnelled();

  public static enum LayerType
  {
    static
    {
      LayerType[] arrayOfLayerType = new LayerType[2];
      arrayOfLayerType[0] = LAYERED;
      arrayOfLayerType[1] = PLAIN;
    }
  }

  public static enum TunnelType
  {
    static
    {
      TunnelType[] arrayOfTunnelType = new TunnelType[2];
      arrayOfTunnelType[0] = PLAIN;
      arrayOfTunnelType[1] = TUNNELLED;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.routing.RouteInfo
 * JD-Core Version:    0.6.1
 */