<?php
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');
$o="open";
$result = mysql_query("SELECT name FROM company WHERE Status='$o' ");
$storeArray = Array();
while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
    $storeArray[] =  $row['name']; 
    echo $row['name'].PHP_EOL; 
}
mysql_close($con);
?>