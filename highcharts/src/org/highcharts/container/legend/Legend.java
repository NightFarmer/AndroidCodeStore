package org.highcharts.container.legend;

import org.highcharts.container.Align;


public class Legend {
	private String layout;
	private String align;
	private String verticalAlign;
	private int borderWidth;
	
	
	public int getBorderWidth() {
		return borderWidth;
	}

	/**
	 * @param borderWidth 图例边框宽度
	 */
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}

	public Layout getLayout() {
		Layout[] values = Layout.values();
		for (Layout hc_Layout : values) {
			if (hc_Layout.getStr().equals(layout)) {
				return hc_Layout;
			}
		}
		return null;
	}

	/**
	 * @param layout 设置图例的排列方式
	 */
	public void setLayout(Layout layout) {
		this.layout = layout.str;
	}

	public Align getAlign() {
		Align[] values = Align.values();
		for (Align hc_Align : values) {
			if (hc_Align.getStr().equals(align)) {
				return hc_Align;
			}
		}
		return null;
	}

	/**
	 * @param align 设置图例的水平位置
	 */
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

	/**
	 * @param verticalAlign 图例垂直位置
	 */
	public void setVerticalAlign(Align verticalAlign) {
		this.verticalAlign = verticalAlign.getStr();
	}

	

	public enum Layout{
		Horizontal("horizontal"),
		Vertical("vertical");
		
		private String str;
		Layout(String str){
			this.str = str;
		}
		public String getStr(){
			return str;
		}
	}
}
