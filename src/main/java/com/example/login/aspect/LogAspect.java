package com.example.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
//	// executionで指定
//
//	// AOPの実装：Controllerクラスの全てのメソッドを対象
//	// Before：メソッドが実行される前に、AOPの処理(Advice)を実行する
//	@Before("execution(* *..*.*Controller.*(..))")
//	public void startLog(JoinPoint jp) {
//		System.out.println("メソッド開始： " + jp.getSignature());
//	}
//	
//	// AOPの実装：Controllerクラスの全てのメソッドを対象
//	// After：メソッドが実行された後に、AOPの処理(Advice)を実行する
//	@After("execution(* *..*.*Controller.*(..))")
//	public void endLog(JoinPoint jp) {
//		System.out.println("メソッド終了： " + jp.getSignature());
//	}
	
//	// AOPの実装：Controllerクラスの全てのメソッドを対象
//	// Around：メソッド実行の前後に、AOPの処理(Advice)を実行する
//	@Around("execution(* *..*.*Controller.*(..))")
//	public Object startLog(ProceedingJoinPoint jp) throws Throwable {
//		System.out.println("メソッド開始： " + jp.getSignature());
//		
//		try {
//			// メソッド実行
//			Object result = jp.proceed();
//			System.out.println("メソッド終了： " + jp.getSignature());
//			return result;
//		} catch (Exception e) {
//			System.out.println("メソッド異常終了： " + jp.getSignature());
//			e.printStackTrace();
//			throw e;
//		}
//	}
	
	// beanで指定
	
	// AOPの実装：Controllerクラスの全てのメソッドを対象
	// Around：メソッド実行の前後に、AOPの処理(Advice)を実行する
	@Around("bean(*Controller)")
	public Object startLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("メソッド開始： " + jp.getSignature());
		
		try {
			// メソッド実行
			Object result = jp.proceed();
			System.out.println("メソッド終了： " + jp.getSignature());
			return result;
		} catch (Exception e) {
			System.out.println("メソッド異常終了： " + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
	
//	// @annotationで指定
//	
//	// AOPの実装：Controllerクラスの全てのメソッドを対象
//	// Around：メソッド実行の前後に、AOPの処理(Advice)を実行する
//	@Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//	public Object startLog(ProceedingJoinPoint jp) throws Throwable {
//		System.out.println("メソッド開始： " + jp.getSignature());
//		
//		try {
//			// メソッド実行
//			Object result = jp.proceed();
//			System.out.println("メソッド終了： " + jp.getSignature());
//			return result;
//		} catch (Exception e) {
//			System.out.println("メソッド異常終了： " + jp.getSignature());
//			e.printStackTrace();
//			throw e;
//		}
//	}
	
//	// @withinで指定
//	
//	// AOPの実装：Controllerクラスの全てのメソッドを対象
//	// Around：メソッド実行の前後に、AOPの処理(Advice)を実行する
//	@Around("@within(org.springframework.stereotype.Controller)")
//	public Object startLog(ProceedingJoinPoint jp) throws Throwable {
//		System.out.println("メソッド開始： " + jp.getSignature());
//		
//		try {
//			// メソッド実行
//			Object result = jp.proceed();
//			System.out.println("メソッド終了： " + jp.getSignature());
//			return result;
//		} catch (Exception e) {
//			System.out.println("メソッド異常終了： " + jp.getSignature());
//			e.printStackTrace();
//			throw e;
//		}
//	}
}
