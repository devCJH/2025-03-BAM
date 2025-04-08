package com.koreaIT.BAM.controller;

import java.util.Scanner;

public abstract class Controller {
	protected Scanner sc;
	protected String cmd;
	
	public abstract void doAction(String methodName, String cmd);
	protected abstract void makeTestData();
}
