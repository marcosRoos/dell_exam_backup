package Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CasesTable {

    private ArrayList<ArrayList<String>> Cases = new ArrayList<ArrayList<String>>();

    public CasesTable( int[] columnsToRead ) {
        readCSV( columnsToRead );
    }

    public CasesTable( ) {
        int[] all = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
        readCSV( all );
    }

    public void readCSV( int[] columnsToRead ) {
        String filePath = "src/Document//save.txt";
        File file = new File( filePath );
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader( new FileReader( filePath ) );
            while( ( line = reader.readLine() ) != null ) {
                String[] row = line.split(";");
                ArrayList<String> linha = new ArrayList<>();
                for( int i = 0; i < columnsToRead.length; i++ ) {
                    linha.add( row[columnsToRead[i]] );
                }
                Cases.add( linha );
            }
            Cases.remove(0);
        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<ArrayList<String>>  getAllCases() {
        return Cases;
    }

}
