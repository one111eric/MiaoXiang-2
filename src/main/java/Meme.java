import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import org.json.*;


public class Meme {
    public Meme()
    {
    	Meme=new HashMap<String,Integer>();
    }
	public void ReadMemes() throws JSONException
	{   
		String content="";
		try {
			content = new Scanner(new File("src\\main\\resources\\memes.json")).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONObject obj=new JSONObject(content);
		JSONArray arr=obj.getJSONArray("memes");
		for(int i=0;i<arr.length();i++)
			{
			String memestring=arr.getJSONObject(i).getString("memeString");
			Meme.put(memestring,0);
			}
	}
	public void SortMemes()
	{
		SortedMeme=new TreeMap<String,Integer>(Meme);
	}
	public void ScoreMemes()
	{
		Random random=new Random();
		
		for(Map.Entry<String, Integer> entry:SortedMeme.entrySet())
		{
			int i=random.nextInt(10)+1;
			entry.setValue(i);
		}
	}
	public void PrintMemes()
	{
		for(Map.Entry<String, Integer> entry:SortedMeme.entrySet())
		{
			System.out.println(entry.getKey()+": "+entry.getValue());
		}
	}
	public void WriteMemes()
	{
		JSONObject obj=new JSONObject();
		
		JSONArray list=new JSONArray();
		
		for(Map.Entry<String, Integer> entry:SortedMeme.entrySet())
		{
			Map<String,String> map=new LinkedHashMap<String,String>();
			map.put("memeString", entry.getKey());
			map.put("Score",entry.getValue().toString());
			
			list.put(map);
			//list.put("{"+entry.getKey()+": "+entry.getValue().toString());
		}
		obj.put("memes", list);
		String output=obj.toString();
		try {
			FileWriter writer=new FileWriter(new File("src\\main\\resources\\memes.json"));
			writer.write(output);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			
		}
		
	}
	private HashMap<String,Integer> Meme;
    private TreeMap<String,Integer> SortedMeme;

public static void main(String[] args) throws JSONException
{
	Meme a=new Meme();
	a.ReadMemes();
	a.SortMemes();
	a.ScoreMemes();
	a.PrintMemes();
	a.WriteMemes();
}
}
