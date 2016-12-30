package com.example.xml;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import android.util.Log;

public class MyContentHandler implements ContentHandler {

	String hisname, address, money, sex, status;
	String tagName;
	
	boolean isStartTag = true;
	
	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		Log.d("debug", "------ begin ------");
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		Log.d("debug", "------ end ------");
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
	
		
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		isStartTag = true;
		
		tagName = localName;
		if (localName.equals("worker")){
			for (int i = 0; i < atts.getLength(); i++){
				Log.d("debug", atts.getLocalName(i) + "=" + atts.getValue(i));
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		isStartTag = false;
		
		if (localName.equals("worker")){
			this.printout();
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		if (!isStartTag)
		{
			return;
		}
		
		if (tagName.equals("name")){
			hisname = new String(ch, start, length);
		} 
		else if (tagName.equals("sex")){
			sex = new String(ch, start, length);
		} 
		else if (tagName.equals("status")){
			status = new String(ch, start, length);
		}
		else if (tagName.equals("address")){
			address = new String(ch, start, length);
		}
		else if (tagName.equals("money")){
			money = new String(ch, start, length);
		}
			
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	private void printout(){
		System.out.print("name: ");
		System.out.println(hisname);
		System.out.print("sex: ");
		System.out.println(sex);
		System.out.print("status: ");
		System.out.println(status);
		System.out.print("address: ");
		System.out.println(address);
		System.out.print("money: ");
		System.out.println(money);
	}
}
