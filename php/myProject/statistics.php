<?php

$con=mysql_connect("localhost","root","");
mysql_select_db('temp');
$result = mysql_query("SELECT * FROM statistics ");
$storeArray = Array();
while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
    
    echo $row['Name'].PHP_EOL; 
    echo $row['Total'].PHP_EOL;
    echo $row['Placed'].PHP_EOL;
}
mysql_close($con);
?>