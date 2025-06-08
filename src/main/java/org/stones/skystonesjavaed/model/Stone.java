package org.stones.skystonesjavaed.model;

import java.io.Serializable;

public class Stone implements Serializable {

    public static final int WIDTH = 125;
    public static final int HEIGHT = 125;

    private String name;
    private int north;
    private int south;
    private int east;
    private int west;
    private String element;
    private boolean stoneColor;

    public Stone(String name, int north, int south, int east, int west, String element, boolean stoneColor){
        this.name = name;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.element = element;
        this.stoneColor = stoneColor;
    }

    public Stone(String name, int north, int south, int east, int west, boolean stoneColor){
        this.name = name;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.stoneColor = stoneColor;
    }

    public Stone(){
        this.name = "None";
        this.north = 0;
        this.south = 0;
        this.east = 0;
        this.west = 0;
        this.element = "None";
    }

    public String getName() {return name;}
    public int getNorth() {return north;}
    public int getSouth() {return south;}
    public int getEast() {return east;}
    public int getWest() {return west;}
    public String getElement() {return element;}
    public boolean getStoneColor() {return stoneColor;}

    //Just for model testing
    public void setStoneColor(boolean stoneColor) {this.stoneColor = stoneColor;}

    public String toString(){
        return name + "," + north + "," + south + "," + east + "," + west + "," + stoneColor;
    }

}
