package org.apache.commons.httpclient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Hashtable;
import java.util.Locale;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.util.EncodingUtil;

public class URI
  implements Serializable, Cloneable, Comparable
{
  protected static final BitSet IPv4address;
  protected static final BitSet IPv6address;
  protected static final BitSet IPv6reference;
  protected static final BitSet URI_reference;
  protected static final BitSet abs_path;
  protected static final BitSet absoluteURI;
  public static final BitSet allowed_IPv6reference;
  public static final BitSet allowed_abs_path;
  public static final BitSet allowed_authority;
  public static final BitSet allowed_fragment;
  public static final BitSet allowed_host;
  public static final BitSet allowed_opaque_part;
  public static final BitSet allowed_query;
  public static final BitSet allowed_reg_name;
  public static final BitSet allowed_rel_path;
  public static final BitSet allowed_userinfo;
  public static final BitSet allowed_within_authority;
  public static final BitSet allowed_within_path;
  public static final BitSet allowed_within_query;
  public static final BitSet allowed_within_userinfo;
  protected static final BitSet alpha;
  protected static final BitSet alphanum;
  protected static final BitSet authority;
  public static final BitSet control;
  protected static String defaultDocumentCharset;
  protected static String defaultDocumentCharsetByLocale;
  protected static String defaultDocumentCharsetByPlatform;
  protected static String defaultProtocolCharset = "UTF-8";
  public static final BitSet delims;
  protected static final BitSet digit;
  public static final BitSet disallowed_opaque_part;
  public static final BitSet disallowed_rel_path;
  protected static final BitSet domainlabel;
  protected static final BitSet escaped;
  protected static final BitSet fragment;
  protected static final BitSet hex;
  protected static final BitSet hier_part;
  protected static final BitSet host;
  protected static final BitSet hostname;
  protected static final BitSet hostport;
  protected static final BitSet mark;
  protected static final BitSet net_path;
  protected static final BitSet opaque_part;
  protected static final BitSet param;
  protected static final BitSet path;
  protected static final BitSet path_segments;
  protected static final BitSet pchar;
  protected static final BitSet percent;
  protected static final BitSet port;
  protected static final BitSet query;
  protected static final BitSet reg_name;
  protected static final BitSet rel_path;
  protected static final BitSet rel_segment;
  protected static final BitSet relativeURI;
  protected static final BitSet reserved;
  protected static final char[] rootPath;
  protected static final BitSet scheme;
  protected static final BitSet segment;
  static final long serialVersionUID = 604752400577948726L;
  protected static final BitSet server;
  public static final BitSet space;
  protected static final BitSet toplabel;
  protected static final BitSet unreserved;
  public static final BitSet unwise;
  protected static final BitSet uric;
  protected static final BitSet uric_no_slash;
  protected static final BitSet userinfo;
  public static final BitSet within_userinfo;
  protected char[] _authority = null;
  protected char[] _fragment = null;
  protected char[] _host = null;
  protected boolean _is_IPv4address;
  protected boolean _is_IPv6reference;
  protected boolean _is_abs_path;
  protected boolean _is_hier_part;
  protected boolean _is_hostname;
  protected boolean _is_net_path;
  protected boolean _is_opaque_part;
  protected boolean _is_reg_name;
  protected boolean _is_rel_path;
  protected boolean _is_server;
  protected char[] _opaque = null;
  protected char[] _path = null;
  protected int _port = -1;
  protected char[] _query = null;
  protected char[] _scheme = null;
  protected char[] _uri = null;
  protected char[] _userinfo = null;
  protected int hash = 0;
  protected String protocolCharset = null;

  static
  {
    Locale localLocale = Locale.getDefault();
    if (localLocale != null)
    {
      defaultDocumentCharsetByLocale = LocaleToCharsetMap.getCharset(localLocale);
      defaultDocumentCharset = defaultDocumentCharsetByLocale;
    }
    try
    {
      defaultDocumentCharsetByPlatform = System.getProperty("file.encoding");
    }
    catch (SecurityException localSecurityException)
    {
    }
    if (defaultDocumentCharset == null)
      defaultDocumentCharset = defaultDocumentCharsetByPlatform;
    rootPath = new char[] { '/' };
    percent = new BitSet(256);
    percent.set(37);
    digit = new BitSet(256);
    for (int i = 48; i <= 57; i++)
      digit.set(i);
    alpha = new BitSet(256);
    for (int j = 97; j <= 122; j++)
      alpha.set(j);
    for (int k = 65; k <= 90; k++)
      alpha.set(k);
    alphanum = new BitSet(256);
    alphanum.or(alpha);
    alphanum.or(digit);
    hex = new BitSet(256);
    hex.or(digit);
    for (int m = 97; m <= 102; m++)
      hex.set(m);
    for (int n = 65; n <= 70; n++)
      hex.set(n);
    escaped = new BitSet(256);
    escaped.or(percent);
    escaped.or(hex);
    mark = new BitSet(256);
    mark.set(45);
    mark.set(95);
    mark.set(46);
    mark.set(33);
    mark.set(126);
    mark.set(42);
    mark.set(39);
    mark.set(40);
    mark.set(41);
    unreserved = new BitSet(256);
    unreserved.or(alphanum);
    unreserved.or(mark);
    reserved = new BitSet(256);
    reserved.set(59);
    reserved.set(47);
    reserved.set(63);
    reserved.set(58);
    reserved.set(64);
    reserved.set(38);
    reserved.set(61);
    reserved.set(43);
    reserved.set(36);
    reserved.set(44);
    uric = new BitSet(256);
    uric.or(reserved);
    uric.or(unreserved);
    uric.or(escaped);
    fragment = uric;
    query = uric;
    pchar = new BitSet(256);
    pchar.or(unreserved);
    pchar.or(escaped);
    pchar.set(58);
    pchar.set(64);
    pchar.set(38);
    pchar.set(61);
    pchar.set(43);
    pchar.set(36);
    pchar.set(44);
    param = pchar;
    segment = new BitSet(256);
    segment.or(pchar);
    segment.set(59);
    segment.or(param);
    path_segments = new BitSet(256);
    path_segments.set(47);
    path_segments.or(segment);
    abs_path = new BitSet(256);
    abs_path.set(47);
    abs_path.or(path_segments);
    uric_no_slash = new BitSet(256);
    uric_no_slash.or(unreserved);
    uric_no_slash.or(escaped);
    uric_no_slash.set(59);
    uric_no_slash.set(63);
    uric_no_slash.set(59);
    uric_no_slash.set(64);
    uric_no_slash.set(38);
    uric_no_slash.set(61);
    uric_no_slash.set(43);
    uric_no_slash.set(36);
    uric_no_slash.set(44);
    opaque_part = new BitSet(256);
    opaque_part.or(uric_no_slash);
    opaque_part.or(uric);
    path = new BitSet(256);
    path.or(abs_path);
    path.or(opaque_part);
    port = digit;
    IPv4address = new BitSet(256);
    IPv4address.or(digit);
    IPv4address.set(46);
    IPv6address = new BitSet(256);
    IPv6address.or(hex);
    IPv6address.set(58);
    IPv6address.or(IPv4address);
    IPv6reference = new BitSet(256);
    IPv6reference.set(91);
    IPv6reference.or(IPv6address);
    IPv6reference.set(93);
    toplabel = new BitSet(256);
    toplabel.or(alphanum);
    toplabel.set(45);
    domainlabel = toplabel;
    hostname = new BitSet(256);
    hostname.or(toplabel);
    hostname.set(46);
    host = new BitSet(256);
    host.or(hostname);
    host.or(IPv6reference);
    hostport = new BitSet(256);
    hostport.or(host);
    hostport.set(58);
    hostport.or(port);
    userinfo = new BitSet(256);
    userinfo.or(unreserved);
    userinfo.or(escaped);
    userinfo.set(59);
    userinfo.set(58);
    userinfo.set(38);
    userinfo.set(61);
    userinfo.set(43);
    userinfo.set(36);
    userinfo.set(44);
    within_userinfo = new BitSet(256);
    within_userinfo.or(userinfo);
    within_userinfo.clear(59);
    within_userinfo.clear(58);
    within_userinfo.clear(64);
    within_userinfo.clear(63);
    within_userinfo.clear(47);
    server = new BitSet(256);
    server.or(userinfo);
    server.set(64);
    server.or(hostport);
    reg_name = new BitSet(256);
    reg_name.or(unreserved);
    reg_name.or(escaped);
    reg_name.set(36);
    reg_name.set(44);
    reg_name.set(59);
    reg_name.set(58);
    reg_name.set(64);
    reg_name.set(38);
    reg_name.set(61);
    reg_name.set(43);
    authority = new BitSet(256);
    authority.or(server);
    authority.or(reg_name);
    scheme = new BitSet(256);
    scheme.or(alpha);
    scheme.or(digit);
    scheme.set(43);
    scheme.set(45);
    scheme.set(46);
    rel_segment = new BitSet(256);
    rel_segment.or(unreserved);
    rel_segment.or(escaped);
    rel_segment.set(59);
    rel_segment.set(64);
    rel_segment.set(38);
    rel_segment.set(61);
    rel_segment.set(43);
    rel_segment.set(36);
    rel_segment.set(44);
    rel_path = new BitSet(256);
    rel_path.or(rel_segment);
    rel_path.or(abs_path);
    net_path = new BitSet(256);
    net_path.set(47);
    net_path.or(authority);
    net_path.or(abs_path);
    hier_part = new BitSet(256);
    hier_part.or(net_path);
    hier_part.or(abs_path);
    hier_part.or(query);
    relativeURI = new BitSet(256);
    relativeURI.or(net_path);
    relativeURI.or(abs_path);
    relativeURI.or(rel_path);
    relativeURI.or(query);
    absoluteURI = new BitSet(256);
    absoluteURI.or(scheme);
    absoluteURI.set(58);
    absoluteURI.or(hier_part);
    absoluteURI.or(opaque_part);
    URI_reference = new BitSet(256);
    URI_reference.or(absoluteURI);
    URI_reference.or(relativeURI);
    URI_reference.set(35);
    URI_reference.or(fragment);
    control = new BitSet(256);
    for (int i1 = 0; i1 <= 31; i1++)
      control.set(i1);
    control.set(127);
    space = new BitSet(256);
    space.set(32);
    delims = new BitSet(256);
    delims.set(60);
    delims.set(62);
    delims.set(35);
    delims.set(37);
    delims.set(34);
    unwise = new BitSet(256);
    unwise.set(123);
    unwise.set(125);
    unwise.set(124);
    unwise.set(92);
    unwise.set(94);
    unwise.set(91);
    unwise.set(93);
    unwise.set(96);
    disallowed_rel_path = new BitSet(256);
    disallowed_rel_path.or(uric);
    disallowed_rel_path.andNot(rel_path);
    disallowed_opaque_part = new BitSet(256);
    disallowed_opaque_part.or(uric);
    disallowed_opaque_part.andNot(opaque_part);
    allowed_authority = new BitSet(256);
    allowed_authority.or(authority);
    allowed_authority.clear(37);
    allowed_opaque_part = new BitSet(256);
    allowed_opaque_part.or(opaque_part);
    allowed_opaque_part.clear(37);
    allowed_reg_name = new BitSet(256);
    allowed_reg_name.or(reg_name);
    allowed_reg_name.clear(37);
    allowed_userinfo = new BitSet(256);
    allowed_userinfo.or(userinfo);
    allowed_userinfo.clear(37);
    allowed_within_userinfo = new BitSet(256);
    allowed_within_userinfo.or(within_userinfo);
    allowed_within_userinfo.clear(37);
    allowed_IPv6reference = new BitSet(256);
    allowed_IPv6reference.or(IPv6reference);
    allowed_IPv6reference.clear(91);
    allowed_IPv6reference.clear(93);
    allowed_host = new BitSet(256);
    allowed_host.or(hostname);
    allowed_host.or(allowed_IPv6reference);
    allowed_within_authority = new BitSet(256);
    allowed_within_authority.or(server);
    allowed_within_authority.or(reg_name);
    allowed_within_authority.clear(59);
    allowed_within_authority.clear(58);
    allowed_within_authority.clear(64);
    allowed_within_authority.clear(63);
    allowed_within_authority.clear(47);
    allowed_abs_path = new BitSet(256);
    allowed_abs_path.or(abs_path);
    allowed_abs_path.andNot(percent);
    allowed_abs_path.clear(43);
    allowed_rel_path = new BitSet(256);
    allowed_rel_path.or(rel_path);
    allowed_rel_path.clear(37);
    allowed_rel_path.clear(43);
    allowed_within_path = new BitSet(256);
    allowed_within_path.or(abs_path);
    allowed_within_path.clear(47);
    allowed_within_path.clear(59);
    allowed_within_path.clear(61);
    allowed_within_path.clear(63);
    allowed_query = new BitSet(256);
    allowed_query.or(uric);
    allowed_query.clear(37);
    allowed_within_query = new BitSet(256);
    allowed_within_query.or(allowed_query);
    allowed_within_query.andNot(reserved);
    allowed_fragment = new BitSet(256);
    allowed_fragment.or(uric);
    allowed_fragment.clear(37);
  }

  protected URI()
  {
  }

  public URI(String paramString)
  {
    parseUriReference(paramString, false);
  }

  public URI(String paramString1, String paramString2)
  {
    this.protocolCharset = paramString2;
    parseUriReference(paramString1, false);
  }

  public URI(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null)
      throw new URIException(1, "scheme required");
    char[] arrayOfChar1 = paramString1.toLowerCase().toCharArray();
    if (validate(arrayOfChar1, scheme))
    {
      this._scheme = arrayOfChar1;
      this._opaque = encode(paramString2, allowed_opaque_part, getProtocolCharset());
      this._is_opaque_part = true;
      char[] arrayOfChar2;
      if (paramString3 == null)
        arrayOfChar2 = null;
      else
        arrayOfChar2 = paramString3.toCharArray();
      this._fragment = arrayOfChar2;
      setURI();
      return;
    }
    throw new URIException(1, "incorrect scheme");
  }

  public URI(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    this(paramString1, paramString2, paramString3, paramInt, null, null, null);
  }

  public URI(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    this(paramString1, paramString2, paramString3, paramInt, paramString4, null, null);
  }

  public URI(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5)
  {
    this(paramString1, paramString2, paramString3, paramInt, paramString4, paramString5, null);
  }

  public URI(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5, String paramString6)
  {
    this(paramString1, str4, paramString4, paramString5, paramString6);
  }

  public URI(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this(paramString1, paramString2, paramString3, null, paramString4);
  }

  public URI(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramString1 != null)
    {
      localStringBuffer.append(paramString1);
      localStringBuffer.append(':');
    }
    if (paramString2 != null)
    {
      localStringBuffer.append("//");
      localStringBuffer.append(paramString2);
    }
    if (paramString3 != null)
    {
      if (((paramString1 != null) || (paramString2 != null)) && (!paramString3.startsWith("/")))
        throw new URIException(1, "abs_path requested");
      localStringBuffer.append(paramString3);
    }
    if (paramString4 != null)
    {
      localStringBuffer.append('?');
      localStringBuffer.append(paramString4);
    }
    if (paramString5 != null)
    {
      localStringBuffer.append('#');
      localStringBuffer.append(paramString5);
    }
    parseUriReference(localStringBuffer.toString(), false);
  }

  public URI(String paramString, boolean paramBoolean)
  {
    parseUriReference(paramString, paramBoolean);
  }

  public URI(String paramString1, boolean paramBoolean, String paramString2)
  {
    this.protocolCharset = paramString2;
    parseUriReference(paramString1, paramBoolean);
  }

  public URI(URI paramURI, String paramString)
  {
    this(paramURI, new URI(paramString));
  }

  public URI(URI paramURI, String paramString, boolean paramBoolean)
  {
    this(paramURI, new URI(paramString, paramBoolean));
  }

  public URI(URI paramURI1, URI paramURI2)
  {
    if (paramURI1._scheme == null)
      throw new URIException(1, "base URI required");
    if (paramURI1._scheme != null)
    {
      this._scheme = paramURI1._scheme;
      this._authority = paramURI1._authority;
      this._is_net_path = paramURI1._is_net_path;
    }
    if ((!paramURI1._is_opaque_part) && (!paramURI2._is_opaque_part))
    {
      boolean bool3 = Arrays.equals(paramURI1._scheme, paramURI2._scheme);
      if ((paramURI2._scheme != null) && ((!bool3) || (paramURI2._authority != null)))
      {
        this._scheme = paramURI2._scheme;
        this._is_net_path = paramURI2._is_net_path;
        this._authority = paramURI2._authority;
        if (paramURI2._is_server)
        {
          this._is_server = paramURI2._is_server;
          this._userinfo = paramURI2._userinfo;
          this._host = paramURI2._host;
          this._port = paramURI2._port;
        }
        else if (paramURI2._is_reg_name)
        {
          this._is_reg_name = paramURI2._is_reg_name;
        }
        this._is_abs_path = paramURI2._is_abs_path;
        this._is_rel_path = paramURI2._is_rel_path;
        this._path = paramURI2._path;
      }
      else if ((paramURI1._authority != null) && (paramURI2._scheme == null))
      {
        this._is_net_path = paramURI1._is_net_path;
        this._authority = paramURI1._authority;
        if (paramURI1._is_server)
        {
          this._is_server = paramURI1._is_server;
          this._userinfo = paramURI1._userinfo;
          this._host = paramURI1._host;
          this._port = paramURI1._port;
        }
        else if (paramURI1._is_reg_name)
        {
          this._is_reg_name = paramURI1._is_reg_name;
        }
      }
      if (paramURI2._authority != null)
      {
        this._is_net_path = paramURI2._is_net_path;
        this._authority = paramURI2._authority;
        if (paramURI2._is_server)
        {
          this._is_server = paramURI2._is_server;
          this._userinfo = paramURI2._userinfo;
          this._host = paramURI2._host;
          this._port = paramURI2._port;
        }
        else if (paramURI2._is_reg_name)
        {
          this._is_reg_name = paramURI2._is_reg_name;
        }
        this._is_abs_path = paramURI2._is_abs_path;
        this._is_rel_path = paramURI2._is_rel_path;
        this._path = paramURI2._path;
      }
      if ((paramURI2._authority == null) && ((paramURI2._scheme == null) || (bool3)))
        if (((paramURI2._path == null) || (paramURI2._path.length == 0)) && (paramURI2._query == null))
        {
          this._path = paramURI1._path;
          this._query = paramURI1._query;
        }
        else
        {
          this._path = resolvePath(paramURI1._path, paramURI2._path);
        }
      if (paramURI2._query != null)
        this._query = paramURI2._query;
      if (paramURI2._fragment != null)
        this._fragment = paramURI2._fragment;
      setURI();
      parseUriReference(new String(this._uri), true);
      return;
    }
    this._scheme = paramURI1._scheme;
    boolean bool1;
    if (!paramURI1._is_opaque_part)
    {
      boolean bool2 = paramURI2._is_opaque_part;
      bool1 = false;
      if (!bool2);
    }
    else
    {
      bool1 = true;
    }
    this._is_opaque_part = bool1;
    this._opaque = paramURI2._opaque;
    this._fragment = paramURI2._fragment;
    setURI();
  }

  public URI(char[] paramArrayOfChar)
  {
    parseUriReference(new String(paramArrayOfChar), true);
  }

  public URI(char[] paramArrayOfChar, String paramString)
  {
    this.protocolCharset = paramString;
    parseUriReference(new String(paramArrayOfChar), true);
  }

  protected static String decode(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Component array of chars may not be null");
    try
    {
      byte[] arrayOfByte = URLCodec.decodeUrl(EncodingUtil.getAsciiBytes(paramString1));
      return EncodingUtil.getString(arrayOfByte, paramString2);
    }
    catch (DecoderException localDecoderException)
    {
      throw new URIException(localDecoderException.getMessage());
    }
  }

  protected static String decode(char[] paramArrayOfChar, String paramString)
  {
    if (paramArrayOfChar == null)
      throw new IllegalArgumentException("Component array of chars may not be null");
    return decode(new String(paramArrayOfChar), paramString);
  }

  protected static char[] encode(String paramString1, BitSet paramBitSet, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Original string may not be null");
    if (paramBitSet == null)
      throw new IllegalArgumentException("Allowed bitset may not be null");
    return EncodingUtil.getAsciiString(URLCodec.encodeUrl(paramBitSet, EncodingUtil.getBytes(paramString1, paramString2))).toCharArray();
  }

  public static String getDefaultDocumentCharset()
  {
    return defaultDocumentCharset;
  }

  public static String getDefaultDocumentCharsetByLocale()
  {
    return defaultDocumentCharsetByLocale;
  }

  public static String getDefaultDocumentCharsetByPlatform()
  {
    return defaultDocumentCharsetByPlatform;
  }

  public static String getDefaultProtocolCharset()
  {
    return defaultProtocolCharset;
  }

  private void readObject(ObjectInputStream paramObjectInputStream)
  {
    paramObjectInputStream.defaultReadObject();
  }

  public static void setDefaultDocumentCharset(String paramString)
  {
    defaultDocumentCharset = paramString;
    throw new DefaultCharsetChanged(2, "the default document charset changed");
  }

  public static void setDefaultProtocolCharset(String paramString)
  {
    defaultProtocolCharset = paramString;
    throw new DefaultCharsetChanged(1, "the default protocol charset changed");
  }

  private void writeObject(ObjectOutputStream paramObjectOutputStream)
  {
    paramObjectOutputStream.defaultWriteObject();
  }

  public Object clone()
  {
    try
    {
      URI localURI = (URI)super.clone();
      localURI._uri = this._uri;
      localURI._scheme = this._scheme;
      localURI._opaque = this._opaque;
      localURI._authority = this._authority;
      localURI._userinfo = this._userinfo;
      localURI._host = this._host;
      localURI._port = this._port;
      localURI._path = this._path;
      localURI._query = this._query;
      localURI._fragment = this._fragment;
      localURI.protocolCharset = this.protocolCharset;
      localURI._is_hier_part = this._is_hier_part;
      localURI._is_opaque_part = this._is_opaque_part;
      localURI._is_net_path = this._is_net_path;
      localURI._is_abs_path = this._is_abs_path;
      localURI._is_rel_path = this._is_rel_path;
      localURI._is_reg_name = this._is_reg_name;
      localURI._is_server = this._is_server;
      localURI._is_hostname = this._is_hostname;
      localURI._is_IPv4address = this._is_IPv4address;
      localURI._is_IPv6reference = this._is_IPv6reference;
      return localURI;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int compareTo(Object paramObject)
  {
    URI localURI = (URI)paramObject;
    if (!equals(this._authority, localURI.getRawAuthority()))
      return -1;
    return toString().compareTo(localURI.toString());
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof URI))
      return false;
    URI localURI = (URI)paramObject;
    if (!equals(this._scheme, localURI._scheme))
      return false;
    if (!equals(this._opaque, localURI._opaque))
      return false;
    if (!equals(this._authority, localURI._authority))
      return false;
    if (!equals(this._path, localURI._path))
      return false;
    if (!equals(this._query, localURI._query))
      return false;
    return equals(this._fragment, localURI._fragment);
  }

  protected boolean equals(char[] paramArrayOfChar1, char[] paramArrayOfChar2)
  {
    if ((paramArrayOfChar1 == null) && (paramArrayOfChar2 == null))
      return true;
    if ((paramArrayOfChar1 != null) && (paramArrayOfChar2 != null))
    {
      if (paramArrayOfChar1.length != paramArrayOfChar2.length)
        return false;
      for (int i = 0; i < paramArrayOfChar1.length; i++)
        if (paramArrayOfChar1[i] != paramArrayOfChar2[i])
          return false;
      return true;
    }
    return false;
  }

  public String getAboveHierPath()
  {
    char[] arrayOfChar = getRawAboveHierPath();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = decode(arrayOfChar, getProtocolCharset());
    return str;
  }

  public String getAuthority()
  {
    String str;
    if (this._authority == null)
      str = null;
    else
      str = decode(this._authority, getProtocolCharset());
    return str;
  }

  public String getCurrentHierPath()
  {
    char[] arrayOfChar = getRawCurrentHierPath();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = decode(arrayOfChar, getProtocolCharset());
    return str;
  }

  public String getEscapedAboveHierPath()
  {
    char[] arrayOfChar = getRawAboveHierPath();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = new String(arrayOfChar);
    return str;
  }

  public String getEscapedAuthority()
  {
    String str;
    if (this._authority == null)
      str = null;
    else
      str = new String(this._authority);
    return str;
  }

  public String getEscapedCurrentHierPath()
  {
    char[] arrayOfChar = getRawCurrentHierPath();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = new String(arrayOfChar);
    return str;
  }

  public String getEscapedFragment()
  {
    String str;
    if (this._fragment == null)
      str = null;
    else
      str = new String(this._fragment);
    return str;
  }

  public String getEscapedName()
  {
    char[] arrayOfChar = getRawName();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = new String(arrayOfChar);
    return str;
  }

  public String getEscapedPath()
  {
    char[] arrayOfChar = getRawPath();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = new String(arrayOfChar);
    return str;
  }

  public String getEscapedPathQuery()
  {
    char[] arrayOfChar = getRawPathQuery();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = new String(arrayOfChar);
    return str;
  }

  public String getEscapedQuery()
  {
    String str;
    if (this._query == null)
      str = null;
    else
      str = new String(this._query);
    return str;
  }

  public String getEscapedURI()
  {
    String str;
    if (this._uri == null)
      str = null;
    else
      str = new String(this._uri);
    return str;
  }

  public String getEscapedURIReference()
  {
    char[] arrayOfChar = getRawURIReference();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = new String(arrayOfChar);
    return str;
  }

  public String getEscapedUserinfo()
  {
    String str;
    if (this._userinfo == null)
      str = null;
    else
      str = new String(this._userinfo);
    return str;
  }

  public String getFragment()
  {
    String str;
    if (this._fragment == null)
      str = null;
    else
      str = decode(this._fragment, getProtocolCharset());
    return str;
  }

  public String getHost()
  {
    if (this._host != null)
      return decode(this._host, getProtocolCharset());
    return null;
  }

  public String getName()
  {
    String str;
    if (getRawName() == null)
      str = null;
    else
      str = decode(getRawName(), getProtocolCharset());
    return str;
  }

  public String getPath()
  {
    char[] arrayOfChar = getRawPath();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = decode(arrayOfChar, getProtocolCharset());
    return str;
  }

  public String getPathQuery()
  {
    char[] arrayOfChar = getRawPathQuery();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = decode(arrayOfChar, getProtocolCharset());
    return str;
  }

  public int getPort()
  {
    return this._port;
  }

  public String getProtocolCharset()
  {
    String str;
    if (this.protocolCharset != null)
      str = this.protocolCharset;
    else
      str = defaultProtocolCharset;
    return str;
  }

  public String getQuery()
  {
    String str;
    if (this._query == null)
      str = null;
    else
      str = decode(this._query, getProtocolCharset());
    return str;
  }

  public char[] getRawAboveHierPath()
  {
    char[] arrayOfChar1 = getRawCurrentHierPath();
    char[] arrayOfChar2;
    if (arrayOfChar1 == null)
      arrayOfChar2 = null;
    else
      arrayOfChar2 = getRawCurrentHierPath(arrayOfChar1);
    return arrayOfChar2;
  }

  public char[] getRawAuthority()
  {
    return this._authority;
  }

  public char[] getRawCurrentHierPath()
  {
    char[] arrayOfChar;
    if (this._path == null)
      arrayOfChar = null;
    else
      arrayOfChar = getRawCurrentHierPath(this._path);
    return arrayOfChar;
  }

  protected char[] getRawCurrentHierPath(char[] paramArrayOfChar)
  {
    if (this._is_opaque_part)
      throw new URIException(1, "no hierarchy level");
    if (paramArrayOfChar == null)
      throw new URIException(1, "empty path");
    String str = new String(paramArrayOfChar);
    int i = str.indexOf('/');
    int j = str.lastIndexOf('/');
    if (j == 0)
      return rootPath;
    if ((i != j) && (j != -1))
      return str.substring(0, j).toCharArray();
    return paramArrayOfChar;
  }

  public char[] getRawFragment()
  {
    return this._fragment;
  }

  public char[] getRawHost()
  {
    return this._host;
  }

  public char[] getRawName()
  {
    if (this._path == null)
      return null;
    for (int i = -1 + this._path.length; i >= 0; i--)
      if (this._path[i] == '/')
      {
        j = i + 1;
        break label47;
      }
    int j = 0;
    label47: int k = this._path.length - j;
    char[] arrayOfChar = new char[k];
    System.arraycopy(this._path, j, arrayOfChar, 0, k);
    return arrayOfChar;
  }

  public char[] getRawPath()
  {
    char[] arrayOfChar;
    if (this._is_opaque_part)
      arrayOfChar = this._opaque;
    else
      arrayOfChar = this._path;
    return arrayOfChar;
  }

  public char[] getRawPathQuery()
  {
    if ((this._path == null) && (this._query == null))
      return null;
    StringBuffer localStringBuffer = new StringBuffer();
    if (this._path != null)
      localStringBuffer.append(this._path);
    if (this._query != null)
    {
      localStringBuffer.append('?');
      localStringBuffer.append(this._query);
    }
    return localStringBuffer.toString().toCharArray();
  }

  public char[] getRawQuery()
  {
    return this._query;
  }

  public char[] getRawScheme()
  {
    return this._scheme;
  }

  public char[] getRawURI()
  {
    return this._uri;
  }

  public char[] getRawURIReference()
  {
    if (this._fragment == null)
      return this._uri;
    if (this._uri == null)
      return this._fragment;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(new String(this._uri));
    localStringBuffer.append("#");
    localStringBuffer.append(new String(this._fragment));
    return localStringBuffer.toString().toCharArray();
  }

  public char[] getRawUserinfo()
  {
    return this._userinfo;
  }

  public String getScheme()
  {
    String str;
    if (this._scheme == null)
      str = null;
    else
      str = new String(this._scheme);
    return str;
  }

  public String getURI()
  {
    String str;
    if (this._uri == null)
      str = null;
    else
      str = decode(this._uri, getProtocolCharset());
    return str;
  }

  public String getURIReference()
  {
    char[] arrayOfChar = getRawURIReference();
    String str;
    if (arrayOfChar == null)
      str = null;
    else
      str = decode(arrayOfChar, getProtocolCharset());
    return str;
  }

  public String getUserinfo()
  {
    String str;
    if (this._userinfo == null)
      str = null;
    else
      str = decode(this._userinfo, getProtocolCharset());
    return str;
  }

  public boolean hasAuthority()
  {
    boolean bool;
    if ((this._authority == null) && (!this._is_net_path))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public boolean hasFragment()
  {
    boolean bool;
    if (this._fragment != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean hasQuery()
  {
    boolean bool;
    if (this._query != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean hasUserinfo()
  {
    boolean bool;
    if (this._userinfo != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public int hashCode()
  {
    if (this.hash == 0)
    {
      char[] arrayOfChar1 = this._uri;
      int i = 0;
      if (arrayOfChar1 != null)
      {
        int k = arrayOfChar1.length;
        for (int m = 0; m < k; m++)
          this.hash = (31 * this.hash + arrayOfChar1[m]);
      }
      char[] arrayOfChar2 = this._fragment;
      if (arrayOfChar2 != null)
      {
        int j = arrayOfChar2.length;
        while (i < j)
        {
          this.hash = (31 * this.hash + arrayOfChar2[i]);
          i++;
        }
      }
    }
    return this.hash;
  }

  protected int indexFirstOf(String paramString1, String paramString2)
  {
    return indexFirstOf(paramString1, paramString2, -1);
  }

  protected int indexFirstOf(String paramString1, String paramString2, int paramInt)
  {
    int i = -1;
    if ((paramString1 != null) && (paramString1.length() != 0))
    {
      if ((paramString2 != null) && (paramString2.length() != 0))
      {
        int j = 0;
        if (paramInt < 0)
          paramInt = 0;
        else if (paramInt > paramString1.length())
          return i;
        int k = paramString1.length();
        char[] arrayOfChar = paramString2.toCharArray();
        while (j < arrayOfChar.length)
        {
          int m = paramString1.indexOf(arrayOfChar[j], paramInt);
          if ((m >= 0) && (m < k))
            k = m;
          j++;
        }
        if (k != paramString1.length())
          i = k;
        return i;
      }
      return i;
    }
    return i;
  }

  protected int indexFirstOf(char[] paramArrayOfChar, char paramChar)
  {
    return indexFirstOf(paramArrayOfChar, paramChar, 0);
  }

  protected int indexFirstOf(char[] paramArrayOfChar, char paramChar, int paramInt)
  {
    if ((paramArrayOfChar != null) && (paramArrayOfChar.length != 0))
    {
      if (paramInt < 0)
        paramInt = 0;
      else if (paramInt > paramArrayOfChar.length)
        return -1;
      while (paramInt < paramArrayOfChar.length)
      {
        if (paramArrayOfChar[paramInt] == paramChar)
          return paramInt;
        paramInt++;
      }
      return -1;
    }
    return -1;
  }

  public boolean isAbsPath()
  {
    return this._is_abs_path;
  }

  public boolean isAbsoluteURI()
  {
    boolean bool;
    if (this._scheme != null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isHierPart()
  {
    return this._is_hier_part;
  }

  public boolean isHostname()
  {
    return this._is_hostname;
  }

  public boolean isIPv4address()
  {
    return this._is_IPv4address;
  }

  public boolean isIPv6reference()
  {
    return this._is_IPv6reference;
  }

  public boolean isNetPath()
  {
    boolean bool;
    if ((!this._is_net_path) && (this._authority == null))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public boolean isOpaquePart()
  {
    return this._is_opaque_part;
  }

  public boolean isRegName()
  {
    return this._is_reg_name;
  }

  public boolean isRelPath()
  {
    return this._is_rel_path;
  }

  public boolean isRelativeURI()
  {
    boolean bool;
    if (this._scheme == null)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public boolean isServer()
  {
    return this._is_server;
  }

  public void normalize()
  {
    if (isAbsPath())
    {
      this._path = normalize(this._path);
      setURI();
    }
  }

  protected char[] normalize(char[] paramArrayOfChar)
  {
    if (paramArrayOfChar == null)
      return null;
    String str = new String(paramArrayOfChar);
    if (str.startsWith("./"))
      str = str.substring(1);
    else if (str.startsWith("../"))
      str = str.substring(2);
    else if (!str.startsWith(".."));
    StringBuffer localStringBuffer1;
    for (str = str.substring(2); ; str = localStringBuffer1.toString())
    {
      int i = str.indexOf("/./");
      if (i == -1)
        break;
      localStringBuffer1 = new StringBuffer();
      localStringBuffer1.append(str.substring(0, i));
      localStringBuffer1.append(str.substring(i + 2));
    }
    if (str.endsWith("/."))
      str = str.substring(0, str.length() - 1);
    int j = 0;
    while (true)
    {
      int k = str.indexOf("/../", j);
      if (k == -1)
        break;
      int i1 = str.lastIndexOf('/', k - 1);
      if (i1 >= 0)
      {
        StringBuffer localStringBuffer2 = new StringBuffer();
        localStringBuffer2.append(str.substring(0, i1));
        localStringBuffer2.append(str.substring(k + 3));
        str = localStringBuffer2.toString();
      }
      else
      {
        j = k + 3;
      }
    }
    int n;
    if (str.endsWith("/.."))
    {
      n = str.lastIndexOf('/', -4 + str.length());
      if (n < 0);
    }
    int m;
    for (str = str.substring(0, n + 1); ; str = str.substring(m + 3))
    {
      m = str.indexOf("/../");
      if ((m == -1) || (str.lastIndexOf('/', m - 1) >= 0))
        break;
    }
    if ((str.endsWith("/..")) && (str.lastIndexOf('/', -4 + str.length()) < 0))
      str = "/";
    return str.toCharArray();
  }

  // ERROR //
  protected void parseAuthority(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield 518	org/apache/commons/httpclient/URI:_is_IPv6reference	Z
    //   5: aload_0
    //   6: iconst_0
    //   7: putfield 516	org/apache/commons/httpclient/URI:_is_IPv4address	Z
    //   10: aload_0
    //   11: iconst_0
    //   12: putfield 514	org/apache/commons/httpclient/URI:_is_hostname	Z
    //   15: aload_0
    //   16: iconst_0
    //   17: putfield 415	org/apache/commons/httpclient/URI:_is_server	Z
    //   20: aload_0
    //   21: iconst_0
    //   22: putfield 417	org/apache/commons/httpclient/URI:_is_reg_name	Z
    //   25: aload_0
    //   26: invokevirtual 342	org/apache/commons/httpclient/URI:getProtocolCharset	()Ljava/lang/String;
    //   29: astore_3
    //   30: aload_1
    //   31: bipush 64
    //   33: invokevirtual 582	java/lang/String:indexOf	(I)I
    //   36: istore 4
    //   38: iload 4
    //   40: iconst_m1
    //   41: if_icmpeq +53 -> 94
    //   44: iload_2
    //   45: ifeq +18 -> 63
    //   48: aload_1
    //   49: iconst_0
    //   50: iload 4
    //   52: invokevirtual 589	java/lang/String:substring	(II)Ljava/lang/String;
    //   55: invokevirtual 335	java/lang/String:toCharArray	()[C
    //   58: astore 17
    //   60: goto +19 -> 79
    //   63: aload_1
    //   64: iconst_0
    //   65: iload 4
    //   67: invokevirtual 589	java/lang/String:substring	(II)Ljava/lang/String;
    //   70: getstatic 265	org/apache/commons/httpclient/URI:allowed_userinfo	Ljava/util/BitSet;
    //   73: aload_3
    //   74: invokestatic 346	org/apache/commons/httpclient/URI:encode	(Ljava/lang/String;Ljava/util/BitSet;Ljava/lang/String;)[C
    //   77: astore 17
    //   79: aload_0
    //   80: aload 17
    //   82: putfield 301	org/apache/commons/httpclient/URI:_userinfo	[C
    //   85: iload 4
    //   87: iconst_1
    //   88: iadd
    //   89: istore 5
    //   91: goto +6 -> 97
    //   94: iconst_0
    //   95: istore 5
    //   97: aload_1
    //   98: bipush 91
    //   100: iload 5
    //   102: invokevirtual 625	java/lang/String:indexOf	(II)I
    //   105: iload 5
    //   107: if_icmplt +91 -> 198
    //   110: aload_1
    //   111: bipush 93
    //   113: iload 5
    //   115: invokevirtual 625	java/lang/String:indexOf	(II)I
    //   118: istore 15
    //   120: iload 15
    //   122: iconst_m1
    //   123: if_icmpne +15 -> 138
    //   126: new 320	org/apache/commons/httpclient/URIException
    //   129: dup
    //   130: iconst_1
    //   131: ldc_w 677
    //   134: invokespecial 325	org/apache/commons/httpclient/URIException:<init>	(ILjava/lang/String;)V
    //   137: athrow
    //   138: iload 15
    //   140: iconst_1
    //   141: iadd
    //   142: istore 6
    //   144: iload_2
    //   145: ifeq +19 -> 164
    //   148: aload_1
    //   149: iload 5
    //   151: iload 6
    //   153: invokevirtual 589	java/lang/String:substring	(II)Ljava/lang/String;
    //   156: invokevirtual 335	java/lang/String:toCharArray	()[C
    //   159: astore 16
    //   161: goto +20 -> 181
    //   164: aload_1
    //   165: iload 5
    //   167: iload 6
    //   169: invokevirtual 589	java/lang/String:substring	(II)Ljava/lang/String;
    //   172: getstatic 269	org/apache/commons/httpclient/URI:allowed_IPv6reference	Ljava/util/BitSet;
    //   175: aload_3
    //   176: invokestatic 346	org/apache/commons/httpclient/URI:encode	(Ljava/lang/String;Ljava/util/BitSet;Ljava/lang/String;)[C
    //   179: astore 16
    //   181: aload_0
    //   182: aload 16
    //   184: putfield 303	org/apache/commons/httpclient/URI:_host	[C
    //   187: aload_0
    //   188: iconst_1
    //   189: putfield 518	org/apache/commons/httpclient/URI:_is_IPv6reference	Z
    //   192: iconst_1
    //   193: istore 7
    //   195: goto +98 -> 293
    //   198: aload_1
    //   199: bipush 58
    //   201: iload 5
    //   203: invokevirtual 625	java/lang/String:indexOf	(II)I
    //   206: istore 6
    //   208: iload 6
    //   210: iconst_m1
    //   211: if_icmpne +15 -> 226
    //   214: aload_1
    //   215: invokevirtual 622	java/lang/String:length	()I
    //   218: istore 6
    //   220: iconst_0
    //   221: istore 7
    //   223: goto +6 -> 229
    //   226: iconst_1
    //   227: istore 7
    //   229: aload_0
    //   230: aload_1
    //   231: iload 5
    //   233: iload 6
    //   235: invokevirtual 589	java/lang/String:substring	(II)Ljava/lang/String;
    //   238: invokevirtual 335	java/lang/String:toCharArray	()[C
    //   241: putfield 303	org/apache/commons/httpclient/URI:_host	[C
    //   244: aload_0
    //   245: aload_0
    //   246: getfield 303	org/apache/commons/httpclient/URI:_host	[C
    //   249: getstatic 199	org/apache/commons/httpclient/URI:IPv4address	Ljava/util/BitSet;
    //   252: invokevirtual 339	org/apache/commons/httpclient/URI:validate	([CLjava/util/BitSet;)Z
    //   255: ifeq +11 -> 266
    //   258: aload_0
    //   259: iconst_1
    //   260: putfield 516	org/apache/commons/httpclient/URI:_is_IPv4address	Z
    //   263: goto +30 -> 293
    //   266: aload_0
    //   267: aload_0
    //   268: getfield 303	org/apache/commons/httpclient/URI:_host	[C
    //   271: getstatic 209	org/apache/commons/httpclient/URI:hostname	Ljava/util/BitSet;
    //   274: invokevirtual 339	org/apache/commons/httpclient/URI:validate	([CLjava/util/BitSet;)Z
    //   277: ifeq +11 -> 288
    //   280: aload_0
    //   281: iconst_1
    //   282: putfield 514	org/apache/commons/httpclient/URI:_is_hostname	Z
    //   285: goto +8 -> 293
    //   288: aload_0
    //   289: iconst_1
    //   290: putfield 417	org/apache/commons/httpclient/URI:_is_reg_name	Z
    //   293: aload_0
    //   294: getfield 417	org/apache/commons/httpclient/URI:_is_reg_name	Z
    //   297: ifeq +75 -> 372
    //   300: aload_0
    //   301: iconst_0
    //   302: putfield 518	org/apache/commons/httpclient/URI:_is_IPv6reference	Z
    //   305: aload_0
    //   306: iconst_0
    //   307: putfield 516	org/apache/commons/httpclient/URI:_is_IPv4address	Z
    //   310: aload_0
    //   311: iconst_0
    //   312: putfield 514	org/apache/commons/httpclient/URI:_is_hostname	Z
    //   315: aload_0
    //   316: iconst_0
    //   317: putfield 415	org/apache/commons/httpclient/URI:_is_server	Z
    //   320: iload_2
    //   321: ifeq +36 -> 357
    //   324: aload_0
    //   325: aload_1
    //   326: invokevirtual 335	java/lang/String:toCharArray	()[C
    //   329: putfield 299	org/apache/commons/httpclient/URI:_authority	[C
    //   332: aload_0
    //   333: aload_0
    //   334: getfield 299	org/apache/commons/httpclient/URI:_authority	[C
    //   337: getstatic 224	org/apache/commons/httpclient/URI:reg_name	Ljava/util/BitSet;
    //   340: invokevirtual 339	org/apache/commons/httpclient/URI:validate	([CLjava/util/BitSet;)Z
    //   343: ifne +184 -> 527
    //   346: new 320	org/apache/commons/httpclient/URIException
    //   349: dup
    //   350: ldc_w 679
    //   353: invokespecial 458	org/apache/commons/httpclient/URIException:<init>	(Ljava/lang/String;)V
    //   356: athrow
    //   357: aload_0
    //   358: aload_1
    //   359: getstatic 263	org/apache/commons/httpclient/URI:allowed_reg_name	Ljava/util/BitSet;
    //   362: aload_3
    //   363: invokestatic 346	org/apache/commons/httpclient/URI:encode	(Ljava/lang/String;Ljava/util/BitSet;Ljava/lang/String;)[C
    //   366: putfield 299	org/apache/commons/httpclient/URI:_authority	[C
    //   369: goto +158 -> 527
    //   372: aload_1
    //   373: invokevirtual 622	java/lang/String:length	()I
    //   376: iconst_1
    //   377: isub
    //   378: iload 6
    //   380: if_icmple +53 -> 433
    //   383: iload 7
    //   385: ifeq +48 -> 433
    //   388: aload_1
    //   389: iload 6
    //   391: invokevirtual 683	java/lang/String:charAt	(I)C
    //   394: bipush 58
    //   396: if_icmpne +37 -> 433
    //   399: iload 6
    //   401: iconst_1
    //   402: iadd
    //   403: istore 14
    //   405: aload_0
    //   406: aload_1
    //   407: iload 14
    //   409: invokevirtual 651	java/lang/String:substring	(I)Ljava/lang/String;
    //   412: invokestatic 688	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   415: putfield 305	org/apache/commons/httpclient/URI:_port	I
    //   418: goto +15 -> 433
    //   421: new 320	org/apache/commons/httpclient/URIException
    //   424: dup
    //   425: iconst_1
    //   426: ldc_w 690
    //   429: invokespecial 325	org/apache/commons/httpclient/URIException:<init>	(ILjava/lang/String;)V
    //   432: athrow
    //   433: new 361	java/lang/StringBuffer
    //   436: dup
    //   437: invokespecial 362	java/lang/StringBuffer:<init>	()V
    //   440: astore 8
    //   442: aload_0
    //   443: getfield 301	org/apache/commons/httpclient/URI:_userinfo	[C
    //   446: ifnull +21 -> 467
    //   449: aload 8
    //   451: aload_0
    //   452: getfield 301	org/apache/commons/httpclient/URI:_userinfo	[C
    //   455: invokevirtual 598	java/lang/StringBuffer:append	([C)Ljava/lang/StringBuffer;
    //   458: pop
    //   459: aload 8
    //   461: bipush 64
    //   463: invokevirtual 369	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   466: pop
    //   467: aload_0
    //   468: getfield 303	org/apache/commons/httpclient/URI:_host	[C
    //   471: ifnull +39 -> 510
    //   474: aload 8
    //   476: aload_0
    //   477: getfield 303	org/apache/commons/httpclient/URI:_host	[C
    //   480: invokevirtual 598	java/lang/StringBuffer:append	([C)Ljava/lang/StringBuffer;
    //   483: pop
    //   484: aload_0
    //   485: getfield 305	org/apache/commons/httpclient/URI:_port	I
    //   488: iconst_m1
    //   489: if_icmpeq +21 -> 510
    //   492: aload 8
    //   494: bipush 58
    //   496: invokevirtual 369	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   499: pop
    //   500: aload 8
    //   502: aload_0
    //   503: getfield 305	org/apache/commons/httpclient/URI:_port	I
    //   506: invokevirtual 379	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   509: pop
    //   510: aload_0
    //   511: aload 8
    //   513: invokevirtual 372	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   516: invokevirtual 335	java/lang/String:toCharArray	()[C
    //   519: putfield 299	org/apache/commons/httpclient/URI:_authority	[C
    //   522: aload_0
    //   523: iconst_1
    //   524: putfield 415	org/apache/commons/httpclient/URI:_is_server	Z
    //   527: return
    //
    // Exception table:
    //   from	to	target	type
    //   405	418	421	java/lang/NumberFormatException
  }

  protected void parseUriReference(String paramString, boolean paramBoolean)
  {
    if (paramString == null)
      throw new URIException("URI-Reference required");
    String str1 = paramString.trim();
    int i = str1.length();
    if (i > 0)
    {
      char[] arrayOfChar3 = new char[1];
      arrayOfChar3[0] = str1.charAt(0);
      if ((validate(arrayOfChar3, delims)) && (i >= 2))
      {
        char[] arrayOfChar4 = new char[1];
        int i7 = i - 1;
        arrayOfChar4[0] = str1.charAt(i7);
        if (validate(arrayOfChar4, delims))
        {
          str1 = str1.substring(1, i7);
          i -= 2;
        }
      }
    }
    int j = str1.indexOf(':');
    int k = str1.indexOf('/');
    int m;
    if (((j <= 0) && (!str1.startsWith("//"))) || ((k >= 0) && (k < j)))
      m = 1;
    else
      m = 0;
    String str2;
    if (m != 0)
      str2 = "/?#";
    else
      str2 = ":/?#";
    int n = indexFirstOf(str1, str2, 0);
    if (n == -1)
      n = 0;
    int i1;
    int i2;
    if ((n > 0) && (n < i) && (str1.charAt(n) == ':'))
    {
      char[] arrayOfChar2 = str1.substring(0, n).toLowerCase().toCharArray();
      if (validate(arrayOfChar2, scheme))
      {
        this._scheme = arrayOfChar2;
        i1 = n + 1;
        i2 = i1;
      }
      else
      {
        throw new URIException("incorrect scheme");
      }
    }
    else
    {
      i1 = n;
      i2 = 0;
    }
    this._is_hier_part = false;
    this._is_rel_path = false;
    this._is_abs_path = false;
    this._is_net_path = false;
    if ((i1 >= 0) && (i1 < i) && (str1.charAt(i1) == '/'))
    {
      this._is_hier_part = true;
      int i6 = i1 + 2;
      if ((i6 < i) && (str1.charAt(i1 + 1) == '/') && (m == 0))
      {
        i1 = indexFirstOf(str1, "/?#", i6);
        if (i1 == -1)
          if (str1.substring(i6).length() == 0)
            i1 = i6;
          else
            i1 = str1.length();
        parseAuthority(str1.substring(i6, i1), paramBoolean);
        this._is_net_path = true;
        i2 = i1;
      }
      if (i2 == i1)
        this._is_abs_path = true;
    }
    if (i2 < i)
    {
      int i5 = indexFirstOf(str1, "?#", i2);
      if (i5 == -1)
        i5 = str1.length();
      i1 = i5;
      if (!this._is_abs_path)
        if (((!paramBoolean) && (prevalidate(str1.substring(i2, i1), disallowed_rel_path))) || ((paramBoolean) && (validate(str1.substring(i2, i1).toCharArray(), rel_path))))
          this._is_rel_path = true;
        else if (((!paramBoolean) && (prevalidate(str1.substring(i2, i1), disallowed_opaque_part))) || ((paramBoolean) && (validate(str1.substring(i2, i1).toCharArray(), opaque_part))))
          this._is_opaque_part = true;
        else
          this._path = null;
      String str4 = str1.substring(i2, i1);
      if (paramBoolean)
        setRawPath(str4.toCharArray());
      else
        setPath(str4);
    }
    String str3 = getProtocolCharset();
    if (i1 >= 0)
    {
      int i4 = i1 + 1;
      if ((i4 < i) && (str1.charAt(i1) == '?'))
      {
        i1 = str1.indexOf('#', i4);
        if (i1 == -1)
          i1 = str1.length();
        if (paramBoolean)
        {
          this._query = str1.substring(i4, i1).toCharArray();
          if (!validate(this._query, uric))
            throw new URIException("Invalid query");
        }
        else
        {
          this._query = encode(str1.substring(i4, i1), allowed_query, str3);
        }
      }
    }
    if (i1 >= 0)
    {
      int i3 = i1 + 1;
      if ((i3 <= i) && (str1.charAt(i1) == '#'))
        if (i3 == i)
        {
          this._fragment = "".toCharArray();
        }
        else
        {
          char[] arrayOfChar1;
          if (paramBoolean)
            arrayOfChar1 = str1.substring(i3).toCharArray();
          else
            arrayOfChar1 = encode(str1.substring(i3), allowed_fragment, str3);
          this._fragment = arrayOfChar1;
        }
    }
    setURI();
  }

  protected boolean prevalidate(String paramString, BitSet paramBitSet)
  {
    if (paramString == null)
      return false;
    char[] arrayOfChar = paramString.toCharArray();
    for (int i = 0; i < arrayOfChar.length; i++)
      if (paramBitSet.get(arrayOfChar[i]))
        return false;
    return true;
  }

  protected char[] removeFragmentIdentifier(char[] paramArrayOfChar)
  {
    if (paramArrayOfChar == null)
      return null;
    int i = new String(paramArrayOfChar).indexOf('#');
    if (i != -1)
      paramArrayOfChar = new String(paramArrayOfChar).substring(0, i).toCharArray();
    return paramArrayOfChar;
  }

  protected char[] resolvePath(char[] paramArrayOfChar1, char[] paramArrayOfChar2)
  {
    String str1;
    if (paramArrayOfChar1 == null)
      str1 = "";
    else
      str1 = new String(paramArrayOfChar1);
    if ((paramArrayOfChar2 != null) && (paramArrayOfChar2.length != 0))
    {
      if (paramArrayOfChar2[0] == '/')
        return normalize(paramArrayOfChar2);
      int i = str1.lastIndexOf('/');
      if (i != -1)
        str1.substring(0, i + 1).toCharArray();
      StringBuffer localStringBuffer = new StringBuffer(str1.length() + paramArrayOfChar2.length);
      String str2;
      if (i != -1)
        str2 = str1.substring(0, i + 1);
      else
        str2 = "/";
      localStringBuffer.append(str2);
      localStringBuffer.append(paramArrayOfChar2);
      return normalize(localStringBuffer.toString().toCharArray());
    }
    return normalize(paramArrayOfChar1);
  }

  public void setEscapedAuthority(String paramString)
  {
    parseAuthority(paramString, true);
    setURI();
  }

  public void setEscapedFragment(String paramString)
  {
    if (paramString == null)
    {
      this._fragment = null;
      this.hash = 0;
      return;
    }
    setRawFragment(paramString.toCharArray());
  }

  public void setEscapedPath(String paramString)
  {
    if (paramString == null)
    {
      this._opaque = null;
      this._path = null;
      setURI();
      return;
    }
    setRawPath(paramString.toCharArray());
  }

  public void setEscapedQuery(String paramString)
  {
    if (paramString == null)
    {
      this._query = null;
      setURI();
      return;
    }
    setRawQuery(paramString.toCharArray());
  }

  public void setFragment(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      this._fragment = encode(paramString, allowed_fragment, getProtocolCharset());
      this.hash = 0;
      return;
    }
    char[] arrayOfChar;
    if (paramString == null)
      arrayOfChar = null;
    else
      arrayOfChar = paramString.toCharArray();
    this._fragment = arrayOfChar;
    this.hash = 0;
  }

  public void setPath(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      String str = getProtocolCharset();
      if ((!this._is_net_path) && (!this._is_abs_path))
      {
        if (this._is_rel_path)
        {
          StringBuffer localStringBuffer1 = new StringBuffer(paramString.length());
          int i = paramString.indexOf('/');
          if (i == 0)
            throw new URIException(1, "incorrect relative path");
          if (i > 0)
          {
            localStringBuffer1.append(encode(paramString.substring(0, i), allowed_rel_path, str));
            localStringBuffer1.append(encode(paramString.substring(i), allowed_abs_path, str));
          }
          else
          {
            localStringBuffer1.append(encode(paramString, allowed_rel_path, str));
          }
          this._path = localStringBuffer1.toString().toCharArray();
        }
        else if (this._is_opaque_part)
        {
          StringBuffer localStringBuffer2 = new StringBuffer();
          localStringBuffer2.insert(0, encode(paramString.substring(0, 1), uric_no_slash, str));
          localStringBuffer2.insert(1, encode(paramString.substring(1), uric, str));
          this._opaque = localStringBuffer2.toString().toCharArray();
        }
        else
        {
          throw new URIException(1, "incorrect path");
        }
      }
      else
        this._path = encode(paramString, allowed_abs_path, str);
      setURI();
      return;
    }
    char[] arrayOfChar;
    if (paramString == null)
      arrayOfChar = null;
    else
      arrayOfChar = paramString.toCharArray();
    this._opaque = arrayOfChar;
    this._path = arrayOfChar;
    setURI();
  }

  public void setQuery(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      setRawQuery(encode(paramString, allowed_query, getProtocolCharset()));
      return;
    }
    char[] arrayOfChar;
    if (paramString == null)
      arrayOfChar = null;
    else
      arrayOfChar = paramString.toCharArray();
    this._query = arrayOfChar;
    setURI();
  }

  public void setRawAuthority(char[] paramArrayOfChar)
  {
    parseAuthority(new String(paramArrayOfChar), true);
    setURI();
  }

  public void setRawFragment(char[] paramArrayOfChar)
  {
    if ((paramArrayOfChar != null) && (paramArrayOfChar.length != 0))
    {
      if (!validate(paramArrayOfChar, fragment))
        throw new URIException(3, "escaped fragment not valid");
      this._fragment = paramArrayOfChar;
      this.hash = 0;
      return;
    }
    this._fragment = paramArrayOfChar;
    this.hash = 0;
  }

  public void setRawPath(char[] paramArrayOfChar)
  {
    if ((paramArrayOfChar != null) && (paramArrayOfChar.length != 0))
    {
      char[] arrayOfChar = removeFragmentIdentifier(paramArrayOfChar);
      if ((!this._is_net_path) && (!this._is_abs_path))
      {
        if (this._is_rel_path)
        {
          int i = indexFirstOf(arrayOfChar, '/');
          if (i == 0)
            throw new URIException(1, "incorrect path");
          if (((i > 0) && (!validate(arrayOfChar, 0, i - 1, rel_segment)) && (!validate(arrayOfChar, i, -1, abs_path))) || ((i < 0) && (!validate(arrayOfChar, 0, -1, rel_segment))))
            throw new URIException(3, "escaped relative path not valid");
          this._path = arrayOfChar;
        }
        else if (this._is_opaque_part)
        {
          if ((!uric_no_slash.get(arrayOfChar[0])) && (!validate(arrayOfChar, 1, -1, uric)))
            throw new URIException(3, "escaped opaque part not valid");
          this._opaque = arrayOfChar;
        }
        else
        {
          throw new URIException(1, "incorrect path");
        }
      }
      else
      {
        if (arrayOfChar[0] != '/')
          throw new URIException(1, "not absolute path");
        if (!validate(arrayOfChar, abs_path))
          throw new URIException(3, "escaped absolute path not valid");
        this._path = arrayOfChar;
      }
      setURI();
      return;
    }
    this._opaque = paramArrayOfChar;
    this._path = paramArrayOfChar;
    setURI();
  }

  public void setRawQuery(char[] paramArrayOfChar)
  {
    if ((paramArrayOfChar != null) && (paramArrayOfChar.length != 0))
    {
      char[] arrayOfChar = removeFragmentIdentifier(paramArrayOfChar);
      if (!validate(arrayOfChar, query))
        throw new URIException(3, "escaped query not valid");
      this._query = arrayOfChar;
      setURI();
      return;
    }
    this._query = paramArrayOfChar;
    setURI();
  }

  protected void setURI()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (this._scheme != null)
    {
      localStringBuffer.append(this._scheme);
      localStringBuffer.append(':');
    }
    if (this._is_net_path)
    {
      localStringBuffer.append("//");
      if (this._authority != null)
        localStringBuffer.append(this._authority);
    }
    if ((this._opaque != null) && (this._is_opaque_part))
      localStringBuffer.append(this._opaque);
    else if ((this._path != null) && (this._path.length != 0))
      localStringBuffer.append(this._path);
    if (this._query != null)
    {
      localStringBuffer.append('?');
      localStringBuffer.append(this._query);
    }
    this._uri = localStringBuffer.toString().toCharArray();
    this.hash = 0;
  }

  public String toString()
  {
    return getEscapedURI();
  }

  protected boolean validate(char[] paramArrayOfChar, int paramInt1, int paramInt2, BitSet paramBitSet)
  {
    if (paramInt2 == -1)
      paramInt2 = paramArrayOfChar.length - 1;
    while (paramInt1 <= paramInt2)
    {
      if (!paramBitSet.get(paramArrayOfChar[paramInt1]))
        return false;
      paramInt1++;
    }
    return true;
  }

  protected boolean validate(char[] paramArrayOfChar, BitSet paramBitSet)
  {
    return validate(paramArrayOfChar, 0, -1, paramBitSet);
  }

  public static class DefaultCharsetChanged extends RuntimeException
  {
    public static final int DOCUMENT_CHARSET = 2;
    public static final int PROTOCOL_CHARSET = 1;
    public static final int UNKNOWN;
    private String reason;
    private int reasonCode;

    public DefaultCharsetChanged(int paramInt, String paramString)
    {
      super();
      this.reason = paramString;
      this.reasonCode = paramInt;
    }

    public String getReason()
    {
      return this.reason;
    }

    public int getReasonCode()
    {
      return this.reasonCode;
    }
  }

  public static class LocaleToCharsetMap
  {
    private static final Hashtable LOCALE_TO_CHARSET_MAP = new Hashtable();

    static
    {
      LOCALE_TO_CHARSET_MAP.put("ar", "ISO-8859-6");
      LOCALE_TO_CHARSET_MAP.put("be", "ISO-8859-5");
      LOCALE_TO_CHARSET_MAP.put("bg", "ISO-8859-5");
      LOCALE_TO_CHARSET_MAP.put("ca", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("cs", "ISO-8859-2");
      LOCALE_TO_CHARSET_MAP.put("da", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("de", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("el", "ISO-8859-7");
      LOCALE_TO_CHARSET_MAP.put("en", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("es", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("et", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("fi", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("fr", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("hr", "ISO-8859-2");
      LOCALE_TO_CHARSET_MAP.put("hu", "ISO-8859-2");
      LOCALE_TO_CHARSET_MAP.put("is", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("it", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("iw", "ISO-8859-8");
      LOCALE_TO_CHARSET_MAP.put("ja", "Shift_JIS");
      LOCALE_TO_CHARSET_MAP.put("ko", "EUC-KR");
      LOCALE_TO_CHARSET_MAP.put("lt", "ISO-8859-2");
      LOCALE_TO_CHARSET_MAP.put("lv", "ISO-8859-2");
      LOCALE_TO_CHARSET_MAP.put("mk", "ISO-8859-5");
      LOCALE_TO_CHARSET_MAP.put("nl", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("no", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("pl", "ISO-8859-2");
      LOCALE_TO_CHARSET_MAP.put("pt", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("ro", "ISO-8859-2");
      LOCALE_TO_CHARSET_MAP.put("ru", "ISO-8859-5");
      LOCALE_TO_CHARSET_MAP.put("sh", "ISO-8859-5");
      LOCALE_TO_CHARSET_MAP.put("sk", "ISO-8859-2");
      LOCALE_TO_CHARSET_MAP.put("sl", "ISO-8859-2");
      LOCALE_TO_CHARSET_MAP.put("sq", "ISO-8859-2");
      LOCALE_TO_CHARSET_MAP.put("sr", "ISO-8859-5");
      LOCALE_TO_CHARSET_MAP.put("sv", "ISO-8859-1");
      LOCALE_TO_CHARSET_MAP.put("tr", "ISO-8859-9");
      LOCALE_TO_CHARSET_MAP.put("uk", "ISO-8859-5");
      LOCALE_TO_CHARSET_MAP.put("zh", "GB2312");
      LOCALE_TO_CHARSET_MAP.put("zh_TW", "Big5");
    }

    public static String getCharset(Locale paramLocale)
    {
      String str = (String)LOCALE_TO_CHARSET_MAP.get(paramLocale.toString());
      if (str != null)
        return str;
      return (String)LOCALE_TO_CHARSET_MAP.get(paramLocale.getLanguage());
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.URI
 * JD-Core Version:    0.6.1
 */