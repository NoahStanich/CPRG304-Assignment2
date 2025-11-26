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

//import javax.xml.stream.XMLInputFactory;
//import javax.xml.stream.XMLStreamReader;
import implementations.MyStack;
import implementations.MyQueue;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;   
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	public static void main(String[] args) {
		try {
			String data = Files.readString(Path.of(args[0]));
			
			MyStack<String> stack = new MyStack<>();
			MyQueue<String> errorQ = new MyQueue<>();
			MyQueue<String> extrasQ = new MyQueue<>();
			
			boolean passedTests = true;
			int index = 1;
			
			// Grabs all of the tags
			ArrayList<String> allMatches = new ArrayList<String>();
			 Matcher m = Pattern.compile("<([^>]+)>")
			     .matcher(data);
			 while (m.find()) {
			   allMatches.add(m.group());
			 }
			 allMatches.remove(0);
			// Gets the tags and then removes it to only its name
			for (int i = 0; i < allMatches.size(); i++) {
				String val = allMatches.get(i);
				boolean selfContain = false;
				if (val.charAt(val.length() - 2) == '/') {
					selfContain = true;
				}
				allMatches.set(i, val.split(" ")[0]);
				allMatches.set(i, allMatches.get(i).substring(1));
				allMatches.set(i, allMatches.get(i).replace(">", ""));
				// Adds an identifier for self closing tags
				if (selfContain) {
					allMatches.set(i, '|' + allMatches.get(i));
				}
			}
			/*
			for (String i : allMatches) {
				System.out.println(i);
			}
			*/


			// Iterates through all tags
			for (String i : allMatches) {
				
				// Opening Tags
				if ((!i.contains("/")) && !i.contains("|")) {
					stack.push(i);
					// Continues with the next item
					index++;
					
				}
				// Closing Tags
				else if (i.contains("/")) {
					String name = i.substring(1);
					String peeker = null;
					if (!errorQ.isEmpty()) {
						peeker = errorQ.peek();
					}
					// Success
					if (name.equals(stack.peek())) {
						stack.pop();
					}
					// No Opening Equivilent
					else if (stack.isEmpty()) {
						passedTests = false;
						System.out.println("Error found near tag: "+index+", </"+name+"> is not constructed correctly");
						errorQ.enqueue(name);
					}
					// Equal to last error
					else if (name.equals(peeker)) {
						passedTests = false;
						System.out.println("Error found near tag: "+index+", </"+name+"> is not constructed correctly");
						errorQ.dequeue();
					
					}
					else {
						passedTests = false;
						boolean hasItem = stack.contains(name);
						System.out.println("Error found near tag: "+index+", </"+name+"> is not constructed correctly");
						if (hasItem) {
							while (true) {
								String curItem = stack.pop();
								errorQ.enqueue(curItem);
								if (curItem.equals(name)) break;
							}
						} else {
							extrasQ.enqueue(name);
						}
						
						
					}
					
					// Continues with the next item
					index++;
				}
				// Self Closing Tags
				else  {
					// Continues with the next item
					index++;
				}
				
			}
			if (passedTests) System.out.println("XML Document is constructed properly.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
