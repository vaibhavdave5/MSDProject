package algorithms;

import java.util.List;
import parser.Node;

public interface Algorithm {
	public double computeSimilarity(List<Node> list1, List<Node> list2);
}
