import models.*;
import modelsTables.AbstractTable;
import modelsTables.TableFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DB {
    private Map<String, AbstractTable> data;
    private static DB instance;
    private DB(String[] fileNames) {
        this.data = new HashMap<>();
        for (String naym : fileNames) {
            try {
                Scanner scFile = new Scanner(new File(naym));
                String[] line;
                naym = naym.split("\\.")[0];
                this.data.put(naym, TableFactory.tableCreator(naym));
                while (scFile.hasNext()) {
                    line = scFile.nextLine().split(":");
                    AbstractModel toAdd = ModelFactory.modelCreator(naym, line);
                    this.data.get(naym).addElem(toAdd);
                    this.data.get(naym).addElemTree(toAdd);
                }
            }
            catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static DB getInstance(String[] fileNames) {
        if (instance == null) {
            instance = new DB(fileNames);
        }
        return instance;
    }

    public Map<String, AbstractTable> getData() {
        return this.data;
    }

    public AbstractTable getByName(String name) {
        return this.data.get(name);
    }
}
