package org.grayleaves.problem;

import static org.junit.Assert.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;

public class ProblemXmlReaderTest
{
//	@Test
	public void verifyXmlParsed() throws Exception
	{
	    DocumentBuilderFactory factory =   DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document document =  builder.parse(
	        ClassLoader.getSystemResourceAsStream("xml/employee.xml"));
	    
	}

}

////Get the DOM Builder Factory
//07	 
//08	    //Get the DOM Builder
//10	 
//11	    //Load and Parse the XML document
//12	    //document contains the complete XML as a Tree.
//16	 
//17	    List<Employee> empList = new ArrayList<>();
//18	 
//19	    //Iterating through the nodes and extracting the data.
//20	    NodeList nodeList = document.getDocumentElement().getChildNodes();
//21	 
//22	    for (int i = 0; i < nodeList.getLength(); i++) {
//23	 
//24	      //We have encountered an <employee> tag.
//25	      Node node = nodeList.item(i);
//26	      if (node instanceof Element) {
//27	        Employee emp = new Employee();
//28	        emp.id = node.getAttributes().
//29	            getNamedItem("id").getNodeValue();
//30	 
//31	        NodeList childNodes = node.getChildNodes();
//32	        for (int j = 0; j < childNodes.getLength(); j++) {
//33	          Node cNode = childNodes.item(j);
//34	 
//35	          //Identifying the child tag of employee encountered.
//36	          if (cNode instanceof Element) {
//37	            String content = cNode.getLastChild().
//38	                getTextContent().trim();
//39	            switch (cNode.getNodeName()) {
//40	              case "firstName":
//41	                emp.firstName = content;
//42	                break;
//43	              case "lastName":
//44	                emp.lastName = content;
//45	                break;
//46	              case "location":
//47	                emp.location = content;
//48	                break;
//49	            }
//50	          }
//51	        }
//52	        empList.add(emp);
//53	      }
//54	 
//55	    }
//56	 
//57	    //Printing the Employee list populated.
//58	    for (Employee emp : empList) {
//59	      System.out.println(emp);
//60	    }
//61	 
//62	  }
//63	}