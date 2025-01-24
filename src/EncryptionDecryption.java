import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
public class EncryptionDecryption {
    private static Scanner scanner;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedwriter;
    private static File file;
    private EncryptionDecryption(){
        System.out.println("constructor Initialized");
    }

    public static void encryptFile() {
        File file2 = new File("encrypted.txt");
        System.out.println("hii");
        encryptedFile();
        System.out.println("Encrypted File created: " + file2.getName());
    }
    public static void encryptedFile(){
        try{
                bufferedReader=new BufferedReader(new FileReader("D:\\StellarEncryption\\untitled\\src\\fileToBeEncrypted.txt"));
                bufferedwriter = new BufferedWriter(new FileWriter("D:\\StellarEncryption\\untitled\\src\\encrypted.txt"));
                String line = bufferedReader.readLine();
                while(line != null){
                    String encryptedMessage = encryptLine(line,3);
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
    public static void decryptFile(){
        File file2 = new File("decrypted.txt");
        System.out.println("hii");
        decryptedFile();
        System.out.println("Decrypted File created: " + file2.getName());
    }
    public static void decryptedFile(){
        try {
                bufferedReader=new BufferedReader(new FileReader("D:\\StellarEncryption\\untitled\\src\\encrypted.txt"));
                bufferedwriter = new BufferedWriter(new FileWriter("D:\\StellarEncryption\\untitled\\src\\decrypted.txt"));
                String line = bufferedReader.readLine();
                while( line != null){
                    String decryptedMessage = decryptLine(line,26 - 3);
                    System.out.println(decryptedMessage);
                    bufferedwriter.write(decryptedMessage);
                    bufferedwriter.newLine();
                    line=bufferedReader.readLine();
                }
                bufferedReader.close();
                bufferedwriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public static String decryptLine(String line , int shift){
            StringBuilder decryptedMessage = new StringBuilder();
            for(int i = 0 ; i < line.length() ; i++){
                char currentCharacter = line.charAt(i);
                if(Character.isLetter(currentCharacter)){
                    char base = Character.isLowerCase(currentCharacter) ? 'a' : 'A';
                    currentCharacter = (char) ((currentCharacter - base + shift) % 26 + base);
                }
                decryptedMessage.append(currentCharacter);
            }
            return decryptedMessage.toString();
        }

    public static void main(String[] args) {
        new EncryptionDecryption();
        encryptFile();
        decryptFile();

    }

}