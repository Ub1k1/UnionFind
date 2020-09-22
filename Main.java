import java.util.PriorityQueue;
import java.util.ArrayList;

public class Main {
    static int[] nodes;
    static PriorityQueue<Edge> edges;

    static boolean union(int a, int b){
        boolean contained = true;
        if (find(a) == find(b)){
            contained = false;
        }
        int aSize = 0;
        int bSize = 0;

        for (int i = 0; i < nodes.length; i++){
            if (nodes[i] == find(a)){
                aSize++;
            }else if (nodes[i] == find(b)){
                bSize++;
            }
        }
        int smaller;
        int bigger;

        if (aSize > bSize){
            bigger = a;
            smaller = b;
        }else{
            smaller = a;
            bigger = b;
        }

        nodes[find(smaller)] = find(bigger);
        return contained;
    }

    static void compress(){

    }

    static int find(int i){
        int current = i;
        while (current != nodes[current]){
            current = nodes[current];
        }
        return current;
    }

    static ArrayList<Edge> minSpanTree(){
        ArrayList<Edge> tree = new ArrayList<>();
        for (int i = 0; i < 18; i++){
            boolean contained = union(edges.peek().from, edges.peek().cost);
            if (contained){
                tree.add(edges.poll());
            }else{
                edges.poll();
            }
        }

        return tree;
    }

    public static void main(String[] args) {
        nodes = new int[10];
        edges = new PriorityQueue<Edge>((a, b) -> a.cost - b.cost);

        for (int i = 0; i < 10; i++){
            nodes[i] = i;
        }

        edges.add(new Edge(0, 1, 5 ));
        edges.add(new Edge(0, 3, 9 ));
        edges.add(new Edge(0, 4, 1 ));
        edges.add(new Edge(1, 2, 4 ));
        edges.add(new Edge(1, 3, 2 ));
        edges.add(new Edge(2, 7, 4 ));
        edges.add(new Edge(2, 8, 1 ));
        edges.add(new Edge(2, 9, 8 ));
        edges.add(new Edge(3, 4, 2 ));
        edges.add(new Edge(3, 5, 5 ));
        edges.add(new Edge(3, 6, 11 ));
        edges.add(new Edge(3, 7, 2 ));
        edges.add(new Edge(4, 5, 1 ));
        edges.add(new Edge(5, 6, 7 ));
        edges.add(new Edge(6, 7, 1 ));
        edges.add(new Edge(6, 8, 4 ));
        edges.add(new Edge(7, 8, 6 ));
        edges.add(new Edge(8, 9, 0 ));

        ArrayList<Edge> tree = minSpanTree();

        for (int i = 0; i < tree.size(); i++){
            System.out.printf("from: %d to: %d cost: %d\n", tree.get(i).from, tree.get(i).to,
                    tree.get(i).cost);
        }
    }
}
