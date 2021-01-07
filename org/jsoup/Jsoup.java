package org.jsoup;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

public class Jsoup
{
  public static String clean(String paramString1, String paramString2, Whitelist paramWhitelist)
  {
    Document localDocument = parseBodyFragment(paramString1, paramString2);
    return new Cleaner(paramWhitelist).clean(localDocument).body().html();
  }

  public static String clean(String paramString1, String paramString2, Whitelist paramWhitelist, Document.OutputSettings paramOutputSettings)
  {
    Document localDocument1 = parseBodyFragment(paramString1, paramString2);
    Document localDocument2 = new Cleaner(paramWhitelist).clean(localDocument1);
    localDocument2.outputSettings(paramOutputSettings);
    return localDocument2.body().html();
  }

  public static String clean(String paramString, Whitelist paramWhitelist)
  {
    return clean(paramString, "", paramWhitelist);
  }

  public static Connection connect(String paramString)
  {
    return HttpConnection.connect(paramString);
  }

  public static boolean isValid(String paramString, Whitelist paramWhitelist)
  {
    Document localDocument = parseBodyFragment(paramString, "");
    return new Cleaner(paramWhitelist).isValid(localDocument);
  }

  public static Document parse(File paramFile, String paramString)
  {
    return DataUtil.load(paramFile, paramString, paramFile.getAbsolutePath());
  }

  public static Document parse(File paramFile, String paramString1, String paramString2)
  {
    return DataUtil.load(paramFile, paramString1, paramString2);
  }

  public static Document parse(InputStream paramInputStream, String paramString1, String paramString2)
  {
    return DataUtil.load(paramInputStream, paramString1, paramString2);
  }

  public static Document parse(InputStream paramInputStream, String paramString1, String paramString2, Parser paramParser)
  {
    return DataUtil.load(paramInputStream, paramString1, paramString2, paramParser);
  }

  public static Document parse(String paramString)
  {
    return Parser.parse(paramString, "");
  }

  public static Document parse(String paramString1, String paramString2)
  {
    return Parser.parse(paramString1, paramString2);
  }

  public static Document parse(String paramString1, String paramString2, Parser paramParser)
  {
    return paramParser.parseInput(paramString1, paramString2);
  }

  public static Document parse(URL paramURL, int paramInt)
  {
    Connection localConnection = HttpConnection.connect(paramURL);
    localConnection.timeout(paramInt);
    return localConnection.get();
  }

  public static Document parseBodyFragment(String paramString)
  {
    return Parser.parseBodyFragment(paramString, "");
  }

  public static Document parseBodyFragment(String paramString1, String paramString2)
  {
    return Parser.parseBodyFragment(paramString1, paramString2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.Jsoup
 * JD-Core Version:    0.6.1
 */