package sample.camel;


import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;



@Service("databaseService")
public class DatabaseService {

	@Autowired
    BookRepository books;
	
	public void printjson(String msg) 
	{
		System.out.println(msg);
		Gson g = new Gson();                  //use gson to conver json to object 
		BooksTable b1 = g.fromJson(msg, BooksTable.class);
		//System.out.println(b1.getId());
		addBook(b1);
		
		
		
	}
	public void addBook(BooksTable b1) {
		books.insert(b1);
	}
}
