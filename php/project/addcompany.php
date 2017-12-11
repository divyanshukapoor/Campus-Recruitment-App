<?php
$a=$_POST['name'];
$b=$_POST['stipend'];
$c=$_POST['cpi'];
$d=$_POST['branch'];
$e=$_POST['designation'];
$f=$_POST['location'];
$g=$_POST['deadline'];
if(empty($a)||empty($b) || empty($c) || empty($d)||empty($e)||empty($f)||empty($g))
{
echo "please fill all the details";
}
else
{
$con=mysql_connect("localhost","root","");
mysql_select_db('dk');
$query="SELECT * FROM company WHERE Name='$a' " ;
$result=mysql_query($query);
$count=mysql_num_rows($result);
if($count==0){
$query1=" INSERT INTO company(Name,Branch,stipend,cpi,designation,location,time)VALUES ('$a','$d','$b','$c','$e','$f','$g') ";
mysql_query($query1);
echo "company successfully added!";
}
else 
{
echo "company already exists!";
}
mysql_close($con);
}
?>