package com.bit.springboard.coupling;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CarOwner_Solve_Coupling {
    public static void main(String[] args) {
        // 1. 스프링 컨테이너 구동
        // 스프링 컨테이너 객체 생성
        // bean 엘리먼트로 등록되어 있는 클래스들의 객체 자동으로 생성
        AbstractApplicationContext factory =
                // 어떠한 설정파일로 스프링 컨테이너를 구동할지 지정
                new GenericXmlApplicationContext("root-context.xml");

        // 2. 의존성 검색(DL: Dependency Lookup): bean 객체로 생성된 객체를 찾는 기능
        //    의존성 주입(DI: Dependency Injection): 의존성 검색으로 찾은 객체를 변수에 담아주는 기능
        Car car = factory.getBean("kiaCar", Car.class);
//        Car car = (Car)factory.getBean("kiaCar");

        car.engineOn();
        car.speedUp();
        car.speedDown();
        car.engineOff();
        
        // scope: "prototype" 객체를 요청할 때마다 새로운 객체 생성
        car = (Car)factory.getBean("kiaCar");

        // lazy-init="true" : 스프링 컨테이너 구동시 객체를 생성하지 않고 요청 시에 객체를 생성한다.
        car = factory.getBean("hyundaiCar", Car.class);

        car.engineOn();
        car.speedUp();
        car.speedDown();
        car.engineOff();

        // 3. 스프링 컨테이너 구동 종료
        // 스프링 컨테이너가 종료되면서 생성되어 있던 bean 객체들을 모두 삭제한다.
        factory.close();
    }
}
