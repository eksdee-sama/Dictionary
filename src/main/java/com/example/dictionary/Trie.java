package com.example.dictionary;

public class Trie {
    static class TrieNode{
        boolean endOfWord;
        String meaning;
        TrieNode[] children;
        TrieNode(){
            endOfWord=false;
            meaning="";
            children=new TrieNode[26];
            for(int i=0;i<26;i++)
            {
                children[i]=null;
            }
        }
    };
    static TrieNode root;
    static void insert(String word, String meaning){
        TrieNode temp =root;
        int index;
        for (int i = 0; i < word.length() ; i++) {
            index=word.charAt(i)-'a';
            if(temp.children[index]==null){
                temp.children[index]=new TrieNode();
            }
            temp=temp.children[index];
        }
        temp.endOfWord=true;
        temp.meaning=meaning;
    }
    static String search(String word){
        TrieNode temp=root;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (temp.children[index] == null)
                return "Word not found";
        }
        return temp.meaning;
    }
    public static void main(String[] args){
        root =new TrieNode();
        insert("love","Dhokha");
        insert("lost","Sampat");
        insert("car","a locomotion vehivle");
        System.out.print(search("love"));
    }
}
