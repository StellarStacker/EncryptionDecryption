import com.sun.source.doctree.EscapeTree;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
public class EncryptionDecryption {
    private static final int  SHIFT=3;
    private static Scanner scanner;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedwriter;
    private static File file;
    private EncryptionDecryption(){
        scanner = new Scanner(System.in);
        System.out.println("constructor Initialized");
    }

    public static void encryptFile(String inputPath, String outputPath) {
        File file2 = new File("encrypted.txt");
        processFile(inputPath,outputPath ,true);
        System.out.println("Encrypted File created: " + file2.getName());
    }
    public static void decryptFile(String inputPath, String outputPath) {
        File file2 = new File("encrypted.txt");
        processFile(inputPath,outputPath ,false);
        System.out.println("Encrypted File created: " + file2.getName());
    }
    public static void processFile(String inputPath,String outputPath,boolean isEncrypt){
        try{
                bufferedReader=new BufferedReader(new FileReader(inputPath));
                bufferedwriter = new BufferedWriter(new FileWriter(outputPath));
                String line = bufferedReader.readLine();
                while(line != null){
                    String encryptedMessage = isEncrypt ? encryptLine(line,SHIFT) : encryptLine(line,26-SHIFT);
                    System.out.println(encryptedMessage);
                    bufferedwriter.write(encryptedMessage);
                    bufferedwriter.newLine();
                    line=bufferedReader.readLine();
                }
                bufferedReader.close();
                bufferedwriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public static String encryptLine(String lineToBeEncrypted,int shift){
        StringBuilder encryptedMessage = new StringBuilder();
        for(int i = 0; i < lineToBeEncrypted.length() ; i++) {
            char currentCharacter = lineToBeEncrypted.charAt(i);
            if (Character.isLetter(currentCharacter)) {
               char base = Character.isLowerCase(currentCharacter) ? 'a' : 'A';
                currentCharacter =  (char) ((currentCharacter - base + shift) % 26 + base);
            }
             encryptedMessage.append(currentCharacter);
        }
        return encryptedMessage.toString();
    }

    public static void graceFullyExit(){
        scanner.close();
        System.out.println("Exiting the program...");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.exit(0);
    }
    public static void main(String[] args) {
        new EncryptionDecryption();
        while(true) {
            System.out.println("1. Encrypt a file");
            System.out.println("2. Decrypt a file");
            System.out.println("3. Exit");
            System.out.println("Enter your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1:
                    System.out.println("Enter the path of the file to be encrypted");
                    String inputPath = scanner.nextLine();
                    System.out.println("Enter the path of the file to be saved");
                    String outputPath = scanner.nextLine();
                    encryptFile(inputPath,outputPath);
                    break;
                case 2:
                    System.out.println("Enter the path of the file to be decrypted");
                    inputPath = scanner.nextLine();
                    System.out.println("Enter the path of the file to be saved");
                    outputPath = scanner.nextLine();
                    decryptFile(inputPath,outputPath);
                    break;
                case 3:
                    graceFullyExit();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

}