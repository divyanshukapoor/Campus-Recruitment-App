<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$x=$_POST['reg'];
$y=$_POST['email'];
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');

if(empty($x) || empty($y))
{
if(empty($y))
echo "please fill all the details";
else
echo "please fill all the details";
}
else
{
$query="SELECT * FROM intern WHERE Registration='$x' AND Email='$y' ";
$result=mysql_query($query);
$count=mysql_num_rows($result);
if($count==0){
echo "Login Failed";
}
else
{
$query1="SELECT Password FROM intern WHERE Registration='$x' AND Email='$y' ";
$result1=mysql_query($query);
$values = mysql_fetch_assoc($result1);
echo $values['Password'];
}
}
mysql_close($con);
?>