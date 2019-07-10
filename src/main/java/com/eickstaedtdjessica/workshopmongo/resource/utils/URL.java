package com.eickstaedtdjessica.workshopmongo.resource.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {

	public static String urlDecode(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}