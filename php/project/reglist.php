<?php
$a=$_POST['comname'];
$con=mysql_connect("localhost","root","");
mysql_select_db('dk');
$result = mysql_query("SELECT Registration FROM intern WHERE Branch='$a' ");
while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
    
    echo $row['Registration'].PHP_EOL; 
}
mysql_close($con);
?>