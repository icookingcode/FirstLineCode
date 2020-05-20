# FirstLineCode
第一行代码（第三版）
## kotlin学习要点
### 函数式API(Lambda表达式)
语法结构  
```
完整结构：
{参数1：参数类型,参数2：参数类型 ,..->函数体}
参数类型大多数可省略：
{参数1，参数2 -> 函数体}
Lambda只有一个参数，可用it代替参数，只保留函数体：
{it}
```  
*  当lambda表达式为函数的最后一个参数时，可将表达式移到括号外面；当仅有一个lambda表达式参数时，可将（）省略。  
fun(lambda)   <=>  fun()lambda  <=>  fun lambda  
### 空检测
加!!像Java一样抛出空异常，加?可不做处理返回值为 null或配合?:做空判断处理  
* obj?.do() 非空时执行  <=> if(obj!=null) obj.do()  
* a = obj?:b  <=> a = if(obj!=null) obj else b
* obj!!.do() 非空时执行，空时断言报错  <=> if(obj!=null) obj.do() else throws Exception

### 默认参数
```
/**
 * 默认参数 可选
 */
fun getUserInfo(name:String,age:Int ,sex:String="male",address:String)="姓名：$name  年龄：$age  性别：$sex  地址：$address"

//调用 有默认参数的可不传,没有默认值则必传  若默认参数不在最后,则必传
val res = getUserInfo("guchao",30,"male","焦作沁阳市")

//若不想传默认参数，但其后仍有参数，可通过键值对方式传参
val res = getUserInfo("guchao",30,address = "焦作沁阳市")
```
### 标准函数
* with  场景：连续调用通一个对象的多个方法
```
val result = with(obj){
    ....
    "value" //最后一行是返回值
}
```
* run  场景：同with,只是使用方式不一样，
```
val result = obj.run{
    //obj的上下文
    "value"  //最后一行是返回值
}
```
* apply  场景：使用方式同run,但无法指定返回值，自动返回对象本身
```
val result = obj.apply{
    //obj的上下文
}
//result == obj
```
* let  场景：使用方式同run，但不持有obj的上下文
```
val result = obj.let{
    //将obj对象传入 以it 可调用
    "value"  //最后一行是返回值
}
```
* repeat 常见：将一件事重复n遍执行
```
repeat(n){
    //需重复执行的代码
}
```
### 定义静态方法
* object 对象里的方法相当于静态方法
```
//Util.function() 即可调用
object Util{
    @JvmStatic
    fun function(){}
}
```
* 通过半生对象定义静态方法(实质是Util类中伴生对象的实例方法)
```
//Util.function() 即可调用
class Util{
    companion object {
        @JvmStatic  //该注释会将方法编译成真正的静态方法
        fun function(){}
    }
}
```
* 通过@JvmStatic注解定义(只能加在对象类 或伴生对象类中的方法上)
* 顶层方法（类之外的方法）  
### 关键字
 * 延时初始化 lateinit
```
class Demo{
    private lateinit var param:String //延迟初始化
    fun somemethod(){
        if(!::param.isInitialized){  //::param.isInitialized 来判断是否初始化过
            param = "HelloWorld"
        }
    }
}
```
 * 定义常量 const (const 只能在单例类、伴生对象或顶层方法中才能使用)
 * 密封类 sealed 好处：kotlin会自动检测密封类有哪些子类，并强制要求你将每个子类所对应的条件全部处理
  ```
//实现
sealed class Base()
class A :Base(){
}
class B :Base(){
}
//使用  不可能出现其他情况，必须实现Base所有子类所对应的条件
when(base:Base){
    A->...
    B->...
}
  ```
 * 运算符重载 operator 重新定义运算符的含义，实现对象的运算
  ```
//定义重载运算符的对象类
class Money(val value:Int){
    operator fun plus(money:Money):Money{
        val sum = value + money.value
        return Money(sum)
    }
}
  ```
  * a+b  调用 a.plus(b)
  * a-b  调用 a.minus(b)
  * a*b  调用 a.times(b)
  * a/b  调用 a.div(b)
  * a%b  调用 a.rem(b)
  * a++  调用 a.inc()
  * a--  调用 a.dec()
  * +av  调用 a.unaryPlus()
  * -a   调用 a.unaryMinus()
  * !a   调用 a.not()
  * a==b | a>b | a<b 调用 a.equals(b)
  * a>=b | a<=b  调用 a.compareTo(b)
  * a..b  调用 a.rangeTo(b)
  * a[b]  调用 a.get(b)
  * a[b]=c  调用 a.set(b,c)
  * a in b  调用 b.contains(a)
### 扩展函数
1. 扩展函数语法
```
fun ClassName.methodName(param1:Int, param2:Int):Int{
        ...
    return 0
}
```
### 高阶函数
如果一个函数接收另一个函数作为参数，或返回值是另一个函数，那么该函数就是高阶函数。  
* 高阶函数
```
//定义高阶函数
fun operation(p1: Int, p2: Int, lambda: (Int, Int) -> Int?): Int? {
    return lambda(p1, p2)
}

//定义函数 加法
val plus: (Int, Int) -> Int = { a: Int, b: Int -> a + b } //Lambda表达式
//定义函数减法
fun minus(a:Int,b: Int):Int{
    return a-b
}
//使用
val res = operation(5, 6, plus) //函数变量
val res1 = operation(5,1,::minus) //::minus 表示函数引用
val res2 = operation(5, 6) { p1, p2 -> p1 * p2 } //直接传入lambda表达式
```
* 内联函数(定义高阶函数时加上inline关键字声明即可)  
注意：内联函数可通过return进行函数返回，非内联函数只可通过return进行局部返回。  
```
//完全消除Lambda表达式带来的运行时开销
inline fun operation(p1: Int, p2: Int, lambda: (Int, Int) -> Int?): Int? {
    return lambda(p1, p2)
}
```
* nionline与crossinline
```
//nionline 作用:内联高阶函数接收多个函数类型参数时，进行内联排除
inline fun inlineTest(block1:()->Unit,noinline block2:()->Unit){
}

//crossinline 作用：相当于契约，保证高阶函数的匿名类实现中不允许使用return关键字进行函数返回
inline fun runRunnable(crossinline block:()->Unit){//不使用crossinline，则会编译报错
    val runnable = Runnable{
        block()
    }
    runnable.run()
}
```
### 泛型
* 定义泛型类
```
//类名后使用<>声明泛型
class MyClass<T>{
    fun method(param:T):T{
        return param
    }
}

//调用
val myClass = MyClass<Int>()
val result = myClass.method(110)
```  
* 定义泛型方法
```
//方法名前使用<> 声明泛型方法
fun <T> method(param:T):T{
    return param
}

//调用
val result = method<Int>(110)
```
* 对泛型类型进行约束
```
fun <T:Number> method(param:T){
    return param
}
```
### 委托
* 类委托  
精髓：将一个类的具体实现委托给另一个类去完成  
```
//创建接口
interface Base{
    fun print()
}
//创建被委托的类
class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}
// 通过关键字 by 建立委托类
class Derived(b: Base) : Base by b

fun main(args:Array<String>){
    val b = BaseImpl(110)
    Derived(b).print() // 输出 110
}
```
* 属性委托  
```
/**
 * Created by guc on 2020/5/7.
 * 描述：属性委托
 */
class Delegate {
    var propValue:Any? = "default"

    /**
     * [any] 指定任何类都可以使用该代理
     * [prop] 属性操作类，可获取各种属性相关的值
     */
    operator fun getValue(any: Any?,prop:KProperty<*>):Any?{
        return propValue
    }
    /**
     * [any] 指定任何类都可以使用该代理
     * [prop] 属性操作类，可获取各种属性相关的值
     * [value] 赋给委托属性的值
     */
    operator fun setValue(any: Any?,prop:KProperty<*>,value:Any?){
        propValue = value
    }
}
```
### infix函数
A to B  &lt;=&gt; A.to(B)  
```
//A.method(B)  等价于 A method B
infix fun String.beginWith(prefix: String) = startsWith(prefix)

string.beginWith(prefix) == string beginWith prefix
```
### 泛型的实化与泛型的协变
 * 泛型的实化允许获取泛型的实际类型（使用inline 和 reified 关键字）  
```
inline fun <reified T> startActivity(context: Context,block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}
```
 * 泛型的协变
```
interface MyClass<T>{
    //in位置：参数位  out位值：返回位
    fun method(param:T):T
}
```
   * out T :T只能出现在out位置,相当于禁用了set方法
   * in T :T只能出现在in位置  
   
协变：假如定义一个MyClass&lt;T&gt;的泛型类，其中A是B的子类，同时MyClass&lt;A&gt;又是MyClass&lt;B&gt;的子类型，那么我们就成MyClass在T这个泛型上的协变。  
实现：MyClass&lt;out T&gt; 指定T只可出现在out位置（即泛型类在其泛型类型的数据上只读）  
```
class SimpleData<out T>(val data:T?) {
    fun get(): T? = data
}
```
* 泛型的逆变  
逆变：假如定义一个MyClass&lt;T&gt;的泛型类，其中A是B的子类，同时MyClass&lt;B&gt;又是MyClass&lt;A&gt;的子类型，那么我们就成MyClass在T这个泛型上的协变。  
实现：MyClass&lt;in T&gt; 指定T只能出现在in位置（即泛型类在其泛型类型的数据上只可写，不可读）  
```
/* 
 * 描述：泛型类逆变
 */
interface Transformer<in T> {
    fun transform(t:T):String
}
```
## Android 
### Activity
* menu 使用  
1. 创建menu文件：res -> menu -> main.xml  
2. 重写activity onCreateOptionsMenu(menu: Menu?)方法  
```
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true 
    }
```  
3. 重写activity onOptionsItemSelected(item: MenuItem)方法
```
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item-> toast(this,"添加")
            R.id.remove_item-> toast(this,"移除")
        }
        return true
    }
```  
* Intent
显示  
```
 val intent = Intent(this,FirstActivity::class.java)
```  
隐式
```
//只有匹配到action 和category 才能启动activity
val intent = Intent("com.guc.firstlinecode.action.ACTION_FISRTACTIVITY")  //aciton
intent.addCategory("category")  //添加category  (可选，若不添加默认 android.intent.category.DEFAULT)

//相应activity需配置intent-filter ,aciton相同则可启动
  <intent-filter>
      <action android:name="com.guc.firstlinecode.action.ACTION_FISRTACTIVITY"/>  
      <category android:name="android.intent.category.DEFAULT"/>
      <category android:name="category"/>
   </intent-filter>
```
### Fragment
1. 创建Fragment类，通过<fragment>标签的android:name属性插入xml布局文件中  
2. 动态添加Fragment  
```
 private fun replaceFragment(
        containerId: Int,   //容器id （一般用FrameLayout）
        fragment: Fragment, //待添加的Fragment实例
        isAddToBackStack: Boolean = false    //true:添加到返回栈，及点击返回键时可移除该Fragment
    ) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        if (isAddToBackStack) transaction.addToBackStack(null)
        transaction.commit()
 }
```
#### 限定符
 * 大小
    * small ：提供给小屏幕
    * normal  ：提供给中等屏幕
    * large ：提供给大屏幕
    * xlarge  ：提供给超大屏幕
    * sw600dp ：最小宽度限定
 * 分辨率
   * ldpi  :120dpi以下
   * mdpi  ：120dpi-160dpi
   * hdpi  : 160dpi-240dpi
   * xhdpi  ：240dpi-320dpi
   * xxhdpi  ：320dpi-480dpi
 * 方向
   * land  :提供给横屏设备
   * port  :提供给竖屏设备
### 广播
广播创建的两种方式：  
1. 动态注册
  * 创建IntentFilter 
  * IntentFilter 添加需接收广播的action
  * 创建广播接收器 class Receiver :BroadcastReceiver(){}
  * 调用activity.registerReceiver 注册广播监听
  * 使用完后调用 activity.unregisterReceiver  取消注册的广播接收器  
2. 静态注册  
需在manifest配置文件中进行接收器的配置  
exported : true:表示允许接收本程序外的广播  
enabled : true:表示启用该广播接受器  
```
 <receiver android:name=".receiver.BootCompleteReceiver"
           android:exported="true"
           android:enabled="true">
           <intent-filter >
                <action android:name="android.intent.action.BOOT_COMPLETED"/>
           </intent-filter>
  </receiver>
```
3. 发送自定义广播
创建广播接收器，并配置
```
  <receiver android:name=".receiver.MyBroadcastReceiver"
            android:exported="true"
            android:enabled="true">
            <intent-filter android:priority="50">
                <action android:name="com.guc.firstlinecode.action.MY_BROADCAST"/>  <!-- 自定义广播action-->
            </intent-filter>
  </receiver>
  <!-- android:priority="50" 属性只对有序广播有效，数值越大，越先接收到广播-->
```
发送自定义广播     
```
        val intent = Intent("com.guc.firstlinecode.action.MY_BROADCAST")//创建指定action的Intent
        intent.setPackage(packageName)  //Android 8.0后静态注册的Receiver无法收到隐式广播，需指定package（发给指定应用）后，才能收到
        context.sendBroadcast(intent)
    //  有序广播可在onReceive()方法中通过调用abortBroadcast()终止广播传递
    //  context.sendOrderedBroadcast(intent,null)//发送有序广播

```
### 数据存储
3种数据持久化功能  
* 文件存储  
* SharedPreferences存储  
* 数据库存储  
1. 文件存储  
通过context.openFileOutput(fileName,mode)/context.openFileInput(fileName)  
默认存储路径：/data/data/<package name>/files/目录下  
2. SharedPreferences存储  
2.1 Context.getSharedPreferences()   路径：/data/data/<package name>/shared_prefs/目录下  
2.2 Activity.getPreferences()  自动以Activity的类名作为文件名进行存储  
实现步骤：  
* 调用SharedPreferences对象的Editor 方法获取SharedPreferences.Editor对象
* 向SharedPreferences.Editor对象中添加数据
* 调用SharedPreferences.Editor对象的apply()方法  
3. SQLite数据库  
SQLite是一款轻量级的关系型数据库。  
默认存储路径：/data/data/<package name>/databases/目录下  
实现步骤：  
 * 通过SQLiteOpenHelper创建SQLiteOpenHelper(dbHelper)对象
 * 通过SQLiteDatabase db = dbHelper.getWritableDatabase()方法创建数据库（没有则创建）
 * 通过SQLiteDatabase db实现数据库CRUD操作  
   * 添加：insert(String table, String nullColumnHack, ContentValues values) ，使用ContentValues对数据进行封装插入数据库  
   * 更新：update(String table, ContentValues values, String whereClause, String[] whereArgs)
   * 删除：delete(String table, String whereClause, String[] whereArgs)   
   * 查询： query(String table, String[] columns, String selection,
                  String[] selectionArgs, String groupBy, String having,
                  String orderBy)  
 * 使用sql操作数据库
   * 添加/更新/删除：db.execSQL(String sql,String[] args)  
   * 查询：val cursor = db.rawQuery(String sql,String[] args)
       ```
       db.execSQL("insert into Book (name,author,pages,price) values (?,?,?,?)",arrayOf("西游记","吴承恩","999","48.5"))
       db.execSQL("update Book set author =? ,pages=? where name=?",arrayOf("吴承恩","782","西游记"))
       db.execSQL("delete from Book where pages > ?",arrayOf("500"))
       db.rawQuery("select * from Book",null)
       ```
 * 数据库事务使用
  ```
    db.beginTransaction()//开启事务
    try{
        //block()
        db.setTransactionSuccessful()//事务已执行成功
    }catch(e:Exception){
    }finally{
        db.endTransaction()//结束事务
    }
    
  ```
![相关目录](https://github.com/icookingcode/FirstLineCode/blob/master/snapshoot/Snapshoot_20200508164848.png)
### ContentProvider (跨程序共享数据)
#### 运行时权限
* CALENDAR  日历权限
* CAMERA  相机权限
* CONTACTS 通讯录
* LOCATION 位置权限
* MICROPHONE 录音权限
* PHONE 打电话权限
* SENSORS 传感器权限
* SMS  短信权限
* STORAGE 存储权限  
1. ContextCompat.checkSelfPermission()检测是否有某个权限
```
//检测是否有打电话权限
ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED
```
2.  ActivityCompat.requestPermissions(activity,permissions,requestCode) 申请权限
```
//申请打电话权限
 ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CALL_PHONE),
                REQUEST_CODE
            )
```
3. override fun onRequestPermissionsResult() 处理申请结果
```
 override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call()
                }else{
                    ToastUtil.toast(this,"你拒绝了该权限")
                }
            }
        }
    }
```  
#### ContentResolver用法(访问其他程序的数据)
* Content URI的标准格式  
content://authority/path
```
content://<package name>.provider/table
```  
* Context.getContentResolver获取ContentResolver对象
```
val contentResolver = context.getContentResolver()
```
* ContentResolver对象提供增删改查方法
```
//查询
val cursor = contentResolver.query(
    uri,//内容uri
    projection,//查询列名
    selection,//约束条件
    selectionArgs,//占位符的值
    sortOrder//排序方式
  )
//插入
val values = contentValuesOf("column1" to "text" ,"column2" to "text")
contentResolver.insert(uri,values)
//更新
contentResolver.update(uri,values,selection,selectionArgs)
//删除
contentResolver.delete(uri,selection,selectionArgs)
```
#### 创建ContentProvider
1. 自定义class BookProvider : ContentProvider(){}，并实现其方法  
2. 定义Uri   
```
content://com.guc.firstlinecode.provider/Book
content://com.guc.firstlinecode.provider/Book/1
```  
  2.1 Uri通配符  
 * &#42;表示匹配任意字符串  
 * &#35;表示匹配任意长度的数字  
3. 通过UriMather类实现匹配内容URI的功能  
  3.1 内容Uri的MIME类型定义
 * 必须以vnd开头
 * 内容uri以路径结尾，后接 android.cursor.dir/ ；内容以id结尾则后接 android.cursor.item/  
 * 最后接上： vnd.&lt;authority&gt;.&lt;path&gt;
 ```
    vnd.android.cursor.dir/vnd.com.guc.firstlinecode.provider.Book}
    vnd.android.cursor.item/vnd.com.guc.firstlinecode.provider.Book}
 ```
4. 结合数据库SQLiteOpenHelper对象实现增删改成操作
5. Manifest配置该BookProvider
```
<provider
     android:authorities="com.guc.firstlinecode.provider"
     android:name=".provider.BookProvider"
     android:enabled="true"
     android:exported="true"/>
```  
### 手机多媒体
#### 通知  
* 创建通知渠道  
Android8.0引入通知渠道的概念  
```
  //1、获取NotificationManager
  val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
  //2、创建通知渠道 大于等于Android8.0
  if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
      val channel1 = NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_DEFAULT)
      val channel2 = NotificationChannel(channelId2, channelName2,NotificationManager.IMPORTANCE_HIGH)
      notificationManager.createNotificationChannels(listOf(channel1,channel2))
  }
```
<img src="https://github.com/icookingcode/FirstLineCode/blob/master/snapshoot/Screenshot_1588903657.png"  height="640" width="360">  

* 通知基本用法  
```
 val pi = PendingIntent.getActivity(this,0,Intent(this,ListViewActivity::class.java),0)
//创建一个Notification
 val notification = NotificationCompat.Builder(context, channelId).run { //channelId必须是已创建好的channel,否则无法显示通知
            setContentTitle("通知标题")
            setContentText("通知的详细信息，这里是具体内容啊")
            setSmallIcon(R.drawable.orange)  //显示在状态栏的图标
            setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large_orange))//拉下通知栏后的图标
            setContentIntent(pi)
            var notice = build()
            notice
        }
//使用NotificationManager,发送通知
 notificationManager.notify(1,notification)
```
* 给通知添加点击事件    
  * 创建PendingIntent
  * 调用 NotificationCompat.Builder对象.setContentIntent()即可
  * 控制通知消失（1、调用 NotificationCompat.Builder对象.setAutoCancel(true) 2、notificationManager.cancel(noticeId)）
* 通知高级用法  
调用 NotificationCompat.Builder对象.setStyle()方法，显示更多样式的通知  
  * NotificationCompat.BigTextStyle().bigText() ,长文本样式
  * NotificationCompat.BigPictureStyle().bigPicture() ,大图样式
####相机和相册
* 拍照  
```
//启动相机
val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
startActivityForResult(intent, REQUEST_CODE)
```  
说明：android 7.0及以上文件的uri需要通过FileProvider获取  
* 系统相册
```
 //打开文件选择器
 val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
 intent.addCategory(Intent.CATEGORY_OPENABLE)
 //指定只显示图片
 intent.type="image/*"
 startActivityForResult(intent, REQUEST_CODE)
```
#### 播放音频视频
* 播放音频 
  * 使用MediaPlayer播放音频（音频资源可放到main/assets目录  
  * 使用AssetManager来读取assets目录下的资源
  * 释放mediaPlayer资源（mediaPlayer.stop() mediaPlayer.release()）  
* 播放视频
  * 使用VideoView播放视频(视频资源放在res/raw目录下)
  ```
  VideoView.setVideoUri(Uri.parse("android.resource://" + getpackageName() + "/" + ${R.raw.movie}));
  ```
### Service
#### 异步消息处理
* Message:消息，携带数据  
* Handler:处理者，负责发送和处理消息  
* MessageQueue：消息队列  
* Looper： 消息管家，负责去除队列中的消息传递给Handler的handleMessage()方法  
#### AsyncTask 使用
作用：进行异步消息处理  
1. 定义class MyTask:AsyncTask<Unit,Int,Boolean>  
2. 创建MyTask对象，调用myTask.execute()执行任务  
#### Service
* startService  Service无法与Activity通讯
* bindService  Service可与Activity通讯
* 前台Service  即使退出应用也会一直运行，不用担心会被系统回收  
  * Android9.0之后，需添加权限&lt;uses-permission android:name="android.permission.FOREGROUND_SERVICE"/&gt;
### 网络技术
#### HttpURLConnection 请求网络
```
 val url = URL("https://www.baidu.com/")
 val connection = url.openConnection() as HttpURLConnection
 //请求方式GET/POST
 connection.requestMethod = "GET"
 //设置超时
 connection.connectTimeout = 8000
 connection.readTimeout = 8000
 //读取服务器返回
 val input = connection.inputStream
 //关闭连接
 connection.disconnect()
```
#### OkHttp
* 引入OkHttp
```
implementation 'com.squareup.okhttp3:okhttp:4.1.0'
```
* OkHttp使用GET示例
```
 val client = OkHttpClient()
 val request = Request.Builder().url("http://192.168.44.141:8099/get_data.xml").build()
 val response = client.newCall(request).execute()
 val responseData = response.body?.string()
```
* OkHttp使用POST示例  
```
val client = OkHttpClient()
val requstBody = FormBody.Builder()
                .add("username","admin")
                .add("password","admin")
                .build()
val request = Request.Builder().url("....")
            .post(requstBody)
            .build()
 val response = client.newCall(request).execute()
 val responseData = response.body?.string()
```  

#### xml解析与json解析
<img src="https://github.com/icookingcode/FirstLineCode/blob/master/snapshoot/Screenshot_1589944520.png"  height="640" width="360">
<img src="https://github.com/icookingcode/FirstLineCode/blob/master/snapshoot/Screenshot_1589958749.png"  height="640" width="360">  

* json解析（使用Gson）  

```
implementation 'com.google.code.gson:gson:2.8.5'
``` 


