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