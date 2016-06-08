/**下午3:56:32
 * @author zhangyh2
 * a.java
 * TODO
 */
package com.darly.test;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.test.AndroidTestRunner;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.darly.dlclent.R;

/**
 * @author zhangyh2 a 下午3:56:32 TODO
 */
@SuppressLint("HandlerLeak")
public class TestActivity extends Activity {

	private TextView resultView;

	private TextView barView;

	private TextView messageView;

	private Thread testRunnerThread;

	private static final int SHOW_RESULT = 0;

	private static final int ERROR_FIND = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		resultView = (TextView) findViewById(R.id.ResultView);
		barView = (TextView) findViewById(R.id.BarView);
		messageView = (TextView) findViewById(R.id.MessageView);
		Button lunch = (Button) findViewById(R.id.LunchButton);
		lunch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startTest();
			}
		});
	}

	private void showMessage(String message) {
		hander.sendMessage(hander.obtainMessage(ERROR_FIND, message));
	}

	private void showResult(String text) {
		hander.sendMessage(hander.obtainMessage(SHOW_RESULT, text));
	}

	private synchronized void startTest() {
		if (testRunnerThread != null && testRunnerThread.isAlive()) {
			testRunnerThread = null;
		}
		if (testRunnerThread == null) {
			testRunnerThread = new Thread(new TestRunner(this));
			testRunnerThread.start();
		} else {
			Toast.makeText(this, "Test is still running", Toast.LENGTH_SHORT)
					.show();
		}
	}

	public Handler hander = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESULT:
				resultView.setText(msg.obj.toString());
				break;
			case ERROR_FIND:
				messageView.append(msg.obj.toString());
				barView.setBackgroundColor(Color.RED);
				break;
			default:
				break;
			}
		}
	};

	class TestRunner implements Runnable, TestListener {

		private Activity parentActivity;

		private int testCount;

		private int errorCount;

		private int failureCount;

		public TestRunner(Activity parentActivity) {
			this.parentActivity = parentActivity;
		}

		@Override
		public void run() {
			testCount = 0;
			errorCount = 0;
			failureCount = 0;

			Suit suite = new Suit();
			AndroidTestRunner testRunner = new AndroidTestRunner();
			testRunner.setTest(suite);
			testRunner.addTestListener(this);
			testRunner.setContext(parentActivity);
			testRunner.runTest();
		}

		@Override
		public void addError(Test test, Throwable t) {
			errorCount++;
			showMessage(t.getMessage() + "\n");
		}

		@Override
		public void addFailure(Test test, AssertionFailedError t) {
			failureCount++;
			showMessage(t.getMessage() + "\n");
		}

		@Override
		public void endTest(Test test) {
			showResult(getResult());
		}

		@Override
		public void startTest(Test test) {
			testCount++;
		}

		private String getResult() {
			int successCount = testCount - failureCount - errorCount;
			return "Test:" + testCount + " Success:" + successCount
					+ " Failed:" + failureCount + " Error:" + errorCount;
		}

	}

}