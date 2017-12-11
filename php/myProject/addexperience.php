<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$y=$_POST['d'];
$x=$_POST['n'];
$z=$_POST['r'];
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');
$query="INSERT INTO interview(name,data,RegNo) VALUES ('$x','$y','$z')";
mysql_query($query); 
echo "Successfully added your experience";
mysql_close($con);
?>