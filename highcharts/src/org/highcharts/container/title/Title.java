package org.highcharts.container.title;

import org.highcharts.container.Align;

public class Title {
	private String align;
	private boolean floating;
	private int margin = 15;
	private String style;
	private String text;
	private boolean useHTML;
	private String verticalAlign;
	private int x;
	private int y = 13;
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

//	public int getY() {
//		return y;
//	}
//
//	public void setY(int y) {
//		this.y = y;
//	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}
//
//	public boolean isFloating() {
//		return floating;
//	}
//
//	public void setFloating(boolean floating) {
//		this.floating = floating;
//	}
//
	public Align getAlign() {
		Align[] values = Align.values();
		for (Align hc_Align : values) {
			if (hc_Align.getStr().equals(align)) {
				return hc_Align;
			}
		}
		return null;
	}

	public void setAlign(Align align) {
		this.align = align.getStr();
	}
	
	public Align getVerticalAlign() {
		Align[] values = Align.values();
		for (Align hc_Align : values) {
			if (hc_Align.getStr().equals(verticalAlign)) {
				return hc_Align;
			}
		}
		return null;
	}

	public void setVerticalAlign(Align verticalAlign) {
		this.verticalAlign = verticalAlign.getStr();
	}
//
//
//	public String getStyle() {
//		return style;
//	}
//
//	public void setStyle(String style) {
//		this.style = style;
//	}
//
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isUseHTML() {
		return useHTML;
	}

	public void setUseHTML(boolean useHTML) {
		this.useHTML = useHTML;
	}
	

}
