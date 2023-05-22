package controller;

import javax.persistence.EntityManager;

import utils.JpaUtil;

public class MainProject {

	public static void main(String[] args) {

EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
System.out.println("Heeoloo");
	}

}
