import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class Customer {
	
	
	public String id;
	public String firstName;
	public String lastName;
	public String addr;
	public int phoneNum;
	
	Element root;
	
	String rootNodeName = "customers";
	String dbFileName = "customers.xml";
	
	String path = "C:\\Users\\AbdulMajeed\\Documents\\OOP\\CustomerInfo\\"+  dbFileName;
	//File f = new File(path);
	
	
	
	public Customer()
	{
		this.id = "";
		this.firstName = "";
		this.lastName = "";
		this.addr = "";
		this.phoneNum = 0;
	}	
	
	public Customer(String id, String firstName, String lastName, String addr, int phoneNum)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addr = addr;
		this.phoneNum = phoneNum;
	}	
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

public void AddCustomer(String id, String firstName, String lastName, String addr, int phoneNum) {		
			
	
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=null;
			Document doc=null;
			Element root =null;
		
			try {
				builder = dbf.newDocumentBuilder();
			
				doc = builder.parse(dbFileName);
				 root = (Element)doc.getDocumentElement();
				
			} catch (SAXException | IOException e) {
				doc = builder.newDocument();
				root = doc.createElement(rootNodeName);
				
				doc.appendChild(root);
				
				System.out.println(root.getNodeName());
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//create root node
			//Element root = doc.createElement("customers");
			
			
			
			
			//System.out.println(root.getNodeName());
			//create customer
			Element customer = doc.createElement("customer");
			//create customerID			
			Element customerID = doc.createElement("customerID");
			Text idValue =  doc.createTextNode(id);
			customerID.appendChild(idValue);
			//create customerFname
			Element customerFname = doc.createElement("Firstname");
			Text firstNameValue =  doc.createTextNode(firstName);
			customerFname.appendChild(firstNameValue);
			//create customerLname
			Element customerLname = doc.createElement("Lastname");
			Text lastNameValue =  doc.createTextNode(lastName);
			customerLname.appendChild(lastNameValue);
			//create customerAddress
			Element customerAddr = doc.createElement("Address");
			Text addrValue =  doc.createTextNode(addr);
			customerAddr.appendChild(addrValue);
			//create customerPhone
			Element customerPhone = doc.createElement("Phone");
			String phoneString = Integer.toString(phoneNum);
			Text phoValue =  doc.createTextNode(phoneString);
			customerPhone.appendChild(phoValue);
			
			//Add to customer node
			customer.appendChild(customerID);
			customer.appendChild(customerFname);
			customer.appendChild(customerLname);
			customer.appendChild(customerAddr);
			customer.appendChild(customerPhone);			
			
			root.appendChild(customer);	 
	
			//Add root to document
			//doc.appendChild(root);
			
		
			
			
			try {
				
				DOMSource source = new DOMSource(doc);
				
				
				
				FileWriter fileWriter = new FileWriter(path);
				Result result = new StreamResult(fileWriter);
				
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.transform(source, result);
				
				fileWriter.close();
				System.out.println("write data successfully to file" + path);
			} catch (IOException  | TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

		
	}

public Customer searchCustomer(String searchText) {
	  

    // This is the name of xml file to be read
    String file = "customer.xml";

    // The followings are the name of the element, its attribute and the
    // expected value for the attribute for the searched element.
    String elementName = "customerID";    
    String elementNameValue = searchText;

    boolean elementFound = false;
    Customer customer = new Customer();

    System.out.println("Information for Customer with " + elementName + " = "
        + elementNameValue + " : ");

    try {

      // Here we parse the xml file and load it to memory
      Document document = DocumentBuilderFactory.newInstance()
          .newDocumentBuilder().parse(file);

      // Here we normalize the document
      document.getDocumentElement().normalize();

      // Here we get a list of all subelements of the searched element
      //NodeList rootNodes = document.getElementsByTagName("customers");
      //Node rootNode = (Node) rootNodes.item(0);
      //Element rootElement = (Element) rootNodes;
      
      
      NodeList customerList = document.getElementsByTagName("customer");
      // Here we go through the list of elements
      for (int i = 0; i < customerList.getLength(); i++) {

        // If the element has already been found, we get out of the
        // loop.
        if (elementFound)
          break;

        if (customerList.item(i).getNodeType() == Node.ELEMENT_NODE) {

          // Here we create an Element object of each node
          Element element = (Element) customerList.item(i);

          // Here we check whether the given attribute of the element
          // has the expected value
          // The attribute name and its expected value were defined
          // earlier above
          Element customerIdElement = (Element) element.getElementsByTagName("customerID").item(0);
          if (customerIdElement.getTextContent().equals(
        		  elementNameValue)) {
            elementFound = true;

            // Here we print the name of the element (tag name)
            // itself
            //System.out.print(element.getTagName() + " ");
            System.out.print("\n");

            // Here we get a list of all attributes of the element
            // and then
            // print out their names and values
            NamedNodeMap attList = element.getAttributes();
            for (int j = 0; j < attList.getLength(); j++) {

              System.out.print(attList.item(j).getNodeName()
                  + "=" + attList.item(j).getNodeValue()
                  + " ");

            }

            System.out.println();

            // Here we get a list of all subelements of element and
            // print
            // out their names and text values
            NodeList elementList = element
                .getElementsByTagName("*");

           
            //for (int k = 0; k < elementList.getLength(); k++) {
              // Here we print the name of the subelement and its
              // text content
            int k = 0;	
            customer.setId(elementList.item(k).getTextContent());
            customer.setFirstName(elementList.item(k+1).getTextContent());
            customer.setLastName(elementList.item(k+2).getTextContent());
            customer.setAddr(elementList.item(k+3).getTextContent());
            customer.setPhoneNum(Integer.parseInt(elementList.item(k+4).getTextContent()));
            
             /* System.out.print(elementList.item(k).getNodeName()
                  + "="
                  + elementList.item(k).getTextContent()
                  + " ");

             
              System.out.println();*/

            //}
          } else {
            if (!elementFound && i == customerList.getLength() - 1)
              System.out
                  .println(elementName + " with "
                      + elementNameValue + " was not found!");
            // break;

          }
        }

      }
      
     
      
    } catch (Exception ex) {
      System.out.println(ex);
    }
	return customer;
	
  
}


public boolean deleteCustomer(String deleteText) {
	  

    // This is the name of xml file to be read
    String file = "customer.xml";

    // The followings are the name of the element, its attribute and the
    // expected value for the attribute for the searched element.
    String elementName = "customerID";    
    String elementNameValue = deleteText;

    boolean elementFound = false;    

    System.out.println("Delete Information for Customer with " + elementName + " = "
        + elementNameValue + " : ");

    try {

      // Here we parse the xml file and load it to memory
      Document document = DocumentBuilderFactory.newInstance()
          .newDocumentBuilder().parse(file);

      // Here we normalize the document
      document.getDocumentElement().normalize();
            
      NodeList customerList = document.getElementsByTagName("customer");
      // Here we go through the list of elements
    
      for (int i = 0; i < customerList.getLength(); i++) {

        // If the element has already been found, we get out of the
        // loop.
        if (elementFound)
          break;

        if (customerList.item(i).getNodeType() == Node.ELEMENT_NODE) {

          // Here we create an Element object of each node
          Element element = (Element) customerList.item(i);

          
          // Here we check whether the given attribute of the element
          // has the expected value
          // The attribute name and its expected value were defined
          // earlier above
          Element customerIdElement = (Element) element.getElementsByTagName("customerID").item(0);          
          if (customerIdElement.getTextContent().equals(
        		  elementNameValue)) {
            
        	  document.getDocumentElement().removeChild(element);
            // Here we remove the node
        	// customerIdElement.getParentNode().removeChild(customerIdElement);
           
            elementFound = true;      

            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                // Here we normalize the DOM tree to combine all adjacent nodes
                document.normalize();
                Source source = new DOMSource(document);
                Result dest = new StreamResult(System.out);
                //transformer.transform(source, dest);

                // Here we save the updated xml document to a new file
                dest = new StreamResult(file);
                transformer.transform(source, dest);
            
          	} else {
            if (!elementFound && i == customerList.getLength() - 1)
              System.out
                  .println(elementName + " with "
                      + elementNameValue + " was not found!");
            // break;

          }
        }

      }
      
     
      
    } catch (Exception ex) {
      System.out.println(ex);
    }
	return elementFound;
	
  
}



}


