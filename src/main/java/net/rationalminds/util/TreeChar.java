package net.rationalminds.util;

/**
 * To create a bit map type structure for lietrals of the tree
 * @author Vaibhav Singh
 *
 */
public class TreeChar implements Comparable<TreeChar>{
	
	public String symbol="";
	
	public int leftSpace=0;
	
	public int level=0;

	public TreeChar(String symbol, int leftSpace, int level) {
		super();
		this.symbol = symbol;
		this.leftSpace = leftSpace;
		this.level = level;
	}

	public TreeChar(String symbol) {
		super();
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "TreeChar [symbol=" + symbol + ", level=" + level+", leftSpace=" + leftSpace +  "]";
	}

	@Override
	public int compareTo(TreeChar arg0) {
		if(level>arg0.level){
			return 1;
		}
		if(level<arg0.level){
			return -1;
		}
		return 0;
	}
	
	
	

}
