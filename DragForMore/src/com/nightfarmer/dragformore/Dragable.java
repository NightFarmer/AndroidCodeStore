package com.nightfarmer.dragformore;

public interface Dragable {

	public enum State{
		LOADING,NORMAL;
	}
	
	void load();
	State getState();
	void cancle();
	void setLoadedListener(LoadedListener l);
	boolean canDragDown();
	
	public interface LoadedListener{
		void loaded(boolean success);
	}
}
