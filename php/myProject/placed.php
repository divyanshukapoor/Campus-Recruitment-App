<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$x=$_POST['reg_no'];
$y=$_POST['company'];
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');

if(empty($x)||empty($y))
{
echo "please fill all the details";
}
else
{
$query="SELECT * FROM intern WHERE Registration='$x' ";
$result=mysql_query($query);
$count=mysql_num_rows($result);
if($count==0){
echo "No Such Details exists";
}
else
{
$query="UPDATE intern SET Company='$y' WHERE Registration='$x' ";
$result=mysql_query($query);
echo "Success";
}
}
mysql_close($con);
?>