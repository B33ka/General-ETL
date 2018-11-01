package test;

//-----------------------------------------------------------------------------
//
//This file is the copyrighted property of Tableau Software and is protected 
//by registered patents and other applicable U.S. and international laws and 
//regulations.
//
//Unlicensed use of the contents of this file is prohibited. Please refer to 
//the NOTICES.txt file for further details.
//
//-----------------------------------------------------------------------------

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class for Servlet: TableauServlet
*
*/
public class TableauServlet extends javax.servlet.http.HttpServlet {
 private static final long serialVersionUID = 1L;

 public TableauServlet() {
     super();
 }       
 
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<!DOCTYPE><html><head><title>Welcome Tableau Servlet</title></head>");
		writer.println("<body><h3> Come in :))</h3></body></html>");
 }   
}
