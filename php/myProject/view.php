<?php
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');
$result = mysql_query("SELECT DISTINCT name FROM interview");
$storeArray = Array();
while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
    $storeArray[] =  $row['name']; 
    echo $row['name'].PHP_EOL; 
}
mysql_close($con);
?>