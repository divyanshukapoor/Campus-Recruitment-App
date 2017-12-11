<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$a=$_POST['address'];
$var=$_POST['value'];
$t="1";
$f="0";
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');
$query1="SELECT * FROM intern WHERE Registration='$a' ";
$result=mysql_query($query1);
$row = mysql_fetch_array($result, MYSQL_ASSOC);

if($var=="Academic"){
$result1=$row['stateAcademic'];
if($result1=="1"){
echo $row['stateAcademic'].PHP_EOL;
echo $row['10Board'].PHP_EOL;
echo $row['10School'].PHP_EOL;
echo $row['10Year'].PHP_EOL;
echo $row['10Persentage'].PHP_EOL;
echo $row['12Board'].PHP_EOL;
echo $row['12School'].PHP_EOL;
echo $row['10Year'].PHP_EOL;
echo $row['10Persentage'].PHP_EOL;
echo $row['1Sem'].PHP_EOL;
echo $row['2Sem'].PHP_EOL;
echo $row['3Sem'].PHP_EOL;
echo $row['4Sem'].PHP_EOL;
echo $row['5Sem'].PHP_EOL;
echo $row['6Sem'].PHP_EOL;
echo $row['7Sem'].PHP_EOL;
echo $row['8Sem'].PHP_EOL;
}
else
echo $f;
}
else
{
$result2=$row['statePersonal'];
if($result2=="1"){
echo $row['statePersonal'].PHP_EOL;
echo $row['Name'].PHP_EOL;
echo $row['Father'].PHP_EOL;
echo $row['Mother'].PHP_EOL;
echo $row['Email'].PHP_EOL;
echo $row['Contact'].PHP_EOL;
echo $row['Address'].PHP_EOL;
}
else
echo $f;
}
mysql_close($con);
?>