package org.jsoup.parser;

import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.DescendableLinkedList;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Document.OutputSettings.Syntax;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;

public class XmlTreeBuilder extends TreeBuilder
{
  private void insertNode(Node paramNode)
  {
    currentElement().appendChild(paramNode);
  }

  private void popStackToClose(Token.EndTag paramEndTag)
  {
    String str = paramEndTag.name();
    Iterator localIterator1 = this.stack.descendingIterator();
    while (localIterator1.hasNext())
    {
      localElement = (Element)localIterator1.next();
      if (localElement.nodeName().equals(str))
        break label51;
    }
    Element localElement = null;
    label51: if (localElement == null)
      return;
    Iterator localIterator2 = this.stack.descendingIterator();
    while (localIterator2.hasNext())
    {
      if ((Element)localIterator2.next() == localElement)
      {
        localIterator2.remove();
        break;
      }
      localIterator2.remove();
    }
  }

  protected void initialiseParse(String paramString1, String paramString2, ParseErrorList paramParseErrorList)
  {
    super.initialiseParse(paramString1, paramString2, paramParseErrorList);
    this.stack.add(this.doc);
    this.doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
  }

  Element insert(Token.StartTag paramStartTag)
  {
    Tag localTag = Tag.valueOf(paramStartTag.name());
    Element localElement = new Element(localTag, this.baseUri, paramStartTag.attributes);
    insertNode(localElement);
    if (paramStartTag.isSelfClosing())
    {
      this.tokeniser.acknowledgeSelfClosingFlag();
      if (!localTag.isKnownTag())
        localTag.setSelfClosing();
    }
    else
    {
      this.stack.add(localElement);
    }
    return localElement;
  }

  void insert(Token.Character paramCharacter)
  {
    insertNode(new TextNode(paramCharacter.getData(), this.baseUri));
  }

  void insert(Token.Comment paramComment)
  {
    Object localObject = new Comment(paramComment.getData(), this.baseUri);
    if (paramComment.bogus)
    {
      String str = ((Comment)localObject).getData();
      if ((str.length() > 1) && ((str.startsWith("!")) || (str.startsWith("?"))))
        localObject = new XmlDeclaration(str.substring(1), ((Comment)localObject).baseUri(), str.startsWith("!"));
    }
    insertNode((Node)localObject);
  }

  void insert(Token.Doctype paramDoctype)
  {
    insertNode(new DocumentType(paramDoctype.getName(), paramDoctype.getPublicIdentifier(), paramDoctype.getSystemIdentifier(), this.baseUri));
  }

  List<Node> parseFragment(String paramString1, String paramString2, ParseErrorList paramParseErrorList)
  {
    initialiseParse(paramString1, paramString2, paramParseErrorList);
    runParser();
    return this.doc.childNodes();
  }

  protected boolean process(Token paramToken)
  {
    switch (1.$SwitchMap$org$jsoup$parser$Token$TokenType[paramToken.type.ordinal()])
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected token type: ");
      localStringBuilder.append(paramToken.type);
      Validate.fail(localStringBuilder.toString());
      break;
    case 5:
      insert(paramToken.asDoctype());
      break;
    case 4:
      insert(paramToken.asCharacter());
      break;
    case 3:
      insert(paramToken.asComment());
      break;
    case 2:
      popStackToClose(paramToken.asEndTag());
      break;
    case 1:
      insert(paramToken.asStartTag());
    case 6:
    }
    return true;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.XmlTreeBuilder
 * JD-Core Version:    0.6.1
 */