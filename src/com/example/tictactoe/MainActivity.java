package com.example.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class MainActivity extends Activity implements OnClickListener{

	ImageButton ib11,ib12,ib13,ib21,ib22,ib23,ib31,ib32,ib33;
	boolean sign=false;
	String answer="",tag="";
	int i,j;
	int matrix[][]=new int[3][3];
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ib11=(ImageButton) findViewById(R.id.ib11);
        ib12=(ImageButton) findViewById(R.id.ib12);
        ib13=(ImageButton) findViewById(R.id.ib13);
        ib21=(ImageButton) findViewById(R.id.ib21);
        ib22=(ImageButton) findViewById(R.id.ib22);
        ib23=(ImageButton) findViewById(R.id.ib23);
        ib31=(ImageButton) findViewById(R.id.ib31);
        ib32=(ImageButton) findViewById(R.id.ib32);
        ib33=(ImageButton) findViewById(R.id.ib33);
        
        ib11.setOnClickListener((OnClickListener) this);
        ib12.setOnClickListener((OnClickListener) this);
        ib13.setOnClickListener((OnClickListener) this);
        ib21.setOnClickListener((OnClickListener) this);
        ib22.setOnClickListener((OnClickListener) this);
        ib23.setOnClickListener((OnClickListener) this);
        ib31.setOnClickListener((OnClickListener) this);
        ib32.setOnClickListener((OnClickListener) this);
        ib33.setOnClickListener((OnClickListener) this);
        
        ib11.setTag(tag);
        ib12.setTag(tag);
        ib13.setTag(tag);
        ib21.setTag(tag);
        ib22.setTag(tag);
        ib23.setTag(tag);
        ib31.setTag(tag);
        ib32.setTag(tag);
        ib33.setTag(tag);
        
        ib11.setContentDescription("11");
        ib12.setContentDescription("12");
        ib13.setContentDescription("13");
        ib21.setContentDescription("21");
        ib22.setContentDescription("22");
        ib23.setContentDescription("23");
        ib31.setContentDescription("31");
        ib32.setContentDescription("32");
        ib33.setContentDescription("33");        
        
        for(int i=0;i<3;i++)
        {
        	for(int j=0;j<3;j++)
        		matrix[i][j]=2;
        }
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		ImageButton ib=(ImageButton) arg0;
		
		i=Integer.valueOf(ib.getContentDescription().toString().substring(0,1));i--;		
		j=Integer.valueOf(ib.getContentDescription().toString().substring(1));j--;
		
		
		if(sign)
		{					
			ib.setImageResource(R.drawable.true_img);
			ib.setTag("true");
			matrix[i][j]=1;
			sign=false;			
		}
		else
		{			
			ib.setImageResource(R.drawable.false_img);
			ib.setTag("false");
			matrix[i][j]=0;
			sign=true;
		}
		ib.setEnabled(false);
		
		switch(arg0.getId())
		{
			case R.id.ib11 : 
			case R.id.ib13 :
			case R.id.ib31 :
			case R.id.ib33 :
			case R.id.ib12 : 
			case R.id.ib21 :
			case R.id.ib23 :
			case R.id.ib32 :			
			case R.id.ib22:
			{				
				answer = checkHorizontal(i,j);
				if(answer.equals("won from horizontal") || answer.equals("won from vertical") || answer.equals("won from diagonal") || answer.equals("won from reverse diagonal"))
					{
						showAlert();
						break;
					}
				
				answer = checkVertical(i,j);
				if(answer.equals("won from horizontal") || answer.equals("won from vertical") || answer.equals("won from diagonal") || answer.equals("won from reverse diagonal"))
					{
						showAlert();
						break;
					}
				
				if(i==j)
				{
				answer = checkDiagonal(j);				
				if(answer.equals("won from horizontal") || answer.equals("won from vertical") || answer.equals("won from diagonal") || answer.equals("won from reverse diagonal"))
					{
						showAlert();
						break;
					}
				}
				
				if(i+j==2)
				{
					answer = checkReverseDiagonal(i,j);				
					if(answer.equals("won from horizontal") || answer.equals("won from vertical") || answer.equals("won from diagonal") || answer.equals("won from reverse diagonal"))
						{
							showAlert();
							break;
						}	
				}
				
				break;
			}
		}
		
	}
	
	public String checkDiagonal(int j)
	{
		int cnt=0,val=0;
		boolean flag=true;
		
		while(cnt<3)
		{
			if(j>=0 && j<=2)
			{
				if(cnt==0)
					val=matrix[j][j];
				else
				{
					if(val!=matrix[j][j])
					{
						flag=false;
						break;
					}
				}
				j++;
			}
			else
			{
				j=0;
				cnt--;
			}
			cnt++;
		}
		if(flag==true)
			answer="won from diagonal";
		
		return answer;
	}
	
	public String checkReverseDiagonal(int i,int j)
	{
		
		int cnt=0,val=0;
		boolean flag=true;
		
		while(cnt<3)
		{
			if(j>=0 && j<=2)
			{
				if(cnt==0)
					val=matrix[i][j];
				else
				{
					if(val!=matrix[i][j])
					{
						flag=false;
						break;
					}
				}
				j++;
				i--;
			}
			else
			{
				j=0;
				i=2;
				cnt--;
			}
			cnt++;
		}
		if(flag==true)
			answer="won from reverse diagonal";
		
		return answer;
	}
	
	public String checkHorizontal(int i,int j)
	{
		int cnt=0,val=0;
		boolean flag=true;
		
		while(cnt<3)
		{
			if(j>=0 && j<=2)
			{
				if(cnt==0)
					val=matrix[i][j];
				else
				{
					if(val!=matrix[i][j])
					{
						flag=false;
						break;
					}
				}
				j++;
			}
			else
			{
				j=0;
				cnt--;
			}
			cnt++;
		}
		if(flag==true)
			answer="won from horizontal";
		
		return answer;
	}
	
	public String checkVertical(int i,int j)
	{
		int cnt=0,val=0;
		boolean flag=true;
		
		while(cnt<3)
		{
			if(i>=0 && i<=2)
			{
				if(cnt==0)
					val=matrix[i][j];
				else
				{
					if(val!=matrix[i][j])
					{
						flag=false;
						break;
					}
				}
				i++;
			}
			else
			{
				i=0;
				cnt--;
			}
			cnt++;
		}
		if(flag==true)
			answer="won from vertical";
		
		return answer;
	}
	
	public void showAlert()
	{
		String sign;
		if(matrix[i][j]==0)
			sign="Cross Sign ";
		else
			sign="True Sign ";
		
		Builder alert = new AlertDialog.Builder(MainActivity.this);
		alert.create();
		alert.setTitle("Result");
		alert.setMessage(sign+answer);
		alert.setPositiveButton("OK", new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							//close application
						//finish();	
						}
						
					});
		alert.show();
		disableAll();
	}
	
	public void disableAll()
	{
		ib11.setEnabled(false);
		ib12.setEnabled(false);
		ib13.setEnabled(false);
		ib21.setEnabled(false);
		ib22.setEnabled(false);
		ib23.setEnabled(false);
		ib31.setEnabled(false);
		ib32.setEnabled(false);
		ib33.setEnabled(false);
	}
}	