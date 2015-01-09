package com.paeez.test.utilities;

import com.paeez.core.model.GenericBet;
import com.paeez.core.model.Group;
import com.paeez.core.model.User;
import com.paeez.test.base.BaseTest;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shrikant on 1/1/15.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
public class SetupUtil extends BaseTest {

    private static final int NO_OF_COLUMNS_USERS = 3;
    private static final int NO_OF_COLUMNS_GROUPS = 3;
    private static final int NO_OF_COLUMNS_GLOBALBETS = 4;
    private static Logger logger = LoggerFactory.getLogger(SetupUtil.class) ;
    final static String USERS_CSV_PATH ="/testdata/Users.csv";
    final static String GROUPS_CSV_PATH ="/testdata/Groups.csv";
    final static String GLOBALBET_CSV_PATH = "/testdata/GlobalBet.csv";

    //private MongoOperations mongoOperations;

    public SetupUtil() {

    }
    public SetupUtil(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public boolean checkSeed(Class clazz) {
        if(mongoOperations.collectionExists(clazz))
            return true;
        return false;
    }
    public void setupRepo() {
        //User seed
        String path = null;
        path = this.getClass().getResource(USERS_CSV_PATH).getPath();
        Assert.assertNotNull("Input Users.csv test file missing", path);
        insertFromCSV(path, User.class, NO_OF_COLUMNS_USERS, null);

        path = this.getClass().getResource(GROUPS_CSV_PATH).getPath();
        Assert.assertNotNull("Input Groups.csv test file missing", path);

        CellProcessor[] processors = new CellProcessor[] {
                new Optional(),
                new Optional(),
                new Optional(new ParseLong()),
                new Optional()
        } ;
        insertFromCSV(path, Group.class, NO_OF_COLUMNS_GROUPS, processors);

        processors = new CellProcessor[] {
                new Optional(),
                new Optional(),
                new Optional(new ParseLong()),
                new Optional()
        } ;
        path = this.getClass().getResource(GLOBALBET_CSV_PATH).getPath();
        Assert.assertNotNull("Input GlobalBet.csv test file missing", path);
        insertFromCSV(path, GenericBet.class, NO_OF_COLUMNS_GLOBALBETS, processors);
    }
    @SuppressWarnings({ "rawtypes", "unchecked", "resource" })
    public void insertAll(Class clazz, List<? extends Object> objectsToSave) {
        if (!mongoOperations.collectionExists(clazz)) {

            mongoOperations.createCollection(clazz);
        }
        mongoOperations.insertAll(objectsToSave);
    }

    @SuppressWarnings({ "rawtypes", "unchecked", "resource" })
    public void insertFromCSV(String path, Class clazz, int noOfColumns, CellProcessor[] processors) {

        if (processors == null ) {
            processors = new CellProcessor[noOfColumns] ;
            for (int i = 0 ; i < noOfColumns ; i++) {

                processors[i] = null ;
            }
        }

        File csvFileName = new File(path) ;
        Reader fileReader;
        try {
            fileReader = new FileReader(csvFileName);
            ICsvBeanReader beanReader = new CsvBeanReader(fileReader, CsvPreference.STANDARD_PREFERENCE);

            String[] header = beanReader.getHeader(true);
            Object obj = null;

            List<Object> objList = new ArrayList<>() ;
            while ((obj = beanReader.read(clazz, header, processors)) != null) {

                objList.add(obj) ;
                logger.info("Adding Object: " + obj);
            }

            insertAll(clazz, objList) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
