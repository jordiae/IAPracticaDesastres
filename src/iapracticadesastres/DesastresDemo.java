
package iapracticadesastres;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import java.util.ArrayList;


public class DesastresDemo {
    
    public static void main(String[] args){
        //Random myRandom=new Random();
        //DesastresBoard DB =new DesastresBoard(5,1,2,1234); //modify for thingy
        //System.out.println("Original time:" + DB.getTime());
        //PrintArrayList(DB.getTravels());
        //TSPHillClimbingSearch(DB);
        //TSPSimulatedAnnealingSearch(DB);
        //experiment1();
        experiment1_2();
    }
    public static void PrintArrayList (ArrayList<ArrayList<ArrayList<Integer>>> x) {
        System.out.println("[");
        for(int i=0; i<x.size(); i++) {
            System.out.println("[");
            for (int j=0; j<x.get(i).size(); j++) {
                System.out.print("[");
                for (int k=0; k<x.get(i).get(j).size(); k++) {
                    System.out.print(x.get(i).get(j).get(k));
                }
                System.out.println("]");   
            }
            System.out.println("]");
        }
        System.out.println("]");
    }
    
    private static void experiment1() {
        try {
            Random myRandom=new Random();
            ArrayList<Integer> seeds = new ArrayList(10);
            ArrayList<Long> times1 = new ArrayList(10);
            ArrayList<Double> x = new ArrayList(10);
            ArrayList<Long> times2 = new ArrayList(10);
            ArrayList<Double> y = new ArrayList(10);
            for (int i=0; i<10; i++) {
                int seed = myRandom.nextInt(400);
                //System.out.println("Seed " + i + " is " + seed);
                //timer here
                long start_time = System.currentTimeMillis();
                DesastresBoard DB =new DesastresBoard(100,5,1,seed);
                Problem problem =  new Problem(DB,new DesastresSuccessorFunction(), new DesastresGoalTest(),new DesastresHeuristicFunction());
                Search search =  new HillClimbingSearch();
                SearchAgent agent = new SearchAgent(problem,search);
                long end_time = System.currentTimeMillis();
                //agent stuff here
                long difference = end_time-start_time;
                DesastresBoard b = (DesastresBoard) search.getGoalState();
                double time = b.getTime();
                
                // test2
                start_time = System.currentTimeMillis();
                DesastresBoardv2 DB2 =new DesastresBoardv2(100,5,1,seed);
                Problem problem2 = new Problem(DB2, new DesastresSuccessorFunctionv2(), new DesastresGoalTest(),new DesastresHeuristicFunctionv2());
                Search search2 = new HillClimbingSearch();
                SearchAgent agent2 = new SearchAgent(problem2,search2);
                end_time = System.currentTimeMillis();
                long difference2 = end_time-start_time;
                DesastresBoardv2 b2 = (DesastresBoardv2) search2.getGoalState();
                double time2 = b2.getTime(); 
                //System.out.println(time2);
                //System.out.println(difference + " " + difference2);
                /*double debug = b2.computeTotalTime();
                if(time2 != debug) System.out.println("ERRORTIME : " + time2 +' '+ debug);*/
                
                seeds.add(seed);
                x.add(time);
                times1.add(difference);
                y.add(time2);
                times2.add(difference2);
            }
            System.out.println("seeds time1 time2 exec1 exec2");
            for(int i=0; i<10; i++) {
                System.out.println(seeds.get(i) + " " + x.get(i) + " " + y.get(i) + " " + times1.get(i) + " " + times2.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void experiment1_2() {
        try {
            Random myRandom=new Random();
            ArrayList<Integer> seeds = new ArrayList(10);
            ArrayList<Long> times1 = new ArrayList(10);
            ArrayList<Double> x = new ArrayList(10);
            ArrayList<Long> times2 = new ArrayList(10);
            ArrayList<Double> y = new ArrayList(10);
            for (int i=0; i<10; i++) {
                int seed = myRandom.nextInt(400);
                //System.out.println("Seed " + i + " is " + seed);
                //timer here
                long start_time = System.currentTimeMillis();
                DesastresBoardv2 DB =new DesastresBoardv2(100,5,1,seed);
                Problem problem =  new Problem(DB,new DesastresSuccessorFunctionv2(), new DesastresGoalTest(),new DesastresHeuristicFunctionv2());
                Search search =  new HillClimbingSearch();
                SearchAgent agent = new SearchAgent(problem,search);
                long end_time = System.currentTimeMillis();
                //agent stuff here
                long difference = end_time-start_time;
                DesastresBoardv2 b = (DesastresBoardv2) search.getGoalState();
                double time = b.getTime();
                
                // test2
                start_time = System.currentTimeMillis();
                DesastresBoardv2 DB2 =new DesastresBoardv2(100,5,1,seed);
                Problem problem2 = new Problem(DB2, new DesastresSuccessorFunctionv3(), new DesastresGoalTest(),new DesastresHeuristicFunctionv2());
                Search search2 = new HillClimbingSearch();
                SearchAgent agent2 = new SearchAgent(problem2,search2);
                end_time = System.currentTimeMillis();
                long difference2 = end_time-start_time;
                DesastresBoardv2 b2 = (DesastresBoardv2) search2.getGoalState();
                double time2 = b2.getTime(); 
                //System.out.println(time2);
                //System.out.println(difference + " " + difference2);
                /*double debug = b2.computeTotalTime();
                if(time2 != debug) System.out.println("ERRORTIME : " + time2 +' '+ debug); //DEBUGGER!*/
                
                seeds.add(seed);
                x.add(time);
                times1.add(difference);
                y.add(time2);
                times2.add(difference2);
            }
            System.out.println("seeds time1 time2 exec1 exec2");
            for(int i=0; i<10; i++) {
                System.out.println(seeds.get(i) + " " + x.get(i) + " " + y.get(i) + " " + times1.get(i) + " " + times2.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void TSPHillClimbingSearch(DesastresBoard TSPB) {
        System.out.println("\nTSP HillClimbing  -->");
        try {
            Problem problem =  new Problem(TSPB,new DesastresSuccessorFunction(), new DesastresGoalTest(),new DesastresHeuristicFunction());
            Search search =  new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            DesastresBoard b = (DesastresBoard) search.getGoalState();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
            System.out.println("Final time:" + b.getTime());
            PrintArrayList(b.getTravels());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void TSPSimulatedAnnealingSearch(DesastresBoard TSPB) {
        System.out.println("\nTSP Simulated Annealing  -->");
        try {
            Problem problem =  new Problem(TSPB,new DesastresSuccessorFunctionSA(), new DesastresGoalTest(),new DesastresHeuristicFunction());
            SimulatedAnnealingSearch search =  new SimulatedAnnealingSearch(2000,100,5,0.001);
            //search.traceOn();
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }
        
    }
    
    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i).toString();
            System.out.println(action);
        }
    }
    
    
}


