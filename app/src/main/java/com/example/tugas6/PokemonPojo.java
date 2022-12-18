package com.example.tugas6;

import java.util.ArrayList;

public class PokemonPojo {
	private String next;
	private Object previous;
	private int count;
	public ArrayList<ResultsItem> results;

	public String getNext(){
		return next;
	}

	public Object getPrevious(){
		return previous;
	}

	public int getCount(){
		return count;
	}

	public ArrayList<ResultsItem> getResults(){
		return results;
	}
}