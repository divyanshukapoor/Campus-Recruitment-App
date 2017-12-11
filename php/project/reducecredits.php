<?php
$x=$_POST['reg'];
$y=$_POST['cre'];
$con=mysql_connect("localhost","root","");
mysql_select_db('dk');
if(empty($x)||empty($y))
{
echo "please fill all details";
}
else
{
$query="UPDATE intern SET Tpo_credit='$y'  WHERE Registration='$x' ";
$result=mysql_query($query);
echo "Successfully Updated";
}
mysql_close($con);
?>