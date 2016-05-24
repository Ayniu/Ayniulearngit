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
	
        //添加一句话，测试查看Github远程哭是否可同步（2016.05.24--Ayniu--）

	Spinner sp1;//列表选择框sp1，sp2，sp3
	Spinner sp2;
	Spinner sp3;
	Button position_Bt;//"位置"按钮
	boolean open;
	ImageButton searchbt;
	ImageButton menubt;
	private ListView listView;
	private SimpleAdapter simp_adapter;
	List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>(); 
	String[] name;//以下为初始化listview中各数据
	String[] xinxi;
	String[] leixing;
	String[] quyu;
	int[] zujin;
	String[] time;//此处发布时间暂定为文本，等待后台数据
	int[] imageIds;//以上为初始化listview中各数据
	String[] quyuStrings;//用于条件筛选的“地区”的判断
	String[] leiStrings;//用于条件筛选的“类型”的判断
	int zujinminInt=0;//用于条件筛选的“租金”的最小值判断
	int zujinmaxInt=0;//用于条件筛选的“租金”的最大值判断
	
     //更新自12月05日，以下为有关各区街道的数组，
        String[] blockofTianhe={"兴华","石牌","沙河","珠吉","长兴","猎德","冼村","沙东",
			"车陂","林和","天园","员村","棠下","天河南","五山","新塘","龙洞","前进","黄村",
			"凤凰","元岗"};        //天河区各街道
	String[] blockofYuexiu={"洪桥","光塔","梅花村","流花","大东","华乐","东山","六榕",
			"矿泉","珠光","黄花岗","人民","北京","登峰","大塘","白云","建设","农林"}; 
	                             //越秀区各街道
	String[] blockofHaizhu={"新港","南石头","江海","瑞宝","华洲","南洲","官洲",
			"琶洲","凤阳","江南中","沙园","龙凤","海幢","素社","昌岗","赤岗","滨江",
			"南华西"};            //海珠区各街道
	String[] blockofBaiyun={"松洲","景泰","同德","黄石","棠景","同和","京溪","永平",
			"三元里","石井","均禾","新市","嘉禾","太和","钟落潭","江高","人和","金沙","云城",
			"鹤龙","石门","白云湖"}; //白云区各街道
	String[] blockofLiwan={"白鹤洞","东沙","花地","彩虹","昌华","金花","石围塘","海龙",
			"多宝","站前","龙津","华林","茶滘","中南","沙面","逢源","南源","东漖","冲口",
			"桥中","岭南","西村"}; //荔湾区各街道
	String[] blockofPanyu={"小谷围","新造","化龙","钟村","南村",
			"沙湾","大石","桥南","石碁","市桥","沙头","东环","石楼","洛浦","石壁","大龙"};
	                            //番禺区各街道
	String[] blockofHuangpu={"鱼珠","红山","穗东","文冲","南岗","荔联","长洲","大沙",
			"黄埔","东区","联和","永和","夏港","萝岗"}; //黄埔区各街道
	String[] blockofHuadu={"新华","花东","赤坭","炭步","狮岭","新雅","梯面","花山","秀全","花城"};
	                            //花都区各街道
	String[] blockofNansha={"南沙","横沥","万顷沙","黄阁","榄核","东涌","珠江","龙穴",
			"大岗"};             //南沙区各街道
	String[] blockofConghua={"江埔","街口","城郊","太平","吕田","良口","温泉","鳌头"};
	                            //从化区各街道
	String[] blockofZengcheng={"增江","朱村","荔城","中新","小楼","石滩","正果","派潭",
			"永宁","仙村","新塘"}; //增城区各街道
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.firstactivity);
        //实现自定义标题栏
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.titlestyle);
	
        sp1=(Spinner) findViewById(R.id.spinner1);
        sp2=(Spinner) findViewById(R.id.spinner2);
        sp3=(Spinner) findViewById(R.id.spinner3);
        searchbt=(ImageButton) findViewById(R.id.searchbt);
        menubt=(ImageButton) findViewById(R.id.menubt);
        listView=(ListView)findViewById(R.id.listView1);
        
      //实现listView的列表内容初始化
        name=new String[]{"和谐花园","爱德小区","科学路","北城路","安德园",
        		"雅贤居","富源小区","科技园","友爱花园","碧桂轩"};
        xinxi=new String[]{"3居|120㎡|","1居|30㎡|","2居|60㎡|",
        		"2居|50㎡|","3居|128㎡|","2居|80㎡|",
        		"3居|106㎡|","2居|78㎡|","3居|110㎡|","4居|130㎡|"};
        leixing=new String[]{"整租","整租","整租","隔断","次卧","主卧","整租","整租","次卧","次卧"};
        quyu=new String[]{"天河区","白云区","荔湾区","天河区","番禺区","海珠区","南沙区"
        		,"增城区","黄埔区","从化区"};
        zujin=new int[]{3600,2000,3200,4100,5500,6200,1800,800,1500,6600};
        time=new String[]{"12-02发布","12-02发布","12-02发布","12-02发布","12-02发布"
        		,"12-01发布","12-01发布","12-01发布","12-01发布","12-01发布"};
        imageIds=new int[]{R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house
        		,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house};
    
        //ListView的显示内容
 		simp_adapter=new SimpleAdapter(this, listItems, R.layout.listitem, 
 		 new String[]{"pic","name","xinxi","leixing","quyu","zujin","time"}, 
 		 new int[]{R.id.pic,R.id.name,R.id.xinxi,R.id.leixing,R.id.quyu,R.id.zujin,R.id.time});
 		listView.setAdapter(simp_adapter);
 		
        //sp1部分
        //为spinner1定义选项
        final String[] array1={"不限","天河区","白云区","海珠区","越秀区","荔湾区","黄埔区",
        		"花都区","番禺区","从化区","南沙区","增城区"};
        ArrayAdapter<String> accountTypesAdapter1 = new ArrayAdapter<String>(this,
        		android.R.layout.simple_spinner_item,array1); 
        accountTypesAdapter1.setDropDownViewResource(R.layout.spinnerstyle); //设置spinner下拉框的的自定义样式
        sp1.setAdapter(accountTypesAdapter1); 
        //实现spinner1各列表项的事件监听
        sp1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() { 
        	// TODO Auto-generated method stub
        	boolean b=false;
    		public void onItemSelected(AdapterView<?> arg0, View arg1, int id,
    				long arg3) {
    			// TODO Auto-generated method stub
        		
    			if(!b){((TextView)arg1).setText(" 位置   ▼"); };
        		b=true;//实现Spinner1在初始状态下为选择类型提示“租金↑↓”
    			switch (id) {
				case 0://不限
				{quyuStrings=array1;//用于条件筛选的判断
				    re();                         }
					break;
				case 1://点击“天河区”
				{quyuStrings=new String[]{"天河区"};//用于条件筛选的判断
			     re();                            }
					break;
				case 2://点击“白云区”
				{quyuStrings=new String[]{"白云区"};
			     re();                            }
					break;
				case 3://点击“海珠区”
				{quyuStrings=new String[]{"海珠区"};
			     re();                            }
				    break;
				case 4://点击“越秀区”
				{quyuStrings=new String[]{"越秀区"};
			     re();                            }
				    break;
				case 5://点击"荔湾区"
				{quyuStrings=new String[]{"荔湾区"};
			     re();                            }
					break;
				case 6://点击"黄埔区"
				{quyuStrings=new String[]{"黄埔区"};
			     re();                            }
					break;
				case 7://点击"花都区"
				{quyuStrings=new String[]{"花都区"};
			     re();                            }
					break;
				case 8://点击"番禺区"
				{quyuStrings=new String[]{"番禺区"};
			     re();                            }
					break; 					
				case 9://点击"从化区"
				{quyuStrings=new String[]{"从化区"};
			     re();                            }
					break;
				case 10://点击"南沙区"
				{quyuStrings=new String[]{"南沙区"};
			     re();                            }
					break;       
				case 11://点击"增城区"
				{quyuStrings=new String[]{"增城区"};
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
  
       //sp2部分
       //为spinner2定义选项
       String[] array2={"不限","500-1000","1000-2000","2000-3000",
    		   "3000-4000","4000-5000","5000-6000","6000以上"};
       final ArrayAdapter<String> accountTypesAdapter2 = new ArrayAdapter<String>(this,
    		android.R.layout.simple_spinner_item,array2); 
       accountTypesAdapter2.setDropDownViewResource(R.layout.spinnerstyle); 
       //设置spinner下拉框的的自定义样式
       sp2.setAdapter(accountTypesAdapter2); 
       //实现spinner2各列表项的事件监听
       sp2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
    	   boolean b=false;
		public void onItemSelected(AdapterView<?> arg0, View arg1, int id,
				long arg3) {
			// TODO Auto-generated method stub
			
			if(!b){((TextView)arg1).setText(" 租金   ▼"); };
    		b=true;//实现Spinner2在初始状态下为选择类型提示“租金↑↓”
			switch (id) {
			case 0://不限租金最大最小值范围
			{zujinminInt=0;zujinmaxInt=Integer.MAX_VALUE;//用于条件筛选的判断
		     re();                             }
				break;
			case 1://限制租金最大最小值范围500-1000
			{zujinminInt=500;zujinmaxInt=1000;
		     re();                             }
				break;
			case 2://限制租金最大最小值范围1000-2000
			{zujinminInt=1000;zujinmaxInt=2000;
		     re();                             }
				break;
            case 3://限制租金最大最小值范围2000-3000
			{zujinminInt=2000;zujinmaxInt=3000;
		     re();                             }
				break;
            case 4://限制租金最大最小值范围3000-4000
			{zujinminInt=3000;zujinmaxInt=4000;
		     re();                             }
				break;
            case 5://限制租金最大最小值范围4000-5000
			{zujinminInt=4000;zujinmaxInt=5000;
		     re();                             }
				break;
            case 6://限制租金最大最小值范围5000-6000
			{zujinminInt=5000;zujinmaxInt=6000;
		     re();                             }
				break;
            case 7://限制租金最大最小值范围6000以上
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
	 
	   //sp3部分
       //为spinner3定义选项
       final String[] array3={"不限","整租","主卧","次卧","隔断"};
       ArrayAdapter<String> accountTypesAdapter3 = new ArrayAdapter<String>(this,
 		       android.R.layout.simple_spinner_item,array3); 
       accountTypesAdapter3.setDropDownViewResource(R.layout.spinnerstyle); //设置spinner下拉框的的自定义样式
       sp3.setAdapter(accountTypesAdapter3); 
     //实现Spinner3在初始状态下为选择类型提示“类型”。
       sp3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
    	   boolean b=false;
    	   public void onItemSelected(AdapterView<?> arg0, View arg1, int id,
   				long arg3) {
   			// TODO Auto-generated method stub
   			if(!b){((TextView)arg1).setText(" 类型   ▼"); };
       		b=true;//实现Spinner1在初始状态下为选择类型提示“租金↑↓”
   			switch (id) {
				case 0://不限
				{leiStrings=array3;  re();}
					break;
				case 1://点击"整租"
				{leiStrings=new String[]{"整租"};//用于条件筛选的判断
			     re();                             }
					break;
				case 2://点击"主卧"
				{leiStrings=new String[]{"主卧"};
			     re();                             }
					break;
				case 3://点击"次卧"
				{leiStrings=new String[]{"次卧"};
			     re();                             }
					break;
				case 4://点击"隔断"
				{leiStrings=new String[]{"隔断"};
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
       
     
       //实现title的搜索按钮事件监听
       searchbt.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//具体事件待写入
		  }
	   });
       //实现title的菜单按钮事件监听
       menubt.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//具体事件待写入
		   }
	   });
      
       //为实现listView各列表项的事件监听,构造方法在最下方
       listView.setOnItemClickListener(this);
       
       
//！！！！！                  	
   	//实现“位置”点击淡出popupWindow
    
    position_Bt=(Button) findViewById(R.id.bt_position);
   	position_Bt.setOnClickListener(new OnClickListener()
   	       {
   		        @Override
   				public void onClick(View v) {
   		         
   		         initPopWindow(); //弹出淡出PopWindow方法
   		        
   		          }
   			});

	}
  	
	//此方法：实现“位置”按钮监听，弹出淡出PopWindow
	protected void initPopWindow() {
	
	   	//创建popupWindow对象
	    PopupWindow pt_popup = new PopupWindow(findViewById(R.id.mainLayout), 560, 700);
	    
		if(!open){
			open=true;
	        //pt_popup.setFocusable(true);  
			View view_position=LayoutInflater.from(getApplicationContext()).inflate(R.layout.positionstyle, null);
		 	//
		   	String[] areastring={"区域"};
		  //加载R.layout.positionstyle对应的界面布局文件
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

	//创建re方法，作为spinner筛选选择时刷新listview的方法
	private void re() {
		// TODO Auto-generated method stub
		//条件限制，将符合三个spinner筛选条件的数据才放入listItems
		simp_adapter.notifyDataSetChanged();//利用notifyDataSetChanged来刷新listview
 		listItems.clear();//清除listItems的内容，以重置
 		for(int i=0;i<name.length;i++){
 			//if做条件判断
 		   if(zujinminInt<=zujin[i]&&zujin[i]<zujinmaxInt//租金条件判断
 			  &&isIn(quyu[i],quyuStrings)&&isIn(leixing[i], leiStrings)){
 			Map<String,Object>listitem=new HashMap<String, Object>();
        	    listitem.put("pic", imageIds[i]);//图片
        	    listitem.put("name", name[i]);//小区街道
        	    listitem.put("xinxi", xinxi[i]);//房屋信息
        	    listitem.put("leixing", leixing[i]);//房屋类型
        	    listitem.put("quyu", quyu[i]);//所在地区
        	    listitem.put("zujin", zujin[i]);//租金
        	    listitem.put("time", time[i]);//发布时间
        	    listItems.add(listitem);
        	   }   
 	    }
	}

	//创建方法isIn，是用于判断spinner所选内容与listview中相应类型的内容是否相同，以实现“位置”和“类型”条件判断
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

	//实现listView各列表项的事件监听的方法
	public void onItemClick(AdapterView<?> parent, View view, int map, long id) 
	{
	   //具体事件待写入
	  
	}
	
}
