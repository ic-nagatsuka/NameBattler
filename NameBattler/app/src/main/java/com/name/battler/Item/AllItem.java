package com.name.battler.Item;

import java.util.ArrayList;
import java.util.List;

public class AllItem {
	/*===========
	 * フィールド変数
	 ===========*/
	
	List<Item> allItem = new ArrayList<>();
	
	/*==========
	 * コンストラクタ
	 ==========*/
	public AllItem(){
		MakeItem();
	}

	public List<Item> GetItemList(){
		return this.allItem;
	}
	
	

	/*==========
	 * public 
	 ==========*/
	
	public void MakeItem(){
		allItem.add( new ItemHeel("薬草", 20) );
		allItem.add( new ItemHeel("大きな薬草", 40) );
		allItem.add( new ItemHeel("水", 10) );
		allItem.add( new ItemHeel("清水", 30) );
	}
	
}
