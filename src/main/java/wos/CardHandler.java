package wos;

import java.util.*;
import java.io.Serializable;

import wos.*;

public class CardHandler implements Serializable{
		
	private static class InvalidColorException extends Exception {
		public InvalidColorException(String message){
			super(message);
		}
	}
}