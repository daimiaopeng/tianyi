package org.jsoup.parser;

import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class Parser
{
  private static final int DEFAULT_MAX_ERRORS;
  private ParseErrorList errors;
  private int maxErrors = 0;
  private TreeBuilder treeBuilder;

  public Parser(TreeBuilder paramTreeBuilder)
  {
    this.treeBuilder = paramTreeBuilder;
  }

  public static Parser htmlParser()
  {
    return new Parser(new HtmlTreeBuilder());
  }

  public static Document parse(String paramString1, String paramString2)
  {
    return new HtmlTreeBuilder().parse(paramString1, paramString2, ParseErrorList.noTracking());
  }

  public static Document parseBodyFragment(String paramString1, String paramString2)
  {
    Document localDocument = Document.createShell(paramString2);
    Element localElement = localDocument.body();
    List localList = parseFragment(paramString1, localElement, paramString2);
    Node[] arrayOfNode = (Node[])localList.toArray(new Node[localList.size()]);
    int i = arrayOfNode.length;
    for (int j = 0; j < i; j++)
      localElement.appendChild(arrayOfNode[j]);
    return localDocument;
  }

  public static Document parseBodyFragmentRelaxed(String paramString1, String paramString2)
  {
    return parse(paramString1, paramString2);
  }

  public static List<Node> parseFragment(String paramString1, Element paramElement, String paramString2)
  {
    return new HtmlTreeBuilder().parseFragment(paramString1, paramElement, paramString2, ParseErrorList.noTracking());
  }

  public static List<Node> parseXmlFragment(String paramString1, String paramString2)
  {
    return new XmlTreeBuilder().parseFragment(paramString1, paramString2, ParseErrorList.noTracking());
  }

  public static String unescapeEntities(String paramString, boolean paramBoolean)
  {
    return new Tokeniser(new CharacterReader(paramString), ParseErrorList.noTracking()).unescapeEntities(paramBoolean);
  }

  public static Parser xmlParser()
  {
    return new Parser(new XmlTreeBuilder());
  }

  public List<ParseError> getErrors()
  {
    return this.errors;
  }

  public TreeBuilder getTreeBuilder()
  {
    return this.treeBuilder;
  }

  public boolean isTrackErrors()
  {
    boolean bool;
    if (this.maxErrors > 0)
      bool = true;
    else
      bool = false;
    return bool;
  }

  public Document parseInput(String paramString1, String paramString2)
  {
    ParseErrorList localParseErrorList;
    if (isTrackErrors())
      localParseErrorList = ParseErrorList.tracking(this.maxErrors);
    else
      localParseErrorList = ParseErrorList.noTracking();
    this.errors = localParseErrorList;
    return this.treeBuilder.parse(paramString1, paramString2, this.errors);
  }

  public Parser setTrackErrors(int paramInt)
  {
    this.maxErrors = paramInt;
    return this;
  }

  public Parser setTreeBuilder(TreeBuilder paramTreeBuilder)
  {
    this.treeBuilder = paramTreeBuilder;
    return this;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.Parser
 * JD-Core Version:    0.6.1
 */