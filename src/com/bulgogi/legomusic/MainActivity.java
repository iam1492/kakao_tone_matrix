package com.bulgogi.legomusic;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.bulgogi.legomusic.tonematrix.ToneMatrix;

public class MainActivity extends Activity {
	private ToneMatrix tm;
	
	//start testcode
	private boolean isPlaying = false;
	private int totalCount = 0;
	private Handler mRepeatHanlder;
	
	boolean [][] test1 = {
			{false,true,true,false},
			{false,false,true,false},
			{false,false,false,false},
			{true,false,true,true}
	};
	boolean [][] test2 = {
			{true,true,false,false},
			{false,true,false,true},
			{false,false,false,false},
			{false,true,true,false}
	};

	boolean [][] test3 = {
			{false,true,false,true},
			{false,true,true,true},
			{false,true,true,false},
			{true,true,true,false}
	};

	boolean [][] test4 = {
			{false,true,false,false},
			{false,false,false,false},
			{true,false,true,false},
			{false,false,false,true}
	};
	
	boolean [][][] testGrid = {test1, test2, test3, test4};

	//start endcode
	
	Runnable mStatusChecker = new Runnable() {
		@Override 
		public void run() {
			totalCount++;
			boolean [][] grid = testGrid[totalCount % 4];
			tm.setGrid(grid);
			
			if (isPlaying)
				mRepeatHanlder.postDelayed(mStatusChecker, 4000);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mRepeatHanlder = new Handler();		
		tm = new ToneMatrix(this);

		tm.setGrid(testGrid[0]);
		tm.startSeq();
		isPlaying = true;

		mRepeatHanlder.postDelayed(mStatusChecker, 4000);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		tm.stopSeq();
		isPlaying = false;
		super.onPause();
	}
	
}
