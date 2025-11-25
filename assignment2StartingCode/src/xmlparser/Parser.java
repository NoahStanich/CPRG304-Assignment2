//<?xml version="1.0" encoding="UTF-8"?>
//<Test>
//  <case id="1">
//    <domain>Java</domain>
//    <count>39</count>
//  </case>
//
//  <case id="2">
//    <domain>C/C++</domain>
//    <count>45</count>
//  </case>
//</Test>

package xmlparser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import implementations.MyStack;
import implementations.MyQueue;
import java.io.FileReader;

public class Parser {
	public static void main(String[] args) {
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader reader = factory.createXMLStreamReader(new FileReader("sample1.xml"));
			MyStack<String> stack = new MyStack<>();
			MyQueue<String> errorQ = new MyQueue<>();
			MyQueue<String> extrasQ = new MyQueue<>();			
			int index = 0;
			
			while (reader.hasNext()) {
				if (reader.isStartElement() && reader.isEndElement()) {
					reader.nextTag();
					index = index + 1;
				}
				
				if (reader.isStartElement() && !(reader.isEndElement())) {
					stack.push(reader.getAttributeType(index));
					index = index + 1;
				}
				
				if (reader.isEndElement()) {
					if (reader.getAttributeType(index) == stack.peek()) {
						stack.pop();
					}
					else if (reader.getAttributeType(index) == errorQ.peek()) {
						errorQ.dequeue();
						index = index + 1;
					}
					else if (stack.size() == 0) {
						errorQ.enqueue(reader.getAttributeType(index));
					}
					else {
						stack.search(reader.getAttributeType(index));
						if (stack.contains(reader.getAttributeType(index))) {
							while (true) {
								errorQ.enqueue(stack.pop());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
