package ex01;

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.exit(-1);
        }

        Scanner fileA = null;
        Scanner fileB = null;
        String lineA = null;
        String lineB = null;

        try {
            FileInputStream fileInputStreamA = new FileInputStream(args[0]);
            fileA = new Scanner(fileInputStreamA, StandardCharsets.UTF_8);
            try {
                lineA = fileA.nextLine();
            }
            catch (NoSuchElementException e){
                System.out.println("Similarity = 0.0");
                System.exit(0);
            }
            FileInputStream fileInputStreamB = new FileInputStream(args[1]);
            fileB = new Scanner(fileInputStreamB, StandardCharsets.UTF_8);
            try {
                lineB = fileB.nextLine();
            }
            catch (NoSuchElementException e){
                System.out.println("Similarity = 0.0");
                System.exit(0);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String result = String.format("%.3f", getSimilarity(lineA, lineB));
        System.out.print("Similarity = ");
        System.out.printf("%.4s\n", result);
    }
    
    public static double getSimilarity(String lineA, String lineB) throws IOException {

        TreeSet<String> dict = new TreeSet<>();
        List<String> fileA = null;
        List<String> fileB = null;

        try {
            fileA = Arrays.asList(lineA.split("\\s+"));
            fileB = Arrays.asList(lineB.split("\\s+"));
        } catch (NullPointerException e ) {
            System.out.println("Empty file.\nSimilarity = 0");
            System.exit(0);
        }
        
        dict.addAll(fileA);
        dict.addAll(fileB);

        FileOutputStream dictionary = null;
        try {
            dictionary = new FileOutputStream("dictionary.txt");
            Object[] arr = dict.toArray();
            for (int i = 1; i < arr.length; i++) {
                dictionary.write(arr[i].toString().getBytes(StandardCharsets.UTF_8));
                if (i < arr.length - 1) {
                    dictionary.write(',');
                    dictionary.write(' ');
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dictionary != null)
                    dictionary.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ArrayList<Integer> fileFrequencyA = new ArrayList<>(dict.size());
        ArrayList<Integer> fileFrequencyB = new ArrayList<>(dict.size());

        for (String word : dict) {
            fileFrequencyA.add(Collections.frequency(fileA, word));
            fileFrequencyB.add(Collections.frequency(fileB, word));
        }
        int num = 0;
        for (int i = 0; i < dict.size(); i++)
            num += (fileFrequencyA.get(i) * fileFrequencyB.get(i));
        double den;
        int denA = 0;
        int denB = 0;
        for (Integer n : fileFrequencyA) {
            denA += n * n;
        }
        for (Integer n : fileFrequencyB) {
            denB += n * n;
        }
        den = Math.sqrt(denA) * Math.sqrt(denB);
        if (den == 0)
            return (0);
        return num / den;
    }
}