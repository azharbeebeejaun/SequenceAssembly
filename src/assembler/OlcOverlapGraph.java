package assembler;

import graph.overlap.*;
import interfaces.IGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class OlcOverlapGraph extends OverlapGraph implements IGraph {
	public OlcOverlapGraph(int minimumOverlapLength) {
		super(minimumOverlapLength);
	}
	
	public void constructGraph(File readsFile, int minimumOverlapLength) 
	{
		try (Scanner fileIn = new Scanner(readsFile)) 
		{
			String currentLine = "";
			StringBuilder read = new StringBuilder();
			int readCount = 0;
			new OlcOverlapGraph(minimumOverlapLength);
			
			while (fileIn.hasNextLine())
			{
				currentLine = fileIn.nextLine();

				if (currentLine.startsWith(">")) {
					if (!read.toString().equals("")) {
						this.addNode(read.toString().toUpperCase());
						readCount++;
					}
					read = new StringBuilder();
				} 
				else
					read.append(currentLine.trim());
			}

			if (!read.toString().equals("")) {
				this.addNode(read.toString().toUpperCase());
				readCount++;
			}

			System.out.println("Number of reads processed: " + readCount);
		}
		catch (FileNotFoundException e) {
			System.err.println("File not found: " + readsFile);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void generateContigs(String outputFile) 
    {
		HashMap<Node, LinkedList<DirectedEdge>> adjacencyList;
		LinkedList<Node> nodeList;
		Node startingNode, aNode;
		Iterator<Node> i;
		BufferedWriter writer;
		int contigCount;
		
		try {
			writer = new BufferedWriter(new FileWriter(new File(outputFile)));
			nodeList = new LinkedList<Node>();
			adjacencyList = this.getAdjacencyList();
			contigCount = 0;
			i = adjacencyList.keySet().iterator();
			
			while (i.hasNext()) {
				startingNode = i.next();

				if (!startingNode.isVisited()) {
					nodeList.add(startingNode);
					startingNode.setVisited();
					while (!nodeList.isEmpty()) {
						aNode = getUnvisitedNeighbour(nodeList.getLast());

						if (aNode == null) {
							contigCount++;
							printContigInFastaFormat(writer, (LinkedList<Node>) nodeList.clone(), contigCount);
							nodeList.removeLast();
						} else {
							aNode.setVisited();
							nodeList.add(aNode);
						}
					}
				}
			}
			
			System.out.println("Number of contigs generated: " + contigCount);
			writer.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("File not found: " + outputFile);
		}
		catch (IOException e) {
			System.err.println("OlcOverlapGraph:generateContigs file "+outputFile+" cannot be created or opened");
		}
    }
}