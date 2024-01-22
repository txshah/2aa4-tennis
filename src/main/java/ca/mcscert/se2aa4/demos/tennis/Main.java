package ca.mcscert.se2aa4.demos.tennis;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    //public static int PLAYER1_STRENGTH;
    //public static int PLAYER2_STRENGTH;

    public static void main(String[] args) {

//stopping errors and ctching stuff 
        System.out.println("** Starting Tennis Counter Assistant");
        System.out.println("**** Reading Command-Line Arguments");
        
        try{
            Configuration config = configure(args);
            System.out.println(config);
            
            Match theMatch = new Match(config.p1Strength(), config.p2Strength());
            String winner = theMatch.play();

            System.out.println("Winner is:" + winner); //only one that should stay system print 

        }catch(ParseException pe){
            System.err.println(pe.getMessage());
            System.exit(1);
        }
        
        System.out.println("**** Starting game");
        System.out.println("** TODO...");
        System.out.println("** Closing Tennis Counter Assistant");
    }

    private static Configuration configure(String[] args) throws ParseException{
        
        //configure system through otions 
        //assuming idea world 
        Options options = new Options();
        options.addOption("p1", true, "Strength of Player 1 in [0,100]");
        options.addOption("p2", true, "Strength of Player 2 in [0,100]");
        
        CommandLineParser parser = new DefaultParser();
            
        CommandLine cmd = parser.parse(options, args);
        
        Integer p1s = Integer.parseInt(cmd.getOptionValue("p1","50"));
        System.out.println("****** P1's Strength is " + p1s+"/100");
        Integer p2s = Integer.parseInt(cmd.getOptionValue("p2","50"));
        System.out.println("****** P2's Strength is " + p2s+"/100");

        return new Configuration(p1s,p2s);

    }


    private record Configuration(Integer p1Strength, Integer p2Strength) {
        //what i want in specification is strength of player from 0-100
        Configuration{
            
            if (p1Strength <0 || p1Strength >100)
                throw new IllegalArgumentException("P1's Strenght is not b/w 0-100:" + p1Strength);
            
            if (p2Strength <0 || p2Strength >100)
                throw new IllegalArgumentException("P2's Strenght is not b/w 0-100:" + p2Strength);
        }
    }
}
