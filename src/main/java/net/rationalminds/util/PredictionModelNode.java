/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rationalminds.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vaibhav Singh
 */
public class PredictionModelNode {

    public char charcter;
    boolean isDigit = false;
    public int level;
    public List<PredictionModelNode> childern;
    public PredictionModelNode parent;
    public boolean explictDateFragment=false;

    public PredictionModelNode getChild(char c) {
        if (childern == null) {
            return null;
        }
        for (PredictionModelNode child : childern) {
            if (c == child.charcter) {
                return child;
            }
        }
        return null;
    }

    public void addChild(PredictionModelNode child) {
        if (childern == null) {
            childern = new ArrayList<PredictionModelNode>();
        }
        child.level=this.level+1;
        childern.add(child);
    }
    
    public boolean hasChildern(){
    	 if (childern == null) {
             return false;
         }else{
        	 return true;
         }
    }
    
    public int childrenCount(){
    	 if (childern == null) {
             return 0;
         }else{
        	 return childern.size();
         }
    }

	@Override
	public String toString() {
		return "PredictionModelNode [charcter=" + charcter + ", isDigit=" + isDigit + ", level=" + level + ", parent=" + parent + ", explictDateFragment=" + explictDateFragment
				+ ", childern=" + childern + "]";
	}

  
    
    
}
