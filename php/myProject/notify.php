<?php
$x="open";
$con=mysql_connect("localhost","root","");
mysql_select_db('temp');

$query="SELECT * FROM company WHERE Status='$x' ";
$result=mysql_query($query);
while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
    echo $row['name'].PHP_EOL; 
    echo $row['time'].PHP_EOL; 
}
mysql_close($con);
?>