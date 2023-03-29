package com.covid;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class App {

	public static String getData(String c) throws Exception {
		String url = "https://www.worldometers.info/coronavirus/country/" + c + "/";
		Document doc = Jsoup.connect(url).get();
//		System.out.println(doc.title());
//		System.out.println(doc.body());
		// #maincounter-wrap
		Elements elements = doc.select("#maincounter-wrap");
//		System.out.println(elements.html());

//		elements.forEach((e)->{
//			System.out.println(e.html());
//			System.out.println();
//			System.out.println();
//		});
		StringBuffer br = new StringBuffer();
		br.append("<html>"
				+ "<body style='text-align:center;color:red;'>");
		br.append(c.toUpperCase()+"<br>");
		elements.forEach((e) -> {
			String text = e.select("h1").text();
			String count = e.select(".maincounter-number>span").text();
			br.append(text).append(" ").append(count).append("<br>");

		});
		br.append("</body>"
				+ "</html>");
		return br.toString();

	}

	public static void main(String[] args) throws Exception {
//    	System.out.println("Enter Country");
//    	    try (Scanner scanner = new Scanner(System.in)) {
//				String country=scanner.nextLine();
//				System.out.println(getData(country));
//			}catch (Exception e) {
//				System.out.println(e.getMessage());
//			}

		JFrame root = new JFrame("Details of country");
		root.setSize(500, 500);
		Font font = new Font("Poppins", Font.BOLD, 30);
		
		// text field
		JTextField field = new JTextField();
		field.setFont(font);
		
		// label
		JLabel dataLabel = new JLabel();
		dataLabel.setFont(font);
		
		field.setHorizontalAlignment(SwingConstants.CENTER);
		dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//button
		JButton button=new JButton("Get");
		button.setFont(font);
		
		button.addActionListener((event)->{
			//click
			try {
			String maindata=field.getText();
			String result=getData(maindata);
			dataLabel.setText(result);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
		
		root.setLayout(new BorderLayout());
		root.add(field,BorderLayout.NORTH);
		root.add(dataLabel,BorderLayout.CENTER);
		root.add(button,BorderLayout.SOUTH);
		root.setVisible(true);
		root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
	}
}
