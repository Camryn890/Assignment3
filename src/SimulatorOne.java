import java.util.ArrayList;
import java.util.Scanner;
public class SimulatorOne {

    public static String number;

    public static String shops;
    public static void main(String[] args){

        Travel travel = new Travel();
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        number = scanner.nextLine();
        for(int i = 0 ; i < Integer.parseInt(number);i++ ){
            System.out.println();
            String line = scanner.nextLine();
            travel.readFile(line);}

        System.out.println();
            shops = scanner.nextLine();
            travel.numberOfshops(shops);

        System.out.println();
        String shop_node = scanner.nextLine();
        travel.shopNodes(shop_node);

        System.out.println();
        String clients = scanner.nextLine();

        travel.numberOfclients(clients);

        System.out.println();
        String client_node = scanner.nextLine();
        travel.clientNodes(client_node);

        travel.findPath();

    }
    static class Travel
    {
        public static Graph graph;
        public static ArrayList<String> client_array;
        public static ArrayList<String> taxi;
        public static Vertex path;
        public static Scanner fileIN;
        public static Scanner file1;
        public static Scanner file2;
        public static String node;
        public static String node1;
        public static double temp_min;
        public static boolean ans;
        public static ArrayList<String> multiple;
        public static String name = "";
        public static String more = "";

        Travel()
        {
            graph = new Graph();
            client_array = new ArrayList<>();
            taxi = new ArrayList<>();
            temp_min = Double.MAX_VALUE;
            multiple = new ArrayList<>();
        }



        public void readFile(String file)
        {
                fileIN = new Scanner(file);
                    String source = fileIN.next();
                        while(fileIN.hasNext()) {
                            String destination = fileIN.next();
                            String cost = fileIN.next();
                            graph.addEdge(source,destination,Double.parseDouble(cost));
                    }

        }

        public void numberOfshops(String shops)
        {
            String shop = shops;
        }

        public void shopNodes(String line)
        {
            fileIN = new Scanner(line);
            while(fileIN.hasNext())
            {
                String number= fileIN.next();
                taxi.add(number);
            }
        }

        public void numberOfclients(String clients)
        {
            String client = clients;
        }

        public void clientNodes(String lines)
        {
            fileIN = new Scanner(lines);
            while(fileIN.hasNext())
            {
                String number= fileIN.next();
                client_array.add(number);
            }
        }

        public void findPath(){

            for (String clients : client_array) {

                for (String taxis : taxi)
                {
                    calculateTaxi(clients, taxis);

                }

                System.out.print(print(clients));

                name = "";
                temp_min = Double.MAX_VALUE;
                multiple.clear();

                findDestination(clients);

                System.out.print(printShop(clients));

                name = "";
                temp_min = Double.MAX_VALUE;
                multiple.clear();
            }

        }

        public void findDestination(String clients){

                for (String taxis : taxi)
                {
                    calculateShop(taxis,clients);

                }
        }


        public static void calculateTaxi(String client,String taxis) {

            graph.dijkstra(taxis);

            Vertex path = graph.getVertex(client);

            node = FindMin(path, taxis);
        }

        public static void calculateShop(String shop,String taxi)
        {
            graph.dijkstra(taxi);

            Vertex paths = graph.getVertex(shop);

            node1 = FindMin(paths,shop);

        }

        public static String FindMin(Vertex path,String taxis)
        {
            if(path.dist < temp_min)
            {
                temp_min = path.dist;
                ans = true;
                name = taxis;
            }

            else if(path.dist == temp_min)
            {

                ans = false ;

                if(!multiple.contains(name)){
                    multiple.add(name);
                }
                multiple.add(taxis);
            }

            if(ans)
            {
                return name;
            }

            else{
            for(String node : multiple){
                more += node + " ";}
            return more ;
            }
        }

        public static String print(String clients)
        {
            String beat = "";

            beat += "client " + clients + "\n";

            file1 = new Scanner(node);

            int i = 0;


            if(node.length() == 1)
            {
                graph.dijkstra(node);

                String temp = node;

                beat += "taxi " + temp + "\n";
                beat += graph.printPath(graph.getVertex(clients)) + "\n";
                node = "";
            }

            else {

                String[] nodes = node.split(" ");
                while (file1.hasNext() && i <nodes.length) {

                    graph.dijkstra(nodes[i]);

                    String temp = file1.next();

                    beat += "taxi " + temp + "\n";
                    beat += graph.printPath(graph.getVertex(clients)) + "\n";
                    i++;
                }
                node = "";

            }
            return beat;}

        public static String printShop(String clients){

            String beat = "";

            file2 = new Scanner(node1);

            if(node1.length() == 1){

                graph.dijkstra(clients);

                String temp1 = node1;

                beat += "shop " + temp1 + "\n";
                beat += graph.printPath(graph.getVertex(temp1)) +"\n";
                node1 = "";
            }
            else {

                while (file2.hasNext()) {

                    graph.dijkstra(clients);

                    String temp1 = file2.next();

                    beat += "shop " + temp1 + "\n";
                    beat += graph.printPath(graph.getVertex(temp1)) + "\n";
                }
                node1 = "";
            }

            return beat;

        }

    }
}
