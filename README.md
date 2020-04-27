# FirstLineCode
第一行代码（第三版）
##kotlin学习要点
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
