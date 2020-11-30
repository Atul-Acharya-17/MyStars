package controller;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;

import static boundary.MyStarsInterface.*;

/**
 * Class that is responsible for saving lists of objects in a file and loading objects from a file
 *
 * @author Anon
 */

public class FileMgr {
    /**
     * Loads a list of objects from a particular file
     *
     * @param fileName name of the required file
     * @return list of objects
     */
    public ArrayList<Object> loadObjects(String fileName) {
        ArrayList<Object> objects = new ArrayList<>();
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        // Read objects
        try {
            fileInputStream = new FileInputStream(new File(fileName));
            objectInputStream = new ObjectInputStream(fileInputStream);
            objects = (ArrayList) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (EOFException e) {
            System.out.println(RED + "System error... End of File exception" + RESET);
        } catch (FileNotFoundException e) {
            System.out.println(RED + "System error... File not found" + RESET);
        } catch (
                ClassNotFoundException e) {
            System.out.println(RED + "System error... Class not found" + RESET);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }

    /**
     * Saves a list of objects to a particular file
     *
     * @param objects  list of objects that need to be saved
     * @param fileName name of the required file
     */
    public void saveObjects(ArrayList<Object> objects, String fileName) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        // Write objects to file
        try {
            fileOutputStream = new FileOutputStream(new File(fileName));
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objects);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (EOFException e) {
            System.out.println(RED + "System error... End of File Exception" + RESET);
        } catch (FileNotFoundException e) {
            System.out.println(RED + "System error... File not found" + RESET);
        } catch (IOException e) {
            System.out.println(RED + "System error... IOException" + RESET);
        }
    }

}