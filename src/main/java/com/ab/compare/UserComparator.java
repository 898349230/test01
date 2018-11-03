package com.ab.compare;

import java.util.Comparator;

public class UserComparator implements Comparator<User>{

	@Override
	public int compare(User o1,User o2) {
		if(o1.getAge()>o2.getAge()){
			return 1;
		}else if(o1.getAge()==o2.getAge()){
			if(o1.getName().compareTo(o2.getName())>0){
				return 1;
			}else if(o1.getName().compareTo(o2.getName())==0){
				return 0;
			}else{
				return -1;
			}
		}else{
			return -1;
		}
	}
}
