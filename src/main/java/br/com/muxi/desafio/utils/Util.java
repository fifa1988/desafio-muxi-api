package br.com.muxi.desafio.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;

public class Util {

	private Util() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static <T, S> List<T> copyList(List<S> src, Class<T> typeClass) {
		
		List<T> target = new ArrayList<>();
		
		for ( S s : src ) {
            T t = BeanUtils.instantiateClass(typeClass);
            BeanUtils.copyProperties(s, t);
            target.add(t);
        }
    	
        return target;
	}
	
	public static boolean isStringInteger(String number ){
	    try{
	        Integer.parseInt(number);
	    }catch(Exception e ){
	        return false;
	    }
	    return true;
	}
	
	public static boolean isNotInteger(String number){
		return Pattern.matches("\\-?\\d+", (CharSequence) number);
	}
	
	
	
}
