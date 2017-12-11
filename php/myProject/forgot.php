<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$y=$_POST['reg_number'];
$x=$_POST['npassword'];


mysql_connect("localhost","root","");
mysql_select_db('temp');
$sqlinsert="UPDATE intern SET Password='$x' where Registration='$y' ";
$result=mysql_query($sqlinsert);
if($result)
 {
  echo "Password updates successfully";
 }
else 
  {
   echo "Password updation failed";
  }
?>