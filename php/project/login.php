<?php
$x=$_POST['id'];
$y=$_POST['adpassword'];
$con=mysql_connect("localhost","root","");
mysql_select_db('dk');

if(empty($x)||empty($y))
{
echo "please fill all details";
}
else
{
$query="SELECT * FROM admin WHERE Id='$x' AND Password='$y' ";
$result=mysql_query($query);
$count=mysql_num_rows($result);
if($count==0){
echo "Login failed...Try again";
}
else
{
echo "Login Success...";
}
}
mysql_close($con);
?>