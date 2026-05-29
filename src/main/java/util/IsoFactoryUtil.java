package util;

import iso.std.iso._20022.tech.xsd.pain_001_001.ObjectFactory;

public class IsoFactoryUtil {
	private static ObjectFactory isoFactory = new ObjectFactory();
	
	public static ObjectFactory getFactory (){
		if (null == isoFactory){
			isoFactory = new ObjectFactory();
		}
		return isoFactory;
	}
}
