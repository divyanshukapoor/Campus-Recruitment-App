<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$r=$_POST['com'];
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');

$query="SELECT * FROM company WHERE name='$r' ";
$result=mysql_query($query);
$row = mysql_fetch_row($result);

echo $row[1].PHP_EOL;
echo $row[2].PHP_EOL;
echo $row[3].PHP_EOL;
echo $row[4].PHP_EOL;
echo $row[5].PHP_EOL;
echo $row[6].PHP_EOL;
mysql_close($con);
?>