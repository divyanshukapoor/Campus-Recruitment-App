<?php
$con=mysql_connect("localhost","root","");
mysql_select_db('dk');
$x=$_POST['com'];
$result = mysql_query("SELECT * FROM intern WHERE Company='$x' ");

while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
    
    echo $row['Registration'].PHP_EOL; 
}
mysql_close($con);
?>