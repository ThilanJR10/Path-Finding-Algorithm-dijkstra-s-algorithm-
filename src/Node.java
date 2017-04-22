/**
 * Created by Thilan on 4/3/2017.
 * 2015275
 * w1608479
 */

public class Node {
    private int heuristic;
    private double moveCost;
    private int x;
    private int y;
    private Node parent;

    public Node(){

    }

    public Node(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public double getMoveCost() {
        return moveCost;
    }

    public void setMoveCost(double moveCost) {
        this.moveCost = moveCost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        Node n= (Node) obj;
        if(n.getX()==x && n.getY()==y){

            return true;
        }else {
            return false;
        }

    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        //String p = (parent!=null)?" || "+parent.toString()+ " || ":"Root";
        return x + " "+ y+" "+moveCost+" "+heuristic+" ";
    }
}
