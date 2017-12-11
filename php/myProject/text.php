<?php


$val="' OR ''='";
$val=mysql_real_escape_string($val);
/*foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  } */

$con=mysql_connect("localhost","root","");
mysql_select_db('temp'); 

$result = mysql_query("SELECT * FROM intern WHERE Registration='$val' ");

while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
    
    echo $row['Name'].PHP_EOL; 
    echo $row['Registration'].PHP_EOL;
    echo $row['Password'].PHP_EOL;
}

mysql_close($con);


?>