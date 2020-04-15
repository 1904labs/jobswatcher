package com.labs1904.jobs.watch;

import java.io.Reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLMarshaller<T> {

	private JAXBContext jaxbContext;
	
	public XMLMarshaller(Class<T> c) {
		try {
			this.jaxbContext = JAXBContext.newInstance(c);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T parse(Reader reader) {
		T response = null;
		try {
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			response = (T) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		
		return response;
	}
	
}
