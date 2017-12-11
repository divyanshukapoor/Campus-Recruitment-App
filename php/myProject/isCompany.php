<?php

foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$a=$_POST['reg'];
$b=$_POST['com'];

$tr="true";
$fl="false";
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');
$query="SELECT isCompany FROM intern WHERE Registration='$a'";
$result=mysql_query($query);
$row=mysql_fetch_assoc($result);
echo $row['isCompany'];
if(!strcmp("false",$row['isCompany'])){

$query1="UPDATE intern SET Company='$b',isCompany='$tr' WHERE Registration='$a' ";
mysql_query($query1);
echo "Succesfully submitted...";
}
else{
echo "User Already Placed";
}
mysql_close($con);
?>