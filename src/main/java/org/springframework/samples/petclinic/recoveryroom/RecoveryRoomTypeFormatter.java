package org.springframework.samples.petclinic.recoveryroom;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class RecoveryRoomTypeFormatter implements Formatter<RecoveryRoomType>{

	private RecoveryRoomService service;
	
	@Autowired
	public RecoveryRoomTypeFormatter(RecoveryRoomService service) {
		this.service = service;
	}
	
    @Override
    public String print(RecoveryRoomType object, Locale locale) {
        return object.getName();
    }

    @Override
    public RecoveryRoomType parse(String text, Locale locale) throws ParseException {
        
    	List<RecoveryRoomType> types = service.getAllRecoveryRoomTypes();
    	
    	for(RecoveryRoomType type:types) {
    		if(text.equals(type.getName())) return type;
    	}
    	
    	throw new ParseException("RecoveryRoomType name not valid: " + text, 0);
    }
    
}
