<?php
$x=$_POST['name'];
$con=mysql_connect("localhost","root","");
mysql_select_db('dk');
$y="close";
if(empty($x))
{
echo "please fill all details";
}
else
{
$query1="SELECT * FROM Company WHERE Name='$x' ";
$result=mysql_query($query1);
$count=mysql_num_rows($result);
if($count==0){
echo "Company name not exist!!";
}
else
{
$query="UPDATE company SET Status='close'  WHERE name='$x' ";
$result=mysql_query($query) or die(mysql_error());
echo "Registration for company closed";
}
}
mysql_close($con);
?>