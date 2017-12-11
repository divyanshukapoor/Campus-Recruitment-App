<?php
$con=mysql_connect("localhost","root","");
mysql_select_db('dk');
$x="0";
$result = mysql_query("SELECT * FROM intern WHERE Verified='$x' ");

while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
    
    echo $row['Registration'].PHP_EOL; 
}
mysql_close($con);
?>