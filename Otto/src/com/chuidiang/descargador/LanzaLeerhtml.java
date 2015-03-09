package com.chuidiang.descargador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class LanzaLeerhtml {

	public static void main(String[] args){
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("Ingrese URL completa:");
            String pagina=sc.nextLine();
            URL url = new URL(pagina);
            System.out.println("Protocolo: "+url.getProtocol());
            System.out.println("Host: "+url.getHost());
            System.out.println("Fichero: "+url.getFile());
            System.out.println("Puerto: "+url.getPort());
            System.out.println("Path: "+url.getPath());
            System.out.println("Query: "+url.getQuery());
            System.out.println("Puerto por defecto: "+url.getDefaultPort());
            //Codigo HTML de la pagina
            System.out.println("\n\n\t\tCodigo HTML de la pagina");
            System.out.println("\t\t------------------------\n\n");
            InputStreamReader reader=new InputStreamReader(url.openStream());
            BufferedReader in= new BufferedReader(reader);
            String inputLine;
            while((inputLine=in.readLine())!=null)
                System.out.println(inputLine);
            in.close();
            
    } catch (Exception ex) {
            ex.printStackTrace();
     }
}
}
