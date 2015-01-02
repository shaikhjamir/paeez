package com.kar.paeez.ws.repo.model;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:paeez_mongodb.xml" })
public abstract class BaseModelTest {

	private static Logger logger = LoggerFactory.getLogger(BaseModelTest.class) ;
	
	@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	protected void dropTable(Class clazz) {

		ApplicationContext ctx = new GenericXmlApplicationContext(
				"paeez_mongodb.xml");
		MongoOperations mo = (MongoOperations) ctx
				.getBean("paeez_mongoTemplate");

		if (mo.collectionExists(clazz)) {

			mo.dropCollection(clazz);
		}

		if (mo.collectionExists(clazz)) {

			Assert.fail("Delete of the collection " + clazz + " failed");
		}
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	protected void insertAll(Class clazz, List<? extends Object> objectsToSave) {

		ApplicationContext ctx = new GenericXmlApplicationContext(
				"paeez_mongodb.xml");
		MongoOperations mo = (MongoOperations) ctx
				.getBean("paeez_mongoTemplate");
		if (!mo.collectionExists(clazz)) {

			mo.createCollection(clazz);
		}
		mo.insertAll(objectsToSave);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	protected void insertFromCSV(String path, Class clazz, int noOfColumns, CellProcessor[] processors) {

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
