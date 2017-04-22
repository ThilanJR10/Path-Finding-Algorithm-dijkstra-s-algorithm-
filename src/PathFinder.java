import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thilan on 4/3/2017.
 * 2015275
 * w1608479
 */


public class PathFinder {
    List<Node> openList = new ArrayList<>();
    List<Node> closedList = new ArrayList<>();
    List<Node> path = new ArrayList<>();
    Node start;
    Node end;

    public PathFinder(Node start,Node end){
        this.start=start;
        this.end=end;
    }
    public void dijkstra (boolean a[][]){

        start.setMoveCost(0);
        // start.setHeuristic(manhatanDistance(start.getX(),start.getY(),end.getX(),end.getY()));
        //   start.setHeuristic(euclideanDistance(start.getX(),start.getY(),end.getX(),end.getY()));
        //start.setHeuristic(chebyshevDistance(start.getX(),start.getY(),end.getX(),end.getY()));
        closedList.add(start);
        try {
            findNeighbours(a,start);
        }catch (Exception e){
        }
        while (true) {
            Node node = openList.get(0);
            for (Node n : openList) {
                if (   node.getMoveCost()>n.getMoveCost()) {//node.getMoveCost() > n.getMoveCost() &&
                    node = n;
                }
            }

            openList.remove(node);
            closedList.add(node);


            try {
                findNeighbours(a,node);
            }catch (Exception e){
                break;
            }
        }

        for (Node n:closedList ) {
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.filledSquare(n.getY(),a.length-n.getX()-1, .5);

        }




    }



    public void findNeighbours(boolean[][] a,Node node)throws Exception{
        calculateCost(node.getX(), node.getY() - 1, a, node);   // down
        calculateCost(node.getX(), node.getY() + 1, a, node);   // up
        calculateCost(node.getX() - 1, node.getY(), a, node);   //left
        calculateCost(node.getX() + 1, node.getY(), a, node);   //right

     //diaganally =====================================================================================


                calculateCost(node.getX() - 1, node.getY() - 1, a, node);

                calculateCost(node.getX() + 1, node.getY() - 1, a, node);

                calculateCost(node.getX() + 1, node.getY() + 1, a, node);

                calculateCost(node.getX() - 1, node.getY() + 1, a, node);


  // ==============================================================================================
    }



    public void drawpath(boolean a[][]){
        path.add(end);

        Node n= closedList.get(closedList.size()-1);
        path.add(n);

        while (true){
            n=n.getParent();
            path.add(n);
            if(n.equals(start))break;
        }


        Node s=path.get(0);
        List<Node> shortestPath = new ArrayList<>();
        shortestPath.add(s);
        for (int i=1;i<path.size();i++){
      /*    System.out.println(path.get(i));
                 if(!(s.getX()==path.get(i).getX() ||  s.getY()==path.get(i).getY())){
                     System.out.println(path.get(i));
                     if(path.get(i).getY()>s.getY() && path.get(i).getX()<s.getX()){
                         System.out.println("H");
                         topToBottomPathSmooth(a,path.get(i),shortestPath);
                    }else if(path.get(i).getY()<s.getY() && path.get(i).getX()<s.getX()){
                         System.out.println("S");
                         topToBottomPathSmooth(a,path.get(i),shortestPath);
                    }else if(path.get(i).getY()>s.getY() && path.get(i).getX()>s.getX()){
                         System.out.println("P");
                          bottomToTopPathSmooth(a,path.get(i),shortestPath);
                      }else {
                         System.out.println("D");
                         bottomToTopPathSmooth(a,path.get(i),shortestPath);
                      }

                }

*/

            s=path.get(i);
            shortestPath.add(s);
        }
        for (Node node:shortestPath ) {
            StdDraw.setPenColor(StdDraw.PINK);
            StdDraw.filledSquare(node.getY(),a.length-node.getX()-1, .5);
        }

        for (Node node:shortestPath ) {
            StdDraw.setPenColor(StdDraw.BLUE);
            try {
                StdDraw.line(node.getParent().getY(), a.length-node.getParent().getX()-1, node.getY(), a.length-node.getX()-1);
            }catch (Exception e){}

        }



        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.filledCircle(start.getY(),a.length-start.getX()-1, .5);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(start.getY(),a.length-start.getX()-1,"A");
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(end.getY(),a.length-end.getX()-1, .5);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(end.getY(),a.length-end.getX()-1,"B");


    }
/*

    private void topToBottomPathSmooth(boolean [][]a ,Node path,List<Node> shortestPath){

            try {
                if (a[path.getX()+1][path.getY()]) {
                    shortestPath.add(new Node(path.getX()+1, path.getY()));
                }else {
                    shortestPath.add(new Node(path.getX(), path.getY()-1));
                }
            }catch (ArrayIndexOutOfBoundsException e){
                if (a[path.getX()][path.getY()-1]){
                    shortestPath.add(new Node(path.getX(), path.getY()-1));
                }
            }
    }

    private void bottomToTopPathSmooth(boolean [][]a ,Node path,List<Node> shortestPath){
        try {
            if (a[path.getX()-1][path.getY()]) {
                shortestPath.add(new Node(path.getX()-1, path.getY()));
            }else {
                shortestPath.add(new Node(path.getX(), path.getY()+1));
            }
        }catch (ArrayIndexOutOfBoundsException e){
            if (a[path.getX()][path.getY()+1]){
                shortestPath.add(new Node(path.getX(), path.getY()+1));
            }
        }
    }

*/

    public void calculateCost(int x,int y,boolean[][] a,Node parent) throws Exception{
        int N=a.length;
        if (x < 0 || x >= N) return;
        if (y < 0 || y >= N) return;
        if(!a[x][y])return;

        Node n = new Node(x, y);
        //int gValue = manhatanDistance(parent.getX(),parent.getY(),x,y);
        double gValue =euclideanDistance(parent.getX(),parent.getY(),x,y);
       // int gValue=chebyshevDistance(parent.getX(),parent.getY(),x,y);
        if (n.equals(end)){
            end.setParent(parent);
            end.setMoveCost(gValue + parent.getMoveCost());
            end.setParent(parent);
            throw new Exception("Goal Found");
        }

        if (!(openList.contains(n) || closedList.contains(n))) {
              // n.setHeuristic(manhatanDistance(x, y, end.getX(), end.getY()));
            //  n.setHeuristic(euclideanDistance(x, y, end.getX(), end.getY()));
            //n.setHeuristic(chebyshevDistance(x, y, end.getX(), end.getY()));
            n.setParent(parent);
            n.setMoveCost(gValue + parent.getMoveCost());
            openList.add(n);
        }

    }



    public  int manhatanDistance(int x1, int y1, int x2, int y2){
        int distance = Math.abs(x1-x2) + Math.abs(y1-y2);
        return distance;
    }


    public  double euclideanDistance(int x1, int y1, int x2, int y2){
        double distance = Math.round((Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2)))* 10)/10.0;
        return distance;
    }

    public int chebyshevDistance(int x1, int y1,int x2 ,int y2){
        int distance = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
        return distance;

    }

}
