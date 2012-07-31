package com.lapatrialibre.panel;

import jp.co.ricoh.dsdk.panel.AttributeColor;
import jp.co.ricoh.dsdk.panel.AttributeType;
import jp.co.ricoh.dsdk.panel.Color;
import jp.co.ricoh.dsdk.panel.Font;
import jp.co.ricoh.dsdk.panel.Label;
import jp.co.ricoh.dsdk.panel.Window;
import jp.co.ricoh.dsdk.panel.event.ActionEvent;
import jp.co.ricoh.dsdk.panel.event.ActionListener;

public class TwitterWindow {

	private int smx = 50;
	private int smy = 50;

	private Color colorSMFG = new Color(255, 255, 255);
	private Color colorSMBG = new Color(0, 0, 0);
	private AttributeColor smColor = new AttributeColor();
	
	private static final int BTN_H = 20;
	
	private Window tweetWindow = null;
	private InputBar inputBar;

	public void initTweetWin(Window win, int x, int y) {
		tweetWindow = win;
		smx = x;
		smy = y;
		smColor.setColor(AttributeType.NONE, AttributeColor.Attribute.FG,
				colorSMFG);
		smColor.setColor(AttributeType.NONE, AttributeColor.Attribute.BG,
				colorSMBG);

		System.out.println("Valuye of x and y is for x " + smx + " and for y "
				+ smy);
	
		setTweetWinUI();

	}
	
	public void setTweetWinUI() {
		Label infoLabel = new Label("Use ID card or input user id to login.");

		infoLabel.setAttributeColor(new TransparentColor());
		infoLabel.setFont(Font.F16);
		infoLabel.setBounds(110, 80, 300, BTN_H);
		tweetWindow.add(infoLabel);

		inputBar = new InputBar();
		inputBar.setButtonBounds(110, 108, 80, BTN_H);
		inputBar.setLabelBounds(189, 108, 180, BTN_H);
		inputBar.setFont(Font.F16);
		inputBar.setButtonLabel("User ID:");
		inputBar.initLabelContent("");
		inputBar.attachTo(tweetWindow);

		XButton loginButton = new XButton();
		loginButton.setLabel("Tweet");
		loginButton.setBounds(390, 108, 80, BTN_H);
		loginButton.setFont(Font.F16);
		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				loginButtonActionPerform(inputBar.getLabelContent());
			}
		});
		tweetWindow.add(loginButton);
	}
	
	private void loginButtonActionPerform(String tweet) {
		System.out.println("Tweet the following " + tweet);
	}


}
