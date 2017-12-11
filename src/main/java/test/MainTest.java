package test;

import dao.PostDAO;

public class MainTest {

	public static void main(String[] args) {
		System.out.println(PostDAO.findAll());
	}
}
