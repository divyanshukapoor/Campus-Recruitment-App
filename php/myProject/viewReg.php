<?php
foreach ($_POST as $key => $value) { 
    $_POST[$key] = mysql_real_escape_string($value); 
  }
$x=$_POST['company'];

$con=mysql_connect("localhost","root","");
mysql_select_db('temp');
$result = mysql_query("SELECT DISTINCT RegNo FROM interview WHERE name='$x' ");
$storeArray = Array();
while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
    $storeArray[] =  $row['RegNo']; 
    echo $row['RegNo'].PHP_EOL; 
}
mysql_close($con);
?>