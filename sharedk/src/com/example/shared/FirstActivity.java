package com.example.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class FirstActivity extends Activity implements OnItemClickListener{
	
        //���һ�仰�����Բ鿴GithubԶ�̿��Ƿ��ͬ����2016.05.24--Ayniu--��

	Spinner sp1;//�б�ѡ���sp1��sp2��sp3
	Spinner sp2;
	Spinner sp3;
	Button position_Bt;//"λ��"��ť
	boolean open;
	ImageButton searchbt;
	ImageButton menubt;
	private ListView listView;
	private SimpleAdapter simp_adapter;
	List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>(); 
	String[] name;//����Ϊ��ʼ��listview�и�����
	String[] xinxi;
	String[] leixing;
	String[] quyu;
	int[] zujin;
	String[] time;//�˴�����ʱ���ݶ�Ϊ�ı����ȴ���̨����
	int[] imageIds;//����Ϊ��ʼ��listview�и�����
	String[] quyuStrings;//��������ɸѡ�ġ����������ж�
	String[] leiStrings;//��������ɸѡ�ġ����͡����ж�
	int zujinminInt=0;//��������ɸѡ�ġ���𡱵���Сֵ�ж�
	int zujinmaxInt=0;//��������ɸѡ�ġ���𡱵����ֵ�ж�
	
     //������12��05�գ�����Ϊ�йظ����ֵ������飬
        String[] blockofTianhe={"�˻�","ʯ��","ɳ��","�鼪","����","�Ե�","����","ɳ��",
			"����","�ֺ�","��԰","Ա��","����","�����","��ɽ","����","����","ǰ��","�ƴ�",
			"���","Ԫ��"};        //��������ֵ�
	String[] blockofYuexiu={"����","����","÷����","����","��","����","��ɽ","����",
			"��Ȫ","���","�ƻ���","����","����","�Ƿ�","����","����","����","ũ��"}; 
	                             //Խ�������ֵ�
	String[] blockofHaizhu={"�¸�","��ʯͷ","����","��","����","����","����",
			"����","����","������","ɳ԰","����","����","����","����","���","����",
			"�ϻ���"};            //���������ֵ�
	String[] blockofBaiyun={"����","��̩","ͬ��","��ʯ","�ľ�","ͬ��","��Ϫ","��ƽ",
			"��Ԫ��","ʯ��","����","����","�κ�","̫��","����̶","����","�˺�","��ɳ","�Ƴ�",
			"����","ʯ��","���ƺ�"}; //���������ֵ�
	String[] blockofLiwan={"�׺׶�","��ɳ","����","�ʺ�","����","��","ʯΧ��","����",
			"�౦","վǰ","����","����","���","����","ɳ��","��Դ","��Դ","���]","���",
			"����","����","����"}; //���������ֵ�
	String[] blockofPanyu={"С��Χ","����","����","�Ӵ�","�ϴ�",
			"ɳ��","��ʯ","����","ʯ��","����","ɳͷ","����","ʯ¥","����","ʯ��","����"};
	                            //��خ�����ֵ�
	String[] blockofHuangpu={"����","��ɽ","�붫","�ĳ�","�ϸ�","����","����","��ɳ",
			"����","����","����","����","�ĸ�","�ܸ�"}; //���������ֵ�
	String[] blockofHuadu={"�»�","����","����","̿��","ʨ��","����","����","��ɽ","��ȫ","����"};
	                            //���������ֵ�
	String[] blockofNansha={"��ɳ","����","����ɳ","�Ƹ�","魺�","��ӿ","�齭","��Ѩ",
			"���"};             //��ɳ�����ֵ�
	String[] blockofConghua={"����","�ֿ�","�ǽ�","̫ƽ","����","����","��Ȫ","��ͷ"};
	                            //�ӻ������ֵ�
	String[] blockofZengcheng={"����","���","���","����","С¥","ʯ̲","����","��̶",
			"����","�ɴ�","����"}; //���������ֵ�
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.firstactivity);
        //ʵ���Զ��������
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.titlestyle);
	
        sp1=(Spinner) findViewById(R.id.spinner1);
        sp2=(Spinner) findViewById(R.id.spinner2);
        sp3=(Spinner) findViewById(R.id.spinner3);
        searchbt=(ImageButton) findViewById(R.id.searchbt);
        menubt=(ImageButton) findViewById(R.id.menubt);
        listView=(ListView)findViewById(R.id.listView1);
        
      //ʵ��listView���б����ݳ�ʼ��
        name=new String[]{"��г��԰","����С��","��ѧ·","����·","����԰",
        		"���;�","��ԴС��","�Ƽ�԰","�Ѱ���԰","�̹���"};
        xinxi=new String[]{"3��|120�O|","1��|30�O|","2��|60�O|",
        		"2��|50�O|","3��|128�O|","2��|80�O|",
        		"3��|106�O|","2��|78�O|","3��|110�O|","4��|130�O|"};
        leixing=new String[]{"����","����","����","����","����","����","����","����","����","����"};
        quyu=new String[]{"�����","������","������","�����","��خ��","������","��ɳ��"
        		,"������","������","�ӻ���"};
        zujin=new int[]{3600,2000,3200,4100,5500,6200,1800,800,1500,6600};
        time=new String[]{"12-02����","12-02����","12-02����","12-02����","12-02����"
        		,"12-01����","12-01����","12-01����","12-01����","12-01����"};
        imageIds=new int[]{R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house
        		,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house};
    
        //ListView����ʾ����
 		simp_adapter=new SimpleAdapter(this, listItems, R.layout.listitem, 
 		 new String[]{"pic","name","xinxi","leixing","quyu","zujin","time"}, 
 		 new int[]{R.id.pic,R.id.name,R.id.xinxi,R.id.leixing,R.id.quyu,R.id.zujin,R.id.time});
 		listView.setAdapter(simp_adapter);
 		
        //sp1����
        //Ϊspinner1����ѡ��
        final String[] array1={"����","�����","������","������","Խ����","������","������",
        		"������","��خ��","�ӻ���","��ɳ��","������"};
        ArrayAdapter<String> accountTypesAdapter1 = new ArrayAdapter<String>(this,
        		android.R.layout.simple_spinner_item,array1); 
        accountTypesAdapter1.setDropDownViewResource(R.layout.spinnerstyle); //����spinner������ĵ��Զ�����ʽ
        sp1.setAdapter(accountTypesAdapter1); 
        //ʵ��spinner1���б�����¼�����
        sp1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() { 
        	// TODO Auto-generated method stub
        	boolean b=false;
    		public void onItemSelected(AdapterView<?> arg0, View arg1, int id,
    				long arg3) {
    			// TODO Auto-generated method stub
        		
    			if(!b){((TextView)arg1).setText(" λ��   ��"); };
        		b=true;//ʵ��Spinner1�ڳ�ʼ״̬��Ϊѡ��������ʾ����������
    			switch (id) {
				case 0://����
				{quyuStrings=array1;//��������ɸѡ���ж�
				    re();                         }
					break;
				case 1://������������
				{quyuStrings=new String[]{"�����"};//��������ɸѡ���ж�
			     re();                            }
					break;
				case 2://�������������
				{quyuStrings=new String[]{"������"};
			     re();                            }
					break;
				case 3://�������������
				{quyuStrings=new String[]{"������"};
			     re();                            }
				    break;
				case 4://�����Խ������
				{quyuStrings=new String[]{"Խ����"};
			     re();                            }
				    break;
				case 5://���"������"
				{quyuStrings=new String[]{"������"};
			     re();                            }
					break;
				case 6://���"������"
				{quyuStrings=new String[]{"������"};
			     re();                            }
					break;
				case 7://���"������"
				{quyuStrings=new String[]{"������"};
			     re();                            }
					break;
				case 8://���"��خ��"
				{quyuStrings=new String[]{"��خ��"};
			     re();                            }
					break; 					
				case 9://���"�ӻ���"
				{quyuStrings=new String[]{"�ӻ���"};
			     re();                            }
					break;
				case 10://���"��ɳ��"
				{quyuStrings=new String[]{"��ɳ��"};
			     re();                            }
					break;       
				case 11://���"������"
				{quyuStrings=new String[]{"������"};
			     re();                            }
					break;
				default:
					break;
				}
           }
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
        });
  
       //sp2����
       //Ϊspinner2����ѡ��
       String[] array2={"����","500-1000","1000-2000","2000-3000",
    		   "3000-4000","4000-5000","5000-6000","6000����"};
       final ArrayAdapter<String> accountTypesAdapter2 = new ArrayAdapter<String>(this,
    		android.R.layout.simple_spinner_item,array2); 
       accountTypesAdapter2.setDropDownViewResource(R.layout.spinnerstyle); 
       //����spinner������ĵ��Զ�����ʽ
       sp2.setAdapter(accountTypesAdapter2); 
       //ʵ��spinner2���б�����¼�����
       sp2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
    	   boolean b=false;
		public void onItemSelected(AdapterView<?> arg0, View arg1, int id,
				long arg3) {
			// TODO Auto-generated method stub
			
			if(!b){((TextView)arg1).setText(" ���   ��"); };
    		b=true;//ʵ��Spinner2�ڳ�ʼ״̬��Ϊѡ��������ʾ����������
			switch (id) {
			case 0://������������Сֵ��Χ
			{zujinminInt=0;zujinmaxInt=Integer.MAX_VALUE;//��������ɸѡ���ж�
		     re();                             }
				break;
			case 1://������������Сֵ��Χ500-1000
			{zujinminInt=500;zujinmaxInt=1000;
		     re();                             }
				break;
			case 2://������������Сֵ��Χ1000-2000
			{zujinminInt=1000;zujinmaxInt=2000;
		     re();                             }
				break;
            case 3://������������Сֵ��Χ2000-3000
			{zujinminInt=2000;zujinmaxInt=3000;
		     re();                             }
				break;
            case 4://������������Сֵ��Χ3000-4000
			{zujinminInt=3000;zujinmaxInt=4000;
		     re();                             }
				break;
            case 5://������������Сֵ��Χ4000-5000
			{zujinminInt=4000;zujinmaxInt=5000;
		     re();                             }
				break;
            case 6://������������Сֵ��Χ5000-6000
			{zujinminInt=5000;zujinmaxInt=6000;
		     re();                             }
				break;
            case 7://������������Сֵ��Χ6000����
			{zujinminInt=6000;zujinmaxInt=Integer.MAX_VALUE;
		     re();                             }
				break;
			default:
				break;
			}
		 }

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		 }
	    });
	 
	   //sp3����
       //Ϊspinner3����ѡ��
       final String[] array3={"����","����","����","����","����"};
       ArrayAdapter<String> accountTypesAdapter3 = new ArrayAdapter<String>(this,
 		       android.R.layout.simple_spinner_item,array3); 
       accountTypesAdapter3.setDropDownViewResource(R.layout.spinnerstyle); //����spinner������ĵ��Զ�����ʽ
       sp3.setAdapter(accountTypesAdapter3); 
     //ʵ��Spinner3�ڳ�ʼ״̬��Ϊѡ��������ʾ�����͡���
       sp3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
    	   boolean b=false;
    	   public void onItemSelected(AdapterView<?> arg0, View arg1, int id,
   				long arg3) {
   			// TODO Auto-generated method stub
   			if(!b){((TextView)arg1).setText(" ����   ��"); };
       		b=true;//ʵ��Spinner1�ڳ�ʼ״̬��Ϊѡ��������ʾ����������
   			switch (id) {
				case 0://����
				{leiStrings=array3;  re();}
					break;
				case 1://���"����"
				{leiStrings=new String[]{"����"};//��������ɸѡ���ж�
			     re();                             }
					break;
				case 2://���"����"
				{leiStrings=new String[]{"����"};
			     re();                             }
					break;
				case 3://���"����"
				{leiStrings=new String[]{"����"};
			     re();                             }
					break;
				case 4://���"����"
				{leiStrings=new String[]{"����"};
			     re();                             }
					break;
				default:
					break;
				}
       }
		 public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		 }
       });
       
     
       //ʵ��title��������ť�¼�����
       searchbt.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//�����¼���д��
		  }
	   });
       //ʵ��title�Ĳ˵���ť�¼�����
       menubt.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//�����¼���д��
		   }
	   });
      
       //Ϊʵ��listView���б�����¼�����,���췽�������·�
       listView.setOnItemClickListener(this);
       
       
//����������                  	
   	//ʵ�֡�λ�á��������popupWindow
    
    position_Bt=(Button) findViewById(R.id.bt_position);
   	position_Bt.setOnClickListener(new OnClickListener()
   	       {
   		        @Override
   				public void onClick(View v) {
   		         
   		         initPopWindow(); //��������PopWindow����
   		        
   		          }
   			});

	}
  	
	//�˷�����ʵ�֡�λ�á���ť��������������PopWindow
	protected void initPopWindow() {
	
	   	//����popupWindow����
	    PopupWindow pt_popup = new PopupWindow(findViewById(R.id.mainLayout), 560, 700);
	    
		if(!open){
			open=true;
	        //pt_popup.setFocusable(true);  
			View view_position=LayoutInflater.from(getApplicationContext()).inflate(R.layout.positionstyle, null);
		 	//
		   	String[] areastring={"����"};
		  //����R.layout.positionstyle��Ӧ�Ľ��沼���ļ�
		    ListView arealistView = (ListView) view_position.findViewById(R.id.lv_area);  
		   ArrayAdapter<String> area_adapter = new ArrayAdapter<String>
		   (this, android.R.layout.simple_list_item_1, areastring);  
		   arealistView.setAdapter(area_adapter); 
			pt_popup.setContentView(view_position);
	        pt_popup.showAsDropDown(position_Bt); 
		}else{
			open=false;
			pt_popup.dismiss();
		}
	}

	//����re��������Ϊspinnerɸѡѡ��ʱˢ��listview�ķ���
	private void re() {
		// TODO Auto-generated method stub
		//�������ƣ�����������spinnerɸѡ���������ݲŷ���listItems
		simp_adapter.notifyDataSetChanged();//����notifyDataSetChanged��ˢ��listview
 		listItems.clear();//���listItems�����ݣ�������
 		for(int i=0;i<name.length;i++){
 			//if�������ж�
 		   if(zujinminInt<=zujin[i]&&zujin[i]<zujinmaxInt//��������ж�
 			  &&isIn(quyu[i],quyuStrings)&&isIn(leixing[i], leiStrings)){
 			Map<String,Object>listitem=new HashMap<String, Object>();
        	    listitem.put("pic", imageIds[i]);//ͼƬ
        	    listitem.put("name", name[i]);//С���ֵ�
        	    listitem.put("xinxi", xinxi[i]);//������Ϣ
        	    listitem.put("leixing", leixing[i]);//��������
        	    listitem.put("quyu", quyu[i]);//���ڵ���
        	    listitem.put("zujin", zujin[i]);//���
        	    listitem.put("time", time[i]);//����ʱ��
        	    listItems.add(listitem);
        	   }   
 	    }
	}

	//��������isIn���������ж�spinner��ѡ������listview����Ӧ���͵������Ƿ���ͬ����ʵ�֡�λ�á��͡����͡������ж�
	public static boolean isIn(String substring, String[] source) {
		 if (source == null || source.length == 0) {
		     return false;
		 }
		 for (int i = 0; i < source.length; i++) {
		     String aSource = source[i];
		 if (aSource.equals(substring)) {
		     return true;
		     }
		   }
		    return false;
		 }

	//ʵ��listView���б�����¼������ķ���
	public void onItemClick(AdapterView<?> parent, View view, int map, long id) 
	{
	   //�����¼���д��
	  
	}
	
}
