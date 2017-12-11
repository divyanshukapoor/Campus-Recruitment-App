<?php
$x=$_POST['reg'];
$con=mysql_connect("localhost","root","");
mysql_select_db('dk');
$result = mysql_query("SELECT * FROM intern WHERE Registration='$x' ");
$row = mysql_fetch_array($result, MYSQL_ASSOC) ;
echo $row['Name'].PHP_EOL;
echo $row['Registration'].PHP_EOL;
echo $row['cpi'].PHP_EOL;
echo $row['Branch'].PHP_EOL;
echo $row['10Persentage'].PHP_EOL;
echo $row['12Persentage'].PHP_EOL;
mysql_close($con);
?>