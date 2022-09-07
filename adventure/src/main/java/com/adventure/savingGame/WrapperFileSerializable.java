package com.adventure.savingGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
    Wrapper class in order to
    read and write objects from/into a file.
*/
public class WrapperFileSerializable {
    public static <T> long write(String nameFileRec, T objectRec) throws FileNotFoundException, IOException {
        return WrapperFileSerializable.write(nameFileRec, objectRec, true);
    }

    public static <T> T read(String nameFileRec) throws FileNotFoundException, IOException, ClassNotFoundException {
        return WrapperFileSerializable.read(nameFileRec, 0);
    }

    @SuppressWarnings("unchecked")
    public static <T> T read(String nameFileRec, long posRec)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream inFile = new FileInputStream(nameFileRec);
        ObjectInputStream inStream = null;

        inFile.getChannel().position(posRec);
        inStream = new ObjectInputStream(inFile);

        T toReturn = (T) inStream.readObject();
        inFile.close();

        return toReturn;
    }

    public static <T> long write(String nameFileRec, T objectRec, boolean appendRec)
            throws FileNotFoundException, IOException {
        long oldSize = Files.size(Paths.get(nameFileRec));
        long newSize = 0;

        FileOutputStream outFile = new FileOutputStream(nameFileRec, appendRec);
        ObjectOutputStream outStream = new ObjectOutputStream(outFile);

        outStream.writeObject(objectRec);
        outFile.close();

        newSize = Files.size(Paths.get(nameFileRec));

        return newSize - oldSize;
    }
}