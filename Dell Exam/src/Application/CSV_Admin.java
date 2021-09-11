package Application;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class CSV_Admin {

    public CSV_Admin() {

        String filePath = "src/Document//save.txt";
        File file = new File( filePath );

        if( !file.exists() ) {
            ArrayList<ArrayList<String>> Cases = new ArrayList<ArrayList<String>>();
            String path = "src/Document//data.csv";
            BufferedReader reader = null;
            String line = "";

            try { // Carregar dados para variáveis
                reader = new BufferedReader( new FileReader( path ) );
                while( ( line = reader.readLine() ) != null ) {
                    ArrayList<String> row = new ArrayList<>(Arrays.asList(line.split(";")));
                    Cases.add( row );
                }
                Cases.remove(0);

                ArrayList<ArrayList<String>> filtered = new ArrayList<ArrayList<String>>();
                for( ArrayList<String> linha : Cases )  { // Filtrar dados

                        LocalDate solicitacao = LocalDate.parse(linha.get(4));
                        LocalDate autorizacao = LocalDate.parse(linha.get(14).substring(0, 10));
                        LocalDate internacao = LocalDate.parse(linha.get(15).substring(0, 10));
                        LocalDate alta;
                        if( !linha.get(16).equals("")) {
                            alta = LocalDate.parse(linha.get(16).substring(0, 10));
                        } else {
                            // Essa adição não será salva, ela serve somente para tolerar a comparação do filtro sem causar erros
                            alta = LocalDate.now().plusDays(1);
                        }

                        if (       Integer.parseInt(linha.get(18)) < 0
                                || Integer.parseInt(linha.get(18)) > 100000
                                || solicitacao.isAfter(autorizacao)
                                || autorizacao.isAfter(internacao)
                                || internacao.isAfter(alta)) {
                            filtered.add(linha);
                        }
                }
                Cases.removeAll( filtered );

                try {
                    if( file.createNewFile() ) {
                        System.out.println( "Arquivo Criado" );
                        //-FileWriter saver = new FileWriter(filePath, true);
                        PrintStream saver = new PrintStream( file );
                        for( ArrayList<String> row : Cases ) {
                            String sRow = String.join( ";", row );
                            saver.println( sRow );
                        }
                    } else {
                        System.out.println( "Erro : O arquivo não pôde ser criado" );
                    }

                } catch ( Exception e ) {
                    e.printStackTrace();
                }
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

    }

}
