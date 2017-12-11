<?php
$x=$_POST['reg'];
$con=mysql_connect("localhost","root","");
mysql_select_db('dk');
$query="SELECT * FROM intern WHERE Registration='$x' ";
$result=mysql_query($query);
$row = mysql_fetch_assoc($result);
$y=$row['Branch'];
$query="SELECT * FROM statistics WHERE Name='$y' ";
$result=mysql_query($query);
$row = mysql_fetch_assoc($result);
$Z=$row['Placed'];
$Z=$Z+1;
$query="UPDATE statistics SET Placed='$Z' WHERE NAME ='$y' " ;
$result=mysql_query($query);
mysql_close($con);
?>