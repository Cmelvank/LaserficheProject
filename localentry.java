
package localent;

import java.util.ArrayList;
import java.io.*;
import java.io.FileReader;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class localentry 
{
    public static ArrayList<String> localentries = new ArrayList<String>();
    private String path = "\\this\\is\\a\\path\\.json";
    
    public localentry(String directory)
    {
        this.path = directory;
    }
    
    public void parseJSON() throws IOException, ParseException
    {
        JSONParser jsonparser = new JSONParser();
        FileReader reader = new FileReader(this.path);
        
        Object obj = jsonparser.parse(reader);
        
        JSONObject jsonobj = (JSONObject)obj;        
        JSONArray process_elem = (JSONArray)jsonobj.get("processing_elements");
        
        for(int i = 0; i < process_elem.size(); i++)
        {
            JSONObject element = (JSONObject)process_elem.get(i);        
            
            JSONArray input_en = (JSONArray)element.get("input_entries");
            
            for(int j = 0; j < input_en.size(); j++)
            {
                JSONObject entry = (JSONObject)input_en.get(j);
                
                if(((String)entry.get("type")).equals("local"))
                {
                    localentries.add((String)entry.get("path"));
                }
            }
        }
        
        
       
    }
        
      
}
