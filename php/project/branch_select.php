<?php
$con=mysql_connect("localhost","root","");
mysql_select_db('dk');
$result = mysql_query("SELECT Name FROM statistics");

while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
    
    echo $row['Name'].PHP_EOL; 
}
mysql_close($con);
?>