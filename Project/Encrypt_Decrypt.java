import java.util.*;
import java.lang.*;
import java.io.*;

//to read from an existing file
class ReadFromFile
{
    // filestr will store data from file
    public String filestr;
    public void read()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter file name:");
        String fileName=sc.next();
        //creating file object
        File myfile=new File(fileName);
        Scanner in= null;
        try {
            //Scanner obj to scan the file
            in = new Scanner(myfile);
            while(in.hasNextLine())
            {
                // storing data in filestr
                filestr= filestr + in.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class Encryption extends ReadFromFile
{
    // NewStr will store the encrypted string
    public String NewStr;

    public void readn(int n,String str)
    {
        // when user wants to input the string
        if(n==1)
            encrypt(str,n);
            // when encrypting from file
        else
            encryptfile();
    }

    public void encryptfile() {
        // read from file
        read();
        // encrypt string filestr with stores data from file
        encrypt(filestr, 2);

        // writing the encrypted data to new file
        File EncryptedFile=new File("EncryptedFile.txt");
        try {
            // encFile.createNewFile();
            FileWriter filewrite=new FileWriter("EncryptedFile.txt");
            filewrite.write(NewStr.substring(8));
            filewrite.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void encrypt(String str, int n)
    {
        Scanner sc=new Scanner(System.in);
        try {
            Scanner in=new Scanner(System.in);
            System.out.println("Enter key:");
            int offset=in.nextInt();
            for (int i=0;i<str.length();i++) {
                int ascii = str.charAt(i);
                if (ascii > 31 && ascii < 127) {
                    ascii = ascii + offset;
                    if (ascii > 126) {
                        ascii = ascii - 126 + 32 - 1;
                    }
                    NewStr = NewStr + (char) ascii;
                }
            }
        }
        catch(Exception ioe)
        {
            System.out.println(ioe);
        }
        if(n==1)
            System.out.println("The encrypted data is: " +NewStr.substring(4));
        else
            System.out.println("The encrypted data is: " +NewStr.substring(8));
        System.out.println("Press Enter to continue");
        sc.nextLine();
    }

}


class Decryption extends ReadFromFile
{
    public String Newstr;

    public void readn(int n,String str)
    {
        // when user wants to input the string
        if(n==1)
            decrypt(str,n);
            // when encrypting from file
        else
            decryptfile();
    }

    public void decryptfile() {
        // read from file
        read();
        // decrypt string filestr with stores data from file
        decrypt(filestr,1);

        // writing the decrypted data to new file
        File EncryptedFile=new File("DecryptedFile.txt");
        try {
            FileWriter filewrite=new FileWriter("DecryptedFile.txt");
            filewrite.write(Newstr.substring(8));
            filewrite.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void decrypt(String str, int n)
    {
        Scanner sc=new Scanner(System.in);
        try {
            System.out.println("Enter key");
            int offset=sc.nextInt();
            for (int i=0;i<str.length();i++) {
                int ascii = str.charAt(i);
                if (ascii > 31 && ascii < 127) {
                    ascii = ascii - offset;
                    if (ascii < 32) {
                        ascii = ascii + 126 - 32 + 1;
                    }
                    Newstr = Newstr + (char) ascii;
                }
            }
        }
        catch(Exception ioe)
        {
            System.out.println(ioe);
        }
        if(n==2)
            System.out.println("The decrypted data is: " +Newstr.substring(4));
        else
            System.out.println("The decrypted data is: " +Newstr.substring(8));
        System.out.println("Press Enter to continue");
        sc.nextLine();
    }
}


public class Encrypt_Decrypt {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("*********ENCRYPTION DECRYPTION PROGRAM*********");
            System.out.println();
            System.out.println("\t1.ENCRYPT DATA");
            System.out.println("\t2.DECRYPT DATA");
            System.out.println("\t3.ENCRYPT FILE");
            System.out.println("\t4.DECRYPT FILE");
            System.out.println("\t5.EXIT");
            System.out.println("SELECT OPTION:");
            int n = sc.nextInt();
            {
                if (n == 1) {
                    Encryption encode = new Encryption();
                    String s;
                    System.out.println("Enter string:");
                    sc.nextLine();
                    s=sc.nextLine();
                    encode.readn(n,s);
                } else if (n == 2) {
                    Decryption decode = new Decryption();
                    String s;
                    System.out.println("Enter string:");
                    sc.nextLine();
                    s=sc.nextLine();
                    decode.decrypt(s,n);
                }
                else if(n==3)
                {
                    Encryption encode = new Encryption();
                    String s=" ";
                    encode.readn(n,s);
                }
                else if(n==4)
                {
                    Decryption decode = new Decryption();
                    String s=" ";
                    decode.readn(n,s);
                }

                else if (n == 5)
                    System.exit(0);
                else
                    System.out.println("INVALID OPTION\n");
            }
        }
    }
}
