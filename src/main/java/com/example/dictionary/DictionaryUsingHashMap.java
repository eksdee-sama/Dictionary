package com.example.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictionaryUsingHashMap {
    private HashMap<String,String > dictionary;
    public DictionaryUsingHashMap()
    {
        dictionary=new HashMap<String,String>();
        addListOfWords();
    }
    public boolean addWord(String Word,String meaning)
    {
        String word=Word.toLowerCase();
        dictionary.put(word,meaning);
        return true;
    }
    public ArrayList<String> getSuggestions(String Word)
    {
        String word=Word.toLowerCase();
        ArrayList<String> suggestions=new ArrayList<String>();
        int i=0;
        for(Map.Entry<String,String> entry: dictionary.entrySet())
        {
            if(word.compareTo(entry.getKey().substring(0,
                    Math.min(word.length(),entry.getKey().length() )))==0) {
                suggestions.add(entry.getKey().substring(0,1).toUpperCase()+entry.getKey().substring(1));

            }
            if(i==4) break;
        }
        return suggestions;
    }
    public String findMeaning(String Word)
    {
        String word=Word.toLowerCase();
        if(!dictionary.containsKey(word))
            return "Given word not found";
        else {
            return dictionary.get(word);
        }
    }
    private void addListOfWords()
    {
        addWord("Love","Pain");
        addWord("Life","Zhand");
    }
}
