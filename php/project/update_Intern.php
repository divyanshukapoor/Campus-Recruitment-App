<?php
$a=$_POST['reg'];
$b=$_POST['com'];
if(empty($a)||empty($b))
{
echo "please fill all details";
}
else
{
$tr="true";
$fl="false";
$con=mysql_connect("localhost","root","");
mysql_select_db('dk');
$query="SELECT isCompany FROM intern WHERE Registration='$a'";
$result=mysql_query($query);
$row=mysql_fetch_assoc($result);

if(!strcmp($row['isCompany'],"false")){
$query1="UPDATE intern SET Company='$b',isCompany='$tr' WHERE Registration='$a' ";
mysql_query($query1);
echo "Successfully submitted...";
}
else
{
echo "User Already Placed";
}
}
mysql_close($con);
?>