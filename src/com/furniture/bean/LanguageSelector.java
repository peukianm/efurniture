package com.furniture.bean;


import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LanguageSelector {

	private Locale currentLocale = new Locale("gr");
	
}
