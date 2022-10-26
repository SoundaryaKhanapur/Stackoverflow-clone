package com.example.demo.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InvestApi {

	public static String fetchTagDesc(String[] tags) {

		String tag = tags[0];

		String url1 = "http://api.stackexchange.com/2.2/tags/"+tag+"/wikis?site=stackoverflow";

		try {

			URL url = new URL(url1);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");



			int status = con.getResponseCode();

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			System.out.println(content.toString());
			
			con.disconnect();

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}



		return null;
	}

}
