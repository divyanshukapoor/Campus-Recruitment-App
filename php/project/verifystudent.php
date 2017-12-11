<?php
$x=$_POST['reg'];
$y="1";
$con=mysql_connect("localhost","root","");
mysql_select_db('dk');
$result = mysql_query("UPDATE intern SET Verified='$y'  WHERE Registration='$x' ");

echo "Student Verified!!";
mysql_close($con);
?>