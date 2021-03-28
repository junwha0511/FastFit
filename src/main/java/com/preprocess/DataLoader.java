package com.preprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
MOVIES FILE
MovieID::Title::Genres

USER FILE
UserID::Gender::Age::Occupation::Zip-code

RATINGS FILE
UserID::MovieID::Rating::Timestamp
 */

public class DataLoader {
    public static HashMap<Integer, Movie> movies = new HashMap<Integer, Movie>(0);
    public static HashMap<Integer, User> users = new HashMap<Integer, User>(0);

    private final static String[] occupationTable = {"other", "academic/educator", "artist",
            "clerical/admin", "college/grad student", "customer service", "doctor/health care",
            "executive/managerial", "farmer", "homemaker", "K-12 student", "lawyer", "programmer",
            "retired", "sales/marketing", "scientist", "self-employed", "technician/engineer",
            "tradesman/craftsman", "unemployed", "writer"};


    public static void read() {
        //File stream
        File file;
        //File readers
        FileReader fileReader;// = new FileReader(ratingFile);
        BufferedReader buffReader; // = new BufferedReader(fileReader);

        String[] dirs = {"./data/movies.dat", "./data/users.dat", "./data/ratings.dat"};
        ArrayList<ArrayList<String[]>> contents = new ArrayList<ArrayList<String[]>>(0);

        try {
            //Data Reading
            for (int i = 0; i < 3; i++) {
                file = new File(dirs[i]);
                fileReader = new FileReader(file);
                buffReader = new BufferedReader(fileReader);
                contents.add(new ArrayList<String[]>(0));

                String buffer = "";
                while ((buffer = buffReader.readLine()) != null) {
                    contents.get(i).add(buffer.split("::"));
                }

                fileReader.close();
                buffReader.close();
            }


        } catch (FileNotFoundException e) {
            System.out.println("[!File NOT FOUND] Please clone git again");
        } catch (IOException e) {
            System.out.println("[!File NOT CRASHED] Please clone git again");
        }
        //preprocess movies
        for (String[] args : contents.get(0)) {
            int id = Integer.parseInt(args[0]);
            movies.put(id, new Movie(id, args[1], args[2].split("|")));
//            Movie test = movies.get(id);
//            System.out.println(test.title);
        }

        //preprocess users
        for (String[] args : contents.get(1)) {
            //Allocate genre(enum type)
            Gender g = null;
            if (args[1].equals('M')) {
                g = Gender.M;
            } else if (args[1].equals(('F'))) {
                g = Gender.F;
            }

            int id = Integer.parseInt(args[0]);
            users.put(id, new User(id, g,
                    Integer.parseInt(args[2]), occupationTable[Integer.parseInt(args[3])], args[4]));
//                User test = users.get(id);
//                System.out.println(test.id);
        }
//
//        //preprocess ratings
//        for (String[] args : contents.get(2)) {
//            //ratings.add()
//        }

    }
}
