package cjc.common.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	
	public static <E> ArrayList<E> newArrayList() {
	    return new ArrayList<E>();
	}
	
	public static <E> boolean  isEmpty(List<E> arrays){
		return arrays==null||arrays.size()==0;
	}
}
