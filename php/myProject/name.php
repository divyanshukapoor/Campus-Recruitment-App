<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$r=$_POST['reg_no'];
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');


$query="SELECT * FROM intern WHERE Registration='$r' ";
$result=mysql_query($query);
$row = mysql_fetch_array($result);


echo $row['Name'].PHP_EOL;
echo $row['Registration'].PHP_EOL;
echo $row['Verified'].PHP_EOL;
echo $row['Tpo_credit'].PHP_EOL;
echo $row['cpi'].PHP_EOL;
mysql_close($con);
?>