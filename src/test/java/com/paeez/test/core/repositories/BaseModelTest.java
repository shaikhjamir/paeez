//package com.paeez.test.core.repositories;
//
//import com.paeez.Application;
//import com.paeez.core.model.User;
//import org.junit.Assert;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.supercsv.cellprocessor.ift.CellProcessor;
//import org.supercsv.io.CsvBeanReader;
//import org.supercsv.io.ICsvBeanReader;
//import org.supercsv.prefs.CsvPreference;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.Reader;
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
////@ContextConfiguration(locations = { "classpath:paeez_mongodb.xml" })
//public class BaseModelTest {
//	private static final int NO_OF_COLUMNS = 3;
//	private static Logger logger = LoggerFactory.getLogger(BaseModelTest.class) ;
//	final static String USERS_CSV_PATH ="/testdata/Users.csv";
//
//	@Autowired
//	static MongoOperations mo;
//
//	@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
////	protected void dropTable(Class clazz) {
////
//////		ApplicationContext ctx = new GenericXmlApplicationContext(
//////				"paeez_mongodb.xml");
//////		MongoOperations mo = (MongoOperations) ctx
//////				.getBean("paeez_mongoTemplate");
////
////		if (mo == null)
////			System.out.println("********************* mo null****************");
////		else
////			System.out.println("********************* mo good****************");
////
////		if (mo.collectionExists(clazz)) {
////
////			mo.dropCollection(clazz);
////		}
////
////		if (mo.collectionExists(clazz)) {
////
////			Assert.fail("Delete of the collection " + clazz + " failed");
////		}
////	}
//
////	protected static boolean setupRepo() {
////
////		ClassLoader classLoader = getClass().getClassLoader();
////		File file = new File(classLoader.getResource(USERS_CSV_PATH).getFile());
////
////		//User seed
////		String path = .getClass().getResource("/testdata/Users.csv").getPath();
////		insertFromCSV(path, User.class, NO_OF_COLUMNS, null);
////
////		return false;
////	}
////	@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
////	protected void insertAll(Class clazz, List<? extends Object> objectsToSave) {
////
//////		ApplicationContext ctx = new GenericXmlApplicationContext(
//////				"paeez_mongodb.xml");
//////		MongoOperations mo = (MongoOperations) ctx
//////				.getBean("paeez_mongoTemplate");
////		if (!mo.collectionExists(clazz)) {
////
////			mo.createCollection(clazz);
////		}
////		mo.insertAll(objectsToSave);
////	}
//
//
//	@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
////	protected static void insertFromCSV(String path, Class clazz, int noOfColumns, CellProcessor[] processors) {
////
////		if (processors == null ) {
////			processors = new CellProcessor[noOfColumns] ;
////			for (int i = 0 ; i < noOfColumns ; i++) {
////
////				processors[i] = null ;
////			}
////		}
////
////		File csvFileName = new File(path) ;
////
////		Reader fileReader;
////		try {
////			fileReader = new FileReader(csvFileName);
////			ICsvBeanReader beanReader = new CsvBeanReader(fileReader, CsvPreference.STANDARD_PREFERENCE);
////
////			String[] header = beanReader.getHeader(true);
////			Object obj = null;
////
////			List<Object> objList = new ArrayList<>() ;
////			while ((obj = beanReader.read(clazz, header, processors)) != null) {
////
////				objList.add(obj) ;
////				logger.info("Adding Object: " + obj);
////			}
////
////			insertAll(clazz, objList) ;
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
//}
