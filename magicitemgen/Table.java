/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magicitemgen;

import java.util.*;

/**
 *
 * @author chris
 */
public class Table {
    private String name;
    private String description;
    private String header;
    private ArrayList<Integer> probabilities[];
    private ArrayList<String> entries;
    
    public Table(String name,
                 String description) {
        this.name = name;
        this.description = description;
        probabilities = new ArrayList[] {new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>()};
        probabilities[0].add(new Integer("0"));
        probabilities[1].add(new Integer("0"));
        probabilities[2].add(new Integer("0"));
        entries = new ArrayList<String>();
    }
  
	public void showTable() {
            System.out.println(name+"\t\t\t"+description);
            //System.out.println("\t\t\t"+header);
            for(int i=0;i<entries.size();i++) {
                    System.out.println(probabilities[0].get(i)+"\t"+
                                       probabilities[1].get(i)+"\t"+
                                       probabilities[2].get(i)+"\t"+
                                                entries.get(i));
             }
	}
    
    public String toString() {
        return name + " - " + description + " - " + header;
    }
    
    public void runTable(int size, boolean autoRandom) {
        int rand;
        
        System.out.println(name+"\t\t"+description);
        
        System.out.print("d100: ");
        if(autoRandom) {
            rand = 1 + (int)(Math.random()*100);
            System.out.println(rand+" \t"+header);
        } else {
            Scanner scan = new Scanner(System.in);
            rand = Integer.parseInt(scan.nextLine());
			System.out.println("\t\t"+header);
        }
        
        int i;
        for(i=0;probabilities[size].get(i) < rand;i++);
        
        System.out.println("\t\t"+entries.get(i)+"\n");
        
        for(String nextTable:entries.get(i).split(" ")) {
            if(nextTable.charAt(0) == '@') {
                //System.out.println("NEXT: "+nextTable);
                ((Table)MagicItemGen.getMap().get(nextTable.substring(1))).runTable(size,autoRandom);
            }
        }
    }
    
    public void setHeader(String header) {
        if(header.split("\t")[0].equals("Minor")) header = header.substring(header.indexOf('\t')+1);
        if(header.split("\t")[0].equals("Medium")) header = header.substring(header.indexOf('\t')+1);
        if(header.split("\t")[0].equals("Major")) header = header.substring(header.indexOf('\t')+1);
        if(header.split("\t")[0].equals("d%")) header = header.substring(header.indexOf('\t')+1);
        
        entries.add(header);
        
        this.header = header;
    }
    
    public void addEntry(int probs[], String text) {
        for(int i=0;i<3;i++) {
            if(probs[i] > 0) {
                //System.out.print(probabilities[0].size()+" ");
                probabilities[i].add(probs[i]);
            } else {
                probabilities[i].add(probabilities[i].get(probabilities[i].size()-1));
            }
        }
        //System.out.println(probabilities[0].get(probabilities[0].size()-1));
        entries.add(text);
    }
    
    public String getName() {
        return this.name;
    }
}
