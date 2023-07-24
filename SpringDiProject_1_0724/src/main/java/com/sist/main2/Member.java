package com.sist.main2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor   //생성자
public class Member {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String sex;
    
    //개발자가 호출하는 함수
    public void print() {
    	System.out.println("ID:"+id);
    	System.out.println("Name:"+name);
    	System.out.println("Address:"+address);
    	System.out.println("Phone:"+phone);
    	System.out.println("Sex:"+sex);
    }
}
