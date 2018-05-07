package com.vince.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class XMLDeom {
    /**
     * 使用第三方组件实现XML文件的解析与生成
     */
    @Test
    public void XMLStream(){
        Person person = new Person();
        person.setPersonid("3258");
        person.setName("kobe");
        person.setAddress("韩城");
        person.setTel("1344540988");
        person.setFax("voce.sss");
        person.setEmail("vince@163.com");
        XStream xStream = new XStream(new Xpp3Driver());
        xStream.alias("person",Person.class);
        xStream.useAttributeFor(Person.class,"personid");
        //解析XML
        String xml = xStream.toXML(person);
        System.out.println(xml);
    }
    /**
     * 从XML文件中读取对象
     */
    @Test
    public void XMLDecoder() throws FileNotFoundException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("xmlperson.xml"));
        XMLDecoder xmlDecoder = new XMLDecoder(bis);
        Person person = (Person) xmlDecoder.readObject();
        System.out.println(person);
    }
    /**
     * 把对象转成XML文件写入
     */
    @Test
    public void XMLEncoder() throws FileNotFoundException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("xmlperson.xml"));
        XMLEncoder xmlEncoder = new XMLEncoder(bos);
        Person person = new Person();
        person.setPersonid("3258");
        person.setName("kobe");
        person.setAddress("韩城");
        person.setTel("1344540988");
        person.setFax("voce.sss");
        person.setEmail("vince@163.com");
        xmlEncoder.writeObject(person);
        xmlEncoder.close();
    }
    /**
     * DOM4J解析XML
     * 基于树型结构，第三方组件
     * 解析速度快，效率更高，使用的JAVA中的迭代器实现数据读取，在WEB框架中使用较多（Hibernate)
     * @throws DocumentException
     */
    @Test
    public void DOM4JParseXML() throws DocumentException {
        //1.创建DOM4J解析器
        SAXReader saxReader = new SAXReader();
        //开始解析
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/vince/xml/person.xml");
        org.dom4j.Document document = saxReader.read(is);
        org.dom4j.Element rootElement = document.getRootElement();
        ArrayList<Person> arrayList = new ArrayList<>();
        Person person = null;
        Iterator<org.dom4j.Element> iterator = rootElement.elementIterator();
        while (iterator.hasNext()){
            person = new Person();
            org.dom4j.Element next = iterator.next();
            person.setPersonid(next.attributeValue("personid"));
            Iterator<org.dom4j.Element> elementIterator = next.elementIterator();
            while(elementIterator.hasNext()){
                org.dom4j.Element element = elementIterator.next();
                String tag = element.getName();
                if("name".equals(tag)){
                    person.setName(element.getText());
                }else if("address".equals(tag)){
                    person.setAddress(element.getText());
                }else if("tel".equals(tag)){
                    person.setTel(element.getText());
                }else if("fax".equals(tag)){
                    person.setFax(element.getText());
                }else if("email".equals(tag)){
                    person.setEmail(element.getText());
                }
            }
            arrayList.add(person);
        }
        System.out.println("结果是：");
        System.out.println(Arrays.toString(arrayList.toArray()));
    }
    /**
     * JDOM解析XML
     * 特点：1.与DOM类似，都是基于树状结构
     *       2.与DOM的区别：
     *          （1）属于第三方开源组件
     *          （2）实现java的Collection接口
     *          （3）效率比DOM更快
     * @throws JDOMException
     * @throws IOException
     */
    @Test
    public void JDOMParseXML() throws JDOMException, IOException {
        //1.创建JDOM解析器
        SAXBuilder saxBuilder = new SAXBuilder();
        //2.解析文档
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/vince/xml/person.xml");
        //此代码完成后，整个XML文档被加载到内存中，以树的形式存储
        org.jdom2.Document document = saxBuilder.build(is);
        Element rootElement = document.getRootElement();
        List<Person> list = new ArrayList<>();
        Person person = null;
        List<Element> children = rootElement.getChildren();
        for (Element element :
                children) {
            person = new Person();
            String personid = element.getAttributeValue("personid");
            person.setPersonid(personid);
            List<Element> elementChildren = element.getChildren();
            for (Element e :
                    elementChildren) {
                String tag = e.getName();
                if("name".equals(tag)){
                    person.setName(e.getText());
                }else if("address".equals(tag)){
                    person.setAddress(e.getText());
                }else if("tel".equals(tag)){
                    person.setTel(e.getText());
                }else if("fax".equals(tag)){
                    person.setFax(e.getText());
                }else if("email".equals(tag)){
                    person.setEmail(e.getText());
                }
            }
            list.add(person);
        }
        System.out.println("结果是：");
        System.out.println(Arrays.toString(list.toArray()));
    }
    /**
     * DOM解析XML文档的特点：
     *      基于树形结构，通过解析器一次性把文档加载到内存中，所以会比较占用内存，可随机访问，
     * 更加灵活，更适合在WEB开发中使用
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    @Test
    public void DomParseXML() throws IOException, SAXException, ParserConfigurationException {
        //1.创建DOM解析器工厂对象
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //2.通过工厂对象创建DOM解析器
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //3.解析文档
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/vince/xml/person.xml");
        //此代码完成后，整个XML文档已经被加载到内存中，以树状形式存储
        Document doc = documentBuilder.parse(is);
        //4.从内存中读取数据
        //获取结点为名称为person的所有节点，返回集合
        NodeList personNodeList = doc.getElementsByTagName("person");
        List<Person> arraylist = new ArrayList<>();
        Person p = null;
        //此循环会迭代两次
        for (int i = 0; i < personNodeList.getLength(); i++) {
            Node personNode = personNodeList.item(i);
            p = new Person();
            //获取结点的属性值
            String personid = personNode.getAttributes().getNamedItem("personid").getNodeValue();
            p.setPersonid(personid);
            //获取当前节点的所有子节点
            NodeList childNodes = personNode.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node item = childNodes.item(j);
                String nodeName = item.getNodeName();
                if("name".equals(nodeName)){
                    p.setName(item.getFirstChild().getNodeValue());
                }else if ("address".equals(nodeName)){
                    p.setAddress(item.getFirstChild().getNodeValue());
                }else if("tel".equals(nodeName)){
                    p.setTel(item.getFirstChild().getNodeValue());
                }else if("fax".equals(nodeName)){
                    p.setFax(item.getFirstChild().getNodeValue());
                }else if("email".equals(nodeName)){
                    p.setEmail(item.getFirstChild().getNodeValue());
                }
            }
            arraylist.add(p);
        }
        System.out.println("结果：");
        System.out.println(Arrays.toString(arraylist.toArray()));


    }
    /**
     * SAX解析XML的特点：
     * 1、基于事件驱动
     * 2、顺序读取，速度快
     * 3、不能任意读取节点（灵活性差）
     * 4、解析时占用的内存小
     * 5、SAX更适用于在性能要求更高的设备上使用（Android开发中）
     */
    @Test
    public void SaxParseXML() throws ParserConfigurationException, SAXException, IOException {
        //1.创建SAX解析器工厂对象
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        //2.通过工厂对象创建SAX解析器
            SAXParser saxParser = saxParserFactory.newSAXParser();
        //3.创建一个数据处理器（需要我们自己去写）
        PersonHandler personHandler = new PersonHandler();
        //4.开始解析
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/vince/xml/person.xml");
        saxParser.parse(is,personHandler);
        List<Person> persons = personHandler.getPersons();
        for (Person p :
                persons) {
            System.out.println(p);
        }
    }
}
