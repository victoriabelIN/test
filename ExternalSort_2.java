import java.io.*;

import java.util.ArrayList;

import java.util.List;

 
public class ExternalSort
{
  
 
public static void main (String[]args)
  {
    
String inputFilename = "input.txt";	
    String outputFilename = "sorted.txt";
    int chunkSize = 500;
    
externalSort (inputFilename, outputFilename, chunkSize);
  
} 
 
public static void externalSort (String inputFilename,
					 String outputFilename, int chunkSize)
  {
    
try
    {
      
List < String > tempFiles = new ArrayList <> ();
      
BufferedReader reader =
	new BufferedReader (new FileReader (inputFilename));
      
 
List < String > chunk = new ArrayList <> ();
      
String line;
      
while ((line = reader.readLine ()) != null)
	{
	  
chunk.add (line);
	  
if (chunk.size () >= chunkSize)
	    {
	      
chunk.sort (null);
	      
String tempFilename = "temp" + tempFiles.size () + ".tmp";
	      
tempFiles.add (tempFilename);
	      
 
BufferedWriter writer =
		new BufferedWriter (new FileWriter (tempFilename));
	    
for (String item:chunk)
		{
		  
writer.write (item);
		  
writer.newLine ();
		
}
	      
writer.close ();
	      
 
chunk.clear ();
	    
}
	
}
      
reader.close ();
      
 
if (!chunk.isEmpty ())
	{
	  
chunk.sort (null);
	  
String tempFilename = "temp" + tempFiles.size () + ".tmp";
	  
tempFiles.add (tempFilename);
	  
 
BufferedWriter writer =
	    new BufferedWriter (new FileWriter (tempFilename));
	
for (String item:chunk)
	    {
	      
writer.write (item);
	      
writer.newLine ();
	    
}
	  
writer.close ();
	
}
      
 
List < BufferedReader > tempReaders = new ArrayList <> ();
    
for (String tempFile:tempFiles)
	{
	  
tempReaders.add (new BufferedReader (new FileReader (tempFile)));
	
}
      
 
BufferedWriter writer =
	new BufferedWriter (new FileWriter (outputFilename));
      
while (!tempReaders.isEmpty ())
	{
	  
String smallestLine = null;
	  
int smallestIndex = -1;
	  
 
for (int i = 0; i < tempReaders.size (); i++)
	    {
	      
BufferedReader tempReader = tempReaders.get (i);
	      
String tempLine = tempReader.readLine ();
	      
if (tempLine != null)
		{
		  
if (smallestLine == null
		       || tempLine.compareTo (smallestLine) < 0)
		    {
		      
smallestLine = tempLine;
		      
smallestIndex = i;
		    
}
		
}
	      else
		{
		  
tempReader.close ();
		  
tempReaders.remove (i);
		  
i--;
		
}
	    
}
	  
 
if (smallestLine != null)
	    {
	      
writer.write (smallestLine);
	      
writer.newLine ();
	    
}
	
}
      
 
writer.close ();
    
 
for (String tempFile:tempFiles)
	{
	  
new File (tempFile).delete ();
	
}
      
 
System.out.println ("External sorting completed.");
    
}
    catch (IOException e)
    {
      
e.printStackTrace ();
    
}
  
}

}
