<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$var=$_POST['name'];
$val=$_POST['reg'];
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');
$result = mysql_query("SELECT data FROM interview WHERE name='$var' AND RegNo='$val' ");
$storeArray = Array();
while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
    $storeArray[] =  $row['data']; 
    echo $row['data'].PHP_EOL; 
}
mysql_close($con);
?>